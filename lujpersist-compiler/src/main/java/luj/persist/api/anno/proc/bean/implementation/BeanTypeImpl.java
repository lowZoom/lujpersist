package luj.persist.api.anno.proc.bean.implementation;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;
import luj.data.type.impl.Data;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.generate.annotation.process.type.ProcType;

final class BeanTypeImpl implements DbBeanImplGeneratorImpl.BeanType {

  BeanTypeImpl(SingleAnnoProc.Context ctx) {
    _ctx = ctx;
    _dbType = ctx.getProcessingType();
  }

  @Override
  public DbBeanImplGeneratorImpl.BeanImplementation createImplementation(String implName) {
    TypeSpec.Builder classBuilder = TypeSpec.classBuilder(implName)
        .addModifiers(Modifier.FINAL)
        .superclass(Data.class)
        .addSuperinterface(_dbType.toTypeName());

    MethodSpec.Builder ctorBuilder = MethodSpec.constructorBuilder();
    return new BeanImplementationImpl(_ctx.getProcessingType(), classBuilder, ctorBuilder);
  }

  private final ProcType _dbType;

  private final SingleAnnoProc.Context _ctx;
}
