package luj.persist.data.meta.property;

import java.util.function.Function;
import luj.data.type.impl.Data;
import luj.persist.data.field.DataField;
import luj.persist.data.field.FieldOp;

class MetaImpl implements BeanProperty {

  public void setName(String name) {
    _name = name;
  }

  public void setGetter(Function<Object, Data> getter) {
    _getter = getter;
  }

  public void setFieldOp(FieldOp<DataField> fieldOp) {
    _fieldOp = fieldOp;
  }

  @Override
  public String getName() {
    return _name;
  }

  @Override
  public Function<Object, Data> getGetter() {
    return _getter;
  }

  @Override
  public FieldOp<DataField> getFieldOp() {
    return _fieldOp;
  }

  private String _name;

  private Function<Object, Data> _getter;
  private FieldOp<DataField> _fieldOp;
}
