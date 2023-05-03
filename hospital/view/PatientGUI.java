
import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Patient;
import Model.Whour;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author kilic
 */
public class PatientGUI extends javax.swing.JFrame {

    private JPanel w_pane;
    private static Patient patient = new Patient();
    private Clinic clinic = new Clinic();
    private DefaultTableModel doctorModel;
    private Object[] doctorData = null;
    private Whour whour = new Whour();
    private DefaultTableModel whourModel;
    private Object[] whourData = null;
    private int selectDoctorID = 0;
    private String selectDoctorName = null;
    private DefaultTableModel appointModel;
    private Object[] appointData = null;
    private Appointment appoint = new Appointment();

    /**
     * Creates new form PatientGUI
     */
    public PatientGUI(Patient patient) throws SQLException {
        doctorModel = new DefaultTableModel();
        Object[] colDoctor = new Object[2];
        colDoctor[0] = "ID";
        colDoctor[1] = "Name Surname";
        doctorModel.setColumnIdentifiers(colDoctor);
        doctorData = new Object[2];

        whourModel = new DefaultTableModel();
        Object[] colWhour = new Object[2];
        colWhour[0] = "ID";
        colWhour[1] = "Date";
        whourModel.setColumnIdentifiers(colWhour);
        whourData = new Object[2];

        appointModel = new DefaultTableModel();
        Object[] colAppoint = new Object[3];
        colAppoint[0] = "ID";
        colAppoint[1] = "Doctor";
        colAppoint[2] = "Date";
        appointModel.setColumnIdentifiers(colAppoint);
        appointData = new Object[3];
        for (int i = 0; i < appoint.getPatientList(patient.getUser_id()).size(); i++) {
            appointData[0] = appoint.getPatientList(patient.getUser_id()).get(i).getId();
            appointData[1] = appoint.getPatientList(patient.getUser_id()).get(i).getDoctorName();
            appointData[2] = appoint.getPatientList(patient.getUser_id()).get(i).getAppDate();
            appointModel.addRow(appointData);
        }

        table_doctor = new JTable(doctorModel);
        table_whour = new JTable(whourModel);

        w_pane = new JPanel();
        jLabel1.setText("Welcome , Dear" + patient.getUser_name());
        table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
        table_appoint = new JTable(appointModel);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        w_appoint = new javax.swing.JTabbedPane();
        w_appointment = new javax.swing.JPanel();
        w_scrollDoctor = new javax.swing.JScrollPane();
        table_doctor = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        select_clinic = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btn_selDoctor = new javax.swing.JButton();
        w_scrollWhour = new javax.swing.JScrollPane();
        table_whour = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btn_addAppoint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        w_scrollAppoint = new javax.swing.JScrollPane();
        table_appoint = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital Management Information System");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(750, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel1.setText("Welcome , Dear");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 17, 530, 48));

        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 32, 118, 33));

        w_appointment.setBackground(new java.awt.Color(255, 255, 255));
        w_appointment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        w_scrollDoctor.setBackground(new java.awt.Color(255, 255, 255));

        table_doctor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        w_scrollDoctor.setViewportView(table_doctor);

        w_appointment.add(w_scrollDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 330));

        jLabel2.setText("Doctor List");
        w_appointment.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 30));

        jLabel3.setText("Clinic Name");
        w_appointment.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 16, 110, 30));

        select_clinic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        select_clinic.setPreferredSize(new java.awt.Dimension(150, 35));
        select_clinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_clinicActionPerformed(evt);
            }
        });
        w_appointment.add(select_clinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        jLabel4.setText("Choose Doctor");
        w_appointment.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 140, 30));

        btn_selDoctor.setText("SELECT");
        btn_selDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selDoctorActionPerformed(evt);
            }
        });
        w_appointment.add(btn_selDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 140, 30));

        table_whour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        w_scrollWhour.setViewportView(table_whour);

        w_appointment.add(w_scrollWhour, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 240, 330));

        jLabel5.setText("Appointment");
        w_appointment.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 140, 30));

        btn_addAppoint.setText("Get an appointment");
        btn_addAppoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAppointActionPerformed(evt);
            }
        });
        w_appointment.add(btn_addAppoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 150, 40));

        w_appoint.addTab("Appointment System", w_appointment);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_appoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        w_scrollAppoint.setViewportView(table_appoint);

        jPanel1.add(w_scrollAppoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 360));

        w_appoint.addTab("My appointments", jPanel1);

        getContentPane().add(w_appoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 730, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void select_clinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_clinicActionPerformed
        // TODO add your handling code here:
        try {
            select_clinic.addItem("---Choose Clinic---");
            for (int i = 0; i < clinic.getList().size(); i++) {
           select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));

               // select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
            }
            select_clinic.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (select_clinic.getSelectedIndex() != 0) {
                        JComboBox c = (JComboBox) e.getSource();
                        Item item = (Item) c.getSelectedItem();
                        // System.out.println(item.getKey() + " - " + item.getValue());
                        DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
                        clearModel.setRowCount(0);
                        try {
                            for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
                                doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getUser_id();
                                doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getUser_name();
                                doctorModel.addRow(doctorData);
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    } else {
                        DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
                        clearModel.setRowCount(0);
                    }
                }
            });
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }//GEN-LAST:event_select_clinicActionPerformed

    private void btn_selDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selDoctorActionPerformed
        // TODO add your handling code here:
        int row = table_doctor.getSelectedRow();
        if (row > 0) {
            String value = table_doctor.getModel().getValueAt(row, 0).toString();
            int id = Integer.parseInt(value);
            DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
            clearModel.setRowCount(0);
            try {
                for (int i = 0; i < whour.getWhourList(id).size(); i++) {
                    whourData[0] = whour.getWhourList(id).get(i).getWhour_id();
                    whourData[1] = whour.getWhourList(id).get(i).getWdate();
                    whourModel.addRow(whourData);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            table_whour.setModel(whourModel);
            selectDoctorID = id;
            selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
            //System.out.println(selectDoctorID + " - " + selectDoctorName);
        } else {
            Helper.showMsg("Please choose a doctor");
        }

    }//GEN-LAST:event_btn_selDoctorActionPerformed

    private void btn_addAppointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAppointActionPerformed
        // TODO add your handling code here:
        int selRow = table_whour.getSelectedRow();
        if (selRow >= 0) {
            String date = table_whour.getModel().getValueAt(selRow, 1).toString();
            try {
                boolean control = patient.addAppointment(selectDoctorID, patient.getUser_id(), selectDoctorName, patient.getUser_name(), date);
                if (control) {
                    Helper.showMsg("success");
                    patient.updateWhourStatus(selectDoctorID, date);
                    updateWhourModel(selectDoctorID);
                    updateAppointModel(patient.getUser_id());

                } else {
                    Helper.showMsg("error");

                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            Helper.showMsg("Please enter a valid date");
        }
    }//GEN-LAST:event_btn_addAppointActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    public void updateWhourModel(int doctor_id) throws SQLException {
        DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
        clearModel.setRowCount(0);
        for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
            whourData[0] = whour.getWhourList(doctor_id).get(i).getWhour_id();
            whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
            whourModel.addRow(whourData);
        }

    }

    public void updateAppointModel(int patient_id) throws SQLException {
        DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
        clearModel.setRowCount(0);
        for (int i = 0; i < appoint.getPatientList(patient_id).size(); i++) {
            appointData[0] = appoint.getPatientList(patient_id).get(i).getId();
            appointData[1] = appoint.getPatientList(patient_id).get(i).getDoctorName();
            appointData[2] = appoint.getPatientList(patient_id).get(i).getAppDate();
            appointModel.addRow(appointData);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PatientGUI(patient).setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addAppoint;
    private javax.swing.JButton btn_selDoctor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> select_clinic;
    private javax.swing.JTable table_appoint;
    private javax.swing.JTable table_doctor;
    private javax.swing.JTable table_whour;
    private javax.swing.JTabbedPane w_appoint;
    private javax.swing.JPanel w_appointment;
    private javax.swing.JScrollPane w_scrollAppoint;
    private javax.swing.JScrollPane w_scrollDoctor;
    private javax.swing.JScrollPane w_scrollWhour;
    // End of variables declaration//GEN-END:variables
}
