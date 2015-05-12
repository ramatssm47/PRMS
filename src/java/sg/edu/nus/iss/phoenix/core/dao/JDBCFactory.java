/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Dinesh
 */
public class JDBCFactory {

    private static DataSource ds;
    private static Context ctx;

    public static Connection getConnection() {
        // Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection conn = null;
        try {
            if (ctx == null) {
                ctx = new InitialContext();
            }
            if (ds == null) {
                ds = (DataSource) ctx.lookup("jdbc/phoenix");

            }

            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phoenix?user=root&password=root");

            } catch (Exception e) {
            }

            //Logger.getLogger(JDBCFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

}
