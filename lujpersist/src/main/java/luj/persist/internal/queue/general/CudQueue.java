package luj.persist.internal.queue.general;

import java.util.function.Consumer;

public interface CudQueue {

  void add(Object elem);

  void compact();

  void clear();

  <T> void forEach(Consumer<T> iter);

  int size();
}
