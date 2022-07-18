package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Categorie;
import tools.Database;

public class CategorieDAO {
	
	public int save(Categorie obj) {
		int newId = 0;
			try {
				
				if(obj.getId() != 0) {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE categories set titre=?, archiver=? WHERE id=?");
					preparedStatement.setString(1,obj.getTitre());
					preparedStatement.setInt(2,obj.getArchiver());
					
					preparedStatement.setInt(3,obj.getId());
		            preparedStatement.executeUpdate();
				}else {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO categories (titre, archiver) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1,obj.getTitre());
					preparedStatement.setInt(2,obj.getArchiver());
					
		            preparedStatement.executeUpdate();
		            
		            ResultSet rs = preparedStatement.getGeneratedKeys();
		            rs.next();
		            newId = rs.getInt(1);
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
			
			return newId;
	}
	
	
	
	public Categorie getById(int id) {
		try {
		
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM categories WHERE id=?");
				preparedStatement.setInt(1,id);
				
				ResultSet resultat=preparedStatement.executeQuery();
				
				Categorie u = new Categorie();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setTitre(resultat.getString( "titre" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					
				}
				return u;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}

	
	
	public ArrayList<Categorie> getAll() {
		ArrayList<Categorie> list = new ArrayList<Categorie>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM categories");
				
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Categorie u = new Categorie();
					u.setId(resultat.getInt( "id" ));
					u.setTitre(resultat.getString( "titre" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void archiverById(Categorie c) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE categories set archiver=? WHERE id=?");
				preparedStatement.setInt(1,c.getArchiver());
				preparedStatement.setInt(2,c.getId());
				
				preparedStatement.executeUpdate();
				
				System.out.println("ARCHIVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("ARCHIVED NO");
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM categories WHERE id=?");
				preparedStatement.setInt(1,id);
				
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
