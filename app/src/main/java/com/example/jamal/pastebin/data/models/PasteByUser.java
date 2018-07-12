package com.example.jamal.pastebin.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "paste")
public class PasteByUser {

    @Element(name = "paste_key")
    private String key;
    @Element(name = "paste_date")
    private int date;
    @Element(name = "paste_title")
    private String title;
    @Element(name = "paste_size")
    private int size;
    @Element(name = "paste_expire_date")
    private int expireDate;
    @Element(name = "paste_private")
    private int pastePrivate;
    @Element(name = "paste_format_long")
    private String formatLong;
    @Element(name = "paste_format_short")
    private String formatShort;
    @Element(name = "paste_url")
    private String url;
    @Element(name = "paste_hits")
    private int hits;

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
}