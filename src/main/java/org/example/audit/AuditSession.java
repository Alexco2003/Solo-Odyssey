package org.example.audit;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class AuditSession {
    private static AuditSession instance = null;
    private static CSVWriter writer;
    private static String path = "audit/AuditSession.csv";
    private static int count = 1;

    private AuditSession() {
        try {

            writer = new CSVWriter(new FileWriter(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static AuditSession getInstance() {
        if (instance == null) {
            instance = new AuditSession();
        }
        return instance;
    }

    public void write(String Action) {
        try {
            String[] entries = {"Action " + count++, Action};
            writer.writeNext(entries);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
