package luj.persist.api.anno.proc.bean;

import luj.generate.annotation.process.SingleAnnoProc;

import java.io.IOException;

public interface DatabaseAllGenerator {

  interface Factory {

    static Factory get() {
      return new DatabaseAllGeneratorFactoryImpl();
    }

    DatabaseAllGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate() throws IOException;
}
