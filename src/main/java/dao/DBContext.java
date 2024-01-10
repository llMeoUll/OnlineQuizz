package dao;

import java.sql.Connection;

public abstract class DBContext<T> {
    protected Connection connection;

    DBContext() {
        try {
            String url = "jdbc:mysql://localhost:9999/online_quizz";
            String user = "root";
            String password = "longdongtao2002";
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T get(T entity);
}
