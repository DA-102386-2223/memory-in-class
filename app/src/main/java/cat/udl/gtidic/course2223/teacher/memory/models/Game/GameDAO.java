package cat.udl.gtidic.course2223.teacher.memory.models.Game;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDAO {
    @Insert
    void insertAll(Game... games);

    @Query("SELECT * FROM game")
    List<Game> getAll();

    @Delete
    void deleteOne(Game comic);

    @Query("DELETE FROM game")
    void deleteAll();

    // max Points
    // last game

}
