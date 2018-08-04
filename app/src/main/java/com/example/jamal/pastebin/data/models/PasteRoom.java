package com.example.jamal.pastebin.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PasteRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String key;
    private int date;
    private String title;
    private int size;
    private int expireDate;
    private int pastePrivate;
    private String formatLong;
    private String formatShort;
    private String url;
    private int hits;

    public PasteRoom(/*int id,*/ String key, int date, String title, int size, int expireDate, int pastePrivate, String formatLong, String formatShort, String url, int hits) {
//        this.id = id;
        this.key = key;
        this.date = date;
        this.title = title;
        this.size = size;
        this.expireDate = expireDate;
        this.pastePrivate = pastePrivate;
        this.formatLong = formatLong;
        this.formatShort = formatShort;
        this.url = url;
        this.hits = hits;
    }
//
    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public int getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public int getPastePrivate() {
        return pastePrivate;
    }

    public String getFormatLong() {
        return formatLong;
    }

    public String getFormatShort() {
        return formatShort;
    }

    public String getUrl() {
        return url;
    }

    public int getHits() {
        return hits;
    }

    public void setId(int id) {
        this.id = id;
    }
}