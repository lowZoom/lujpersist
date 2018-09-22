package luj.persist.data.root.statement.object;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import luj.persist.database.save.TransSaveStatement;
import org.springframework.stereotype.Service;

@Service
public class ObjectStatementMaker {

  public List<TransSaveStatement> make(OStatMakeObject obj) {
    List<TransSaveStatement> result = new ArrayList<>(2);

    String tableName = obj.getTableName();
    String idCol = obj.getIdColumn();
    String valCol = obj.getValColumn();

    Long dataId = obj.getDataId();
    if (!obj.isInDatabase()) {
      result.add(makeInsert(tableName, idCol, valCol, dataId));
    }

    String jsonStr = obj.toJson();
    result.add(makeUpdate(tableName, valCol, jsonStr, idCol, dataId));

    return result;
  }

  private StatementInsert makeInsert(String tableName, String idCol, String valCol, Long id) {
    String sql = "INSERT INTO " + tableName + " (" + idCol + ',' + valCol + ") VALUES (?,'{}')";
    return new StatementInsert(sql, id);
  }

  private StatementUpdate makeUpdate(String tableName,
      String valCol, String valJson, String idCol, Long id) {
    String sql = "UPDATE " + tableName + " SET " + valCol + "=? WHERE " + idCol + "=?";
    checkNotNull(valJson, sql);

    return new StatementUpdate(sql, valJson, id);
  }
}
