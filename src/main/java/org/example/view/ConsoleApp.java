package org.example.view;

import org.example.audit.AuditSession;
import org.example.exceptions.InvalidDataException;
import org.example.models.*;
import org.example.config.seeders.DatabaseSeeder;
import org.example.config.DatabaseSetup;
import org.example.services.*;

import java.sql.SQLOutput;
import java.util.*;

public class ConsoleApp {
    private static ConsoleApp instance = null;
    private Scanner scanner = new Scanner(System.in);
    
    private UserService userService;
    private ShopService shopService;
    private DungeonService dungeonService;
    private EnemyService enemyService;
    private QuestService questService;

    private ConsoleApp() {
        this.userService = new UserService();
        this.shopService = new ShopService();
        this.dungeonService = new DungeonService();
        this.enemyService = new EnemyService();
        this.questService = new QuestService();
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
                    pause3();
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

    // Pause
    public void pause3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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


       pause3();

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
                        if (key.isStolen())
                        {
                            System.out.println("\033[0;34m" + "->Stolen: " + "Yes" + "\033[0m");
                        }
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
                        AuditSession.getInstance().write("Player " + username + " bought item " + shop1.getItems().get(itemNumber - 1).getName() + ".");
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
                        AuditSession.getInstance().write("Player " + username + " sold item " + items.get(itemNumber - 1).getName() + ".");
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "9":
                    System.out.println();
                    ArrayList<Dungeon> dungeons = dungeonService.getDungeonByPlayerId(id);
                    for (int i = 0; i < dungeons.size(); i++) {
                        ArrayList<Integer> enemiesId = dungeonService.getEnemiesByDungeonId(dungeons.get(i).getId_dungeon());
                        ArrayList<Enemy> enemies = enemyService.getEnemiesByEnemiesId(enemiesId);
                        dungeons.get(i).setEnemies(enemies);
                    }
                    int dungeonId = displayDungeons(dungeons);
                    if (dungeonId == -1)
                    {
                        System.out.println();
                        System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared all Dungeons! " + "\033[0;35m" + "The System " + "\033[0;33m"+ "is pleased with your progress." + "\033[0m");
                        AuditSession.getInstance().write("Player " + username + " cleared all Dungeons.");
                        System.out.println();
                        pause3();
                        break;
                    }

                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the Unlocked Dungeon for you..." + "\033[0m");
                    pause3();

                    Player player = userService.getPlayer(id);
                    boolean exit5 = false;
                    for (Enemy enemy : dungeons.get(dungeonId).getEnemies()) {
                        if (exit5==true)
                        {
                            break;
                        }
                        enemyService.updateEnemyEncountered(enemy.getId_enemy());
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the " + enemy.getName() + " for you..." + "\033[0m");
                        if (enemy instanceof BossAssassin || enemy instanceof BossMage || enemy instanceof BossTank)
                        {
                            System.out.println("\033[0;33m" + "Standing before you is " + enemy.getName() + ", the formidable Boss of this Dungeon! " + "\033[0;35m" + "The System " + "\033[0;33m" + "is pleased with your progress and awaits the outcome of this confrontation." + "\033[0m");
                        }

                        pause3();

                        while (enemy.getHealth()>0)
                        {
                            System.out.println();
                            System.out.println("\033[0;32m" + "Enemy Health: " + enemy.getHealth() + " \033[0;31m" + "Enemy Damage: " + enemy.getDamage() + "\033[0m");
                            System.out.println("\033[0;32m" + "Your Health: " + player.getHealth() + " \033[0;31m" + "Your Damage: " + player.getDamage() + "\033[0m");

                            if (enemy instanceof Assassin) {

                                enemy.setHealth(enemy.getHealth() - player.getDamage());
                                System.out.println("\033[0;33m" + "You attacked the " + enemy.getName() + " with " + player.getDamage() + " damage." + "\033[0m");
                                pause2();

                                if (enemy.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the " + enemy.getName() + "!" + "\033[0m");
                                    if (enemy instanceof BossAssassin)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " defeated the Boss " + enemy.getName() + ".");

                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " defeated the " + enemy.getName() + ".");

                                    }
                                    if (enemy instanceof BossAssassin)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                        AuditSession.getInstance().write("Player " + username + " cleared Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                        dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());
                                        userService.updatePlayerLevelOnReward(id, dungeons.get(dungeonId).getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, dungeons.get(dungeonId).getRewardMoney());
                                        for (Item item : ((BossAssassin) enemy).getItems())
                                        {
                                            shopService.buyItem(id, item.getId_item());
                                            userService.updateStatsOnBuy(id, item.getDamage(), item.getHealth());

                                        }
                                    }
                                    break;
                                }

                                int damage = enemy.getDamage();
                                int criticalChance = ((Assassin) enemy).getCriticalChance();
                                int random = (int) (Math.random() * 100) + 1;

                                if (random <= criticalChance) {
                                    System.out.println("\033[0;33m" + "The " + enemy.getName() + " will use a critical strike!" + "\033[0m");
                                    damage *= 2;
                                }

                                System.out.println("\033[0;33m" + "The " + enemy.getName() + " attacked you with " + damage + " damage." + "\033[0m");
                                player.setHealth(player.getHealth() - damage);

                                if (player.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the " + enemy.getName() + "..." + "\033[0m");
                                    if (enemy instanceof BossAssassin)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the Boss " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }
                                    pause3();
                                    exit5 = true;
                                    break;
                                }

                            }

                            if (enemy instanceof Mage)
                            {


                                enemy.setHealth(enemy.getHealth() - player.getDamage());
                                System.out.println("\033[0;33m" + "You attacked the " + enemy.getName() + " with " + player.getDamage() + " damage." + "\033[0m");
                   pause2();

                                if (enemy.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the " + enemy.getName() + "!" + "\033[0m");
                                    if (enemy instanceof BossMage)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " defeated the Boss " + enemy.getName() + ".");

                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " defeated the " + enemy.getName() + ".");

                                    }
                                    if (enemy instanceof BossMage)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                        AuditSession.getInstance().write("Player " + username + " cleared Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                        dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());
                                        userService.updatePlayerLevelOnReward(id, dungeons.get(dungeonId).getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, dungeons.get(dungeonId).getRewardMoney());
                                        for (Item item : ((BossMage) enemy).getItems())
                                        {
                                            shopService.buyItem(id, item.getId_item());
                                            userService.updateStatsOnBuy(id, item.getDamage(), item.getHealth());

                                        }
                                    }
                                    break;
                                }

                                int damage = enemy.getDamage();
                                int mana = ((Mage) enemy).getMana();
                                int random = (int) (Math.random() * 2);
                                int heal = 0;

                                if (mana>0)
                                {
                                    if (random == 0) {
                                        damage = (int) (damage * 1.5);
                                        ((Mage) enemy).setMana(mana - 20);
                                        System.out.println("\033[0;33m" + "The " + enemy.getName() + " will have increased damage!" + "\033[0m");

                                    } else {

                                        heal = (int) (enemy.getHealth() * 0.1);
                                        enemy.setHealth(enemy.getHealth() + heal);
                                        ((Mage) enemy).setMana(mana - 20);
                                        System.out.println("\033[0;33m" + "The " + enemy.getName() + " will heal itself!" + "\033[0m");

                                    }
                                }
                                else
                                {
                                    System.out.println("\033[0;33m" + "The " + enemy.getName() + " has no mana left!" + "\033[0m");
                                }

                                System.out.println("\033[0;33m" + "The " + enemy.getName() + " attacked you with " + damage + " damage." + "\033[0m");
                                player.setHealth(player.getHealth() - damage);

                                if (player.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the " + enemy.getName() + "..." + "\033[0m");
                                    if (enemy instanceof BossMage)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the Boss " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }                                    pause3();
                                    exit5 = true;
                                    break;
                                }



                            }

                            if (enemy instanceof Tank)
                            {
                                int armor = ((Tank) enemy).getArmor();
                                if(armor>0)
                                {
                                    System.out.println("\033[0;33m" + "The " + enemy.getName() + " has armor!" + "\033[0m");
                                    ((Tank) enemy).setArmor(armor - player.getDamage());
                                    armor = ((Tank) enemy).getArmor();
                                    if (armor < 0)
                                    {
                                        enemy.setHealth(enemy.getHealth() - Math.abs(armor));
                                        ((Tank) enemy).setArmor(0);

                                    }
                                }
                                else
                                {
                                    System.out.println("\033[0;33m" + "The " + enemy.getName() + " has no armor!" + "\033[0m");
                                    enemy.setHealth(enemy.getHealth() - player.getDamage());
                                }

                                System.out.println("\033[0;33m" + "You attacked the " + enemy.getName() + " with " + player.getDamage() + " damage." + "\033[0m");

                                pause2();

                                if (enemy.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the " + enemy.getName() + "!" + "\033[0m");
                                    if (enemy instanceof BossTank)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " defeated the Boss " + enemy.getName() + ".");

                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " defeated the " + enemy.getName() + ".");

                                    }
                                    if (enemy instanceof BossTank)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                        AuditSession.getInstance().write("Player " + username + " cleared Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                        dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());
                                        userService.updatePlayerLevelOnReward(id, dungeons.get(dungeonId).getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, dungeons.get(dungeonId).getRewardMoney());
                                        for (Item item : ((BossTank) enemy).getItems())
                                        {
                                            shopService.buyItem(id, item.getId_item());
                                            userService.updateStatsOnBuy(id, item.getDamage(), item.getHealth());

                                        }
                                    }
                                    break;
                                }

                                int damage = enemy.getDamage();

                                System.out.println("\033[0;33m" + "The " + enemy.getName() + " attacked you with " + damage + " damage." + "\033[0m");
                                player.setHealth(player.getHealth() - damage);

                                if (player.getHealth() <= 0)
                                {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the " + enemy.getName() + "..." + "\033[0m");
                                    if (enemy instanceof BossTank)
                                    {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the Boss " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }else {
                                        AuditSession.getInstance().write("Player " + username + " was defeated by the " + enemy.getName() + "and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + ".");
                                    }
                                    pause3();
                                    exit5 = true;
                                    break;
                                }

                            }




                        }
                    }



                    System.out.println();
                    break;

                case "11":
                    System.out.println();
                    ArrayList<Player> players3 = userService.getAllPlayers();
                    System.out.println("\033[0;35m" + "<<-- The System's PVP -->>" + "\033[0m");
                    System.out.println();
                    for (int i = 0; i < players3.size(); i++) {
                        int playerNumber = i + 1;
                        System.out.println("\033[0;34m" + playerNumber + ". " + players3.get(i).toString3() + "\033[0m");
                    }

                    try {
                        System.out.println("\033[0;35m" + "Enter the number of the player you want to challenge: " + "\033[0m");
                        int playerNumber1 = scanner.nextInt();
                        scanner.nextLine();
                        if (playerNumber1 < 1 || playerNumber1 > players3.size()) {
                            throw new InvalidDataException("Invalid player number. Please enter a valid number.");
                        }
                        Player player1 = players3.get(playerNumber1 - 1);
                        if (player1.getId_user() == id) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to challenge another player..." + "\033[0m");
                            System.out.println();
                            break;
                        }
                        Player player2 = userService.getPlayer(id);
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the PVP Arena for you..." + "\033[0m");
                        pause3();
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is summoning the " + "\033[0;34m" + "Player " + player1.getUsername() + "\033[0;33m" + " for you..." + "\033[0m");
                        pause3();

                        int random2 = (int) (Math.random() * 2);
                        boolean winP1 = false;
                        boolean winP2 = false;

                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is choosing who will strike first..." + "\033[0m");
                        pause3();

                        if (random2 == 0) {
                            System.out.println();
                            System.out.println("\033[0;33m" + "The " + "\033[0;34m" + "Player " + player2.getUsername() + "\033[0;33m" + " will attack first!" + "\033[0m");
                            while (player1.getHealth() > 0 && player2.getHealth() > 0) {
                                System.out.println();
                                System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " -> " + "\033[0;32m" + "Health: " + player1.getHealth() + " \033[0;31m" + "Damage: " + player1.getDamage() + "\033[0m");
                                System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " -> " + "\033[0;32m" + "Health: " + player2.getHealth() + " \033[0;31m" + "Damage: " + player2.getDamage() + "\033[0m");
                                player1.setHealth(player1.getHealth() - player2.getDamage());
                                System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player1.getUsername() + "\033[0;33m" + " with " + player2.getDamage() + " damage." + "\033[0m");
                                pause2();

                                if (player1.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player2.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player1.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP2 = true;
                                    break;
                                }

                                player2.setHealth(player2.getHealth() - player1.getDamage());
                                System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player2.getUsername() + "\033[0;33m" + " with " + player1.getDamage() + " damage." + "\033[0m");
                                pause2();

                                if (player2.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player1.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player2.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP1 = true;
                                    break;
                                }
                            }

                        } else {
                            System.out.println();
                            System.out.println("\033[0;33m" + "The " + "\033[0;34m" + "Player " + player1.getUsername() + "\033[0;33m" + " will attack first!" + "\033[0m");
                            while (player1.getHealth() > 0 && player2.getHealth() > 0) {
                                System.out.println();
                                System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " -> " + "\033[0;32m" + "Health: " + player1.getHealth() + " \033[0;31m" + "Damage: " + player1.getDamage() + "\033[0m");
                                System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " -> " + "\033[0;32m" + "Health: " + player2.getHealth() + " \033[0;31m" + "Damage: " + player2.getDamage() + "\033[0m");
                                player2.setHealth(player2.getHealth() - player1.getDamage());
                                System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player2.getUsername() + "\033[0;33m" + " with " + player1.getDamage() + " damage." + "\033[0m");
                                pause2();

                                if (player2.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player1.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player2.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP1 = true;
                                    break;
                                }

                                player1.setHealth(player1.getHealth() - player2.getDamage());
                                System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player1.getUsername() + "\033[0;33m" + " with " + player2.getDamage() + " damage." + "\033[0m");
                                pause2();

                                if (player1.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player2.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player1.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP2 = true;
                                    break;
                                }
                            }
                        }

                        if (winP1)
                        {

                            userService.updatePlayerWins(player1.getId_user());
                            userService.updatePlayerLosses(player2.getId_user());
                            AuditSession.getInstance().write("Player " + player1.getUsername() + " defeated Player " + player2.getUsername() + " in PVP.");
                        }

                        if (winP2)
                        {
                            userService.updatePlayerWins(player2.getId_user());
                            userService.updatePlayerLosses(player1.getId_user());
                            AuditSession.getInstance().write("Player " + player2.getUsername() + " defeated Player " + player1.getUsername() + " in PVP.");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    } catch (InvalidDataException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        System.out.println();
                        break;
                    }

                    System.out.println();
                    break;




                case "12":
                    System.out.println();
                    ArrayList<Dungeon> dungeons1 = dungeonService.getDungeonByPlayerId(id);
                    for (int i = 0; i < dungeons1.size(); i++) {
                        ArrayList<Integer> enemiesId = dungeonService.getEnemiesByDungeonId(dungeons1.get(i).getId_dungeon());
                        ArrayList<Enemy> enemies = enemyService.getEnemiesByEnemiesId(enemiesId);
                        dungeons1.get(i).setEnemies(enemies);
                    }
                    System.out.println("\033[0;35m" + "<<-- The System's Codex -->>" + "\033[0m");
                    System.out.println();
                    System.out.println();
                    System.out.println("\033[0;35m" + "<<-- The System's Enemies -->>" + "\033[0m");
                    System.out.println();

                    HashSet<Integer> uniqueEnemyIdsSet = new HashSet<>();
                    for (Dungeon dungeon : dungeons1) {
                        for (Enemy enemy : dungeon.getEnemies()) {
                            if (enemy.isEncountered()) {
                                uniqueEnemyIdsSet.add(enemy.getId_enemy());
                            }
                        }
                    }

                    ArrayList<Integer> uniqueEnemyIds = new ArrayList<>(uniqueEnemyIdsSet);

                    ArrayList<Enemy> uniqueEnemies = enemyService.getEnemiesByEnemiesId(uniqueEnemyIds);

                    for (Enemy enemy : uniqueEnemies) {
                        System.out.println("\033[0;35m" + enemy + "\033[0m");
                    }

                    System.out.println();
                    System.out.println("\033[0;35m" + "<<-- The System's Lore -->>" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m"+ "I. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is a vast and mysterious world, filled with danger and adventure. " + "\033[0;35m" + "The System " + "\033[0;33m" + "awaits the " +"\033[0;34m" + "Player" + "\033[0;33m" +"'s courage and determination to overcome the challenges that lie ahead." + "\033[0m");
                    if (dungeonService.countDungeonsCompleted(id)>=1)
                    {
                        System.out.println("\033[0;33m"+ "II. "+"\033[0;35m" + "The System " + "\033[0;33m" + "was created by a mysterious entity known as "+ "\033[0;35m" + "The Architect" + "\033[0;33m" + ". Its purpose remains unknown." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=2)
                    {
                        System.out.println("\033[0;33m"+ "III. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is not just a tool, it's a living entity that adapts and evolves." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=3)
                    {
                        System.out.println("\033[0;33m"+ "IV. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is designed to test the " + "\033[0;34m" + "Player" + "\033[0;33m" +"'s abilities and push them to their limits." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=4)
                    {
                        System.out.println("\033[0;33m"+ "V. "+"\033[0;35m" + "The System " + "\033[0;33m" + "rewards those who overcome its challenges with power beyond imagination." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=5)
                    {
                        System.out.println("\033[0;33m"+ "VI. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is rumored to have a mind of its own, choosing who will rise and who will fall." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=6)
                    {
                        System.out.println("\033[0;33m"+ "VII. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is said to be a gateway to other realms, each more dangerous than the last." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=7)
                    {
                        System.out.println("\033[0;33m"+ "VIII. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is believed to be a creation of " + "\033[0;35m" + "The Architect " + "\033[0;33m" + "to find the ultimate warrior." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=8)
                    {
                        System.out.println("\033[0;33m"+ "IX. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is a test, a trial for those who dare to challenge " + "\033[0;35m" + "The Architect" + "\033[0;33m" + "'s design." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=9)
                    {
                        System.out.println("\033[0;33m"+ "X. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is a path to ascension, a way for mortals to transcend their limits and reach the realm of gods." + "\033[0m");
                    }
                    if (dungeonService.countDungeonsCompleted(id)>=10)
                    {
                        System.out.println("\033[0;33m"+ "XI. "+"\033[0;35m" + "The System " + "\033[0;33m" + "is " + "\033[0;35m" + "The Architect" + "\033[0;33m" + "'s masterpiece, a world within a world, a game that's more than a game." + "\033[0m");
                    }


                    System.out.println();
                    break;

                case "13":
                    System.out.println();
                    System.out.println("\033[0;34m" + "<<-- The Player's Achievements -->>" + "\033[0m");
                    System.out.println();
                    ArrayList<Dungeon> dungeons2 = dungeonService.getDungeonByPlayerId(id);
                    for (int i = 0; i < dungeons2.size(); i++) {
                        ArrayList<Integer> enemiesId = dungeonService.getEnemiesByDungeonId(dungeons2.get(i).getId_dungeon());
                        ArrayList<Enemy> enemies = enemyService.getEnemiesByEnemiesId(enemiesId);
                        dungeons2.get(i).setEnemies(enemies);
                    }

                    HashSet<Integer> uniqueEnemyIds1 = new HashSet<>();
                    for (Dungeon dungeon : dungeons2) {
                        for (Enemy enemy : dungeon.getEnemies()) {
                            if (enemy.isEncountered()) {
                                uniqueEnemyIds1.add(enemy.getId_enemy());
                            }
                        }
                    }
                    int count = uniqueEnemyIds1.size();


                    int achievementsCompleted = 0;

                    int dungeonsCleared = dungeonService.countDungeonsCompleted(id);
                    if (dungeonsCleared == 10) achievementsCompleted++;
                    System.out.println((dungeonsCleared == 10 ? "\033[0;32m" : "\033[0;33m") + "I. Dungeon Master - Dungeons Cleared: " + dungeonsCleared + "/10 " + (dungeonsCleared == 10 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int enemiesEncountered = count;
                    if (enemiesEncountered == 25) achievementsCompleted++;
                    System.out.println((enemiesEncountered == 25 ? "\033[0;32m" : "\033[0;33m") + "II. Monster Hunter - Enemies Encountered: " + enemiesEncountered + "/25 " + (enemiesEncountered == 25 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int bossesDefeated = dungeonService.countDungeonsCompleted(id);
                    if (bossesDefeated == 10) achievementsCompleted++;
                    System.out.println((bossesDefeated == 10 ? "\033[0;32m" : "\033[0;33m") + "III. Boss Slayer - Bosses Defeated: " + bossesDefeated + "/10 " + (bossesDefeated == 10 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int bossItemsAcquired = dungeonService.countDungeonsCompleted(id)*6;
                    if (bossItemsAcquired == 60) achievementsCompleted++;
                    System.out.println((bossItemsAcquired == 60 ? "\033[0;32m" : "\033[0;33m") + "IV. Treasure Hunter - Boss Items Acquired: " + bossItemsAcquired + "/60 " + (bossItemsAcquired == 60 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int itemsBought = shopService.countItemsBought(id)-bossItemsAcquired;
                    if (itemsBought == 37) achievementsCompleted++;
                    System.out.println((itemsBought == 37 ? "\033[0;32m" : "\033[0;33m") + "V. Shopaholic - Items Bought: " + itemsBought + "/37 " + (itemsBought == 37 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int totalItems = shopService.countItemsBought(id);
                    if (totalItems == 97) achievementsCompleted++;
                    System.out.println((totalItems == 97 ? "\033[0;32m" : "\033[0;33m") + "VI. Collector - Total Items Acquired: " + totalItems + "/97 " + (totalItems == 97 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    System.out.println();
                    System.out.println((achievementsCompleted == 6 ? "\033[0;32m" : "\033[0;33m") + "God of War - All Achievements: " + achievementsCompleted + "/6 " + (achievementsCompleted == 6 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    // TODO : Add more achievements related to quests


                    System.out.println();
                    break;

                case "14":
                    System.out.println();
                    System.out.println("\033[0;35m" + "<<-- The System's Leaderboard -->>" + "\033[0m");
                    System.out.println();
                    ArrayList<Player> players = userService.getAllPlayers();
                    players.sort(Comparator.comparingInt(Player::getLevel).reversed());
                    for (int i = 0; i < players.size(); i++) {
                        int playerNumber = i + 1;
                        System.out.println("\033[0;34m" + playerNumber + ". " + players.get(i).toString2() + "\033[0m");
                    }
                    break;

                    



                case "200":
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is exiting the profile of " + "\033[0;34m" +  "Player " + "\033[0;34m" + username + "\033[0;33m" + "..." + "\033[0m");
                    System.out.println();
                    //pause3();
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
                    //pause3();
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

    public int displayDungeons(ArrayList<Dungeon> dungeons)
    {
        int dungeonIndex = -1;
        System.out.println("\033[0;35m" + "<<-- Dungeons -->>" + "\033[0m");
        for (int i = 0; i < dungeons.size(); i++) {
            System.out.println();
            int dungeonNumber = i + 1;
            if (i == 0) {
                if (!dungeons.get(i).isCompleted()) {
                    System.out.println("\033[0;33m" + dungeonNumber + ". " + dungeons.get(i) + "\033[0;33m" + "(Unlocked)" + "\033[0m");
                    dungeonIndex = i;
                } else {
                    System.out.println("\033[0;32m" + dungeonNumber + ". " + dungeons.get(i) + "\033[0;32m" + "(Completed)" + "\033[0m");
                }
            } else {
                if (!dungeons.get(i).isCompleted()) {
                    if (dungeons.get(i - 1).isCompleted()) {
                        System.out.println("\033[0;33m" + dungeonNumber + ". " + dungeons.get(i) + "\033[0;33m" + "(Unlocked)" + "\033[0m");
                        dungeonIndex = i;
                    } else {
                        System.out.println("\033[0;31m" + dungeonNumber + ". " + dungeons.get(i) + "\033[0;31m" + "(Locked)" + "\033[0m");
                    }
                } else {
                    System.out.println("\033[0;32m" + dungeonNumber + ". " + dungeons.get(i) + "\033[0;32m" + "(Completed)" + "\033[0m");
                }
            }
        }

        return dungeonIndex;

    }











}
