package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.CommentaireDAO;
import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Commentaire;
import tools.Database;

/**
 * Servlet implementation class CommentairesList
 */
public class CommentairesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentairesList() {
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

		CommentaireDAO cd = new CommentaireDAO();
		UtilisateurDAO ud = new UtilisateurDAO();
		
		// ARCHIVED BUTTON
		if ( request.getParameter("archived") != null ) {
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				Commentaire cb = cd.getById(id);
				cb.setArchiver(0);
				cd.archiverById(cb);
				
			} 
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				Commentaire cb = cd.getById(id);
				cb.setArchiver(1);
				cd.archiverById(cb);
				
			}
		}
		
		// DELETED BUTTON
		if ( request.getParameter("deleted") != null ) {
				
			int id = Integer.parseInt( request.getParameter("id") );
			cd.deleteByIdCommentaire(id);
		}
		
		// UPDATE BUTTON
		if ( request.getParameter("updateComments") != null ) {
			
			int row = cd.getAll().size();
			ArrayList<Integer> idCol = new ArrayList<>();
			
			for ( int i = 1; i <= row; i++) {
				
				int id = Integer.parseInt( request.getParameter( "row-" + i + "-id" ) );
				idCol.add(id);
				System.out.println(idCol);
				
			}
			
			int j = -1;
			int k = 0;
			for ( Commentaire c : cd.getAll() ) {
				
				j++;
				k++;
				c = cd.getById( idCol.get(j) );
				
				if ( c.getArchiver() == 1 ) {
					
					String msg = "Les utilisateurs qui ont le statut archivé n’ont pas été mis à jour";
					request.setAttribute("alertUpdateComments", msg);
					
				} else {
					
					String t = request.getParameter( "row-" + k + "-text" );
					
					c.setCommentaire(t);
					
					System.out.println(c);
					cd.save(c);
					
				}
			}
		}
		
		request.setAttribute("cbCol", cd.getAll());
		request.setAttribute("ubColClients", ud.getAllClients());
		request.setAttribute("ubColProspects", ud.getAllProspect());
		request.getRequestDispatcher("commentairesList.jsp").forward(request, response);
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
