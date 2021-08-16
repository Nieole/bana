package org.bana.entity;

import io.vavr.collection.List;
import java.util.Map;
import java.util.Objects;
import org.bana.utils.StringUtils;
import org.casbin.jcasbin.model.Assertion;
import org.casbin.jcasbin.model.Model;

public class CasbinRule {
  private String ptype;
  private String v0;
  private String v1;
  private String v2;
  private String v3;
  private String v4;
  private String v5;
  private String v6;
  private String v7;

  public String getPtype() {
    return ptype;
  }

  public void setPtype(String ptype) {
    this.ptype = ptype;
  }

  public String getV0() {
    return v0;
  }

  public void setV0(String v0) {
    this.v0 = v0;
  }

  public String getV1() {
    return v1;
  }

  public void setV1(String v1) {
    this.v1 = v1;
  }

  public String getV2() {
    return v2;
  }

  public void setV2(String v2) {
    this.v2 = v2;
  }

  public String getV3() {
    return v3;
  }

  public void setV3(String v3) {
    this.v3 = v3;
  }

  public String getV4() {
    return v4;
  }

  public void setV4(String v4) {
    this.v4 = v4;
  }

  public String getV5() {
    return v5;
  }

  public void setV5(String v5) {
    this.v5 = v5;
  }

  public String getV6() {
    return v6;
  }

  public void setV6(String v6) {
    this.v6 = v6;
  }

  public String getV7() {
    return v7;
  }

  public void setV7(String v7) {
    this.v7 = v7;
  }

  public List<String> toPolicy(){
    List<String> policy = List.of(ptype);
    List.of(v0,v1,v2,v3,v4,v5,v6,v7)
        .filter(StringUtils::hasText)
        .peek(policy::append);
    return policy;
  }

  public static List<CasbinRule> transformToCasbinRule(Model model) {
    return List.ofAll(model.model.values())
        .flatMap(Map::values)
        .flatMap(CasbinRule::rule);
  }

  private static List<CasbinRule> rule(Assertion assertion){
    if (assertion.policy.isEmpty()){
      return List.empty();
    }
    return List.ofAll(assertion.policy)
        .map(p -> {
          CasbinRule rule = new CasbinRule();
          rule.setPtype(assertion.key);
          rule.setV0(p.get(0));
          if (p.size() >= 2) {
            rule.setV1(p.get(1));
          }
          if (p.size() >= 3) {
            rule.setV2(p.get(2));
          }
          if (p.size() >= 4) {
            rule.setV3(p.get(3));
          }
          if (p.size() >= 5) {
            rule.setV4(p.get(4));
          }
          if (p.size() >= 6) {
            rule.setV5(p.get(5));
          }
          if (p.size() >= 7) {
            rule.setV6(p.get(6));
          }
          if (p.size() >= 8) {
            rule.setV7(p.get(7));
          }
          return rule;
        });
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CasbinRule that = (CasbinRule) o;
    return Objects.equals(ptype, that.ptype) && Objects.equals(v0, that.v0)
        && Objects.equals(v1, that.v1) && Objects.equals(v2, that.v2)
        && Objects.equals(v3, that.v3) && Objects.equals(v4, that.v4)
        && Objects.equals(v5, that.v5) && Objects.equals(v6, that.v6)
        && Objects.equals(v7, that.v7);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ptype, v0, v1, v2, v3, v4, v5, v6, v7);
  }
}