package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.Database;

import java.io.IOException;

import dao.RechercheDAO;

/**
 * Servlet implementation class TopRecherches
 */
public class TopRecherches extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private RechercheDAO rechDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopRecherches() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database.Connect();
		rechDao = new RechercheDAO();
		request.setAttribute("topRecherches", rechDao.getmotsAndcount(100));
		request.getRequestDispatcher("/table_top_recherches_body.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
