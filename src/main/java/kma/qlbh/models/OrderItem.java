/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kma.qlbh.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class OrderItem {

    private int idOrder, idFoodItem, idTopping, quantity, unitPrice;
    private String note;

    public OrderItem() {
        quantity = 1;
        idTopping = 0;
        note = "";
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdFoodItem() {
        return idFoodItem;
    }

    public void setIdFoodItem(int idFoodItem) {
        this.idFoodItem = idFoodItem;
    }

    public int getIdTopping() {
        return idTopping;
    }

    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static OrderItem getFromResultSet(ResultSet rs) throws SQLException {
        OrderItem oi = new OrderItem();
        oi.setIdFoodItem(rs.getInt("idFoodItem"));
        oi.setIdOrder(rs.getInt("idOrder"));
        oi.setIdTopping(rs.getInt("idTopping"));
        oi.setQuantity(rs.getInt("quantity"));
        oi.setUnitPrice(rs.getInt("unitPrice"));
        oi.setNote(rs.getNString("note"));
        return oi;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "idOrder=" + idOrder + ", idFoodItem=" + idFoodItem + ", idTopping=" + idTopping + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", note=" + note + '}';
    }

}
