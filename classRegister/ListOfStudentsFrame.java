
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
public class ListOfStudentsFrame extends javax.swing.JFrame {

    /**
     * Creates new form ListOfStudentsFrame
     */
    public ListOfStudentsFrame() {
        initComponents();
        studentTable();
        favicon();
    }
    
    private void favicon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("collegeLcolor.png")));
    }
    
    // fetches all data from the STUDENT and PROGRAMS tables in the database
    private void studentTable(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT STUDENTID AS 'ID',FIRSTNAME,LASTNAME,GENDER,PROGRAM FROM STUDENT JOIN PROGRAMS ON PROGRAMS.PROGRAMID=STUDENT.PROGRAMID";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)studentTbl.getModel();
              
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
             
                  studentTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
    // this method fetches all data in the ASSESSMENT table in the database IN ASSENDING ORDER
    private void assendingOrder(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT STUDENTID AS 'ID',FIRSTNAME,LASTNAME,GENDER,PROGRAM FROM STUDENT JOIN PROGRAMS ON PROGRAMS.PROGRAMID=STUDENT.PROGRAMID ORDER BY FIRSTNAME";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)studentTbl.getModel();
              
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
             
                  studentTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    // this method fetches all data in the ASSESSMENT table in the database IN DESCENDING ORDER
    private void descendingOrder(){
        final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        final String SELECT_QUERY = 
        "SELECT STUDENTID AS 'ID',FIRSTNAME,LASTNAME,GENDER,PROGRAM FROM STUDENT JOIN PROGRAMS ON PROGRAMS.PROGRAMID=STUDENT.PROGRAMID ORDER BY FIRSTNAME DESC";
        
        try(
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);    
           )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
              int numberOfColumns = metaData.getColumnCount();
              
              DefaultTableModel tableModel = (DefaultTableModel)studentTbl.getModel();
              
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
             
                  studentTbl.setModel(tableModel);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
    
//=========================================================================================   
        private int id;
        private String fname;
        private String lname;
        private String gender;
        private String program;
        
    
        public ListOfStudentsFrame(int Id, String Fname, String Lname, String gender, String Prog){
            this.id = Id;
            this.fname = Fname;
            this.lname = Lname;
            this.gender = gender;
            this.program = Prog;
            
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
    
        public String getGender(){
            return gender;
        }
    
        public String getProg(){
            return program;
        }
    
        
    
    
    //================================================================================
    private ArrayList<ListOfStudentsFrame> ListUser(String SearchedTerm){
        ArrayList<ListOfStudentsFrame> usersList = new ArrayList<ListOfStudentsFrame>();
       final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=GITECK;integratedSecurity=true";
        Statement st;
        ResultSet rs;
    
        //String sqr = "SELECT * FROM STUDENT WHERE CONCAT('ID', 'FNAME', 'LNAME', 'AGE') LIKE '%"+SearchedTerm+"%'";
        //WHERE CONCAT('ASSESSMENTID','FIRSTNAME','LASTNAME','FIRSTTEST','SECONDTEST','EXAM','PROJECT') LIKE '%"+SearchedTerm+"%'";
        
    
        try {
            Connection con = DriverManager.getConnection(DATABASE_URL);          
            st = con.createStatement();
            String sql ="SELECT STUDENTID,FIRSTNAME,LASTNAME,GENDER,PROGRAM FROM STUDENT JOIN PROGRAMS ON PROGRAMS.PROGRAMID=STUDENT.PROGRAMID WHERE FIRSTNAME LIKE '%"+SearchedTerm+"%'"
                    + "OR LASTNAME LIKE '%"+SearchedTerm+"%' OR GENDER LIKE '%"+SearchedTerm+"%' OR PROGRAM LIKE '%"+SearchedTerm+"%'";
         
            rs = st.executeQuery(sql);
    
            ListOfStudentsFrame users;
            while(rs.next()){
                users = new ListOfStudentsFrame(
                    rs.getInt("STUDENTID"),
                    rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"),
                    rs.getString("GENDER"),
                    rs.getString("PROGRAM")
                    
                );
                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return usersList;
    }
    
    private void findUsers(){
        ArrayList<ListOfStudentsFrame> users = ListUser(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"ID", "FIRSTNAME","LASTNAME","GENDER","PROGRAM"});
        Object[] row = new Object[5];
    
        for(int i = 0; i < users.size(); i++){
           
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getFname();
            row[2] = users.get(i).getLname();
            row[3] = users.get(i).getGender();
            row[4] = users.get(i).getProg();
            
            model.addRow(row);
        }
        studentTbl.setModel(model);
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
        studentTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student > List of Students");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(500, 290));
        setResizable(false);

        jScrollPane1.setForeground(new java.awt.Color(0, 153, 153));

        studentTbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        studentTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        studentTbl.setGridColor(new java.awt.Color(0, 153, 153));
        studentTbl.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(studentTbl);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List Of Students");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/student1.png"))); // NOI18N

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

        jMenu2.setForeground(new java.awt.Color(0, 153, 153));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/edit.png"))); // NOI18N
        jMenu2.setText("Edit");

        jMenuItem1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(0, 153, 153));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/newUser.png"))); // NOI18N
        jMenuItem1.setText("New Student");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jMenuItem3.setForeground(new java.awt.Color(0, 153, 153));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/update.png"))); // NOI18N
        jMenuItem3.setText("Update Student info");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(0, 153, 153));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classRegister/garbage.png"))); // NOI18N
        jMenuItem2.setText("Delete Student");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
                        .addGap(46, 46, 46)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
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

    //refreshes the table
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        studentTbl.setModel(new DefaultTableModel());
        studentTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        findUsers();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        studentTbl.setModel(new DefaultTableModel());
        descendingOrder();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        studentTbl.setModel(new DefaultTableModel());
        assendingOrder();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         new NewStudent().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new UpdateStudent().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new DeleteStudent().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(ListOfStudentsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListOfStudentsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListOfStudentsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListOfStudentsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListOfStudentsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable studentTbl;
    // End of variables declaration//GEN-END:variables
}
