package luj.persist.internal.database.load.impl;

import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.object.database.ObjectColumn;
import luj.persist.internal.data.set.DbSet;
import luj.persist.internal.database.load.DDatLoadSet;
import luj.persist.internal.database.load.json.DataJsonDecoder;

class SetImpl implements DDatLoadSet {

  public SetImpl(DbSet dbSet, DbDataLoaderAdapterImpl adapter) {
    _dbSet = dbSet;
    _meta = dbSet.getMeta();

    _dataJsonDecoder = adapter.getDataJsonDecoder();
    _adapter = adapter;
  }

  @Override
  public String getDataTableName() {
    return _meta.getDataTableName();
  }

  @Override
  public String getDataIdColumn() {
    return ObjectColumn.ID.getName();
  }

  @Override
  public String getDataValueColumn() {
    return ObjectColumn.VALUE.getName();
  }

  @Override
  public Iterable<Long> iter() {
    return _dbSet.getValue();
  }

  @Override
  public DecodeResult decodeData(Long dataId, String jsonStr) {
    JsonImpl json = new JsonImpl(dataId, jsonStr, _meta, _adapter);
    Object dataObj = _dataJsonDecoder.decode(json);

    return new DecodeResultImpl(dataObj, _adapter);
  }

  private final DbSet _dbSet;
  private final DbMeta _meta;

  private final DataJsonDecoder _dataJsonDecoder;
  private final DbDataLoaderAdapterImpl _adapter;
}
