package luj.persist.anno.proc.bean.meta;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import luj.generate.annotation.process.type.ProcType;
import luj.persist.data.meta.DbMetaHolder;
import org.springframework.stereotype.Component;

final class DbMetaGeneratorImpl implements DbMetaGenerator {

  DbMetaGeneratorImpl(ProcType dbType) {
    _dbType = dbType;
  }

  @Override
  public void generate(String metaName) throws IOException {
    _dbType.getPackage().writeToFile(TypeSpec.classBuilder(metaName)
        //.addAnnotation(Generated.class)
        .addAnnotation(Component.class)
        .addModifiers(Modifier.FINAL)
        .superclass(getParamType(DbMetaHolder.class, _dbType.toTypeName()))
        .build());
  }

  private TypeName getParamType(Class<?> mainType, TypeName paramType) {
    return ParameterizedTypeName.get(ClassName.get(mainType), paramType);
  }

  private final ProcType _dbType;
}
