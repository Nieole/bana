package org.bana.entity;

import io.vavr.collection.List;
import java.io.Serializable;

public interface Rule extends Serializable {

  List<String> toPolicy();
}
