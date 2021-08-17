package org.bana.entity;

public class DefaultUserEntity extends UserEntity<String> {

  private String id;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String id() {
    return id;
  }
}
