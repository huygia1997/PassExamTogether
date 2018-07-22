package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Subject implements Serializable {
    private int subId;
    private String subName;
    private int userId;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    private Date closedDate;
    private String subCode;

    public Subject(int subId, String subName, int userId, Date createdDate, Date updatedDate, boolean active, Date closedDate, String subCode) {
        this.subId = subId;
        this.subName = subName;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.closedDate = closedDate;
        this.subCode = subCode;
    }

    public Subject(String subName, int userId, Date createdDate, Date updatedDate, boolean active, Date closedDate, String subCode) {
        this.subName = subName;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.closedDate = closedDate;
        this.subCode = subCode;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    @Override
    public String toString() {
        return subCode;
    }
}
