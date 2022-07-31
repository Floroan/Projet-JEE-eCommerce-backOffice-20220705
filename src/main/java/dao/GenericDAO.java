package dao;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.jdt.internal.compiler.lookup.MethodScope;

import model.Commande;
import tools.Constantes;
import tools.Database;

public class GenericDAO<T> {

	private T t;
	
	public GenericDAO() {

	}
	public GenericDAO(T t) {
		super();
		this.t = t;
	}
	
	public void archiveById(int id, int statut, String tableName) {

		try {
			Commande c = null; c.getClass().getSimpleName();
			Object o = null; o.getClass().getSimpleName();
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE " + tableName + " set archiver=? WHERE id=?");
				preparedStatement.setInt(1, c.getArchiver());
				preparedStatement.setInt(2,c.getId());
				
				preparedStatement.executeUpdate();
				
				System.out.println("ARCHIVE OK");
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("ARCHIVE NO");
	    }
	}
	
	
	public int countRows(String tableName) {
		
		try {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) FROM "+ tableName + " WHERE archiver=0;");
				ResultSet resultat = preparedStatement.executeQuery();
				resultat.next();
				int c = resultat.getInt(1);
				System.out.println(c);
				return c;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("problème");
	    	return 0;
	    }
	}
	
	public void countObjectRows_WithInterval(String tableName, int interval) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) from "+ tableName + " WHERE " + tableName + ".date > NOW() - INTERVAL "+ interval +" hour;");

				
				ResultSet resultat = preparedStatement.executeQuery();
				
				if(!resultat.next()) {
					System.out.println(0);
				}else {
					int c = resultat.getInt(1);
					System.out.println(c);
				}
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("problème");
	    }
	}
	
	public void resultObject_WithIntervalDays(String tableName, int interval) {
		//Object o = new Object();
		System.out.println(t.getClass().getSimpleName());
		
		Method[] mts = t.getClass().getMethods();
		
		TreeSet<String> attrs = new TreeSet<>();
		TreeSet<String> orderedCols = new TreeSet<String>();
		orderedCols = this.getColumnsByOrder("commandes");
		System.out.println("ordered cols: " + orderedCols);
		
		for(Method m : mts) {
			if(m.getName().startsWith("set")) {
				//System.out.println(m.getName());
				attrs.add(m.getName().substring(3));
			}	
		}
		
		Iterator<String> itAttrs = attrs.iterator();
		Iterator<String> itCols = attrs.iterator();
		TreeSet<String> lastRamparts = new TreeSet<>();
		while(itAttrs.hasNext() && itCols.hasNext()) {
			String a = itAttrs.next();
			String c = itCols.next();
			if(a.equalsIgnoreCase(c)){
				lastRamparts.add(a);
			}
		}
		System.out.println("last ramparts: " + lastRamparts);
		
//		try {
//			
//				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * from "+ tableName + " WHERE " + tableName + ".date >= (NOW() - INTERVAL "+ interval +" DAY);");
//
//				ResultSet resultat = preparedStatement.executeQuery();
//				
//				resultat.getObject("");
//			
//		} catch (Exception ex) {
//	    	ex.printStackTrace();
//	    	System.out.println("problème");
//	    }
		
	}


	public TreeSet<String> getColumnsByOrder(String tableName){
		Database.Connect();
		TreeSet<String> list = new TreeSet<String>();
		
		try {
		
			PreparedStatement preparedStatement = 
					Database.connexion.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + Constantes.databaseName + "' AND TABLE_NAME = '" + tableName + "'; ");

			ResultSet resultat = preparedStatement.executeQuery();
			System.out.println("result: "  + resultat.toString());
			while(resultat.next()) {
			String col = resultat.getString("COLUMN_NAME");
			// restrictions
			if(!col.contains("MDP") || !col.contains("motdepasse")) {
			list.add(col);
			}
			}
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("problème");
    }
		return list;
	}
	
	// added 29/07/2022 19:22
	public ArrayList<String> getOneColumnByTableDesired(String columnName, String tableName) {
		Database.Connect();
		ArrayList<String> list = new ArrayList<String>();
		try {
		
			PreparedStatement preparedStatement = 
					Database.connexion.prepareStatement("SELECT ? FROM ?; ");
			preparedStatement.setString(1, columnName);
			preparedStatement.setString(2, tableName);
			ResultSet resultat = preparedStatement.executeQuery();
			
			
			while(resultat.next()) {
			String col = resultat.getString(columnName);
			// restrictions
			list.add(col);			
			}
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("problème");
    }
		return list;
	}
	
	public void moultiRecherche(String ...TableName) {
		
		// SELECT table_name FROM information_schema.tables WHERE table_type = 'base table' AND table_schema='projetdeux_tangflo';
		
		
		//String q = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'"+ nomTable + "';";
		
		HashMap<String, ArrayList<String>> liste;
	}
}
