package oma.grafiikka.ot1;

import java.sql.*;

public class SqlConn {
    private String url;
    private String username;
    private String password;
    Connection connection = null;

    public SqlConn(String username, String password, int port, String dbName){
        this.username = username;
        this.password = password;
        this.url = String.format("jdbc:mysql://localhost:%d/%s", port, dbName);
    }

    public void connect() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT city FROM city";
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet resSet = prepStatement.executeQuery();
            while(resSet.next()){
                String city = resSet.getString("city");
                System.out.println(city);
            }
            prepStatement.close();
            resSet.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch (SQLException e){

        }
    }

    public void addCustomer(String pcode, String fname, String sname, String addr, String email, String pnr)
            throws SQLException{

        String sql = "INSERT INTO asiakas (postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pcode);
        preparedStatement.setString(2, fname);
        preparedStatement.setString(3, sname);
        preparedStatement.setString(4, addr);
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, pnr);

        int succesful = preparedStatement.executeUpdate();
        if(succesful < 1){
            throw new SQLException();
        }
    }

}
