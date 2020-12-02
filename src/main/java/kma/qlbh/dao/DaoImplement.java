package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @createAt Dec 1, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class DaoImplement<T> {

    public QueryAdapter queryAdapter;

    Connection conn = Database.getInstance().getConnection();

    public DaoImplement(QueryAdapter queryAdapter) {
        this.queryAdapter = queryAdapter;
    }

    public ArrayList<T> getAll() throws SQLException {
        ArrayList<T> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = queryAdapter.getAllQuery();
        ResultSet rs = statement.executeQuery(query);
        return customers;
    }
}
