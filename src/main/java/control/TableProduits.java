package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Produit;
import tools.Constantes;
import tools.Database;
import tools.RedirectError500;

import java.io.IOException;

import dao.ProduitDAO;

/**
 * Servlet implementation class TableProduits
 */
public class TableProduits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProduitDAO prodDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableProduits() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		Database.Connect();
		prodDao = new ProduitDAO();
		
		if(request.getParameter(Constantes.archiver) != null) {
			System.out.println("go archiver produit");
			Produit p = prodDao.getById(Integer.parseInt(request.getParameter(Constantes.idProd)));
			
			if(p.getArchiver() == 0) {
				p.setArchiver(1);
			}else if(p.getArchiver() == 1) {
				p.setArchiver(0);
			}			
			prodDao.archiverById(p);
		}
		
		///RedirectError500.redirect500(session, response);
		

		
		request.setAttribute("prods", prodDao.getAll());
		//request.getRequestDispatcher("/table_produits_body.jsp").forward(request, response);
		
		if(session.getAttribute("isConnected") == null) {
			request.getRequestDispatcher("/error500.jsp").forward(request, response);
			
		}else {
			request.getRequestDispatcher("/table_produits_body.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
