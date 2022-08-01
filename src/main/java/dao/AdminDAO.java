package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Admin;
import tools.Database;

public class AdminDAO {

	public void save(Admin obj) {

		try {

			if (obj.getId() != 0) {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"UPDATE admin set nom=?, email=?, password=?, privileges=?, archiver=? WHERE id=?");
				preparedStatement.setString(1, obj.getNom());
				preparedStatement.setString(2, obj.getEmail());
				preparedStatement.setString(3, obj.getPassword());
				preparedStatement.setString(4, obj.getPrivileges());
				preparedStatement.setInt(5, obj.getArchiver());

				preparedStatement.setInt(6, obj.getId());
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = Database.connexion.prepareStatement(
						"INSERT INTO admin (nom, email, password, privileges, archiver) VALUES(?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, obj.getNom());
				preparedStatement.setString(2, obj.getEmail());
				preparedStatement.setString(3, obj.getPassword());
				preparedStatement.setString(4, obj.getPrivileges());
				preparedStatement.setInt(5, obj.getArchiver());

				preparedStatement.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Admin getById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM admin WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			Admin u = new Admin();
			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setEmail(resultat.getString("email"));
				u.setPassword(resultat.getString("password"));
				u.setPrivileges(resultat.getString("privileges"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Admin getOne(Admin u) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM admin WHERE nom=? AND password=?");
			preparedStatement.setString(1, u.getNom());
			preparedStatement.setString(2, u.getPassword());

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setEmail(resultat.getString("email"));
				u.setPassword(resultat.getString("password"));
				u.setPrivileges(resultat.getString("privileges"));
				u.setArchiver(resultat.getInt("archiver"));
			}
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Admin getOneByNameMailPass(String name, String mail, String pass) throws DaoException {
		
		Connection connexion = null;
		PreparedStatement ps = null;
		
		try {
			connexion = Database.connexion;
			ps = connexion.prepareStatement("SELECT * FROM admin WHERE nom=? AND email=? AND password=?");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, pass);
			
			ResultSet resultat = ps.executeQuery();
			
			Admin u = new Admin();
			if (resultat.next()) {

				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setEmail(resultat.getString("email"));
				u.setPassword(resultat.getString("password"));
				u.setPrivileges(resultat.getString("privileges"));
				u.setArchiver(resultat.getInt("archiver"));
				return u;
				
			} else {		
				return null;
			}

		} catch (Exception e) { // normalement SQLException
//			try {
//				if (connexion != null) {	
//					connexion.rollback();
//				}
//			} catch (SQLException ex2) {
//			}
//			ex.printStackTrace();
//			return null;
			throw new DaoException("Impossible de communiquer avec la base de données.");
			
		} finally {
			try {
				if ( connexion != null ) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec le serveur.");
			}
		}

	}

	public ArrayList<Admin> getAll() {
		ArrayList<Admin> list = new ArrayList<Admin>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM admin");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Admin u = new Admin();
				u.setId(resultat.getInt("id"));
				u.setNom(resultat.getString("nom"));
				u.setEmail(resultat.getString("email"));
				u.setPassword(resultat.getString("password"));
				u.setPrivileges(resultat.getString("privileges"));
				u.setArchiver(resultat.getInt("archiver"));

				list.add(u);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("DELETE FROM admin WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
}
