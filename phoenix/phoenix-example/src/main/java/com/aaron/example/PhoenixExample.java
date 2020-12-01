package com.aaron.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PhoenixExample {
    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        ResultSet rset = null;

        Connection con = DriverManager.getConnection("jdbc:phoenix:master.allchips.com,node1.allchips.com,node2.allchips.com:2181");
        stmt = con.createStatement();
//        stmt.executeUpdate("create table test (mykey integer not null primary key, mycolumn varchar)");
//        stmt.executeUpdate("upsert into ALLCHIPS_WAREHOURSE.test values (NEXT VALUE FOR ALLCHIPS_WAREHOURSE.my_sequence,111)");

        for (int i = 0; i < 100000000; i++) {
            stmt.executeUpdate(String.format("UPSERT INTO ALLCHIPS_WAREHOURSE.TEST (mykey, mycolumn) values (NEXT VALUE FOR ALLCHIPS_WAREHOURSE.my_sequence,%d)",i));
            if(i%1000==0) {
                con.commit();
            }
        }
        con.commit();
        PreparedStatement statement = con.prepareStatement("select * from ALLCHIPS_WAREHOURSE.TEST limit 10");
        rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString("mykey"));
            System.out.println(rset.getString("mycolumn"));
        }
        statement.close();
        con.close();
    }
}
