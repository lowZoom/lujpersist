package luj.persist.anno.proc.bean.implementation;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import luj.generate.annotation.processing.ProcType;
import luj.generate.annotation.processing.SingleAnnoProc;

final class BeanImplementationImpl implements DbBeanImplGeneratorImpl.BeanImplementation {

  BeanImplementationImpl(TypeSpec.Builder implClass,
      MethodSpec.Builder implConstructor, SingleAnnoProc.Context ctx) {
    _procType = ctx.getProcessingType();

    _implClass = implClass;
    _implConstructor = implConstructor;

    _ctx = ctx;
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
    TypeSpec classSpec = _implClass
        .addMethod(_implConstructor.build())
        .build();

    String packageName = _procType.getPackageName();
    _ctx.writeToFile(packageName, classSpec);
  }

  private final ProcType _procType;

  private final TypeSpec.Builder _implClass;
  private final MethodSpec.Builder _implConstructor;

  private final SingleAnnoProc.Context _ctx;
}
