/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.sql.Connection;
import java.sql.Statement;

public class CleanupDAO {

    // Delete BOTH tables
    public static void deleteAll(){

        try{
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM transactions");
            st.executeUpdate("DELETE FROM plans");

            System.out.println("✅ All records deleted.");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Delete ONLY plans
    public static void deletePlans(){

        try{
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM plans");

            System.out.println("✅ Plans cleared.");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

