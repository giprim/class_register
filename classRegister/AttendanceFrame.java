
package classRegister;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIPRIM
 */
public class AttendanceFrame extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceFrame
     */
    public AttendanceFrame() {
        initComponents();
        attendanceTable();
        favicon();
    }

     private void favicon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("collegeLcolor.png")));
    }
    
    
    //fetches all data from the ATTENDANCE table from the database
    private void attendanceTable(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT ATTENDANCEID AS 'ID', FIRSTNAME, LASTNAME, DATE_TIME, ATTENDED FROM ATTENDANCE JOIN STUDENT ON STUDENT.STUDENTID=ATTENDANCE.STUDENTID";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)attendTbl.getModel();
              
              for(int i = 1; i <= numberOfColumns; i++){
                  tableModel.addColumn(metaData.getColumnName(i));                  
              } 
              
              Object[] rowData = new Object[numberOfColumns];
              while(resultSet.next()){
                  for(int i = 0; i < numberOfColumns; i++){
                      rowData[i] = resultSet.getObject(i + 1);
                  }
                      tableModel.addRow(rowData); 
                  
              }
             
                  attendTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
    
    // this method fetches all data in the ASSESSMENT table in the database IN ASSENDING ORDER
    private void assendingOrder(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT ATTENDANCEID AS 'ID', FIRSTNAME, LASTNAME, DATE_TIME, ATTENDED FROM ATTENDANCE JOIN STUDENT ON STUDENT.STUDENTID=ATTENDANCE.STUDENTID ORDER BY FIRSTNAME";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)attendTbl.getModel();
              
              for(int i = 1; i <= numberOfColumns; i++){
                  tableModel.addColumn(metaData.getColumnName(i));                  
              } 
              
              Object[] rowData = new Object[numberOfColumns];
              while(resultSet.next()){
                  for(int i = 0; i < numberOfColumns; i++){
                      rowData[i] = resultSet.getObject(i + 1);
                  }
                      tableModel.addRow(rowData); 
                  
              }
             
                  attendTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    // this method fetches all data in the ASSESSMENT table in the database IN DESCENDING ORDER
    private void descendingOrder(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT ATTENDANCEID AS 'ID', FIRSTNAME, LASTNAME, DATE_TIME, ATTENDED FROM ATTENDANCE JOIN STUDENT ON STUDENT.STUDENTID=ATTENDANCE.STUDENTID ORDER BY FIRSTNAME DESC";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)attendTbl.getModel();
              
              for(int i = 1; i <= numberOfColumns; i++){
                  tableModel.addColumn(metaData.getColumnName(i));                  
              } 
              
              Object[] rowData = new Object[numberOfColumns];
              while(resultSet.next()){
                  for(int i = 0; i < numberOfColumns; i++){
                      rowData[i] = resultSet.getObject(i + 1);
                  }
                      tableModel.addRow(rowData); 
                  
              }
             
                  attendTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
    
//=========================================================================================   
        private int id;
        private String fname;
        private String lname;
        private String date_time;
        private String attend;
        
    
        public AttendanceFrame(int Id, String Fname, String Lname, String Dtime, String Attend){
            this.id = Id;
            this.fname = Fname;
            this.lname = Lname;
            this.date_time = Dtime;
            this.attend = Attend;
            
        }
    
        public int getId(){
            return id;
        }
    
        public String getFname(){
            return fname;
        }
    
        public String getLname(){
            return lname;
        }
    
        public String gettestF(){
            return date_time;
        }
    
        public String gettestS(){
            return attend;
        }
    
        
    
    
    //================================================================================
    private ArrayList<AttendanceFrame> ListUser(String SearchedTerm){
        ArrayList<AttendanceFrame> usersList = new ArrayList<AttendanceFrame>();
       final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        Statement st;
        ResultSet rs;
    
        //String sqr = "SELECT * FROM STUDENT WHERE CONCAT('ID', 'FNAME', 'LNAME', 'AGE') LIKE '%"+SearchedTerm+"%'";
        //WHERE CONCAT('ASSESSMENTID','FIRSTNAME','LASTNAME','FIRSTTEST','SECONDTEST','EXAM','PROJECT') LIKE '%"+SearchedTerm+"%'";
        
    
        try {
            Connection con = DriverManager.getConnection(DATABASE_URL);          
            st = con.createStatement();
            String sql ="SELECT ATTENDANCEID, FIRSTNAME, LASTNAME, DATE_TIME, ATTENDED FROM ATTENDANCE JOIN STUDENT ON STUDENT.STUDENTID=ATTENDANCE.STUDENTID WHERE FIRSTNAME LIKE '%"+SearchedTerm+"%'"
                    + "OR LASTNAME LIKE '%"+SearchedTerm+"%' OR DATE_TIME LIKE '%"+SearchedTerm+"%' OR ATTENDED LIKE '%"+SearchedTerm+"%'";
         
            rs = st.executeQuery(sql);
    
            AttendanceFrame users;
            while(rs.next()){
                users = new AttendanceFrame(
                    rs.getInt("ATTENDANCEID"),
                    rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"),
                    rs.getString("DATE_TIME"),
                    rs.getString("ATTENDED")
                    
                );
                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return usersList;
    }
    
    private void findUsers(){
        ArrayList<AttendanceFrame> users = ListUser(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"ID", "FIRSTNAME","LASTNAME","DATE_TIME","ATTENDED"});
        Object[] row = new Object[5];
    
        for(int i = 0; i < users.size(); i++){
           
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getFname();
            row[2] = users.get(i).getLname();
            row[3] = users.get(i).gettestF();
            row[4] = users.get(i).gettestS();
            
            model.addRow(row);
        }
        attendTbl.setModel(model);
    }

   
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        attendTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Students > Attendance");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(500, 270));
        setResizable(false);

        jScrollPane1.setForeground(new java.awt.Color(0, 153, 153));

        attendTbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        attendTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        attendTbl.setGridColor(new java.awt.Color(0, 153, 153));
        attendTbl.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(attendTbl);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Attendance");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/attendance.png"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/refresh.png"))); // NOI18N
        jButton2.setText("refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/ascend.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 153));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/descendpsd.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 153, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/delete.png"))); // NOI18N
        jButton5.setText("close");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jMenu1.setForeground(new java.awt.Color(0, 153, 153));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/folder.png"))); // NOI18N
        jMenu1.setText("File");

        jMenuItem1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(0, 153, 153));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/020-plus.png"))); // NOI18N
        jMenuItem1.setText("New Attendance");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton5});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //calling the NewAttendance() Frame
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new NewAttendance().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    // refreshes the table
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        attendTbl.setModel(new DefaultTableModel());
        attendanceTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        findUsers();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        attendTbl.setModel(new DefaultTableModel());
        descendingOrder();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        attendTbl.setModel(new DefaultTableModel());
        assendingOrder();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendTbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
