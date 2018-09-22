package luj.persist.anno.proc.bean.property;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import luj.generate.annotation.processing.ProcType;
import luj.generate.annotation.processing.SingleAnnoProc;

final class BeanTypeImpl implements DbBeanPropertyListGeneratorImpl.BeanType {

  BeanTypeImpl(SingleAnnoProc.Context ctx) {
    _ctx = ctx;
    _procType = ctx.getProcessingType();
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
    String packageName = _procType.getPackageName();
    _ctx.writeToFile(packageName, listClass);
  }

  private final ProcType _procType;

  private final SingleAnnoProc.Context _ctx;
}
