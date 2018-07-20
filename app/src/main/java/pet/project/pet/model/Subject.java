package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Subject implements Serializable {
    private int subjectID;
    private String subjectName;
    private int userID;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    private Date closedDate;
    private String subCode;

    public Subject(String subjectName, int userID, Date createdDate, Date updatedDate, boolean active, Date closedDate, String subCode) {
        this.subjectName = subjectName;
        this.userID = userID;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.closedDate = closedDate;
        this.subCode = subCode;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
}
