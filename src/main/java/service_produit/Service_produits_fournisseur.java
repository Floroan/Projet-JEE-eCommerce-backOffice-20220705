package service_produit;

import dao.Entree_stockDAO;
import dao.FournisseurDao;
import dao.ProduitDAO;
import model.Entree_stock;
import model.Produit;
import tools.Database;

public class Service_produits_fournisseur {

	private static ProduitDAO prodDAO;
	private static Entree_stockDAO stockDAO;
	private static FournisseurDao fournisseurDAO;
	
	
	public static Produit produitEntreeStockFournisseur(int id) {
		
		Database.Connect();
		prodDAO = new ProduitDAO();
		Produit p = prodDAO.getById(id);
		stockDAO = new Entree_stockDAO();
		fournisseurDAO = new FournisseurDao();
		p.setEntrees_stock(stockDAO.getAllByProduit(id));
		
		for(Entree_stock es: p.getEntrees_stock()) {
			es.setF(fournisseurDAO.getById(es.getFk_fournisseur()));
		}
		return p;
	}
	
}
