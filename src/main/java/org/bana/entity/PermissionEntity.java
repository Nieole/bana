package org.bana.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * base permission entity
 *
 * @param <ID>
 */
@MappedSuperclass
public abstract class PermissionEntity<ID extends Serializable> implements BaseEntity<ID> {

  @Column(length = 200)
  private String type;
  @Column(length = 200)
  private String name;
  @Column(length = 200)
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
