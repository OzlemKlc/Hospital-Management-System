/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import Helper.Helper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kilic
 */
public class Patient extends User {

    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public Patient() {
        super();
    }

    public Patient(int User_id, String TC_id, String User_name, String User_password, String User_type) {
        super(User_id, TC_id, User_name, User_password, User_type);
    }

    public boolean register(String TC_id, String User_password, String User_name) throws SQLException {
        int key = 0;
        boolean duplicate = false;
        String query = "INSERT INTO user" + "(TC_id,User_password,User_name,User_type) VALUES " + "(?,?,?,?)";
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE TC_id = '" + TC_id + "'");
            while (rs.next()) {
                duplicate = true;
                Helper.showMsg("There is a patient with this TC number");
                break;
            }
            if (!duplicate) {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, TC_id);
                preparedStatement.setString(2, User_password);
                preparedStatement.setString(3, User_name);
                preparedStatement.setString(4, "Patient");
                preparedStatement.executeUpdate();
                key = 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (key == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name, String appDate) throws SQLException {
        int key = 0;

        String query = "INSERT INTO appointment" + "(doctor_id,doctor_name,patient_id,patient_name,appDate) VALUES " + "(?,?,?,?,?)";
        try {
            //st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, doctor_id);
            preparedStatement.setString(2, doctor_name);
            preparedStatement.setInt(3, patient_id);
            preparedStatement.setString(4, patient_name);
            preparedStatement.setString(5, appDate);

            preparedStatement.executeUpdate();
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

    public boolean updateWhourStatus(int doctor_id, String wdate) throws SQLException {
        int key = 0;

        String query = "UPDATE whour SET status=? WHERE doctor_id=? AND wdate=?";
        try {
            //st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "P");
            preparedStatement.setInt(2, doctor_id);
            preparedStatement.setString(3, wdate);

            preparedStatement.executeUpdate();
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

}
