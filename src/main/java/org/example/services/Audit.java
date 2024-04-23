package org.example.services;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {
    private static Audit instance = null;
    private static CSVWriter writer;
    private static String path = "audit.csv";
    private static int count = 0;

    private Audit() {
        try {

            writer = new CSVWriter(new FileWriter(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Audit getInstance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public <T> void write(String sqlStatement, T entity, String result) {
        try {
            String[] entries = {"Statement " + count++, sqlStatement, String.valueOf(entity), result};
            writer.writeNext(entries);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
