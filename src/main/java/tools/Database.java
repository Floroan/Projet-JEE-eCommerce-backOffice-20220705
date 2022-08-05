package tools;

import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.http.HttpServletRequest;

public class Database {
	private static String dburl="jdbc:mysql://localhost:3306/projetdeux_tangflo";
	private static String dbuser="root";
	private static String dbpass="";
	public static Connection connexion = null;
	
	public static void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // deprecate mais attendu dans certaines versions d'IDE
			System.out.println("Driver OK");
			connexion = DriverManager.getConnection(dburl,dbuser,dbpass);
				
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("PROBLEME MYSQL DRIVER");
        }
	}
	
	
	public static void Connect(HttpServletRequest r) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // deprecate mais attendu dans certaines versions d'IDE
			System.out.println("Driver OK");
			connexion = DriverManager.getConnection(dburl,dbuser,dbpass);
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("PROBLEME MYSQL DRIVER");
        }
	}
}

