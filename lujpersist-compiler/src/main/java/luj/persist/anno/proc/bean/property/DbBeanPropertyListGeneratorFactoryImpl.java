package luj.persist.anno.proc.bean.property;

import luj.generate.annotation.process.SingleAnnoProc;

final class DbBeanPropertyListGeneratorFactoryImpl implements DbBeanPropertyListGenerator.Factory {

  @Override
  public DbBeanPropertyListGenerator instance(SingleAnnoProc.Context ctx) {
    BeanTypeImpl beanType = new BeanTypeImpl(ctx.getProcessingType());
    return new DbBeanPropertyListGeneratorImpl(beanType);
  }
}
