package luj.persist.api;

public interface PersistSession {

  <T> T createData(Class<T> dataType);
}
