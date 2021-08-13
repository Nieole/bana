package org.bana.service;

import java.io.Serializable;
import org.bana.entity.DefaultRoleEntity;
import org.bana.entity.PermissionEntity;
import org.casbin.jcasbin.main.Enforcer;

public class DefaultRoleService extends RoleService<DefaultRoleEntity, String> {

  public <E extends PermissionEntity<I>, I extends Serializable> DefaultRoleService(
      DataAdapter<DefaultRoleEntity, String> dataAdapter, Enforcer enforcer,
      PermissionService<E, I> permissionService) {
    super(dataAdapter, enforcer, permissionService);
  }

  @Override
  public String fromId(String id) {
    return id;
  }

  @Override
  public String id(String id) {
    return id;
  }
}
