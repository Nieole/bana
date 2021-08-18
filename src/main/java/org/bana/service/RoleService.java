package org.bana.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.bana.adapter.DataAdapter;
import org.bana.entity.PermissionEntity;
import org.bana.entity.RoleEntity;
import org.casbin.jcasbin.main.Enforcer;

public abstract class RoleService<Entity extends RoleEntity<ID>, ID extends Serializable> extends
    BaseService<Entity, ID> {

  protected final PermissionService permissionService;

  public <E extends PermissionEntity<I>, I extends Serializable> RoleService(
      DataAdapter<Entity, ID> dataAdapter, Enforcer enforcer,
      PermissionService<E, I> permissionService) {
    super(dataAdapter, enforcer);
    this.permissionService = permissionService;
  }

  @Override
  public boolean delete(ID id) {
    enforcer.deleteRole(id(id));
    return super.delete(id);
  }

  @Override
  public boolean delete(Entity entity) {
    enforcer.deleteRole(entity.id());
    return super.delete(entity);
  }

  @Override
  public boolean batchDelete(Collection<Entity> entities) {
    entities.forEach(entity -> enforcer.deleteRole(entity.id()));
    return super.batchDelete(entities);
  }

  @Override
  public boolean batchDeleteById(Collection<ID> ids) {
    ids.forEach(id -> enforcer.deleteRole(id(id)));
    return super.batchDeleteById(ids);
  }

  /**
   * addPermissionForRole adds a permission for a role. Returns false if the role already has the
   * permission (aka not affected).
   *
   * @param role       the role.
   * @param permission the permission, usually be (obj, act). It is actually the rule without the
   *                   subject.
   * @return succeeds or not.
   */
  public <E extends PermissionEntity<I>, I extends Serializable> boolean addPermissionForRole(
      Entity role, Collection<E> permission) {
    return enforcer.addPermissionForUser(role.id(), permission.parallelStream().map(E::id).collect(
        Collectors.toList()));
  }

  /**
   * updatePermissionForRole updates a permission for a role. Returns false if the role already has
   * the permission (aka not affected).
   *
   * @param role          the role.
   * @param oldPermission the old permission.
   * @param newPermission the new permission.
   * @return succeeds or not.
   */
  public <E extends PermissionEntity<I>, I extends Serializable> boolean updatePermissionForRole(
      Entity role, Collection<E> oldPermission, Collection<E> newPermission) {
    return enforcer.updatePermissionForUser(role.id(),
        oldPermission.parallelStream().map(E::id).collect(
            Collectors.toList()),
        newPermission.parallelStream().map(E::id).collect(Collectors.toList()));
  }

  /**
   * deletePermissionForRole deletes a permission for a role. Returns false if the role does not
   * have the permission (aka not affected).
   *
   * @param role       the role.
   * @param permission the permission, usually be (obj, act). It is actually the rule without the
   *                   subject.
   * @return succeeds or not.
   */
  public <E extends PermissionEntity<I>, I extends Serializable> boolean deletePermissionForRole(
      Entity role, Collection<E> permission) {
    return enforcer.deletePermissionForUser(role.id(),
        permission.parallelStream().map(E::id).collect(
            Collectors.toList()));
  }

  /**
   * deletePermissionsForRole deletes permissions for a role. Returns false if the user or role does
   * not have any permissions (aka not affected).
   *
   * @param role the role.
   * @return succeeds or not.
   */
  public boolean deletePermissionsForRole(Entity role) {
    return enforcer.deletePermissionForUser(role.id());
  }

  /**
   * hasPermissionForRole determines whether a role has a permission.
   *
   * @param role       the role.
   * @param permission the permission, usually be (obj, act). It is actually the rule without the
   *                   subject.
   * @return whether the user has the permission.
   */
  public <E extends PermissionEntity<I>, I extends Serializable> boolean hasPermissionForRole(
      Entity role, Collection<E> permission) {
    return enforcer.hasPermissionForUser(role.id(), permission.parallelStream().map(E::id).collect(
        Collectors.toList()));
  }

  /**
   * getImplicitPermissionsForUser gets implicit permissions for a role. Compared to
   * getPermissionsForUser(), this function retrieves permissions for inherited roles. For example:
   * p, admin, data1, read p, alice, data2, read g, alice, admin
   * <p>
   * getPermissionsForUser("alice") can only get: [["alice", "data2", "read"]]. But
   * getImplicitPermissionsForUser("alice") will get: [["admin", "data1", "read"], ["alice",
   * "data2", "read"]].
   *
   * @param role the role.
   * @return implicit permissions for a role.
   */
  public <E extends PermissionEntity<I>, I extends Serializable> Collection<E> getImplicitPermissionsForRole(
      Entity role) {
    return permissionService.batchSelect(enforcer.getImplicitPermissionsForUser(role.id())
        .parallelStream()
        .map(list -> list.get(list.size() - 1))
        .collect(Collectors.toSet()));
  }
}
