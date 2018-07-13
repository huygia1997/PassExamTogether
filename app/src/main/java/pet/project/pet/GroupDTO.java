package pet.project.pet;

import java.sql.Date;

public class GroupDTO {
    private String groupName;
    private String groupOwner;
    private String timeCreated;
    private boolean haveMentor;
    private int numberQuestion;
    private String tag;


    public GroupDTO(String groupName, String groupOwner, String timeCreated, boolean haveMentor, int numberQuestion, String tag) {
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        this.timeCreated = timeCreated;
        this.haveMentor = haveMentor;
        this.numberQuestion = numberQuestion;
        this.tag = tag;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(String groupOwner) {
        this.groupOwner = groupOwner;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public boolean isHaveMentor() {
        return haveMentor;
    }

    public void setHaveMentor(boolean haveMentor) {
        this.haveMentor = haveMentor;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}