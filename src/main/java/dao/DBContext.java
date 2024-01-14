package dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DBContext<T> {
    protected Connection connection;

    DBContext() {
        try {
            Dotenv dotenv = Dotenv.configure().load();

            String url = dotenv.get("db_url");
            String user = dotenv.get("db_user");
            String password = dotenv.get("db_password");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T get(T entity);
    public abstract ArrayList<T> list();

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(T entity);
}
