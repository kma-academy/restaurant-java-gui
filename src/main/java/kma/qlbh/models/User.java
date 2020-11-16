package kma.qlbh.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import kma.qlbh.utils.LevelPermission;

/**
 * @createAt Nov 11, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class User {

    protected int id;
    protected String userName, password, phoneNumber, name;
    protected LevelPermission lvPermission;

    public User() {

    }

    public User(String userName, String password, String phoneNumber, String name) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lvPermission = LevelPermission.MEMBER_PERMISSION;
    }

    public User(int id, String userName, String password, String phoneNumber, String name) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lvPermission = LevelPermission.MEMBER_PERMISSION;
    }

    public User(int id, String userName, String password, String phoneNumber, String name, LevelPermission lvPermission) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lvPermission = lvPermission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LevelPermission getLvPermission() {
        return lvPermission;
    }

    public void setLvPermission(LevelPermission lvPermission) {
        this.lvPermission = lvPermission;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", name=" + name + ", lvPermission=" + lvPermission + '}';
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static User resultToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getNString("username"));
        user.setPassword(rs.getNString("password"));
        user.setName(rs.getNString("name"));
        user.setPhoneNumber(rs.getNString("phoneNumber"));
        user.setLvPermission(LevelPermission.getById(rs.getInt("lvPermission")));
        return user;
    }

}
