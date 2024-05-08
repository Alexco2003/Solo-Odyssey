package org.example.test;

import org.example.models.Item;
import org.example.models.Player;
import org.example.repositories.ItemRepository;
import org.example.repositories.PlayerRepository;
import java.io.Console;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        HashMap<Item, Integer> items = new HashMap<>();

//         ItemRepository itemRepository;
//
//         itemRepository = new ItemRepository();
//         itemRepository.updateItemQuantityOnBuy(292);

//        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "encountered an error: "  + "!" + "\n" + "\033[0;35m" + "The System " + "\033[0;33m" + "wants you to try again!" + "\033[0m");
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Couldn't get Console instance");
//            System.exit(0);
//        }
//
//        System.out.println("\033[0;33m" + "Enter username: " + "\033[0m");
//        String username = console.readLine();
//
//        System.out.println("\033[0;33m" + "Enter password: " + "\033[0m");
//        char[] passwordChars = console.readPassword();
//        String password = new String(passwordChars);
//
//
//
//        System.out.println("Player added successfully!");
    }
}
//// mask password with asterisk java console
//public class Test {
//
//    public void consoleFunc() {
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Couldn't get Console instance");
//            System.exit(0);
//        }
//
//        char[] passwordArray = console.readPassword("Enter your  password: ");
//        for (int i = 0; i < passwordArray.length; i++) {
//            System.out.print("*");
//        }
//        System.out.println();
//        console.printf("Password entered was: \n", new String(passwordArray));
//
//    }
//
//    public static void main(String[] args) {
//        new Test().consoleFunc();
//    }
//}
//import java.io.Console;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//import javax.swing.*;
//import java.io.Console;
//
//public class Test {
//    public static void main(String[] args) {
//        System.out.println("Enter username: ");
//        String username = new Scanner(System.in).nextLine();
//
//        String password = getPassword();
//        // Rest of your code...
//    }
//
//    public static String getPassword() {
//        String password;
//        Console console = System.console();
//        if (console == null) {
//            password = getPasswordWithoutConsole("Enter password: ");
//        } else {
//            password = String.valueOf(console.readPassword("Enter password: "));
//        }
//        return password;
//    }
//
//    public static String getPasswordWithoutConsole(String prompt) {
//        final JPasswordField passwordField = new JPasswordField();
//        return JOptionPane.showConfirmDialog(
//                null,
//                passwordField,
//                prompt,
//                JOptionPane.OK_CANCEL_OPTION,
//                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(passwordField.getPassword()) : "";
//    }
//}
//import org.jline.reader.LineReader;
//import org.jline.reader.LineReaderBuilder;
//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//
//public class Test{
//    public static void main(String[] args) {
//        try {
//            Terminal terminal = TerminalBuilder.terminal();
//            LineReader reader = LineReaderBuilder.builder()
//                    .terminal(terminal)
//                    .build();
//
//            System.out.println("\033[0;33m" + "Enter username: " + "\033[0m");
//            String username = reader.readLine();
//
//            System.out.println("\033[0;33m" + "Enter password: " + "\033[0m");
//            String password = reader.readLine("", '*');
//
//            // Rest of your code...
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//public class Test {
//    public static void main(String[] args) {
//
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Couldn't get Console instance");
//            System.exit(0);
//        }
//
//        System.out.println("\033[0;33m" + "Enter username: " + "\033[0m");
//        String username = console.readLine();
//
//        System.out.println("\033[0;33m" + "Enter password: " + "\033[0m");
//        char[] passwordChars = console.readPassword();
//        String password = new String(passwordChars);

//            System.out.println(".▄▄ ·       ▄▄▌                  ·▄▄▄▄   ▄· ▄▌.▄▄ · .▄▄ · ▄▄▄ . ▄· ▄▌");
//            System.out.println("▐█ ▀. ▪     ██•  ▪         ▪     ██▪ ██ ▐█▪██▌▐█ ▀. ▐█ ▀. ▀▄.▀·▐█▪██▌");
//            System.out.println("▄▀▀▀█▄ ▄█▀▄ ██▪   ▄█▀▄      ▄█▀▄ ▐█· ▐█▌▐█▌▐█▪▄▀▀▀█▄▄▀▀▀█▄▐▀▀▪▄▐█▌▐█▪");
//            System.out.println("▐█▄▪▐█▐█▌.▐▌▐█▌▐▌▐█▌.▐▌    ▐█▌.▐▌██. ██  ▐█▀·.▐█▄▪▐█▐█▄▪▐█▐█▄▄▌ ▐█▀·.");
//            System.out.println(" ▀▀▀▀  ▀█▄▀▪.▀▀▀  ▀█▄▀▪     ▀█▄▀▪▀▀▀▀▀•   ▀ •  ▀▀▀▀  ▀▀▀▀  ▀▀▀   ▀ •");
//            System.out.println();


//
//
//    }
//}


