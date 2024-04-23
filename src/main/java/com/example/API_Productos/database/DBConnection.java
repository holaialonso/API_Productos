package database;

import com.example.API_Productos.database.SchemaDB;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static DBConnection connection = null;

    public static void newConnection() {

        String url=String.format("jdbc:mysql://%s/%s", SchemaDB.SERVER, SchemaDB.DB_NAME);
        String user = SchemaDB.USER;
        String pass= SchemaDB.PASSWORD;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (DBConnection) DriverManager.getConnection(url, user, pass);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }


    public static DBConnection getConnection(){

        if(connection==null){
            newConnection();
        }

        return connection;
    }
}