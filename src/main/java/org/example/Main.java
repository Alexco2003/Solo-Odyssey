package org.example;

import org.example.view.ConsoleApp;

public class Main {

    private static ConsoleApp app;
    public static void main(String[] args) {
        try {
            app = ConsoleApp.getInstance();
            app.start();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage() + "!" + "\n" + "Please try again!");
        }

    }
}