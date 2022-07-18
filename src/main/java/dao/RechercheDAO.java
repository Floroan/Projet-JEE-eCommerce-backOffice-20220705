package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Recherche;
import tools.Database;

public class RechercheDAO {

	public void save(Recherche obj) {
		

		try {
		
			if(obj.getId() != 0) {
				System.out.println("le commentaire existe d�j�: UPDATE");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE recherches set fk_user=?, motcle=? , date=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1,obj.getFk_user());
				preparedStatement.setString(2,obj.getMotcle());
				preparedStatement.setDate(3,obj.getDate());
				preparedStatement.setInt(4,obj.getArchiver());
				
				preparedStatement.setInt(5,obj.getId());
				
	            preparedStatement.executeUpdate();
			}else {
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO recherches (fk_user, motcle, date, archiver) VALUES(?,?,?,?)");
				preparedStatement.setInt(1,obj.getFk_user());
				preparedStatement.setString(2,obj.getMotcle());
				preparedStatement.setDate(3,obj.getDate());
				preparedStatement.setInt(4,obj.getArchiver());
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}



public Recherche getById(int idProd, int idCli) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM recherches WHERE id=?");
			preparedStatement.setInt(1,idProd);
			preparedStatement.setInt(2,idCli);
			ResultSet resultat=preparedStatement.executeQuery();
			
			Recherche u = new Recherche();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt( "archiver" ));
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Recherche> getAll() {
	ArrayList<Recherche> list = new ArrayList<Recherche>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM recherches");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Recherche u = new Recherche();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Recherche> getAllByClient(int id) {
	ArrayList<Recherche> list = new ArrayList<Recherche>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM recherches WHERE fk_user=? ORDER BY recherches.note DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Recherche u = new Recherche();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt( "archiver" ));		

				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiverById(Recherche c) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE recherches set archiver=? WHERE id=?");
			preparedStatement.setInt(1,c.getArchiver());
			preparedStatement.setInt(2,c.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("ARCHIVED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("ARCHIVED NO");
    }
}

public void deleteById( int id) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM recherches WHERE id=?");
			preparedStatement.setInt(1,id);

			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}
	
	
}
