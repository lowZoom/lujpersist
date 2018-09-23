package luj.persist.anno.proc.bean.factory;

import luj.generate.annotation.process.SingleAnnoProc;

import java.io.IOException;

public interface DbBeanFactoryGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanFactoryGeneratorFactoryImpl();
    }

    DbBeanFactoryGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String factoryName, String implName) throws IOException;
}
