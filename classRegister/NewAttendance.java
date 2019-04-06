
package classRegister;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author GIPRIM
 */
public class NewAttendance extends javax.swing.JFrame {
 final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
    String turnUp;
    Connection connect;
    Statement prep;
    ResultSet rs;
    
    String[] names;
    
    int idFromStudent;
    /**
     * Creates new form NewAttendance
     */
    public NewAttendance() {
        initComponents();
        updateCombo();
        showDate();
        startTime();
        favicon();
    }
    
    private void favicon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("collegeLcolor.png")));
    }

    // GETS THE NAME OF STUDENTS IN THE STUDENT DATABASE AND UPDATE THE COMBOBOX
    private void updateCombo(){
        final String sql = "SELECT * FROM STUDENT";
        
        try{
            connect = DriverManager.getConnection(DATABASE_URL);
            prep = connect.createStatement();
            rs = prep.executeQuery(sql);
            
            while(rs.next()){
                studentList.addItem(rs.getString("FIRSTNAME")+ " " + rs.getString("LASTNAME"));
            }
                     
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                prep.close();
                rs.close();
                connect.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    
    //SET UP FOR THE DATE
    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        dateLab.setText(s.format(d));
    }
    
    //SET UP FOR THE TIME TO START
    void startTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                timeLab.setText(s.format(d));
            }
        }
        ).start();
    }
    
    //SET UP FOR THE TIME TO STOP
    void stopTime(){
         new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                timeLab.setText(s.format(d));
            }
        }
        ).stop();
    }
    
    //INSERTION FOR THE ATTENDANCE
    private void insertAttendance(){
        if(studentList.getSelectedIndex() >= 1 ){
       final String INSERT_STATEMENT = "INSERT INTO ATTENDANCE(STUDENTID, ATTENDED)" + "VALUES(?,?)";
            
            //TRYING THE CONNECTION 
            try(
                    Connection connect = DriverManager.getConnection(DATABASE_URL);
                    PreparedStatement preparedStatement = connect.prepareStatement(INSERT_STATEMENT);
               ){
                
                
                preparedStatement.setInt(1, idFromStudent);
                preparedStatement.setString(2, turnUp);
                
                
                preparedStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "New Student Added", "Form Submitted", JOptionPane.INFORMATION_MESSAGE);
                
                
                studentList.setSelectedIndex(0);
                
            }catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Database Error", "Form Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
               
        }
    }
    
    //GETTING THE STUDENT ID FROM DATABASE
     void getStudentId(){
        final String sql = "SELECT * FROM STUDENT WHERE FIRSTNAME=? AND LASTNAME=?";
       if(!studentList.getSelectedItem().equals("Select")){
       try(
               Connection connection = DriverManager.getConnection(DATABASE_URL);
               PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
            String programText = (String)studentList.getSelectedItem();
             names = programText.split(" ");
            
            preparedStatement.setString(1,names[0].toUpperCase());
            preparedStatement.setString(2,names[1].toUpperCase());
            
            ResultSet resul = preparedStatement.executeQuery();
            
           
             if(resul.next()){
                  idFromStudent = resul.getInt("STUDENTID");
             } 
            
                     
        }catch(SQLException e){
            e.printStackTrace();
        }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        studentList = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        dateText = new javax.swing.JLabel();
        dateLab = new javax.swing.JLabel();
        dateText1 = new javax.swing.JLabel();
        timeLab = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Attendance");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(500, 270));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("New Attendance");

        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/editM.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Select a student and mark either \"present\" or \"absent\"");

        studentList.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        studentList.setForeground(new java.awt.Color(0, 153, 153));
        studentList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        studentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentListActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/update.png"))); // NOI18N
        jButton4.setText("Upload");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 153, 153));
        jRadioButton1.setText("Present");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 153, 153));
        jRadioButton2.setText("Absent");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        dateText.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        dateText.setForeground(new java.awt.Color(0, 153, 153));
        dateText.setText("Date:");

        dateLab.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        dateLab.setForeground(new java.awt.Color(102, 0, 102));

        dateText1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        dateText1.setForeground(new java.awt.Color(0, 153, 153));
        dateText1.setText("Time:");

        timeLab.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        timeLab.setForeground(new java.awt.Color(102, 0, 102));

        jButton5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 153, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/delete.png"))); // NOI18N
        jButton5.setText("Close");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateLab, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(172, 172, 172)
                                        .addComponent(dateText1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(studentList, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(111, 111, 111))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentList, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateLab, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dateText1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// SETS THE VALUE OF TURNUP TO ABSENT IF SELECTED
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
         turnUp = showedDown();
       
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      stopTime();
      dispose();
      
    }//GEN-LAST:event_jButton5ActionPerformed

// SETS THE VALUE OF TURNUP TO PRESENT IF SELECTED
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
         turnUp = showedUp();
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed
// CALLS THE INSERTATTENDANCE METHOD
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        insertAttendance();
    }//GEN-LAST:event_jButton4ActionPerformed
// CALLS THE GETSTUDENTID METHOD
    private void studentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentListActionPerformed
         getStudentId();
        
    }//GEN-LAST:event_studentListActionPerformed
    
    // RETURNS PRESENT
    String showedUp(){
        return "PRESENT";
    }
    // RETURNS ABSENT
    String showedDown(){
        return "ABSENT";
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel dateText;
    private javax.swing.JLabel dateText1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JComboBox<String> studentList;
    private javax.swing.JLabel timeLab;
    // End of variables declaration//GEN-END:variables
}
