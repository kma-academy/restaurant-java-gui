package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @createAt Nov 11, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Database {

    private static Database instance = null;

    private static final String JDBC_STRING = "jdbc:mysql://localhost:3316";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "army";
    private static final String DB_NAME = "restaurant";
    private static final String URL = JDBC_STRING + "/" + DB_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection conn = null;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
            System.out.println("Connect database success!");
        } catch (Exception e) {
            System.out.println("Connect database error:");
            System.out.println(e.toString());
            System.exit(0);
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

    public static void main(String[] args) {
        try {
            Connection connection = getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT CURRENT_TIMESTAMP";
            ResultSet rs = stmt.executeQuery(query);
            // show data
            while (rs.next()) {
                System.out.println(rs.getDate("CURRENT_TIMESTAMP").toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
