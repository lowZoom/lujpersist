package luj.persist.api.anno.proc.bean.property;

import com.squareup.javapoet.TypeName;
import javax.lang.model.element.ExecutableElement;

final class BeanFieldImpl implements DbBeanPropertyListGeneratorImpl.BeanField {

  BeanFieldImpl(ExecutableElement element) {
    _element = element;
  }

  @Override
  public String getName() {
    return _element.getSimpleName().toString();
  }

  @Override
  public TypeName getType() {
    return TypeName.get(_element.getReturnType());
  }

  private final ExecutableElement _element;
}
