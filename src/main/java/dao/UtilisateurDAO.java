package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Adresse_livraison;
import model.Commande;
import model.Commentaire;
import model.Utilisateur;
import model.Visite;
import tools.Database;

public class UtilisateurDAO {

	public void save(Utilisateur obj) {

		try {

			if (obj.getId() != 0) {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE utilisateurs set nom=?, prenom=?, date_inscription=?, email=?, password=?, archiver=? WHERE id=?");
				preparedStatement.setString(1, obj.getNom());
				preparedStatement.setString(2, obj.getPrenom());
				preparedStatement.setDate(3, obj.getDate_inscription());
				preparedStatement.setString(4, obj.getEmail());
				preparedStatement.setString(5, obj.getPassword());
				preparedStatement.setInt(6, obj.getArchiver());

				preparedStatement.setInt(7, obj.getId());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO utilisateurs (nom, prenom, date_inscription, email, password, archiver) VALUES(?,?,?,?,?,?)");
				preparedStatement.setString(1, obj.getNom());
				preparedStatement.setString(2, obj.getPrenom());
				preparedStatement.setDate(3, obj.getDate_inscription());
				preparedStatement.setString(4, obj.getEmail());
				preparedStatement.setString(5, obj.getPassword());
				preparedStatement.setInt(6, obj.getArchiver());

				preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Utilisateur getById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Utilisateur getByMail(String mail) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE email=?");
			preparedStatement.setString(1, mail);

			ResultSet resultat = preparedStatement.executeQuery();

			resultat.next();

			Utilisateur u = new Utilisateur();

			u.setId(resultat.getInt("id"));
			u.setNom(resultat.getString("nom"));
			u.setPrenom(resultat.getString("prenom"));
			u.setEmail(resultat.getString("email"));
			u.setDate_inscription(resultat.getDate("date_inscription"));
			u.setPassword(resultat.getString("password"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Utilisateur getOne(String name, String pass) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE nom=? AND password=? AND archiver=0");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, pass);
			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

// getOneWithReviews
	public Utilisateur getOneAndReviews(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT * FROM `utilisateurs` LEFT JOIN commentaires ON utilisateurs.id = commentaires.fk_user WHERE utilisateurs.id=? AND commentaires.archiver=0;");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();

			ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				Commentaire c = new Commentaire();

				c.setId(resultat.getInt("id"));
				c.setCommentaire(resultat.getString("commentaire"));
				c.setNote(resultat.getInt("note"));
				c.setFk_prod(resultat.getInt("fk_prod"));
				c.setFk_user(resultat.getInt("fk_user"));
				c.setArchiver(resultat.getInt("archiver"));
				commentaires.add(c);
				u.setCommentaires(commentaires);
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Utilisateur getOneAndCommandes(int id) {
		try {

			String rq_commandesAndAdresses = "SELECT * FROM `utilisateurs` LEFT JOIN commandes ON utilisateurs.id = commandes.fk_utilisateur LEFT JOIN adresses_livraison ON commandes.fk_adresse = adresses_livraison.id WHERE utilisateurs.id=3 AND commandes.archiver=0;";
			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT * FROM `utilisateurs` LEFT JOIN commandes ON utilisateurs.id = commandes.fk_user WHERE utilisateurs.id=? AND commandes.archiver=0;");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();

			ArrayList<Commande> commandes = new ArrayList<Commande>();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				Commande c = new Commande();

				c.setId(resultat.getInt("id"));
				c.setFk_utilisateur(resultat.getInt("fk_utilisateur"));
				c.setDate(resultat.getDate("date"));
				c.setTotal(resultat.getDouble("total"));
				c.setFk_adresse(resultat.getInt("fk_adresse"));
				c.setEtat(resultat.getInt("etat"));
				c.setArchiver(resultat.getInt("archiver"));

				Adresse_livraison add_livraison = new Adresse_livraison();

				commandes.add(c);
				u.setCommandes(commandes);
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Utilisateur getOneAnd(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement(
					"SELECT * FROM `utilisateurs` LEFT JOIN commentaires ON utilisateurs.id = commentaires.fk_user WHERE utilisateurs.id=? AND commentaires.archiver=0;");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();

			ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				Commentaire c = new Commentaire();

				c.setId(resultat.getInt("id"));
				c.setCommentaire(resultat.getString("commentaire"));
				c.setNote(resultat.getInt("note"));
				c.setFk_prod(resultat.getInt("fk_prod"));
				c.setFk_user(resultat.getInt("fk_user"));
				c.setArchiver(resultat.getInt("archiver"));
				commentaires.add(c);
				u.setCommentaires(commentaires);
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Utilisateur getOneByNameAndMail(String name, String mail) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE nom=? AND email=? AND archiver=0");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, mail);
			ResultSet resultat = preparedStatement.executeQuery();

			Utilisateur u = new Utilisateur();
			if (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));
				return u;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Utilisateur> getAll() {
		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM utilisateurs");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Utilisateur u = new Utilisateur();
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// RETRIEVE ALL WHO HAVE ORDER(S)
	public ArrayList<Utilisateur> getAllClients() {

		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();

		try {

			CommandeDAO od = new CommandeDAO();

			PreparedStatement ps = Database.connexion
					.prepareStatement(
							"SELECT * FROM `utilisateurs` WHERE id IN(SELECT fk_utilisateur FROM `commandes`)");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Utilisateur o = new Utilisateur();

				o.setId(rs.getInt("id"));
				o.setNom(rs.getString("nom"));
				o.setPrenom(rs.getString("prenom"));
				o.setDate_inscription(rs.getDate("date_inscription"));
				o.setEmail(rs.getString("email"));
				o.setPassword(rs.getString("password"));
				o.setArchiver(rs.getInt("archiver"));

				ArrayList<Commande> orders = od.getAllByClient(o.getId());
				o.setCommandes(orders);

				list.add(o);
			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;

		}
	}

	// RETRIEVE ALL WHO HAVE NO ORDER
	public ArrayList<Utilisateur> getAllProspect() {

		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();

		try {

			VisiteDAO vd = new VisiteDAO();

			PreparedStatement ps = Database.connexion
					.prepareStatement(
							"SELECT * FROM `utilisateurs` WHERE id!=1 AND id NOT IN(SELECT fk_utilisateur FROM `commandes`)");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Utilisateur o = new Utilisateur();

				o.setId(rs.getInt("id"));
				o.setNom(rs.getString("nom"));
				o.setPrenom(rs.getString("prenom"));
				o.setDate_inscription(rs.getDate("date_inscription"));
				o.setEmail(rs.getString("email"));
				o.setPassword(rs.getString("password"));
				o.setArchiver(rs.getInt("archiver"));

				ArrayList<Visite> v = vd.getAllByClient(o.getId());
				o.setVisites(v);

				list.add(o);
			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;

		}
	}

	public ArrayList<Utilisateur> getAllNotArchived() {
		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE archiver=0");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				Utilisateur u = new Utilisateur();

				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Utilisateur> getAllArchived() {
		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE archiver=1");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Utilisateur u = new Utilisateur();
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void archiverById(int id, int archiver) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE utilisateurs set archiver=? WHERE id=?");
			preparedStatement.setInt(1, archiver);
			preparedStatement.setInt(2, id);

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
					.prepareStatement("DELETE FROM utilisateurs WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

	public ArrayList<Utilisateur> getByLike(String crit) {
		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM utilisateurs WHERE nom LIKE ? OR prenom LIKE ?;");
			preparedStatement.setString(1, "%" + crit + "%");
			preparedStatement.setString(2, "%" + crit + "%");
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Utilisateur u = new Utilisateur();
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setPrenom(resultat.getString("prenom"));
				u.setEmail(resultat.getString("email"));
				u.setDate_inscription(resultat.getDate("date_inscription"));
				u.setPassword(resultat.getString("password"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}