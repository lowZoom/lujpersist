package luj.persist.example.module.player.database;

import luj.data.type.JStr;
import luj.persist.api.anno.Database;

@Database(needSet = true)
public interface PlayerDb {

  JStr account();
}
