package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @createAt Nov 11, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Database {

    private static Database instance = null;

    private static final String JDBC_STRING = "jdbc:sqlserver://localhost:1433";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "kmasecretkey";
    private static final String DB_NAME = "restaurant";

    private Connection conn = null;

    private Database() {
        try {
            this.conn = DriverManager.getConnection(JDBC_STRING + ";databaseName=" + DB_NAME, DB_USER, DB_PASS);
            System.out.println("Connect database success!");
        } catch (Exception e) {
            System.out.println("Connect database error:");
            System.out.println(e.toString());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

}
