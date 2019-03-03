package luj.persist.internal.data.meta.property;

import java.lang.reflect.Method;

class MetaBuilder {

  public MetaBuilder(Method propertyMethod) {
    _result = new MetaImpl();

    _propertyMethod = propertyMethod;
  }

  public Method getPropertyMethod() {
    return _propertyMethod;
  }

  public MetaImpl getResult() {
    return _result;
  }

  private final MetaImpl _result;

  private final Method _propertyMethod;
}
