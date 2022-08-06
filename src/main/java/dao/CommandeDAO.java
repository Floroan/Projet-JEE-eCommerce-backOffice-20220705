package dao;


//import java.util.Date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.naming.factory.TransactionFactory;

import model.Commande;
import model.Details_commande;
import model.Utilisateur;
import tools.Database;
//import tools.DateUtilToDateSql;
import tools.DateManipulator;

public class CommandeDAO {

	private Details_commandeDAO detailsDao;

	public void save(Commande obj) {

		try {
			
			if(obj.getId() != 0) {
				PreparedStatement preparedStatement  = Database.connexion
						.prepareStatement("UPDATE commandes SET fk_utilisateur=?, date=?, total=?, fk_adresse=?, etat=?, archiver=? WHERE id=?;");
				
				preparedStatement.setInt(1, obj.getFk_utilisateur());
				preparedStatement.setDate(2, obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4, obj.getFk_adresse());
				preparedStatement.setInt(5, obj.getEtat());
				preparedStatement.setInt(6, obj.getArchiver());

				preparedStatement.setInt(7, obj.getId());
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);

				preparedStatement.executeUpdate();
				
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO commandes (fk_utilisateur, date, total, fk_adresse, etat, archiver) VALUES(?,?,?,?,?,?)");
				preparedStatement.setInt(1, obj.getFk_utilisateur());
				preparedStatement.setDate(2, obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4, obj.getFk_adresse());
				preparedStatement.setInt(5, obj.getEtat());
				preparedStatement.setInt(6, obj.getArchiver());

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

			if (obj.getId() != 0) {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE commandes set fk_utilisateur=?, date=?, total=?, fk_adresse=?, etat=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1, obj.getFk_utilisateur());
				preparedStatement.setDate(2, obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4, obj.getFk_adresse());
				preparedStatement.setInt(5, obj.getEtat());
				preparedStatement.setInt(6, obj.getArchiver());

//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);

				preparedStatement.setInt(7, obj.getId());

				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO commandes (idClient, dateCommande, totalCommande) VALUES(?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, obj.getFk_utilisateur());
				preparedStatement.setDate(2, obj.getDate());
				preparedStatement.setDouble(3, obj.getTotal());
				preparedStatement.setInt(4, obj.getFk_adresse());
				preparedStatement.setInt(5, obj.getEtat());
				preparedStatement.setInt(6, obj.getArchiver());

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM commandes WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Commande u = new Commande();
			resultat.next();
			u.setId(resultat.getInt("id"));
			u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
			u.setDate(resultat.getDate("date"));
			u.setTotal(resultat.getDouble("total"));
			u.setFk_adresse(resultat.getInt("fk_adresse"));
			u.setEtat(resultat.getInt("etat"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Commande getByIdCommandeUserAdressAndDetails(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM commandes WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Commande u = new Commande();
			resultat.next();
			u.setId(resultat.getInt("id"));
			u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
			u.setDate(resultat.getDate("date"));
			u.setTotal(resultat.getDouble("total"));
			u.setFk_adresse(resultat.getInt("fk_adresse"));
			u.setEtat(resultat.getInt("etat"));
			u.setArchiver(resultat.getInt("archiver"));

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=? AND total=? AND date=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setDouble(2, total);

			long timeInMilliSeconds = d.getTime();
			java.sql.Date date = new java.sql.Date(timeInMilliSeconds);

			preparedStatement.setDate(3, date);
			ResultSet resultat = preparedStatement.executeQuery();

			Commande u = new Commande();
			resultat.next();
			u.setId(resultat.getInt("id"));
			u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
			u.setDate(resultat.getDate("date"));
			u.setTotal(resultat.getDouble("total"));
			u.setFk_adresse(resultat.getInt("fk_adresse"));
			u.setEtat(resultat.getInt("etat"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Commande> getAll() {
		ArrayList<Commande> list = new ArrayList<Commande>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM commandes");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commande u = new Commande();
				
				u.setId(resultat.getInt("id"));
				u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
				u.setDate(resultat.getDate("date"));
				u.setTotal(resultat.getDouble("total"));
				u.setFk_adresse(resultat.getInt("fk_adresse"));
				u.setEtat(resultat.getInt("etat"));
				u.setArchiver(resultat.getInt("archiver"));
				
				
				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur ut = utDao.getById(u.getFk_utilisateur());
				
				u.setU(ut);
				
				list.add(u);
			}
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Commande> getLimit(int limit) {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes ORDER BY commandes.date DESC LIMIT ? ");
			preparedStatement.setInt(1, limit);
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
				
				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur ut = utDao.getById(u.getFk_utilisateur());
				
				u.setU(ut);
				
				list.add(u);
			}
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public LinkedHashMap<java.util.Date, Integer> getWithInterval(int interval) {
	LinkedHashMap<java.util.Date, Integer> list = new LinkedHashMap<java.util.Date, Integer>();
	try {
		
			PreparedStatement preparedStatement  =
					Database.connexion.prepareStatement("SELECT commandes.date,COUNT(*) as cnt FROM commandes WHERE commandes.date >= (NOW() - INTERVAL ? DAY) GROUP by commandes.date;");
			preparedStatement.setInt(1, interval);
			ResultSet resultat=preparedStatement.executeQuery();

			while(resultat.next()) {

				list.put(resultat.getDate( "date" ), resultat.getInt( "cnt" ));

			}
			return list;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}
//SELECT * from "+ tableName + " WHERE " + tableName + ".date >= (NOW() - INTERVAL "+ interval +" DAY);


public ArrayList<Commande> getAllByEtat(int etat) {
	ArrayList<Commande> list = new ArrayList<Commande>();
	try {
		
			PreparedStatement preparedStatement  = Database.connexion.prepareStatement("SELECT * FROM commandes WHERE etat=? AND archiver=0;");
			preparedStatement.setInt(1, etat);
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
				
				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur ut = utDao.getById(u.getFk_utilisateur());
				
				u.setU(ut);
				
				Adresse_livraisonDAO adressDao = new Adresse_livraisonDAO();
				u.setAdresse(adressDao.getByIdCommande(u.getFk_adresse()));
				
				Details_commandeDAO dcDao = new Details_commandeDAO();
				Details_commande dc;
				
				ArrayList<Details_commande> details = dcDao.getAllByCommande(u.getId());
				u.setDetails(details);

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

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM commandes");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt("id"));
				u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
				u.setDate(resultat.getDate("date"));
				u.setTotal(resultat.getDouble("total"));
				u.setFk_adresse(resultat.getInt("fk_adresse"));
				u.setEtat(resultat.getInt("etat"));
				u.setArchiver(resultat.getInt("archiver"));

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=?");
			preparedStatement.setInt(1, idClient);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt("id"));
				u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
				u.setDate(resultat.getDate("date"));
				u.setTotal(resultat.getDouble("total"));
				u.setFk_adresse(resultat.getInt("fk_adresse"));
				u.setEtat(resultat.getInt("etat"));
				u.setArchiver(resultat.getInt("archiver"));

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM commandes WHERE fk_utilisateur=?");
			preparedStatement.setInt(1, idClient);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Commande u = new Commande();
				u.setId(resultat.getInt("id"));
				u.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
				u.setDate(resultat.getDate("date"));
				u.setTotal(resultat.getDouble("total"));
				u.setFk_adresse(resultat.getInt("fk_adresse"));
				u.setEtat(resultat.getInt("etat"));
				u.setArchiver(resultat.getInt("archiver"));

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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE commandes set archiver=? WHERE id=?");
			preparedStatement.setInt(1, c.getArchiver());
			preparedStatement.setInt(2, c.getId());

			preparedStatement.executeUpdate();

			System.out.println("ARCHIVE OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ARCHIVE NO");
		}
	}

	public int countTotalCommande() {
		int c = 0;
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT count(*) FROM commandes;");

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

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT count(*) from commandes WHERE commandes.date > now() - INTERVAL 24 hour;");

			ResultSet resultat = preparedStatement.executeQuery();

			if (!resultat.next()) {
				System.out.println(0);
				return 0;
			} else {
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

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM commandes WHERE id=?");
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

	/********************
	 * 
	 * 		MÉTHODES
	 * 
	 ********************/
	
	// RETRIEVE DATE FIRST ORDER USER
	public String dateFirstOrder( int id ) {
		
		try {
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT `fk_utilisateur`, MIN(date) FROM `commandes` WHERE `fk_utilisateur`=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Date date = rs.getDate("MIN(date)");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format( date );
			
			return strDate;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	// RETRIEVE DATE LAST ORDER USER
	public String dateLastOrder( int id ) {
		
		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT `fk_utilisateur`, MAX(date) FROM `commandes` WHERE `fk_utilisateur`=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Date date = rs.getDate("MAX(date)");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format( date );
			
			return strDate;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	// RETRIEVE AVERAGE AMOUNT ORDER USER
	public Double orderAverage( int id ) {
		
		try {
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT fk_utilisateur, AVG(total) FROM `commandes` WHERE `fk_utilisateur`=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Double d = rs.getDouble("AVG(total)");
			
			return d;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	// RETRIEVE SUM ORDERS USER
	public Double ordersSum( int id ) {
		
		try {
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT fk_utilisateur, SUM(total) FROM `commandes` WHERE `fk_utilisateur`=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Double d = rs.getDouble("SUM(total)");
			
			return d;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// COUNT ALL ORDERS CLICKS BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return String
	public String countAllOrdersByOneUserPerEachMonthOfTheCurrentYear (int fk_user) {
		
		
		String s = "";
		String s2 = "";
		String s31 = "";
		ArrayList<String> s31Col = new ArrayList<>();
		String s30 = "";
		ArrayList<String> s30Col = new ArrayList<>();	
		
		String year = DateManipulator.dateGetCurrentYYYY();
		
		try {
			
			for (int i = 1; i <= 12; i++) {
				
				// Février année bisextile ???
				if ( i == 2 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `commandes` WHERE `fk_utilisateur`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-27\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s2 += String.valueOf(c) + ",";
					
				}

				// Mois à 31 jours
				if ( i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `commandes` WHERE `fk_utilisateur`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-31\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s31 = String.valueOf(c) + ",";
					s31Col.add(s31);
					
				}
				
				// Mois à 30 jours
				if ( i == 4 || i == 6 || i == 9 || i == 11 ) {
					
					PreparedStatement ps = Database.connexion
							.prepareStatement("SELECT COUNT(*) FROM `commandes` WHERE `fk_utilisateur`=? "
									+ "AND `date` BETWEEN \"" + year + "-" + i + "-01\" AND \"" + year + "-" + i + "-31\"");
					ps.setInt(1, fk_user);
					
					ResultSet rs = ps.executeQuery();
					
					rs.next();
					
					Integer c = rs.getInt("COUNT(*)");
					
					s30 = String.valueOf(c) + ",";
					s30Col.add(s30);
					
				}
			}
//				System.out.println("s2 : " + s2);
//				System.out.println("s31Col : " + s31Col);
//				System.out.println("s30Col : " + s30Col);
			s = s31Col.get(0) 		// janvier
					+ s2 			// février
					+ s31Col.get(1) // mars
					+ s30Col.get(0) // avril
					+ s31Col.get(2) // mai
					+ s30Col.get(1) // juin
					+ s31Col.get(3) // juillet
					+ s31Col.get(4) // août
					+ s30Col.get(2) // septembre
					+ s31Col.get(5) // octobre
					+ s30Col.get(3) // novembre
					+ s31Col.get(6);// décembre
			
			s = s.substring( 0, s.length() - 1 ); // retirer la dernière virgule
//			System.out.println("s : " + s);
			
			return s;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}

	}
	
	// COUNT ALL ORDERS BY ONE USER PER EACH MONTH OF THE CURRENT YEAR
	// return int
	public int countAllOrdersByOneUser (int fk_user) {
		
		try {
			
			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT COUNT(*) FROM `commandes` WHERE `fk_utilisateur`=? ");
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
}