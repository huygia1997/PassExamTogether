package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class History implements Serializable {
    private int histId;
    private int userId;
    private int postId;
    private Date createdDate;
    private int postType;

    public History(int userId, int postId, int postType) {
        this.userId = userId;
        this.postId = postId;
        this.postType = postType;
    }

    public int getHistId() {
        return histId;
    }

    public void setHistId(int histId) {
        this.histId = histId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public History() {

    }
}
