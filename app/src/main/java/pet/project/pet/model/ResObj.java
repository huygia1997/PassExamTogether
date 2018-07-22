package pet.project.pet.model;

import javax.xml.transform.Result;

public class ResObj {
    private boolean message;
    private int currentUserId;
    private int currentUserRoleId;
    private int voteStatus;

    public int getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(int voteStatus) {
        this.voteStatus = voteStatus;
    }

    public int getCurrentUserRoleId() {
        return currentUserRoleId;
    }

    public void setCurrentUserRoleId(int currentUserRoleId) {
        this.currentUserRoleId = currentUserRoleId;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
