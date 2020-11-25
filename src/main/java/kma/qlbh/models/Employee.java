package kma.qlbh.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @createAt Nov 24, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Employee {
    
    protected int id;
    protected String username, password, name, phoneNumber, permissionName;
    protected int permissionId;
    protected Date startDate;
    
    public Employee() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getPermissionId() {
        return permissionId;
    }
    
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
    
    public String getPermissionName() {
        return permissionName;
    }
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", phoneNumber=" + phoneNumber + ", permissionName=" + permissionName + '}';
    }
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    
    public static Employee getFromResultSet(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setUsername(rs.getNString("username"));
        e.setPassword(rs.getNString("password"));
        e.setName(rs.getNString("name"));
        e.setPhoneNumber(rs.getNString("phoneNumber"));
        e.setPermissionName(rs.getNString("permissionName"));
        e.setPermissionId(rs.getInt("permissionId"));
        e.setStartDate(rs.getDate("startDate"));
        return e;
    }
    
}
