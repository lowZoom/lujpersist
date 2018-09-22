package luj.persist.database.load.impl;

import luj.data.type.impl.Data;
import luj.persist.data.object.DbObject;
import luj.persist.data.object.DbObjectGetter;
import luj.persist.database.load.DDatLoadSet;

class DecodeResultImpl implements DDatLoadSet.DecodeResult {

  public DecodeResultImpl(Object dataObj, DbDataLoaderAdapterImpl adapter) {
    _dataObj = dataObj;
    _dbObjectGetter = adapter.getDbObjectGetter();
  }

  @Override
  public void markInDatabase() {
    DbObject dbObj = _dbObjectGetter.get((Data) _dataObj);
    dbObj.setInDatabase(true);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getDataObject() {
    return (T) _dataObj;
  }

  private final Object _dataObj;

  private final DbObjectGetter _dbObjectGetter;
}
