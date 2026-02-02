/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.util.Scanner;
import java.sql.*;
public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        

        while(true){
            try {

        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM transactions");
        rs.next();

        int count = rs.getInt(1);

        if(count == 0){

            System.out.println("\nHey I saw u got money, store it in me to keep a track.");

            System.out.print("Enter starting balance: ");

          
            double bal = sc.nextDouble();

            PreparedStatement ps =
              con.prepareStatement("INSERT INTO transactions(amount,type,description) VALUES(?,?,?)");

            ps.setDouble(1, bal);
            ps.setString(2, "RECEIVED");
            ps.setString(3, "Opening Balance");

            ps.executeUpdate();

            System.out.println("✅ Opening balance saved!\n");
        }

    } catch(Exception e){
        e.printStackTrace();
    }
            
            
            System.out.println("\n1.Add Transaction");
            System.out.println("2.Add Plan");
            System.out.println("3.View Report");
            System.out.println("4.Exit");

            int ch = sc.nextInt();

            switch(ch){

                case 1:
                    System.out.print("Amount: ");
                    double a = sc.nextDouble();
                    System.out.print("Type(SPENT/RECEIVED): ");
                    String t = sc.next();
                    sc.nextLine();
                    System.out.print("Description: ");
                    String d = sc.nextLine();
                    TransactionDAO.addTransaction(a,t,d);
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Item: ");
                    String item=sc.nextLine();
                    System.out.print("Amount: ");
                    double amt=sc.nextDouble();
                    System.out.print("Priority(1-5): ");
                    int p=sc.nextInt();
                    System.out.print("Pursuing(true/false): ");
                    boolean b=sc.nextBoolean();
                    PlanDAO.addPlan(item,amt,p,b);
                    break;

                case 3:
                    ReportDAO.showReport();
                    break;

                case 4:

    System.out.println("\nBefore exiting choose:");
    System.out.println("1. Keep all records");
    System.out.println("2. Delete EVERYTHING");
    System.out.println("3. Delete ONLY plans");

    int exitChoice = sc.nextInt();

    switch(exitChoice){

        case 1:
            System.out.println("Records preserved.");
            break;

        case 2:
            CleanupDAO.deleteAll();
            break;

        case 3:
            CleanupDAO.deletePlans();
            break;

        default:
            System.out.println("Invalid choice. Keeping records.");
    }

    System.out.println("Exiting application...");
    System.exit(0);

                    
            }
        }
    }




}

