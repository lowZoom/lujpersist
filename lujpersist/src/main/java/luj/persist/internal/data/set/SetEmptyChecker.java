package luj.persist.internal.data.set;

import luj.data.type.JSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetEmptyChecker {

  public boolean check(JSet set) {
    DbSet impl = _dbSetImplGetter.get(set);
    return impl.getValue().isEmpty();
  }

  @Autowired
  private DbSetGetter _dbSetImplGetter;
}
