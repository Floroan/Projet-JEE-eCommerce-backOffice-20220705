package service_adresse_livraison;

import dao.Adresse_livraisonDAO;
import jakarta.servlet.http.HttpServletRequest;
import model.Adresse_livraison;
import model.Utilisateur;
import tools.Constantes;
import tools.Database;
import tools.Fields;

public class Service_adresse_livraison {

	private  Adresse_livraisonDAO adressDAO;
	
	public Service_adresse_livraison() {
		Database.Connect();	
	}
	
	public void save_une_adresse(HttpServletRequest r){
		Adresse_livraison add = new Adresse_livraison();
		r.getParameter(null);
		adressDAO = new Adresse_livraisonDAO();
		adressDAO.save(add);
	}
	
	public void form_nouvelle_adresse(HttpServletRequest r, Utilisateur ut) throws NullPointerException{
		
		Adresse_livraison add = new Adresse_livraison();
		add.setAdresse(r.getParameter(Constantes.newAdresse));
		add.setVille(r.getParameter(Constantes.newVille));
		add.setCp(r.getParameter(Constantes.newCP));
		add.setPays(r.getParameter(Constantes.newPays));
		//add.setArchiver(Integer.parseInt(r.getParameter(Constantes.archiver)));		
		add.setFk_user(ut.getId());
		
		adressDAO = new Adresse_livraisonDAO();
		adressDAO.save(add);
	}
	
}
