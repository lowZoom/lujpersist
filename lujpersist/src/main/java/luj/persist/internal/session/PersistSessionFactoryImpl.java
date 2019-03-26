package luj.persist.internal.session;

import luj.ava.spring.Internal;
import luj.persist.api.PersistSession;
import luj.persist.internal.data.object.DataCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class PersistSessionFactoryImpl implements PersistSessionFactory {

  @Override
  public PersistSession create() {
    return new PersistSessionImpl(_dataCreator);
  }

  @Autowired
  private DataCreator _dataCreator;
}
