package luj.persist.data.field.type.str;

import luj.data.type.JStr;
import luj.data.type.impl.Impl;
import luj.persist.data.impl.DataImplGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class StrImplGetOrCreator {

  public DbStrImpl getOrCreate(JStr str) {
    DbStrImpl impl = _dataImplGetter.get(str);
    if (impl != null) {
      return impl;
    }

    DbStr state = new DbStr();
    DbStrImpl newImpl = new DbStrImpl(state, getFieldOp());

    Impl.set(str, newImpl);
    return newImpl;
  }

  @SuppressWarnings("unchecked")
  private <T> T getFieldOp() {
    return (T) _dbStrOp;
  }

  @Autowired
  private DataImplGetter _dataImplGetter;

  @Autowired
  private DbStrOp _dbStrOp;
}
