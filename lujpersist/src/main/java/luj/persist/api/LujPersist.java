package luj.persist.api;

import org.omg.CORBA.NO_IMPLEMENT;

public enum LujPersist {
  ;

  public static PersistSession start() {
    throw new NO_IMPLEMENT("start尚未实现");
  }
}
