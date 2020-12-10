/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kma.qlbh.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import kma.qlbh.utils.OrderStatus;
import kma.qlbh.utils.OrderType;

/**
 *
 * @author MSI
 */
public class Order {

    private int id, idEmployee, idTable;
    private OrderType type;
    private OrderStatus status;
    private Timestamp orderDate, payDate;
    private int paidAmount, totalAmount;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static Order getFromResultSet(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setIdEmployee(rs.getInt("idEmployee"));
        o.setIdTable(rs.getInt("idTable"));
        o.setType(OrderType.getById(rs.getNString("type")));
        o.setStatus(OrderStatus.getById(rs.getNString("status")));
        o.setOrderDate(rs.getTimestamp("orderDate"));
        o.setPayDate(rs.getTimestamp("payDate"));
        o.setPaidAmount(rs.getInt("paidAmount"));
        o.setTotalAmount(rs.getInt("totalAmount"));
        return o;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", idEmployee=" + idEmployee + ", idTable=" + idTable + ", type=" + type + ", status=" + status + ", orderDate=" + orderDate + ", payDate=" + payDate + ", paidAmount=" + paidAmount + ", totalAmount=" + totalAmount + '}';
    }

}