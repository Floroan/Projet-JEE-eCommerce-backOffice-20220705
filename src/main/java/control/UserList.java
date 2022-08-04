package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import tools.Database;
import tools.DateManipulator;

/**
 * Servlet implementation class UserList
 */
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
		
		// ADD EMPLOYEE FORM
		if ( request.getParameter("addUserForm") != null ) {
			
			if ( request.getParameter("firstName").isBlank() 
					|| request.getParameter("lastName").isBlank()
					|| request.getParameter("mail").isBlank()
					|| request.getParameter("password").isBlank()
					|| !tools.RegexValidator.emailValidator( request.getParameter("mail") ) 
//							|| !tools.RegexValidator.passwordValidator("password")
					) {
				
				String invalidAdd = "Vérifier que les champs ne soient pas vide et que le mail soit au bon format.";
				request.setAttribute("invalidAdd", invalidAdd);
				
			} else {
				
				String fn = request.getParameter("firstName");
				String ln = request.getParameter("lastName");
				String e = request.getParameter("mail");
				String p = request.getParameter("password");
				

//				System.out.println("ud.getByMail() : " + ud.getByMail(e));
				if ( ud.getByMail(e) == null ) {
					
					Utilisateur ub = new Utilisateur();
					ub.setNom(fn);
					ub.setPrenom(ln);
					ub.setEmail(e);
					ub.setPassword(p);
					
					Date d = new Date();
					ub.setDate_inscription( DateManipulator.dateConvertToSql( d ) );
					
//					System.out.println(ub);
					ud.save(ub);
					
					String userAdded = "L’employé(e) a bien été enregistré(e).";
					
					request.setAttribute("userAdded", userAdded);
					
				} else {
					
					String invalidAdd = "Cet email existe déjà.";
					request.setAttribute("invalidAdd", invalidAdd);
					
				}
				
			}
		}
		
		// BOUTON ARCHIVER
		if ( request.getParameter("archived") != null ) {
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				ud.archiverById(id, 0);
				
			} 
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int id = Integer.parseInt( request.getParameter("id") );
				ud.archiverById(id, 1);
				
			}
		}
		
		// BOUTON METTRE À JOUR : userList.jsp
//		if ( request.getParameter( "update") != null ) {
//			System.out.println("chui dans");
//			Utilisateur u = ud.getById( Integer.parseInt( request.getParameter( "id" ) ) );
//			
//			if ( u.getArchiver() == 1 ) {
//				
//				String msg = "Vous ne pouvez pas modifier un utilisateur archivé";
//				request.setAttribute("msg", msg);
//				
//			} else {
//				
//				String fn = request.getParameter( u.getNom() );
//				String ln = request.getParameter( u.getPrenom() );
//				String e = request.getParameter( u.getEmail() );
//				
//				u.setNom(fn);
//				u.setPrenom(ln);
//				u.setEmail(e);
//				
//				ud.save(u);
//				
//			}
//		}
		
		// BOUTON METTRE À JOUR : userList2.jsp
		if ( request.getParameter( "row-1-id" ) != null ) {
			System.out.println("chui dans");
			
			int row = ud.getAll().size();
			ArrayList<Integer> idCol = new ArrayList<>();
			
			for ( int i = 1; i <= row; i++) {
				
				int id = Integer.parseInt( request.getParameter( "row-" + i + "-id" ) );
				idCol.add(id);
				System.out.println(idCol);
				
			}
			
			int j = -1;
			int k = 0;
			for ( Utilisateur u : ud.getAll() ) {
				
				j++;
				k++;
				u = ud.getById( idCol.get(j) );
				
				if ( u.getArchiver() == 1 ) {
					
					String msg = "Les utilisateurs qui ont le statut archivé n’ont pas été mis à jour";
					request.setAttribute("msg", msg);
					
				} else {
					
					String fn = request.getParameter( "row-" + k + "-nom" );
					String ln = request.getParameter( "row-" + k + "-prenom" );
					String e = request.getParameter( "row-" + k + "-mail" );
					
					u.setNom(fn);
					u.setPrenom(ln);
					u.setEmail(e);
					
					System.out.println(u);
					ud.save(u);
					
				}
			}
			
		}
		
		// BOUTON METTRE À JOUR AVEC LE DATATABLE EDITOR : userList3EDITOR.jsp
		
		request.setAttribute("visiteurs", ud.getById(1));
		request.setAttribute("clients", ud.getAllClients());
		request.setAttribute("prospects", ud.getAllProspect());
		request.getRequestDispatcher("userList2.jsp").forward(request, response);
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
