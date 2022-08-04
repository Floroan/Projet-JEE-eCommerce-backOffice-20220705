package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Commentaire;
import model.Produit;
import model.Utilisateur;
import tools.DaoTools;
import tools.Database;

public class CommentaireDAO {

	private UtilisateurDAO utilDao;

	public void save(Commentaire obj) {

		Commentaire old = new Commentaire();
//<<<<<<< HEAD
		old = this.getByIdProdAndClient(obj.getFk_prod(), obj.getFk_user());
		
		try {
		
			if(obj.getFk_user() == old.getFk_user() && obj.getFk_prod() == old.getFk_prod()) {
				System.out.println("le commentaire existe déjà: UPDATE");
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE commentaires set commentaire=?, note=? , date=?, archiver=? WHERE fk_prod=? AND fk_user=? AND id=?");
				preparedStatement.setString(1,obj.getCommentaire());
				preparedStatement.setInt(2,obj.getNote());
				preparedStatement.setDate(3,obj.getDate());
				preparedStatement.setInt(4,obj.getArchiver());
				
				
				preparedStatement.setInt(5,obj.getFk_prod());
				preparedStatement.setInt(6,obj.getFk_user());
				preparedStatement.setInt(7,obj.getId());
				
	            preparedStatement.executeUpdate();
			}else {
//=======
//		old = this.getById(obj.getFk_prod(), obj.getFk_user());
//
//		try {
//
//			if (obj.getFk_user() == old.getFk_user() && obj.getFk_prod() == old.getFk_prod()) {
//				System.out.println("le commentaire existe d�j�: UPDATE");
//				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
//						"UPDATE commentaires set commentaire=?, note=? , date=?, archiver=? WHERE fk_prod=? AND fk_user=?");
//				preparedStatement.setString(1, obj.getCommentaire());
//				preparedStatement.setInt(2, obj.getNote());
//				preparedStatement.setInt(3, obj.getArchiver());
//
//				preparedStatement.setInt(4, obj.getFk_prod());
//				preparedStatement.setInt(5, obj.getFk_user());
//
//				preparedStatement.executeUpdate();
//			} else {
//>>>>>>> tanguyMain
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO commentaires (commentaire, note, date, fk_prod, fk_user, archiver) VALUES(?,?,?,?,?,?)");
				preparedStatement.setString(1, obj.getCommentaire());
				preparedStatement.setInt(2, obj.getNote());
				preparedStatement.setDate(3, obj.getDate());
				preparedStatement.setInt(4, obj.getFk_prod());
				preparedStatement.setInt(5, obj.getFk_user());
				preparedStatement.setInt(6, obj.getArchiver());

				preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}
	}
	
//<<<<<<< HEAD
	public Commentaire getById(int id) {
		try {
		
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commentaires WHERE id=?");
				preparedStatement.setInt(1,id);
				ResultSet resultat=preparedStatement.executeQuery();
				
				Commentaire u = new Commentaire();
				while(resultat.next()) {
					u.setId(resultat.getInt( "id" ));
					u.setCommentaire(resultat.getString("commentaire"));
					u.setNote(resultat.getInt("note"));
					u.setDate(resultat.getDate("date"));
					u.setFk_prod(resultat.getInt( "fk_prod" ));
					u.setFk_user(resultat.getInt( "fk_user" ));
					u.setArchiver(resultat.getInt( "archiver" ));
				}
				return u;
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}

public Commentaire getByIdProdAndClient(int idProd, int idCli) {
	try {
	
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commentaires WHERE fk_prod=? AND fk_user=?");
			preparedStatement.setInt(1,idProd);
			preparedStatement.setInt(2,idCli);
			ResultSet resultat=preparedStatement.executeQuery();
			
//=======
//	}
//
//	public Commentaire getById(int idProd, int idCli) {
//		try {
//
//			PreparedStatement preparedStatement = Database.connexion
//					.prepareStatement("SELECT * FROM commentaires WHERE fk_prod=? AND fk_user=?");
//			preparedStatement.setInt(1, idProd);
//			preparedStatement.setInt(2, idCli);
//			ResultSet resultat = preparedStatement.executeQuery();
//
//>>>>>>> tanguyMain
			Commentaire u = new Commentaire();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setCommentaire(resultat.getString("commentaire"));
				u.setNote(resultat.getInt("note"));
				u.setDate(resultat.getDate("date"));
				u.setFk_prod(resultat.getInt("fk_prod"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Commentaire> getAll() {
		ArrayList<Commentaire> list = new ArrayList<Commentaire>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commentaires");

			ResultSet rs = ps.executeQuery();
			
			UtilisateurDAO ud = new UtilisateurDAO();
			ProduitDAO pd = new ProduitDAO();

			while (rs.next()) {
				Commentaire c = new Commentaire();
				c.setId(rs.getInt("id"));
				c.setCommentaire(rs.getString("commentaire"));
				c.setNote(rs.getInt("note"));
				c.setDate(rs.getDate("date"));
				c.setFk_prod(rs.getInt("fk_prod"));
				c.setFk_user(rs.getInt("fk_user"));
				c.setArchiver(rs.getInt("archiver"));
				
				Utilisateur u = ud.getById( rs.getInt("fk_user") );
				Produit p = pd.getById( rs.getInt("fk_prod") );

				c.setUtilisateur(u);
				c.setProd(p);
				
				list.add(c);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* mapping with clients */
	public ArrayList<Commentaire> getAllByProduit(int id) {
		ArrayList<Commentaire> list = new ArrayList<Commentaire>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM commentaires "
					+ "LEFT JOIN utilisateurs ON utilisateurs.id = commentaires.fk_user "
					+ "WHERE fk_prod=? ORDER BY commentaires.note DESC");

			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commentaire u = new Commentaire();
				u.setId(resultat.getInt("id"));
				u.setCommentaire(resultat.getString("commentaire"));
				u.setNote(resultat.getInt("note"));
				u.setDate(resultat.getDate("date"));
				u.setFk_prod(resultat.getInt("fk_prod"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setArchiver(resultat.getInt("archiver"));

				Utilisateur c;
				utilDao = new UtilisateurDAO();
				c = utilDao.getById(u.getFk_user());
//				c.setId(resultat.getInt( "id" ));
//				c.setNom(resultat.getString( "nom" ));
//				c.setPrenom(resultat.getString( "prenom" ));
//				c.setEmail(resultat.getString( "email" ));
//				c.setDate_inscription(resultat.getDate("date_inscription"));
//				c.setPassword(resultat.getString( "password" ));
//				c.setArchiver(resultat.getInt("archiver"));

				u.setUtilisateur(c);

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Commentaire> getAllByClient(int id) {
		ArrayList<Commentaire> list = new ArrayList<Commentaire>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT * FROM commentaires "
					+ "LEFT JOIN produits ON produits.id = commentaires.fk_prod "
					+ "WHERE fk_user=? ORDER BY commentaires.note DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commentaire u = new Commentaire();
				u.setId(resultat.getInt("id"));
				u.setCommentaire(resultat.getString("commentaire"));
				u.setNote(resultat.getInt("note"));
				u.setDate(resultat.getDate("date"));
				u.setFk_prod(resultat.getInt("fk_prod"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setArchiver(resultat.getInt("archiver"));

				Produit p = new Produit();
				p.setId(resultat.getInt("id"));
				p.setTitre(resultat.getString("titre"));
				p.setDescription(resultat.getString("description"));
				p.setPrix(resultat.getDouble("prix"));
				p.setImage(resultat.getString("image"));
				p.setFk_sous_categorie(resultat.getInt("fk_sous_categorie"));
				p.setStock(resultat.getInt("stock"));
				p.setStock_min(resultat.getInt("stock_min"));
				p.setArchiver(resultat.getInt("archiver"));

				u.setProd(p);

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void archiverById(Commentaire c) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE commentaires set archiver=? WHERE id=?");
			preparedStatement.setInt(1, c.getArchiver());
			preparedStatement.setInt(2, c.getId());

			preparedStatement.executeUpdate();

			System.out.println("ARCHIVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ARCHIVED NO");
		}
	}

	public void deleteById(int fk_prod, int fk_user) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM commentaires WHERE fk_prod=? AND fk_user=?");
			preparedStatement.setInt(1, fk_prod);
			preparedStatement.setInt(2, fk_user);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	
	/**********************
	 * 
	 * 		MÉTHODES
	 * 
	 **********************/
	
	// COUNT ALL COMMENTAIRES BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return String
	public String countAllCommentsByOneUserPerEachMonthOfTheCurrentYear (int fk_user) {
		
		DaoTools dt = new DaoTools();
		String paramGraph =  dt.countAllClicksByOneUserPerEachMonthOfTheCurrentYearForOneTable(fk_user, "commentaires");
		
		return paramGraph;

	}
	
	// COUNT ALL COMMENTS BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return int
	public int countAllCommentsByOneUser (int fk_user) {
		
		try {
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(*) FROM `commentaires` WHERE `fk_user`=? ");
			ps.setInt(1, fk_user);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			int c = rs.getInt("COUNT(*)");

			return c;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
			
		}

	}
	
	public void deleteByIdCommentaire(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM commentaires WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
}
