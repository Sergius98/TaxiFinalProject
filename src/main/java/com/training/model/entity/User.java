package com.training.model.entity;

public class User {
    private int id;
    private String nickname;
    private String password;
    private int role; // 0 - user, 1 - admin
    private long spendMoney;

    public User(){

    }
    public User(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
        this.role = 0;
        this.spendMoney = 0;
    }
    public User(int id, String nickname, String password, int role, int spendMoney) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.spendMoney = spendMoney;
    }

    public User getSafe(){
        this.password = "";
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(long spendMoney) {
        this.spendMoney = spendMoney;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
