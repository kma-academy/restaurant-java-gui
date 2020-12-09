package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kma.qlbh.models.FoodItem;

/**
 * @createAt Dec 8, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class FoodItemDao implements Dao<FoodItem> {

    Connection conn = Database.getInstance().getConnection();

    @Override
    public ArrayList<FoodItem> getAll() throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` ORDER BY `food_item`.`idCategory` ASC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            foodItems.add(foodItem);
        }
        return foodItems;
    }

    @Override
    public FoodItem get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `id` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            return foodItem;
        }
        return null;
    }

    @Override
    public void save(FoodItem t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(FoodItem t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(FoodItem t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
