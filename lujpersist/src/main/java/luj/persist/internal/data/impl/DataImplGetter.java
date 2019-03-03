package luj.persist.internal.data.impl;

import luj.data.type.impl.Data;

public interface DataImplGetter {

  <T> T get(Data data);
}
