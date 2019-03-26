package luj.persist.internal.session;

import luj.persist.api.PersistSession;
import luj.persist.internal.data.object.DataCreator;

final class PersistSessionImpl implements PersistSession {

  PersistSessionImpl(DataCreator dataCreator) {
    _dataCreator = dataCreator;
  }

  @Override
  public <T> T createData(Class<T> dataType) {
    return _dataCreator.create(dataType, null);
  }

  private final DataCreator _dataCreator;
}
