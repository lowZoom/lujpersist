package luj.persist.anno.proc.bean.meta;

import luj.generate.annotation.process.SingleAnnoProc;

import java.io.IOException;

public interface DbMetaGenerator {

  interface Factory {

    static DbMetaGenerator create(SingleAnnoProc.Context ctx) {
      return new DbMetaGeneratorImpl(ctx);
    }
  }

  void generate(String metaName) throws IOException;
}
