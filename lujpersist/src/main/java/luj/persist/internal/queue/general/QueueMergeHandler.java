package luj.persist.internal.queue.general;

import java.util.List;

public interface QueueMergeHandler {

  interface Context {

    <T> T getCurrentElement();

    Object getBeforeElement();

    Result result();
  }

  interface Result {

    Result skip();

    Result replaceBefore(List<Object> with);

    Result replaceCurrent(List<Object> with);
  }

  boolean canHandle(Object elem);

  Result onMerge(Context ctx);
}
