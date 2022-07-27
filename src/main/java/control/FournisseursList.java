package control;

import java.io.IOException;

import dao.FournisseurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
				fb.setNom( fd.getById(idHref).getNom() );
				fb.setArchiver(0);
				
				fd.save(fb);
				
			} 
			
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				
				int idForm = Integer.parseInt( request.getParameter("id") );
				
				fb.setId(idForm);
				fb.setNom( fd.getById(idForm).getNom() );
				fb.setArchiver(1);
				
				fd.archiverById(fb);
				
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
		// doGet(request, response);
		
		Database.Connect();
		
		// FORM AJOUTER UN NOUVEAU FOURNISSEUR
		if (request.getParameter("addFournisseurForm") != null ) {
			
			FournisseurDao fd = new FournisseurDao();
			Fournisseur fb = new Fournisseur();
			
			String nom = request.getParameter( "nom" );
			
			fb.setNom(nom);
			
			fd.save(fb);
			
			response.sendRedirect("FournisseursList");
			
		}
		
		// FORM METTRE À JOUR ADRESSE
		if (request.getParameter("updateFournisseurForm") != null ) {
			
			FournisseurDao fd = new FournisseurDao();
			Fournisseur fb = new Fournisseur();
			
			int idFournisseur = Integer.parseInt( request.getParameter("idFournisseur") );
			
			String nom = request.getParameter( "nom" );
			
			
			fb.setId(idFournisseur);
			fb.setNom(nom);
			System.out.println(fb);
			fd.save(fb);
			
			response.sendRedirect("FournisseursList");
			
		}
	}
}