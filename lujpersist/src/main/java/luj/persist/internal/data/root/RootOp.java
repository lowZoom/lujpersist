package luj.persist.internal.data.root;

import java.util.List;
import luj.persist.internal.database.save.TransSaveStatement;

public interface RootOp<R extends DataRoot> {

  void applyModification(R rootImpl, Object dataObj);

  List<TransSaveStatement> toSql(R rootImpl, Object dataObj);
}
