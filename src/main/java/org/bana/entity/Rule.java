package org.bana.entity;

import io.vavr.collection.List;
import java.io.Serializable;
import org.casbin.jcasbin.model.Model;

public interface Rule extends Serializable {

  List<String> toPolicy();
}
