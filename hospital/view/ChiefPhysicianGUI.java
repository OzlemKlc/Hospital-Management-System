


import Model.ChiefPhysician;
import Model.User;
import javax.swing.JPanel;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
//import static java.util.Collections.list;
// import java.awt.Color;
import Helper.*;
import Model.Clinic;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import Model.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Worker;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author kilic
 */
public class ChiefPhysicianGUI extends javax.swing.JFrame {
    
    static ChiefPhysician ChiefPhy = new ChiefPhysician();
    private JPanel w_pane;
    private DefaultTableModel doctorModel = null;
    private Object[] doctorData = null;
    private ArrayList<User> list;
    private DefaultTableModel clinicModel = null;
    private Object[] clinicData = null;
    Clinic clinic = new Clinic();
    private JPopupMenu clinicMenu;
    private DefaultTableModel workerModel = null;
    private Object[] workerData = null;

    //Worker worker = new Worker();
// private Color c0=new Color(209, 242, 235);
// private Color c1=new Color(163, 228, 215);
    /**
     * Creates new form ChiefPhysician
     *
     * @return
     */
    public ArrayList<User> getDoctorList() {
        return list;
    }

  public ChiefPhysicianGUI() {
        initComponents();
    }

    public ChiefPhysicianGUI(ChiefPhysician ChiefPhy) throws SQLException {
        jLabel1.setText("Welcome, Dear" + ChiefPhy.getUser_name());
//       DOCTOR MODEL
        doctorModel = new DefaultTableModel();
        Object[] colDoctorName = new Object[4];
        colDoctorName[0] = "ID";
        colDoctorName[1] = "Name Surname";
        colDoctorName[2] = "TC ıd";
        colDoctorName[3] = "Password";
        doctorModel.setColumnIdentifiers(colDoctorName);
        doctorData = new Object[4];
        table_doctor = new javax.swing.JTable(doctorModel);
        
        for (int i = 0; i < ChiefPhy.getDoctorList().size(); i++) {
            doctorData[0] = ChiefPhy.getDoctorList().get(i).getUser_id();
            doctorData[1] = ChiefPhy.getDoctorList().get(i).getTC_id();
            doctorData[2] = ChiefPhy.getDoctorList().get(i).getUser_name();
            doctorData[3] = ChiefPhy.getDoctorList().get(i).getUser_password();
            doctorModel.addRow(doctorData);
            
        }

////  WORKER MODEL
        DefaultTableModel workerModel = new DefaultTableModel();
        Object[] colWorker = new Object[2];
        colWorker[0] = "ID";
        colWorker[1] = "Name ";
        workerModel.setColumnIdentifiers(colWorker);
        Object[] workerData = new Object[2];

        // clinic model
        clinicModel = new DefaultTableModel();
        Object[] colClinic = new Object[2];
        colClinic[0] = "ID";
        colClinic[1] = "Clinic Name";
        
        clinicModel.setColumnIdentifiers(colClinic);
        clinicData = new Object[2];
        table_clinic = new javax.swing.JTable(clinicModel);
        
        for (int i = 0; i < clinic.getList().size(); i++) {
            clinicData[0] = clinic.getList().get(i).getId();
            clinicData[1] = clinic.getList().get(i).getName();
            
            clinicModel.addRow(clinicData);
        }
        
        table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                try {
                    fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
                    
                } catch (Exception ex) {
                    
                }
                
            }
        });
        
/*
        table_doctor.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int selectUser_ID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
                    String selectUser_name = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
                    String selectTC_id = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
                    String selectUser_password = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
                    
                    try {
                        boolean control = ChiefPhy.updateDoctor(selectUser_ID, selectTC_id, selectUser_name, selectUser_password);
                        if (control) {
                            Helper.showMsg("success");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();

//}
                    }
                    
                }
                
            });
*/
        w_clinic  = new javax.swing.JPanel();
            
            w_tab.addTab (
                    
            "Clinics", w_clinic);

        clinicMenu  = new JPopupMenu();
            JMenuItem updateMenu = new JMenuItem("Update");
            JMenuItem deleteMenu = new JMenuItem("Delete");
            
            clinicMenu.add (updateMenu);
            
            clinicMenu.add (deleteMenu);
            
            table_clinic.setComponentPopupMenu (clinicMenu);
            
            updateMenu.addActionListener ( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
                
                Clinic selectClinic = null;
                try {
                    selectClinic = clinic.getFetch(selID);
                } catch (SQLException ex) {
                    Logger.getLogger(ChiefPhysicianGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
                updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateGUI.setVisible(true);
                
                updateGUI.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        try {
                            updateClinicModel();
                            
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        
                    }
                });
            }
        });
        
        deleteMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (Helper.confirm("sure")) {
                    int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
                    try {
                        if (clinic.deleteClinic(selID)) {
                            Helper.showMsg("success");
                            updateClinicModel();
                            
                        } else {
                            Helper.showMsg("error");
                        }
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                }
                
            }
            
        });
        
        table_clinic = new JTable(clinicModel);
        table_clinic.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // super.mousePressed(e);
                Point point = e.getPoint();
                int selectedRow = table_clinic.rowAtPoint(point);
                table_clinic.setColumnSelectionInterval(selectedRow, selectedRow);
            }
        });
        // JComboBox işlemleriii
        select_doctor = new javax.swing.JComboBox<>();
        for (int i = 0; i < ChiefPhy.getDoctorList().size(); i++) {
            select_doctor.addItem(new Item(ChiefPhy.getDoctorList().get(i).getUser_id(), ChiefPhy.getDoctorList().get(i).getUser_name()));
        }
