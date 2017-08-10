package com.willthishappen.infuture.domain;

import java.util.Objects;

public class UserBean {
    private String id;
    private String name;
    private String photoUrl;
    private String email;

    public UserBean(String id, String name, String photoUrl, String email) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.email = email;
    }

    public UserBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserBean)) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(id, userBean.id);
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
