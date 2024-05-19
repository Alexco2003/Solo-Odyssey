package org.example.view;

import org.example.audit.AuditSession;
import org.example.exceptions.InvalidDataException;
import org.example.models.*;
import org.example.config.seeders.DatabaseSeeder;
import org.example.config.DatabaseSetup;
import org.example.services.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
        displayTitleStart();
        flushConsole();
        displayTitleMotto();
        flushConsole();


        while (!exit) {

            displayTitleLogin();
            flushConsole();
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

    public void flushConsole() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        System.out.println("\rLoading complete! Press 'Enter' to continue..." + "\033[0m");
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
        System.out.println("\033[0;34m" + "1. Tutorial                          |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "2. View your Profile                 |" + "\033[0m");
        System.out.println("\033[0;34m" + "3. View your Inventory               |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "4. View your Shop                    |" + "\033[0m");
        System.out.println("\033[0;34m" + "5. View your Shop (Sorted by Price)  |" + "\033[0m");
        System.out.println("\033[0;34m" + "6. View your Shop (Sorted by Damage) |" + "\033[0m");
        System.out.println("\033[0;34m" + "7. View your Shop (Sorted by Health) |" + "\033[0m");
        System.out.println("\033[0;34m" + "8. Buy an Item                       |" + "\033[0m");
        System.out.println("\033[0;34m" + "9. Sell an Item                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "10. Play Dungeons                    |" + "\033[0m");
        System.out.println("\033[0;34m" + "11. Play Quests                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "12. Play PVP                         |" + "\033[0m");
        System.out.println("\033[0;34m" + "13. Play Arena                       |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "14. Codex                            |" + "\033[0m");
        System.out.println("\033[0;34m" + "15. Achievements                     |" + "\033[0m");
        System.out.println("\033[0;34m" + "16. PVP Stats                        |" + "\033[0m");
        System.out.println("\033[0;34m" + "17. Leaderboard                      |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "18. Exit                             |" + "\033[0m");
        System.out.println("\033[0;34m" + "-------------------------------------|" + "\033[0m");
        System.out.println("\033[0;34m" + "Enter command: " + "\033[0m");

    }

    public void playerMenu(String username, Integer id)
    {
        boolean exit = false;
        while (!exit) {
            displayPlayerMenu(username, id);
            flushConsole();
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println();

                    System.out.println("\033[0;33m" + "Welcome, " + "\033[0;34m"+ username + "\033[0;33m" + ", to The Tutorial!" + "\033[0m");
                    System.out.println();
                    pause2();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "will guide you through its features..." + "\033[0m");
                    System.out.println();
                    System.out.println();
                    pause2();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Level/Stats/Money ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> A Player has a Level, Damage, Health, and Money." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Money is the currency used to buy Items from the Shop." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Player's Level is increased by playing Dungeons, Quests, PVP, and Arena." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Each level up will increase your Damage and Health by 5 and 10, respectively." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Max Level is 999, but even after this point, you will still get the stats bonus from a level up." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> 'The Leaderboard' will display all the Players sorted by their level." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The 'Title' of the Player will change based on the level." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The System's ranking is from E to S (E,D,C,B,A,S)." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Items ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> Items can be bought from the Shop or found in Dungeons." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Items increase your Damage and Health with their stats." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Items can be sold at the Shop for 75% of their initial price." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> After defeating a Boss, you will receive his 'Stolen' Items." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> 'Stolen' Items are unique and can be sold at the Shop for a higher price than any other item." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Once sold, a 'Stolen' Item is lost (it can't be bought back)." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Enemies ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> Enemies can be found in Dungeons and the Arena." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Each enemy has Damage and Health." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Enemies can be Assassins, Mages, or Tanks." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Each enemy has unique abilities and stats." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Assassins have a chance to deal critical damage. A critical strike will deal double damage." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Mages have mana. Mages have a chance to deal increased damage (*1.5) or heal themselves (of 10% of their current health). Each ability will consume 20 mana." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Tanks have armor. Tanks will reduce the incoming damage by the amount of armor they have. If the incoming damage is higher than the armor, the armor will be reduced to 0 and the remaining damage will be dealt to the Tank's health." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Dungeons ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> Dungeons can be played only once." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> All Dungeons will be played in order." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Upon completing a Dungeon, the next one will unlock." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Complete all the Dungeons to unlock the Arena." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Dungeons will reward you with money, experience, and items." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Each Dungeon has normal enemies and a single Boss." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The first strike will be decided by The System for each encounter." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Completing all Dungeons will unlock The Arena." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Quests ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> Quests will reward you with money and experience." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Quests can be played multiple times." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Daily Quest can be played for multiple times and the rewards will be given every time." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The System resets 'The Daily Quest' every time the Player enters the 'Quests' section, because a new day has started." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Active Quests can be played for multiple times, but rewards will be given only once, upon first completion." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Passive Quests can't be played, but once the conditions are met, the rewards will be given." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- PVP ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> PVP will reward you with stats regarding your wins and losses." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Completing PVP Passive Quests will reward you with money and experience." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The first attack will be decided every round, it is not predefined." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> In PVP, both players have a missing attack chance. If the attack is missing, the player will deal 0 damage." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The default missing attack chance is 35%, but it can be decreased by completing PVP Passive Quests (-5% per quest)." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> You can see your PVP Stats in the 'PVP Stats' section." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Arena ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> The Arena is a repeatable Dungeon, with a random generated combination of previous enemies." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Arena can contain multiple Bosses." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Arena will reward you with money and experience." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Arena will not reward you with items." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Arena will unlock after completing all Dungeons." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Codex ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> The Codex contains information about The System's enemies and lore." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The Codex will unlock new information as you progress through the game." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> An enemy is 'encountered' when it is seen in a Dungeon (does not matter if it is defeated or not)." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Once an enemy is encountered, its information will be stored in the Codex." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Each Dungeon that is completed will unlock a new lore entry in the Codex." + "\033[0m");
                    System.out.println();

                    System.out.println();
                    System.out.println("\033[0;33m" + "Press 'Enter' to jump to the next section..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("\033[0;35m" + "<- Achievements ->" + "\033[0m");
                    System.out.println();
                    System.out.println("\033[0;33m" + "-> Achievements are unlocked by completing specific tasks." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Achievements are a way to track your progress through the game." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> Achievements will not reward you with money, experience, or items." + "\033[0m");
                    System.out.println("\033[0;33m" + "-> The 'Platinum' is a special achievement that is unlocked by completing all other achievements." + "\033[0m");
                    System.out.println();


                    System.out.println();
                    System.out.println("\033[0;35m" + "The System" + "\033[0;33m" + " has shared its knowledge with you and hopes you are now prepared for the journey ahead. Press 'Enter' to continue..." + "\033[0m");
                    scanner.nextLine();

                    System.out.println();
                    break;

                case "2":
                    System.out.println();
                    System.out.println("\033[0;34m" + userService.getPlayer(id) + "\033[0m");
                    break;

                case "3":
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

                case "4":
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

                case "5":
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

                case "6":
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

                case "7":
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


                case "8":
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
                        AuditSession.getInstance().write("Player " + username + " bought item '" + shop1.getItems().get(itemNumber - 1).getName() + "'.");
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "9":
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
                        AuditSession.getInstance().write("Player " + username + " sold item '" + items.get(itemNumber - 1).getName() + "'.");
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        scanner.nextLine();
                        System.out.println();
                        break;
                    }

                case "10":
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
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the '" + enemy.getName() + "' for you..." + "\033[0m");
                        if (enemy instanceof BossAssassin || enemy instanceof BossMage || enemy instanceof BossTank)
                        {
                            System.out.println("\033[0;33m" + "Standing before you is '" + enemy.getName() + "', the formidable Boss of this Dungeon! " + "\033[0;35m" + "The System " + "\033[0;33m" + "is pleased with your progress and awaits the outcome of this confrontation." + "\033[0m");
                        }

                        pause3();

                        int whoWillStrikeFirst = (int) (Math.random() * 2) + 1;
                        if(whoWillStrikeFirst == 1)
                        {
                            System.out.println();
                            System.out.println("\033[0;33m" + "You will strike first!" + "\033[0m");
                            pause3();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will strike first!" + "\033[0m");
                            pause3();
                        }

                        if(whoWillStrikeFirst==1)
                        {
                            while (enemy.getHealth()>0)
                            {
                                System.out.println();
                                System.out.println("\033[0;32m" + "Enemy Health: " + enemy.getHealth() + " \033[0;31m" + "Enemy Damage: " + enemy.getDamage() + "\033[0m");
                                System.out.println("\033[0;32m" + "Your Health: " + player.getHealth() + " \033[0;31m" + "Your Damage: " + player.getDamage() + "\033[0m");

                                if (enemy instanceof Assassin) {

                                    enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");
                                    pause2();

                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossAssassin)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossAssassin)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(13).getName() + "'.");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will use a critical strike!" + "\033[0m");
                                        damage *= 2;
                                    }

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossAssassin)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }
                                        pause3();
                                        exit5 = true;
                                        break;
                                    }

                                }

                                if (enemy instanceof Mage)
                                {


                                    enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");
                                    pause2();

                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossMage)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossMage)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(13).getName() + "'.");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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
                                            System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will have increased damage!" + "\033[0m");

                                        } else {

                                            heal = (int) (enemy.getHealth() * 0.1);
                                            enemy.setHealth(enemy.getHealth() + heal);
                                            ((Mage) enemy).setMana(mana - 20);
                                            System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will heal itself!" + "\033[0m");

                                        }
                                    }
                                    else
                                    {
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no mana left!" + "\033[0m");
                                    }

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossMage)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
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
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has armor!" + "\033[0m");
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
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no armor!" + "\033[0m");
                                        enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    }

                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");

                                    pause2();

                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossTank)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossTank)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(13).getName() + "'.");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossTank)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }
                                        pause3();
                                        exit5 = true;
                                        break;
                                    }

                                }




                            }



                        } else
                        {
                            while (enemy.getHealth()>0)
                            {
                                System.out.println();
                                System.out.println("\033[0;32m" + "Enemy Health: " + enemy.getHealth() + " \033[0;31m" + "Enemy Damage: " + enemy.getDamage() + "\033[0m");
                                System.out.println("\033[0;32m" + "Your Health: " + player.getHealth() + " \033[0;31m" + "Your Damage: " + player.getDamage() + "\033[0m");

                                if (enemy instanceof Assassin) {

                                    int damage = enemy.getDamage();
                                    int criticalChance = ((Assassin) enemy).getCriticalChance();
                                    int random = (int) (Math.random() * 100) + 1;

                                    if (random <= criticalChance) {
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will use a critical strike!" + "\033[0m");
                                        damage *= 2;
                                    }

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    pause2();
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossAssassin)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }
                                        pause3();
                                        exit5 = true;
                                        break;
                                    }

                                    enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");

                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossAssassin)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossAssassin)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(13).getName() + "'.");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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

                                }

                                if (enemy instanceof Mage)
                                {
                                    int damage = enemy.getDamage();
                                    int mana = ((Mage) enemy).getMana();
                                    int random = (int) (Math.random() * 2);
                                    int heal = 0;

                                    if (mana>0)
                                    {
                                        if (random == 0) {
                                            damage = (int) (damage * 1.5);
                                            ((Mage) enemy).setMana(mana - 20);
                                            System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will have increased damage!" + "\033[0m");

                                        } else {

                                            heal = (int) (enemy.getHealth() * 0.1);
                                            enemy.setHealth(enemy.getHealth() + heal);
                                            ((Mage) enemy).setMana(mana - 20);
                                            System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will heal itself!" + "\033[0m");

                                        }
                                    }
                                    else
                                    {
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no mana left!" + "\033[0m");
                                    }

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    pause2();
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossMage)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }                                    pause3();
                                        exit5 = true;
                                        break;
                                    }

                                    enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");

                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossMage)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossMage)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(13).getName() + "'.");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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





                                }

                                if (enemy instanceof Tank)
                                {
                                    int damage = enemy.getDamage();

                                    System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                    pause2();
                                    player.setHealth(player.getHealth() - damage);

                                    if (player.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                        if (enemy instanceof BossTank)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the Boss '" + enemy.getName() + "' and failed to clear Dungeon " + dungeons.get(dungeonId).getName() + "'.");
                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                        }
                                        pause3();
                                        exit5 = true;
                                        break;
                                    }

                                    int armor = ((Tank) enemy).getArmor();
                                    if(armor>0)
                                    {
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has armor!" + "\033[0m");
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
                                        System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no armor!" + "\033[0m");
                                        enemy.setHealth(enemy.getHealth() - player.getDamage());
                                    }

                                    System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player.getDamage() + " damage." + "\033[0m");


                                    if (enemy.getHealth() <= 0)
                                    {
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                        if (enemy instanceof BossTank)
                                        {
                                            AuditSession.getInstance().write("Player " + username + " defeated the Boss '" + enemy.getName() + "'.");

                                        }else {
                                            AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");

                                        }
                                        if (enemy instanceof BossTank)
                                        {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared the Dungeon!" + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeons.get(dungeonId).getName() + "'.");
                                            dungeonService.completeDungeon(dungeons.get(dungeonId).getId_dungeon());

                                            int count3 = 0;
                                            dungeons.get(dungeonId).setCompleted(true);
                                            ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);
                                            for(Dungeon dungeon : dungeons)
                                            {
                                                if (dungeon.isCompleted())
                                                {
                                                    count3++;
                                                }
                                            }
                                            if (count3 == 1)
                                            {
                                                questService.completeQuest(quests.get(10).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(10).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(10).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(10).getName() + "'.");
                                            }
                                            if (count3 == 2)
                                            {
                                                questService.completeQuest(quests.get(11).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(11).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(11).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(11).getName() + "'.");
                                            }
                                            if (count3 == 3)
                                            {
                                                questService.completeQuest(quests.get(12).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(12).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(12).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(12).getName() + "'.");
                                            }
                                            if (count3 == 5)
                                            {
                                                questService.completeQuest(quests.get(13).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(13).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(13).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest " + quests.get(13).getName() + ".");
                                            }
                                            if (count3 == 10)
                                            {
                                                questService.completeQuest(quests.get(14).getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quests.get(14).getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quests.get(14).getRewardMoney());
                                                AuditSession.getInstance().write("Player " + username + " completed Quest '" + quests.get(14).getName() + "'.");
                                            }

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



                                }




                            }
                        }


                    }


                    System.out.println();
                    break;

                case "11":
                    System.out.println();
                    ArrayList<Quest> quests = questService.getQuestsByPlayerId(id);

                    ArrayList<Integer> availableQuests = displayQuests(quests);

                    try {
                        System.out.println();
                        System.out.println("\033[0;35m" + "Enter the number of the quest you want to play: " + "\033[0m");
                        int questNumber = scanner.nextInt();
                        scanner.nextLine();


                        if (!availableQuests.contains(questNumber-1)) {
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter a valid number..." + "\033[0m");
                        } else {
                            int questIndex = questNumber-1;
                            Quest quest = quests.get(questIndex);
                            System.out.println();
                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the Quest " + "\033[0;35m" + quest.getName() + "\033[0;33m" +" for you..." + "\033[0m");
                            pause3();
                            System.out.println();
                            if (questIndex == 0) {

                                Random random = new Random();
                                int randomNumber = random.nextInt(5);

                                String exercise = "";
                                String exerciseSingle = "";

                                if (randomNumber == 0) {
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;35m" + "||            The System              ||");
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                    System.out.println("\033[0;33m" + "|| following task:                    ||");
                                    System.out.println("\033[0;33m" + "||                                    ||");
                                    System.out.println("\033[0;33m" + "|| Perform 25 Push-ups.               ||");
                                    System.out.println("\033[0;33m" + "|| To confirm each push-up, press     ||");
                                    System.out.println("\033[0;33m" + "|| 'Space + Enter' 25 times.          ||");
                                    System.out.println("\033[0;35m" + "========================================" + "\033[0m");
                                    exercise = "Push-ups";
                                    exerciseSingle = "Push-up";
                                } else if (randomNumber == 1) {
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;35m" + "||            The System              ||");
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                    System.out.println("\033[0;33m" + "|| following task:                    ||");
                                    System.out.println("\033[0;33m" + "||                                    ||");
                                    System.out.println("\033[0;33m" + "|| Perform 25 Pull-ups.               ||");
                                    System.out.println("\033[0;33m" + "|| To confirm each pull-up, press     ||");
                                    System.out.println("\033[0;33m" + "|| 'Space + Enter' 25 times.          ||");
                                    System.out.println("\033[0;35m" + "========================================" + "\033[0m");
                                    exercise = "Pull-ups";
                                    exerciseSingle = "Pull-up";

                                } else if (randomNumber == 2) {
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;35m" + "||            The System              ||");
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                    System.out.println("\033[0;33m" + "|| following task:                    ||");
                                    System.out.println("\033[0;33m" + "||                                    ||");
                                    System.out.println("\033[0;33m" + "|| Perform 25 Sit-ups.                ||");
                                    System.out.println("\033[0;33m" + "|| To confirm each sit-up, press      ||");
                                    System.out.println("\033[0;33m" + "|| 'Space + Enter' 25 times.          ||");
                                    System.out.println("\033[0;35m" + "========================================" + "\033[0m");
                                    exercise = "Sit-ups";
                                    exerciseSingle = "Sit-up";

                                } else if (randomNumber == 3) {
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;35m" + "||            The System              ||");
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                    System.out.println("\033[0;33m" + "|| following task:                    ||");
                                    System.out.println("\033[0;33m" + "||                                    ||");
                                    System.out.println("\033[0;33m" + "|| Perform 25 Squats.                 ||");
                                    System.out.println("\033[0;33m" + "|| To confirm each squat, press       ||");
                                    System.out.println("\033[0;33m" + "|| 'Space + Enter' 25 times.          ||");
                                    System.out.println("\033[0;35m" + "========================================" + "\033[0m");
                                    exercise = "Squats";
                                    exerciseSingle = "Squat";
                                } else if (randomNumber == 4) {
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;35m" + "||            The System              ||");
                                    System.out.println("\033[0;35m" + "========================================");
                                    System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                    System.out.println("\033[0;33m" + "|| following task:                    ||");
                                    System.out.println("\033[0;33m" + "||                                    ||");
                                    System.out.println("\033[0;33m" + "|| Run 25 KM.                         ||");
                                    System.out.println("\033[0;33m" + "|| To confirm each KM, press          ||");
                                    System.out.println("\033[0;33m" + "|| 'Space + Enter' 25 times.          ||");
                                    System.out.println("\033[0;35m" + "========================================" + "\033[0m");
                                    exercise = "KM";
                                    exerciseSingle = "KM";
                                }

                                displayCountdown();
                                flushConsole();



                                System.out.println();
                                int counter = 0;
                                System.out.println("\033[0;33m" + "Press 'Space + Enter' to confirm each " + exerciseSingle + ".");
                                System.out.println();
                                while (counter < 25) {

                                    String input1 = scanner.nextLine();
                                    if (input1.equals(" ")) {
                                        counter++;
                                        System.out.println("Counter incremented. Confirmed " + exercise + ": " + counter);
                                    } else {
                                        System.out.println("Failed to confirm " + exerciseSingle + ". Please press 'Space + Enter'.");
                                    }
                                }
                                System.out.println();
                                System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Daily Quest!" + "\033[0m");
                                userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                userService.updatePlayerTitle(id);
                                userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                            }
                            if (questIndex == 1)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Press 'Enter' when " + "\033[0;35m" + "The Architect" + "\033[0;33m" + "   ||");
                                System.out.println("\033[0;33m" + "|| appears on screen.                 ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();


                                        Random rand = new Random();
        int winningPosition = rand.nextInt(10);

        int currentPosition = 0;
        boolean gameRunning = true;

        while (gameRunning) {
            if (System.in.available() > 0) {
                if (currentPosition-1 == winningPosition) {
                    System.out.println();
                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Agility Quest 1!" + "\033[0m");
                    if (!quest.isCompleted()) {
                        questService.completeQuest(quest.getId_quest());
                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                        userService.updatePlayerTitle(id);
                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                    }
                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                    scanner.nextLine();
                } else {
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Agility Quest 1..." + "\033[0m");
                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                    scanner.nextLine();
                }
                gameRunning = false;
            } else {

                if (currentPosition!=winningPosition) {
                    System.out.print("\033[0;34m" + "\rPlayer" + "\033[0m");
                     }

                if (currentPosition == winningPosition) {
                    System.out.print("\033[0;35m" + " \rThe Architect" + "\033[0m");
                }

                currentPosition = (currentPosition + 1) % 10;

                Thread.sleep(500);
            }
        }



                            }
                            if (questIndex == 2)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Press 'Enter' when " + "\033[0;35m" + "The System" + "\033[0;33m" + "      ||");
                                System.out.println("\033[0;33m" + "|| appears on screen.                 ||");
                                System.out.println("\033[0;33m" + "|| You have 25 seconds,               ||");
                                System.out.println("\033[0;33m" + "|| or the mission will fail.          ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                Random rand = new Random();
                                int winningPosition = rand.nextInt(10);

                                int currentPosition = 0;
                                AtomicBoolean gameRunning = new AtomicBoolean(true);

                                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

                                Runnable task = () -> {
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Agility Quest 2 due to time out..." + "\033[0m");
                                    gameRunning.set(false);
                                };

                                executorService.schedule(task, 25, TimeUnit.SECONDS);

                                while (gameRunning.get()) {
                                    if (System.in.available() > 0) {
                                        executorService.shutdownNow();
                                        if (currentPosition-1 == winningPosition) {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Agility Quest 2!" + "\033[0m");
                                            if (!quest.isCompleted()) {
                                                questService.completeQuest(quest.getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                            }
                                            AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                            scanner.nextLine();
                                        } else {
                                            System.out.println();
                                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Agility Quest 2..." + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                            scanner.nextLine();
                                        }
                                        gameRunning.set(false);
                                    } else {

                                        if (currentPosition!=winningPosition) {
                                            int random2 = (int) (Math.random() * 2);
                                            if (random2 == 0) {
                                                System.out.print("\033[0;34m" + "\rPlayer" + "\033[0m");
                                            } else {
                                                System.out.print("\033[0;35m" + "\rThe Architect" + "\033[0m");
                                            }

                                        }

                                        if (currentPosition == winningPosition) {
                                            System.out.print("\033[0;35m" + " \rThe System" + "\033[0m");
                                        }

                                        currentPosition = (currentPosition + 1) % 10;

                                        Thread.sleep(500);
                                    }
                                }



                            }
                            if (questIndex == 3)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Press 'Enter' when " + "\033[0m" + "Player" + "\033[0;33m" + "          ||");
                                System.out.println("\033[0;33m" + "|| appears on screen.                 ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                Random rand = new Random();
                                int winningPosition = rand.nextInt(10);

                                int currentPosition = 0;
                                boolean gameRunning = true;

                                while (gameRunning) {
                                    if (System.in.available() > 0) {
                                        if (currentPosition-1 == winningPosition) {
                                            System.out.println();
                                            System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Agility Quest 3!" + "\033[0m");
                                            if (!quest.isCompleted()) {
                                                questService.completeQuest(quest.getId_quest());
                                                userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                                userService.updatePlayerTitle(id);
                                                userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                            }
                                            AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                            scanner.nextLine();
                                        } else {
                                            System.out.println();
                                            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Agility Quest 3..." + "\033[0m");
                                            AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                            scanner.nextLine();
                                        }
                                        gameRunning = false;
                                    } else {

                                        if (currentPosition!=winningPosition) {
                                            int random = (int) (Math.random() * 7);
                                            if (random == 0)
                                            {
                                                System.out.print("\033[0;31m" + "\rPlayer" + "\033[0m"); // Red
                                            }
                                            if (random == 1)
                                            {
                                                System.out.print("\033[0;32m" + "\rPlayer" + "\033[0m"); // Green
                                            }
                                            if (random == 2)
                                            {
                                                System.out.print("\033[0;35m" + "\rPlayer" + "\033[0m"); // Purple
                                            }
                                            if (random == 3)
                                            {
                                                System.out.print("\033[0;33m" + "\rPlayer" + "\033[0m"); // Yellow
                                            }
                                            if (random == 4)
                                            {
                                                System.out.print("\033[0;36m" + "\rPlayer" + "\033[0m"); // Cyan
                                            }
                                            if (random == 5)
                                            {
                                                System.out.print("\033[0;30m" + "\rPlayer" + "\033[0m"); // Black
                                            }
                                            if (random == 6)
                                            {
                                                System.out.print("\033[0;94m" + "\rPlayer" + "\033[0m"); // Light Blue
                                            }


                                        }

                                        if (currentPosition == winningPosition) {
                                            System.out.print("\033[0;34m" + " \rPlayer" + "\033[0m"); // Blue
                                        }

                                        currentPosition = (currentPosition + 1) % 10;

                                        Thread.sleep(500);
                                    }
                                }


                            }
                            if (questIndex == 4)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Answer the following riddle.       ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                System.out.println("\033[0;33m" + "Riddle: " + "\033[0;35m" + "What gets bigger, the more you take away?" + "\033[0m");
                                System.out.println();
                                System.out.println("\033[0;33m" + "Enter your answer: " + "\033[0m");
                                String answer = scanner.nextLine();
                                if (answer.trim().equalsIgnoreCase("A Hole") || answer.trim().equalsIgnoreCase("Hole"))
                                {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Intelligence Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }
                                else
                                {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 1..." + "\033[0m");
                                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                }


                            }
                            if (questIndex == 5)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Complete the sentence.             ||");
                                System.out.println("\033[0;33m" + "|| You have 5 seconds,                ||");
                                System.out.println("\033[0;33m" + "|| or the mission will fail.          ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                AtomicBoolean isTimeout = new AtomicBoolean(false);

                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                Future<String[]> future = executor.submit(new Callable<String[]>() {
                                    public String[] call() {
                                        Scanner scanner = new Scanner(System.in);

                                        int sentenceChoice = new Random().nextInt(3);

                                        String sentence1 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;35m" + "The System " + "\033[0;33m" + "was created by ... to make the ... stronger.'";
                                        String sentence2 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;34m" + "The Player " + "\033[0;33m" + "will get s....... and s....... with the help of " + "\033[0;35m" + "The System" + "\033[0;33m" + ".'";
                                        String sentence3 = "\033[0;33m" + "Complete the sentence: '" + "\033[0;35m" + "The Architect " + "\033[0;33m" + "is the creator of " + "\033[0;35m" + "..." + "\033[0;33m" + " , and the master of the " + "\033[0;34m" + "... " + "\033[0;33m" + ".'";

                                        if(sentenceChoice == 0)
                                        {
                                            System.out.println(sentence1);
                                        }
                                        if(sentenceChoice == 1)
                                        {
                                            System.out.println(sentence2);
                                        }
                                        if(sentenceChoice == 2)
                                        {
                                            System.out.println(sentence3);
                                        }

                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Enter your answers: " + "\033[0m");
                                        System.out.println("\033[0;33m" + "First answer: " + "\033[0m");
                                        String input1 = scanner.nextLine();
                                        if (isTimeout.get()) return new String[]{"", "", Integer.toString(sentenceChoice)};
                                        System.out.println("\033[0;33m" + "Second answer: " + "\033[0m");
                                        String input2 = scanner.nextLine();
                                        return new String[]{input1, input2, Integer.toString(sentenceChoice)};
                                    }
                                });

                                try {
                                    String[] result = future.get(5, TimeUnit.SECONDS);
                                    if (!isTimeout.get()) {
                                        flushConsole();
                                        int sentenceChoice = Integer.parseInt(result[2]);
                                        if (sentenceChoice == 0)
                                        {
                                            if (result[0].trim().equalsIgnoreCase("The Architect") && result[1].trim().equalsIgnoreCase("Player")) {
                                                System.out.println();
                                                System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Intelligence Quest 2!" + "\033[0m");
                                                if (!quest.isCompleted()) {
                                                    questService.completeQuest(quest.getId_quest());
                                                    userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                                    userService.updatePlayerTitle(id);
                                                    userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                                }
                                                AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                            } else {
                                                System.out.println();
                                                System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 2..." + "\033[0m");
                                                AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                            }

                                        }
                                        if (sentenceChoice == 1)
                                        {
                                            if (result[0].trim().equalsIgnoreCase("stronger") && result[1].trim().equalsIgnoreCase("stronger")) {
                                                System.out.println();
                                                System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Intelligence Quest 2!" + "\033[0m");
                                                if (!quest.isCompleted()) {
                                                    questService.completeQuest(quest.getId_quest());
                                                    userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                                    userService.updatePlayerTitle(id);
                                                    userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                                }
                                                AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                            } else {
                                                System.out.println();
                                                System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 2..." + "\033[0m");
                                                AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                            }

                                        }
                                        if (sentenceChoice == 2)
                                        {
                                            if (result[0].trim().equalsIgnoreCase("The System") && result[1].trim().equalsIgnoreCase("Player")) {
                                                System.out.println();
                                                System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Intelligence Quest 2!" + "\033[0m");
                                                if (!quest.isCompleted()) {
                                                    questService.completeQuest(quest.getId_quest());
                                                    userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                                    userService.updatePlayerTitle(id);
                                                    userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                                }
                                                AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                            } else {
                                                System.out.println();
                                                System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 2..." + "\033[0m");
                                                AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                            }

                                        }

                                    } else {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 2 due to time out..." + "\033[0m");
                                        AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                    }


                                } catch (TimeoutException e) {
                                    isTimeout.set(true);
                                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 2 due to time out..." + "\033[0m");
                                    flushConsole();

                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                } finally {
                                    executor.shutdownNow();
                                }


                            }
                            if (questIndex == 6)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Complete the quiz.                 ||");
                                System.out.println("\033[0;33m" + "|| You need to answer correctly,      ||");
                                System.out.println("\033[0;33m" + "|| on all questions.                  ||");
                                System.out.println("\033[0;33m" + "|| A question can have only one       ||");
                                System.out.println("\033[0;33m" + "|| correct answer.                    ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                int correctAnswers = 0;
                                int totalQuestions = 4;

                                System.out.println("\033[0;33m" + "1. There are two enemies in front of an enemy, two enemies behind an enemy and an enemy in the middle. How many enemies are there?" + "\033[0m");
                                System.out.println("\033[0;33m" + "a) 4" + "\033[0m");
                                System.out.println("\033[0;33m" + "b) 3" + "\033[0m");
                                System.out.println("\033[0;33m" + "c) 2" + "\033[0m");
                                System.out.println("\033[0;33m" + "d) 5" + "\033[0m");
                                System.out.println();

                                flushConsole();
                                System.out.println("\033[0;33m" + "Enter your answer: " + "\033[0m");
                                String answer1 = scanner.nextLine();
                                if (answer1.trim().equalsIgnoreCase("b") || answer1.trim().equalsIgnoreCase("b)")) {
                                    correctAnswers++;
                                }

                                System.out.println();
                                System.out.println("\033[0;33m" + "2. Five players were killing monsters, A finished before B, but behind C. D finished before E, but behind B. What was the finishing order?" + "\033[0m");
                                System.out.println("\033[0;33m" + "1. A, B, C, D, E" + "\033[0m");
                                System.out.println("\033[0;33m" + "2. A, C, B, D, E" + "\033[0m");
                                System.out.println("\033[0;33m" + "3. C, A, B, D, E" + "\033[0m");
                                System.out.println("\033[0;33m" + "4. C, A, D, B, E" + "\033[0m");

                                flushConsole();
                                System.out.println();
                                System.out.println("\033[0;33m" + "Enter your answer: " + "\033[0m");
                                String answer2 = scanner.nextLine();
                                if (answer2.trim().equalsIgnoreCase("3") || answer2.trim().equalsIgnoreCase("3.")) {
                                    correctAnswers++;
                                }

                                System.out.println();
                                System.out.println("\033[0;33m" + "3. Player A is looking at Player B. Player B is looking at Player C. Player A is marked, Player C is not, and we don’t know if Player B is marked. Is a marked person looking at an unmarked person?" + "\033[0m");
                                System.out.println("\033[0;33m" + "T. True" + "\033[0m");
                                System.out.println("\033[0;33m" + "F. False" + "\033[0m");

                                flushConsole();
                                System.out.println();
                                System.out.println("\033[0;33m" + "Enter your answer: " + "\033[0m");
                                String answer3 = scanner.nextLine();
                                if (answer3.trim().equalsIgnoreCase("T") || answer3.trim().equalsIgnoreCase("T.") || answer3.trim().equalsIgnoreCase("True") || answer3.trim().equalsIgnoreCase("True.")){
                                    correctAnswers++;
                                }

                                System.out.println();
                                System.out.println("\033[0;33m" + "4. A Player has 53 enemies to fight in one Dungeon: 21 assassins, 15 mages and 17 tanks. The lights are out and he is completely in the dark. How many enemies must he take out to make 100 percent certain he has taken down at least one mage?" + "\033[0m");
                                System.out.println("\033[0;33m" + "A. 21" + "\033[0m");
                                System.out.println("\033[0;33m" + "B. 38" + "\033[0m");
                                System.out.println("\033[0;33m" + "C. 53" + "\033[0m");
                                System.out.println("\033[0;33m" + "D. 39" + "\033[0m");

                                flushConsole();
                                System.out.println();
                                System.out.println("\033[0;33m" + "Enter your answer: " + "\033[0m");
                                String answer4 = scanner.nextLine();
                                if (answer4.trim().equalsIgnoreCase("D") || answer4.trim().equalsIgnoreCase("D.")) {
                                    correctAnswers++;
                                }

                                if (correctAnswers == totalQuestions) {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Intelligence Quest 3!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                } else {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Intelligence Quest 3..." + "\033[0m");
                                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                }








                            }
                            if (questIndex == 7)
                            {

                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Watch the sequence of colored      ||");
                                System.out.println("\033[0;33m" + "|| rectangles.                        ||");
                                System.out.println("\033[0;33m" + "|| You need to answer which color     ||");
                                System.out.println("\033[0;33m" + "|| was seen the most.                 ||");
                                System.out.println("\033[0;33m" + "|| Possible colors : 'Green', 'Red',  ||");
                                System.out.println("\033[0;33m" + "|| 'Blue', 'Yellow', 'Purple'         ||");
                                System.out.println("\033[0;33m" + "|| Example Answer : 'Green'           ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                String[] colors = {"\033[0;31m███", "\033[0;32m███", "\033[0;34m███", "\033[0;33m███", "\033[0;35m███"};
                                int[] colorCounts = new int[colors.length];
                                List<String> colorSequence = new ArrayList<>();

                                Random rand = new Random();

                                int cntMax = -1;
                                int maxIndex = -1;

                                for (int i = 0; i < colors.length; i++) {
                                    colorCounts[i] = rand.nextInt(5) + 1;
                                    if (colorCounts[i] > cntMax) {
                                        cntMax = colorCounts[i];
                                        maxIndex = i;
                                    }
                                }

                                for (int i = 0; i < colors.length; i++) {
                                    if (colorCounts[i] == cntMax && i != maxIndex) {
                                        colorCounts[i]--;
                                    }

                                    for (int j = 0; j < colorCounts[i]; j++) {
                                        colorSequence.add(colors[i]);
                                    }
                                }

                                Collections.shuffle(colorSequence);

                                int cntRed = 0;
                                int cntGreen = 0;
                                int cntBlue = 0;
                                int cntYellow = 0;
                                int cntPurple = 0;


                                for (String color : colorSequence) {
                                    System.out.print("\r" + color);
                                    if (color.equals("\033[0;31m███")) {
                                        cntRed++;
                                    }
                                    if (color.equals("\033[0;32m███")) {
                                        cntGreen++;
                                    }
                                    if (color.equals("\033[0;34m███")) {
                                        cntBlue++;
                                    }
                                    if (color.equals("\033[0;33m███")) {
                                        cntYellow++;
                                    }
                                    if (color.equals("\033[0;35m███")) {
                                        cntPurple++;
                                    }
                                    try {
                                        Thread.sleep(1000);
                                        while (System.in.available() > 0) {
                                            System.in.read();
                                        }
                                    } catch (InterruptedException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                flushConsole();

                                System.out.println("\033[0;33m" + "\rEnter your answer: " + "\033[0m");
                                String answer = scanner.nextLine();

                                boolean correctAnswer = false;

                                if (answer.trim().equalsIgnoreCase("Red") && cntRed == cntMax) {
                                    System.out.println();
                                    correctAnswer = true;
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }

                                if (answer.trim().equalsIgnoreCase("Green") && cntGreen == cntMax) {
                                    System.out.println();
                                    correctAnswer = true;
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }

                                if (answer.trim().equalsIgnoreCase("Blue") && cntBlue == cntMax) {
                                    System.out.println();
                                    correctAnswer = true;
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }

                                if (answer.trim().equalsIgnoreCase("Yellow") && cntYellow == cntMax) {
                                    System.out.println();
                                    correctAnswer = true;
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }

                                if (answer.trim().equalsIgnoreCase("Purple") && cntPurple == cntMax) {
                                    System.out.println();
                                    correctAnswer = true;
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 1!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                }

                                if (!correctAnswer) {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Memory Quest 1..." + "\033[0m");
                                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");

                                }


                            }
                            if (questIndex == 8)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Watch the sequence of numbers and  ||");
                                System.out.println("\033[0;33m" + "|| symbols.                           ||");
                                System.out.println("\033[0;33m" + "|| Your answer need to be the result  ||");
                                System.out.println("\033[0;33m" + "|| of all the operations.             ||");
                                System.out.println("\033[0;33m" + "|| Possible symbols : '+', '-', 'x',  ||");
                                System.out.println("\033[0;33m" + "|| ':'. For ':', ignore the rest of   ||");
                                System.out.println("\033[0;33m" + "|| the division. For example,         ||");
                                System.out.println("\033[0;33m" + "|| '9:4' will result in 2.            ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println();

                                Random rand = new Random();
                                int operationsCount = rand.nextInt(11) + 5;
                                String[] symbols = {"+", "-", "x", ":"};
                                List<String> sequence = new ArrayList<>();

                                for (int i = 0; i < operationsCount; i++) {
                                    int number = rand.nextInt(10);
                                    String symbol = symbols[rand.nextInt(4)];

                                    if (symbol.equals(":") && number == 0) {
                                        number = rand.nextInt(9) + 1;
                                    }
                                    sequence.add(symbol);
                                    sequence.add(String.valueOf(number));
                                }


                                sequence.removeFirst();


                                for (String s : sequence) {
                                    System.out.print("\033[0;33m" + "\r" + s + "\033[0m");
                                    pause2();
                                }


                                int result = 0;
                                String previousOperator = "+";
                                for (String s : sequence) {
                                    if (s.matches("\\d+")) {
                                        switch (previousOperator) {
                                            case "+":
                                                result += Integer.parseInt(s);
                                                break;
                                            case "-":
                                                result -= Integer.parseInt(s);
                                                break;
                                            case "x":
                                                result *= Integer.parseInt(s);
                                                break;
                                            case ":":
                                                result /= Integer.parseInt(s);
                                                break;
                                        }
                                    } else {
                                        previousOperator = s;
                                    }
                                }

                                try {
                                    System.out.println("\033[0;33m" + "\rEnter your answer: ");
                                    int userAnswer = scanner.nextInt();
                                    scanner.nextLine();

                                    if (userAnswer == result){
                                        System.out.println();
                                        System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 2!" + "\033[0m");
                                        if (!quest.isCompleted()) {
                                            questService.completeQuest(quest.getId_quest());
                                            userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                            userService.updatePlayerTitle(id);
                                            userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                        }
                                        AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                    } else {
                                        System.out.println();
                                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Memory Quest 2..." + "\033[0m");
                                        AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                                    System.out.println();
                                    scanner.nextLine();
                                    break;
                                }



                            }
                            if (questIndex == 9)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;33m" + "|| "+ "\033[0;35m" + "The System" + "\033[0;33m" + " requires you to do the  ||");
                                System.out.println("\033[0;33m" + "|| following task:                    ||");
                                System.out.println("\033[0;33m" + "||                                    ||");
                                System.out.println("\033[0;33m" + "|| Watch the sequence of words and    ||");
                                System.out.println("\033[0;33m" + "|| reconstruct the phrase.            ||");
                                System.out.println("\033[0;33m" + "|| Your answer need to be the correct ||");
                                System.out.println("\033[0;33m" + "|| phrase.                            ||");
                                System.out.println("\033[0;33m" + "|| Example of answer: 'The strong     ||");
                                System.out.println("\033[0;33m" + "|| eat the weak'                      ||");
                                System.out.println("\033[0;33m" + "|| This quest is case-sensitive.      ||");
                                System.out.println("\033[0;35m" + "========================================" + "\033[0m");

                                displayCountdown();
                                flushConsole();
                                System.out.println("\033[0m");

                                String[] words = {"The", "System", "is", "a", "world", "full", "of", "secrets", "and", "mysteries"};
                                String[] words2 = {"The", "Player", "needs", "to", "get", "stronger", "in", "order", "to", "not", "become", "the", "prey"};
                                String[] words3 = {"The", "Architect", "is", "very", "keen", "in", "finding", "the", "ultimate", "Player", "but", "for", "unknown", "reasons"};

                                Random rand = new Random();
                                int randomIndex = rand.nextInt(3);

                                String[] currentWords1 = new String[0];

                                if(randomIndex == 0)
                                {
                                    currentWords1 = words;
                                }
                                if(randomIndex == 1)
                                {
                                    currentWords1 = words2;
                                }
                                if(randomIndex == 2)
                                {
                                    currentWords1 = words3;
                                }

                                for (String word : currentWords1) {
                                    System.out.print("\033[0;33m" + "\r" + word);
                                    pause2();
                                }

                                flushConsole();

                                System.out.println("\rEnter your answer: " + "\033[0m");
                                String userAnswer = scanner.nextLine();

                                String correctAnswer = "";
                                if(randomIndex == 0)
                                {
                                    correctAnswer = "The System is a world full of secrets and mysteries";
                                }
                                if(randomIndex == 1)
                                {
                                    correctAnswer = "The Player needs to get stronger in order to not become the prey";
                                }
                                if(randomIndex == 2)
                                {
                                    correctAnswer = "The Architect is very keen in finding the ultimate Player but for unknown reasons";
                                }

                                if (userAnswer.trim().equals(correctAnswer)) {
                                    System.out.println();
                                    System.out.println("\033[0;33m" + "Congratulations! You have successfully completed the Memory Quest 3!" + "\033[0m");
                                    if (!quest.isCompleted()) {
                                        questService.completeQuest(quest.getId_quest());
                                        userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                        userService.updatePlayerTitle(id);
                                        userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    }
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");
                                } else {
                                    System.out.println();
                                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed the Memory Quest 3..." + "\033[0m");
                                    AuditSession.getInstance().write("Player " + username + " failed the Quest '" + quest.getName() + "'.");
                                }


                            }
                            if (questIndex == 15)
                            {
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println("\033[0;35m" + "||            The System              ||");
                                System.out.println("\033[0;35m" + "========================================");
                                System.out.println();

                                for (int i = 0; i < 10; i++) {
                                    System.out.print("\033[0;34m" + "\rPlayer " + username + ": " + "\033[0;33m" + "WHO ARE YOU" + "?".repeat(i % 4) + "\033[0m");
                                    pause2();
                                }
                                System.out.println();
                                for (int i = 0; i < 10; i++) {
                                    System.out.print("\033[0;34m" + "\rPlayer " + username + ": " + "\033[0;33m"  + "WHAT IS THIS PLACE" + "?".repeat(i % 4) + "\033[0m");
                                    pause2();
                                }

                                pause3();
                                System.out.println();
                                System.out.println("\033[0;35m" + "The Architect: " + "\033[0;33m" + "I am " + "\033[0;35m" + "The Architect" + "\033[0;33m" +", the creator of " + "\033[0;35m" +"The System" + "\033[0;33m" + "." + "\033[0m");


                                pause3();
                                System.out.println("\033[0;35m" + "The Architect: " + "\033[0;33m" + "And you, " + "\033[0;34m" + username + "\033[0;33m" + ", have been chosen to be my ultimate weapon." + "\033[0m");
                                pause3();


                                for (int i = 0; i < 10; i++) {
                                    System.out.print("\033[0;34m" + "\rPlayer " + username + ": " + "\033[0;33m" + "Weapon" + "?".repeat(i % 4) + "\033[0m");
                                    pause2();
                                }

                                pause3();
                                System.out.println();
                                System.out.println("\033[0;35m" + "The Architect: " +"\033[0;33m" + "Yes, that was the initial plan. But as I watched your journey through " +"\033[0;35m" + "The System" + "\033[0;33m" +", my plans changed." + "\033[0m");

                                pause3();
                                System.out.println("\033[0;35m" + "The Architect: " +"\033[0;33m" + "You've faced dungeons, quests, enemies... and you've grown stronger with each challenge." + "\033[0m");

                                pause3();
                                System.out.println("\033[0;35m" + "The Architect: " + "\033[0;33m" + "I am proud of what you've become. And now, I want you to be my successor." + "\033[0m");

                                for (int i = 0; i < 10; i++) {
                                    System.out.print("\033[0;34m" + "\rPlayer " + username + ": " + "\033[0;33m" + "Your successor" + " ?".repeat(i % 4) + "\033[0m");
                                    pause2();
                                }

                                pause3();
                                System.out.println();
                                System.out.println("\033[0;35m" + "The Architect: " + "\033[0;33m" + "Yes, " + "\033[0;34m" + username + "\033[0;33m" + ". I want you to be " + "\033[0;35m" + "The Next Architect of The System" + "\033[0;33m" + "!" + "\033[0m");
                                System.out.println();
                                for (int i = 0; i < 9; i++) {
                                    System.out.print("\033[0;33m" + "\r" + ".".repeat(i % 4) + "\033[0m");
                                    pause2();
                                }
                                pause3();


                                System.out.println();
                                System.out.println("\033[0;35m" + "The System: "+"\033[0;33m" + "Congratulations! You have successfully completed the Final Secret Quest!" + "\033[0m");
                                pause3();
                                System.out.println("\033[0;35m" + "The System: "+"\033[0;33m" + "You are now " + "\033[0;35m" + "The Next Architect of The System" + "\033[0;33m" + "!" + "\033[0m");
                                pause3();
                                System.out.println();



                                if(!quest.isCompleted())
                                {
                                    rollCredits();
                                    questService.completeQuest(quest.getId_quest());
                                    userService.updatePlayerLevelOnReward(id, quest.getRewardLevel());
                                    userService.updatePlayerTitle(id);
                                    userService.updatePlayerMoneyOnSell(id, quest.getRewardMoney());
                                    userService.promotePlayer(id);
                                    AuditSession.getInstance().write("Player " + username + " completed the Quest '" + quest.getName() + "'.");

                                }

                                System.out.println();
                                System.out.println();
                                System.out.println("\033[0;35m" + "The System: "+"\033[0;33m" + "You have unlocked the " + "\033[0;35m" + "Architect" + "\033[0;33m" + " menu!" + "\033[0m");
                                pause3();
                                System.out.println("\033[0;35m" + "The System: "+"\033[0;35m" + "The System" +"\033[0;33m" + " will now log you off..." + "\033[0m");
                                pause3();

                                exit = true;




                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to enter valid data..." + "\033[0m");
                        scanner.nextLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    System.out.println();
                    break;



                case "12":

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
                        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is assigning the missing attack chance for each player..." + "\033[0m");
                        pause3();

                        int missingChanceP1 = 35;
                        int missingChanceP2 = 35;

                        ArrayList<Quest> questsP1 = questService.getQuestsByPlayerId(player1.getId_user());
                        ArrayList<Quest> questsP2 = questService.getQuestsByPlayerId(player2.getId_user());

                        int completedQuestsP1 = 0;
                        int completedQuestsP2 = 0;

                        int startP1 = Math.max(0, questsP1.size() - 5);
                        int startP2 = Math.max(0, questsP2.size() - 5);

                        for (int i = startP1; i < questsP1.size(); i++) {
                            if (questsP1.get(i).isCompleted()) {
                                completedQuestsP1++;
                            }
                        }

                        for (int i = startP2; i < questsP2.size(); i++) {
                            if (questsP2.get(i).isCompleted()) {
                                completedQuestsP2++;
                            }
                        }

                        while(completedQuestsP1 != 0)
                        {
                            missingChanceP1 -= 5;
                            completedQuestsP1--;
                        }

                        while(completedQuestsP2 != 0)
                        {
                            missingChanceP2 -= 5;
                            completedQuestsP2--;
                        }

                        System.out.println();
                        System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " -> " + "\033[0;33m" + "Missing Attack Chance: " + missingChanceP1 + "%" + "\033[0m");
                        System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " -> " + "\033[0;33m" + "Missing Attack Chance: " + missingChanceP2 + "%" + "\033[0m");
                        pause3();

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

                                int random3 = (int) (Math.random() * 100);
                                if (random3 < missingChanceP2) {
                                    System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " missed the attack!" + "\033[0m");
                                    pause2();
                                }
                                else
                                {
                                    player1.setHealth(player1.getHealth() - player2.getDamage());
                                    System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player1.getUsername() + "\033[0;33m" + " with " + player2.getDamage() + " damage." + "\033[0m");
                                    pause2();
                                }


                                if (player1.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player2.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player1.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP2 = true;
                                    break;
                                }

                                random3 = (int) (Math.random() * 100);
                                if (random3 < missingChanceP1) {
                                    System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " missed the attack!" + "\033[0m");
                                    pause2();
                                }
                                else
                                {
                                    player2.setHealth(player2.getHealth() - player1.getDamage());
                                    System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player2.getUsername() + "\033[0;33m" + " with " + player1.getDamage() + " damage." + "\033[0m");
                                    pause2();
                                }

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

                                int random3 = (int) (Math.random() * 100);
                                if (random3 < missingChanceP1) {
                                    System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " missed the attack!" + "\033[0m");
                                    pause2();
                                }
                                else {
                                    player2.setHealth(player2.getHealth() - player1.getDamage());
                                    System.out.println("\033[0;34m" + player1.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player2.getUsername() + "\033[0;33m" + " with " + player1.getDamage() + " damage." + "\033[0m");
                                    pause2();
                                }


                                if (player2.getHealth() <= 0) {
                                    System.out.println();
                                    System.out.println("\033[0;34m" + "Player " + player1.getUsername() + "\033[0;33m" + " has defeated " + "\033[0;34m" +  "Player " +  player2.getUsername() + "\033[0;33m" + "!" + "\033[0m");
                                    winP1 = true;
                                    break;
                                }

                                random3 = (int) (Math.random() * 100);
                                if (random3 < missingChanceP2) {
                                    System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " missed the attack!" + "\033[0m");
                                    pause2();
                                }
                                else {
                                    player1.setHealth(player1.getHealth() - player2.getDamage());
                                    System.out.println("\033[0;34m" + player2.getUsername() + "\033[0;33m" + " attacked " + "\033[0;34m" + player1.getUsername() + "\033[0;33m" + " with " + player2.getDamage() + " damage." + "\033[0m");
                                    pause2();
                                }


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

                            ArrayList<Quest> quests1 = questService.getQuestsByPlayerId(player1.getId_user());
                            MultiplayerStats multiplayerStats = userService.getMultiplayerStats(player1.getId_user());
                            if (!quests1.get(16).isCompleted() && multiplayerStats.getWins()==1)
                            {
                                questService.completeQuest(quests1.get(16).getId_quest());
                                userService.updatePlayerLevelOnReward(player1.getId_user(), quests1.get(16).getRewardLevel());
                                userService.updatePlayerTitle(player1.getId_user());
                                userService.updatePlayerMoneyOnSell(player1.getId_user(), quests1.get(16).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player1.getUsername() + " completed the Quest '" + quests1.get(16).getName() + "'.");
                            }
                            if (!quests1.get(17).isCompleted() && multiplayerStats.getWins()==5)
                            {
                                questService.completeQuest(quests1.get(17).getId_quest());
                                userService.updatePlayerLevelOnReward(player1.getId_user(), quests1.get(17).getRewardLevel());
                                userService.updatePlayerTitle(player1.getId_user());
                                userService.updatePlayerMoneyOnSell(player1.getId_user(), quests1.get(17).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player1.getUsername() + " completed the Quest '" + quests1.get(17).getName() + "'.");
                            }
                            if (!quests1.get(18).isCompleted() && multiplayerStats.getWins()==10)
                            {
                                questService.completeQuest(quests1.get(18).getId_quest());
                                userService.updatePlayerLevelOnReward(player1.getId_user(), quests1.get(18).getRewardLevel());
                                userService.updatePlayerTitle(player1.getId_user());
                                userService.updatePlayerMoneyOnSell(player1.getId_user(), quests1.get(18).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player1.getUsername() + " completed the Quest '" + quests1.get(18).getName() + "'.");
                            }
                            if (!quests1.get(19).isCompleted() && multiplayerStats.getWins()==20)
                            {
                                questService.completeQuest(quests1.get(19).getId_quest());
                                userService.updatePlayerLevelOnReward(player1.getId_user(), quests1.get(19).getRewardLevel());
                                userService.updatePlayerTitle(player1.getId_user());
                                userService.updatePlayerMoneyOnSell(player1.getId_user(), quests1.get(19).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player1.getUsername() + " completed the Quest '" + quests1.get(19).getName() + "'.");
                            }
                            if (!quests1.get(20).isCompleted() && multiplayerStats.getWins()==50)
                            {
                                questService.completeQuest(quests1.get(20).getId_quest());
                                userService.updatePlayerLevelOnReward(player1.getId_user(), quests1.get(20).getRewardLevel());
                                userService.updatePlayerTitle(player1.getId_user());
                                userService.updatePlayerMoneyOnSell(player1.getId_user(), quests1.get(20).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player1.getUsername() + " completed the Quest '" + quests1.get(20).getName() + "'.");
                            }


                        }

                        if (winP2)
                        {
                            userService.updatePlayerWins(player2.getId_user());
                            userService.updatePlayerLosses(player1.getId_user());
                            AuditSession.getInstance().write("Player " + player2.getUsername() + " defeated Player " + player1.getUsername() + " in PVP.");

                            ArrayList<Quest> quests2 = questService.getQuestsByPlayerId(player2.getId_user());
                            MultiplayerStats multiplayerStats = userService.getMultiplayerStats(player2.getId_user());

                            if (!quests2.get(16).isCompleted() && multiplayerStats.getWins()==1)
                            {
                                questService.completeQuest(quests2.get(16).getId_quest());
                                userService.updatePlayerLevelOnReward(player2.getId_user(), quests2.get(16).getRewardLevel());
                                userService.updatePlayerTitle(player2.getId_user());
                                userService.updatePlayerMoneyOnSell(player2.getId_user(), quests2.get(16).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player2.getUsername() + " completed the Quest '" + quests2.get(16).getName() + "'.");
                            }
                            if (!quests2.get(17).isCompleted() && multiplayerStats.getWins()==5)
                            {
                                questService.completeQuest(quests2.get(17).getId_quest());
                                userService.updatePlayerLevelOnReward(player2.getId_user(), quests2.get(17).getRewardLevel());
                                userService.updatePlayerTitle(player2.getId_user());
                                userService.updatePlayerMoneyOnSell(player2.getId_user(), quests2.get(17).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player2.getUsername() + " completed the Quest '" + quests2.get(17).getName() + "'.");
                            }
                            if (!quests2.get(18).isCompleted() && multiplayerStats.getWins()==10)
                            {
                                questService.completeQuest(quests2.get(18).getId_quest());
                                userService.updatePlayerLevelOnReward(player2.getId_user(), quests2.get(18).getRewardLevel());
                                userService.updatePlayerTitle(player2.getId_user());
                                userService.updatePlayerMoneyOnSell(player2.getId_user(), quests2.get(18).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player2.getUsername() + " completed the Quest '" + quests2.get(18).getName() + "'.");
                            }
                            if (!quests2.get(19).isCompleted() && multiplayerStats.getWins()==20)
                            {
                                questService.completeQuest(quests2.get(19).getId_quest());
                                userService.updatePlayerLevelOnReward(player2.getId_user(), quests2.get(19).getRewardLevel());
                                userService.updatePlayerTitle(player2.getId_user());
                                userService.updatePlayerMoneyOnSell(player2.getId_user(), quests2.get(19).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player2.getUsername() + " completed the Quest '" + quests2.get(19).getName() + "'.");
                            }
                            if (!quests2.get(20).isCompleted() && multiplayerStats.getWins()==50)
                            {
                                questService.completeQuest(quests2.get(20).getId_quest());
                                userService.updatePlayerLevelOnReward(player2.getId_user(), quests2.get(20).getRewardLevel());
                                userService.updatePlayerTitle(player2.getId_user());
                                userService.updatePlayerMoneyOnSell(player2.getId_user(), quests2.get(20).getRewardMoney());
                                AuditSession.getInstance().write("Player " + player2.getUsername() + " completed the Quest '" + quests2.get(20).getName() + "'.");
                            }

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

                case "13":

                    System.out.println();
                    System.out.println("\033[0;35m" + "<<-- The System's Arena -->>" + "\033[0m");
                    ArrayList<Dungeon> dungeons5 = dungeonService.getDungeonByPlayerId(id);


                    for (int i = 0; i < dungeons5.size(); i++) {
                        ArrayList<Integer> enemiesId = dungeonService.getEnemiesByDungeonId(dungeons5.get(i).getId_dungeon());
                        ArrayList<Enemy> enemies = enemyService.getEnemiesByEnemiesId(enemiesId);
                        dungeons5.get(i).setEnemies(enemies);
                    }

                    int completedDungeons = 0;

                   for (Dungeon dungeon : dungeons5) {
                        if (dungeon.isCompleted())
                        {
                            completedDungeons++;
                        }

                    }

                   if(completedDungeons == 10) {


                       HashSet<Integer> uniqueEnemyIds2 = new HashSet<>();
                       for (Dungeon dungeon : dungeons5) {
                           for (Enemy enemy : dungeon.getEnemies()) {
                               if (enemy.isEncountered()) {
                                   uniqueEnemyIds2.add(enemy.getId_enemy());
                               }
                           }
                       }

                       ArrayList<Integer> enemyIdsList = new ArrayList<>(uniqueEnemyIds2);

                       Random rand = new Random();
                       int numberEnemies = rand.nextInt(9) + 4;

                       Collections.shuffle(enemyIdsList);

                       ArrayList<Integer> selectedEnemyIds = new ArrayList<>(enemyIdsList.subList(0, numberEnemies));


                       ArrayList<Enemy> generatedEnemies = enemyService.getEnemiesByEnemiesId(selectedEnemyIds);

                       Dungeon dungeonArena = new Dungeon(-1, -1, "The Arena", "The Arena is a place where the fallen ARISE!", 11, 5, false, generatedEnemies, 250);

                       System.out.println();
                       System.out.println("\033[0;33m" + "1. " + dungeonArena + "(Repeatable)" + "\033[0m");
                       System.out.println();
                       System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the Dungeon for you..." + "\033[0m");
                       pause3();

                       Player player23 = userService.getPlayer(id);
                       boolean exit23 = false;
                       for (Enemy enemy : generatedEnemies) {
                           if (exit23 == true) {
                               break;
                           }

                           System.out.println();
                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is preparing the '" + enemy.getName() + "' for you..." + "\033[0m");
                           pause3();

                           int whoWillStrikeFirst = (int) (Math.random() * 2) + 1;
                           if(whoWillStrikeFirst == 1)
                           {
                               System.out.println();
                               System.out.println("\033[0;33m" + "You will strike first!" + "\033[0m");
                               pause3();
                           }
                           else
                           {
                               System.out.println();
                               System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will strike first!" + "\033[0m");
                               pause3();
                           }

                           if(whoWillStrikeFirst==1)
                           {
                               while (enemy.getHealth() > 0) {
                                   System.out.println();
                                   System.out.println("\033[0;32m" + "Enemy Health: " + enemy.getHealth() + " \033[0;31m" + "Enemy Damage: " + enemy.getDamage() + "\033[0m");
                                   System.out.println("\033[0;32m" + "Your Health: " + player23.getHealth() + " \033[0;31m" + "Your Damage: " + player23.getDamage() + "\033[0m");

                                   if (enemy instanceof Assassin) {

                                       enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");
                                       pause2();

                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }

                                       int damage = enemy.getDamage();
                                       int criticalChance = ((Assassin) enemy).getCriticalChance();
                                       int random = (int) (Math.random() * 100) + 1;

                                       if (random <= criticalChance) {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will use a critical strike!" + "\033[0m");
                                           damage *= 2;
                                       }

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }

                                   }

                                   if (enemy instanceof Mage) {


                                       enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");
                                       pause2();

                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }

                                       int damage = enemy.getDamage();
                                       int mana = ((Mage) enemy).getMana();
                                       int random = (int) (Math.random() * 2);
                                       int heal = 0;

                                       if (mana > 0) {
                                           if (random == 0) {
                                               damage = (int) (damage * 1.5);
                                               ((Mage) enemy).setMana(mana - 20);
                                               System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will have increased damage!" + "\033[0m");

                                           } else {

                                               heal = (int) (enemy.getHealth() * 0.1);
                                               enemy.setHealth(enemy.getHealth() + heal);
                                               ((Mage) enemy).setMana(mana - 20);
                                               System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will heal itself!" + "\033[0m");

                                           }
                                       } else {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no mana left!" + "\033[0m");
                                       }

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }


                                   }

                                   if (enemy instanceof Tank) {
                                       int armor = ((Tank) enemy).getArmor();
                                       if (armor > 0) {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has armor!" + "\033[0m");
                                           ((Tank) enemy).setArmor(armor - player23.getDamage());
                                           armor = ((Tank) enemy).getArmor();
                                           if (armor < 0) {
                                               enemy.setHealth(enemy.getHealth() - Math.abs(armor));
                                               ((Tank) enemy).setArmor(0);

                                           }
                                       } else {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no armor!" + "\033[0m");
                                           enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       }

                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");

                                       pause2();

                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }

                                       int damage = enemy.getDamage();

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }

                                   }


                               }
                           }
                           else
                           {
                               while (enemy.getHealth() > 0) {
                                   System.out.println();
                                   System.out.println("\033[0;32m" + "Enemy Health: " + enemy.getHealth() + " \033[0;31m" + "Enemy Damage: " + enemy.getDamage() + "\033[0m");
                                   System.out.println("\033[0;32m" + "Your Health: " + player23.getHealth() + " \033[0;31m" + "Your Damage: " + player23.getDamage() + "\033[0m");

                                   if (enemy instanceof Assassin) {

                                       int damage = enemy.getDamage();
                                       int criticalChance = ((Assassin) enemy).getCriticalChance();
                                       int random = (int) (Math.random() * 100) + 1;

                                       if (random <= criticalChance) {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will use a critical strike!" + "\033[0m");
                                           damage *= 2;
                                       }

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       pause2();
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }

                                       enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");

                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }



                                   }

                                   if (enemy instanceof Mage) {

                                       int damage = enemy.getDamage();
                                       int mana = ((Mage) enemy).getMana();
                                       int random = (int) (Math.random() * 2);
                                       int heal = 0;

                                       if (mana > 0) {
                                           if (random == 0) {
                                               damage = (int) (damage * 1.5);
                                               ((Mage) enemy).setMana(mana - 20);
                                               System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will have increased damage!" + "\033[0m");

                                           } else {

                                               heal = (int) (enemy.getHealth() * 0.1);
                                               enemy.setHealth(enemy.getHealth() + heal);
                                               ((Mage) enemy).setMana(mana - 20);
                                               System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' will heal itself!" + "\033[0m");

                                           }
                                       } else {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no mana left!" + "\033[0m");
                                       }

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       pause2();
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }

                                       enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");


                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }




                                   }

                                   if (enemy instanceof Tank) {

                                       int damage = enemy.getDamage();

                                       System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' attacked you with " + damage + " damage." + "\033[0m");
                                       pause2();
                                       player23.setHealth(player23.getHealth() - damage);

                                       if (player23.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have been defeated by the '" + enemy.getName() + "'..." + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " was defeated by the '" + enemy.getName() + "' and failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                                           pause3();
                                           exit23 = true;
                                           break;
                                       }

                                       int armor = ((Tank) enemy).getArmor();
                                       if (armor > 0) {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has armor!" + "\033[0m");
                                           ((Tank) enemy).setArmor(armor - player23.getDamage());
                                           armor = ((Tank) enemy).getArmor();
                                           if (armor < 0) {
                                               enemy.setHealth(enemy.getHealth() - Math.abs(armor));
                                               ((Tank) enemy).setArmor(0);

                                           }
                                       } else {
                                           System.out.println("\033[0;33m" + "The '" + enemy.getName() + "' has no armor!" + "\033[0m");
                                           enemy.setHealth(enemy.getHealth() - player23.getDamage());
                                       }

                                       System.out.println("\033[0;33m" + "You attacked the '" + enemy.getName() + "' with " + player23.getDamage() + " damage." + "\033[0m");

                                       if (enemy.getHealth() <= 0) {
                                           System.out.println();
                                           System.out.println("\033[0;33m" + "Congratulations! You have successfully defeated the '" + enemy.getName() + "'!" + "\033[0m");
                                           AuditSession.getInstance().write("Player " + username + " defeated the '" + enemy.getName() + "'.");
                                           break;
                                       }



                                   }


                               }
                           }


                       }

                       if (exit23 == true) {
                           System.out.println();
                           System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is sorry to inform you that you have failed to clear Dungeon '" + dungeonArena.getName() + "'..." + "\033[0m");
                           AuditSession.getInstance().write("Player " + username + " failed to clear Dungeon '" + dungeonArena.getName() + "'.");
                           pause3();
                       } else {
                           System.out.println();
                           System.out.println("\033[0;33m" + "Congratulations! You have successfully cleared Dungeon '" + dungeonArena.getName() + "'!" + "\033[0m");
                           userService.updatePlayerMoneyOnSell(id, dungeonArena.getRewardMoney());
                           userService.updatePlayerLevelOnReward(id, dungeonArena.getRewardLevel());
                           userService.updatePlayerTitle(id);
                           AuditSession.getInstance().write("Player " + username + " cleared Dungeon '" + dungeonArena.getName() + "'.");
                       }
                   }
                   else {
                       System.out.println();
                       System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to complete all the Dungeons first..." + "\033[0m");
                       pause3();
                   }



                    System.out.println();
                    break;





                case "14":
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

                case "15":
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
                    System.out.println((dungeonsCleared == 10 ? "\033[0;32m" : "\033[0;33m") + "I. Dungeon Legend - Dungeons Cleared: " + dungeonsCleared + "/10 " + (dungeonsCleared == 10 ? "(Completed)" : "(In Progress)") + "\033[0m");

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

                    int winsMultiplayer = userService.getMultiplayerStats(id).getWins();
                    if (winsMultiplayer >= 50) achievementsCompleted++;
                    System.out.println((winsMultiplayer >= 50 ? "\033[0;32m" : "\033[0;33m") + "VII. PVP Conqueror - Multiplayer Wins: " + Math.min(winsMultiplayer, 50) + "/50 " + (winsMultiplayer >= 50 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    int questsCompleted = questService.getQuestsByPlayerId(id).stream().filter(Quest::isCompleted).collect(Collectors.toList()).size();
                    if (questsCompleted == 20) achievementsCompleted++;
                    System.out.println((questsCompleted == 20 ? "\033[0;32m" : "\033[0;33m") + "VIII. Adventure Champion - Quests Completed: " + questsCompleted + "/20 " + (questsCompleted == 20 ? "(Completed)" : "(In Progress)") + "\033[0m");

                    System.out.println();
                    System.out.println((achievementsCompleted == 8 ? "\033[0;32m" : "\033[0;33m") + "IX. God of War - All Achievements: " + achievementsCompleted + "/8 " + (achievementsCompleted == 8 ? "(Completed)" : "(In Progress)") + "\033[0m");


                    System.out.println();
                    break;

                case "16":
                    System.out.println();

                    System.out.println("\033[0;34m" + "<<-- The Player's PVP Stats -->>" + "\033[0m");
                    MultiplayerStats multiplayerStats = userService.getMultiplayerStats(id);
                    System.out.println();

                    System.out.println("\033[0;34m" + multiplayerStats + "\033[0m");

                    break;

                case "17":
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


                case "18":
                    System.out.println();
                    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "is exiting the profile of " + "\033[0;34m" +  "Player " + "\033[0;34m" + username + "\033[0;33m" + "..." + "\033[0m");
                    System.out.println();
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
            flushConsole();
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


    }

    // Display Player or Architect Menu
    public void displayPlayerOrArchitectMenu() {
        System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "requires you to choose..." + "\033[0m");
        System.out.println("\033[0;33m" + "1. " + "\033[0;34m" + "Player" + "\033[0m");
        System.out.println("\033[0;33m" + "2. " + "\033[0;35m" + "Architect" + "\033[0m");
        System.out.println("\033[0;33m" + "Enter command: " + "\033[0m");
    }

    public void playerOrArchitectMenu(String username, Integer id)
    {
        boolean exit = false;
        while (!exit) {
            displayPlayerOrArchitectMenu();
            flushConsole();
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

    public ArrayList<Integer> displayQuests(ArrayList<Quest> quests)
    {
        ArrayList<Integer> questIndexes = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < quests.size()-5; i++) {
            if (quests.get(i).isCompleted()) {
                count++;
            }
        }
        System.out.println("\033[0;35m" + "<<-- Quests -->>" + "\033[0m");
        for (int i = 0; i < quests.size(); i++) {
            System.out.println();
            int questNumber = i + 1;
            if (i==0)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Daily Quest (Active Quest) ->" + "\033[0m");
                System.out.println();
            }

            if (i==1)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Agility Quests (Active Quests) ->" + "\033[0m");
                System.out.println();
            }

            if(i==4)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Intelligence Quests (Active Quests) ->" + "\033[0m");
                System.out.println();
            }

            if(i==7)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Memory Quests (Active Quests) ->" + "\033[0m");
                System.out.println();
            }

            if (i==10)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Dungeon Quests (Passive Quests) ->" + "\033[0m");
                System.out.println();
            }
            if (i==15)
            { if (count==14)
            {System.out.println();
                System.out.println("\033[0;35m" + "<- Secret Quest (Active Quest) ->" + "\033[0m");
                System.out.println();
                if (quests.get(i).isCompleted()) {
                    System.out.println("\033[0;32m" + questNumber + ". " + quests.get(i) + "\033[0;32m" + "(Completed)" + "\033[0m");
                } else {
                    System.out.println("\033[0;33m" + questNumber + ". " + quests.get(i) + "\033[0;33m" + "(Available)" + "\033[0m");
                }
                questIndexes.add(i);
            }
            if(count==15)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- Secret Quest (Active Quest) ->" + "\033[0m");
                System.out.println();
                if (quests.get(i).isCompleted()) {
                    System.out.println("\033[0;32m" + questNumber + ". " + quests.get(i) + "\033[0;32m" + "(Finished)" + "\033[0m");
                } else {
                    System.out.println("\033[0;33m" + questNumber + ". " + quests.get(i) + "\033[0;33m" + "(Available)" + "\033[0m");
                }

            }
            }

            if (i==16)
            {
                System.out.println();
                System.out.println("\033[0;35m" + "<- PVP Quests (Passive Quests) ->" + "\033[0m");
                System.out.println();
            }

                if (i!=15) {
                    if (i>=0 && i<=9)
                    {
                        questIndexes.add(i);
                    }

                    if (quests.get(i).isCompleted()) {
                        System.out.println("\033[0;32m" + questNumber + ". " + quests.get(i) + "\033[0;32m" + "(Completed)" + "\033[0m");
                    } else {
                        if (i == 0) {
                            System.out.println("\033[0;33m" + questNumber + ". " + quests.get(i) + "\033[0;33m" + "(Repeatable)" + "\033[0m");

                        } else if (i >= 1 && i <= 9) {
                            System.out.println("\033[0;33m" + questNumber + ". " + quests.get(i) + "\033[0;33m" + "(Available)" + "\033[0m");
                        } else {
                            System.out.println("\033[0;33m" + questNumber + ". " + quests.get(i) + "\033[0;33m" + "(In Progress)" + "\033[0m");

                        }
                    }
                }

        }

        return questIndexes;

    }

    public void displayCountdown()
    {
        System.out.println();
        String[] countdownNumbers = {"3", "2", "1", "Start!"};
        String redCircle = "\u001B[31m\u2B24"; // Unicode for red circle
        String greenCircle = "\u001B[32m\u2B24"; // Unicode for green circle

        for (String number : countdownNumbers) {
            if (!number.equals("Start!")) {
                System.out.println(redCircle + " " + number);
            } else {
                System.out.println(greenCircle + " " + number);
            }
            pause2();
        }
    }



public void rollCredits()
{
    System.out.println();
    System.out.println("\033[0;35m" + "<<-- The System's Credits ->> ");
    System.out.println();
    pause3();
    System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "thanks you for playing..." + "\033[0m");
    System.out.println();
    pause3();
    System.out.println("Creative Game Director: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Game Director: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Programmer: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Combat Designer: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Level Designer: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Artist: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Writer: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Lead Tester: Codarcea Alexandru-Christian");
    System.out.println();
    pause3();
    System.out.println("Special Thanks: Solo Leveling");
    pause3();
}







}
