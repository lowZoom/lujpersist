package luj.persist.internal.queue.general.compact.merge;

import luj.persist.internal.queue.general.QueueMergeHandler;

final class MergeContextImpl implements QueueMergeHandler.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getCurrentElement() {
    return (T) _currentElem;
  }

  @Override
  public Object getBeforeElement() {
    return _beforeElem;
  }

  @Override
  public QueueMergeHandler.Result result() {
    return _result;
  }

  Object _currentElem;
  Object _beforeElem;

  MergeResultImpl _result;
}
