package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Contact;
import model.Utilisateur;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import dao.ContactDAO;
import dao.GenericDAO;
import dao.UtilisateurDAO;

/**
 * Servlet implementation class GestionMessagerie
 */
public class GestionMessagerie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       private ContactDAO contDao;
       private GenericDAO genDao;
       
       private UtilisateurDAO utDao;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionMessagerie() {
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
		
		contDao = new ContactDAO();
		genDao = new GenericDAO();
		utDao = new UtilisateurDAO();
		
		request.setAttribute("count", genDao.countRows("contacts"));
		
		request.setAttribute("allMessages", contDao.getAll());
		request.setAttribute("messages_non_lus", contDao.getAllByEtat(Constantes.nonlu));
		request.setAttribute("messages_lus_non_resolus", contDao.getAllByEtat(Constantes.nonResolu));
		request.setAttribute("messages_lus_resolus", contDao.getAllByEtat(Constantes.resolu));
		
		if(request.getParameter("etat") != null) {
			int etat =Integer.parseInt(request.getParameter("etat"));
			
			switch(etat) {
			
			case 0:
				request.setAttribute("allMessages", contDao.getAllByEtat(Constantes.nonlu));
				break;	
			case 1:
				request.setAttribute("allMessages", contDao.getAllByEtat(Constantes.enCours));
				break;
			case 2:
				request.setAttribute("allMessages", contDao.getAllByEtat(Constantes.resolu));
				break;
				
			case 3:
				request.setAttribute("allMessages", contDao.getAllByEtat(Constantes.nonResolu));
				break;
			
			}
		}
		
		
		if(request.getParameter(Constantes.rechercheContact) != null) {
			// recherche dans le nom du contact, titre et corps de message
			System.out.println("action recherche messages, par mot-clé");
			System.out.println(request.getParameter("argRecherche"));
			
			ArrayList<Contact> contactsRecherche =  contDao.getByLike(request.getParameter("argRecherche"));
			ArrayList<Utilisateur> utilisateursRecherche =  utDao.getByLike(request.getParameter("argRecherche"));
			
			System.out.println(contactsRecherche.size());
			
			ArrayList<Contact> contByUser = null;
			for(Utilisateur ut: utilisateursRecherche) {
				contByUser = contDao.getAllByClientAndEtat(ut.getId(), 0);
				//contactsRecherche.addAll(contDao.getAllByClientAndEtat(ut.getId(), 0));
			}
			
			HashSet hs = new HashSet();
			hs.addAll(contactsRecherche);
			hs.addAll(contByUser);
			contByUser.clear();
			contByUser.addAll(hs);

			System.out.println(contByUser.size());
		}
		
		//request.getRequestDispatcher("/headerMessagerie.jsp").include(request, response);
		request.getRequestDispatcher("/messagerie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
