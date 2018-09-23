package luj.persist.anno.proc.bean.meta;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import luj.generate.annotation.process.ProcType;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.persist.data.meta.DbMetaHolder;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.io.IOException;

final class DbMetaGeneratorImpl implements DbMetaGenerator {

  DbMetaGeneratorImpl(SingleAnnoProc.Context ctx) {
    _ctx = ctx;
    _dbType = ctx.getProcessingType();
  }

  @Override
  public void generate(String metaName) throws IOException {
    TypeSpec metaClass = TypeSpec.classBuilder(metaName)
        //.addAnnotation(Generated.class)
        .addAnnotation(Component.class)
        .addModifiers(Modifier.FINAL)
        .superclass(getParamType(DbMetaHolder.class, _dbType.toTypeName()))
        .build();

    String packageName = _dbType.getPackageName();
    _ctx.writeToFile(packageName, metaClass);
  }

  private TypeName getParamType(Class<?> mainType, TypeName paramType) {
    return ParameterizedTypeName.get(ClassName.get(mainType), paramType);
  }

  private final ProcType _dbType;

  private final SingleAnnoProc.Context _ctx;
}
