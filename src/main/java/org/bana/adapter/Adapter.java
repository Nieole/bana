package org.bana.adapter;

import java.util.List;
import org.bana.entity.Rule;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.FilteredAdapter;

public interface Adapter extends FilteredAdapter {
  List<? extends Rule> transformToCasbinRule(Model model);
}
