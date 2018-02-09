package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by AELION on 09/02/2018.
 */
public class JdbcConnector {

    DataSource dataSource;

    public JdbcConnector() {
        this.dataSource = this.createDataSource();
    }

    DataSource createDataSource() {

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

    public Connection getConnection() throws SQLException {
       return  dataSource.getConnection();

    }
}
