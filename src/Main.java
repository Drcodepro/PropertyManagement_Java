import com.rss.dao.RoomDAO;
import com.rss.dao.TenantDAO;
import com.rss.model.Room;
import com.rss.model.Tenant;
import com.rss.util.DBconnection;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection con = DBconnection.getConnection();

        // Tenant operations class
        TenantDAO Tdao = new TenantDAO();

        // add Tenant
        Tenant t1 = new Tenant("Raja ji","9836673673","Sitakhoh,Balaghat");
        Tdao.AddTenant(con,t1);


        // Add Room
        Room rm1 = new Room(3000,"nagpur",true);
        RoomDAO.AddRoom(con,rm1);




        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}