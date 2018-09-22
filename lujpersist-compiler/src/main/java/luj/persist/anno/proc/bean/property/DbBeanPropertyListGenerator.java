package luj.persist.anno.proc.bean.property;

import java.io.IOException;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface DbBeanPropertyListGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanPropertyListGeneratorFactoryImpl();
    }

    DbBeanPropertyListGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String listName) throws IOException;
}
