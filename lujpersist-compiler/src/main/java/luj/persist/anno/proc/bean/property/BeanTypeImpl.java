package luj.persist.anno.proc.bean.property;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import luj.generate.annotation.process.type.ProcType;

final class BeanTypeImpl implements DbBeanPropertyListGeneratorImpl.BeanType {

  BeanTypeImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public TypeName toTypeName() {
    return _procType.toTypeName();
  }

  @Override
  public List<DbBeanPropertyListGeneratorImpl.BeanField> getFieldList() {
    return _procType.toElement().getEnclosedElements().stream()
        .map(e -> new BeanFieldImpl((ExecutableElement) e))
        .collect(Collectors.toList());
  }

  @Override
  public void savePropertyList(TypeSpec listClass) throws IOException {
    _procType.getPackage().writeToFile(listClass);
  }

  private final ProcType _procType;
}
