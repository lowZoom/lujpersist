package luj.persist.internal.data.object;

import luj.persist.internal.data.meta.DbMeta;

public class DbObject {

  public DbObject(Long dataId, DbMeta meta) {
    _dataId = dataId;
    _meta = meta;
  }

  public boolean isInDatabase() {
    return _inDatabase;
  }

  public void setInDatabase(boolean inDatabase) {
    _inDatabase = inDatabase;
  }

  public Long getDataId() {
    return _dataId;
  }

  public DbMeta getMeta() {
    return _meta;
  }

  private boolean _inDatabase;

  private final Long _dataId;
  private final DbMeta _meta;
}
