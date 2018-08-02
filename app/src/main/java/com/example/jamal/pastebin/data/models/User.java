package com.example.jamal.pastebin.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "user")
public class User {

    @Element(name = "user_name")
    private String name;
    @Element(name = "user_format_short")
    private String formatShort;
    @Element(name = "user_expiration")
    private String expiration;
    @Element(name = "user_avatar_url")
    private String avatarUrl;
    @Element(name = "user_private")
    private int userPrivate;
    @Element(name = "user_website")
    private String website;
    @Element(name = "user_email")
    private String email;
    @Element(name = "user_location")
    private String location;
    @Element(name = "user_account_type")
    private String accountType;

    public String getName() {
        return name;
    }

    public String getFormatShort() {
        return formatShort;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getUserPrivate() {
        return userPrivate;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}