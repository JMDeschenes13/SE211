package com.se211project;
import java.sql.*;
import java.sql.ResultSet;

public class DatabaseHandler{
    public Statement DBConn;

 

    public DatabaseHandler(){
        try{
            DBConn = getDBConnection();
        }catch(SQLException e){
            System.out.println("Error Establishing Connection to Database");
            e.printStackTrace();
        }

    }

    private Statement getDBConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/se211project?autoReconnect=true";
        String user = "SE211ProjectUser";
        String password = "password";
        Connection myConn = DriverManager.getConnection(url,user, password);
        Statement DBConn = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return DBConn;
    }
}
