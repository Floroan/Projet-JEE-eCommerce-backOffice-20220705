package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Categorie;
import model.Sous_categorie;
import tools.Database;

public class Sous_categorieDAO {
	
	private CategorieDAO catDao = new CategorieDAO();

	public int save(Sous_categorie obj) {
		int newId = 0;
			try {
				
				if(obj.getId() != 0) {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE sous_categories set titre=?, fk_categorie=?, archiver=? WHERE id=?");
					preparedStatement.setString(1,obj.getTitre());
					preparedStatement.setInt(2,obj.getFk_categorie());
					preparedStatement.setInt(3,obj.getArchiver());
					
					preparedStatement.setInt(4,obj.getId());
		            preparedStatement.executeUpdate();
				}else {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO sous_categories (titre, fk_categorie, archiver) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1,obj.getTitre());
					preparedStatement.setInt(2,obj.getFk_categorie());
					preparedStatement.setInt(3,obj.getArchiver());
					
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
		
	public Sous_categorie getById(int id) {
		try {
		
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM sous_categories WHERE id=?");
				preparedStatement.setInt(1,id);
				
				ResultSet resultat=preparedStatement.executeQuery();
				
				Sous_categorie u = new Sous_categorie();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setTitre(resultat.getString( "titre" ));
					u.setFk_categorie(resultat.getInt( "fk_categorie" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					
					u.setC(catDao.getById(u.getFk_categorie()));
				}
				return u;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public ArrayList<Sous_categorie> getAll() {
		ArrayList<Sous_categorie> list = new ArrayList<Sous_categorie>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM sous_categories");
				
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Sous_categorie u = new Sous_categorie();
					u.setId(resultat.getInt( "id" ));
					u.setTitre(resultat.getString( "titre" ));
					u.setFk_categorie(resultat.getInt( "fk_categorie" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					
					u.setC(catDao.getById(u.getFk_categorie()));
					
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public ArrayList<Sous_categorie> getAllByCategorie(Categorie c) {
		ArrayList<Sous_categorie> list = new ArrayList<Sous_categorie>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM sous_categories wHERE fk_categorie=?");
				preparedStatement.setInt(1,c.getId());
				ResultSet resultat= preparedStatement.executeQuery();

				while(resultat.next()) {
					Sous_categorie u = new Sous_categorie();
					u.setId(resultat.getInt( "id" ));
					u.setTitre(resultat.getString( "titre" ));
					u.setArchiver(resultat.getInt( "fk_categorie" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void archiverById(Sous_categorie c) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE sous_categories set archiver=? WHERE id=?");
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
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM sous_categories WHERE id=?");
				preparedStatement.setInt(1,id);
				
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
