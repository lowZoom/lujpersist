package luj.persist.data.root.statement.set;

import java.util.Set;

public interface SStatMakeSet {

  String getTableName();

  String getKeyColumn();

  String getValColumn();

  String getKey();

  Set<Long> getAddHistory();

  Set<Long> getRemoveHistory();
}
