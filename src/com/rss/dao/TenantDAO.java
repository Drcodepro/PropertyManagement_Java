package com.rss.dao;
import com.rss.model.Tenant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TenantDAO {

    public void AddTenant(Connection con, Tenant tn){
        String sql = "Insert into tenants (fullName,phone,address) values(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tn.name);
            ps.setString(2,tn.phone);
            ps.setString(3,tn.address);
            ps.executeUpdate();

            System.out.println("Tenant added to the DataBase");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
