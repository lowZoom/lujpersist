package luj.persist.internal.queue.general;

import java.util.LinkedList;
import java.util.List;

public class CudQueueFactory {

  public CudQueueFactory(List<QueueMergeHandler> mergerList) {
    _mergerList = mergerList;
  }

  public CudQueue create() {
    return new CudQueueImpl(new LinkedList<>(), _mergerList);
  }

  private final List<QueueMergeHandler> _mergerList;
}
