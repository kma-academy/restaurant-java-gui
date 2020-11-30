package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kma.qlbh.models.Customer;

/**
 * @createAt Nov 25, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class CustomerDao implements Dao<Customer> {

    Connection conn = Database.getInstance().getConnection();

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `customer`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Customer customer = Customer.getFromResultSet(rs);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `customer`";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Customer customer = Customer.getFromResultSet(rs);
            return customer;
        }
        return null;
    }

    @Override
    public void save(Customer t) throws SQLException {
        if (t == null) {
            throw new SQLException("Customer rỗng");
        }
        String query = "INSERT INTO `customer` (`phoneNumber`, `name`, `address`, `birthday`) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getPhoneNumber());
        stmt.setNString(2, t.getName());
        stmt.setNString(3, t.getAddress());
        stmt.setDate(4, t.getBirthday());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}
