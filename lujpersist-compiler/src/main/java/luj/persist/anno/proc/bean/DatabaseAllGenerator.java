package luj.persist.anno.proc.bean;

import java.io.IOException;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface DatabaseAllGenerator {

  interface Factory {

    static Factory get() {
      return new DatabaseAllGeneratorFactoryImpl();
    }

    DatabaseAllGenerator instance(SingleAnnoProc.Context ctx);
  }

  void generate() throws IOException;
}
