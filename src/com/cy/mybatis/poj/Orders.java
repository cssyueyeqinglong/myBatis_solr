package com.cy.mybatis.poj;

public class Orders {
    private int id;
    private String userId;
    private String number;
    private String createtime;
    private String note;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "userId='" + userId + '\'' +
                ", number='" + number + '\'' +
                ", createtime='" + createtime + '\'' +
                ", note='" + note + '\'' +
                ", user=" + user +
                '}';
    }
}
