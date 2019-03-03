package luj.persist.internal.data.set.impl;

import luj.data.type.JSet;
import luj.persist.internal.data.impl.DataImplGetter;
import luj.persist.internal.data.set.DbSet;
import luj.persist.internal.data.set.DbSetGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DbSetGetterImpl implements DbSetGetter {

  @Override
  public DbSet get(JSet set) {
    DbSetImpl impl = _dataImplGetter.get(set);
    return impl.getDbSet();
  }

  @Autowired
  private DataImplGetter _dataImplGetter;
}
