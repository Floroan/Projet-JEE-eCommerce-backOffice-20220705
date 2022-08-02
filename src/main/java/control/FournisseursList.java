package control;

import java.io.IOException;

import dao.FournisseurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BeanException;
import model.Fournisseur;
import tools.Database;

/**
 * Servlet implementation class FournisseursList
 */
public class FournisseursList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FournisseursList() {
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
		
		FournisseurDao fd = new FournisseurDao();
		
		// DELETE FOURNISSEUR
		// FAUX !!! 
		// - Et si il y a des achats en cours ?
		// - Comment se rappeler que ce fournisseur est bidon ?
//		if ( request.getParameter("delete") != null ) {
//			
//			int idFournisseur = Integer.parseInt( request.getParameter("idFournisseur") );
//			fd.deleteById(idFournisseur);
//			
//		}
		
		// BOUTON ARCHIVER FOURNISSEUR
		if ( request.getParameter("archived") != null ) {
			
			Fournisseur fb = new Fournisseur();
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				
				int idHref = Integer.parseInt( request.getParameter("id") );
				
				fb.setId(idHref);
				fb.setArchiver(0);
				fd.archiverById(fb);
			} 
			
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int idForm = Integer.parseInt( request.getParameter("id") );
				
				fb = fd.getById(idForm);
				fb.setArchiver(1);
				fd.archiverById(fb);
			}
		}
		
		// FORM AJOUTER UN NOUVEAU FOURNISSEUR
		if (request.getParameter("addFournisseurForm") != null ) {
			
			try {
				String nom = request.getParameter( "nom" );
			
				Fournisseur fb = new Fournisseur();
				fb.setNom(nom);
				fd.save(fb);
				request.setAttribute("supplierAdded", "Le nouveau fournisseur a bien été ajouté.");
				
			} catch (BeanException e) {
				
				e.printStackTrace();
				System.out.println("Dao exception : " + e.getMessage());
				request.setAttribute("invalidAdd", e.getMessage());
			}
		}
		
		// FORM METTRE À JOUR ADRESSE
		if (request.getParameter("updateFournisseurForm") != null ) {
						
			int idFournisseur = Integer.parseInt( request.getParameter("idFournisseur") );
			Fournisseur fbCheckIfIsArchived = fd.getById(idFournisseur);
			if ( fbCheckIfIsArchived.getArchiver() == 1 ) {
				
				request.setAttribute("invalidUpdate", "Un fournisseur archivé ne peut être modifié.");
				
			} else {
				
				try {
					String nom = request.getParameter( "nom" );
					
					Fournisseur fb = new Fournisseur();
					fb.setId(idFournisseur);
					fb.setNom(nom);
					fd.save(fb);
					request.setAttribute("supplierUpdated", "Le nom du fournisseur a bien été mis à jour.");
					
				} catch (Exception e) {
					
					e.printStackTrace();
					System.out.println("Dao exception : " + e.getMessage());
					request.setAttribute("invalidUpdate", e.getMessage());					
				}
			}
		}
		
		request.setAttribute("fbCol", fd.getAll());
		request.getRequestDispatcher("fournisseursList.jsp").forward(request, response);
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