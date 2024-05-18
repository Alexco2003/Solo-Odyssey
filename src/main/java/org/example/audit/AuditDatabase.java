package org.example.audit;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.io.File;

public class AuditDatabase {
    private static AuditDatabase instance = null;
    private static CSVWriter writer;
    private static String path = "audit/auditDatabase.csv";
    private static int count = 1;

    private AuditDatabase() {
        try {

            writer = new CSVWriter(new FileWriter(path,true));

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
            File file = new File(path);
            if (file.length() != 0 && count == 1) {
                writer.writeNext(new String[]{});
                writer.writeNext(new String[]{});
                writer.flush();

            }

            if (count == 1) {
                writer.writeNext(new String[]{"No.", "SQL Statement", "Entity", "Time"});
                writer.writeNext(new String[]{});
                writer.flush();
            }

                String[] entries = {"Statement " + count++, sqlStatement, String.valueOf(entity), LocalDateTime.now().toString()};
                writer.writeNext(entries);
                writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
