package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.ContactDAO;
import dao.ProduitDAO;
import dao.RechercheDAO;
import dao.VisiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produit;
import tools.DataTablesListeProductsClickedByOneUser;
import tools.Database;

/**
 * Servlet implementation class UserVisitorList
 */
public class UserVisitorList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserVisitorList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		int id = 1; // Visitor
		Database.Connect();

		ProduitDAO pd = new ProduitDAO();
		VisiteDAO vd = new VisiteDAO();
		RechercheDAO rd = new RechercheDAO();
		ContactDAO cd = new ContactDAO(); // mails envoyés par User
		
		/*
		 * CAMEMBERT Cliques par article - id="chart9" : clique(s) par article
		 */
		ArrayList<Produit> pbCol = pd.getAll();
		ArrayList<Integer> countsCol = new ArrayList<>();

		String title = "";
		String titleLong = "";
		String counts = "";
		String countsLong = "";

		for (Produit p : pbCol) {

			countsCol.add(vd.sumOfProductClicksByOneUserForOneProduct(id, p.getId()));

		}

		int i = -1;
		for (Produit p : pbCol) {

			i++;

			if (countsCol.get(i) > 0) {

				if (p.getTitre().length() > 20) {

					titleLong += "'" + p.getTitre().substring(0, 20) + "',";
					countsLong += vd.sumOfProductClicksByOneUserForOneProduct(id, p.getId()) + ",";

				} else {

					title += "'" + p.getTitre() + "',";
					counts += vd.sumOfProductClicksByOneUserForOneProduct(id, p.getId()) + ",";

				}
			}
		}
		title += titleLong;
		counts += countsLong;
		System.out.println(title);
		if (title.length() > 0) {

			title.substring(0, title.length() - 1); // retirer la dernière virgule
			counts.substring(0, counts.length() - 1);

		}

		/*
		 * GRAPHIQUE Nombre de cliques article par mois - id="chart5" : total clique(s)
		 * par mois
		 */
		String productsClickedPerMonth = vd.countAllProductClicksByOneUserPerEachMonthOfTheCurrentYear(id);

		/*
		 * GRAPHIQUE Nombre de recherches par mois - id="chart1" : total clique(s) par
		 * mois
		 */
		String countResearchesPerMonth = rd.countAllResearchesByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalResearches = rd.countAllResearchesByOneUser(id);

		/*
		 * GRAPHIQUE Nombre de message par mois - id="chart2" : total clique(s) par mois
		 * Table : contacts
		 */
		String countMessagesPerMonth = cd.countAllMessagesByOneUserPerEachMonthOfTheCurrentYear(id);
		int totalMessages = cd.countAllMessagesByOneUser(id);
		

		// DATATABLE : LISTE DES PRODUITS PAR NOMBRE DE CLICKS
//		ArrayList<Visite> vb = vd.getAllByClient(1);
		ArrayList<Produit> pb = pd.getAll();
		ArrayList<DataTablesListeProductsClickedByOneUser> dcList = new ArrayList<DataTablesListeProductsClickedByOneUser>();

		for (Produit p : pb) {

			DataTablesListeProductsClickedByOneUser dc = new DataTablesListeProductsClickedByOneUser();
			
			dc.setImage(p.getImage());
			dc.setTitre(p.getTitre());
			dc.setSumOfProductClicks( vd.sumOfProductClicksByOneUserForOneProduct( 1, p.getId() ) );

			dcList.add(dc);
		}
		System.out.println(dcList);

		request.setAttribute("char_cats_titre", title);
		request.setAttribute("char_cats_nbr", counts);
		request.setAttribute("cliksPerProduct", productsClickedPerMonth);
		request.setAttribute("cliksPerResearch", countResearchesPerMonth);
		request.setAttribute("totalResearches", totalResearches);
		request.setAttribute("countMessagesPerMonth", countMessagesPerMonth);
		request.setAttribute("totalMessages", totalMessages);
		request.setAttribute("dcList", dcList);
		request.getRequestDispatcher("userVisitorList.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
