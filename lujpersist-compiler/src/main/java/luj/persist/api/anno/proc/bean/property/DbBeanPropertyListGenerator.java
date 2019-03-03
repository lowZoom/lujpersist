package luj.persist.api.anno.proc.bean.property;

import luj.generate.annotation.process.SingleAnnoProc;

import java.io.IOException;

public interface DbBeanPropertyListGenerator {

  interface Factory {

    static Factory get() {
      return new DbBeanPropertyListGeneratorFactoryImpl();
    }

    DbBeanPropertyListGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate(String listName) throws IOException;
}
