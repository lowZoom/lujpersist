package luj.persist.api;

import luj.persist.internal.session.PersistSessionFactory;
import org.springframework.context.ApplicationContext;

public enum LujPersist {
  ;

  public static PersistSession start(ApplicationContext appContext) {
    return PersistSessionFactory.get(appContext).create();
  }
}
