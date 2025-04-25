package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","@21Tejud");
        }
        catch (SQLException | ClassNotFoundException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE,null,e);
        }

        return con;
    }
}
