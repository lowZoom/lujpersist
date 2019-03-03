package luj.persist.internal.database.load.impl;

import luj.persist.internal.database.load.DDatLoadDatabase;

class QueryResultImpl implements DDatLoadDatabase.QueryResult {

  public QueryResultImpl(Long dataId, String jsonStr) {
    _dataId = dataId;
    _jsonStr = jsonStr;
  }

  @Override
  public Long getDataId() {
    return _dataId;
  }

  @Override
  public String getJsonStr() {
    return _jsonStr;
  }

  private final Long _dataId;

  private final String _jsonStr;
}
