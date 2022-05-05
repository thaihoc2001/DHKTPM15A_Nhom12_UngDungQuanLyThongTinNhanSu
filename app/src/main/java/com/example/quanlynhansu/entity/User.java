package com.example.quanlynhansu.entity;

public class User {
    public String userName;
    public String gmail;
    public String passWord;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public User(String userName, String gmail, String passWord) {
        this.userName = userName;
        this.gmail = gmail;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
