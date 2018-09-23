package luj.persist.anno.proc.bean;

import luj.generate.annotation.process.SingleAnnoProc;

final class DatabaseAllGeneratorFactoryImpl implements DatabaseAllGenerator.Factory {

  @Override
  public DatabaseAllGenerator instance(SingleAnnoProc.Context ctx) {
    BeanTypeImpl type = new BeanTypeImpl(ctx);
    return new DatabaseAllGeneratorImpl(type);
  }
}
