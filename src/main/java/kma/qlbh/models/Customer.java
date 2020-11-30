package kma.qlbh.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @createAt Nov 24, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Customer {

    protected int id;
    protected String phoneNumber, name, address;
    protected Date birthday;

    public Customer() {
        birthday = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", phoneNumber=" + phoneNumber + ", name=" + name + ", address=" + address + ", birthday=" + birthday + '}';
    }

    public static Customer getFromResultSet(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setName(rs.getNString("name"));
        c.setAddress(rs.getNString("address"));
        c.setPhoneNumber(rs.getString("phoneNumber"));
        c.setBirthday(rs.getDate("birthday"));
        return c;
    }

}
