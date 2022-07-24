package control;

import java.io.IOException;
import java.util.ArrayList;

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
import model.Produit;
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
		
		int id = Integer.parseInt( request.getParameter("id") );
		
		Database.Connect();
		
		UtilisateurDAO ud = new UtilisateurDAO();
		VisiteDAO vd = new VisiteDAO();
		ProduitDAO pd = new ProduitDAO();
		RechercheDAO rd = new RechercheDAO();
		ContactDAO cd = new ContactDAO(); // mails envoyés par User
		CommentaireDAO comD = new CommentaireDAO();
		CommandeDAO commandeD = new CommandeDAO();
		
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
		
		request.getRequestDispatcher("userClientCard.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
