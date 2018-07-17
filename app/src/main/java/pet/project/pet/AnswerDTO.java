package pet.project.pet;

import java.io.Serializable;
import java.sql.Date;

public class AnswerDTO implements Serializable{
    String answerID;
    String userID;
    String questionID;
    String content;
    int totalVote;
    Date createdDate;
    Date updatedDate;
    boolean approvedByMentor;
    Date approvedDate;
    String approvedMentorID;
    boolean active;

    public AnswerDTO(String answerID, String userID, String questionID, String content, int totalVote, boolean active) {
        this.answerID = answerID;
        this.userID = userID;
        this.questionID = questionID;
        this.content = content;
        this.totalVote = totalVote;
        this.active = active;
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
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

    public String getApprovedMentorID() {
        return approvedMentorID;
    }

    public void setApprovedMentorID(String approvedMentorID) {
        this.approvedMentorID = approvedMentorID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
