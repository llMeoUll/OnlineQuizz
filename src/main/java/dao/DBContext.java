package dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBContext<T> {
    protected Connection connection;

    DBContext() {
        try {
            Dotenv dotenv = Dotenv.configure().load();

            String url = dotenv.get("db_url");
            String user = dotenv.get("db_user");
            String password = dotenv.get("db_password");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user , password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T get(T entity);
}
