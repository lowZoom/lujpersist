package luj.persist.database.table.sql;

import java.sql.SQLException;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class SqlErrorMatcher {

  public boolean match(SQLException exception, String... desc) {
    String msg = exception.getMessage();
    return Arrays.stream(desc).allMatch(msg::contains);
  }
}
