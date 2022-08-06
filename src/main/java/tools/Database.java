package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static String dburl = "jdbc:mysql://localhost:8889/projetdeux_tangflo";
	private static String dbuser = "root";
	private static String dbpass = "root";
	public static Connection connexion = null;

//	public static void Connect() {
	public static void Connect() /*throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException*/ {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // deprecate mais attendu dans certaines versions
																	// d'IDE
			// Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// // .newInstance()
			System.out.println("Driver OK");

			connexion = DriverManager.getConnection(dburl, dbuser, dbpass);

		} catch (InstantiationException e) {
//			e.printStackTrace();
			System.out.println("PROBLEME MYSQL DRIVER : InstantiationException");
//			throw new InstantiationException("PROBLEME MYSQL DRIVER : InstantiationException");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("PROBLEME MYSQL DRIVER : IllegalAccessException");
//			throw new IllegalAccessException("PROBLEME MYSQL DRIVER : IllegalAccessException");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("PROBLEME MYSQL DRIVER: ClassNotFoundException");
//			throw new ClassNotFoundException("PROBLEME MYSQL DRIVER: ClassNotFoundException");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("PROBLEME MYSQL DRIVER : SQLException");
//			throw new SQLException("PROBLEME MYSQL DRIVER : SQLException");
		} 

	}
}
