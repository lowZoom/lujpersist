package luj.persist.internal.data.root.statement.object.impl;

import java.util.List;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.object.DbObject;
import luj.persist.internal.data.object.database.ObjectColumn;
import luj.persist.internal.data.root.statement.object.OStatMakeObject;
import luj.persist.internal.database.save.json.DJsEncodeField;
import luj.persist.internal.database.save.json.DataJsonEncoder;

class OStatMakeObjectImpl implements OStatMakeObject {

  public OStatMakeObjectImpl(DbObject dbObject, DbMeta dbMeta,
      List<DJsEncodeField> fieldList, DataJsonEncoder dataJsonEncoder) {
    _dbObject = dbObject;
    _dbMeta = dbMeta;

    _fieldList = fieldList;
    _dataJsonEncoder = dataJsonEncoder;
  }

  @Override
  public String getTableName() {
    return _dbMeta.getDataTableName();
  }

  @Override
  public String getIdColumn() {
    return ObjectColumn.ID.getName();
  }

  @Override
  public String getValColumn() {
    return ObjectColumn.VALUE.getName();
  }

  @Override
  public Long getDataId() {
    return _dbObject.getDataId();
  }

  @Override
  public boolean isInDatabase() {
    return _dbObject.isInDatabase();
  }

  @Override
  public String toJson() {
    return _dataJsonEncoder.encode(_fieldList);
  }

  private final DbObject _dbObject;
  private final DbMeta _dbMeta;

  private final List<DJsEncodeField> _fieldList;
  private final DataJsonEncoder _dataJsonEncoder;
}
