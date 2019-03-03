package luj.persist.internal.database.load.impl;

import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.object.DataCreateAndInitializer;
import luj.persist.internal.database.load.json.DJsDecodeJson;
import luj.persist.internal.database.load.json.DJsDecodeObject;

class JsonImpl implements DJsDecodeJson {

  public JsonImpl(Long dataId, String jsonStr, DbMeta dbMeta, DbDataLoaderAdapterImpl adapter) {
    _dataId = dataId;
    _jsonStr = jsonStr;
    _dbMeta = dbMeta;

    _dataCreateAndInitializer = adapter.getDataCreateAndInitializer();
    _adapter = adapter;
  }

  @Override
  public String getJsonStr() {
    return _jsonStr;
  }

  @Override
  public DJsDecodeObject createDataObject() {
    Object data = _dataCreateAndInitializer.createAndInit(_dbMeta, _dataId);
    return new ObjectImpl(data, _dbMeta, _adapter);
  }

  private final Long _dataId;
  private final String _jsonStr;
  private final DbMeta _dbMeta;

  private final DataCreateAndInitializer _dataCreateAndInitializer;
  private final DbDataLoaderAdapterImpl _adapter;
}
