package frgp.utn.edu.ar.Util;

import java.sql.*;

public class Conexion {
	private static Connection cn = null;
	
	public static Connection getConnection() {
		if (cn != null) {
			return cn;
		} else {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/TP_Integrador";
				String user = "root";
				String password = "root";
				
				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				
			} catch (ClassNotFoundException e) {
			    e.printStackTrace();
			} catch (SQLException e) {
			    e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Conexión: " + cn);
			return cn;
		}
	}
}
