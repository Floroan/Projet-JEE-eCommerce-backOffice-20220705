package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Favori;
import model.Produit;
import model.Utilisateur;
import tools.Database;

public class FavoriDAO {
	
	
	public int save(Favori obj) {
		int newId = 0;
			try {
				
				if(obj.getId() != 0) {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE favoris set fk_prod=?, fk_user=?, archiver=? WHERE id=?");
					preparedStatement.setInt(1,obj.getFk_prod());
					preparedStatement.setInt(2,obj.getFk_user());
					preparedStatement.setInt(3,obj.getArchiver());
					
					preparedStatement.setInt(4,obj.getId());
		            preparedStatement.executeUpdate();
				}else {
					PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO favoris (fk_prod, fk_user, archiver) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1,obj.getFk_prod());
					preparedStatement.setInt(2,obj.getFk_user());
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
	
	
	
	public Favori getById(int id) {
		try {
		
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM favoris WHERE id=?");
				preparedStatement.setInt(1,id);
				
				ResultSet resultat=preparedStatement.executeQuery();
				
				Favori u = new Favori();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setFk_prod(resultat.getInt( "fk_prod" ));
					u.setFk_user(resultat.getInt( "fk_user" ));					
					u.setArchiver(resultat.getInt( "archiver" ));
					
				}
				return u;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}

	
	
	public ArrayList<Favori> getAll() {
		ArrayList<Favori> list = new ArrayList<Favori>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM favoris");
				
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Favori u = new Favori();
					u.setId(resultat.getInt( "id" ));
					u.setFk_prod(resultat.getInt( "fk_prod" ));
					u.setFk_user(resultat.getInt( "fk_user" ));					
					u.setArchiver(resultat.getInt( "archiver" ));
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public ArrayList<Favori> getAllByClient(Utilisateur ut) {
		ArrayList<Favori> list = new ArrayList<Favori>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM favoris WHERE fk_user=?");
				preparedStatement.setInt(1, ut.getId());
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Favori u = new Favori();
					u.setId(resultat.getInt( "id" ));
					u.setFk_prod(resultat.getInt( "fk_prod" ));
					u.setFk_user(resultat.getInt( "fk_user" ));					
					u.setArchiver(resultat.getInt( "archiver" ));
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public ArrayList<Favori> getAllByProduit(Produit p) {
		ArrayList<Favori> list = new ArrayList<Favori>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM favoris WHERE fk_prod=?");
				preparedStatement.setInt(1, p.getId());
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Favori u = new Favori();
					u.setId(resultat.getInt( "id" ));
					u.setFk_prod(resultat.getInt( "fk_prod" ));
					u.setFk_user(resultat.getInt( "fk_user" ));					
					u.setArchiver(resultat.getInt( "archiver" ));
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void archiverById(Favori c) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE favoris set archiver=? WHERE id=?");
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
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM favoris WHERE id=?");
				preparedStatement.setInt(1,id);
				
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
