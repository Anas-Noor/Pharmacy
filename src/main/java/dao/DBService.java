package dao;
import View.Admin.Admin_ViewMedicine;
import View.OrderMedicine;

import java.awt.*;
import java.sql.*;


public class DBService {
    public static Connection con;
    public static Boolean duplicate_check;
    public static Integer orderID;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharmacy_management","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String query) throws SQLException {
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;

    }

    public static void addProductPreparedQuery(String query) {
        duplicate_check = false;
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        }catch (Exception e){

            duplicate_check = true;
            System.out.println(e);
        }
    }

    public static void PreparedQuery(String query) {

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        }catch (Exception e){

            System.out.println(e);
        }
    }

    public static void PreparedQuery_GettingKey(String query){
        try {
            PreparedStatement pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                orderID = rs.getInt(1);
                System.out.println(orderID);
            }
        }catch (Exception e){
            duplicate_check = true;
            System.out.println(e);
        }
    }

    public static void deleteProduct(String query) {

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        }catch (Exception e){

            Admin_ViewMedicine.errorOnDeleteProduct();
        }
    }




}
