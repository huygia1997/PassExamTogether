package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Vote implements Serializable {
    private int ansId;
    private int votedUserId;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    private int status;

    public Vote(int ansId, int votedUserId, boolean active, int status) {
        this.ansId = ansId;
        this.votedUserId = votedUserId;
        this.active = active;
        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public int getVotedUserId() {
        return votedUserId;
    }

    public void setVotedUserId(int votedUserId) {
        this.votedUserId = votedUserId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vote(int ansId, int votedUserId, Date createdDate, Date updatedDate, boolean active) {
        this.ansId = ansId;
        this.votedUserId = votedUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
    }

    public Vote(int ansId, int votedUserId, boolean active) {

        this.ansId = ansId;
        this.votedUserId = votedUserId;
        this.active = active;
    }



}
