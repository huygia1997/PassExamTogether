package pet.project.pet.model;

import java.io.Serializable;
import java.util.Date;

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
    private String username;
    private String displayName;

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Group(String groupName, int subId, String password, int userId, int totalQuestions, boolean active) {
        this.groupName = groupName;
        this.subId = subId;
        this.password = password;
        this.userId = userId;
        this.totalQuestions = totalQuestions;
        this.active = active;
    }

    public Group(String groupName, int subId, String password, int userId, int totalQuestions, Date createdDate, boolean active) {
        this.groupName = groupName;
        this.subId = subId;
        this.password = password;
        this.userId = userId;
        this.totalQuestions = totalQuestions;
        this.createdDate = createdDate;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Group() {
    }

    public Group(int groupId, String password, int userId, int totalQuestions, Date createdDate, Date updatedDate, boolean active) {
        this.groupId = groupId;
        this.password = password;
        this.userId = userId;
        this.totalQuestions = totalQuestions;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
    }

    public Group(String groupName, String password, int userId, int totalQuestions, Date createdDate, Date updatedDate, boolean active, Date closedDate) {
        this.groupName = groupName;
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
