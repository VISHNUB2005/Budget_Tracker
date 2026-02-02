/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.sql.*;

public class ReportDAO {

    public static void showReport() {

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs1 =
              st.executeQuery("SELECT SUM(amount) FROM transactions WHERE type='RECEIVED'");
            rs1.next();
            double received = rs1.getDouble(1);

            ResultSet rs2 =
              st.executeQuery("SELECT SUM(amount) FROM transactions WHERE type='SPENT'");
            rs2.next();
            double spent = rs2.getDouble(1);

            System.out.println("\n---- Monthly Report ----");
            System.out.println("Total Received : "+received);
            System.out.println("Total Spent    : "+spent);
            System.out.println("Balance        : "+(received-spent));

            System.out.println("\nActive Plans:");

            ResultSet rs3 =
              st.executeQuery("SELECT * FROM plans WHERE pursuing=true");

            while(rs3.next()){
                System.out.println(rs3.getString("item")+
                        " | "+rs3.getDouble("planned_amount")+
                        " | Priority:"+rs3.getInt("priority"));
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
