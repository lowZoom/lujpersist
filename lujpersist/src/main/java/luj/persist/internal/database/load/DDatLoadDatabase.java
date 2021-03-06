package luj.persist.internal.database.load;

import java.util.List;

public interface DDatLoadDatabase {

  interface QueryResult {

    Long getDataId();

    String getJsonStr();
  }

  List<QueryResult> runQuery(String sql);
}
