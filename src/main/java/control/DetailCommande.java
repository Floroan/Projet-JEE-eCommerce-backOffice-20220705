package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Adresse_livraison;
import model.Commande;
import service_adresse_livraison.Service_adresse_livraison;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;

import dao.Adresse_livraisonDAO;
import dao.CommandeDAO;

/**
 * Servlet implementation class DetailCommande
 */
public class DetailCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommandeDAO cmdDao;
	private Commande cmd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
		int id =  Integer.parseInt(request.getParameter("id"));
		
		Database.Connect();
		cmdDao = new CommandeDAO();
		cmd = cmdDao.getByIdCommandeUserAdressAndDetails(id);
		
		request.setAttribute("commande", cmd);
		
		Adresse_livraisonDAO adressDao = new Adresse_livraisonDAO();
		ArrayList<Adresse_livraison> adresses = adressDao.getAllByClient(cmd.getFk_utilisateur());
		
		request.setAttribute("adresses", adresses);
		
		if(request.getParameter("modifier") != null) {
				
		}
		
		if(request.getParameter("nouvelleAdresse") != null) {
			System.out.println("nouvelle adresse");
			//Service_adresse_livraison.form_nouvelle_adresse(request, cmd.getU());	
		}
		
		}catch(NullPointerException ne) {
			request.getRequestDispatcher("/ficheCommande2.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/ficheCommande2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
