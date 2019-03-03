package luj.persist.internal.data.object;

import luj.data.type.impl.Data;

public interface DbObjectGetter {

  DbObject get(Data data);
}
