package org.bana.service;

import java.io.Serializable;
import java.util.Collection;
import org.bana.adapter.DataAdapter;
import org.bana.entity.BaseEntity;
import org.casbin.jcasbin.main.Enforcer;

public abstract class BaseService<Entity extends BaseEntity<ID>, ID extends Serializable> {

  protected final DataAdapter<Entity, ID> dataAdapter;
  protected final Enforcer enforcer;

  public BaseService(DataAdapter<Entity, ID> dataAdapter, Enforcer enforcer) {
    this.dataAdapter = dataAdapter;
    this.enforcer = enforcer;
  }

  public DataAdapter<Entity, ID> getDataAdapter() {
    return dataAdapter;
  }

  public Enforcer getEnforcer() {
    return enforcer;
  }

  /**
   * id to ID
   *
   * @param id id
   * @return ID
   */
  public abstract ID fromId(String id);

  /**
   * ID to id
   *
   * @param id id
   * @return id
   */
  public abstract String id(ID id);

  public boolean save(Entity entity) {
    return dataAdapter.save(entity);
  }

  public boolean batchSave(Collection<Entity> entities) {
    return dataAdapter.batchSave(entities);
  }

  public Entity select(ID id) {
    return dataAdapter.select(id);
  }

  public Collection<Entity> batchSelect(Collection<ID> ids) {
    return dataAdapter.batchSelect(ids);
  }

  public boolean update(Entity entity) {
    return dataAdapter.update(entity);
  }

  public boolean batchUpdate(Collection<Entity> entities) {
    return dataAdapter.batchUpdate(entities);
  }

  public boolean delete(Entity entity) {
    return dataAdapter.delete(entity);
  }

  public boolean delete(ID id) {
    return dataAdapter.delete(id);
  }

  public boolean batchDelete(Collection<Entity> entities) {
    return dataAdapter.batchDelete(entities);
  }

  public boolean batchDeleteById(Collection<ID> ids) {
    return dataAdapter.batchDeleteById(ids);
  }
}
