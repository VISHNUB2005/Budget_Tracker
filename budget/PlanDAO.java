/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.sql.*;

public class PlanDAO {

    public static void addPlan(String item,double amt,int priority,boolean pursue){

        try {
            Connection con = DBConnection.getConnection();

            String sql =
              "INSERT INTO plans(item,planned_amount,priority,pursuing,completed) VALUES(?,?,?,?,false)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,item);
            ps.setDouble(2,amt);
            ps.setInt(3,priority);
            ps.setBoolean(4,pursue);

            ps.executeUpdate();

            System.out.println("Plan Added!");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

