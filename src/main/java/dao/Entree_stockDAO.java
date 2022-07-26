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

			if (obj.getId() != 0) {
				System.out.println("le commentaire existe déjà: UPDATE");
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE entree_stock SET fk_fournisseur=?, fk_produit=?, date=?, qte=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1, obj.getFk_fournisseur());
				preparedStatement.setInt(2, obj.getFk_produit());
				preparedStatement.setDate(3, obj.getDate());
				preparedStatement.setInt(4, obj.getQte());
				preparedStatement.setInt(5, obj.getArchiver());

				preparedStatement.setInt(6, obj.getId());

				preparedStatement.executeUpdate();
			} else {
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO entree_stock (fk_fournisseur, fk_produit, date, qte, archiver) VALUES(?,?,?,?,?)");
				preparedStatement.setInt(1, obj.getFk_fournisseur());
				preparedStatement.setInt(2, obj.getFk_produit());
				preparedStatement.setDate(3, obj.getDate());
				preparedStatement.setInt(4, obj.getQte());
				preparedStatement.setInt(5, obj.getArchiver());

				preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Entree_stock getById(int id) {
		try {

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT * FROM entree_stock WHERE id=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			Entree_stock u = new Entree_stock();
			
			rs.next();
				
			u.setId(rs.getInt("id"));
			u.setFk_fournisseur(rs.getInt("fk_fournisseur"));
			u.setFk_produit(rs.getInt("fk_produit"));
			u.setDate(rs.getDate("date"));
			u.setQte(rs.getInt("qte"));
			u.setArchiver(rs.getInt("archiver"));
			
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Entree_stock> getAll() {
		ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
		try {

			ProduitDAO pd = new ProduitDAO();
			FournisseurDao fd = new FournisseurDao();

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM entree_stock");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Entree_stock eb = new Entree_stock();

				eb.setId(rs.getInt("id"));
				eb.setFk_fournisseur(rs.getInt("fk_fournisseur"));
				eb.setFk_produit(rs.getInt("fk_produit"));
				eb.setDate(rs.getDate("date"));
				eb.setQte(rs.getInt("qte"));
				eb.setArchiver(rs.getInt("archiver"));

				Produit pb = pd.getById(rs.getInt("fk_produit"));
				eb.setP(pb);

				Fournisseur fb = fd.getById(rs.getInt("fk_fournisseur"));
				eb.setF(fb);

				list.add(eb);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Entree_stock> getAllArchiver() {
		
		ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
		
		try {

			ProduitDAO pd = new ProduitDAO();
			FournisseurDao fd = new FournisseurDao();

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT * FROM entree_stock WHERE archiver=1");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Entree_stock eb = new Entree_stock();

				eb.setId(rs.getInt("id"));
				eb.setFk_fournisseur(rs.getInt("fk_fournisseur"));
				eb.setFk_produit(rs.getInt("fk_produit"));
				eb.setDate(rs.getDate("date"));
				eb.setQte(rs.getInt("qte"));
				eb.setArchiver(rs.getInt("archiver"));

				Produit pb = pd.getById(rs.getInt("fk_produit"));
				eb.setP(pb);

				Fournisseur fb = fd.getById(rs.getInt("fk_fournisseur"));
				eb.setF(fb);

				list.add(eb);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

public ArrayList<Entree_stock> getAllToReceive() {
		
		ArrayList<Entree_stock> list = new ArrayList<Entree_stock>();
		
		try {

			ProduitDAO pd = new ProduitDAO();
			FournisseurDao fd = new FournisseurDao();

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT * FROM entree_stock WHERE archiver=0");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Entree_stock eb = new Entree_stock();

				eb.setId(rs.getInt("id"));
				eb.setFk_fournisseur(rs.getInt("fk_fournisseur"));
				eb.setFk_produit(rs.getInt("fk_produit"));
				eb.setDate(rs.getDate("date"));
				eb.setQte(rs.getInt("qte"));
				eb.setArchiver(rs.getInt("archiver"));

				Produit pb = pd.getById(rs.getInt("fk_produit"));
				eb.setP(pb);

				Fournisseur fb = fd.getById(rs.getInt("fk_fournisseur"));
				eb.setF(fb);

				list.add(eb);
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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM entree_stock WHERE fk_produit=? ORDER BY entree_stock.date DESC");

			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Entree_stock u = new Entree_stock();
				u.setId(resultat.getInt("id"));
				u.setFk_fournisseur(resultat.getInt("fk_fournisseur"));
				u.setFk_produit(resultat.getInt("fk_produit"));
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

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT * FROM entree_stock WHERE fk_fournisseur=? ORDER BY entree_stock.date DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Entree_stock u = new Entree_stock();
				u.setId(resultat.getInt("id"));
				u.setFk_fournisseur(resultat.getInt("fk_fournisseur"));
				u.setFk_produit(resultat.getInt("fk_produit"));
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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE fournisseurs SET archiver=? WHERE id=?");
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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM entree_stock WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
}
