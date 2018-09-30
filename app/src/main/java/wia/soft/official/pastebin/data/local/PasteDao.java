package wia.soft.official.pastebin.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import wia.soft.official.pastebin.data.models.PasteRoom;

@Dao
public interface PasteDao {

    @Query("SELECT * FROM PasteRoom")
    List<PasteRoom> getAll();

    @Query("SELECT * FROM  PasteRoom WHERE  id = :id")
    PasteRoom getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (PasteRoom paste);

    @Update()
    void update (PasteRoom favoritesUser);

    @Delete()
    void delete (PasteRoom favoritesUser);
}