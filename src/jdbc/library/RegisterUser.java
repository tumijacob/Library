/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Tumi
 */
public class RegisterUser extends javax.swing.JFrame {
    
    String DATABASE_URL = "jdbc:mysql://localhost/books";
    String USERNAME = "deitel";
    String PASSWORD = "deitel";
    
    Connection connection;
    Statement statement;
    
    static String firstName, lastName, email,phoneNumber;
    static String  username, password, confirmPassword, userLevelName;
    static int userId;
    boolean newUser = true;//is true if we are registering new user not editing
   
    /**
     * Creates new form RegisterUser
     */
    public RegisterUser() {
        initComponents();
        cbUserLevel.setModel(new DefaultComboBoxModel(LoginPage.userLevels));
    }

    public RegisterUser(int userId, String firstName, String lastName, String email, 
            String phoneNumber, String username, String password,String userLevel) {
        initComponents();
        cbUserLevel.setModel(new DefaultComboBoxModel(LoginPage.userLevels));
        this.setTitle("Edit user!");
        this.txtEmail.setText(email);
        this.txtFirstName.setText(firstName);
        this.txtLastName.setText(lastName);
        this.txtPassword.setText(password);
        this.txtPhoneNumber.setText(phoneNumber);
        this.txtUsername.setText(username);
        this.cbUserLevel.setSelectedItem(userLevel);
        RegisterUser.userId = userId;
        this.newUser = false;
        
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btCancel = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbUserLevel = new javax.swing.JComboBox();
        txtConfimPassword = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register new user");

        jLabel1.setText("First name:");

        jLabel2.setText("Last name:");

        jLabel3.setText("Email address:");

        jLabel4.setText("Phone number:");

        jLabel5.setText("Username:");

        jLabel6.setText("Password:");

        jLabel7.setText("Confirm password:");

        btCancel.setMnemonic('C');
        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btSave.setMnemonic('S');
        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel8.setText("User level ID:");

        cbUserLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFirstName)
                                .addComponent(txtEmail)
                                .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtLastName)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtConfimPassword)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSave)
                        .addGap(32, 32, 32)
                        .addComponent(btCancel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(cbUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfimPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btSave)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        
        if(isInputsValid()){
            String query = "INSERT INTO Users(FirstName, LastName, Email, "
                    + "PhoneNumber, Username, Password, UserlevelName) "
                    + "VALUES('" + firstName + "', '" + lastName + "', '" + 
                    email + "', '" + phoneNumber + "', '" + username + "', '"
                    + LoginPage.encrypPassword(password) + "', '" + userLevelName + "')";
            String updateQuery = "UPDATE Users SET FirstName ='" + firstName + "',"
                    + " LastName ='" + lastName + "', "
                    + "Email = '" + email + "' , "
                    + "PhoneNumber = '" + phoneNumber + "', "
                    + "Username ='" + username + "' , "
                    + "Password ='" + LoginPage.encrypPassword(password) + "' , "
                    + "UserLevelName ='" + userLevelName + "' WHERE UserID = " + userId;
            connect();
            try {
                if(newUser){
                    int result = statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(this,result + " row(s) affected.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
                }else{
                    int resultUpdate = statement.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(this,resultUpdate + " row(s) updated.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
                }
                
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(this, sqlException.getMessage(),
                        "Error!", JOptionPane.WARNING_MESSAGE);
                close();
            }finally{
                close();
            }
       }
       
    }//GEN-LAST:event_btSaveActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    public void initializeVariables(){
        
        firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        email = txtEmail.getText();
        phoneNumber = txtPhoneNumber.getText();
        username = txtUsername.getText();
        password = new String(txtPassword.getPassword());
        confirmPassword = new String(txtConfimPassword.getPassword());
        userLevelName = cbUserLevel.getSelectedItem().toString();
    }
    
    public boolean isInputsValid(){
        
        initializeVariables();
        boolean status = false;
        
       if(!firstName.matches("[^\\s]([a-zA-Z \\s]{2,25})")){
            JOptionPane.showMessageDialog(this, "Invalid first "
                    + "name. \nName must be 3 to 25 characters long and "
                    + "\ncontains only letters and white space(s).\nAnd may not start "
                    + "with space.  ", "Warning!", JOptionPane.WARNING_MESSAGE);
            
        }
        else if(!lastName.matches("[^\\s]([a-zA-Z \\s -]{2,25})")){
            JOptionPane.showMessageDialog(this, "Invalid last "
                    + "name. \nName must be 3 to 25 characters long and "
                    + "\ncontains only letters and space(s).\nAnd may not start "
                    + "with white space.", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(!email.matches("[^\\d]([\\w \\$ \\.]{1,})(@)([\\w \\.]{2,})")){
            JOptionPane.showMessageDialog(this, "Invalid email address"
                    + "\nEmail Must not start with number, and may contains only "
                    + "letters, numbers and '@','.','$' and '_'."
                    + "\ncontains only letters and space(s).\nAnd may not start "
                    + "with white space.", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(!phoneNumber.matches("[^\\s]([0-9 \\s -]{9,15})")){
            JOptionPane.showMessageDialog(this, "Invalid phone number."
                    + "\nPhone number must contains numbers or white space(s) only "
                    + "And may not start with white space"
                    + "with space.", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(!username.matches("[\\w \\$ \\.]{2,15}")){
            JOptionPane.showMessageDialog(this, "Invalid username."
                    + "\nUsername must be 3 to 15 characters long and "
                    + "\ncontains only letters, numbers and '$', and '_'.",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(!password.matches("[\\w \\$ \\.]{3,15}")){
            System.out.println(password);
            JOptionPane.showMessageDialog(this, "Invalid password."
                    + "\npassword must be 4 to 15 characters long and "
                    + "\ncontains only letters, numbers and '$', and '_'.",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(userLevelName.equals("Select user level")){
            JOptionPane.showMessageDialog(this, "Select user level.",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        else if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Passwords do not match!",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }else{
            status = true;
        }
        return status;
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(this,"Error: " + sqlException.getMessage());
        }
    }
    
    public void close(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(this,"Error: " + sqlException.getMessage());
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
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterUser().setVisible(true);
                new RegisterUser(userId, firstName, lastName, 
                        email, phoneNumber, username, password, username).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cbUserLevel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPasswordField txtConfimPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
