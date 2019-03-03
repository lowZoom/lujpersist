package luj.persist.internal.data.impl;

import java.util.function.Function;
import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.data.type.impl.Impl;

@Internal
class DataImplGetterImpl implements DataImplGetter {

  public DataImplGetterImpl() {
    _getter = Impl.getter();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(Data data) {
    return (T) _getter.apply(data);
  }

  private final Function<Data, Object> _getter;
}
