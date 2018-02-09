package io.pax.cryptos.dao;

import io.pax.cryptos.domain.SimpleWallet;
import io.pax.cryptos.domain.Wallet;

import java.sql.*;
import java.util.*;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Wallet> listWallets() throws SQLException {
        List<Wallet> wallets = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            wallets.add(new SimpleWallet(id, name));

        }
        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }

    public int createWallet(int userId, String name) throws SQLException {
        //Never ever String concatenation in JDBC !!!!
        String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)";
        //exple : query = "INSERT INTO wallet (name, user_id) VALUES ('test"', 2)";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.setInt(2, userId);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }

    public void deleteWallet(int walletId) throws SQLException {
        String query = "DELETE FROM wallet WHERE  id= ?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, walletId);

        statement.executeUpdate();

        statement.close();
        conn.close();


    }

    public List<Wallet> findByName(String extract) throws SQLException {
        List<Wallet> wallets = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet WHERE name LIKE '" + extract + "%'");
        //SELECT * FROM user u JOIN wallet w ON u.id=w.user_id WHERE u.name LIKE ?
        while (rs.next()) {
            extract = rs.getString("name");
            int id = rs.getInt("id");
            wallets.add(new SimpleWallet(id, extract));

        }
        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }

    public void deleteByName(String name) throws SQLException {
        String query = "DELETE FROM wallet WHERE  name= ?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        statement.executeUpdate();

        statement.close();
        conn.close();

    }

    public void updateWallet(int walletId, String newName) throws SQLException {

        String query = "UPDATE wallet SET name = ? WHERE id= ?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, newName);
        statement.setInt(2, walletId);

        statement.executeUpdate();

        statement.close();
        conn.close();

    }
    public void deleteAll(int userId) throws SQLException {
        String query = "DELETE FROM wallet WHERE  user_id= ?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    public static void main(String[] args) throws SQLException {
        WalletDao dao = new WalletDao();
        //int id = dao.createWallet(2, "Wallet a supprimer");
        //dao.deleteWallet(id);
        //dao.findByName("Y")
        //dao.deleteByName("Yolo");
        //dao.updateWallet(2,"serieux");
        //dao.deleteAll(6);
    }
}
