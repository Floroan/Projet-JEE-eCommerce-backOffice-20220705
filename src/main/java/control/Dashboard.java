package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Commande;
import service_commande.Service_commandes;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;

import dao.CategorieDAO;
import dao.CommandeDAO;
import dao.ContactDAO;
import dao.GenericDAO;
import dao.VisiteDAO;

/**
 * Servlet implementation class Dashboard
 */
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CategorieDAO catDao;
	private CommandeDAO cmdDao;
	private VisiteDAO visiDao;
	private ContactDAO contDao;
	
	private GenericDAO genDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
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
		visiDao = new VisiteDAO();
		catDao = new CategorieDAO();
		genDao = new GenericDAO();
		contDao = new ContactDAO();
		
		ArrayList<Commande> commandes = cmdDao.getAll(); 
		
		request.setAttribute("commandes", commandes);
		request.setAttribute("total_CA", Service_commandes.chiffreAffaireTotal(commandes));
		request.setAttribute("totalVisites", visiDao.getAll());
		request.setAttribute("totalProduits", genDao.countRows("produits"));
		request.setAttribute("messagesNonLus", contDao.getAllByEtat(Constantes.nonlu));
		if(session.getAttribute("isConnected").equals(true)) {
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}else {
			response.sendRedirect("Signin");
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
