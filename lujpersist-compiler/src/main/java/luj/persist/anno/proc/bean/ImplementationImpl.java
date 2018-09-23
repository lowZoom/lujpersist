package luj.persist.anno.proc.bean;

import luj.generate.annotation.process.SingleAnnoProc;
import luj.persist.anno.proc.bean.factory.DbBeanFactoryGenerator;

import java.io.IOException;

final class ImplementationImpl implements DatabaseAllGeneratorImpl.Implementation {

  ImplementationImpl(String implName, SingleAnnoProc.Context ctx,
      DbBeanFactoryGenerator.Factory beanFactoryGeneratorFactory) {
    _implName = implName;
    _ctx = ctx;

    _beanFactoryGeneratorFactory = beanFactoryGeneratorFactory;
  }

  @Override
  public void generateFactory(String factoryName) throws IOException {
    DbBeanFactoryGenerator generator = _beanFactoryGeneratorFactory.instance(_ctx);
    generator.generate(factoryName, _implName);
  }

  private final String _implName;
  private final SingleAnnoProc.Context _ctx;

  private final DbBeanFactoryGenerator.Factory _beanFactoryGeneratorFactory;
}
