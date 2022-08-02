package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Fournisseur;
import tools.Database;

public class FournisseurDao {
	public int save(Fournisseur obj) {
		
		int newId = 0; // To return the Generated Key
		Connection c = null;
        PreparedStatement ps = null;
        
		try {
			c = Database.connexion;

			if (obj.getId() != 0) {
				ps = c.prepareStatement("UPDATE fournisseurs set nom=?, archiver=? WHERE id=?");
				ps.setString(1, obj.getNom());
				ps.setInt(2, obj.getArchiver());

				ps.setInt(3, obj.getId());
				ps.executeUpdate();
			} else {
				ps = c.prepareStatement("INSERT INTO fournisseurs (nom, archiver) VALUES(?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getNom());
				ps.setInt(2, obj.getArchiver());
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
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

	public Fournisseur getById(int id) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("SELECT * FROM fournisseurs WHERE id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			resultat.next();

			Fournisseur u = new Fournisseur();

			u.setId(resultat.getInt("id"));
			u.setNom(resultat.getString("nom"));
			u.setArchiver(resultat.getInt("archiver"));

			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fournisseur> getAll() {
		ArrayList<Fournisseur> list = new ArrayList<Fournisseur>();
		try {

			PreparedStatement preparedStatement = Database.connexion.prepareStatement("SELECT * FROM fournisseurs");

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Fournisseur o = new Fournisseur();
				o.setId(resultat.getInt("id"));
				o.setNom(resultat.getString("nom"));
				o.setArchiver(resultat.getInt("archiver"));
				list.add(o);
			}

			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Fournisseur> getAllNonArchiver() {
		
		ArrayList<Fournisseur> list = new ArrayList<Fournisseur>();
		
		try {

			PreparedStatement ps = Database.connexion
					.prepareStatement("SELECT * FROM fournisseurs WHERE archiver=0");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Fournisseur o = new Fournisseur();
				
				o.setId(rs.getInt("id"));
				o.setNom(rs.getString("nom"));
				o.setArchiver(rs.getInt("archiver"));
				
				list.add(o);
				
			}

			return list;

		} catch (Exception ex) {
			
			ex.printStackTrace();
			return null;
			
		}
	}

	public void archiverById(Fournisseur c) {
		try {

			PreparedStatement preparedStatement = Database.connexion
					.prepareStatement("UPDATE fournisseurs set archiver=? WHERE id=?");
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
					.prepareStatement("DELETE FROM fournisseurs WHERE id=?");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
}
