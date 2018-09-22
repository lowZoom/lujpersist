package luj.persist.database.load;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.sql.DataSource;
import luj.data.type.JSet;
import luj.persist.data.meta.DbMeta;
import luj.persist.data.meta.DbMetaMap;
import luj.persist.data.set.impl.SetCreator;
import luj.persist.database.jdbc.JdbcQueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbSetLoader {

  public JSet load(DataSource source, Class<?> dataType, String key) {
    DbMeta dbMeta = _dbMetaMap.get(dataType);
    checkNotNull(dbMeta, dataType.getName());

    String sqlStr = _setSqlMaker.make(dbMeta, key);

    //TODO: 执行SQL语句到数据库，获得查询结果
    ImmutableSet.Builder<Long> builder = ImmutableSet.builder();
    _jdbcSelectRunner.run(source, sqlStr, this::onSqlResult, builder);

    return createSet(key, builder, dbMeta);
  }

  private void onSqlResult(ResultSet result, ImmutableSet.Builder<Long> builder)
      throws SQLException {
    Long objId = result.getLong(1);
    builder.add(objId);
  }

  private JSet createSet(String key, ImmutableSet.Builder<Long> builder, DbMeta dbMeta) {
    return _setCreator.create(key, new HashSet<>(builder.build()), dbMeta);
  }

  @Autowired
  private DbMetaMap _dbMetaMap;

  @Autowired
  private SetSqlMaker _setSqlMaker;

  @Autowired
  private JdbcQueryRunner _jdbcSelectRunner;

  @Autowired
  private SetCreator _setCreator;
}
