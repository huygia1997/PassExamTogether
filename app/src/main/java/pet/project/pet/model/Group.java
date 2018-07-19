package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Group implements Serializable {
    private int groupId;
    private String groupName;
    private int subId;
    private String password;
    private int userId;
    private int totalQuestions;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    private Date closedDate;

    public Group() {
    }

    public Group(String groupName, int subId, String password, int userId, int totalQuestions, Date createdDate, Date updatedDate, boolean active, Date closedDate) {
        this.groupName = groupName;
        this.subId = subId;
        this.password = password;
        this.userId = userId;
        this.totalQuestions = totalQuestions;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.closedDate = closedDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
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
}
