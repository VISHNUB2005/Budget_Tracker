/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.sql.*;

public class TransactionDAO extends DisplayDAO{

    public static void addTransaction(double amount, String type, String desc) {

        try {
            Connection con = DBConnection.getConnection();

            if(con == null){
                System.out.println("Database connection failed!");
                return;
            }
            
            // STEP 1: Get total received
            Statement st = con.createStatement();

            ResultSet rs1 = st.executeQuery(
                    "SELECT IFNULL(SUM(amount),0) FROM transactions WHERE type='RECEIVED'");
            rs1.next();
            double received = rs1.getDouble(1);

            // STEP 2: Get total spent
            ResultSet rs2 = st.executeQuery(
                    "SELECT IFNULL(SUM(amount),0) FROM transactions WHERE type='SPENT'");
            rs2.next();
            double spent = rs2.getDouble(1);

            double balance = received - spent;

            // STEP 3: Prevent negative balance
            if(type.equalsIgnoreCase("SPENT") && amount > balance){
                System.out.println("❌ Insufficient balance!");
                System.out.println("Current Balance: " + balance);
                return;
            }

            // STEP 4: Insert transaction
            String sql =
              "INSERT INTO transactions(amount,type,description) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, type.toUpperCase());
            ps.setString(3, desc);

            ps.executeUpdate();

            System.out.println("✅ Transaction Added Successfully!");
            

        } catch(Exception e) {
            e.printStackTrace();
        }
        TransactionDAO.showTransactions();
    }
}
