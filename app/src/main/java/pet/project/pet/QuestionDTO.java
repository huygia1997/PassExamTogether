package pet.project.pet;

import java.sql.Date;

public class QuestionDTO {
    private String questionID;
    private String tag;
    private String title;
    private String groupId;
    private String timeCreated;
    private String userID;
    private int numberAnswer;
    private boolean active;
    private boolean approvalAnswer;

    public QuestionDTO(String questionID, String tag, String title, String groupId, String timeCreated, String userID, int numberAnswer, boolean active, boolean approvalAnswer) {
        this.questionID = questionID;
        this.tag = tag;
        this.title = title;
        this.groupId = groupId;
        this.timeCreated = timeCreated;
        this.userID = userID;
        this.numberAnswer = numberAnswer;
        this.active = active;
        this.approvalAnswer = approvalAnswer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getNumberAnswer() {
        return numberAnswer;
    }

    public void setNumberAnswer(int numberAnswer) {
        this.numberAnswer = numberAnswer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isApprovalAnswer() {
        return approvalAnswer;
    }

    public void setApprovalAnswer(boolean approvalAnswer) {
        this.approvalAnswer = approvalAnswer;
    }
}
