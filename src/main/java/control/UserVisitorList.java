package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProduitDAO;
import dao.VisiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produit;
import model.Visite;
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

		Database.Connect();

		ProduitDAO pd = new ProduitDAO();
		VisiteDAO vd = new VisiteDAO();

		// DATATABLE : LISTE DES PRODUITS PAR NOMBRE DE CLICKS

		ArrayList<Visite> vb = vd.getAllByClient(1);
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
