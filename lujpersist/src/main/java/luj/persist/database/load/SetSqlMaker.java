package luj.persist.database.load;

import luj.persist.data.meta.DbMeta;
import org.springframework.stereotype.Service;

@Service
public class SetSqlMaker {

  public String make(DbMeta meta, String key) {
    return String.format("SELECT val FROM `%s` WHERE key='%s'", meta.getSetTableName(), key);
  }
}
