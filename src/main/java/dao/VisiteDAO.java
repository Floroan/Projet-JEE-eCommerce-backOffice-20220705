package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.Produit;
import model.Utilisateur;
import model.Visite;
import tools.DaoTools;
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
					.prepareStatement("SELECT * FROM visites "
							+ "WHERE fk_prod=? ORDER BY visites.date DESC");

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
					.prepareStatement("SELECT * FROM visites "
							+ "WHERE Fk_user=? "
							+ "ORDER BY visites.date DESC");

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

				UtilisateurDAO ud = new UtilisateurDAO();
				Utilisateur u = ud.getById(id);
				o.setU(u);

				list.add(o);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
//<<<<<<< HEAD

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

	/**********************
	 * 
	 * MÉTHODES
	 * 
	 **********************/

	// RETRIEVE DATE LAST VISIT
	public Date dateLastVisit(int id) {

		try {

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT `fk_user`, MAX(date) FROM `visites` WHERE `fk_user`=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Date date = rs.getDate("MAX(date)");

			return date;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;

		}
	}

//=======
//
//	public void deleteById(int idProduit, int idClient) {
//		try {
//
//			PreparedStatement preparedStatement = Database.connexion
//					.prepareStatement("DELETE FROM visites WHERE idProduit=? AND idClient=?");
//			preparedStatement.setInt(1, idProduit);
//			preparedStatement.setInt(2, idClient);
//>>>>>>> main
//
//			preparedStatement.executeUpdate();
//
//			System.out.println("DELETED OK");
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("DELETED NO");
//		}
//	}

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
			// conflit GitHub : j’ai du rajouter à la mano les 2 }
		}
	}

//<<<<<<< HEAD
	// RETRIEVE THE NUMBER OF PRODUCT VIEWED BY A CLIENT, A LEAD OR A VISITOR
	public Integer numberOfProductsViewedBy(int id) {

		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(DISTINCT fk_prod) FROM visites WHERE `fk_user`=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Integer i = rs.getInt("COUNT(DISTINCT fk_prod)");

			return i;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//=======
//		} catch (Exception ex) {
//			ex.printStackTrace();
//>>>>>>> main
			return null;
		}
	}

//<<<<<<< HEAD

	// RETRIEVE TOTAL CLICKS ON PRODUCT CARD BY A CLIENT, A LEAD OR A VISITOR
	public Integer sumOfProductClicks(int id) {

		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(fk_prod) FROM visites WHERE `fk_user`=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Integer i = rs.getInt("COUNT(fk_prod)");

			return i;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// RETRIEVE TOTAL CLICKS ON PRODUCT CARD BY fk_user AND fk_prod
	public Integer sumOfProductClicksByOneUserForOneProduct(int fk_user, int fk_prod) {

		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(fk_prod) FROM visites WHERE `fk_user`=? AND `fk_prod`=?");
			ps.setInt(1, fk_user);
			ps.setInt(2, fk_prod);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Integer i = rs.getInt("COUNT(fk_prod)");

			return i;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// COUNT ALL PRODUCT CLICKS BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return String
	public String countAllProductClicksByOneUserPerEachMonthOfTheCurrentYear(int fk_user) {

		DaoTools dt = new DaoTools();
		String paramGraph = dt.countAllClicksByOneUserPerEachMonthOfTheCurrentYearForOneTable(fk_user, "visites");

		return paramGraph;

	}
//=======
//	public void deleteByIdProduit(int idProduit, int idClient) {
//		try {
//
//			PreparedStatement preparedStatement = Database.connexion
//					.prepareStatement("DELETE FROM visites WHERE idProduit=?");
//			preparedStatement.setInt(1, idProduit);
//
//			preparedStatement.executeUpdate();
//
//			System.out.println("DELETED OK");
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("DELETED NO");
//		}
//
//	}

	/*
	 * Methodes Florian
	 * 
	 * 
	 */

	public LinkedHashMap<Produit, Integer> getVisitesByProduit(int nbProduits) {
		
		LinkedHashMap<Produit, Integer> list = new LinkedHashMap<Produit, Integer>();

		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT visites.fk_prod, produits.titre, produits.image, COUNT(*) as cnt"
							+ "	FROM visites"
							+ "	LEFT JOIN produits"
							+ "	ON visites.fk_prod = produits.id"
							+ "	WHERE produits.archiver = 0"
							+ "	GROUP by visites.fk_prod, produits.titre, produits.image"
							+ "	ORDER by cnt DESC LIMIT ?;");
			ps.setInt(1, nbProduits);
			ResultSet resultat = ps.executeQuery();
			
			while (resultat.next()) {
				Produit p = new Produit();
				p.setId(resultat.getInt("fk_prod"));
				p.setTitre(resultat.getString("titre"));
				p.setImage(resultat.getString("image"));
				list.put(p, resultat.getInt("cnt"));
			}
			
			System.out.println(list);
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	// SELECT visites.fk_prod, COUNT(*) as cnt FROM visites GROUP by visites.fk_prod
	// ORDER by cnt DESC LIMIT 10

//>>>>>>> main
}
