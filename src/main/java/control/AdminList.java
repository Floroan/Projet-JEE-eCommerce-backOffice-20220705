package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import tools.Database;

/**
 * Servlet implementation class AdminList
 */
public class AdminList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminList() {
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
		
		AdminDAO ad = new AdminDAO();
		
		// BOUTON ARCHIVER
		if ( request.getParameter("archived") != null ) {
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				Admin ab = ad.getById(id);
				
				ab.setArchiver(0);
				
				ad.save(ab);
				
			} 
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				Admin ab = ad.getById(id);
				
				ab.setArchiver(1);
				
				ad.save(ab);
				
			}
		}
		
		// BOUTON METTRE À JOUR : userList2.jsp
		if ( request.getParameter( "update" ) != null ) {
//			System.out.println("chui dans");
			
			int row = ad.getAll().size();
			ArrayList<Integer> idCol = new ArrayList<>();
			
			for ( int i = 1; i <= row; i++) {
				
				int id = Integer.parseInt( request.getParameter( "row-" + i + "-id" ) );
				idCol.add(id);
				System.out.println(idCol);
				
			}
			
			int j = -1;
			int k = 0;
			for ( Admin u : ad.getAll() ) {
				
				j++;
				k++;
				u = ad.getById( idCol.get(j) );
				
				if ( u.getArchiver() == 1 ) {
					
					String msg = "Les employés qui ont le statut archivé n’ont pas été mis à jour";
					request.setAttribute("msg", msg);
					
				} else {
					
					String fn = request.getParameter( "row-" + k + "-nom" );
					String e = request.getParameter( "row-" + k + "-mail" );
					
					u.setNom(fn);
					u.setEmail(e);
					
//					System.out.println(u);
					ad.save(u);
					
				}
			}
			
		}
		
		request.setAttribute("staff", ad.getAll());
		request.getRequestDispatcher("adminList.jsp").forward(request, response);
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
