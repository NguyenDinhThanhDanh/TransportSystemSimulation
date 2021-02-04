package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDb implements Database{

  private static Connection conn ;

    private  MysqlDb(){}

    /**
     * Creation à la database MySQL
     * @return Connection , connexion à la base de donnée
     */
    public static Connection getConnection(){
        if( conn == null){
            try {
                String uri = "jdbc:mysql://0.0.0.0:3306/clientsdb";
                String user="root";
                String pwd="rootpwd";

                conn = DriverManager.getConnection(uri,user,pwd);

                // Do something with the Connection
                System.out.println("Connection a la base de données réussie");

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return conn;
    }
}
