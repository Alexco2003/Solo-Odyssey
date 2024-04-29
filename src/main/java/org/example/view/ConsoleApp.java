package org.example.view;

import org.example.models.Item;
import org.example.models.Player;
import org.example.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ConsoleApp {
    private static ConsoleApp instance = null;

    private ConsoleApp() {
    }

    public static ConsoleApp getInstance() {
        if (instance == null) {
            instance = new ConsoleApp();
        }
        return instance;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("--- Player Management System ---");
            System.out.println("1. Create a new player");
            System.out.println("2. Get an existing player");
            System.out.println("3. Get all players");
            System.out.println("4. Update a player");
            System.out.println("5. Delete a player");
            System.out.println("6. Exit");

            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println("Enter player username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter player password:");
                    String password = scanner.nextLine();
                    System.out.println("Enter player level:");
                    int level = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter player title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter player damage:");
                    int damage = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter player health:");
                    int health = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter player money:");
                    double money = Double.parseDouble(scanner.nextLine());

                    Player player = new Player(0, username, password, level, title, damage, health, money, new HashMap<Item, Integer>());

                    PlayerRepository playerRepository = new PlayerRepository();
                    playerRepository.add(player);

                    System.out.println("Player added successfully!");

                    break;

                case "2":
                    System.out.println("Enter player id:");
                    int id = Integer.parseInt(scanner.nextLine());

                    playerRepository = new PlayerRepository();
                    player = playerRepository.get(id);

                    if (player != null) {
                        System.out.println(player);
                    } else {
                        System.out.println("Player not found!");
                    }

                    break;

                case "3":
                    playerRepository = new PlayerRepository();
                    ArrayList<Player> players = playerRepository.getAll();
                    for (Player p : players) {
                        System.out.println(p);
                    }
                    break;

                case "4":
                    System.out.println("Enter player id:");
                    id = Integer.parseInt(scanner.nextLine());

                    playerRepository = new PlayerRepository();
                    player = playerRepository.get(id);

                    if (player != null) {
                        System.out.println("Enter player level:");
                        level = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter player title:");
                        title = scanner.nextLine();
                        System.out.println("Enter player damage:");
                        damage = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter player health:");
                        health = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter player money:");
                        money = Double.parseDouble(scanner.nextLine());

                        player.setLevel(level);
                        player.setTitle(title);
                        player.setDamage(damage);
                        player.setHealth(health);
                        player.setMoney(money);

                        playerRepository.update(player);

                        System.out.println("Player updated successfully!");
                    } else {
                        System.out.println("Player not found!");
                    }

                    break;

                case "5":
                    System.out.println("Enter player id:");
                    id = Integer.parseInt(scanner.nextLine());

                    playerRepository = new PlayerRepository();
                    player = playerRepository.get(id);

                    if (player != null) {
                        playerRepository.delete(player);

                        System.out.println("Player deleted successfully!");
                    } else {
                        System.out.println("Player not found!");
                    }

                    break;

                case "6":
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
