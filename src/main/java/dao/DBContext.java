package dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DBContext<T> {
    protected Connection connection;

    DBContext() {
        try {
            Dotenv dotenv = Dotenv.configure().load();
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T get(T entity);
    public abstract ArrayList<T> list();

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(T entity);
}
