package org.example;

import org.example.view.ConsoleApp;

public class Main {

    private static ConsoleApp app;
    public static void main(String[] args) {

        try {
            app = ConsoleApp.getInstance();
            app.start();
        } catch (Exception e) {
            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "encountered an error: " + e.getMessage() + "!" + "\n" + "\033[0;35m" + "The System " + "\033[0;33m" + "wants you to try again!" + "\033[0m");
        }

    }

}

