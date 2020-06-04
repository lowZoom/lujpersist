package luj.persist.internal.queue.general.compact;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import luj.persist.internal.queue.general.QueueMergeHandler;
import luj.persist.internal.queue.general.compact.merge.QueueElementMerger;

public class QueueCompacter {

  public QueueCompacter(List<Object> elemList, List<QueueMergeHandler> mergerList) {
    _elemList = elemList;
    _mergerList = mergerList;
  }

  public List<Object> compact() {
    List<Object> oldList = _elemList;
    List<Object> newList = compactOneRound(oldList);

    while (newList.size() < oldList.size()) {
      oldList = newList;
      newList = compactOneRound(oldList);
    }

    return newList;
  }

  private List<Object> compactOneRound(List<Object> srcList) {
    for (int i = 0; i < srcList.size(); i++) {
      Object elem = srcList.get(i);
      QueueMergeHandler merger = getMerger(elem);
      if (merger == null) {
        continue;
      }

      List<Object> beforeList = srcList.subList(0, i + 1);
      new QueueElementMerger(elem, merger, beforeList).merge();

      i = beforeList.size() - 1;
    }

    return srcList.stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private QueueMergeHandler getMerger(Object elem) {
    return _mergerList.stream()
        .filter(m -> m.canHandle(elem))
        .findAny()
        .orElse(null);
  }

  private final List<Object> _elemList;

  private final List<QueueMergeHandler> _mergerList;
}
