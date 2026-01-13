package com.rss.dao;

import com.rss.util.DBconnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingDAO {


    public static int BookRoom(){

        String bookRoomSql = "INSERT INTO bookings(booking_date,tenant_id,room_id) values(?,?,?);";
        String getBookingIdSql = " SELECT booking_id FROM bookings WHERE tenant_id=? AND room_id=?;";
        String setRoomAvailabilitySql = "UPDATE rooms SET isAvailable=? WHERE room_id=?;";


        // actual booking logic
        Connection con = null; // for new connection reference;
        try {
            // created a new connection for this Transaction
            con = DBconnection.getConnection();
            // prevent unnecessary commits if any error occurs
            con.setAutoCommit(false);


            // ******* Get available rooms  ******
            ArrayList<Integer> AvailableRoomList =  RoomDAO.GetAvailableRooms(con);

            // getting roomId and tenanId from user --
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter following details to book Room - ");
            System.out.println("Enter Tenant Id : ");
            int tenantId = sc.nextInt();
            System.out.println("Enter Room Id : ");
            int roomId = sc.nextInt();

            // Verify The User Input
            if(!AvailableRoomList.contains(roomId)){
                return -1;
            }


            // room booking logic
            PreparedStatement ps = con.prepareStatement(bookRoomSql);
            LocalDate ld = LocalDate.now();
            ps.setDate(1, Date.valueOf(ld));
            ps.setInt(2,tenantId);
            ps.setInt(3,roomId);
            ps.executeUpdate();

            // Set Room Availability in Database
            RoomDAO.updateRoomVal(con,setRoomAvailabilitySql,false,roomId);

            // get the Booking Id
            PreparedStatement ps2 = con.prepareStatement(getBookingIdSql);
            ps2.setInt(1,tenantId);
            ps2.setInt(2,roomId);
            ResultSet rs = ps2.executeQuery();

            // save all changes (commit) the changes if no errors in any DBoperations
            con.commit();

            rs.next(); // to move pointer to first row of table
            return rs.getInt("booking_id");


        } catch (Exception e) {
            try{
                if(con!=null){  // check if connection is not null
                    con.rollback(); // otherwise it will throw NullPointer exception
                }
            }
            catch (SQLException error){ // handle rollback exception
                error.printStackTrace();
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(con != null)con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
