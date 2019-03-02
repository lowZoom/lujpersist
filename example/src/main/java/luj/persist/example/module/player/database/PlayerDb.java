package luj.persist.example.module.player.database;

import luj.data.type.JStr;
import luj.persist.anno.Database;

@Database(needSet = true)
public interface PlayerDb {

  JStr account();
}
