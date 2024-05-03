package org.example.models;

public class Architect extends User{

    private int level;

    public Architect(int id_user, String username, String password, int level) {
        super(id_user,username, password);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Architect{" +
                "level=" + level +
                ", id_user=" + id_user +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }




}
