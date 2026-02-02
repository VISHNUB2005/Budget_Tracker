/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.sql.*;

public class DisplayDAO {

    public static void showTransactions(){

        try{
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM transactions");

            System.out.println("\nID | Amount | Type | Description | DateTime");
            System.out.println("-----------------------------------------------");

            while(rs.next()){
                System.out.println(
                    rs.getInt("id")+" | "+
                    rs.getDouble("amount")+" | "+
                    rs.getString("type")+" | "+
                    rs.getString("description")+" | "+
                    rs.getTimestamp("t_datetime")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

