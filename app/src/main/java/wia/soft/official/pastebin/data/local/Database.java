package wia.soft.official.pastebin.data.local;

import android.arch.persistence.room.RoomDatabase;

import wia.soft.official.pastebin.data.models.PasteRoom;

@android.arch.persistence.room.Database(entities = PasteRoom.class, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract PasteDao pasteDao();
}