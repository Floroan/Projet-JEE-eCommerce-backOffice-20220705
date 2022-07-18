package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Commande;
import model.Image;
import tools.Database;

public class ImageDAO {

	public void save(Image c) {
		
		try {
			
			if(c.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE images set fk_produit=?, url=?, archiver=? WHERE id=?");
				

				preparedStatement.setInt(1,c.getFk_produit());
				preparedStatement.setString(1,c.getUrl());
				preparedStatement.setInt(3,c.getArchiver());
				
				preparedStatement.setInt(4,c.getId());


	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO images (fk_produit, url, archiver) VALUES(?,?,?)");
				preparedStatement.setInt(1,c.getFk_produit());
				preparedStatement.setString(1,c.getUrl());
				preparedStatement.setInt(3,c.getArchiver());
			
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
}
	
	public Image getOneByProduit(int id) {
		
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM images WHERE fk_produit=?");
				preparedStatement.setInt(1, id);
				ResultSet resultat=preparedStatement.executeQuery();
				
				Image u = new Image();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setFk_produit(resultat.getInt( "fk_produit" ));
					u.setUrl(resultat.getString( "url" ));
					u.setArchiver(resultat.getInt( "archiver" ));
				}
				return u;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	public ArrayList<Image> getAllByProduit(int id) {
		ArrayList<Image> list = new ArrayList<Image>();
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM images WHERE fk_produit=?");
				preparedStatement.setInt(1, id);
				ResultSet resultat=preparedStatement.executeQuery();

				while(resultat.next()) {
					Image u = new Image();
					u.setId(resultat.getInt( "id" ));
					u.setFk_produit(resultat.getInt( "fk_produit" ));
					u.setUrl(resultat.getString( "url" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					
					list.add(u);
				}
				
				return list;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	public void archiverById(Image c) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE images set archiver=? WHERE id=?");
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
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM images WHERE id=?");
				preparedStatement.setInt(1, id);
				
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("DELETED NO");
	    }
	}
}
