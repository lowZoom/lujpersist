package luj.persist.data.set.impl;

import luj.data.type.JSet;
import luj.persist.data.impl.DataImplGetter;
import luj.persist.data.set.DbSet;
import luj.persist.data.set.DbSetGetter;
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
