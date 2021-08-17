package org.bana.entity;

public class DefaultPermissionEntity extends PermissionEntity<String> {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String id() {
    return id;
  }
}
