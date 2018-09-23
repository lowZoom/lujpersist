package luj.persist.anno.proc.bean.factory;

import com.squareup.javapoet.*;
import luj.generate.annotation.process.ProcType;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.persist.data.object.DataFactory;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.io.IOException;

final class DbBeanFactoryGeneratorImpl implements DbBeanFactoryGenerator {

  DbBeanFactoryGeneratorImpl(SingleAnnoProc.Context ctx) {
    _ctx = ctx;
    _dbType = ctx.getProcessingType();
  }

  @Override
  public void generate(String factoryName, String implName) throws IOException {
    TypeName dbTypeName = _dbType.toTypeName();

    TypeSpec factoryClass = TypeSpec.classBuilder(factoryName)
        .addAnnotation(Component.class)
        .addModifiers(Modifier.FINAL)
        .addSuperinterface(getFactoryType(dbTypeName))
        .addMethod(buildCreate(dbTypeName, implName))
        .build();

    _ctx.writeToFile(_dbType.getPackageName(), factoryClass);
  }

  private TypeName getFactoryType(TypeName dbType) {
    return ParameterizedTypeName.get(ClassName.get(DataFactory.class), dbType);
  }

  private MethodSpec buildCreate(TypeName dbType, String implName) {
    return MethodSpec.methodBuilder("create")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .returns(dbType)
        .addStatement("return new $L()", implName)
        .build();
  }

  private final SingleAnnoProc.Context _ctx;

  private final ProcType _dbType;
}
