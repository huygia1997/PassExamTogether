package pet.project.pet;

import java.io.Serializable;
import java.sql.Date;

public class SubjectDTO implements Serializable {
    private String subjectID;
    private String subjectName;
    private String userID;
    private String createdDate;
    private Date updatedDate;
    private boolean active;
    private Date closedDate;

    public SubjectDTO(String subjectID, String subjectName, String userID, String createdDate, boolean active) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.userID = userID;
        this.createdDate = createdDate;
        this.active = active;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
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
