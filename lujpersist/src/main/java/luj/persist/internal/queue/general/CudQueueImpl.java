package luj.persist.internal.queue.general;

import java.util.List;
import java.util.function.Consumer;
import luj.persist.internal.queue.general.compact.QueueCompacter;

final class CudQueueImpl implements CudQueue {

  CudQueueImpl(List<Object> elemList, List<QueueMergeHandler> mergerList) {
    _elemList = elemList;
    _mergerList = mergerList;
  }

  @Override
  public void add(Object elem) {
    _elemList.add(elem);
  }

  @Override
  public void compact() {
    _elemList = new QueueCompacter(_elemList, _mergerList).compact();
  }

  @Override
  public void clear() {
    _elemList.clear();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> void forEach(Consumer<T> iter) {
    for (Object elem : _elemList) {
      iter.accept((T) elem);
    }
  }

  @Override
  public int size() {
    return _elemList.size();
  }

  private List<Object> _elemList;

  private final List<QueueMergeHandler> _mergerList;
}
