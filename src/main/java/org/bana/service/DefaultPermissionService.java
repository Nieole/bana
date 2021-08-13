package org.bana.service;

import org.bana.adapter.DataAdapter;
import org.bana.entity.DefaultPermissionEntity;
import org.casbin.jcasbin.main.Enforcer;

public class DefaultPermissionService extends PermissionService<DefaultPermissionEntity, String> {

  public DefaultPermissionService(
      DataAdapter<DefaultPermissionEntity, String> dataAdapter,
      Enforcer enforcer
  ) {
    super(dataAdapter, enforcer);
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
