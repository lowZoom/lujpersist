package luj.persist.database.save.impl;

import java.util.stream.Stream;
import luj.data.type.impl.Data;
import luj.persist.data.root.DataRoot;
import luj.persist.data.root.RootOp;
import luj.persist.database.save.TransSavable;
import luj.persist.database.save.TransSaveStatement;

class SavableImpl implements TransSavable {

  public SavableImpl(Data data, DataRoot dataRoot) {
    _data = data;

    _dataRoot = dataRoot;
    _rootOp = dataRoot.getRootOp();
  }

  @Override
  public Stream<TransSaveStatement> toSqlStream() {
    return _rootOp.toSql(_dataRoot, _data).stream();
  }

  private final Data _data;

  private final DataRoot _dataRoot;
  private final RootOp<DataRoot> _rootOp;
}
