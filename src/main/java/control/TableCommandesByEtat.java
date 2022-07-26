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

import dao.CommandeDAO;

/**
 * Servlet implementation class TableCommandesByEtat
 */
public class TableCommandesByEtat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommandeDAO cmdDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableCommandesByEtat() {
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
		session.setAttribute("etat", request.getParameter("etat"));
		
		Database.Connect();
		cmdDao = new CommandeDAO();

		if(request.getParameter("archiveCommande") != null) {
			Commande c = cmdDao.getById(Integer.parseInt(request.getParameter(Constantes.idcommande)));
			
			if(c.getArchiver() == 0) {
				c.setArchiver(1);
			}else if (c.getArchiver() == 1){
				c.setArchiver(0);
			}
			cmdDao.archiveById(c);
		}
		
		switch((String)session.getAttribute("etat")) {
		
		case "1" : request.setAttribute("titre", "les commandes en cours"); request.setAttribute("message", "Pas de  commandes en cours");
			break;
			
		case "2" : request.setAttribute("titre", "les commandes validées"); request.setAttribute("message", "Pas de commandes validées");
			break;
			
		case "3" : request.setAttribute("titre", "les commandes livrées ");  request.setAttribute("message", "Pas de commandes livrées");
		
		}
		
		request.setAttribute("commandes", cmdDao.getAllByEtat( Integer.parseInt(request.getParameter("etat"))));
		request.getRequestDispatcher("/table_commandes_bodyEtats.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
