package luj.persist.internal.queue.general.compact.merge;

import java.util.List;
import luj.persist.internal.queue.general.QueueMergeHandler;

public class QueueElementMerger {

  public QueueElementMerger(Object curElem, QueueMergeHandler merger, List<Object> beforeList) {
    _curElem = curElem;
    _merger = merger;
    _beforeList = beforeList;
  }

  public void merge() {
    for (int i = _beforeList.size() - 2; i >= 0; i--) {
      Object elem = _beforeList.get(i);
      MergeContextImpl ctx = createContext(elem);

      MergeResultImpl result = (MergeResultImpl) _merger.onMerge(ctx);
      if (result._skip) {
        continue;
      }

      _beforeList.set(i, null);
      _beforeList.addAll(i, result._replaceBefore);

      _beforeList.set(_beforeList.size() - 1, null);
      _beforeList.addAll(result._replaceCurrent);

      return;
    }
  }

  private MergeContextImpl createContext(Object beforeElem) {
    MergeContextImpl ctx = new MergeContextImpl();
    ctx._currentElem = _curElem;
    ctx._beforeElem = beforeElem;

    ctx._result = new MergeResultImpl();
    return ctx;
  }

  private final Object _curElem;
  private final QueueMergeHandler _merger;

  private final List<Object> _beforeList;
}
