/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author kilic
 */
public class ChiefPhysician extends User {

    //private Statement st =null;
    //private ResultSet rs = null;
    Connection con = conn.connDb();
    Statement st = null;
    ResultSet rs = null;

    PreparedStatement preparedStatement = null;

    public ChiefPhysician(int User_id, String TC_id, String User_name, String User_password, String User_type) {
        super(User_id, TC_id, User_name, User_password, User_type);
    }

    public ChiefPhysician() {
    }

    public ArrayList<User> getDoctorList() throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        //c
        User obj;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT *FROM user WHERE type='doctor'");
            while (rs.next()) {
                obj = new User(rs.getInt("User_id"), rs.getString("TC_id"), rs.getString("User_name"), rs.getString("User_password"), rs.getString("User_type"));
                list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public boolean addDoctor(String TC_id, String User_password, String User_name) throws SQLException {

        String query = "INSERT INTO user " + "(TC_id, User_name,User_password,User_type) VALUES" + "(?,?,?,?)";
        boolean key = false;
        Connection con1 = conn.connDb();
        try {
            st = con1.createStatement();
            PreparedStatement preparedStatement1 = con1.prepareStatement(query);
            preparedStatement1.setString(1, TC_id);
            preparedStatement1.setString(2, User_name);
            preparedStatement1.setString(3, User_password);
            preparedStatement1.setString(4, "doctor");
            preparedStatement1.executeUpdate();
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

    public boolean deleteDoctor(int User_id) throws SQLException {

        String query = "DELETE * FROM user WHERE User_id = ?";
        boolean key = false;
        Connection con1 = conn.connDb();
        try {
            st = con1.createStatement();
/*
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, User_id);
            preparedStatement.executeUpdate();


*/
            PreparedStatement preparedStatement1 = con1.prepareStatement(query);
            preparedStatement1.setInt(1, User_id);
            preparedStatement1.executeUpdate();
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

    public boolean updateDoctor(int User_id, String User_password, String User_name) throws SQLException {

        String query = "UPDATE user SET User_name = ? , TC_id = ? , User_password = ?  WHERE User_id = ?";
        boolean key = false;
        Connection con1 = conn.connDb();

        try {
            st = con.createStatement();
            PreparedStatement preparedStatement1 = con.prepareStatement(query);
            preparedStatement1.setString(1, User_name);
            preparedStatement1.setString(2, TC_id);
            preparedStatement1.setString(3, User_password);
            preparedStatement1.setInt(4, User_id);

            preparedStatement1.executeUpdate();
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

    public boolean addWorker(int user_id, int clinic_id) throws SQLException {

        String query = "INSERT INTO worker " + "(user_id,clinic_id) VALUES" + "(?,?)";
        boolean key = false;
        int count = 0; // klinikte aynı kişi varsa aynı kişiyi eklemesini önlemem lazım
        //Connection con1 = conn.connDb();

        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id= " + clinic_id + "AND user_id=" + user_id);
            while (rs.next()) {
                count++;
            }
            if (count == 0) {
                PreparedStatement preparedStatement1 = con.prepareStatement(query);
                preparedStatement1.setInt(1, user_id);
                preparedStatement1.setInt(2, clinic_id);
                preparedStatement1.executeUpdate();
            }
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

    public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {  // başhekimden sildim sanırım en son bak 
        ArrayList<User> list = new ArrayList<>();

        User obj;
        try {
            //ConnectMySql con = conn.ConnectMySql(); /// SIKINTILIIII
            st = con.createStatement();
            rs = st.executeQuery("SELECT u.User_id,u.TC_id,u.User_type,u.User_name,u.User_password FROM worker w LEFT JOIN User u ON w.id = u.User_id" + clinic_id);
            while (rs.next()) {
                obj = new User(rs.getInt("u.User_id"), rs.getString("u.TC_id"), rs.getString("u.User_name"), rs.getString("u.User_password"), rs.getString("u.User_type"));
                list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
