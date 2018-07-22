package pet.project.pet.model;

import java.io.Serializable;

public class GroupParticipant implements Serializable {
    private int groupId;
    private int groupParticipantId;
    private String detailOfGroup;

    public GroupParticipant(int groupId, int groupParticipantId) {
        this.groupId = groupId;
        this.groupParticipantId = groupParticipantId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupParticipantId() {
        return groupParticipantId;
    }

    public void setGroupParticipantId(int groupParticipantId) {
        this.groupParticipantId = groupParticipantId;
    }

    public String getDetailOfGroup() {
        return detailOfGroup;
    }

    public void setDetailOfGroup(String detailOfGroup) {
        this.detailOfGroup = detailOfGroup;
    }
}
