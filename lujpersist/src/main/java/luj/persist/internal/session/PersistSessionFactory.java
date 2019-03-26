package luj.persist.internal.session;

import luj.persist.api.PersistSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface PersistSessionFactory {

  static PersistSessionFactory get(ApplicationContext appContext) {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(appContext);
      resultCtx.register(InjectConf.class);
      resultCtx.refresh();
      return resultCtx.getBean(PersistSessionFactory.class);
    }
  }

  PersistSession create();
}
