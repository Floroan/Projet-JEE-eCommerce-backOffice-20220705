package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Coordonnee;
import tools.Database;

public class CoordonneesDAO {

	// CREATE if !exist else UPDATE
  public void save(Coordonnee o) {
      try {

          if (o.getId() != 0) {
              
              PreparedStatement ps = Database.connexion
                      .prepareStatement("UPDATE coordonnees set nom=? adresse=? telephone=? email=? logo=? archiver=? WHERE id=?");
              ps.setString(1, o.getNom());
              ps.setString(2, o.getAdresse());
              ps.setString(3, o.getTelephone());
              ps.setString(4, o.getEmail());
              ps.setString(5, o.getLogo());
              ps.setInt(6, o.getArchiver());
              
              ps.setInt(7, o.getId());
              ps.executeUpdate();
              
          } else {
              
              PreparedStatement ps = Database.connexion
                      .prepareStatement("INSERT INTO coordonnees (nom, adresse, telephone, email, logo, archiver) VALUES(?, ?, ?, ?, ?, ?)");
              ps.setString(1, o.getNom());
              ps.setString(2, o.getAdresse());
              ps.setString(3, o.getTelephone());
              ps.setString(4, o.getEmail());
              ps.setString(5, o.getLogo());
              ps.setInt(6, o.getArchiver());
              
              ps.executeUpdate();
              
          }
          System.out.println("SAVED OK");

      } catch (Exception ex) {
          
          ex.printStackTrace();
          System.out.println("SAVED NO");
          
      }
  }
  
  public Coordonnee getById(int id) {
      try {

          PreparedStatement ps = Database.connexion
                  .prepareStatement("SELECT * FROM coordonnees WHERE id=?");
          ps.setInt(1, id);

          ResultSet rs = ps.executeQuery();
          
          Coordonnee o = new Coordonnee();
          
          rs.next();
          
          o.setId(rs.getInt("id"));
          o.setNom(rs.getString("nom"));
          o.setAdresse(rs.getString("adresse"));
          o.setTelephone(rs.getString("telephone"));
          o.setEmail(rs.getString("email"));
          o.setLogo(rs.getString("logo"));
          o.setArchiver(rs.getInt("archiver"));
                          
          return o;

      } catch (Exception ex) {
          
          ex.printStackTrace();
          return null;
          
      }
  }
  
  public void archivedById(Coordonnee c) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("UPDATE coordonnees SET archiver=? WHERE id=?");
				preparedStatement.setInt(1, c.getArchiver());
				preparedStatement.setInt(2, c.getId());
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("DELETED NO");
	    }
	}

  public void deleteById(int id) {
		try {
			
				PreparedStatement preparedStatement  = Database.connexion.prepareStatement("DELETE FROM coordonnees WHERE id=?");
				preparedStatement.setInt(1, id);
				
				preparedStatement.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	System.out.println("DELETED NO");
	    }
	}
}

