package luj.persist.api.anno.proc.bean.meta;

import java.io.IOException;
import luj.generate.annotation.process.SingleAnnoProc;

public interface DbMetaGenerator {

  interface Factory {

    static DbMetaGenerator create(SingleAnnoProc.Context ctx) {
      return new DbMetaGeneratorImpl(ctx.getProcessingType());
    }
  }

  void generate(String metaName) throws IOException;
}
