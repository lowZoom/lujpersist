package luj.persist.internal.data.root.statement.object.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldImplGetter;
import luj.persist.internal.data.meta.property.BeanProperty;
import luj.persist.internal.database.save.json.DJsEncodeField;
import luj.persist.internal.database.save.json.JsonWriter;

class DJsEncodeFieldImpl implements DJsEncodeField {

  public DJsEncodeFieldImpl(Object data, BeanProperty beanProperty,
      FieldImplGetter fieldImplGetter) {
    _data = data;
    _beanProperty = beanProperty;

    _fieldImplGetter = fieldImplGetter;
  }

  @Override
  public void writeToJson(JsonWriter writer) throws IOException {
    BeanProperty property = _beanProperty;
    String key = property.getName();

    DataField fieldImpl = _fieldImplGetter.get(property, _data);
    checkNotNull(fieldImpl, key);

    fieldImpl.writeJson(writer, key);
  }

  private final Object _data;
  private final BeanProperty _beanProperty;

  private final FieldImplGetter _fieldImplGetter;
}
