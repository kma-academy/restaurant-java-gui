package kma.qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kma.qlbh.models.User;

/**
 * @createAt Nov 11, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class UserDao implements Dao<User> {

    Connection conn = Database.getInstance().getConnection();

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM [user]");
        while (rs.next()) {
            User user = User.resultToUser(rs);
            users.add(user);
        }
        return users;
    }

    @Override
    public User get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM [user] WHERE id = " + id);
        if (rs.next()) {
            User user = User.resultToUser(rs);
            return user;
        }
        return null;
    }

    @Override
    public void save(User t) throws SQLException {

        if (t == null) {
            throw new SQLException("User rỗng");
        }
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO [user](username, password, phoneNumber, name, lvPermission) VALUES(?,?,?,?,?)");
//            stmt.setLong(0, t.getId());
        stmt.setNString(1, t.getUserName());
        stmt.setNString(2, t.getPassword());
        stmt.setNString(3, t.getPhoneNumber());
        stmt.setNString(4, t.getName());
        stmt.setInt(5, t.getLvPermission().getId());
        int row = stmt.executeUpdate();
        System.out.println(row);
    }

    public User findByUserName(String username) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM [user] WHERE username = N'" + username + "'");
        while (rs.next()) {
            User user = User.resultToUser(rs);
            return user;
        }
        return null;

    }

    @Override
    public void update(User t) throws SQLException {
        if (t == null) {
            throw new SQLException("User rỗng");
        }
        if (t.getId() == 0) {
            throw new SQLException("User chưa tồn tại");
        }
        PreparedStatement stmt = conn.prepareStatement("UPDATE [user] SET [username] = ?,[password] = ?,[phoneNumber] = ?,[name] = ?,[lvPermission] = ? WHERE [id] =?");
//            stmt.setLong(0, t.getId());
        stmt.setNString(1, t.getUserName());
        stmt.setNString(2, t.getPassword());
        stmt.setNString(3, t.getPhoneNumber());
        stmt.setNString(4, t.getName());
        stmt.setInt(5, t.getLvPermission().getId());
        stmt.setInt(6, t.getId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(User t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM [user] WHERE [id] =?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
    }

    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM [user] WHERE [id] =?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}
