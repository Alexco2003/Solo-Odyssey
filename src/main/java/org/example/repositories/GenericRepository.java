package org.example.repositories;

import org.example.services.AuditDatabase;
import org.example.services.AuditSession;
import org.example.services.DatabaseConnection;

import java.util.ArrayList;
public interface GenericRepository<T> {

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    AuditDatabase auditDatabase = AuditDatabase.getInstance();
    AuditSession auditSession = AuditSession.getInstance();

    public void add(T entity);

    public T get(int id);

    public ArrayList<T> getAll();

    public void update(T entity);

    public void delete(T entity);

}
