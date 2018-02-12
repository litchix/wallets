package io.pax.cryptos.dao;

import com.mysql.jdbc.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.pax.cryptos.domain.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class UserDao {

    JdbcConnector connector = new JdbcConnector();


    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");


        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            users.add(new SimpleUser(id, name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public User findUserWithWallets(int userId) throws SQLException {

        Connection connection = connector.getConnection();
        String query = "SELECT * FROM wallet w JOIN user u ON w.user_id=u.id WHERE u.id LIKE ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet set = statement.executeQuery();

        User user = null;
        List<Wallet> wallets = new ArrayList<>();

        while (set.next()) {
            String userName = set.getString("u.name");
            System.out.println("userName : " + userName);
            user = new FullUser(userId, userName, wallets);

            int walletId = set.getInt("w.id");
            String walletName = set.getString("w.name");

            if (walletId > 0) {
                Wallet wallet = new SimpleWallet(walletId, walletName);
                wallets.add(wallet);
            }

        }
        set.close();
        statement.close();
        connection.close();

        return user;
    }


    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM user WHERE  id= ?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);

        new WalletDao().deleteAll(userId);

        statement.executeUpdate();

        statement.close();
        conn.close();

    }

    public List<User> findByName(String extract) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE name LIKE '" + extract + "%'");

        while (rs.next()) {
            extract = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new SimpleUser(id, extract));

        }
        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public void deleteByName(String exactName) throws SQLException {
        String query = "DELETE FROM user WHERE  name= ?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, exactName);

        //findByName(exactName).;


        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    public void updateUser(int userId, String newName) throws SQLException {
        String query = "UPDATE user SET name = ? WHERE id= ?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, newName);
        statement.setInt(2, userId);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
        System.out.println(dao.findUserWithWallets(1));
        //dao.listUsers();
        //dao.createUser("Alix");
        //dao.deleteUser(5);
        //System.out.println(dao.findByName("A"));
        //dao.deleteByName("Alix");
        //dao.updateUser(14,"New Alix");
    }


}