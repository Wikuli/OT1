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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deconnect(){
        try{
            connection.close();
        }
        catch (SQLException e){

        }
    }
}
