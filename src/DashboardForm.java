import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JFrame{
    private JButton btnRegister;
    private JLabel lblAdmin;
    private JPanel dashboardPanel;

    public DashboardForm(){
        setTitle("Dashboard");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(700,600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        boolean hasRegisteredUsers = connectToDatabase();
        if(hasRegisteredUsers){
            LoginForm loginForm = new LoginForm(this);
            User user = loginForm.user;

            if(user != null ){
                lblAdmin.setText("User: "+user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }else{
                dispose();
            }
        }else{
            RegisterForm registerForm = new RegisterForm(this);
            User user = registerForm.user;

            if(user != null){
                lblAdmin.setText("User: "+user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }else {
                dispose();
            }
        }
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(DashboardForm.this);
                User user = registerForm.user;

                if(user != null){
                    JOptionPane.showConfirmDialog(DashboardForm.this,
                            "New User: "+user.name,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    static boolean connectToDatabase(){
        boolean hasRegisteredUsers = false;

        final String MYSQL_SERVER_URL = "jdbc:mysql://localhost/";
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "anu123@GMP";

        try{
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL,USERNAME,PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS MyStore");
            statement.close();
            conn.close();

            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(200)," +
                    "email VARCHAR(200)," +
                    "phone VARCHAR(200)," +
                    "address VARCHAR(200)," +
                    "password VARCHAR(200) NOT NULL" +
                    ")";
            statement.executeUpdate(sql);

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERS");

            if(resultSet.next()){
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0){
                    hasRegisteredUsers = true;
                }
            }
            statement.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return hasRegisteredUsers;
    }

    public static void main(String[] args) {
        DashboardForm dashboardForm = new DashboardForm();
    }
}
