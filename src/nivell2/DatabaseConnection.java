package nivell2;

import java.sql.*;

public class DatabaseConnection {
	
	private static Connection connect=null;
	String url;
    String driver;
    String usuario;
    String password;
	
	private DatabaseConnection() {
		
		url = "jdbc:mysql://localhost:3306/floristeriapepadb";
	    driver = "com.mysql.cj.jdbc.Driver";
	    usuario = "test"; // "root";
	    password = "1234"; // "Admin&Admin";
	 
	    try{
	    	
	    	Class.forName(driver);
	    	connect = DriverManager.getConnection(url, usuario, password);
	    }
	    catch(ClassNotFoundException | SQLException e){
	    	
	    	e.printStackTrace();
	    }
	}

	public static Connection getConnect() {
		
		
		if (connect==null) {
			
			new DatabaseConnection();
		}
		return connect;
	}
	
	public static void closeConnect() {
		
		if (connect!=null) {
			
			try {
				connect.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
