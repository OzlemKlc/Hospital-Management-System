/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kilic
 */
public class Doctor extends User {

    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
    /*
        LAB11 SQL LABI
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "bzVvpDTz");
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM week13_lab" + ".gsmcare_customer");

        getResult(result);

        PreparedStatement statement2 = con.prepareStatement("select count(*) from week13_lab" + ".gsmcare_customer");
        ResultSet result2 = statement2.executeQuery();
        getResult(result2);
     */
    public Doctor() {
        super();
    }

    /**
     *
     * @param User_id
     * @param TC_id
     * @param User_name
     * @param User_password
     * @param User_type
     * @throws SQLExceptions
     */
    public Doctor(int User_id, String TC_id, String User_name, String User_password, String User_type) throws SQLException {
        super(User_id, TC_id, User_name, User_password, User_type);
    }

    public boolean addWhour(int doctor_id, String doctor_name, String wdate) throws SQLException {
        int key = 0;
        int count = 0;
        String query = "INSERT INTO whour" + "(doctor_id,doctor_name,wdate) VALUES " + "(?,?,?)";
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM whour WHERE status = 'A' AND doctor_id =" + doctor_id + "AND wdate = '" + wdate + "'");
            while (rs.next()) {
                count++;
                break;
            }
            if (count == 0) {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, doctor_id);
                preparedStatement.setString(2, doctor_name);
                preparedStatement.setString(3, wdate);
                preparedStatement.executeUpdate();
            }
            key = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (key == 1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {  /// Whour.java ya yazdım galiba burdan silinecek sanırım 
        ArrayList<Whour> list = new ArrayList<>();
        Whour obj;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id);
            while (rs.next()) {
                obj = new Whour();
                obj.setWhour_id(rs.getInt("id"));
                obj.setDoctor_id(rs.getInt("doctor_id"));
                obj.setDoctor_name(rs.getString("doctor_name"));
                obj.setStatus(rs.getString("status"));
                obj.setWdate(rs.getString("wdate"));
                list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public boolean deleteWhour(int id) throws SQLException {

        String query = "DELETE * FROM whour WHERE User_id = ?";
        boolean key = false;
        // ConnectMySql con = conn.ConnectMySql();
        try {
            st = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (key) {
            return true;
        } else {
            return false;
        }
    }
/*
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
*/

}
