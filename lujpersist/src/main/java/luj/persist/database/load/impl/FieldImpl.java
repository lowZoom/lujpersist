package luj.persist.database.load.impl;

import java.io.IOException;
import luj.persist.data.field.DataField;
import luj.persist.data.field.FieldImplGetter;
import luj.persist.data.field.FieldOp;
import luj.persist.data.meta.property.BeanProperty;
import luj.persist.database.load.json.DJsDecodeField;
import luj.persist.database.load.json.JsonReader;

class FieldImpl implements DJsDecodeField {

  public FieldImpl(BeanProperty beanProperty, Object dataObj, DbDataLoaderAdapterImpl adapter) {
    _beanProperty = beanProperty;
    _dataObj = dataObj;

    _fieldOp = beanProperty.getFieldOp();
    _fieldImplGetter = adapter.getFieldImplGetter();
  }

  @Override
  public String getFieldName() {
    return _beanProperty.getName();
  }

  @Override
  public void readJson(JsonReader reader) throws IOException {
    DataField fieldImpl = _fieldImplGetter.get(_beanProperty, _dataObj);
    _fieldOp.readJson(fieldImpl, reader);
  }

  private final BeanProperty _beanProperty;
  private final Object _dataObj;

  private final FieldOp<DataField> _fieldOp;
  private final FieldImplGetter _fieldImplGetter;
}
