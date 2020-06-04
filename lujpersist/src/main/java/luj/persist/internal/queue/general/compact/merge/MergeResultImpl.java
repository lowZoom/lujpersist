package luj.persist.internal.queue.general.compact.merge;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.persist.internal.queue.general.QueueMergeHandler;

final class MergeResultImpl implements QueueMergeHandler.Result {

  @Override
  public QueueMergeHandler.Result skip() {
    _skip = true;
    return this;
  }

  @Override
  public QueueMergeHandler.Result replaceBefore(List<Object> with) {
    _replaceBefore = with;
    return this;
  }

  @Override
  public QueueMergeHandler.Result replaceCurrent(List<Object> with) {
    _replaceCurrent = with;
    return this;
  }

  boolean _skip;

  List<Object> _replaceBefore = ImmutableList.of();
  List<Object> _replaceCurrent = ImmutableList.of();
}
