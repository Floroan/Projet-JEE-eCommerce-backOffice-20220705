package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import model.Produit;
import model.Utilisateur;
import model.Visite;
import tools.Database;

public class VisiteDAO {

	private ProduitDAO prodDao;
	
	
	
	public VisiteDAO() {
		super();
		prodDao = new ProduitDAO();
	}

	public void save(Visite obj) {

		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("INSERT INTO visites (fk_prod, fk_user, date) VALUES(?,?,?)");
			preparedStatement.setInt(1, obj.getFk_prod());
			preparedStatement.setInt(2, obj.getFk_user());
			preparedStatement.setDate(3, obj.getDate());

			preparedStatement.executeUpdate();

			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Visite getByIds(int idProd, int idCli) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM visites WHERE idProduit=? AND idClient=?");
			preparedStatement.setInt(1, idProd);
			preparedStatement.setInt(2, idCli);
			ResultSet resultat = preparedStatement.executeQuery();

			Visite o = new Visite();
			while (resultat.next()) {

				o.setId(resultat.getInt("id"));
				o.setFk_prod(resultat.getInt("fk_prod"));
				o.setFk_user(resultat.getInt("fk_user"));
				o.setDate(resultat.getDate("date"));
				o.setArchiver(resultat.getInt("archiver"));
			}
			return o;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Visite> getAll() {
		ArrayList<Visite> list = new ArrayList<Visite>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM visites");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Visite o = new Visite();
				o.setId(resultat.getInt("id"));
				o.setFk_prod(resultat.getInt("fk_prod"));
				o.setFk_user(resultat.getInt("fk_user"));
				o.setDate(resultat.getDate("date"));
				o.setArchiver(resultat.getInt("archiver"));

				list.add(o);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* mapping with clients */
	public ArrayList<Visite> getAllByProduit(int id) {
		ArrayList<Visite> list = new ArrayList<Visite>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM visites " + "WHERE fk_prod=? ORDER BY visites.date DESC");

			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Visite o = new Visite();
				o.setId(resultat.getInt("id"));
				o.setFk_prod(resultat.getInt("fk_prod"));
				o.setFk_user(resultat.getInt("fk_user"));
				o.setDate(resultat.getDate("date"));
				o.setArchiver(resultat.getInt("archiver"));

				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur u = utDao.getById(o.getFk_user());
				o.setU(u);

				list.add(o);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Visite> getAllByClient(int id) {
		ArrayList<Visite> list = new ArrayList<Visite>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM visites " + "WHERE Fk_user=? ORDER BY visites.date DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Visite o = new Visite();
				o.setId(resultat.getInt("id"));
				o.setFk_prod(resultat.getInt("fk_prod"));
				o.setFk_user(resultat.getInt("fk_user"));
				o.setDate(resultat.getDate("date"));

				ProduitDAO prodDao = new ProduitDAO();
				Produit p = prodDao.getById(o.getFk_prod());

				o.setP(p);

				list.add(o);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int idProduit, int idClient) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM visites WHERE idProduit=? AND idClient=?");
			preparedStatement.setInt(1, idProduit);
			preparedStatement.setInt(2, idClient);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

	public LinkedHashMap<Date, Integer> getWithInterval(int interval) {
		LinkedHashMap<Date, Integer> list = new LinkedHashMap<Date, Integer>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT visites.date,COUNT(*) as cnt FROM visites WHERE visites.date >= (NOW() - INTERVAL ? DAY) GROUP by visites.date;");
			preparedStatement.setInt(1, interval);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				list.put(resultat.getDate("date"), resultat.getInt("cnt"));

			}
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteByIdProduit(int idProduit, int idClient) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM visites WHERE idProduit=?");
			preparedStatement.setInt(1, idProduit);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}

	}
	
	
	/*
	 * Methodes Florian
	 * 
	 * 
	 */
	
	public LinkedHashMap<Produit, Integer> getVisitesByProduit(int  nbProduits){
		LinkedHashMap<Produit, Integer> list = new LinkedHashMap<Produit, Integer>();
		
		try {
				PreparedStatement preparedStatement  =
						Database.connexion.prepareStatement("SELECT visites.fk_prod, COUNT(*) as cnt FROM visites GROUP by visites.fk_prod ORDER by cnt DESC LIMIT ?");
				preparedStatement.setInt(1, nbProduits);
				ResultSet resultat=preparedStatement.executeQuery();

				Produit p = new Produit();
				while(resultat.next()) {
					p = prodDao.getTitreAndMainImageById(resultat.getInt("fk_prod"));
					list.put(p, resultat.getInt( "cnt" ));
				}
				return list;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	//SELECT visites.fk_prod, COUNT(*) as cnt FROM visites GROUP by visites.fk_prod ORDER by cnt DESC LIMIT 10
	
	
}
