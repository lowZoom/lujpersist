package luj.persist.internal.data.root.statement.set;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import luj.persist.internal.data.set.DbSet;
import luj.persist.internal.database.save.TransSaveStatement;
import org.springframework.stereotype.Service;

@Service
public class SetStatementMaker {

  public interface Adapter {

    List<TransSaveStatement> make(DbSet dbSet);
  }

  public List<TransSaveStatement> make(SStatMakeSet set) {
    String tableName = set.getTableName();
    String key = set.getKey();
    checkNotNull(key, tableName);

    String keyCol = set.getKeyColumn();
    String valCol = set.getValColumn();

    return ImmutableList.<TransSaveStatement>builder()
        .addAll(makeInsertList(tableName, keyCol, valCol, key, set.getAddHistory()))
        .addAll(makeDeleteList(tableName, keyCol, valCol, key, set.getRemoveHistory()))
        .build();
  }

  private List<TransSaveStatement> makeInsertList(String tableName,
      String keyCol, String valCol, String key, Set<Long> addHistory) {
    return addHistory.stream()
        .map(id -> makeInsert(tableName, keyCol, valCol, key, id))
        .collect(Collectors.toList());
  }

  private TransSaveStatement makeInsert(String tableName,
      String keyCol, String valCol, String key, Long dataId) {
    String sql = "INSERT INTO " + tableName
        + " (" + keyCol + ',' + valCol + ") VALUES (?,?)";

    return new StatementImpl(sql, key, dataId);
  }

  private List<TransSaveStatement> makeDeleteList(String tableName,
      String keyCol, String valCol, String key, Set<Long> removeHistory) {
    return removeHistory.stream()
        .map(id -> makeDelete(tableName, keyCol, valCol, key, id))
        .collect(Collectors.toList());
  }

  private TransSaveStatement makeDelete(String tableName, String keyCol,
      String valCol, String key, Long dataId) {
    String sql = "DELETE FROM " + tableName
        + " WHERE " + keyCol + "=? AND " + valCol + "=?";

    return new StatementImpl(sql, key, dataId);
  }
}
