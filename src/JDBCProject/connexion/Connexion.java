package JDBCProject.connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    static Connection conn=null;
    public static Connection getConn() throws SQLException {
        if(conn == null){
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_java","root","");
            
            
        }
        return conn;
    }
}
