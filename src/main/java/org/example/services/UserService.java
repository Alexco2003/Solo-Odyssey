package org.example.services;

import org.example.models.Architect;
import org.example.models.Item;
import org.example.models.Player;
import org.example.repositories.ArchitectRepository;
import org.example.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class UserService {

    private PlayerRepository playerRepository;
    private ArchitectRepository architectRepository;


    public UserService() {
        this.playerRepository = new PlayerRepository();
        this.architectRepository = new ArchitectRepository();
    }

    // Login and Register
    public int checkLogin(String username, String password) {
        return playerRepository.checkLogin(username, password);
    }

    public boolean checkPlayerExists(int id) {
        return playerRepository.checkPlayerExists(id);
    }

    public boolean checkArchitectExists(int id) {
        return architectRepository.checkArchitectExists(id);
    }

    public void registerPlayer(String username, String password) {
        playerRepository.add(new Player(0, username, password, 1, "Player", 10, 100, 0, new HashMap<Item, Integer>()));
    }

    public boolean checkUsernameExists(String username) {
        return playerRepository.checkUsernameExists(username);
    }


    //CRUD Player
    public void addPlayer(String username, String password, int level, String title, int damage, int health, double money) {
        Player player = new Player(0, username, password, level, title, damage, health, money, new HashMap<Item, Integer>());
        playerRepository.add(player);
    }

    public Player getPlayer(int id) {
        return playerRepository.get(id);
    }

    public ArrayList<Player> getAllPlayers() {
        return playerRepository.getAll();
    }

    public void updatePlayer(int id, String username, String password, int level, String title, int damage, int health, double money) {
        Player player = new Player(id, username, password, level, title, damage, health, money, new HashMap<Item, Integer>());
        playerRepository.update(player);
    }

    public void deletePlayer(int id) {
        playerRepository.delete(playerRepository.get(id));
    }

    //CRUD Architect
    public void addArchitect(String username, String password, int level) {
        Architect architect = new Architect(0, username, password, level);
        architectRepository.add(architect);
    }

    public Architect getArchitect(int id) {
        return architectRepository.get(id);
    }

    public ArrayList<Architect> getAllArchitects() {
        return architectRepository.getAll();
    }

    public void updateArchitect(int id, String username, String password, int level) {
        Architect architect = new Architect(id, username, password, level);
        architectRepository.update(architect);
    }

    public void deleteArchitect(int id) {
        architectRepository.delete(architectRepository.get(id));
    }


}
