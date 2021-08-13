package org.bana.entity;

import java.io.Serializable;

/**
 * base role entity
 *
 * @param <ID>
 */
public abstract class RoleEntity<ID extends Serializable> implements BaseEntity<ID> {

  private String name;
  private ID parentId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ID getParentId() {
    return parentId;
  }

  public void setParentId(ID parentId) {
    this.parentId = parentId;
  }
}