//  2: ayşe 
//  3: ahmet olmalı seçtiğim zaman 
        select_doctor.addActionListener(e -> {
            JComboBox c = (JComboBox) e.getSource();
            Item item = (Item) c.getSelectedItem();
            System.out.println(item.getKey() + ":" + item.getValue());
        });
        
       // initComponents();
        
    }
    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        popupMenu1 = new java.awt.PopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        w_tab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        fld_dName = new javax.swing.JTextField();
        fld_dTCid = new javax.swing.JTextField();
        fld_dPass = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        fld_doctorID = new javax.swing.JTextField();
        w_scrollPane = new javax.swing.JScrollPane();
        table_doctor = new javax.swing.JTable();
        w_clinic = new javax.swing.JPanel();
        wscroll_clinic = new javax.swing.JScrollPane();
        table_clinic = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        fld_clinicName = new javax.swing.JTextField();
        w_scrollWorker = new javax.swing.JScrollPane();
        table_worker = new javax.swing.JTable();
        select_doctor = new javax.swing.JComboBox<>();
        btn_addClinic1 = new javax.swing.JButton();
        btn_addWorker = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_workerSelect = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital Management Information System");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        jLabel1.setText("Welcome, Dear");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 17, 570, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        w_tab.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fld_dName.setText("jTextField1");
        jPanel2.add(fld_dName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 160, 30));

        fld_dTCid.setText("jTextField2");
        jPanel2.add(fld_dTCid, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 160, 30));

        fld_dPass.setText("jTextField3");
        fld_dPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_dPassActionPerformed(evt);
            }
        });
        jPanel2.add(fld_dPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 160, 30));

        jLabel2.setText("Name Surname");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 160, 30));

        jLabel3.setText("T.C. id");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 160, 30));

        jLabel4.setText("Password");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 160, 30));

        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 160, 30));

        jLabel5.setText("User ID");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 160, 30));

        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 335, 160, 30));

        fld_doctorID.setText("jTextField4");
        jPanel2.add(fld_doctorID, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 160, 30));

        w_scrollPane.setBackground(new java.awt.Color(255, 255, 255));

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
        w_scrollPane.setViewportView(table_doctor);

        jPanel2.add(w_scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, 360));

        w_tab.addTab("Doctor Management", jPanel2);

        w_clinic.setBackground(new java.awt.Color(255, 255, 255));
        w_clinic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wscroll_clinic.setBackground(new java.awt.Color(255, 255, 255));
        wscroll_clinic.setPreferredSize(new java.awt.Dimension(260, 233));

        table_clinic.setModel(new javax.swing.table.DefaultTableModel(
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
        wscroll_clinic.setViewportView(table_clinic);

        w_clinic.add(wscroll_clinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 360));

        jLabel6.setText("Clinic Name");
        w_clinic.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 150, 30));

        fld_clinicName.setText("jTextField1");
        fld_clinicName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_clinicNameActionPerformed(evt);
            }
        });
        w_clinic.add(fld_clinicName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 150, 30));

        table_worker.setModel(new javax.swing.table.DefaultTableModel(
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
        w_scrollWorker.setViewportView(table_worker);

        w_clinic.add(w_scrollWorker, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 250, 360));

        select_doctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        w_clinic.add(select_doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 150, 40));

        btn_addClinic1.setText("ADD");
        btn_addClinic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClinic1ActionPerformed(evt);
            }
        });
        w_clinic.add(btn_addClinic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 150, 30));

        btn_addWorker.setText("ADD");
        btn_addWorker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addWorkerActionPerformed(evt);
            }
        });
        w_clinic.add(btn_addWorker, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 150, 40));

        jLabel7.setText("Clinic Name");
        w_clinic.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 150, 30));

        btn_workerSelect.setText("SELECT");
        btn_workerSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_workerSelectActionPerformed(evt);
            }
        });
        w_clinic.add(btn_workerSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 150, 40));

        w_tab.addTab("Clinics", w_clinic);

        getContentPane().add(w_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 730, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
        dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fld_dPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_dPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_dPassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: 
        if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0 || fld_dTCid.getText().length() == 0) {
            Helper.showMsg("fill");
        } else {
            try {
                boolean control = ChiefPhy.addDoctor(fld_dTCid.getText(), fld_dName.getText(), fld_dPass.getText());
                if (control) {
                    Helper.showMsg("success");
                    fld_dName.setText(null);
                    fld_dTCid.setText(null);
                    fld_dPass.setText(null);
                    updateDoctorModel();
                }
                
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (fld_doctorID.getText().length() == 0) {
            Helper.showMsg("Please select a valid doctor");
        } else {
            
            if (Helper.confirm("sure")) {
                
                int selectID = Integer.parseInt(fld_doctorID.getText());
                
                try {
                    boolean control = ChiefPhy.deleteDoctor(selectID);
                    if (control) {
                        Helper.showMsg("success");
                        fld_doctorID.setText(null);
                        updateDoctorModel();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                
            }
            
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void fld_clinicNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_clinicNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_clinicNameActionPerformed

    private void btn_addClinic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClinic1ActionPerformed
        // TODO add your handling code here:   
        if (fld_clinicName.getText().length() == 0) {
            Helper.showMsg("fill");
            
        } else {
            try {
                if (clinic.addClinic(fld_clinicName.getText())) {
                    Helper.showMsg("success");
                    fld_clinicName.setText(null);
                    updateClinicModel();
                }
                
            } catch (SQLException e1) {
                // e1.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btn_addClinic1ActionPerformed

    private void btn_addWorkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addWorkerActionPerformed
        // TODO add your handling code here:
        int selRow = table_clinic.getSelectedRow();
        if (selRow >= 0) {
            String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
            int selClinicID = Integer.parseInt(selClinic);
            Item doctorItem = (Item) select_doctor.getSelectedItem();
            
            try {
                boolean control = ChiefPhy.addWorker(doctorItem.getKey(), selClinicID);
                if (control) {
                    Helper.showMsg("success");
                    DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
                    clearModel.setRowCount(0);
                    for (int i = 0; i < ChiefPhy.getClinicDoctorList(selClinicID).size(); i++) {
                        workerData[0] = ChiefPhy.getClinicDoctorList(selClinicID).get(i).getUser_id();
                        workerData[1] = ChiefPhy.getClinicDoctorList(selClinicID).get(i).getUser_name();
                        workerModel.addRow(workerData);
                    }
                    table_worker.setModel(workerModel);
                    
                } else {
                    Helper.showMsg("error");
                }
                
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            
        } else {
            Helper.showMsg("Please select an clinic !");
        }

    }//GEN-LAST:event_btn_addWorkerActionPerformed

    private void btn_workerSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_workerSelectActionPerformed
        // TODO add your handling code here:
        int selRow = table_clinic.getSelectedRow();
        if (selRow >= 0) {
            String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
            int selClinicID = Integer.parseInt(selClinic);
            DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
            clearModel.setRowCount(0);
            try {
                for (int i = 0; i < ChiefPhy.getClinicDoctorList(selClinicID).size(); i++) {
                    workerData[0] = ChiefPhy.getClinicDoctorList(selClinicID).get(i).getUser_id();
                    workerData[1] = ChiefPhy.getClinicDoctorList(selClinicID).get(i).getUser_name();
                    workerModel.addRow(workerData);
                }
                
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            table_worker.setModel(workerModel);
            
        } else {
            Helper.showMsg("Please select a clinic!");
        }

    }//GEN-LAST:event_btn_workerSelectActionPerformed
    
    public void updateDoctorModel() throws SQLException {
        DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
        clearModel.setRowCount(0);
        for (int i = 0; i < ChiefPhy.getDoctorList().size(); i++) {
            doctorData[0] = ChiefPhy.getDoctorList().get(i).getUser_id();
            doctorData[1] = ChiefPhy.getDoctorList().get(i).getTC_id();
            doctorData[2] = ChiefPhy.getDoctorList().get(i).getUser_name();
            doctorData[3] = ChiefPhy.getDoctorList().get(i).getUser_password();
            doctorModel.addRow(doctorData);
            
        }
    }
    
    public void updateClinicModel() throws SQLException {
        DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel(); // TYPE CASTING
        clearModel.setRowCount(0);
        for (int i = 0; i < clinic.getList().size(); i++) {
            clinicData[0] = clinic.getList().get(i).getId();
            clinicData[1] = clinic.getList().get(i).getName();
            clinicModel.addRow(clinicData);
            
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
            java.util.logging.Logger.getLogger(ChiefPhysician.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiefPhysician.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiefPhysician.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiefPhysician.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChiefPhysicianGUI(ChiefPhy).setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addClinic1;
    private javax.swing.JButton btn_addWorker;
    private javax.swing.JButton btn_workerSelect;
    private javax.swing.JTextField fld_clinicName;
    private javax.swing.JTextField fld_dName;
    private javax.swing.JTextField fld_dPass;
    private javax.swing.JTextField fld_dTCid;
    private javax.swing.JTextField fld_doctorID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JComboBox<String> select_doctor;
    private javax.swing.JTable table_clinic;
    private javax.swing.JTable table_doctor;
    private javax.swing.JTable table_worker;
    private javax.swing.JPanel w_clinic;
    private javax.swing.JScrollPane w_scrollPane;
    private javax.swing.JScrollPane w_scrollWorker;
    private javax.swing.JTabbedPane w_tab;
    private javax.swing.JScrollPane wscroll_clinic;
    // End of variables declaration//GEN-END:variables

}
