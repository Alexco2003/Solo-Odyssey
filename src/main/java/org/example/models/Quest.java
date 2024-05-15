package org.example.models;

public class Quest {
    private int id_quest;
    private int id_player;
    private String name;
    private String description;
    private int rewardLevel;
    private double rewardMoney;
    private boolean completed = false;

    public Quest(int id_quest, int id_player, String name, String description, int rewardLevel, boolean completed, double rewardMoney) {
        this.id_quest = id_quest;
        this.id_player = id_player;
        this.name = name;
        this.description = description;
        this.rewardLevel = rewardLevel;
        this.completed = completed;
        this.rewardMoney = rewardMoney;
    }

    public double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public int getId_quest() {
        return id_quest;
    }

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(int rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        sb.append("Description: " + description + "\n");
        sb.append("Reward: " + rewardLevel + " level, " + rewardMoney + " gold\n");
        return sb.toString();
    }




}
