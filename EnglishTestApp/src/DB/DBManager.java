package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBManager {
	private final static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String protocol = "jdbc:derby:";
	
	static {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Driver is loaded.");
			
			Connection conn = null;
			Properties props = new Properties();
			props.put("user", "admin");
			props.put("password", "1q2w3e4r");
			
			conn = DriverManager.getConnection(protocol + "derbyDB;create=true", props);
			System.out.println("Connection is established.");
			
			Statement stmt = conn.createStatement();
			
//			stmt.execute("create table users(ID ")
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
