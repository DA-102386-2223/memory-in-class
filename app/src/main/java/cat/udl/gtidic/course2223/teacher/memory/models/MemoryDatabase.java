package cat.udl.gtidic.course2223.teacher.memory.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.GameDAO;

@Database(entities = {Game.class}, version = 1)
public abstract class MemoryDatabase extends RoomDatabase {
    public abstract GameDAO gameDAO();
}
