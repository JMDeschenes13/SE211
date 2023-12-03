package com.se211project;


import java.sql.*;
import com.se211project.DatabaseHandler;
import com.se211project.GUI;


public class Main {
    private static GUI gui;
    private static Statement DBConn;
    private static DatabaseHandler databaseHandler;
    
     

    public static void main(String[] args){
        configDB();
        gui = new GUI();
        
        
        
    }

    public static String[] getRoomListData(){
        String[] listData = new String[4];
        String sqlQuery = "SELECT * FROM Room WHERE Avail_Status = true";
        ResultSet rs;
        
        try{
            
            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Room Number: " + rs.getString("ID") + ", ";
                listData[i] += "Room Type: " + rs.getString("Room_Type") + ", ";
                listData[i] += "Room Rate: $" + rs.getString("Room_Rate");
                //listData[i] += "Floor Number: " + rs.getString("Floor_ID");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;

    }


    public static String[] getServiceListData(){

        String[] listData = new String[1];
        String sqlQuery = "SELECT * FROM Service";
        ResultSet rs;
        
        try{
            
            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Service ID: " + rs.getString("ID") + ", ";
                listData[i] += "Service Name: " + rs.getString("Serv_Type") + ", ";
                listData[i] += "Service Cost: $" + rs.getString("Service_Cost");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;

    
    }


    public static String[] getReservationListData(int GuestID){

        String[] listData = new String[1];
        String sqlQuery = "SELECT * FROM Reservation WHERE Guest_ID = " + GuestID;
        ResultSet rs;

        
        try{
            
            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Reservation ID: " + rs.getString("ID") + ", ";
                listData[i] += "Room Number: " + rs.getString("Room_ID") + ", ";
                listData[i] += "Check In Date: " + rs.getString("Checkin_Date").substring(0,rs.getString("Checkin_date").length()-9) + ", ";
                listData[i] += "Check Out Date: " + rs.getString("Checkout_Date").substring(0,rs.getString("Checkout_date").length()-9);
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;

    }

    public static String[] getServiceBookingListData(int GuestID){

        String[] listData = new String[1];
        String sqlQuery = "SELECT * FROM Service_Booking WHERE Guest_ID = " + GuestID;
        ResultSet rs;

        
        try{
            
            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Service Booking ID: " + rs.getString("ID") + ", ";
                listData[i] += "Service ID: " + rs.getString("Serv_ID") + ", ";
                listData[i] += "Guest ID: " + rs.getString("Guest_ID");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;

    }


    public static String[] getRoomsAvailableData(String startDate, String endDate){
        String[] listData = new String[4];
        String sqlQuery = "SELECT ID, Room_Type, Room_Rate FROM Room WHERE NOT EXISTS(SELECT * FROM Reservation WHERE Room.ID = Reservation.Room_ID AND '" + startDate + " 00:00:00' NOT BETWEEN checkin_date";
        sqlQuery += " AND checkout_date AND '" + endDate + " 00:00:00' NOT BETWEEN checkin_date AND checkout_date";
        ResultSet rs;


        sqlQuery = "SELECT ID, Room_Type, Room_Rate FROM Room WHERE NOT EXISTS(SELECT * FROM Reservation WHERE Room.ID = Reservation.Room_ID AND ('" + startDate + "' BETWEEN";
        sqlQuery += " Reservation.checkin_date AND Reservation.Checkout_Date OR '" + endDate + "' BETWEEN Reservation.Checkin_date AND Reservation.checkout_date))";
        try{

            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Room Number: " + rs.getString("ID") + ", ";
                listData[i] += "Room Type: " + rs.getString("Room_Type") + ", ";
                listData[i] += "Room Rate: $" + rs.getString("Room_Rate");
                
                i++;
            }

        }catch(SQLException e){
            e.printStackTrace();
        
        }
        return listData;
        
    }

    public static String[] getGuestListData(){
        String[] listData = new String[4];
        String sqlQuery = "SELECT ID, first_Name, last_name FROM Guest";
        ResultSet rs;
        
        try{
            
            
            rs = DBConn.executeQuery(sqlQuery);
            rs.last();
            listData = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                listData[i] = "Guest ID: " + rs.getString("ID") + ", ";
                listData[i] += rs.getString("First_Name");
                listData[i] += " " +  rs.getString("Last_Name");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;

    }



    

    private static boolean configDB(){
        databaseHandler = new DatabaseHandler();
        DBConn = databaseHandler.DBConn;
        String selectDB = "USE hotel;";

        boolean result = false;
        
        
        try{
            result = DBConn.execute(selectDB);
            //result = DBConn.execute(sql);
            //result = DBConn.execute(insertSQL);
            System.out.println(result); 
       }catch(SQLException e){
           e.printStackTrace();
            
         }
        
        return result;
    }


    public static void createReservation(String startDate, String endDate, String roomNumber, String GuestID){
        String sqlQuery = "";
        String sqlSelect = "SELECT * FROM Reservation";
        int maxIDValue = 0;
        try{
            ResultSet rs = DBConn.executeQuery(sqlSelect);
            rs.last();
            maxIDValue = rs.getInt("ID");
            maxIDValue++;
            sqlQuery = "INSERT INTO Reservation VALUES ("+ maxIDValue + ", "  + GuestID + ", " + roomNumber + ", '" + startDate + "', '" + endDate + "', NOW() )";

            DBConn.execute(sqlQuery);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void createServiceBooking(String guestID, String serviceID){
        System.out.println(serviceID);
        String sqlQuery = "";
        String sqlSelect = "SELECT * FROM Service_Booking";
        int maxIDValue = 0;
        try{
            ResultSet rs = DBConn.executeQuery(sqlSelect);
            rs.last();
            maxIDValue = rs.getInt("ID");
            maxIDValue++;
            sqlQuery = "INSERT INTO Service_Booking(ID, Guest_ID, Serv_ID) VALUES ("+ maxIDValue + ", "  + guestID + ", " + serviceID +  ")";

            DBConn.execute(sqlQuery);
        }catch(SQLException e){
            e.printStackTrace();
        }



    }
}