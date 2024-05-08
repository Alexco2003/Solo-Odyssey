package org.example.view;

import org.example.models.Architect;
import org.example.models.Item;
import org.example.models.Player;
import org.example.config.seeders.DatabaseSeeder;
import org.example.config.DatabaseSetup;
import org.example.models.Shop;
import org.example.services.ShopService;
import org.example.services.UserService;

import java.util.*;

public class ConsoleApp {
    private static ConsoleApp instance = null;
    private Scanner scanner = new Scanner(System.in);
    
    private UserService userService;
    private ShopService shopService;

    private ConsoleApp() {
        this.userService = new UserService();
        this.shopService = new ShopService();
    }

    public static ConsoleApp getInstance() {
        if (instance == null) {
            instance = new ConsoleApp();
        }
        return instance;
    }

    // Main method
    public void start() {

        boolean exit = false;
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.setup();
        DatabaseSeeder databaseSeeder = new DatabaseSeeder();
        databaseSeeder.seed();
        //displayTitleStart();
        //displayTitleMotto();


        while (!exit) {

            displayTitleLogin();
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println();
                    System.out.println("\033[0;33m"+ "Enter username: " + "\033[0m");
                    String username = scanner.nextLine();
                    System.out.println("\033[0;33m"+ "Enter password: " + "\033[0m");
                    String password = scanner.nextLine();

                    if (userService.checkLogin(username, password) != -1) 
                    {
                    if (userService.checkPlayerExists(userService.checkLogin(username,password)) && userService.checkArchitectExists(userService.checkLogin(username,password)))
                        {
                            System.out.println();

                            playerOrArchitectMenu(username, userService.checkLogin(username,password));
                        }
                    else
                    {
                        if (userService.checkArchitectExists(userService.checkLogin(username, password)))    {
                            System.out.println();
                            architectMenu();
                        }
                        if (userService.checkPlayerExists(userService.checkLogin(username, password)))    {
                            System.out.println();
                            playerMenu(username, userService.checkLogin(username,password));
                        }
                    }

                    }
                    else {
                        if (userService.checkUsernameExists(username)) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter the correct password..." + "\033[0m");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to register first..." + "\033[0m");
                            System.out.println();

                        }
                    }

                    break;

                case "2":
                    System.out.println();
                    System.out.println("\033[0;33m"+ "Enter username: " + "\033[0m");
                    String username1 = scanner.nextLine();
                    System.out.println("\033[0;33m"+ "Enter password: " + "\033[0m");
                    String password1 = scanner.nextLine();
                    if (userService.checkUsernameExists(username1)) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to choose another username..." + "\033[0m");
                        System.out.println();
                        break;
                    }
                    userService.registerPlayer(username1, password1);
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to login..." + "\033[0m");
                    System.out.println();


                    break;

                case "3":
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "will be waiting..." + "\033[0m");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    exit = true;
                    break;

                default:
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "received an invalid command..." + "\033[0m");
                    System.out.println();
                    break;
            }
        }

        scanner.close();
    }

    // Clear Screen
    public void clearScreen() {
        for(int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    // Display Title various
    public void displayTitle() {
        System.out.println("\033[0;31m" + ".▄▄ ·       ▄▄▌                  ·▄▄▄▄   ▄· ▄▌.▄▄ · .▄▄ · ▄▄▄ . ▄· ▄▌"); // Red
        System.out.println("\033[0;32m" + "▐█ ▀. ▪     ██•  ▪         ▪     ██▪ ██ ▐█▪██▌▐█ ▀. ▐█ ▀. ▀▄.▀·▐█▪██▌"); // Green
        System.out.println("\033[0;33m" + "▄▀▀▀█▄ ▄█▀▄ ██▪   ▄█▀▄      ▄█▀▄ ▐█· ▐█▌▐█▌▐█▪▄▀▀▀█▄▄▀▀▀█▄▐▀▀▪▄▐█▌▐█▪"); // Yellow
        System.out.println("\033[0;34m" + "▐█▄▪▐█▐█▌.▐▌▐█▌▐▌▐█▌.▐▌    ▐█▌.▐▌██. ██  ▐█▀·.▐█▄▪▐█▐█▄▪▐█▐█▄▄▌ ▐█▀·."); // Blue
        System.out.println("\033[0;35m" + " ▀▀▀▀  ▀█▄▀▪.▀▀▀  ▀█▄▀▪     ▀█▄▀▪▀▀▀▀▀•   ▀ •  ▀▀▀▀  ▀▀▀▀  ▀▀▀   ▀ •" + "\033[0m"); // Purple
    }

    public void displayTitleStart() {
        displayTitle();

        for (int i = 0; i < 9; i++) {
            System.out.print("\033[0;33m" + "\rLoading" + ".".repeat(i % 4));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\rLoading complete! Press Enter to continue..." + "\033[0m");
        scanner.nextLine();
        System.out.println();

    }

    public void displayMotto(){
        System.out.println("\033[0;30m" + "   ┏┳┓┓            "+"\033[0;34m"+"┓"+"\033[0;30m"+"                           ┓");
        System.out.println("\033[0;30m" + "    ┃ ┣┓┏┓  "+"\033[0;34m"+"┓┏┏┏┓┏┓┃┏"+"\033[0;30m"+"  ┏┓┏┓┏┓  ┏┓┏┓┏┓┓┏  ╋┏┓  ╋┣┓┏┓  "+"\033[0;35m"+"┏╋┏┓┏┓┏┓┏┓");
        System.out.println("\033[0;30m" + "••• ┻ ┛┗┗   "+"\033[0;34m"+"┗┻┛┗ ┗┻┛┗"+"\033[0;30m"+"  ┗┻┛ ┗   ┣┛┛ ┗ ┗┫  ┗┗┛  ┗┛┗┗   "+"\033[0;35m"+"┛┗┛ ┗┛┛┗┗┫"+"\033[0;30m"+"•••");
        System.out.println("\033[0;30m" + "                               ┛      ┛                       " + "\033[0;35m" +"┛" + "\033[0m");
    }

    public void displayTitleMotto() {
        displayTitle();
        displayMotto();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println();
    }

    public void displayTitleLogin() {
        displayTitle();
        System.out.println();
        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" +  "greets you..." + "\033[0m");
        System.out.println("\033[0;33m" + "1. Login" + "\033[0m");
        System.out.println("\033[0;33m" + "2. Register" + "\033[0m");
        System.out.println("\033[0;33m" + "3. Exit" + "\033[0m");
        System.out.println("\033[0;33m" + "Enter command: " + "\033[0m");
    }

    // Player Menu
    public void displayPlayerMenu(String username, Integer id){
        displayTitle();
        System.out.println();
        System.out.println("\033[0;35m" + "The System " + "\033[0;34m" + "is designed to make the Player " + username + " stronger." + "\033[0m");
        System.out.println();
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "1. View your Profile                 |" + "\033[0m");
        System.out.println("\033[0;34m" + "2. View your Inventory               |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "3. View your Shop                    |" + "\033[0m");
        System.out.println("\033[0;34m" + "4. View your Shop (Sorted by Price)  |" + "\033[0m");
        System.out.println("\033[0;34m" + "5. View your Shop (Sorted by Damage) |" + "\033[0m");
        System.out.println("\033[0;34m" + "6. View your Shop (Sorted by Health) |" + "\033[0m");
        System.out.println("\033[0;34m" + "7. Buy an Item                       |" + "\033[0m");
        System.out.println("\033[0;34m" + "8. Sell an Item                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "9. Play Dungeons                     |" + "\033[0m");
        System.out.println("\033[0;34m" + "10. Play Quests                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "11. Play PVP                         |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "12. Codex                            |" + "\033[0m");
        System.out.println("\033[0;34m" + "13. Achievements                     |" + "\033[0m");
        System.out.println("\033[0;34m" + "14. Leaderboard                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "15. Tutorial                         |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "200. Exit                            |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "Enter command: " + "\033[0m");

    }

    public void playerMenu(String username, Integer id)
    {
        boolean exit = false;
        while (!exit) {
            displayPlayerMenu(username, id);
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println();
                    System.out.println("\033[0;34m" + userService.getPlayer(id) + "\033[0m");
                    break;

                case "2":
                    System.out.println();
                    System.out.println("\033[0;34m" + "<<-- Inventory -->>" + "\033[0m");
                    System.out.println();
                    userService.getPlayer(id).getInventory().forEach((key, value) -> {
                        System.out.println("\033[0;34m" + "Item Name: " + key.getName());
                        System.out.println("->Quantity: " + value);
                        System.out.println("->Damage: " + key.getDamage());
                        System.out.println("->Health: " + key.getHealth() + "\033[0m");
                        System.out.println();
                    });

                    break;

                case "3":
                    System.out.println();
                    Shop shop = shopService.getShop(id);
                    System.out.println("\033[0;35m" + "<<-- " + shop.getName() + " -->> " + "\033[0m");
                    System.out.println();
                    for (int i = 0; i < shop.getItems().size(); i++) {
                        if (!shop.getItems().get(i).isStolen()) {
                            int itemNumber = i + 1;
                            System.out.println("\033[0;35m" + itemNumber + ". " + shop.getItems().get(i) + "\033[0m");                        }
                    }

                    break;

                case "4":
                    System.out.println();
                    Shop shop2 = shopService.getShop(id);
                    System.out.println("\033[0;35m" + "<<-- " + shop2.getName() + " -->> " + "\033[0m");
                    System.out.println();
                    shop2.getItems().sort(Comparator.comparingDouble(Item::getPrice));
                    for (int i = 0; i < shop2.getItems().size(); i++)
                    {
                        if (!shop2.getItems().get(i).isStolen()) {
                            System.out.println("\033[0;35m" + shop2.getItems().get(i) + "\033[0m");                        }
                    }
                    break;

                case "5":
                    System.out.println();
                    Shop shop3 = shopService.getShop(id);
                    System.out.println("\033[0;35m" + "<<-- " + shop3.getName() + " -->> " + "\033[0m");
                    System.out.println();
                    shop3.getItems().sort(Comparator.comparingInt(Item::getDamage).reversed());
                    for (int i = 0; i < shop3.getItems().size(); i++)
                    {
                        if (!shop3.getItems().get(i).isStolen()) {
                            System.out.println("\033[0;35m" + shop3.getItems().get(i) + "\033[0m");                        }
                    }
                    break;

                case "6":
                    System.out.println();
                    Shop shop4 = shopService.getShop(id);
                    System.out.println("\033[0;35m" + "<<-- " + shop4.getName() + " -->> " + "\033[0m");
                    System.out.println();
                    shop4.getItems().sort(Comparator.comparingInt(Item::getHealth).reversed());
                    for (int i = 0; i < shop4.getItems().size(); i++)
                    {
                        if (!shop4.getItems().get(i).isStolen()) {
                            System.out.println("\033[0;35m" + shop4.getItems().get(i) + "\033[0m");                        }
                    }
                    break;


                case "7":
                    try {
                        System.out.println();
                        Shop shop7 = shopService.getShop(id);
                        System.out.println("\033[0;35m" + "<<-- " + shop7.getName() + " -->> " + "\033[0m");
                        System.out.println();
                        for (int i = 0; i < shop7.getItems().size(); i++) {
                            if (!shop7.getItems().get(i).isStolen()) {
                                int itemNumber = i + 1;
                                System.out.println("\033[0;35m" + itemNumber + ". " + shop7.getItems().get(i) + "\033[0m");                        }
                        }
                    System.out.println("\033[0;35m" + "Enter the number of the item you want to buy: " + "\033[0m");
                    int itemNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (itemNumber < 1 || itemNumber > 37)
                    {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        System.out.println();
                        break;
                    }
                    Shop shop1 = shopService.getShop(id);
                    Player player = userService.getPlayer(id);
                    if (player.getMoney() - shop1.getItems().get(itemNumber - 1).getPrice() < 0)
                    {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to have enough money..." + "\033[0m");
                        System.out.println();
                        break;

                    }
                    if (shop1.getItems().get(itemNumber - 1).getQuantity() <= 0)
                    {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires the item to be in stock..." + "\033[0m");
                        System.out.println();
                        break;
                    }
                        int idItem = shop1.getItems().get(itemNumber - 1).getId_item();
                        shopService.buyItem(id, idItem);
                        userService.updatePlayerMoneyOnBuy(id, shop1.getItems().get(itemNumber - 1).getPrice());
                        userService.updateStatsOnBuy(id, shop1.getItems().get(itemNumber - 1).getDamage(), shop1.getItems().get(itemNumber - 1).getHealth());
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "8":
                    try {
                        System.out.println();
                        Player player = userService.getPlayer(id);
                        HashMap<Item, Integer> inventory = player.getInventory();
                        ArrayList<Item> items = new ArrayList<>(inventory.keySet());
                        for (int i = 0; i < items.size(); i++) {
                            int itemNumber = i + 1;
                            System.out.println("\033[0;35m" + itemNumber + ". " + items.get(i).toString2() + "\033[0m");
                        }
                        System.out.println("\033[0;35m" + "Enter the number of the item you want to sell: " + "\033[0m");
                        int itemNumber = scanner.nextInt();
                        scanner.nextLine();
                        if (itemNumber < 1 || itemNumber > items.size())
                        {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        int idItem = items.get(itemNumber - 1).getId_item();
                        shopService.sellItem(id, idItem);
                        userService.updatePlayerMoneyOnSell(id, items.get(itemNumber - 1).getPrice()*0.75);
                        userService.updateStatsOnSell(id, items.get(itemNumber - 1).getDamage(), items.get(itemNumber - 1).getHealth());
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                    



                case "200":
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is exiting the profile of " + "\033[0;34m" +  "Player " + "\033[0;34m" + username + "\033[0;33m" + "..." + "\033[0m");
                    System.out.println();
                    // Uncomment this in the final, now for testing it is commented
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    exit = true;
                    break;

                default:
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "received an invalid command..." + "\033[0m");
                    System.out.println();
                    break;
            }
        }

    }


    // Architect Menu
    public void displayArchitectMenu(){
        displayTitle();
        System.out.println();
        System.out.println("\033[0;35m" + "The System is ruled by its creator, The Architect." + "\033[0m");
        System.out.println();
        System.out.println("\033[0;35m" + "1. Create a new Player" + "\033[0m");
        System.out.println("\033[0;35m" + "2. Create a new Architect" + "\033[0m");
        System.out.println("\033[0;35m" + "3. View an existing Player" + "\033[0m");
        System.out.println("\033[0;35m" + "4. View an existing Architect" + "\033[0m");
        System.out.println("\033[0;35m" + "5. View all Players" + "\033[0m");
        System.out.println("\033[0;35m" + "6. View all Architects" + "\033[0m");
        System.out.println("\033[0;35m" + "7. Update a Player" + "\033[0m");
        System.out.println("\033[0;35m" + "8. Update an Architect" + "\033[0m");
        System.out.println("\033[0;35m" + "9. Delete a Player" + "\033[0m");
        System.out.println("\033[0;35m" + "10. Delete an Architect" + "\033[0m");
        System.out.println("\033[0;35m" + "11. Exit" + "\033[0m");
        System.out.println("\033[0;35m" + "Enter command: " + "\033[0m");

    }

    public void architectMenu()
    {
        boolean exit = false;
        while (!exit) {
            displayArchitectMenu();
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter username:" + "\033[0m");
                        String username2 = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter password:" + "\033[0m");
                        String password2 = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter level:" + "\033[0m");
                        int level = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter title:" + "\033[0m");
                        String title = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter damage:" + "\033[0m");
                        int damage = scanner.nextInt();
                        System.out.println("\033[0;35m" + "Enter health:" + "\033[0m");
                        int health = scanner.nextInt();
                        System.out.println("\033[0;35m" + "Enter money:" + "\033[0m");
                        double money = scanner.nextDouble();
                        scanner.nextLine();
                        userService.addPlayer(username2, password2, level, title, damage, health, money);
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "2":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter username:" + "\033[0m");
                        String username1 = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter password:" + "\033[0m");
                        String password1 = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter level:" + "\033[0m");
                        int level1 = scanner.nextInt();
                        scanner.nextLine();
                        userService.addArchitect(username1, password1, level1);
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "3":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter player id:" + "\033[0m");
                        int id = Integer.parseInt(scanner.nextLine());

                        Player player = userService.getPlayer(id);

                        if (player != null) {
                            System.out.println();
                            System.out.println("\033[0;34m" + player + "\033[0m");
                        } else {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the player with the given id." + "\033[0m");
                            System.out.println();
                        }

                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                case "4":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter architect id:" + "\033[0m");
                        int id1 = Integer.parseInt(scanner.nextLine());

                        Architect architect = userService.getArchitect(id1);

                        if (architect != null) {
                            System.out.println();
                            System.out.println("\033[0;35m" + architect + "\033[0m");
                        } else {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the architect with the given id." + "\033[0m");
                            System.out.println();
                        }

                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                case "5":
                    System.out.println();
                    ArrayList<Player> players = userService.getAllPlayers();
                    if (players.isEmpty()) {
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize any players." + "\033[0m");
                        System.out.println();
                        break;
                    }
                    for (Player player1 : players) {
                        System.out.println("\033[0;34m" + player1 + "\033[0m");

                    }

                    break;

                case "6":
                    System.out.println();
                    ArrayList<Architect> architects = userService.getAllArchitects();
                    if (architects.isEmpty()) {
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize any architects." + "\033[0m");
                        System.out.println();
                        break;
                    }
                    for (Architect architect1 : architects) {
                        System.out.println("\033[0;35m" + architect1 + "\033[0m");
                    }

                    break;

                case "7":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter player id:" + "\033[0m");
                        int id2 = Integer.parseInt(scanner.nextLine());
                        if (userService.getPlayer(id2) == null) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the player with the given id." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        System.out.println("\033[0;35m" + "Enter level:" + "\033[0m");
                        int level2 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter title:" + "\033[0m");
                        String title1 = scanner.nextLine();
                        System.out.println("\033[0;35m" + "Enter damage:" + "\033[0m");
                        int damage1 = scanner.nextInt();
                        System.out.println("\033[0;35m" + "Enter health:" + "\033[0m");
                        int health1 = scanner.nextInt();
                        System.out.println("\033[0;35m" + "Enter money:" + "\033[0m");
                        double money1 = scanner.nextDouble();
                        scanner.nextLine();
                        userService.updatePlayer(id2, "", "", level2, title1, damage1, health1, money1);
                        System.out.println();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "8":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter architect id:" + "\033[0m");
                        int id3 = Integer.parseInt(scanner.nextLine());
                        if (id3==1) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "does not allow alteration of the original Architect..." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        if (userService.getArchitect(id3) == null) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the architect with the given id." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        System.out.println("\033[0;35m" + "Enter level:" + "\033[0m");
                        int level3 = scanner.nextInt();
                        scanner.nextLine();
                        userService.updateArchitect(id3, "", "", level3);
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                case "9":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter player id:" + "\033[0m");
                        int id4 = Integer.parseInt(scanner.nextLine());
                        if (userService.getPlayer(id4) == null) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the player with the given id." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        userService.deletePlayer(id4);
                        System.out.println();
                        exit = true;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                case "10":
                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter architect id:" + "\033[0m");
                        int id5 = Integer.parseInt(scanner.nextLine());
                        if (id5==1) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "does not allow termination of the original Architect..." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        if (userService.getArchitect(id5) == null) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "did not recognize the architect with the given id." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        userService.deleteArchitect(id5);
                        System.out.println();
                        exit = true;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                case "11":
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is exiting the profile of " + "\033[0;35m" + "The Architect" +"\033[0;33m" + "..." + "\033[0m");
                    System.out.println();
                    // Uncomment this in the final, now for testing it is commented
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    exit = true;
                    break;

                default:
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "received an invalid command..." + "\033[0m");
                    System.out.println();
                    break;
            }
        }


    }

    // Display Player or Architect Menu
    public void displayPlayerOrArchitectMenu() {
        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to choose between Player or Architect..." + "\033[0m");
        System.out.println("\033[0;33m" + "1. Player" + "\033[0m");
        System.out.println("\033[0;33m" + "2. Architect" + "\033[0m");
        System.out.println("\033[0;33m" + "Enter command: " + "\033[0m");
    }

    public void playerOrArchitectMenu(String username, Integer id)
    {
        boolean exit = false;
        while (!exit) {
            displayPlayerOrArchitectMenu();
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println();
                    playerMenu(username, id);
                    exit=true;
                    break;

                case "2":
                    System.out.println();
                    architectMenu();
                    exit=true;
                    break;

                default:
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "received an invalid command..." + "\033[0m");
                    System.out.println();
                    break;
            }
        }
    }








}
