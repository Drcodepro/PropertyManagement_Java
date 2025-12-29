package com.rss.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import  com.rss.model.Room;

public class RoomDAO {
    public static void AddRoom(Connection con, Room rm){
        String sql = "Insert into rooms(isAvailable,price,location) values (?,?,?);";

        try {
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
}
