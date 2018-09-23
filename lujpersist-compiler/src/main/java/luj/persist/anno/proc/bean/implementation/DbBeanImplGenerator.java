package luj.persist.anno.proc.bean.implementation;

import luj.generate.annotation.process.SingleAnnoProc;

import java.io.IOException;

public interface DbBeanImplGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanImplGeneratorFactoryImpl();
    }

    DbBeanImplGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String implName) throws IOException;
}
