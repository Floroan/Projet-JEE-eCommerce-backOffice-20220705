package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import model.Commande;
import tools.Database;

public class GenericDAO {

	
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
	
	public void countRows_WithInterval(String tableName, int interval) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) from commandes WHERE " + tableName + ".date > now() - INTERVAL "+ interval +" hour;");

				
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
	
	
	
	public void recherche() {
		HashMap<String, ArrayList<String>> liste;
	}
}
