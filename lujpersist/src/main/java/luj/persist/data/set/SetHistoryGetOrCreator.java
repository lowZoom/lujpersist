package luj.persist.data.set;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

@Service
public class SetHistoryGetOrCreator {

  public Set<Long> getOrCreate(Supplier<Set<Long>> historyGetter,
      Consumer<Set<Long>> historySetter) {
    Set<Long> history = historyGetter.get();
    if (history != null) {
      return history;
    }

    Set<Long> newHistory = new HashSet<>();
    historySetter.accept(newHistory);
    return newHistory;
  }
}
