package com.willthishappen.infuture.domain;

import java.util.Objects;

public class PredictBean {
    private String id;
    private String text;
    private long date;
    private long likeCount = 0;
    private boolean likedByCurrentUser = false;
    //private Map<String, String> likes;

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

    /**
     * NOTE: do not store likes in memory!
     */
    /*public void setLikes(Map<String, String> likes) {
        this.likes = likes;
    }*/

    /**
     * NOTE: method left for accessibility. Not supposed to return any value but <code>null</code>, because holding likes in memory can be highly costly.
     * @return <code>null</code> in most cases
     */
    /*public Map<String, String> getLikes() {
        return likes;
    }*/

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }

    /**
     * Updates fields: number of likes and if current user liked this; after that <code>likes</code> set to <code>null</code> to free memory
     * @param user current user, for who you check thi 'like' status
     */
    /*public void updateLikesInfo(@NonNull UserBean user) {
        if (likes != null) {
            likeCount = likes.size();
            likedByCurrentUser = likes.containsKey(user.getId());

            likes = null;
        }
    }*/

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
