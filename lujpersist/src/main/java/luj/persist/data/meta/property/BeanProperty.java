package luj.persist.data.meta.property;

import java.util.function.Function;
import luj.data.type.impl.Data;
import luj.persist.data.field.DataField;
import luj.persist.data.field.FieldOp;

public interface BeanProperty {

  String getName();

  Function<Object, Data> getGetter();

  FieldOp<DataField> getFieldOp();
}
