package org.example;

import org.example.view.ConsoleApp;

public class Main {

    private static ConsoleApp app;
    public static void main(String[] args) {

        try {
            app = ConsoleApp.getInstance();
            app.start();
        } catch (Exception e) {
            System.out.println();
            System.out.println("\033[0;35m" + "The System " + "\033[0;33m" + "encountered an error!" + "\n" + "\033[0;35m" + "The System " + "\033[0;33m" + "needs to be restarted..." + "\033[0m");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }

}

