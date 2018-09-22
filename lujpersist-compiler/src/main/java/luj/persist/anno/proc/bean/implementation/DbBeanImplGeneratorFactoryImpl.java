package luj.persist.anno.proc.bean.implementation;

import luj.generate.annotation.processing.SingleAnnoProc;

final class DbBeanImplGeneratorFactoryImpl implements DbBeanImplGenerator.Factory {

  @Override
  public DbBeanImplGenerator instance(SingleAnnoProc.Context ctx) {
    BeanTypeImpl beanType = new BeanTypeImpl(ctx);
    return new DbBeanImplGeneratorImpl(beanType);
  }
}
