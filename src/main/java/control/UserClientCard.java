package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.Adresse_livraisonDAO;
import dao.CommandeDAO;
import dao.CommentaireDAO;
import dao.ContactDAO;
import dao.ProduitDAO;
import dao.RechercheDAO;
import dao.UtilisateurDAO;
import dao.VisiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Adresse_livraison;
import model.Produit;
import model.Utilisateur;
import tools.DataTablesListeClients;
import tools.Database;

/**
 * Servlet implementation class UserClientCard
 */
public class UserClientCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClientCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Database.Connect();
		
		UtilisateurDAO ud = new UtilisateurDAO();
		VisiteDAO vd = new VisiteDAO();
		ProduitDAO pd = new ProduitDAO();
		RechercheDAO rd = new RechercheDAO();
		ContactDAO cd = new ContactDAO(); // mails envoyés par User
		CommentaireDAO comD = new CommentaireDAO();
		CommandeDAO commandeD = new CommandeDAO();
		Adresse_livraisonDAO ad = new Adresse_livraisonDAO();
		
		
		/*
		 * HREF 
		 */
		// from userList.jsp
		// from POST from Servlet UserClientCard
		int id = Integer.parseInt( request.getParameter("id") );
		// from POST from Servlet UserClientCard
		if ( request.getParameter("msg") != null ) {
			
			if ( request.getParameter("msg").equals("yes") ) {
				
				String msg = "Avec le statut archivé, vous ne pouvez pas mettre à jour cette fiche.";
				System.out.println(request.getParameter("msg"));
				request.setAttribute("msg", msg);
				
			}

		}
		
		
		// STATISTIQUES DES COMMANDES
		DataTablesListeClients dc = new DataTablesListeClients();
		Utilisateur ub = ud.getById(id);
		
		dc.setDateFirstOrder( commandeD.dateFirstOrder( ub.getId()) );
		dc.setDateLastOrder( commandeD.dateLastOrder( ub.getId() ) );
		dc.setOrderAverage( commandeD.orderAverage( ub.getId() ) );
		dc.setOrderSum( commandeD.ordersSum( ub.getId() ) );
		
		/*
		 * CAMEMBERT Cliques par article - id="chart9" : clique(s) par article
		 */
		ArrayList<Produit> pbCol = pd.getAll();
		
		String title = "";
		String counts = "";
		
		for (Produit p : pbCol) {
			
			title += "'" + p.getTitre().substring(0, 10) + "',";
			counts += vd.sumOfProductClicksByOneUserForOneProduct(id, p.getId()) + ",";
			
		}
		title.substring( 0, title.length() - 1 ); // retirer la ,
		counts.substring( 0, counts.length() - 1 );
		
		/*
		 * GRAPHIQUE Nombre de cliques article par mois - id="chart5" : total clique(s) par mois
		 */
		String productsClickedPerMonth = vd.countAllProductClicksByOneUserPerEachMonthOfTheCurrentYear(id);
		
		/*
		 * GRAPHIQUE Nombre de recherches par mois - id="chart1" : total clique(s) par mois
		 */
		String countResearchesPerMonth = rd.countAllResearchesByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalResearches = rd.countAllResearchesByOneUser(id);
		
		/*
		 * GRAPHIQUE Nombre de message par mois - id="chart2" : total clique(s) par mois
		 * Table : contacts
		 */
		String countMessagesPerMonth = cd.countAllMessagesByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalMessages = cd.countAllMessagesByOneUser(id);
		
		/*
		 * GRAPHIQUE Nombre de commentaires par mois - id="chart3" : total clique(s) par mois
		 */
		String countCommentsPerMonth = comD.countAllCommentsByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalComments = comD.countAllCommentsByOneUser(id);
		
		/*
		 * GRAPHIQUE Nombre de commandes par mois - id="chart4" : total clique(s) par mois
		 */
		String countOrdersPerMonth = commandeD.countAllOrdersByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalCommandes = commandeD.countAllOrdersByOneUser(id);
		
		/*
		 * FORMULAIRES
		 */
		// BOUTON ARCHIVER USER
		if ( request.getParameter("archived") != null ) {
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				
				int idHref = Integer.parseInt( request.getParameter("id") );
				ud.archiverById(idHref, 0);
				
			} 
			
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int idForm = Integer.parseInt( request.getParameter("id") );
				ud.archiverById(idForm, 1);
				
			}
		}
		
		// DELETE ADRESSE DE LIVRAISON
		if ( request.getParameter("delete") != null ) {
			
			int idAddress = Integer.parseInt( request.getParameter("idAddress") );
			ad.deleteById(idAddress);
			
		}
		
		request.setAttribute("ub", ud.getById(id));
		request.setAttribute("dc", dc);
		request.setAttribute("char_cats_titre", title);
		request.setAttribute("char_cats_nbr", counts);
		request.setAttribute("cliksPerProduct", productsClickedPerMonth);
		request.setAttribute("cliksPerResearch", countResearchesPerMonth);
		request.setAttribute("totalResearches", totalResearches);
		request.setAttribute("countMessagesPerMonth", countMessagesPerMonth);
		request.setAttribute("totalMessages", totalMessages);
		request.setAttribute("countCommentsPerMonth", countCommentsPerMonth);
		request.setAttribute("totalComments", totalComments);
		request.setAttribute("countOrdersPerMonth", countOrdersPerMonth);
		request.setAttribute("totalCommandes", totalCommandes);
		request.setAttribute("abCol", ad.getAllByClient(id)); // AFFICHER LES ADRESSE DE LIVRAISON
		
		request.getRequestDispatcher("userClientCard.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		
		/*
		 * 
		 * ATTENTION TRÈS DANGEREUX MAIS C’EST MON CHOIX ;)
		 * toujours entrer dans getPost avec name="id" (id du user) sinon ça va planter
		 * 
		 */
		int id = Integer.parseInt( request.getParameter("id") );
		
		
		Database.Connect();
		
		// FORM METTRE À JOUR USER
		if ( request.getParameter("updateProfileForm") != null ) {
		
			UtilisateurDAO ud = new UtilisateurDAO();
			Utilisateur u = ud.getById( id );
			System.out.println(u);
			
			String msg = "no";
			if ( u.getArchiver() == 1 ) {
				
				msg = "yes";
				
			} else {
				
				String fn = request.getParameter( "row-1-nom" );
				String ln = request.getParameter( "row-1-prenom" );
				String e = request.getParameter( "row-1-mail" );
				
				u.setNom(fn);
				u.setPrenom(ln);
				u.setEmail(e);
				
				System.out.println(u);
				ud.save(u);
				
			}
			
			response.sendRedirect("UserClientCard?id=" + id + "&msg=" + msg);
			
		}
		
		// FORM AJOUTER UNE NOUVELLE ADRESSE
		if (request.getParameter("addAddressForm") != null ) {
			
			Adresse_livraisonDAO ad = new Adresse_livraisonDAO();
			Adresse_livraison ab = new Adresse_livraison();
			
			String address = request.getParameter( "address" );
			String cp = request.getParameter( "cp" );
			String ville = request.getParameter( "city" );
			String pays = request.getParameter( "country" );
			
			ab.setFk_user(id);
			ab.setAdresse(address);
			ab.setCp(cp);
			ab.setVille(ville);
			ab.setPays(pays);
			
			ad.save(ab);
			
			response.sendRedirect("UserClientCard?id=" + id);
			
		}
		
		// FORM METTRE À JOUR ADRESSE
		if (request.getParameter("updateAddressForm") != null ) {
			
			Adresse_livraisonDAO ad = new Adresse_livraisonDAO();
			Adresse_livraison ab = new Adresse_livraison();
			
			int idAddress = Integer.parseInt( request.getParameter("idAddress") );
			System.out.println("request : " + request.getParameter("idAddress"));
			System.out.println("idAddress : " + idAddress);
			String address = request.getParameter( "address" );
			String cp = request.getParameter( "cp" );
			String ville = request.getParameter( "city" );
			String pays = request.getParameter( "country" );
			
			ab.setId(idAddress);
			ab.setFk_user(id);
			ab.setAdresse(address);
			ab.setCp(cp);
			ab.setVille(ville);
			ab.setPays(pays);
			System.out.println(ab);
			ad.save(ab);
			
			response.sendRedirect("UserClientCard?id=" + id);
			
		}
	}

}