//package org.example.view;
//
//import org.example.models.Item;
//import org.example.models.Player;
//import org.example.repositories.PlayerRepository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Scanner;
//
//public class ConsoleApp {
//    private static org.example.view.ConsoleApp instance = null;
//
//    private ConsoleApp() {
//    }
//
//    public static org.example.view.ConsoleApp getInstance() {
//        if (instance == null) {
//            instance = new org.example.view.ConsoleApp();
//        }
//        return instance;
//    }
//
//    public void start() {
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//        displayTitle();
//
//        while (!exit) {
//            System.out.println("--- Player Management System ---");
//            System.out.println("1. Create a new player");
//            System.out.println("2. Get an existing player");
//            System.out.println("3. Get all players");
//            System.out.println("4. Update a player");
//            System.out.println("5. Delete a player");
//            System.out.println("6. Exit");
//
//            String input = scanner.nextLine();
//
//            switch (input) {
//
//                case "1":
//                    System.out.println("Enter player username:");
//                    String username = scanner.nextLine();
//                    System.out.println("Enter player password:");
//                    String password = scanner.nextLine();
//                    System.out.println("Enter player level:");
//                    int level = Integer.parseInt(scanner.nextLine());
//                    System.out.println("Enter player title:");
//                    String title = scanner.nextLine();
//                    System.out.println("Enter player damage:");
//                    int damage = Integer.parseInt(scanner.nextLine());
//                    System.out.println("Enter player health:");
//                    int health = Integer.parseInt(scanner.nextLine());
//                    System.out.println("Enter player money:");
//                    double money = Double.parseDouble(scanner.nextLine());
//
//                    Player player = new Player(0, username, password, level, title, damage, health, money, new HashMap<Item, Integer>());
//
//                    PlayerRepository playerRepository = new PlayerRepository();
//                    playerRepository.add(player);
//
//                    System.out.println("Player added successfully!");
//
//                    break;
//
//                case "2":
//                    System.out.println("Enter player id:");
//                    int id = Integer.parseInt(scanner.nextLine());
//
//                    playerRepository = new PlayerRepository();
//                    player = playerRepository.get(id);
//
//                    if (player != null) {
//                        System.out.println(player);
//                    } else {
//                        System.out.println("Player not found!");
//                    }
//
//                    break;
//
//                case "3":
//                    playerRepository = new PlayerRepository();
//                    ArrayList<Player> players = playerRepository.getAll();
//                    for (Player p : players) {
//                        System.out.println(p);
//                    }
//                    break;
//
//                case "4":
//                    System.out.println("Enter player id:");
//                    id = Integer.parseInt(scanner.nextLine());
//
//                    playerRepository = new PlayerRepository();
//                    player = playerRepository.get(id);
//
//                    if (player != null) {
//                        System.out.println("Enter player level:");
//                        level = Integer.parseInt(scanner.nextLine());
//                        System.out.println("Enter player title:");
//                        title = scanner.nextLine();
//                        System.out.println("Enter player damage:");
//                        damage = Integer.parseInt(scanner.nextLine());
//                        System.out.println("Enter player health:");
//                        health = Integer.parseInt(scanner.nextLine());
//                        System.out.println("Enter player money:");
//                        money = Double.parseDouble(scanner.nextLine());
//
//                        player.setLevel(level);
//                        player.setTitle(title);
//                        player.setDamage(damage);
//                        player.setHealth(health);
//                        player.setMoney(money);
//
//                        playerRepository.update(player);
//
//                        System.out.println("Player updated successfully!");
//                    } else {
//                        System.out.println("Player not found!");
//                    }
//
//                    break;
//
//                case "5":
//                    System.out.println("Enter player id:");
//                    id = Integer.parseInt(scanner.nextLine());
//
//                    playerRepository = new PlayerRepository();
//                    player = playerRepository.get(id);
//
//                    if (player != null) {
//                        playerRepository.delete(player);
//
//                        System.out.println("Player deleted successfully!");
//                    } else {
//                        System.out.println("Player not found!");
//                    }
//
//                    break;
//
//                case "6":
//                    System.out.println("Exiting...");
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Invalid option. Please try again.");
//                    break;
//            }
//        }
//
//        scanner.close();
//    }
//
//    public void displayTitle() {
//        System.out.println("\033[0;31m" + ".▄▄ ·       ▄▄▌                  ·▄▄▄▄   ▄· ▄▌.▄▄ · .▄▄ · ▄▄▄ . ▄· ▄▌"); // Red
//        System.out.println("\033[0;32m" + "▐█ ▀. ▪     ██•  ▪         ▪     ██▪ ██ ▐█▪██▌▐█ ▀. ▐█ ▀. ▀▄.▀·▐█▪██▌"); // Green
//        System.out.println("\033[0;33m" + "▄▀▀▀█▄ ▄█▀▄ ██▪   ▄█▀▄      ▄█▀▄ ▐█· ▐█▌▐█▌▐█▪▄▀▀▀█▄▄▀▀▀█▄▐▀▀▪▄▐█▌▐█▪"); // Yellow
//        System.out.println("\033[0;34m" + "▐█▄▪▐█▐█▌.▐▌▐█▌▐▌▐█▌.▐▌    ▐█▌.▐▌██. ██  ▐█▀·.▐█▄▪▐█▐█▄▪▐█▐█▄▄▌ ▐█▀·."); // Blue
//        System.out.println("\033[0;35m" + " ▀▀▀▀  ▀█▄▀▪.▀▀▀  ▀█▄▀▪     ▀█▄▀▪▀▀▀▀▀•   ▀ •  ▀▀▀▀  ▀▀▀▀  ▀▀▀   ▀ •"); // Purple
//        System.out.println("\033[0m"); // Reset
//    }
//
//
//}
