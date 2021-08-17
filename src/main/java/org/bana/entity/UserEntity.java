package org.bana.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * base user entity
 *
 * @param <ID> ID
 */
@MappedSuperclass
public abstract class UserEntity<ID extends Serializable> implements BaseEntity<ID> {

  @Column(length = 200)
  private String name;
  @Column(length = 500)
  private String password;
  @Column(length = 200)
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
