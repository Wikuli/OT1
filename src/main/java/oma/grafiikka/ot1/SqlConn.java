package oma.grafiikka.ot1;

import java.sql.*;

public class SqlConn {
    private String url = "jdbc:mysql://localhost:3306/sakila";
    private String username;
    private String password;

    public SqlConn(String username, String password){
        this.username = username;
        this.password = password;
    }



    public void connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM city";
            ResultSet resSet = statement.executeQuery(sql);

            while (resSet.next()) {
                int id = resSet.getInt("city_id");
                String city = resSet.getString("city");
                System.out.println("City: " + city + "  ID: " + id);
            }

            resSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
