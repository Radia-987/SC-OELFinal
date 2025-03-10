/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mypackage;

/**
 *
 * @author ASUS
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionEvent;

public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    public home() {
        initComponents();
//        fetchTasksFromDatabase(); 
filtercomboActionPerformed(new java.awt.event.ActionEvent(filtercombo, ActionEvent.ACTION_PERFORMED, "filter"));

    }

    
    
    private void fetchTasksFromDatabase(String filterType) {
        // Create DatabaseConnection object
//        DatabaseConnection dbConnection = new DatabaseConnection();
//
//        // SQL query to fetch tasks from the database
//        String query = "SELECT * FROM tasks";  // Replace 'tasks' with your table name
//
//        try {
//            // Get the connection and statement from the DatabaseConnection
//            Connection conn = dbConnection.getConnection();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            // Creating the table model
//            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//            model.setRowCount(0);  // Clear existing rows
//
//            // Loop through the result set and add each row to the table
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                String priority = rs.getString("priority");
//                Date dueDate = rs.getDate("due_date");
//                String status = rs.getString("status");
//
//                // Adding data to the table
//                model.addRow(new Object[]{
//                        id, title, description, priority, dueDate, status, 
//                }
//                );
//            }
//
//            // Close the resources
//            rs.close();
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//        }
DatabaseConnection dbConnection = new DatabaseConnection();

    // Construct the query based on the filter
    String query = "SELECT * FROM tasks";  // Default query if no filter

    if ("priority".equals(filterType)) {
        query = "SELECT * FROM tasks ORDER BY priority DESC";  // Sort by priority (high first)
    } else if ("status".equals(filterType)) {
        query = "SELECT * FROM tasks ORDER BY status DESC";  // Sort by status (completed first, then pending)
    } else if ("due_date".equals(filterType)) {
        query = "SELECT * FROM tasks ORDER BY due_date ASC";  // Sort by due date (earliest first)
    }else if ("id".equals(filterType) || filterType.isEmpty()) {
        query += " ORDER BY id ASC";  // Sort tasks by ascending order of ID (default)
    }


    try {
        // Get the connection and statement from the DatabaseConnection
        Connection conn = dbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        // Creating the table model
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  // Clear existing rows

        // Loop through the result set and add each row to the table
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String priority = rs.getString("priority");
            Date dueDate = rs.getDate("due_date");
            String status = rs.getString("status");

            // Adding data to the table
            model.addRow(new Object[]{
                    id, title, description, priority, dueDate, status,
            });
        }

        // Close the resources
        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addtaskbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        editbtn = new javax.swing.JButton();
        filtercombo = new javax.swing.JComboBox<>();
        removebtn = new javax.swing.JButton();
        descviewbtn = new javax.swing.JButton();
        Backbtn = new javax.swing.JButton();
        jlabel = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        addtaskbtn.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        addtaskbtn.setForeground(new java.awt.Color(255, 249, 249));
        addtaskbtn.setText("ADD TASK");
        addtaskbtn.setBorderPainted(false);
        addtaskbtn.setContentAreaFilled(false);
        addtaskbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtaskbtnActionPerformed(evt);
            }
        });
        getContentPane().add(addtaskbtn);
        addtaskbtn.setBounds(850, 60, 240, 70);

        jTable1.setBackground(new java.awt.Color(202, 172, 205));
        jTable1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Title ", "Desc.", "Priority", "Due Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAlignmentX(5.0F);
        jTable1.setAlignmentY(5.0F);
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setRowHeight(40);
        jTable1.setRowMargin(9);
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 180, 1070, 420);

        editbtn.setBackground(new java.awt.Color(104, 69, 117));
        editbtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        editbtn.setForeground(new java.awt.Color(255, 249, 249));
        editbtn.setText("Edit Task");
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });
        getContentPane().add(editbtn);
        editbtn.setBounds(170, 630, 170, 50);

        filtercombo.setBackground(new java.awt.Color(104, 69, 117));
        filtercombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filtercombo.setForeground(new java.awt.Color(255, 249, 249));
        filtercombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Filter", "Based on Priority", "Based on Deadline", "Based on Completion", " " }));
        filtercombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtercomboActionPerformed(evt);
            }
        });
        getContentPane().add(filtercombo);
        filtercombo.setBounds(640, 90, 170, 30);

        removebtn.setBackground(new java.awt.Color(104, 69, 117));
        removebtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        removebtn.setForeground(new java.awt.Color(255, 249, 249));
        removebtn.setText("Remove Task");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });
        getContentPane().add(removebtn);
        removebtn.setBounds(570, 630, 170, 50);

        descviewbtn.setBackground(new java.awt.Color(104, 69, 117));
        descviewbtn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        descviewbtn.setForeground(new java.awt.Color(255, 249, 249));
        descviewbtn.setText("View Desc.");
        descviewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descviewbtnActionPerformed(evt);
            }
        });
        getContentPane().add(descviewbtn);
        descviewbtn.setBounds(370, 630, 170, 50);

        Backbtn.setBackground(new java.awt.Color(104, 69, 117));
        Backbtn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Backbtn.setForeground(new java.awt.Color(255, 249, 249));
        Backbtn.setText("Back");
        Backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackbtnActionPerformed(evt);
            }
        });
        getContentPane().add(Backbtn);
        Backbtn.setBounds(770, 630, 170, 50);

        jlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mypackage/homefinal (1).jpg"))); // NOI18N
        getContentPane().add(jlabel);
        jlabel.setBounds(0, -200, 1220, 1160);

        jButton3.setText("jButton3");
        getContentPane().add(jButton3);
        jButton3.setBounds(970, 300, 75, 23);

        jButton4.setText("jButton1");
        getContentPane().add(jButton4);
        jButton4.setBounds(920, 220, 50, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

     
     
    private void addtaskbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtaskbtnActionPerformed
        // TODO add your handling code here:
           add addpage = new add();
       addpage.setSize(970, 820); // Optional: Set size if not already set in the 'home' class
    addpage.setLocationRelativeTo(null); // Center the JFrame on the screen
    addpage.setVisible(true);
    }//GEN-LAST:event_addtaskbtnActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        // TODO add your handling code here:
       int selectedRow = jTable1.getSelectedRow();
if (selectedRow != -1) {
    // Get the ID of the selected row (assuming it is in the first column)
    int id = (int) jTable1.getValueAt(selectedRow, 0);

    // Pass the ID to Edit.java
    Edit editPage = new Edit(id);
    editPage.setSize(970, 820); // Optional: Set size if not already set in the 'home' class
    editPage.setLocationRelativeTo(null); // Center the JFrame on the screen
    editPage.setVisible(true);

    this.dispose(); // Close the current page (optional)
} else {
    JOptionPane.showMessageDialog(this, "Please select a row to edit.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
}

    }//GEN-LAST:event_editbtnActionPerformed

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();  // yourTable is the JTable object
    
    // Check if a row is selected
    if (selectedRow != -1) {
        // Get the ID of the selected row
        int taskId = (int) jTable1.getValueAt(selectedRow, 0);  // Assuming ID is in the first column (index 0)

        // Connect to the database and delete the selected task
        DatabaseConnection dbConnection = new DatabaseConnection();
        try (Connection conn = dbConnection.getConnection()) {
            // Prepare the DELETE query
            String query = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, taskId);  // Set the taskId to the query parameter

            // Execute the query
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Task deleted successfully.");
                // After successful deletion, refresh the table or UI
//                refreshTaskList();
            } else {
                JOptionPane.showMessageDialog(this, "Task deletion failed.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a task to delete.");
    }
 this.dispose();  // Close this "Add Task" window
home homeWindow = new home();  // Create a new instance of the home screen
   
    // Ensure the home screen is visible again
    
        homeWindow.setVisible(false);  // Make the existing home screen window visible
    
    homeWindow.setSize(1170,800); // Optional: Set size if not already set in the 'home' class
    homeWindow.setLocationRelativeTo(null); // Center the JFrame on the screen
    homeWindow.setVisible(true);
    }//GEN-LAST:event_removebtnActionPerformed

    private void descviewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descviewbtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();  // yourTable is the JTable object
    
    // Check if a row is selected
    if (selectedRow != -1) {
        // Get the ID of the selected row (assuming the ID is in the first column)
        int taskId = (int) jTable1.getValueAt(selectedRow, 0);  

        // Create and open a new window to view the description
       View view = new View(taskId);
                view.setSize(710, 640); // Set the size of the JFrame to 790x790
                view.setLocationRelativeTo(null); // Center the JFrame on the screen
                view.setVisible(true);
        
    } else {
        JOptionPane.showMessageDialog(this, "Please select a task to view description.");
    }
    }//GEN-LAST:event_descviewbtnActionPerformed

    private void filtercomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtercomboActionPerformed
        // TODO add your handling code here:
        String selectedFilter = filtercombo.getSelectedItem().toString();

    // Re-fetch tasks based on the selected filter
    if ("Based on Priority".equals(selectedFilter)) {
        fetchTasksFromDatabase("priority");  // Filter by priority
    } else if ("Based on Deadline".equals(selectedFilter)) {
        fetchTasksFromDatabase("due_date");  // Filter by due date (if needed)
    } else if ("Based on Completion".equals(selectedFilter)) {
        fetchTasksFromDatabase("status");  // Filter by status (completed first)
    } else if ("No Filter".equals(selectedFilter)){
        fetchTasksFromDatabase("id");  // No filter, show all tasks
    }
    }//GEN-LAST:event_filtercomboActionPerformed

    private void BackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
         main frame = new main();
            frame.setSize(720,620); // Set the size of the JFrame to 520x520
            frame.setLocationRelativeTo(null); // Center the JFrame on the screen
            frame.setVisible(true);
    }//GEN-LAST:event_BackbtnActionPerformed

    
    public static void main(String args[]) {
    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                home frame = new home();
                frame.setSize(790, 790); // Set the size of the JFrame to 790x790
                frame.setLocationRelativeTo(null); // Center the JFrame on the screen
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Backbtn;
    private javax.swing.JButton addtaskbtn;
    private javax.swing.JButton descviewbtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JComboBox<String> filtercombo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JButton removebtn;
    // End of variables declaration//GEN-END:variables
}
