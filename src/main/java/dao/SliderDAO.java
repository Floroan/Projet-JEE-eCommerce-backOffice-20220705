package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import model.Slide;
import tools.Database;

public class SliderDAO {
	
	
	public void save(Slide obj) {
		
		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE slider set titre=?, description=?, image=?, titreBouton=?, url=?, archiver=? WHERE id=?");
				preparedStatement.setString(1,obj.getTitre());
				preparedStatement.setString(2, obj.getDescription());
				preparedStatement.setString(3, obj.getImage());
				preparedStatement.setString(4, obj.getTitreBouton());
				preparedStatement.setString(5, obj.getUrl());
				preparedStatement.setInt(6, obj.getArchiver()); 
				
				preparedStatement.setInt(7,obj.getId());
				
	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO slider (titre, description, image, titreBouton, url, archiver) VALUES(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1,obj.getTitre());
				preparedStatement.setString(2, obj.getDescription());
				preparedStatement.setString(3, obj.getImage());
				preparedStatement.setString(4, obj.getTitreBouton());
				preparedStatement.setString(5, obj.getUrl());
				preparedStatement.setInt(6, obj.getArchiver()); 
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Slide getOneById(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM slider WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Slide u = new Slide();
			resultat.next();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString( "titre" ));
				u.setDescription(resultat.getString( "description" ));
				u.setImage(resultat.getString("image"));
				u.setTitreBouton(resultat.getString( "titreBouton" ));
				u.setUrl(resultat.getString( "url" ));
				u.setArchiver(resultat.getInt("archiver"));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Slide getByStatut(Slide sly) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM slider WHERE id=? AND archiver=?");
			preparedStatement.setInt(1,sly.getId());
			preparedStatement.setInt(2, sly.getArchiver());
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Slide u = new Slide();
			resultat.next();
			u.setId(resultat.getInt( "id" ));
			u.setTitre(resultat.getString( "titre" ));
			u.setDescription(resultat.getString( "description" ));
			u.setImage(resultat.getString("image"));
			u.setTitreBouton(resultat.getString( "titreBouton" ));
			u.setUrl(resultat.getString( "url" ));
			u.setArchiver(resultat.getInt("archiver"));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Slide> getAll() {
	ArrayList<Slide> list = new ArrayList<Slide>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM slider");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Slide u = new Slide();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString( "titre" ));
				u.setDescription(resultat.getString( "description" ));
				u.setImage(resultat.getString("image"));
				u.setTitreBouton(resultat.getString( "titreBouton" ));
				u.setUrl(resultat.getString( "url" ));
				u.setArchiver(resultat.getInt("archiver"));
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Slide> getAllByStatutArchiver(int statut) {
	ArrayList<Slide> list = new ArrayList<Slide>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM slider WHERE slider.archiver=?");
			preparedStatement.setInt(1, statut);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Slide u = new Slide();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString( "titre" ));
				u.setDescription(resultat.getString( "description" ));
				u.setImage(resultat.getString("image"));
				u.setTitreBouton(resultat.getString( "titreBouton" ));
				u.setUrl(resultat.getString( "url" ));
				u.setArchiver(resultat.getInt("archiver"));
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiveById(Slide sly) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE slider SET archiver=? WHERE id=?");
			preparedStatement.setInt(1, sly.getArchiver());
			preparedStatement.setInt(2, sly.getId());
			preparedStatement.executeUpdate();
			
			System.out.println("archive OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("archive NO");
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM slider WHERE id=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}
}

