package luj.persist.data.object;

import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.persist.data.impl.DataImplGetter;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
class DbObjectGetterImpl implements DbObjectGetter {

  @Override
  public DbObject get(Data data) {
    DbObjectImpl impl = _dataImplGetter.get(data);
    return impl.getDbObject();
  }

  @Autowired
  private DataImplGetter _dataImplGetter;
}
