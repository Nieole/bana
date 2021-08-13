package org.bana.entity;

public class DefaultRoleEntity extends RoleEntity<String> {

  private String id;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String id() {
    return id;
  }
}
