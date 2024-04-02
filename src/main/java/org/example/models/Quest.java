package org.example.models;

public class Quest {

    private String name;
    private String description;
    private int reward;
    private boolean completed;

    public Quest(String name, String description, int reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.completed = false;
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

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
