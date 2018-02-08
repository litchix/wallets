package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class UserDao {

    public DataSource connect() {

        DataSource dataSource;

        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/cryptos");

        } catch (NamingException e) {

            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setDatabaseName("cryptos");
            mysqlDataSource.setPort(3306);
            dataSource = mysqlDataSource;
        }

        return dataSource;
    }

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = connect().getConnection();
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
    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";
        System.out.println(query);

        Connection conn = connect().getConnection();
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

    public void deleteUser(int userId) throws SQLException{
        String query = "DELETE FROM user WHERE  id= ?";
        //SELECT * FROM user u JOIN wallet w ON u.id=w.user_id WHERE u.name LIKE ?
        System.out.println(query);

        Connection conn = connect().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);

        new WalletDao().deleteAll(userId);

        statement.executeUpdate();

        statement.close();
        conn.close();

    }

    public List<User> findByName(String extract) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = connect().getConnection();
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

        Connection conn = connect().getConnection();
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

        Connection conn = connect().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, newName);
        statement.setInt(2, userId);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }




    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
        //dao.listUsers();
        //dao.createUser("Alix");
        //dao.deleteUser(5);
        //System.out.println(dao.findByName("A"));
        //dao.deleteByName("Alix");
        //dao.updateUser(14,"New Alix");
    }



}