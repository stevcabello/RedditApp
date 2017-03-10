package com.rappi.test.redditapp.models;

/**
 * Models the post's feed
 */

public class Feed {
    private String title;
    private String user;
    private int subscribers;
    private int comments;
    private String detail;

    public Feed(String title, String user, int subscribers, int comments, String detail) {
        this.title = title;
        this.user = user;
        this.subscribers = subscribers;
        this.comments = comments;
        this.detail = detail;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }




}
