package org.bana.adapter;

import io.vavr.collection.Traversable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.bana.entity.Rule;
import org.casbin.jcasbin.exception.CasbinAdapterException;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.FilteredAdapter;

public interface Adapter extends FilteredAdapter {
  /**
   * the filter class. Enforcer only accept this filter currently.
   */
  public static class Filter {

    public String[] p;
    public String[] g;
  }

  List<? extends Rule> transformToCasbinRule(Model model);

  void setIsFiltered(Boolean isFiltered);

  default void loadFilteredPolicy(Model model, Object filter) throws CasbinAdapterException{
    if (Objects.isNull(filter)){
      loadPolicy(model);
      return;
    }
    if (!(filter instanceof Filter)) {
      throw new CasbinAdapterException("Invalid filter type.");
    }
    try {
      io.vavr.collection.List.ofAll(loadAllRule())
          .distinct()
          .map(Rule::toPolicy)
          .toMap(Traversable::head, list -> {
            if (!filterCasbinRule(list, (Filter) filter)) {
              return Collections.singletonList(list.tail().toJavaList());
            } else {
              return Collections.<List<String>>emptyList();
            }
          }).forEach((k, v) -> model.model.get(k.substring(0, 1)).get(k).policy.addAll(v));
      setIsFiltered(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  default void loadPolicy(Model model){
    io.vavr.collection.List.ofAll(loadAllRule())
        .distinct()
        .map(Rule::toPolicy)
        .toMap(io.vavr.collection.List::head, list -> Collections.singletonList(
            list.tail().toJavaList()))
        .forEach((k, v) -> model.model.get(k.substring(0, 1)).get(k).policy.addAll(v));
    setIsFiltered(false);
  }

  default void removePolicy(String sec, String ptype, List<String> rule){
    if (rule.isEmpty()) {
      return;
    }
    removeFilteredPolicy(sec, ptype, 0, rule.toArray(new String[0]));
  }

  List<Rule> loadAllRule();

  default boolean filterCasbinRule(io.vavr.collection.List<String> policy, Filter filter) {
    String[] filterSlice = new String[]{};
    switch (policy.get(0)) {
      case "p":
        filterSlice = filter.p;
        break;
      case "g":
        filterSlice = filter.g;
        break;
      default:
        break;
    }
    return filterWords(policy, filterSlice);
  }

  default boolean filterWords(io.vavr.collection.List<String> policy, String[] filter) {
    boolean skipLine = false;
    for (int i = 0; i < filter.length; i++) {
      if (filter[i].length() > 0 && !Objects.equals(filter[i].trim(), policy.get(i))) {
        skipLine = true;
        break;
      }
    }
    return skipLine;
  }
}
