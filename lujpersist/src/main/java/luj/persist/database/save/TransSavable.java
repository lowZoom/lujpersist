package luj.persist.database.save;

import java.util.stream.Stream;

public interface TransSavable {

  Stream<TransSaveStatement> toSqlStream();
}
