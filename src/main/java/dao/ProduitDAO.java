package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Image;
import model.Produit;
import tools.Database;

public class ProduitDAO {
	
	public int save(Produit obj) {
		int newId=0;
		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE produits set titre=?, description=?, prix=?, image=?, fk_sous_categorie=?, stock=?, stock_min=?, archiver=? WHERE id=?");
				preparedStatement.setString(1,obj.getTitre());
				preparedStatement.setString(2,obj.getDescription());
				preparedStatement.setDouble(3,obj.getPrix());
				preparedStatement.setString(4, obj.getImage());
				preparedStatement.setInt(5,obj.getFk_sous_categorie());
				preparedStatement.setInt(6,obj.getStock());
				preparedStatement.setInt(7,obj.getStock_min());
				preparedStatement.setInt(8,obj.getArchiver());
				
				preparedStatement.setInt(9,obj.getId());
	            preparedStatement.executeUpdate();
			}else {
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("INSERT INTO produits (titre, description, prix, image, fk_sous_categorie, stock, stock_min, archiver) VALUES(?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1,obj.getTitre());
				preparedStatement.setString(2,obj.getDescription());
				preparedStatement.setDouble(3,obj.getPrix());
				preparedStatement.setString(4, obj.getImage());
				preparedStatement.setInt(5,obj.getFk_sous_categorie());
				preparedStatement.setInt(6,obj.getStock());
				preparedStatement.setInt(7,obj.getStock_min());
				preparedStatement.setInt(8,obj.getArchiver());
				
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

public Produit getById(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits WHERE id=?");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Produit u = new Produit();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				
				ImageDAO imgDao = new ImageDAO();
				ArrayList<Image> imgs = imgDao.getAllByProduit(u.getId());
				u.setImages(imgs);
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Produit getByStatut(int statut) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits WHERE statutProduit=?");
			preparedStatement.setInt(1,statut);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Produit u = new Produit();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
	
}


public ArrayList<Produit> getAll() {
	ArrayList<Produit> list = new ArrayList<Produit>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Produit u = new Produit();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Produit> getLast5() {
	ArrayList<Produit> list = new ArrayList<Produit>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits order by produits.id desc limit 5;");
			
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Produit u = new Produit();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}



public ArrayList<Produit> getAllByCat(int catid) {
	ArrayList<Produit> list = new ArrayList<Produit>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits WHERE fk_sous_categorie=?");
			preparedStatement.setInt(1,catid);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Produit u = new Produit();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public int getCountBySsCat(int sscatid) {

	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT count(*) FROM produits WHERE fk_sous_categorie=? AND archiver=0;");
			preparedStatement.setInt(1, sscatid);
			ResultSet resultat= preparedStatement.executeQuery();

			resultat.next();
			int c = resultat.getInt(1);
			System.out.println(c);
			return c;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return 0;
    }
}

public ArrayList<Produit> getAllByCriteria(String crit) {
	ArrayList<Produit> list = new ArrayList<Produit>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM produits WHERE titre LIKE ? OR description LIKE ?");
			preparedStatement.setString(1, "%"+crit+"%");
			preparedStatement.setString(2, "%"+crit+"%");
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Produit u = new Produit();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setDescription(resultat.getString("description"));
				u.setPrix(resultat.getDouble( "prix" ));
				u.setImage(resultat.getString( "image" ));
				u.setFk_sous_categorie(resultat.getInt( "fk_sous_categorie" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void archiverById(Produit p) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE produits set archiver=? WHERE id=?");
			preparedStatement.setInt(1, p.getArchiver());
			preparedStatement.setInt(2, p.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("archive OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("archive NO");
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM produits WHERE id=?");
			preparedStatement.setInt(1,id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}

/*
 * methodes Florian
 */

public Produit getTitreAndMainImageById(int id) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT produits.id, produits.titre, produits.image FROM produits WHERE id=? AND archiver = 0");
			preparedStatement.setInt(1,id);
			
			ResultSet resultat=preparedStatement.executeQuery();
			
			Produit u = new Produit();
			while(resultat.next()) {
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setImage(resultat.getString( "image" ));

				
				ImageDAO imgDao = new ImageDAO();
				ArrayList<Image> imgs = imgDao.getAllByProduit(u.getId());
				u.setImages(imgs);
			}
			return u;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

// SELECT produits.id, produits.titre, produits.image, produits.stock, produits.stock_min, produits.archiver FROM `produits` WHERE produits.stock <= produits.stock_min + 10;
public ArrayList<Produit> getProduitsAlerteStock(String crit) {
	ArrayList<Produit> list = new ArrayList<Produit>();
	try {
		
			PreparedStatement preparedStatement  =
					Database.connexion.prepareStatement("SELECT produits.id, produits.titre,"
							+ " produits.image, produits.stock, produits.stock_min, produits.archiver "
							+ "FROM `produits` WHERE produits.stock <= produits.stock_min;");
			//preparedStatement.setString(1, "%"+crit+"%");

			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {
				Produit u = new Produit();
				u.setId(resultat.getInt( "id" ));
				u.setTitre(resultat.getString("titre"));
				u.setImage(resultat.getString( "image" ));
				u.setStock(resultat.getInt( "stock" ));
				u.setStock_min(resultat.getInt( "stock_min" ));
				u.setArchiver(resultat.getInt( "archiver" ));
				list.add(u);
			}
			
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}
}
