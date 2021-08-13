package org.bana.entity;

import java.io.Serializable;

/**
 * base user entity
 *
 * @param <ID> ID
 */
public abstract class UserEntity<ID extends Serializable> implements BaseEntity<ID> {

  private String name;
  private String password;
  private String account;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }
}
