/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author kilic
 */
public class User {

    DBConnection conn = new DBConnection();
    private int User_id;
    String TC_id;
    String User_name;
    String User_password;
    String User_type;

    //Connection con = conn.connDb();
    /**
     */
    public User() {

    }

    public int getUser_id() {
        return User_id;
    }

    /**
     * @param User_id the User_id to set
     */
    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    /**
     * @return the TC_id
     */
    public String getTC_id() {
        return TC_id;
    }

    /**
     * @param TC_id the TC_no to set
     */
    public void setTC_id(String TC_id) {
        this.TC_id = TC_id;
    }

    /**
     * @return the User_name
     */
    public String getUser_name() {
        return User_name;
    }

    /**
     * @param User_name the User_name to set
     */
    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    /**
     * @return the User_password
     */
    public String getUser_password() {
        return User_password;
    }

    /**
     * @param User_password the User_password to set
     */
    public void setUser_password(String User_password) {
        this.User_password = User_password;
    }

    /**
     * @return the User_type
     */
    public String getUser_type() {
        return User_type;
    }

    /**
     * @param User_type the User_type to set
     */
    public void setUser_type(String User_type) {
        this.User_type = User_type;
    }

    public User(int user_id, String TC_id, String user_name, String user_password, String user_type) {
        super();
        this.User_id = user_id;
        this.TC_id = TC_id;
        this.User_name = user_name;
        this.User_password = user_password;
        this.User_type = user_type;
    }

}
