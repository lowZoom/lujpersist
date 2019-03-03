package luj.persist.internal.database.load.impl;

import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.meta.property.BeanProperty;
import luj.persist.internal.database.load.json.DJsDecodeField;
import luj.persist.internal.database.load.json.DJsDecodeObject;

class ObjectImpl implements DJsDecodeObject {

  public ObjectImpl(Object data, DbMeta dbMeta, DbDataLoaderAdapterImpl adapter) {
    _data = data;
    _dbMeta = dbMeta;

    _adapter = adapter;
  }

  @Override
  public List<DJsDecodeField> getFieldList() {
    return _dbMeta.getPropertyList().stream()
        .map(this::toField)
        .collect(Collectors.toList());
  }

  @Override
  public Object getData() {
    return _data;
  }

  private DJsDecodeField toField(BeanProperty property) {
    return new FieldImpl(property, _data, _adapter);
  }

  private final Object _data;
  private final DbMeta _dbMeta;

  private final DbDataLoaderAdapterImpl _adapter;
}
