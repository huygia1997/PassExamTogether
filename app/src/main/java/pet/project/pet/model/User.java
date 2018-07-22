package pet.project.pet.model;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
    private String displayName;
    private String email;
    private String profileUserUrl;
    private int roleId;
    private float totalPoints;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(String username, String password, String displayName, int roleId) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.roleId = roleId;
    }

    public User(int userId, String username, String password, String displayName, String email, String profileImgUrl, int roleId, float totalPoints) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.profileUserUrl = profileImgUrl;
        this.roleId = roleId;
        this.totalPoints = totalPoints;
    }

    public User(String username, String password, String displayName, String email, String profileImgUrl, int roleId, float totalPoints) {

        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.profileUserUrl = profileImgUrl;
        this.roleId = roleId;
        this.totalPoints = totalPoints;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImgUrl() {
        return profileUserUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileUserUrl = profileImgUrl;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public float getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(float totalPoints) {
        this.totalPoints = totalPoints;
    }
}
