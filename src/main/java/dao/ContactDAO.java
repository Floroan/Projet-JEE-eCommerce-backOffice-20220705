package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Contact;
import model.Produit;
import model.Utilisateur;
import tools.Database;

public class ContactDAO {

	
	public void save(Contact obj) {
		
		try {
		
			if(obj.getId() != 0) {
				System.out.println("le commentaire existe d�j�: UPDATE");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE contacts set fk_user=?, sujet=? , email=?, message=?, date=?, etat=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1,obj.getFk_user());
				preparedStatement.setString(2,obj.getSujet());
				preparedStatement.setString(3, obj.getEmail());
				preparedStatement.setString(4, obj.getMessage());
				preparedStatement.setDate(5, obj.getDate());
				preparedStatement.setInt(6,obj.getEtat());
				preparedStatement.setInt(7,obj.getArchiver());
				
				preparedStatement.setInt(8,obj.getId());
				
				
	            preparedStatement.executeUpdate();
			}else {
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO contacts (fk_user, sujet, email, message, date, etat, archiver) VALUES(?,?,?,?,?,?,?)");
				preparedStatement.setInt(1,obj.getFk_user());
				preparedStatement.setString(2,obj.getSujet());
				preparedStatement.setString(3, obj.getEmail());
				preparedStatement.setString(4, obj.getMessage());
				preparedStatement.setDate(5, obj.getDate());
				preparedStatement.setInt(6,obj.getEtat());
				preparedStatement.setInt(7,obj.getArchiver());
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

	public Contact getById(int id) {
		try {
		
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts WHERE id=?");
				preparedStatement.setInt(1,id);
				
				ResultSet resultat=preparedStatement.executeQuery();
				UtilisateurDAO utilDao = new UtilisateurDAO();
				
				Contact u = new Contact();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setFk_user(resultat.getInt( "fk_user" ));
					u.setSujet(resultat.getString("sujet"));
					u.setEmail(resultat.getString("email"));
					u.setMessage(resultat.getString("message"));
					u.setDate(resultat.getDate("date"));
					u.setEtat(resultat.getInt( "etat" ));
					u.setArchiver(resultat.getInt( "archiver" ));
					
					
					Utilisateur c;
					c = utilDao.getById(u.getFk_user());
					u.setU(c);
				}
				return u;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}

public Contact getByIdClient(int idCli) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts WHERE fk_user=?");
			preparedStatement.setInt(1,idCli);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Contact u = new Contact();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Contact getByIdClientAndEtat(int idCli, int statut) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts WHERE fk_user=? AND etat=?");
			preparedStatement.setInt(1,idCli);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Contact u = new Contact();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Contact> getAll() {
	ArrayList<Contact> list = new ArrayList<Contact>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts");
			
			ResultSet resultat=preparedStatement.executeQuery();
			UtilisateurDAO utilDao = new UtilisateurDAO();
			
			while(resultat.next()) {
				Contact u = new Contact();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				Utilisateur c;
				c = utilDao.getById(u.getFk_user());
				u.setU(c);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Contact> getAllByEtat(int etat) {
	ArrayList<Contact> list = new ArrayList<Contact>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts WHERE etat=?");
			preparedStatement.setInt(1, etat);
			ResultSet resultat=preparedStatement.executeQuery();
			UtilisateurDAO utilDao = new UtilisateurDAO();
			
			while(resultat.next()) {
				Contact u = new Contact();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				Utilisateur c;
				c = utilDao.getById(u.getFk_user());
				u.setU(c);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Contact> getAllByClient(int id) {
	ArrayList<Contact> list = new ArrayList<Contact>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts  WHERE fk_user=? ORDER BY contacts.date DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Contact u = new Contact();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));

				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Contact> getAllByClientAndEtat(int idCli, int etat) {
	ArrayList<Contact> list = new ArrayList<Contact>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts  WHERE fk_user=? AND etat=? ORDER BY contacts.date DESC");
			preparedStatement.setInt(1, idCli);
			preparedStatement.setInt(2, etat);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Contact u = new Contact();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));

				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiverById(Contact c) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE contacts set archiver=? WHERE id=?");
			preparedStatement.setInt(1,c.getArchiver());
			preparedStatement.setInt(2,c.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("ARCHIVED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("ARCHIVED NO");
    }
}

public void deleteById(int fk_prod, int fk_user) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM contacts WHERE fk_prod=? AND fk_user=?");
			preparedStatement.setInt(1,fk_prod);
			preparedStatement.setInt(2,fk_user);
			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}



public ArrayList<Contact> getByLike(String crit) {
	ArrayList<Contact> list = new ArrayList<Contact>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM contacts WHERE sujet LIKE ? OR message LIKE ?");
			preparedStatement.setString(1, "%"+crit+"%");
			preparedStatement.setString(2, "%"+crit+"%");
			ResultSet resultat=preparedStatement.executeQuery();
			UtilisateurDAO utilDao = new UtilisateurDAO();
			
			while(resultat.next()) {
				Contact u = new Contact();
				u.setId(resultat.getInt( "id" ));
				u.setFk_user(resultat.getInt( "fk_user" ));
				u.setSujet(resultat.getString("sujet"));
				u.setEmail(resultat.getString("email"));
				u.setMessage(resultat.getString("message"));
				u.setDate(resultat.getDate("date"));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				Utilisateur c;
				c = utilDao.getById(u.getFk_user());
				u.setU(c);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

}
