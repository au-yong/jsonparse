package com.example.user.jsonparse;

/**
 * Created by User on 27/11/2016.
 */

public class TakeData {
    private String section;
    private String date;
    private String webUrl;
    private String title;

    public TakeData(String section, String title, String webUrl, String date) {
        this.section = section;
        this.title = title;
        this.webUrl = webUrl;
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
