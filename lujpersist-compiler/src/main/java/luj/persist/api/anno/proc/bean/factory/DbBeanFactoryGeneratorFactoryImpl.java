package luj.persist.api.anno.proc.bean.factory;


import luj.generate.annotation.process.SingleAnnoProc;

final class DbBeanFactoryGeneratorFactoryImpl implements DbBeanFactoryGenerator.Factory {

  @Override
  public DbBeanFactoryGenerator instance(SingleAnnoProc.Context ctx) {
    return new DbBeanFactoryGeneratorImpl(ctx.getProcessingType());
  }
}
