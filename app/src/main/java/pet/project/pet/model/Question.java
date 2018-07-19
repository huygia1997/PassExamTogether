package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Question implements Serializable{
    private int quesId;
    private int userId;
    private int groupId;
    private int subId;
    private String title;
    private String content;
    private int totalAnswers;
    private Date createdDate;
    private Date updatedDate;
    private boolean approvedAnswer;
    private boolean active;
    private Date closedDate;


    public Question(int userId, int groupId, int subId, String title, String content, int totalAnswers, Date createdDate, Date updatedDate, boolean approvedAnswer, boolean active, Date closedDate) {
        this.userId = userId;
        this.groupId = groupId;
        this.subId = subId;
        this.title = title;
        this.content = content;
        this.totalAnswers = totalAnswers;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.approvedAnswer = approvedAnswer;
        this.active = active;
        this.closedDate = closedDate;
    }

    public Question() {
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
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

    public boolean isApprovedAnswer() {
        return approvedAnswer;
    }

    public void setApprovedAnswer(boolean approvedAnswer) {
        this.approvedAnswer = approvedAnswer;
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

    public Question(int quesId, int userId, int groupId, int subId, String title, String content, int totalAnswers, Date createdDate, Date updatedDate, boolean approvedAnswer, boolean active, Date closedDate) {

        this.quesId = quesId;
        this.userId = userId;
        this.groupId = groupId;
        this.subId = subId;
        this.title = title;
        this.content = content;
        this.totalAnswers = totalAnswers;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.approvedAnswer = approvedAnswer;
        this.active = active;
        this.closedDate = closedDate;
    }
}
