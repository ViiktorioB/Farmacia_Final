import java.sql.*;
public class BBDD {

	private Connection con;
	private Statement st;
	
	public void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
		}
		
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Farmacia","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
		}
		
		
		st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
		}
	}

	public ResultSet loadSelect(String query) {

		ResultSet rs;
		rs = null;
		
		try {
			rs= st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error a BBDD .load.Class"+ e.getMessage());
		}
		
		return rs;
	}
	
	public void updateDoctor(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error a BBDD .exeUpdate.Class"+ e.getMessage());
		}
	}
	
	 public Statement getStatement() throws SQLException {
	        if (con != null) {
	            return con.createStatement();
	        } else {
	            throw new SQLException("No se ha establecido una conexión a la base de datos.");
	        }
	    }
	 
	 public void executeInsert(String query) {
	        try {
	            st.execute(query, Statement.RETURN_GENERATED_KEYS);
	        } catch (SQLException e) {
	            System.out.print("Error en BBDD.executeInsert(): " + e.getMessage());
	        }
	    }

}

