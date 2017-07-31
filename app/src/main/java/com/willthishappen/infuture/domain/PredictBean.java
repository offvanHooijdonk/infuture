package com.willthishappen.infuture.domain;

/**
 * Created by Yahor_Fralou on 7/31/2017 5:33 PM.
 */

public class PredictBean {
    private int id;
    private String text;
    private long date;

    public PredictBean() {
    }

    public PredictBean(int id, String text, long date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof PredictBean)) return false;

        PredictBean that = (PredictBean) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
