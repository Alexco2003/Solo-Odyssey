package org.example.view;

import org.example.models.Item;
import org.example.models.Player;
import org.example.repositories.PlayerRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ConsoleApp {
    private static ConsoleApp instance = null;
    private Scanner scanner = new Scanner(System.in);

    private ConsoleApp() {
    }

    public static ConsoleApp getInstance() {
        if (instance == null) {
            instance = new ConsoleApp();
        }
        return instance;
    }

    public void start() {

        boolean exit = false;
        displayTitleStart();
        displayTitleMotto();
        displayTitleLogin();



        while (!exit) {

            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println();
                    System.out.println("\033[0;33m"+ "Enter username: " + "\033[0m");
                    String username = scanner.nextLine();
                    System.out.println("\033[0;33m"+ "Enter password: " + "\033[0m");
                    String password = scanner.nextLine();

                    break;

                case "2":


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
                    break;
            }
        }

        scanner.close();
    }

    public void clearScreen() {
        for(int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

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










}
