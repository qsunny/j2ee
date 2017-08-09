package com.aaron.mybatisgenerator.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil
{
    private static final Logger logger = Logger.getLogger(JdbcUtil.class);

    public static Connection getConnection(String driver, String url, String userName, String password)
            throws Exception
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getConnection", e);
        }throw new Exception();
    }

    public static void close(Connection conn)
    {
        if (conn != null)
            try
            {
                conn.close();
            } catch (SQLException e) {
                logger.error("close", e);
            }
    }
}