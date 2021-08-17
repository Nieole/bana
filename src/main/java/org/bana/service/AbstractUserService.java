package org.bana.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.bana.adapter.DataAdapter;
import org.bana.entity.RoleEntity;
import org.bana.entity.UserEntity;
import org.casbin.jcasbin.main.Enforcer;

public abstract class AbstractUserService<Entity extends UserEntity<ID>, ID extends Serializable> extends
    BaseService<Entity, ID> {

  protected final AbstractRoleService roleService;

  public <R extends RoleEntity<RI>, RI extends Serializable> AbstractUserService(
      DataAdapter<Entity, ID> dataAdapter, Enforcer enforcer,
      AbstractRoleService<R, RI> roleService
  ) {
    super(dataAdapter, enforcer);
    this.roleService = roleService;
  }

  @Override
  public boolean delete(ID id) {
    enforcer.deleteUser(id(id));
    return super.delete(id);
  }

  @Override
  public boolean delete(Entity entity) {
    enforcer.deleteUser(entity.id());
    return super.delete(entity);
  }

  @Override
  public boolean batchDelete(Collection<Entity> entities) {
    entities.forEach(entity -> enforcer.deleteUser(entity.id()));
    return super.batchDelete(entities);
  }

  @Override
  public boolean batchDeleteById(Collection<ID> ids) {
    ids.forEach(id -> enforcer.deleteUser(id(id)));
    return super.batchDeleteById(ids);
  }

  /**
   * getRolesForUser gets the roles that a user has.
   *
   * @param user the user.
   * @return the roles that the user has.
   */
  public <E extends RoleEntity<I>, I extends Serializable> Collection<E> getRolesForUser(
      Entity user) {
    return roleService.batchSelect(enforcer.getRolesForUser(user.id()));
  }

  /**
   * getUsersForRole gets the users that has a role.
   *
   * @param role the role.
   * @return the users that has the role.
   */
  public <E extends RoleEntity<I>, I extends Serializable> Collection<Entity> getUserForRole(
      E role) {
    return batchSelect(
        enforcer.getUsersForRole(role.id()).stream().map(this::fromId).collect(Collectors.toSet()));
  }


  /**
   * hasRoleForUser determines whether a user has a role.
   *
   * @param user the user.
   * @param role the role.
   * @return whether the user has the role.
   */
  public <E extends RoleEntity<I>, I extends Serializable> boolean hasRoleForUser(Entity user,
      E role) {
    return enforcer.hasRoleForUser(user.id(), role.id());
  }

  /**
   * addRoleForUser adds a role for a user. Returns false if the user already has the role (aka not
   * affected).
   *
   * @param user the user.
   * @param role the role.
   * @return succeeds or not.
   */
  public <E extends RoleEntity<I>, I extends Serializable> boolean addRoleForUser(Entity user,
      E role) {
    return enforcer.addRoleForUser(user.id(), role.id());
  }

  /**
   * deleteRoleForUser deletes a role for a user. Returns false if the user does not have the role
   * (aka not affected).
   *
   * @param user the user.
   * @param role the role.
   * @return succeeds or not.
   */
  public <E extends RoleEntity<I>, I extends Serializable> boolean deleteRoleForUser(Entity user,
      E role) {
    return enforcer.deleteRoleForUser(user.id(), role.id());
  }

  /**
   * deleteRolesForUser deletes all roles for a user. Returns false if the user does not have any
   * roles (aka not affected).
   *
   * @param user the user.
   * @return succeeds or not.
   */
  public boolean deleteRolesForUser(Entity user) {
    return enforcer.deleteRolesForUser(user.id());
  }

  /**
   * getImplicitRolesForUser gets implicit roles that a user has. Compared to getRolesForUser(),
   * this function retrieves indirect roles besides direct roles. For example: g, alice, role:admin
   * g, role:admin, role:user
   * <p>
   * getRolesForUser("alice") can only get: ["role:admin"]. But getImplicitRolesForUser("alice")
   * will get: ["role:admin", "role:user"].
   *
   * @param user the user.
   * @return implicit roles that a user has.
   */
  public <E extends RoleEntity<I>, I extends Serializable> Collection<E> getImplicitRolesForUser(
      Entity user) {
    return roleService.batchSelect(enforcer.getImplicitRolesForUser(user.id()));
  }

  /**
   * getImplicitUsersForRole gets implicit users for a role.
   *
   * @param role the role.
   * @return implicit users that a role has.
   */
  public <E extends RoleEntity<I>, I extends Serializable> Collection<Entity> getImplicitUsersForRole(
      E role) {
    return batchSelect(
        enforcer.getImplicitUsersForRole(role.id()).parallelStream().map(this::fromId)
            .collect(Collectors.toList()));
  }
}
