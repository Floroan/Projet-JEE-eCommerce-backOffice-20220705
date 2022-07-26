package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Sous_categorie;
import tools.Database;

import java.io.IOException;

import dao.CategorieDAO;
import dao.Sous_categorieDAO;

/**
 * Servlet implementation class GestionSousCategorie
 */
public class GestionSousCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CategorieDAO catDao;
	private Sous_categorieDAO scatDao;
	private Sous_categorie ss_cat;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionSousCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Database.Connect();
		catDao = new CategorieDAO();
		scatDao = new Sous_categorieDAO();
		
		if(request.getParameter("ajoutSsCat") != null) {
			ss_cat = new Sous_categorie();
			ss_cat.setTitre(request.getParameter("ajoutTitre"));
			ss_cat.setArchiver(Integer.parseInt(request.getParameter("ajoutArchive")));
			ss_cat.setFk_categorie(Integer.parseInt(request.getParameter("associerCat")));
			scatDao.save(ss_cat);
		}
		
		if(request.getParameter("editSscat") != null) {
			ss_cat = new Sous_categorie();
			ss_cat.setId(Integer.parseInt(request.getParameter("sscatId")));
			ss_cat.setTitre(request.getParameter("sscatTitre"));

			ss_cat.setFk_categorie(Integer.parseInt(request.getParameter("changerCat")));
			scatDao.save(ss_cat);
		}
		
		if(request.getParameter("archiveSscat") != null) {
			ss_cat = scatDao.getById(Integer.parseInt(request.getParameter("sscatId")));

			if(ss_cat.getArchiver() == 0) {
				ss_cat.setArchiver(1);
			}
			else if(ss_cat.getArchiver() == 1) {
				ss_cat.setArchiver(0);
			}
			scatDao.archiverById(ss_cat);
		}
		
		request.setAttribute("ss_cats", scatDao.getAll());
		request.setAttribute("cats", catDao.getAll());
		request.getRequestDispatcher("/gestion_sous_categories.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
