import com.rss.dao.BookingDAO;
import com.rss.dao.RoomDAO;
import com.rss.dao.TenantDAO;
import com.rss.model.Room;
import com.rss.model.Tenant;
import com.rss.util.DBconnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static void selectUserAction(Connection con){
        System.out.println("Select you Action you want to perform - ");
        System.out.println( "1 for add 'Tanent'" );
        System.out.println( "2 for add 'Room' " );
        System.out.println( "3 for 'booking room' " );
        System.out.println( "4 for delete 'Tanent' " );
        System.out.println( "5 for delete 'Room' " );
        System.out.println("6 for 'Cancel Booking'");
        System.out.print("Enter - ");

        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();

        switch (action){
            case 1: addTenant(con); break;
            case 2: addRoom(con); break;
            case 3: bookRoom(con); break;
            case 4: deleteTenant(con); break;
            case 5: deleteRoom(con); break;
            case 6: cancelBooking(con); break;
            default: System.out.println("Please enter the correct Action, try again");
        }
    }


    // ************ add Tenant ***********

    private static void addTenant(Connection con){
        Tenant t1 = new Tenant("karan mankar","4393746738","kohoka,Balaghat");
        Tenant t2 = new Tenant("bhupendra","9026673673","tekadi,Balaghat");
        TenantDAO.AddTenant(con,t1);
        TenantDAO.AddTenant(con,t2);
        System.out.println("----- Tenant Added successfully --- ");
    }



    // ******** Add Room **********

    private  static  void addRoom(Connection con){
        Room rm1 = new Room(8000,"NCR",true);
        Room rm2 = new Room(12000,"peru",true);
        Room rm3 = new Room(43000,"gurugram",true);
        RoomDAO.AddRoom(con,rm1);
        RoomDAO.AddRoom(con,rm2);
        RoomDAO.AddRoom(con,rm3);
        System.out.println("----- Room Added successfully --- ");
    }



    // ******* Book Room ********

    private static void bookRoom(Connection con){
        // call room book method from BookingDAO
        int booking_Id = BookingDAO.BookRoom(con);
        if(booking_Id == -1) System.out.println("***** Entered Room_Id is Invalid, Please Try Again *****");
        else System.out.println("User Successfully Booked a Room, Booking id is: " + booking_Id);
    }


    // ******** Delete Tenant **********

    private static void deleteTenant(Connection con){
        System.out.print("Enter the Tenant_id want to delete : ");
        Scanner sc = new Scanner(System.in);
        TenantDAO.deleteTenant(con,sc.nextInt());
        System.out.println("---- Tenant Deleted Successfully -----");
    }

    // ********** Delete Room **********
    private static void deleteRoom(Connection con){
        System.out.print("Enter the Room_id want to delete : ");
        Scanner sc = new Scanner(System.in);
        RoomDAO.deleteRoom(con,sc.nextInt());
        System.out.println("---- Room Deleted Successfully -----");
    }


    // ********* Delete the Existing Booking **********
    private static void cancelBooking(Connection con){

        Scanner sc = new Scanner(System.in);

        // booking id is just for safety, we are not using this variable
        System.out.print("Enter the Booking_Id want to delete : ");
        int booking_id = sc.nextInt();

        System.out.print("Enter the Room_id associated to this booking: ");
        int room_id = sc.nextInt();
        BookingDAO.deleteBooking(con,room_id);
        System.out.println("---- Booking Deleted Successfully -----");
    }


    public static void main(String[] args) {

        // creating an connection
        Connection con = DBconnection.getConnection();

        // select the operation want to perform
        selectUserAction(con);

        try {
            if(con!=null) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}