package luj.persist.internal.database.load;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import luj.data.type.JSet;
import org.springframework.stereotype.Service;

@Service
public class DbDataLoader {

  public interface Adapter {

    <T> Collection<T> load(DataSource source, JSet set);
  }

  public <T> Collection<T> load(DDatLoadDatabase database, DDatLoadSet set) {
    String sql = makeSql(set);

    List<DDatLoadSet.DecodeResult> resultList = database.runQuery(sql).stream()
        .map(r -> set.decodeData(r.getDataId(), r.getJsonStr()))
        .collect(Collectors.toList());

    for (DDatLoadSet.DecodeResult result : resultList) {
      result.markInDatabase();
    }

    return resultList.stream()
        .map(DDatLoadSet.DecodeResult::<T>getDataObject)
        .collect(Collectors.toList());
  }

  private String makeSql(DDatLoadSet set) {
    StringBuilder sql = new StringBuilder("SELECT ")
        .append(set.getDataIdColumn())
        .append(',')
        .append(set.getDataValueColumn())
        .append(" FROM ")
        .append(set.getDataTableName())
        .append(" WHERE ")
        .append(set.getDataIdColumn())
        .append(" IN (");

    for (Long dataId : set.iter()) {
      sql.append(dataId);
      sql.append(',');
    }

    int last = sql.length() - 1;
    if (sql.charAt(last) == ',') {
      sql.deleteCharAt(last);
    }

    sql.append(')');
    return sql.toString();
  }
}
