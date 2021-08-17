package org.bana.entity;

import java.io.Serializable;

/**
 * base entity
 *
 * @param <ID>
 */
public interface BaseEntity<ID extends Serializable> extends Serializable {

  /**
   * convert id to string
   *
   * @return id
   */
  String id();
}
