package org.example.models;

public class MultiplayerStats {

    private int id_multiplayerStats;
    private int id_user;
    private int wins;
    private int losses;

    public MultiplayerStats(int id_multiplayerStats, int id_user, int wins, int losses) {
        this.id_multiplayerStats = id_multiplayerStats;
        this.id_user = id_user;
        this.wins = wins;
        this.losses = losses;
    }

    public int getId_multiplayerStats() {
        return id_multiplayerStats;
    }

    public void setId_multiplayerStats(int id_multiplayerStats) {
        this.id_multiplayerStats = id_multiplayerStats;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Wins =" + wins + "\n" + "Losses =" + losses;

    }


}
