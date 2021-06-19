package com.qht.entity;


import sun.plugin.util.UserProfile;

public class UserAdmin {
    private int userId;
    private String userAccount;
    private String userNickName;
    private String userPwd;
    private String userProfile;
    private int locked;

    public UserAdmin() {
    }

    public UserAdmin( String userAccount, String userNickName, String userPwd, String userProfile, int locked) {
        this.userAccount = userAccount;
        this.userNickName = userNickName;
        this.userPwd = userPwd;
        this.userProfile = userProfile;
        this.locked = locked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "UserAdmin{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", locked=" + locked +
                '}';
    }
}
