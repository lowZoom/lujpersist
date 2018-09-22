package luj.persist.database.save;

import java.sql.SQLException;

//FIXME: ExecutableStatement
public interface TransSaveExecutable extends AutoCloseable {

  TransSaveStatParam getParam();

  void execute() throws SQLException;
}
