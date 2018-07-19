package pet.project.pet.model;

import java.io.Serializable;
import java.sql.Date;

public class Answer implements Serializable {
    private int ansId;
    private int userId;
    private int quesId;
    private String content;
    private int totalVote;
    private Date createdDate;
    private Date updatedDate;
    private boolean approvedByMentor;
    private Date approvedDate;
    private int approvedMentorId;
    private boolean active;

    public Answer() {
    }

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
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

    public boolean isApprovedByMentor() {
        return approvedByMentor;
    }

    public void setApprovedByMentor(boolean approvedByMentor) {
        this.approvedByMentor = approvedByMentor;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public int getApprovedMentorId() {
        return approvedMentorId;
    }

    public void setApprovedMentorId(int approvedMentorId) {
        this.approvedMentorId = approvedMentorId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Answer(int userId, int quesId, String content, int totalVote, Date createdDate, Date updatedDate, boolean approvedByMentor, Date approvedDate, int approvedMentorId, boolean active) {

        this.userId = userId;
        this.quesId = quesId;
        this.content = content;
        this.totalVote = totalVote;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.approvedByMentor = approvedByMentor;
        this.approvedDate = approvedDate;
        this.approvedMentorId = approvedMentorId;
        this.active = active;
    }

    public Answer(int ansId, int userId, int quesId, String content, int totalVote, Date createdDate, Date updatedDate, boolean approvedByMentor, Date approvedDate, int approvedMentorId, boolean active) {

        this.ansId = ansId;
        this.userId = userId;
        this.quesId = quesId;
        this.content = content;
        this.totalVote = totalVote;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.approvedByMentor = approvedByMentor;
        this.approvedDate = approvedDate;
        this.approvedMentorId = approvedMentorId;
        this.active = active;
    }
}
