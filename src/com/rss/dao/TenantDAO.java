package com.rss.dao;
import com.rss.model.Tenant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TenantDAO {

    public static void AddTenant(Connection con, Tenant tenant){
        String sql = "Insert into tenants (fullName,phone,address) values(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenant.name);
            ps.setString(2,tenant.phone);
            ps.setString(3,tenant.address);
            ps.executeUpdate();

            System.out.println("Tenant added to the DataBase");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  static  void deleteTenant(Connection con, int tenant){
        String dlBookingSql = "delete from bookings where tenant_id = ?;";
        String dlTenantSql = " delete from tenants where tenant_id = ?;";
        String getRoomId = "select room_id from bookings where tenant_id = ?";
        String setRoomAvailabilitySql = "UPDATE rooms SET isAvailable=? WHERE room_id=?;";


        try {

            con.setAutoCommit(false);

            // get the roomId from bookings table
            int roomId;
            try(PreparedStatement ps = con.prepareStatement(getRoomId)) {
                ps.setInt(1, tenant);
                ResultSet rs = ps.executeQuery();
                rs.next();
                roomId = rs.getInt("room_id");
            }


            // delete the associated booking
            try(PreparedStatement ps = con.prepareStatement(dlBookingSql)) {
                ps.setInt(1, tenant);
                ps.executeUpdate();
            }



            // change the room Availability
            RoomDAO.updateRoomVal(con,setRoomAvailabilitySql,true,roomId);



            // delete the Tenant
            try(PreparedStatement ps = con.prepareStatement(dlTenantSql)) {
                ps.setInt(1, tenant);
               ps.executeUpdate();
            }

            con.commit();

        }
        catch(Exception e){
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
