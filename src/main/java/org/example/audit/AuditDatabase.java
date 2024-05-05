package org.example.audit;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class AuditDatabase {
    private static AuditDatabase instance = null;
    private static CSVWriter writer;
    private static String path = "audit/auditDatabase.csv";
    private static int count = 1;

    private AuditDatabase() {
        try {

            writer = new CSVWriter(new FileWriter(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static AuditDatabase getInstance() {
        if (instance == null) {
            instance = new AuditDatabase();
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
