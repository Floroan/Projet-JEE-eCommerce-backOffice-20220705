package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Commentaire;
import model.Details_commande;
import model.Produit;
import tools.Database;

public class Details_commandeDAO {

	public void save(Details_commande obj) {
		
		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE details_commande set fk_commande=?, fk_produit=?, qte=?, prix=? archiver=? WHERE id=?");
				preparedStatement.setInt(1,obj.getFk_commande());
				preparedStatement.setInt(2, obj.getFk_produit());
				preparedStatement.setInt(3, obj.getQte());
				preparedStatement.setDouble(4, obj.getPrix());
				preparedStatement.setInt(5, obj.getArchiver());
				
				preparedStatement.setInt(6,obj.getId());
				
	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO details_commande (fk_commande, fk_produit, qte, prix, archiver) VALUES(?,?,?,?,?)");
				preparedStatement.setInt(1,obj.getFk_commande());
				preparedStatement.setInt(2, obj.getFk_produit());
				preparedStatement.setInt(3, obj.getQte());
				preparedStatement.setDouble(4, obj.getPrix());
				preparedStatement.setInt(5, obj.getArchiver());
				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Details_commande getById(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Details_commande u = new Details_commande();
			resultat.next();
			u.setId(resultat.getInt( "id" ));
			u.setFk_commande(resultat.getInt( "fk_commande" ));
			u.setFk_produit(resultat.getInt( "fk_produit" ));
			u.setQte(resultat.getInt( "qte" ));
			u.setPrix(resultat.getDouble( "prix" ));
			u.setArchiver(resultat.getInt( "archiver" ));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Details_commande getByIdCommande(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Details_commande u = new Details_commande();
			resultat.next();
			u.setId(resultat.getInt( "id" ));
			u.setFk_commande(resultat.getInt( "fk_commande" ));
			u.setFk_produit(resultat.getInt( "fk_produit" ));
			u.setQte(resultat.getInt( "qte" ));
			u.setPrix(resultat.getDouble( "prix" ));
			u.setArchiver(resultat.getInt( "archiver" ));
			
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Details_commande> getAll() {
	ArrayList<Details_commande> list = new ArrayList<Details_commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Details_commande u = new Details_commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_commande(resultat.getInt( "fk_commande" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setQte(resultat.getInt( "qte" ));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}



public ArrayList<Details_commande> getAllByCommande(int id) {
	ArrayList<Details_commande> list = new ArrayList<Details_commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande WHERE fk_commande=?");
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Details_commande u = new Details_commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_commande(resultat.getInt( "fk_commande" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setQte(resultat.getInt( "qte" ));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setArchiver(resultat.getInt( "archiver" ));

				ProduitDAO prodDao = new ProduitDAO();
				Produit p = prodDao.getById(u.getFk_produit());
				u.setP(p);
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Details_commande> getAllByIdCommande(int id) {
	ArrayList<Details_commande> list = new ArrayList<Details_commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande WHERE fk_commande=?");
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Details_commande u = new Details_commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_commande(resultat.getInt( "fk_commande" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setQte(resultat.getInt( "qte" ));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Details_commande> getAllByCriteria(double start, double end) {
	ArrayList<Details_commande> list = new ArrayList<Details_commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM details_commande WHERE total BETWEEN ? AND ? ");
			preparedStatement.setDouble(1, start);
			preparedStatement.setDouble(2, end);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Details_commande u = new Details_commande();
				u.setId(resultat.getInt( "id" ));
				u.setFk_commande(resultat.getInt( "fk_commande" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setQte(resultat.getInt( "qte" ));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiverById(Details_commande c) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE details_commande set archiver=? WHERE id=?");
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
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM details_commande WHERE id=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}
}
