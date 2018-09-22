package luj.persist.anno.proc.bean.meta;

import java.io.IOException;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface DbMetaGenerator {

  interface Factory {

    static DbMetaGenerator create(SingleAnnoProc.Context ctx) {
      return new DbMetaGeneratorImpl(ctx);
    }
  }

  void generate(String metaName) throws IOException;
}
