///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package kma.qlbh.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;e
//import kma.qlbh.models.Food;
//
///**
// *
// * @author MSI
// */
//public class FoodDao implements Dao<Food>{
//    Connection conn = Database.getInstance().getConnection();
//
//    @Override
//    public ArrayList<Food> getAll() throws SQLException {
//        ArrayList<Food> foods = new ArrayList<>();
//        Statement statement = conn.createStatement();
//        String query = "SELECT * FROM Food_item;";
//        ResultSet rs = statement.executeQuery(query);
//        while (rs.next()) {
//            Food food = Food.getFromResultSet(rs);
//            foods.add(food);
//        }
//        return foods;
//
//    }
//
//    @Override
//    public Food get(int id) throws SQLException {
//        Statement statement = conn.createStatement();
//        String query = "SELECT * FROM Food_item WHERE Food_item.id = " + id;
//        ResultSet rs = statement.executeQuery(query);
//        if (rs.next()) {
//            Food food = Food.getFromResultSet(rs);
//            return food;
//        }
//        return null;
//    }
////  `food_item` (`id`, `name`, `description`, `urlImage`, `unitName`, `unitPrice`, `idCategory`)
//    @Override
//    public void save(Food food) throws SQLException {
//        Statement statement = conn.createStatement();
//        String query = "INSERT INTO Food_item VALUES(?,?,?,?,?,?,?)";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setInt(1, food.getId());
//        stmt.setString(2, food.getName());
//        stmt.setString(3, food.getDescription());
//        stmt.setString(4, food.getUrlImage());
//        stmt.setString(5, food.getUnitName().getId());
//        stmt.setInt(6, food.getPrice());
//        stmt.setString(7, food.getIdCategory().getId());
//        int row =stmt.executeUpdate();
//    }
//
//    @Override
//    public void update(Food food) throws SQLException {
//        Statement statement = conn.createStatement();
//        String query = "UPDATE Food_item SET"
//                + "`name` = ?,`description`= ?,`urlImage`=?,`unitName`=?`unitPrice`=?,`idCategory`=?"
//                + "WHERE `id`= ?";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setString(1, food.getName());
//        stmt.setString(2, food.getDescription());
//        stmt.setString(3, food.getUrlImage());
//        stmt.setString(4, food.getUnitName().getId());
//        stmt.setInt(5, food.getPrice());
//        stmt.setString(6, food.getIdCategory().getId());
//        int row = stmt.executeUpdate();
//    }
//
//    @Override
//    public void delete(Food t) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `Food_item` WHERE `Food_item`.`id` = ?");
//        stmt.setInt(1, t.getId());
//        stmt.executeUpdate();
//    }
//
//    @Override
//    public void deleteById(int id) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `Food_item` WHERE `Food_item`.`id` = ?");
//        stmt.setInt(1, id);
//        stmt.executeUpdate();
//    }
//    
//}
