package luj.persist.internal.database.load.impl;

import java.util.Collection;
import javax.sql.DataSource;
import luj.data.type.JSet;
import luj.persist.internal.data.field.FieldImplGetter;
import luj.persist.internal.data.object.DataCreateAndInitializer;
import luj.persist.internal.data.object.DbObjectGetter;
import luj.persist.internal.data.set.DbSet;
import luj.persist.internal.data.set.DbSetGetter;
import luj.persist.internal.database.jdbc.JdbcQueryRunner;
import luj.persist.internal.database.load.DbDataLoader;
import luj.persist.internal.database.load.json.DataJsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DbDataLoaderAdapterImpl implements DbDataLoader.Adapter {

  @Override
  public <T> Collection<T> load(DataSource source, JSet set) {
    DatabaseImpl database = new DatabaseImpl(source, _jdbcQueryRunner);
    DbSet dbSet = _dbSetGetter.get(set);
    return _dbDataLoader.load(database, new SetImpl(dbSet, this));
  }

  public DataJsonDecoder getDataJsonDecoder() {
    return _dataJsonDecoder;
  }

  public FieldImplGetter getFieldImplGetter() {
    return _fieldImplGetter;
  }

  public DataCreateAndInitializer getDataCreateAndInitializer() {
    return _dataCreateAndInitializer;
  }

  public DbObjectGetter getDbObjectGetter() {
    return _dbObjectGetter;
  }

  @Autowired
  private JdbcQueryRunner _jdbcQueryRunner;

  @Autowired
  private DbSetGetter _dbSetGetter;

  @Autowired
  private DbDataLoader _dbDataLoader;

  @Autowired
  private DataJsonDecoder _dataJsonDecoder;

  @Autowired
  private FieldImplGetter _fieldImplGetter;

  @Autowired
  private DataCreateAndInitializer _dataCreateAndInitializer;

  @Autowired
  private DbObjectGetter _dbObjectGetter;
}
