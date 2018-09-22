package luj.persist.anno.proc.bean.property;

import luj.generate.annotation.processing.SingleAnnoProc;

final class DbBeanPropertyListGeneratorFactoryImpl implements DbBeanPropertyListGenerator.Factory {

  @Override
  public DbBeanPropertyListGenerator instance(SingleAnnoProc.Context ctx) {
    BeanTypeImpl beanType = new BeanTypeImpl(ctx);
    return new DbBeanPropertyListGeneratorImpl(beanType);
  }
}
