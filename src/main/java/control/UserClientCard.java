package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProduitDAO;
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
		
		/*
		 * CAMEMBERT id="chart9" : clique(s) par article
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
		 * GRAPHIQUE id="chart5" : total clique(s) par mois
		 */
		String cliksPerProduct = vd.countAllProductClicksByOneUserPerEachMonthOfTheCurrentYear(id);
		
		request.setAttribute("char_cats_titre", title);
		request.setAttribute("char_cats_nbr", counts);
		request.setAttribute("cliksPerProduct", cliksPerProduct);
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
