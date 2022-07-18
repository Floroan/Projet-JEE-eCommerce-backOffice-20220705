/**
 * 
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import model.Entree_stock;
import model.Fournisseur;
import model.Produit;
import model.Utilisateur;
import tools.Database;

/**
 * @author 75013-33-16
 *
 */
public class Entree_stockDAO {

	private FournisseurDao fournisseurDao;
	private ProduitDAO produitDao;
	
	public void save(Entree_stock obj) {
		
		try {
		
			if(obj.getId() != 0) {
				System.out.println("le commentaire existe d�j�: UPDATE");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE entree_stock set id=?, fk_fournisseur=?, fk_produit=?, date=?, qte=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1,obj.getFk_fournisseur());
				preparedStatement.setInt(2,obj.getFk_produit());
				preparedStatement.setDate(3,obj.getDate());
				preparedStatement.setInt(4,obj.getQte());
				preparedStatement.setInt(5,obj.getArchiver());
				
				preparedStatement.setInt(6,obj.getId());

				
	            preparedStatement.executeUpdate();
			}else {
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO entree_stock (fk_fournisseur, fk_produit, date, qte, archiver) VALUES(?,?,?,?,?)");
				preparedStatement.setInt(1,obj.getFk_fournisseur());
				preparedStatement.setInt(2,obj.getFk_produit());
				preparedStatement.setDate(3,obj.getDate());
				preparedStatement.setInt(4,obj.getQte());
				preparedStatement.setInt(5,obj.getArchiver());

				
	            preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}



public Entree_stock getById(int idProd, int idCli) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM entree_stock WHERE fk_prod=? AND fk_user=?");
			preparedStatement.setInt(1,idProd);
			preparedStatement.setInt(2,idCli);
			ResultSet resultat=preparedStatement.executeQuery();
			
			Entree_stock u = new Entree_stock();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setFk_fournisseur(resultat.getInt( "fk_fournisseur" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setDate(resultat.getDate("date"));
				u.setQte(resultat.getInt("qte"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Entree_stock> getAll() {
	ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM entree_stock");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Entree_stock u = new Entree_stock();
				u.setId(resultat.getInt( "id" ));
				u.setFk_fournisseur(resultat.getInt( "fk_fournisseur" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setDate(resultat.getDate("date"));
				u.setQte(resultat.getInt("qte"));
				u.setArchiver(resultat.getInt("archiver"));
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Entree_stock> getAllByProduit(int id) {
	ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM entree_stock WHERE fk_produit=? ORDER BY entree_stock.date DESC");
			
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Entree_stock u = new Entree_stock();
				u.setId(resultat.getInt( "id" ));
				u.setFk_fournisseur(resultat.getInt( "fk_fournisseur" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setDate(resultat.getDate("date"));
				u.setQte(resultat.getInt("qte"));
				u.setArchiver(resultat.getInt("archiver"));
				
				Fournisseur c;
				fournisseurDao = new FournisseurDao();
				c = fournisseurDao.getById(u.getFk_fournisseur());
				
				u.setF(c);
				
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Entree_stock> getAllByFournisseur(int id) {
	ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM entree_stock WHERE fk_fournisseur=? ORDER BY entree_stock.date DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Entree_stock u = new Entree_stock();
				u.setId(resultat.getInt( "id" ));
				u.setFk_fournisseur(resultat.getInt( "fk_fournisseur" ));
				u.setFk_produit(resultat.getInt( "fk_produit" ));
				u.setDate(resultat.getDate("date"));
				u.setQte(resultat.getInt("qte"));
				u.setArchiver(resultat.getInt("archiver"));
	
				Produit p;
				produitDao = new ProduitDAO();
				p = produitDao.getById(u.getFk_produit());
	
				u.setP(p);

				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiveById(Entree_stock e) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE fournisseurs SET archiver=? WHERE id=?");
			preparedStatement.setInt(1, e.getArchiver());
			preparedStatement.setInt(2, e.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("ARCHIVE OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("ARCHIVE NO");
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM entree_stock WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}
}
