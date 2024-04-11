package org.example.repositories;

import org.example.models.Player;
import org.example.services.Audit;
import org.example.services.DatabaseConnection;

import java.util.ArrayList;
public interface GenericRepository<T> {

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Audit audit = Audit.getInstance();

    public void add(T entity);

    public T get(int id);

    public ArrayList<T> getAll();

    public void update(T entity);

    public void delete(T entity);

}
