package org.bana.entity;

import java.io.Serializable;

/**
 * base permission entity
 *
 * @param <ID>
 */
public abstract class PermissionEntity<ID extends Serializable> implements BaseEntity<ID> {

  private String type;
  private String name;
  private String action;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
