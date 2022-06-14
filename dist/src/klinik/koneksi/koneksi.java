/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik.koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {

    private static Connection mysqlKonek;

    public static Connection getDB() throws SQLException {
       try{
Class.forName("com.mysql.jdbc.Driver");
System.out.println("berhasil konek");
}
catch(ClassNotFoundException ex){
System.out.println("gagal koneksi"+ex);
}
String url = "jdbc:mysql://localhost/klinik";
try{
mysqlKonek = DriverManager.getConnection(url,"root","");
System.out.println("berhasil koneksi database");
 }
catch (SQLException ex){
System.out.println("gagal koneksi database"+ex);
}
return mysqlKonek;
    }
    
     
}
