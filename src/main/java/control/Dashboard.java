package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.CategorieDAO;
import dao.CommandeDAO;
import dao.ContactDAO;
import dao.GenericDAO;
import dao.ProduitDAO;
import dao.RechercheDAO;
import dao.Sous_categorieDAO;
import dao.VisiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Commande;
import model.Sous_categorie;
import service_commande.Service_commandes;
import tools.ChartsGenerator;
import tools.Constantes;
import tools.Database;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (request.getParameter("lastCmds") != null) {
			LinkedHashMap<Date, Integer> cmds = new LinkedHashMap<>();
			cmds = cmdDao.getWithInterval(Integer.parseInt(request.getParameter("lastCmdsInterval")));
			String lastcmds = "";

			for (Map.Entry<Date, Integer> entry : cmds.entrySet()) {
				// Date dt = entry.getKey();
				// System.out.println("date:" + dt);
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
				String dt = df.format(entry.getKey());
				System.out.println("date:" + dt);
				lastcmds += "{ x: " + "\"" + dt + " GMT" + "\"" + ", y: " + entry.getValue() + "},";
			}
			System.out.println(lastcmds);
			request.setAttribute("lastcmds", lastcmds);
		} else { // par defaut sur 7 jours
			LinkedHashMap<Date, Integer> cmds = new LinkedHashMap<>();
			cmds = cmdDao.getWithInterval(7);
			String lastcmds = "";

			for (Map.Entry<Date, Integer> entry : cmds.entrySet()) {
				Date dt = entry.getKey();
				lastcmds += "{ x:" + dt.toGMTString() + ", y: " + entry.getValue() + "},";
			}
			request.setAttribute("lastcmds", lastcmds);
		}

		ArrayList<Commande> commandes = cmdDao.getLimit(20);

		String char_sscats_titre = "";
		String char_sscats_nbr = "";

		for (Sous_categorie ss : ssCatDao.getAll()) {
			char_sscats_titre += "'" + ss.getTitre() + "',";
			char_sscats_nbr += prodDao.getCountBySsCat(ss.getId()) + ",";
		}

		/*
		 * CAMEMBERT MOTS CLÉ RECHERCHÉS
		 */
//		HashMap<Recherche, Integer> mapMotsCle = new HashMap<>();
//		mapMotsCle = rechDao.getmotsAndcount(5);
//		String mots = "";
//		String nbMot = "";
//		for (Map.Entry<Recherche, Integer> entry : mapMotsCle.entrySet()) {
//			Recherche r = entry.getKey();
//			/*
//			 * Problème avec Group by : -
//			 * https://stackoverflow.com/questions/23921117/disable-only-full-group-by :
//			 * ONLY_FULL_GROUP_BY, - tuto pour régler le problème :
//			 * https://grafikart.fr/tutoriels/only-full-group-by-sql-1206
//			 */
//			mots += "'" + entry.getKey().getMotcle() + "',";
//			nbMot += entry.getValue() + ",";
//		}
		/*
		 * NOUVEAU CAMEMBERT MOTS CLÉ RECHERCHÉS
		 */
		HashMap<String, Integer> mapMotsCle = new HashMap<>();
		mapMotsCle = rechDao.countTheMostSearchedWords(5);
		String mots = "";
		String nbMot = "";
		for (Map.Entry<String, Integer> entry : mapMotsCle.entrySet()) {
			
			mots += "'" + entry.getKey() + "',";
			nbMot += entry.getValue() + ",";
		}

		HashMap<String, Integer> ssprod = new HashMap<>();

		for (Sous_categorie ss : ssCatDao.getAll()) {
			ssprod.put(ss.getTitre(), prodDao.getCountBySsCat(ss.getId()));
		}

		System.out.println(char_sscats_titre);
		System.out.println(char_sscats_nbr);

		request.setAttribute("char_sscats_titre", char_sscats_titre);
		request.setAttribute("char_sscats_nbr", char_sscats_nbr);

		// visites à 7 jours
		String visiA7jours = "";
		int visitotalA7jours = 0;
		for (Map.Entry<java.sql.Date, Integer> entry : visiDao.getWithInterval(7).entrySet()) {

			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			String dt = df.format(entry.getKey());
			visiA7jours += entry.getValue() + ", ";
			visitotalA7jours += entry.getValue();

		}
		System.out.println("visite 7 jours: " + visiA7jours);
		System.out.println("visite 7 jours: " + visitotalA7jours);

		request.setAttribute("cmdsAll", genDao.countRows("commandes"));
		request.setAttribute("cmdsLast20", commandes);
		request.setAttribute("total_CA", Service_commandes.chiffreAffaireTotal(commandes));
		request.setAttribute("cmdsA24h", Service_commandes.last_commandes_With24hInterval());
		request.setAttribute("visiA7jours", visiA7jours);
		request.setAttribute("visitotalA7jours", visitotalA7jours);
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

		if (session.getAttribute("isConnected").equals(true)) {
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		} else {
			response.sendRedirect("Signin");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
