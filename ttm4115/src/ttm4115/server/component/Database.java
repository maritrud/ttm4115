package ttm4115.server.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private static final String USER = "root";
	private static final String PASS = "raspberry";
	private static final String DB_URL = "jdbc:mysql://localhost/lds";
	Connection conn = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println(conn);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void setStatus(int id, boolean status, String timestamp) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "UPDATE lds_unit SET status="+status+", timestamp="+timestamp+" WHERE id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}	
	}
}
