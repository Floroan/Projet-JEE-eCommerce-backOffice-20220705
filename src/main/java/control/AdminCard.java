package control;

import java.io.IOException;

import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;

/**
 * Servlet implementation class AdminCard
 */
public class AdminCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCard() {
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
		
		HttpSession session = request.getSession(true);
		Admin abSession = (Admin) session.getAttribute("admin");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		AdminDAO ad = new AdminDAO();
		Admin ab = ad.getById(id);
		
		// UPDATE EMPLOYEE FORM
		if (request.getParameter("updateProfileForm") != null) {

			System.out.println(ab);

			
			if (ab.getArchiver() == 1) {

				String profilArchiver = "Avec le statut archivé, vous ne pouvez pas mettre à jour cet fiche employé.";
				
				request.setAttribute("profilArchiver", profilArchiver);

			} else {

				String fn = request.getParameter("name");
				String e = request.getParameter("mail");

				ab.setNom(fn);
				ab.setEmail(e);

				System.out.println(ab);
//				ad.save(ab);
				
				String profilUpdated = "La fiche a bien été mise à jour.";
				
				request.setAttribute("profilUpdated", profilUpdated);

			}
		}
		
		// ARCHIVED BOUTON
		if (request.getParameter("archived") != null) {
			
			if (request.getParameter("archived").equals("isArchived")) {
				
				ab.setArchiver(0);
				ad.save( ab );
				
			}
			
			if (request.getParameter("archived").equals("isNotArchived")) {
				
				ab.setArchiver(1);
				ad.save( ab );
				
			}
		}
		
		// PRIVILEGE FORM
		if (request.getParameter("privilege") != null) {
			
			String privileges = "";
			if ( request.getParameter("employeeType") != null ) {
				if ( request.getParameter("employeeType").isBlank() ) {
					privileges = "non renseigné,";
				} else {
					privileges = request.getParameter("employeeType") + ",";
				}
			}
			
			// STATISTIQUES
			if ( request.getParameter("stat") != null ) {
				privileges += "stat,";
				if ( request.getParameter("boStat") != null ) {
					privileges += "boStat,";
					if ( request.getParameter("boStatEmployees") != null ) {
						privileges += "boStatEmployees,";	
					}
				}
				if ( request.getParameter("foStat") != null ) {
					privileges += "foStat,";
					if ( request.getParameter("foStatClients") != null ) {
						privileges += "foStatClients,";	
					}
					if ( request.getParameter("foStatProspects") != null ) {
						privileges += "foStatProspects,";	
					}
					if ( request.getParameter("foStatVisiteurs") != null ) {
						privileges += "foStatVisiteurs,";
					}
				}
				
			}
			
			// GESTION DU SITE
			if ( request.getParameter("webSite") != null ) {
				privileges += "webSite,";
			}
			
			// GESTION COMMANDES
			if ( request.getParameter("commandes") != null ) {
				privileges += "commandes,";
			}
			
			// GESTION DES UTILISATEURS
			if ( request.getParameter("utilisateurs") != null ) {
				privileges += "utilisateurs,";
			}
			
			System.out.println(privileges);
			ab.setPrivileges(privileges);
			ad.save(ab);
			
		}
		
		request.setAttribute("ab", ad.getById(id));
		request.setAttribute("abSession", abSession);

		request.getRequestDispatcher("adminCard.jsp").forward(request, response);
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
