package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Adresse_livraison;
import model.Commande;
import model.Details_commande;
import model.Produit;
import service_adresse_livraison.Service_adresse_livraison;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import dao.Adresse_livraisonDAO;
import dao.CommandeDAO;
import dao.Details_commandeDAO;
import dao.ProduitDAO;

/**
 * Servlet implementation class DetailCommande
 */
public class DetailCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommandeDAO cmdDao;
	private Details_commandeDAO detailDao;
	private Details_commande detail;
	private Commande cmd;
	private ArrayList<Adresse_livraison> adresses;
	private ProduitDAO prodDao;
	
	
	private int id;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//try {
			
		id =  Integer.parseInt(request.getParameter("id"));
		
		Database.Connect();
		cmdDao = new CommandeDAO();
		
		if(id > 0) {
			
			fillCommande(id);
			request.setAttribute("commande", cmd);
			System.out.println(cmd);
			request.setAttribute("adresses", adresses);
		}

		
		if(request.getParameter("retirer") != null) {
			System.out.println("retirer");
			
		}
		
		if(request.getParameter("rechercher") != null) {
			System.out.println("rechercher");
			
			cmd = cmdDao.getById(Integer.parseInt(request.getParameter(Constantes.idcommande)));
			fillCommande(Integer.parseInt(request.getParameter(Constantes.idcommande)));
			request.setAttribute("commande", cmd);
			request.setAttribute("adresses", adresses);
			
			//boucle infini
			String path = "/DetailCommande?id=" + cmd.getId(); 
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(path);    
			rd.include(request, response);
		}
		
		if(request.getParameter(Constantes.modifierQte) != null) {
			
			System.out.println("modifier qte" + request.getParameter(Constantes.detailId));
			
			int qte = Integer.parseInt( request.getParameter("qte"));
			detailDao = new Details_commandeDAO();
			Details_commande dt = detailDao.getById(Integer.parseInt(request.getParameter(Constantes.detailId)));
			int oldQte = dt.getQte();
			int diff = 0;

			prodDao = new ProduitDAO();
			Produit p = prodDao.getById(dt.getFk_produit());
			
			if(p.getStock() < qte && p.getStock() < p.getStock_min()) {
				request.setAttribute("message", "Le stock actuel sur ce produit ne permet pas d'en augmenter la quantité sur cette commande.");
				
			}else if (p.getStock() >= p.getStock_min() && p.getStock() > qte ) {
				
				System.out.println("on change la quantité");
				
				//sauvegarde du detail + nouvelle quantité
				dt.setQte(qte);
				detailDao.save(dt);
				
				// maj commande courante, retrait du detail par l'id et par itération
				Iterator<Details_commande> it = cmd.getDetails().iterator();
				
				while(it.hasNext()) {
					if(it.next().getId() == dt.getId()){
						it.remove();
					}
				}
				

				cmd.getDetails().add(dt);
				//System.out.println(cmd.toString());
				cmdDao.save(cmd);
				
				int stock = p.getStock();
				System.out.println("stock:" + stock);
				//System.out.println(diff);
				if(qte > oldQte) {
					diff = qte - oldQte;
					System.out.println(diff);
					p.setStock(stock - diff);
					
				}
				if (qte < oldQte) {
					diff = oldQte - qte;
					System.out.println(diff);
					p.setStock(stock + diff);
					
				}
				diff = 0; qte = 0; oldQte = 0;
				prodDao.save(p);
			}

		}
		
		if(request.getParameter(Constantes.augmenterQte) != null) {
			
			System.out.println("augmenter");
			
			request.getAttribute(Constantes.detailId);
			detailDao = new Details_commandeDAO();
			Details_commande dt = detailDao.getById(Integer.parseInt(request.getParameter(Constantes.detailId)));
			
			prodDao = new ProduitDAO();
			Produit p = prodDao.getById(dt.getFk_produit());
			
			if(p.getStock() <= p.getStock_min() || p.getStock() == 0) {
				request.setAttribute("message", "Le stock actuel sur ce produit ne permet pas d'en augmenter la quantité sur cette commande.");
				
			}else if (p.getStock() >= p.getStock_min() + 10) {
				int qte = dt.getQte() + 1;
				dt.setQte(qte);
				
				System.out.println(dt);
				detailDao.save(dt);
				
				// maj commande
				Iterator<Details_commande> it = cmd.getDetails().iterator();
				
				while(it.hasNext()) {
					if(it.next().getId() == dt.getId()){
						it.remove();
					}
				}
				cmd.getDetails().add(dt);
				//System.out.println(cmd.toString());
				cmdDao.save(cmd);
				
				p.setStock(p.getStock() - 1);
				prodDao.save(p);
			}

		}
		
		if(request.getParameter(Constantes.diminuerQte) != null) {
			System.out.println("diminuer");
			request.getAttribute(Constantes.detailId);
			detailDao = new Details_commandeDAO();
			Details_commande dt = detailDao.getById(Integer.parseInt(request.getParameter(Constantes.detailId)));
			
			prodDao = new ProduitDAO();
			Produit p = prodDao.getById(dt.getFk_produit());
			
			int qte = dt.getQte() - 1;
			dt.setQte(qte);
			detailDao.save(dt);
			
			// maj commande
			Iterator<Details_commande> it = cmd.getDetails().iterator();
			
			while(it.hasNext()) {
				if(it.next().getId() == dt.getId()){
					it.remove();
				}
			}
			cmd.getDetails().add(dt);
			//System.out.println(cmd.toString());
			cmdDao.save(cmd);
			
			p.setStock(p.getStock() + 1);
			prodDao.save(p);
			
		}
		
		if(request.getParameter(Constantes.supprimer) != null) {	
			System.out.println("supp detail");
			
			Iterator<Details_commande> it = cmd.getDetails().iterator();
			
			while(it.hasNext()) {
				if(it.next().getId() == Integer.parseInt(request.getParameter(Constantes.detailId))){
					it.remove();
				}
			}
			System.out.println(cmd.getDetails());
			//cmdDao.save(cmd);
	
		}
		
		if(request.getParameter(Constantes.modifier) != null) {
			System.out.println("action requise modifier commande");
			System.out.println(request.getParameter("id"));
			Commande c  = new Commande();
			c.setId(Integer.parseInt(request.getParameter("id")));
			
			detail = new Details_commande();
			
			for(Details_commande dl : c.getDetails()) {
				
			}	
		}
		
		if(request.getParameter("nouvelleAdresse") != null) {
			System.out.println("nouvelle adresse");
			Service_adresse_livraison sal = new Service_adresse_livraison();
			request.setAttribute("messageNouvelleAdresse", sal.form_nouvelle_adresse(request, cmd.getU()));	
		}           
		
//		}catch(NullPointerException ne) {
//			request.getRequestDispatcher("/ficheCommande2.jsp").forward(request, response);
//		}
	
		request.getRequestDispatcher("/ficheCommande2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void fillCommande(int id) {
		cmd = cmdDao.getByIdCommandeUserAdressAndDetails(id);
		Adresse_livraisonDAO adressDao = new Adresse_livraisonDAO();
		adresses = adressDao.getAllByClient(cmd.getFk_utilisateur());
	}
	
}
