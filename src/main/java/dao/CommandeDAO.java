package dao;

import java.util.Date;

import org.apache.naming.factory.TransactionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Adresse_livraison;
import model.Commande;
import model.Details_commande;
import model.Produit;
import model.Utilisateur;
import tools.Database;
//import tools.DateUtilToDateSql;

public class CommandeDAO {

	private Details_commandeDAO detailsDao;
	
	public void save(Commande obj) {
		
		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE commandes fk_utilisateur=?, date=?, total=?, fk_adresse=?, etat=?, archiver=? WHERE id=?");
				
				preparedStatement.setInt(1,obj.getFk_utilisateur());
				preparedStatement.setDate(2,obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4,obj.getFk_adresse());
				preparedStatement.setInt(5,obj.getEtat());
				preparedStatement.setInt(6,obj.getArchiver());
				
				preparedStatement.setInt(7,obj.getId());
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);

	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO commandes (fk_utilisateur, date, total, fk_adresse, etat, archiver) VALUES(?,?,?,?,?,?)");
				preparedStatement.setInt(1,obj.getFk_utilisateur());
				preparedStatement.setDate(2,obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4,obj.getFk_adresse());
				preparedStatement.setInt(5,obj.getEtat());
				preparedStatement.setInt(6,obj.getArchiver());
				
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
}

	public int saveAndReturnGeneratedId(Commande obj) {
			int newId = 0;
		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE commandes set fk_utilisateur=?, date=?, total=?, fk_adresse=?, etat=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1,obj.getFk_utilisateur());
				preparedStatement.setDate(2,obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4,obj.getFk_adresse());
				preparedStatement.setInt(5,obj.getEtat());
				preparedStatement.setInt(6,obj.getArchiver());
				
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);
				
				preparedStatement.setInt(7,obj.getId());
				
	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO commandes (idClient, dateCommande, totalCommande) VALUES(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1,obj.getFk_utilisateur());
				preparedStatement.setDate(2,obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4,obj.getFk_adresse());
				preparedStatement.setInt(5,obj.getEtat());
				preparedStatement.setInt(6,obj.getArchiver());
				
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);
				
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
	
public Commande getById(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat = preparedStatement.executeQuery();
			
			Commande u = new Commande();
			resultat.next();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Commande getByIdCommandeUserAdressAndDetails(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat = preparedStatement.executeQuery();
			
			Commande u = new Commande();
			resultat.next();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur ut = utDao.getById(u.getFk_utilisateur());
				
				u.setU(ut);
				
				Adresse_livraisonDAO adressDao = new Adresse_livraisonDAO();
				u.setAdresse(adressDao.getByIdCommande(u.getFk_adresse()));
				
				Details_commandeDAO dcDao = new Details_commandeDAO();
				Details_commande dc;
				
				ArrayList<Details_commande> details = dcDao.getAllByCommande(u.getId());
				u.setDetails(details);
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Commande getByInscritTotalAndDate(Integer id, double total, Date d) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=? AND total=? AND date=?");
			preparedStatement.setInt(1,id);
			preparedStatement.setDouble(2,total);
			
			long timeInMilliSeconds = d.getTime();
	        java.sql.Date date = new java.sql.Date(timeInMilliSeconds);
			
			preparedStatement.setDate(3, date);
			ResultSet resultat=preparedStatement.executeQuery();
			
			Commande u = new Commande();
			resultat.next();
			u.setId(resultat.getInt( "id" ));
			u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
			u.setDate(resultat.getDate( "date" ));
			u.setTotal(resultat.getDouble( "total" ));
			u.setFk_adresse(resultat.getInt( "fk_adresse" ));
			u.setEtat(resultat.getInt( "etat" ));
			u.setArchiver(resultat.getInt( "archiver" ));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Commande> getAll() {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
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

public ArrayList<Commande> getAllAndDetails() {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				detailsDao = new Details_commandeDAO();
				ArrayList<Details_commande> details = detailsDao.getAllByCommande(u.getId());
				
				u.setDetails(details);
				
				list.add(u);
			}
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Commande> getAllByClient(int idClient) {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=?");
			preparedStatement.setInt(1, idClient);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				detailsDao = new Details_commandeDAO();
				ArrayList<Details_commande> details = detailsDao.getAllByCommande(u.getId());
				
				u.setDetails(details);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Commande> getAllAndDetailsByClient(int idClient) {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=?");
			preparedStatement.setInt(1, idClient);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_utilisateur(resultat.getInt( "fk_utilisateur" ));
				u.setDate(resultat.getDate( "date" ));
				u.setTotal(resultat.getDouble( "total" ));
				u.setFk_adresse(resultat.getInt( "fk_adresse" ));
				u.setEtat(resultat.getInt( "etat" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				detailsDao = new Details_commandeDAO();
				ArrayList<Details_commande> details = detailsDao.getAllByCommande(u.getId());
				
				u.setDetails(details);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiveById(Commande c) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE commandes set archiver=? WHERE id=?");
			preparedStatement.setInt(1, c.getArchiver());
			preparedStatement.setInt(2,c.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("ARCHIVE OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("ARCHIVE NO");
    }
}

public int countTotalCommande() {
	int c= 0;
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) FROM commandes;");

			
			ResultSet resultat = preparedStatement.executeQuery();
			resultat.next();
			 c = resultat.getInt(1);
			System.out.println(c);
			return c;
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("problème");
    }
	return c;
}

public int countCommande_a24h() {
	int c = 0;
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) from commandes WHERE commandes.date > now() - INTERVAL 24 hour;");

			ResultSet resultat = preparedStatement.executeQuery();
			
			if(!resultat.next()) {
				System.out.println(0);
				return 0;
			}else {
				c = resultat.getInt(1);
				System.out.println(c);
				return c;
			}

		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("problème");
    }
	return c;
}




public void deleteById(int id) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM commandes WHERE id=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}


// transaction : valider les détails, la décrémentation du stock produit
// à voir si dans update ou non
public boolean validerCommande() {
	
	try {
		Database.connexion.setAutoCommit(false);
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	TransactionFactory tf = new TransactionFactory();
	
	return false;
}

}
