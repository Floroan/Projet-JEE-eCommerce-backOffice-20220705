package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import tools.Database;

import java.io.IOException;

import dao.CategorieDAO;

/**
 * Servlet implementation class GestionCategorie
 */
public class GestionCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategorieDAO catDao;
	private Categorie cat;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database.Connect();
		catDao = new CategorieDAO();
		
		if(request.getParameter("ajoutCat") != null) {
			cat = new Categorie();
			cat.setTitre(request.getParameter("ajoutTitre"));
			cat.setArchiver(Integer.parseInt(request.getParameter("ajoutArchive")));
			catDao.save(cat);
		}
		
		if(request.getParameter("editcat") != null) {
			System.out.println(request.getParameter("catTitre"));
			System.out.println(request.getParameter("idcat"));
			cat = new Categorie();
			cat.setTitre(request.getParameter("catTitre"));
			cat.setId(Integer.parseInt(request.getParameter("catId")));
			catDao.save(cat);
		}
		
		if(request.getParameter("archivecat") != null) {
			System.out.println(request.getParameter("catTitre"));
			System.out.println(request.getParameter("idcat"));
			
			cat = catDao.getById(Integer.parseInt(request.getParameter("catId")));
			
			if(cat.getArchiver() == 0) {
				cat.setArchiver(1);
			}
			else if(cat.getArchiver() == 1) {
				cat.setArchiver(0);
			}
			catDao.archiverById(cat);
		}
		request.setAttribute("cats", catDao.getAll());
		request.getRequestDispatcher("/gestion_categories.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
