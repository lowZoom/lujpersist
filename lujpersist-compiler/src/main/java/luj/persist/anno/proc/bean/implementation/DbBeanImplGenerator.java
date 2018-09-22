package luj.persist.anno.proc.bean.implementation;

import java.io.IOException;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface DbBeanImplGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanImplGeneratorFactoryImpl();
    }

    DbBeanImplGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String implName) throws IOException;
}
