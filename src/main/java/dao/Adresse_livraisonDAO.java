package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Adresse_livraison;
import model.Utilisateur;
import tools.Database;

public class Adresse_livraisonDAO {

	public void save(Adresse_livraison obj) {

		try {

			if (obj.getId() != 0) {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE adresses_livraison SET fk_user=?, adresse=?, cp=?, ville=?, pays=?, archiver=? WHERE id=?");

				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getAdresse());
				preparedStatement.setString(3, obj.getCp());
				preparedStatement.setString(4, obj.getVille());
				preparedStatement.setString(5, obj.getPays());
				preparedStatement.setInt(6, obj.getArchiver());

				preparedStatement.setInt(7, obj.getId());
//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);

				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO adresses_livraison (fk_user, adresse, cp, ville, pays, archiver) VALUES(?,?,?,?,?,?)");
				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getAdresse());
				preparedStatement.setString(3, obj.getCp());
				preparedStatement.setString(4, obj.getVille());
				preparedStatement.setString(5, obj.getPays());
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

	public int saveAndReturnGeneratedId(Adresse_livraison obj) {
		int newId = 0;
		try {

			if (obj.getId() != 0) {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE adresses_livraison set fk_utilisateur=?, date=?, total=?, fk_adresse=?, etat=?, archiver=? WHERE id=?");
				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getAdresse());
				preparedStatement.setString(3, obj.getCp());
				preparedStatement.setString(4, obj.getVille());
				preparedStatement.setString(5, obj.getPays());
				preparedStatement.setInt(6, obj.getArchiver());

//				java.sql.Date d = DateUtilToDateSql.convert(obj.getDate());
//				preparedStatement.setDate(3, d);

				preparedStatement.setInt(7, obj.getId());

				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO adresses_livraison (idClient, dateCommande, totalCommande) VALUES(?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, obj.getFk_user());
				preparedStatement.setString(2, obj.getAdresse());
				preparedStatement.setString(3, obj.getCp());
				preparedStatement.setString(4, obj.getVille());
				preparedStatement.setString(5, obj.getPays());
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

	public Adresse_livraison getById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM adresses_livraison WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Adresse_livraison u = new Adresse_livraison();
			resultat.next();
			u.setId(resultat.getInt("id"));
			u.setFk_user(resultat.getInt("fk_user"));
			u.setAdresse(resultat.getString("adresse"));
			u.setCp(resultat.getString("cp"));
			u.setVille(resultat.getString("ville"));
			u.setPays(resultat.getString("pays"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Adresse_livraison getByIdCommande(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM adresses_livraison WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Adresse_livraison u = new Adresse_livraison();
			resultat.next();
			u.setId(resultat.getInt("id"));
			u.setFk_user(resultat.getInt("fk_user"));
			u.setAdresse(resultat.getString("adresse"));
			u.setCp(resultat.getString("cp"));
			u.setVille(resultat.getString("ville"));
			u.setPays(resultat.getString("pays"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Adresse_livraison> getAll() {
		ArrayList<Adresse_livraison> list = new ArrayList<Adresse_livraison>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM adresses_livraison");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Adresse_livraison u = new Adresse_livraison();
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setAdresse(resultat.getString("adresse"));
				u.setCp(resultat.getString("cp"));
				u.setVille(resultat.getString("ville"));
				u.setPays(resultat.getString("pays"));
				u.setArchiver(resultat.getInt("archiver"));

				UtilisateurDAO utDao = new UtilisateurDAO();
				Utilisateur ut = utDao.getById(u.getFk_user());

				u.setU(ut);

				list.add(u);
			}
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Adresse_livraison> getAllByClient(int idClient) {
		ArrayList<Adresse_livraison> list = new ArrayList<Adresse_livraison>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM adresses_livraison WHERE fk_user=?");
			preparedStatement.setInt(1, idClient);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Adresse_livraison u = new Adresse_livraison();
				u.setId(resultat.getInt("id"));
				u.setFk_user(resultat.getInt("fk_user"));
				u.setAdresse(resultat.getString("adresse"));
				u.setCp(resultat.getString("cp"));
				u.setVille(resultat.getString("ville"));
				u.setPays(resultat.getString("pays"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void archiveById(Adresse_livraison c) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE adresses_livraison set archiver=? WHERE id=?");
			preparedStatement.setInt(1, c.getArchiver());
			preparedStatement.setInt(2, c.getId());

			preparedStatement.executeUpdate();

			System.out.println("ARCHIVE OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ARCHIVE NO");
		}
	}

	public int countTotalAdresses() {
		int c = 0;
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT count(*) FROM adresses_livraison;");

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

	public void deleteById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("DELETE FROM adresses_livraison WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

}
