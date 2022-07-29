package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.UtilisateurDAO;
import dao.VisiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import tools.DataTablesListeProspects;
import tools.Database;

/**
 * Servlet implementation class UserProspect
 */
public class UserProspectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProspectList() {
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
		VisiteDAO vd = new VisiteDAO();
		
		// DATATABLE : LISTE DES PROSPECTS
		// Les prospects sont des Users qui n’ont jamais acheté (Order)
		ArrayList<Utilisateur> u = ud.getAllProspect();
		ArrayList<DataTablesListeProspects> dcList = new ArrayList<DataTablesListeProspects>();
		
		for ( Utilisateur ubOne : u) {
		
			DataTablesListeProspects dc = new DataTablesListeProspects();
			
			int id = ubOne.getId(); 
			
			dc.setId( id );
			dc.setName( ubOne.getNom() );
			dc.setMail( ubOne.getEmail() );
			dc.setDateLastVisit( vd.dateLastVisit( id ) );
			dc.setNumberOfProductsViewed( vd.numberOfProductsViewedBy( id ) );
			dc.setSumOfProductClicks( vd.sumOfProductClicks( id ) );
						
			dcList.add(dc);
			
		}
		
		request.setAttribute("dcList", dcList);
		request.getRequestDispatcher("userProspectList.jsp").forward(request, response);
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
