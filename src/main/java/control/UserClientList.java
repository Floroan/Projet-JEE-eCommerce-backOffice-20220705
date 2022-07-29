package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.CommandeDAO;
import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import tools.DataTablesListeClients;
import tools.Database;

/**
 * Servlet implementation class UserClientList
 */
public class UserClientList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClientList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Database.Connect();
		
		UtilisateurDAO ud = new UtilisateurDAO();
		CommandeDAO od = new CommandeDAO();
		
		// DATATABLE : LISTE DES CLIENTS
		// Les clients sont des Users qui ont au moins une commande (Order)
		ArrayList<Utilisateur> u = ud.getAllClients();
		ArrayList<DataTablesListeClients> dcList = new ArrayList<DataTablesListeClients>();
		
		for ( Utilisateur ubOne : u) {
		
			DataTablesListeClients dc = new DataTablesListeClients();
			
			dc.setId( ubOne.getId() );
			dc.setName( ubOne.getNom() );
			dc.setMail( ubOne.getEmail() );
			dc.setDateFirstOrder( od.dateFirstOrder( ubOne.getId()) );
			dc.setDateLastOrder( od.dateLastOrder( ubOne.getId() ) );
			dc.setOrderAverage( od.orderAverage( ubOne.getId() ) );
			dc.setOrderSum( od.ordersSum( ubOne.getId() ) );
			
			dcList.add(dc);
			
		}
		
		request.setAttribute("dcList", dcList);
		request.getRequestDispatcher("userClientList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
