package luj.persist.internal.database.save.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import luj.data.type.impl.Data;
import luj.persist.internal.data.impl.DataImplGetter;
import luj.persist.internal.data.root.DataRoot;
import luj.persist.internal.database.save.TransSavable;
import luj.persist.internal.database.save.TransactionSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TransactionSaverAdapterImpl implements TransactionSaver.Adapter {

  @Override
  public void save(DataSource source, Object... data) {
    List<TransSavable> saveList = Arrays.stream(data)
        .map(d -> toSavable((Data) d))
        .collect(Collectors.toList());

    DatabaseImpl database = new DatabaseImpl(source);
    _transactionSaver.save(database, saveList);
  }

  private TransSavable toSavable(Data data) {
    DataRoot root = _dataImplGetter.get(data);
    return new SavableImpl(data, root);
  }

  @Autowired
  private TransactionSaver _transactionSaver;

  @Autowired
  private DataImplGetter _dataImplGetter;
}
