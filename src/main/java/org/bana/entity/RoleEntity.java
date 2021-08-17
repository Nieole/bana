package org.bana.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * base role entity
 *
 * @param <ID>
 */
@MappedSuperclass
public abstract class RoleEntity<ID extends Serializable> implements BaseEntity<ID> {

  @Column(length = 200)
  private String name;
  @Column(length = 40)
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
