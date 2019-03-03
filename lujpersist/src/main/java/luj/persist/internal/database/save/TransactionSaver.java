package luj.persist.internal.database.save;

import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionSaver {

  public interface Adapter {

    void save(DataSource source, Object... data);
  }

  public void save(TransSaveDatabase database, List<TransSavable> dataList) {
    List<TransSaveStatement> statementList = dataList.stream()
        .flatMap(TransSavable::toSqlStream)
        .collect(Collectors.toList());

    TransSaveTransaction transaction = database.startTransaction();
    try {
      for (TransSaveStatement statement : statementList) {
        executeUpdate(transaction, statement);
      }
      transaction.commit();

    } catch (Exception e) {
      transaction.rollback();
      throw wrap(e);

    } finally {
      transaction.close();
    }
  }

  private void executeUpdate(TransSaveTransaction transaction,
      TransSaveStatement statement) throws Exception {
    String sql = statement.getSql();
    LOG.info("{}", sql);

    try (TransSaveExecutable exec = transaction.startExecute(sql)) {
      statement.applyParam(exec.getParam());
      exec.execute();
    }
  }

  private RuntimeException wrap(Exception e) {
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    }
    return new UnsupportedOperationException(e);
  }

  private static final Logger LOG = LoggerFactory.getLogger(TransactionSaver.class);
}
