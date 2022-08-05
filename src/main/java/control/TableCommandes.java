package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Commande;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;

import dao.CommandeDAO;

/**
 * Servlet implementation class TableCommandes
 */
public class TableCommandes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommandeDAO cmdDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableCommandes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(0);
		
		Database.Connect();
		cmdDao = new CommandeDAO();

		if(request.getParameter("archiveCommande") != null) {
			System.out.println("gogo");
			Commande c = cmdDao.getById(Integer.parseInt(request.getParameter(Constantes.idcommande)));
			
			if(c.getArchiver() == 0) {
				c.setArchiver(1);
			}else if (c.getArchiver() == 1){
				c.setArchiver(0);
			}
			cmdDao.archiveById(c);
		}
		
		if(request.getParameter("changeEtat") != null) {
			System.out.println("change etat");
			int id = Integer.parseInt( request.getParameter(Constantes.idcommande));
			System.out.println(id);
			Commande c = cmdDao.getById(id);
			c.setEtat(Integer.parseInt(request.getParameter(Constantes.etat)));
			cmdDao.save(c);
	}
		
		request.setAttribute("commandes", cmdDao.getAll());
		request.getRequestDispatcher("/table_commandes_body.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
