package luj.persist.data.root;

import java.util.List;
import luj.persist.database.save.TransSaveStatement;

public interface RootOp<R extends DataRoot> {

  void applyModification(R rootImpl, Object dataObj);

  List<TransSaveStatement> toSql(R rootImpl, Object dataObj);
}
