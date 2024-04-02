package org.example.view;

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
        System.out.println("Hello World2!");
    }
}
