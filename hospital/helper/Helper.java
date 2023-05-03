/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author kilic
 */
public class Helper {

    public static void optionPaneChangedButtonText() {
        UIManager.put("OptionPane.cancelButtonText", "cancel");
        UIManager.put("OptionPane.noButtonText", "no");
        UIManager.put("OptionPane.okButtonText", "okay");
        UIManager.put("OptionPane.yesButtonText", "yes");
    }

    public static void showMsg(String str) {
        String msg;
        optionPaneChangedButtonText();

        switch (str) {
            case "fillUp":
                msg = " Please fill in all fields";
                break;
            case "success":
                msg = "Transaction successful";
                break;
            case "error":
                msg = "Something went wrong";
                break;
            default:
                msg = str;
        }

        JOptionPane.showMessageDialog(null, msg, "Mesage", JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(String str) {
        String msg;
        optionPaneChangedButtonText();

        switch (str) {
            case "sure":
                msg = "Do you want to perform this operation?";
                break;
            default:
                msg = str;
                break;
        }

        int res = JOptionPane.showConfirmDialog(null, msg, "Attention!", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            return true;
        } else {
            return false;
        }
    }

}
