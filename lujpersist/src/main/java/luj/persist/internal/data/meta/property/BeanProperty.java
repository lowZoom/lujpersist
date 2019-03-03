package luj.persist.internal.data.meta.property;

import java.util.function.Function;
import luj.data.type.impl.Data;
import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldOp;

public interface BeanProperty {

  String getName();

  Function<Object, Data> getGetter();

  FieldOp<DataField> getFieldOp();
}
