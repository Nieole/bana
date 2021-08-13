package org.bana.adapter;

import java.io.Serializable;
import java.util.Collection;
import org.bana.entity.BaseEntity;

public interface DataAdapter<DATA extends BaseEntity<ID>, ID extends Serializable> {

  /**
   * save data
   *
   * @param data data
   * @return result
   */
  boolean save(DATA data);

  /**
   * batch save
   *
   * @param data data
   * @return result
   */
  boolean batchSave(Collection<DATA> data);

  /**
   * select by id
   *
   * @param id id
   * @return data
   */
  DATA select(ID id);

  /**
   * select by ids
   *
   * @param ids ids
   * @return datas
   */
  Collection<DATA> batchSelect(Collection<ID> ids);

  /**
   * update data
   *
   * @param data data
   * @return result
   */
  boolean update(DATA data);

  /**
   * batch update
   *
   * @param data data
   * @return result
   */
  boolean batchUpdate(Collection<DATA> data);

  /**
   * delete data
   *
   * @param data data
   * @return result
   */
  boolean delete(DATA data);

  /**
   * delete data
   *
   * @param id id
   * @return result
   */
  boolean delete(ID id);

  /**
   * batch delete
   *
   * @param data data
   * @return result
   */
  boolean batchDelete(Collection<DATA> data);

  /**
   * batch delete
   *
   * @param ids ids
   * @return result
   */
  boolean batchDeleteById(Collection<ID> ids);
}
