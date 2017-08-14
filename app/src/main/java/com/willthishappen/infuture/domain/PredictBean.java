package com.willthishappen.infuture.domain;

import java.util.Objects;

public class PredictBean {
    private String id;
    private String text;
    private long date;
    private long likeNumber = 0;
    private boolean likedByCurrentUser = false;

    public PredictBean() {
    }

    public PredictBean(String id, String text, long date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(long likeNumber) {
        this.likeNumber = likeNumber;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof PredictBean)) return false;

        PredictBean that = (PredictBean) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
