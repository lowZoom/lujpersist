package luj.persist.anno.proc;

import com.google.auto.service.AutoService;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.persist.anno.Database;
import luj.persist.anno.proc.bean.DatabaseAllGenerator;

import javax.annotation.processing.Processor;
import java.io.IOException;
import java.lang.annotation.Annotation;

@AutoService(Processor.class)
public class DatabaseProc extends SingleAnnoProc {

  public DatabaseProc() {
    _generatorFactory = DatabaseAllGenerator.Factory.get();
  }

  @Override
  public Class<? extends Annotation> supportedAnnotationType() {
    return Database.class;
  }

  @Override
  public void processElement(Context ctx) throws IOException {
    DatabaseAllGenerator generator = _generatorFactory.instance(ctx);
    generator.generate();
  }

  private final DatabaseAllGenerator.Factory _generatorFactory;
}
