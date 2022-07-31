package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Recherche;
import model.Utilisateur;
import tools.DaoTools;
import tools.Database;

public class RechercheDAO {

	public void save(Recherche obj) {

		try {

			if (obj.getId() != 0) {
				System.out.println("le commentaire existe d�j�: UPDATE");
				PreparedStatement preparedStatement = Database.connexion
						.prepareStatement("UPDATE recherches set fk_user=?, motcle=? , date=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getMotcle());
				preparedStatement.setDate(3, obj.getDate());
				preparedStatement.setInt(4, obj.getArchiver());

				preparedStatement.setInt(5, obj.getId());

				preparedStatement.executeUpdate();
			} else {
				System.out.println("le commentaire n'existe pas: INSERT");
				PreparedStatement preparedStatement = Database.connexion
						.prepareStatement("INSERT INTO recherches (fk_user, motcle, date, archiver) VALUES(?,?,?,?)");
				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getMotcle());
				preparedStatement.setDate(3, obj.getDate());
				preparedStatement.setInt(4, obj.getArchiver());

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM recherches WHERE id=?");
			preparedStatement.setInt(1, idProd);
			preparedStatement.setInt(2, idCli);
			ResultSet resultat = preparedStatement.executeQuery();

			Recherche u = new Recherche();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt("archiver"));
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

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM recherches");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Recherche u = new Recherche();
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt("archiver"));

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM recherches WHERE fk_user=? ORDER BY recherches.note DESC");
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Recherche u = new Recherche();
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public LinkedHashMap<Recherche, Integer> getmotsAndcount(int limit) {
		
		LinkedHashMap<Recherche, Integer> list = new LinkedHashMap<>();
		
		try {
			
			/*
			  * Problème avec Group by --> Ajouter ONLY_FULL_GROUP_BY,
			  * - https://stackoverflow.com/questions/23921117/disable-only-full-group-by : ONLY_FULL_GROUP_BY,
			  * - tuto pour régler le problème : https://grafikart.fr/tutoriels/only-full-group-by-sql-1206 
			  * 
			  */
			// Le problème est que MySQL ne sait pas quel champ choisir.
			// Il en prend donc un au hasard quand ONLY_FULL_GROUP_BY n’est pas activé
			// Essaye cette requête pour comprendre :
			// SELECT GROUP_CONCAT(recherches.id SEPARATOR ','), GROUP_CONCAT(recherches.fk_user SEPARATOR ','), GROUP_CONCAT(recherches.date SEPARATOR ','), GROUP_CONCAT(recherches.motcle SEPARATOR ','), GROUP_CONCAT(recherches.archiver SEPARATOR ','), COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY count DESC LIMIT 5
			// De surcroit est-ce utile de récupérer un objet pour afficher un graphique ?
			// À part pour faire joujou ;)
			// Ci dessous la bonne requête et plus bas la nouvelle méthode countTheMostSearchedWords():
			// SELECT recherches.motcle, COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY count DESC LIMIT ?;
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT *, COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY count DESC LIMIT ?;"); // FAUX
			ps.setInt(1, limit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Recherche u = new Recherche();
				u.setId(rs.getInt("id"));
				u.setFk_user(rs.getInt("fk_user"));
				u.setMotcle(rs.getString("motcle"));
				u.setDate(rs.getDate("date"));
				u.setArchiver(rs.getInt("archiver"));

				list.put(u, rs.getInt("count"));
				
			}
			
			return list;

		} catch (Exception ex) {
			
			ex.printStackTrace();
			return null;
			
		}
	}

	public HashMap<Recherche, Integer> getmotCountAndUser() {
		HashMap<Recherche, Integer> list = new HashMap<>();
		try {
			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT motcle, COUNT(*) as count FROM recherches LEFT JOIN utilisateurs ON recherches.fk_user = utilisateurs.id WHERE utilisateurs.id = recherches.fk_user GROUP BY motcle ORDER BY count DESC;");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Recherche u = new Recherche();
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setMotcle(resultat.getString("motcle"));
				u.setDate(resultat.getDate("date"));
				u.setArchiver(resultat.getInt("archiver"));

				Utilisateur ut = new Utilisateur();
				ut.setId(resultat.getInt("id"));
				ut.setNom(resultat.getString("nom"));

				list.put(u, resultat.getInt("count"));
			}
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

//SELECT motcle, COUNT(*) as count FROM recherches LEFT JOIN utilisateurs ON recherches.fk_user = utilisateurs.id WHERE utilisateurs.id = recherches.fk_user GROUP BY motcle ORDER BY count DESC; 

//SELECT motcle,COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY count DESC;
//SELECT motcle,COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY motcle ASC;

	public void archiverById(Recherche c) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE recherches set archiver=? WHERE id=?");
			preparedStatement.setInt(1, c.getArchiver());
			preparedStatement.setInt(2, c.getId());

			preparedStatement.executeUpdate();

			System.out.println("ARCHIVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ARCHIVED NO");
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM recherches WHERE id=?");
			preparedStatement.setInt(1, id);

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

	// COUNT ALL RESEARCHES BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return String
	public String countAllResearchesByOneUserPerEachMonthOfTheCurrentYear(int fk_user) {

		DaoTools dt = new DaoTools();
		String paramGraph = dt.countAllClicksByOneUserPerEachMonthOfTheCurrentYearForOneTable(fk_user, "recherches");

		return paramGraph;

	}

	// COUNT ALL RESEARCHES BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return int
	public int countAllResearchesByOneUser(int fk_user) {

		try {

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(*) FROM `recherches` WHERE `fk_user`=? ");
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
	
	/*
	 * Ci dessous la bonne requête et plus bas la nouvelle méthode countTheMostSearchedWords():
	 * SELECT recherches.motcle, COUNT(*) as count FROM recherches GROUP BY motcle ORDER BY count DESC LIMIT ?;
	 */
	
	// compter les mots les plus recherchés
	public LinkedHashMap<String, Integer> countTheMostSearchedWords(int limit) {
		
		LinkedHashMap<String, Integer> list = new LinkedHashMap<>();
		
		try {
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT recherches.motcle, COUNT(*) as count "
							+ "FROM recherches GROUP BY motcle ORDER BY count DESC LIMIT ?");
			ps.setInt(1, limit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				list.put( rs.getString( "motcle" ), rs.getInt( "count" ) );
				
			}
			
			return list;

		} catch (Exception ex) {
			
			ex.printStackTrace();
			return null;
			
		}
	}


}
