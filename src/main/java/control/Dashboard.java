package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Commande;
import model.Recherche;
import model.Sous_categorie;
import service_commande.Service_commandes;
import tools.ChartsGenerator;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.CategorieDAO;
import dao.CommandeDAO;
import dao.ContactDAO;
import dao.GenericDAO;
import dao.ProduitDAO;
import dao.RechercheDAO;
import dao.Sous_categorieDAO;
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
	private Sous_categorieDAO ssCatDao;
	private ProduitDAO prodDao;
	private RechercheDAO rechDao;
	
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
		ssCatDao = new Sous_categorieDAO();
		prodDao = new ProduitDAO();
		rechDao = new RechercheDAO(); 
		
		ArrayList<Commande> commandes = cmdDao.getLimit(20);
		
		
		String char_sscats_titre="";
		String char_sscats_nbr="";
		
		for(Sous_categorie ss: ssCatDao.getAll()){ 
			char_sscats_titre += "'"+ ss.getTitre()+"',";
			char_sscats_nbr += prodDao.getCountBySsCat(ss.getId())+",";
		}
		
		HashMap<Recherche, Integer> mapMotsCle = new HashMap<>(); 
		mapMotsCle = rechDao.getmotsAndcount(5);
		String mots ="";
		String nbMot = "";
		for(Map.Entry<Recherche, Integer> entry : mapMotsCle.entrySet()){ 
		 Recherche r = entry.getKey();
	
		 mots += "'" + entry.getKey().getMotcle() + "',"; 
		 nbMot += entry.getValue() + ",";
		} 
		
		HashMap<String, Integer> ssprod = new HashMap<>();
		
		for(Sous_categorie ss: ssCatDao.getAll()){ 
			ssprod.put(ss.getTitre(), prodDao.getCountBySsCat(ss.getId()));
		}
		
		System.out.println(char_sscats_titre);
		System.out.println(char_sscats_nbr);
		
		request.setAttribute("char_sscats_titre", char_sscats_titre);
		request.setAttribute("char_sscats_nbr", char_sscats_nbr);
		
		request.setAttribute("commandes", commandes);
		request.setAttribute("total_CA", Service_commandes.chiffreAffaireTotal(commandes));
		request.setAttribute("totalVisites", visiDao.getAll());
		request.setAttribute("totalProduits", genDao.countRows("produits"));
		request.setAttribute("messagesNonLus", contDao.getAllByEtat(Constantes.nonlu));
		request.setAttribute("sscats", ssCatDao.getAll());
		
		ChartsGenerator cg = new ChartsGenerator();
		System.out.println(mots);
		System.out.println(nbMot);
		request.setAttribute("topRecherches_MOTS", mots);
		request.setAttribute("topRecherches_NBR", nbMot);
		request.setAttribute("testChart", cg.pie(char_sscats_titre, char_sscats_nbr, null));
		
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
