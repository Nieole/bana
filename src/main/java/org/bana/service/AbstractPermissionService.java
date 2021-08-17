package org.bana.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.bana.adapter.DataAdapter;
import org.bana.entity.PermissionEntity;
import org.casbin.jcasbin.main.Enforcer;

public abstract class AbstractPermissionService<Entity extends PermissionEntity<ID>, ID extends Serializable> extends
    BaseService<Entity, ID> {

  public AbstractPermissionService(DataAdapter<Entity, ID> dataAdapter,
      Enforcer enforcer) {
    super(dataAdapter, enforcer);
  }

  @Override
  public boolean delete(Entity entity) {
    enforcer.deletePermission(entity.id());
    return super.delete(entity);
  }

  @Override
  public boolean delete(ID id) {
    enforcer.deletePermission(id(id));
    return super.delete(id);
  }

  @Override
  public boolean batchDelete(Collection<Entity> entities) {
    enforcer.deletePermission(
        entities.parallelStream().map(Entity::id).collect(Collectors.toList()));
    return super.batchDelete(entities);
  }

  @Override
  public boolean batchDeleteById(Collection<ID> ids) {
    enforcer.deletePermission(ids.parallelStream().map(this::id).collect(Collectors.toList()));
    return super.batchDeleteById(ids);
  }

  /**
   * deletePermission deletes a permission. Returns false if the permission does not exist (aka not
   * affected).
   *
   * @param permission the permission, usually be (obj, act). It is actually the rule without the
   *                   subject.
   * @return succeeds or not.
   */
  public boolean deletePermission(Collection<Entity> permission) {
    return enforcer.deletePermission(
        permission.parallelStream().map(Entity::id).collect(Collectors.toList()));
  }

}
