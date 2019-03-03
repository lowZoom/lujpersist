package luj.persist.api.anno.proc.bean.implementation;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import luj.generate.annotation.process.type.ProcType;

final class BeanImplementationImpl implements DbBeanImplGeneratorImpl.BeanImplementation {

  BeanImplementationImpl(ProcType procType, Builder implClass, MethodSpec.Builder implConstructor) {
    _procType = procType;

    _implClass = implClass;
    _implConstructor = implConstructor;
  }

  @Override
  public List<DbBeanImplGeneratorImpl.BeanField> getFieldList() {
    return _procType.toElement().getEnclosedElements().stream()
        .map(e -> (ExecutableElement) e)
        .map(e -> new BeanFieldImpl(e, _implClass, _implConstructor))
        .collect(Collectors.toList());
  }

  @Override
  public void writeToFile() throws IOException {
    _procType.getPackage().writeToFile(_implClass
        .addMethod(_implConstructor.build())
        .build());
  }

  private final ProcType _procType;

  private final TypeSpec.Builder _implClass;
  private final MethodSpec.Builder _implConstructor;
}
