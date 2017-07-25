/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import schedulingapplication.model.User;

/**
 *
 */
public class UserDAO {

    public static boolean checkUser(User user) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "select `userId` from `U03q1A`.`user` where `username`=? and `password`=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        return rs.first();
    }
}
