package com.rss.dao;
import java.sql.*;
import  com.rss.model.Room;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDAO {
    public static void AddRoom(Connection con, Room rm){
        String sql = "Insert into rooms(isAvailable,price,location) values (?,?,?);";

        try {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,rm.isAvailable);
            ps.setInt(2,rm.price);
            ps.setString(3,rm.location);

            ps.executeUpdate();
            System.out.println("Room added to DB successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  static ArrayList<Integer> GetAvailableRooms( Connection con){
        String sql = "SELECT room_id,price,location FROM rooms WHERE isAvailable=1";
        ArrayList<Integer> AvailableRoomList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available rooms are ---");
            int i =1;
            while(rs.next()){
                int roomId = rs.getInt("room_id");
                int roomPrice = rs.getInt("price");
                String roomLocation = rs.getString("location");

                AvailableRoomList.add(roomId);

                System.out.println("(" + i++ + "). " +
                        "room_id: " + roomId +
                        ", room_price: " + roomPrice+
                        ", room_location: " + roomLocation
                );
            }

            return AvailableRoomList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateRoomVal(Connection con, String updateSql, boolean newValue,int roomId){
        try {
            PreparedStatement ps = con.prepareStatement(updateSql);
            ps.setBoolean(1,newValue);
            ps.setInt(2,roomId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
