package luj.persist.internal.data.set;

import java.util.Set;
import luj.data.type.JSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetAdder {

  public void add(JSet set, Long newId) {
    DbSet impl = _dbSetImplGetter.get(set);
    Set<Long> addHistory = _historyGetOrCreator.getOrCreate(impl::getAdd, impl::setAdd);
    addHistory.add(newId);
  }

  @Autowired
  private DbSetGetter _dbSetImplGetter;

  @Autowired
  private SetHistoryGetOrCreator _historyGetOrCreator;
}
