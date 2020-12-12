package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kma.qlbh.models.OrderItem;

/**
 *
 * @author MSI
 */
public class OrderItemDao implements Dao<OrderItem> {

    Connection conn = Database.getInstance().getConnection();

    @Override
    public ArrayList<OrderItem> getAll() throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order_item` ORDER BY `order_item`.`idOrder` DESC, `order_item`.`quantity` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            OrderItem orderItem = OrderItem.getFromResultSet(rs);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public OrderItem get(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "INSERT INTO `order_item` (`idOrder`, `idFoodItem`, `idTopping`, `quantity`, `unitPrice`, `note`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getIdOrder());
        stmt.setInt(2, t.getIdFoodItem());
        stmt.setInt(3, t.getIdTopping());
        stmt.setInt(4, t.getQuantity());
        stmt.setInt(5, t.getUnitPrice());
        stmt.setNString(6, t.getNote());
    }

    @Override
    public void update(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "UPDATE `order_item` SET , `quantity` = ?, `unitPrice` = ?, `note` = ? WHERE `idOrder` = ? AND `idFoodItem` = ? AND `idTopping` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getQuantity());
        stmt.setInt(2, t.getUnitPrice());
        stmt.setNString(3, t.getNote());
        stmt.setInt(4, t.getIdOrder());
        stmt.setInt(5, t.getIdFoodItem());
        stmt.setInt(6, t.getIdTopping());
    }

    @Override
    public void delete(OrderItem t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `idOrder` = ? AND `idFoodItem` = ? AND `idTopping` = ?");
        stmt.setInt(4, t.getIdOrder());
        stmt.setInt(5, t.getIdFoodItem());
        stmt.setInt(6, t.getIdTopping());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addItem(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "CALL `addOrderItem`(?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getIdOrder());
        stmt.setInt(2, t.getIdFoodItem());
        stmt.setInt(3, t.getIdTopping());
        stmt.setInt(4, t.getQuantity());
        stmt.setNString(5, t.getNote());
    }

    public ArrayList<OrderItem> getByIdOrder(int idOrder) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order_item`WHERE `idOrder` = " + idOrder + "  ORDER BY `order_item`.`quantity` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            OrderItem orderItem = OrderItem.getFromResultSet(rs);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

}
