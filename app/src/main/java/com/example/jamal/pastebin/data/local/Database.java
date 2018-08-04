package com.example.jamal.pastebin.data.local;

import android.arch.persistence.room.RoomDatabase;

import com.example.jamal.pastebin.data.models.PasteRoom;

@android.arch.persistence.room.Database(entities = PasteRoom.class, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract PasteDao pasteDao();
}