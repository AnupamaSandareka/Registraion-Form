public class User {
    public String name;
    public String email;
    public String phone;
    public String address;
    public String password;
}

/*
 public User user;
    private User getAuthenticatedUser(String email, String password){
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "anu123@GMP";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.name = resultSet.getString("email");
                user.name = resultSet.getString("phone");
                user.name = resultSet.getString("address");
                user.name = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if(user != null){
            System.out.println("Successful Authentication of: "+user.name);
            System.out.println("         Email: "+user.email);
            System.out.println("         Password: "+user.password);
            System.out.println("         Phone: "+user.phone);
        }else{
            System.out.println("Authentication cancelled");
        }
    }
 */