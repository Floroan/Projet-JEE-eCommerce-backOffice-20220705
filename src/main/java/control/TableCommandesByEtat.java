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

import org.apache.tomcat.jakartaee.bcel.Const;

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
		
		if(request.getParameter("Preparer") != null) {
				System.out.println("préparer la commande");
				int id = Integer.parseInt( request.getParameter(Constantes.idcommande));
				response.sendRedirect("/Hytek_Admin/DetailCommande?id="+id);
				//request.getRequestDispatcher("/DetailCommande?id="+id).forward(request, response);
		}
		
		if(request.getParameter(Constantes.remonteCmd) != null) {
			System.out.println("remonter commande");
			request.getParameter(Constantes.idcommande);
			Commande c  = cmdDao.getById(Integer.parseInt(request.getParameter(Constantes.idcommande)));
			c.setEtat(Constantes.cmdEnCours);
			cmdDao.save(c);
			System.out.println(request.getParameter(Constantes.idcommande));
	}
		
		if(request.getParameter("Valider la livraison") != null) {
			System.out.println("Valider la livraison de la commande");
			int id = Integer.parseInt( request.getParameter(Constantes.idcommande));
			Commande c = cmdDao.getById(id);
			c.setEtat(3);
			cmdDao.save(c);
			response.sendRedirect("/Hytek_Admin/TableCommandesByEtat?etat=2");
		}
		 
		
		switch((String)session.getAttribute("etat")) {
		
		case "1" :  request.setAttribute("titre", "les commandes en cours"); 
					request.setAttribute("message", "Pas de  commandes en cours"); 
					request.setAttribute("titreBouton", "Preparer");
			break;
			
		case "2" : 	request.setAttribute("titre", "les commandes à livrer"); 
					request.setAttribute("message", "Plus de commandes à livrer");
					request.setAttribute("titreBouton", "Valider la livraison");
			break;
			
		case "3" : request.setAttribute("titre", "les commandes livrées ");  
				   request.setAttribute("message", "Plus de commandes livrées, voir les archivages.");
				   request.setAttribute("titreBouton", "Archiver");
		    break;
		    
		case "4" : request.setAttribute("titre", "les commandes signalées ");  
		   		request.setAttribute("message", "Plus de commandes signalées, voir les archivages.");
		   request.setAttribute("titreBouton", "Archiver");
		   break;
		}
		
		request.setAttribute("commandes", cmdDao.getAllByEtat( Integer.parseInt(request.getParameter("etat"))));
		
		try {
			request.getRequestDispatcher("/table_commandes_bodyEtats.jsp").forward(request, response);
		}catch (IllegalStateException e) {
			// TODO: handle exception
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
