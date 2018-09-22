package luj.persist.anno.proc.bean.factory;

import java.io.IOException;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface DbBeanFactoryGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanFactoryGeneratorFactoryImpl();
    }

    DbBeanFactoryGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String factoryName, String implName) throws IOException;
}
