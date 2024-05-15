



















package org.example.test;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test {
    public static void main(String[] args) {


//        String sentence1 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;35m" + "The System " + "\033[0;33m" + "was created by ... to make the ... stronger.'";
//        String sentence2 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;34m" + "The Player " + "\033[0;33m" + "will get s....... and s....... with the help of " + "\033[0;35m" + "The System" + "\033[0;33m" + ".'";
//        String sentence3 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;35m" + "The Architect " + "\033[0;33m" + "is the creator of " + "\033[0;35m" + "..." + "\033[0;33m" + " , and the master of the " + "\033[0;34m" + "... " + "\033[0;33m" + ".'";
//        System.out.println(sentence1);
//        System.out.println(sentence2);
//        System.out.println(sentence3);
    }
//        AtomicBoolean isTimeout = new AtomicBoolean(false);
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<String[]> future = executor.submit(new Callable<String[]>() {
//            public String[] call() {
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Enter your first answer:");
//                String input1 = scanner.nextLine();
//                System.out.println("Enter your second answer:");
//                String input2 = scanner.nextLine();
//                return new String[]{input1, input2};
//            }
//        });
//
//        try {
//            String[] result = future.get(5, TimeUnit.SECONDS); // Timeout after 5 seconds
//            System.out.println("First answer: " + result[0]);
//            System.out.println("Second answer: " + result[1]);
//        } catch (TimeoutException e) {
//            isTimeout.set(true);
//            System.out.println("Timeout occurred. You cannot input anymore.");
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        } finally {
//            executor.shutdownNow();
//        }
//    }
}


//package org.example.test;
//
//public class Test {
//    public static void main(String[] args) {
//
//        {
//            System.out.print("\033[0;31m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;32m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;35m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;33m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;36m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;30m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//
//        {
//            System.out.print("\033[0;94m" + "Player" + "\033[0m");
//            System.out.println();
//        }
//        System.out.print("\033[0;34m" + "Player" + "\033[0m");
//    }
//}
//
//
//











//package org.example.test;
//
//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//
//public class Test {
//    public static void main(String[] args) {
//        try {
//            Terminal terminal = TerminalBuilder.terminal();
//            int counter = 0;
//            while (counter < 10) {
//                int codePoint = terminal.reader().read();
//                if (codePoint == ' ') {
//                    counter++;
//                    System.out.println("Counter incremented. Current value: " + counter);
//                } else {
//                    System.out.println("Invalid input. Please press space.");
//                }
//            }
//            System.out.println("You've pressed space 10 times!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//package org.example.test;
//import java.io.IOException;
//import java.util.Random;
//
//public class Test {
//    public static void main(String[] args) throws InterruptedException, IOException {
//        Random rand = new Random();
//        int winningPosition = rand.nextInt(10);
//
//        int currentPosition = 0;
//        boolean gameRunning = true;
//
//        while (gameRunning) {
//            if (System.in.available() > 0) { // check if the user has pressed a key
//                if (currentPosition-1 == winningPosition) {
//                    System.out.println("\nYou win!");
//                    System.out.println("The Architect was at position " + winningPosition + "!");
//                } else {
//                    System.out.println("\nYou lose!");
//                }
//                gameRunning = false;
//            } else {
//                // print the loading animation with the current position
//                if (currentPosition!=winningPosition) {
//                    System.out.print("\033[0;34m" + "\rPlayer" + "\033[0m"); // Player in blue
//                     }
//
//                if (currentPosition == winningPosition) {
//                    System.out.print("\033[0;35m" + " \rThe Architect" + "\033[0m"); // The Architect in purple
//                }
//                //System.out.print("\033[0;33m" + ".".repeat(currentPosition % 4) + "Loading" + ".".repeat(currentPosition % 4) + "\033[0m"); // Loading in yellow
//
//                // update the current position
//                currentPosition = (currentPosition + 1) % 10;
//
//                // wait for a while before the next frame
//                Thread.sleep(500);
//            }
//        }
//    }
//}
//import java.io.IOException;
//
//public class Test  {
//    public static void main(String[] args) throws InterruptedException, IOException {
//        int winningPosition = 3; // predefined winning position
//        int currentPosition = 0;
//        boolean gameRunning = true;
//
//        while (gameRunning) {
//            if (System.in.available() > 0) { // check if the user has pressed a key
//                if (currentPosition == winningPosition) {
//                    System.out.println("You win!");
//                } else {
//                    System.out.println("You lose!");
//                }
//                gameRunning = false;
//            } else {
//                // clear the console
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
//
//                // print the line with the current position
//                for (int i = 0; i < currentPosition; i++) {
//                    System.out.print("-");
//                }
//                System.out.println("*");
//
//                // update the current position
//                currentPosition = (currentPosition + 1) % 20;
//
//                // wait for a while before the next frame
//                Thread.sleep(2000);
//            }
//        }
//    }
//}
//        String[] lines = new String[]{
//                "\033[0;31m" + "_     ____    _      _     _   _____  _    ____  _      _    _____  ____  _____  _  _ ", // Red
//                "\033[0;32m" + "/ \\   /  _ \\  / \\__/|/ \\ /\\/ \\ /__ __\\/ \\  /  _ \\/ \\  /|/ \\  /__ __\\/  _ \\/__ __\\/ \\/ \\", // Green
//                "\033[0;33m" + "| |   | / \\|  | |\\/||| | ||| |   / \\  | |  | / \\|| |\\ ||| |    / \\  | / \\|  / \\  | || |", // Yellow
//                "\033[0;34m" + "| |_ /| |-||  | |  ||| \\_/|| |_ /| |  | |  | |-||| | \\||| |    | |  | |-||  | |  | |\\_/", // Blue
//                "\033[0;35m" + "\\____/\\_/ \\|  \\_/  \\|\\____/\\____/\\_/  \\_/  \\_/ \\|\\_/  \\|\\_/    \\_/  \\_/ \\|  \\_/  \\_/(_)", // Purple
//                "\033[0m" // Reset
//        };
//
//        int exit = 0;
//        while (exit<3) {
//            for (String line : lines) {
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
//                System.out.println(line);
//                Thread.sleep(1000);
//            }
//
//            exit++;
//
//        }
//
//        System.out.println("\033[0;31m" + "⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣶⣤⡀⢀⣤⣶⣿⣿⣿⣿⣿⣷⣤⡀⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀"); // Red
//        System.out.println("\033[0;31m" + "⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄"); // Red
//        System.out.println("\033[0;31m" + "⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇"); // Red
//        System.out.println("\033[0;31m" + "⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀"); // Red
//        System.out.println("\033[0;31m" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\033[0m"); // Red
//    }
//}

//        BossAssassinRepository bossAssassinRepository = new BossAssassinRepository();
//        System.out.println(bossAssassinRepository.get(81));
//
//        AssassinRepository assassinRepository = new AssassinRepository();
//        Assassin assassin = assassinRepository.get(81);
//        System.out.println(assassin);
//
//        BossMageRepository bossMageRepository = new BossMageRepository();
//        System.out.println(bossMageRepository.get(97));
//
//        MageRepository mageRepository = new MageRepository();
//        Mage mage = mageRepository.get(97);
//        System.out.println(mage);
//
//        BossTankRepository bossTankRepository = new BossTankRepository();
//        System.out.println(bossTankRepository.get(140));
//
//        TankRepository tankRepository = new TankRepository();
//        Tank tank = tankRepository.get(140);
//        System.out.println(tank);


//
//        MageRepository mageRepository = new MageRepository();
//        System.out.println(mageRepository.get(91));
//
//        TankRepository tankRepository = new TankRepository();
//        System.out.println(tankRepository.get(83));

//        HashMap<Item, Integer> items = new HashMap<>();

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
    //}//
//}
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
