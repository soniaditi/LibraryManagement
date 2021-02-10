package LibraryManagment;

import java.sql.*;


public class DbmsConnection {
	String url; //"jdbc:mysql://localhost:3306/books" ;
	String username; //"root";
	String password; //"8237446759";
	
	

	public DbmsConnection(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con= DriverManager.getConnection(url,username,password);
		System.out.println("Connection established successfully!!");
		return con;
	}
	
	public void closeConnection(Connection con, Statement st) throws SQLException {
		st.close();
		con.close();
	}
}
