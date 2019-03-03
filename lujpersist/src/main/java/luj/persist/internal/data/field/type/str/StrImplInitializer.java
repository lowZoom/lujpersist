package luj.persist.internal.data.field.type.str;

import luj.data.type.impl.Data;
import luj.data.type.impl.Impl;
import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldOp;
import org.springframework.stereotype.Service;

@Service
public class StrImplInitializer {

  @SuppressWarnings("unchecked")
  public void init(Data data, FieldOp<?> op) {
    DbStr state = new DbStr();
    DbStrImpl newImpl = new DbStrImpl(state, (FieldOp<DataField>) op);

    Impl.set(data, newImpl);
  }
}
