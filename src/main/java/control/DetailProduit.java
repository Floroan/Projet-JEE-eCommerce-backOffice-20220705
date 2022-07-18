package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produit;
import tools.Database;

import java.io.IOException;

import dao.CategorieDAO;
import dao.ProduitDAO;
import dao.Sous_categorieDAO;

/**
 * Servlet implementation class DetailProduit
 */
public class DetailProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CategorieDAO catDao;
	private Sous_categorieDAO scatDao;
	private ProduitDAO prodDao;
	private Produit prod;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Database.Connect();
		catDao = new CategorieDAO();
		prodDao = new ProduitDAO();
		request.setAttribute("cats", catDao.getAll());
		scatDao = new Sous_categorieDAO();
		request.setAttribute("ss_cats", scatDao.getAll());
		prod =  prodDao.getById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("prod", prod);
		request.setAttribute("ss_cat_produit", scatDao.getById(prod.getFk_sous_categorie()));
		
		if(request.getParameter("modifier") != null) {
			System.out.println("go");
		}
		
		if(request.getParameter("archiver") != null) {
			System.out.println("go");
		}
		
		if(request.getParameter("recImg") != null) {
			System.out.println("go");
		}
		
		
		request.getRequestDispatcher("/ficheProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
