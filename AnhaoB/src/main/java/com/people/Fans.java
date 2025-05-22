package com.people;

public class Fans {
    private int id;
    private String username;
    private String avatar;
    private String phone;
    private String groupName;

    @Override
    public String toString() {
        return "Fans{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Fans(int id, String username, String avatar, String phone, String groupName) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.phone = phone;
        this.groupName = groupName;
    }

    public Fans() {
    }
}
