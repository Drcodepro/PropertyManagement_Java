import com.rss.dao.BookingDAO;
import com.rss.dao.RoomDAO;
import com.rss.dao.TenantDAO;
import com.rss.model.Room;
import com.rss.model.Tenant;
import com.rss.util.DBconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection con = DBconnection.getConnection();

        // Tenant operations class
        TenantDAO Tdao = new TenantDAO();

        // add Tenant
        Tenant t1 = new Tenant("sahil shekh","7493746738","kohoka,Balaghat");
        Tenant t2 = new Tenant("Ajju R","1026673673","Sitakhoh,Balaghat");
        Tdao.AddTenant(con,t1);
        Tdao.AddTenant(con,t2);


        // Add Room
        Room rm1 = new Room(1000,"katangi",true);
        Room rm2 = new Room(7000,"delhi",true);
        Room rm3 = new Room(3000,"seoni",true);
        RoomDAO.AddRoom(con,rm1);
        RoomDAO.AddRoom(con,rm2);
        RoomDAO.AddRoom(con,rm3);



        // call room book method from BookingDAO
        int booking_Id = BookingDAO.BookRoom();

        if(booking_Id == -1) System.out.println("***** Entered Room_Id is Invalid, Please Try Again *****");
        else System.out.println("User Successfully Booked a Room, Booking id is: " + booking_Id);

        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}