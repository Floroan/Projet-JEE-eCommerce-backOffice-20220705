package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dao.Entree_stockDAO;
import dao.FournisseurDao;
import dao.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Entree_stock;
import model.Fournisseur;
import tools.Database;
import tools.DateManipulator;

/**
 * Servlet implementation class FournisseurCommandeForm
 */
public class FournisseurCommandeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FournisseurCommandeForm() {
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
		
		ProduitDAO pd = new ProduitDAO();
		FournisseurDao fd = new FournisseurDao();
		Entree_stockDAO ed = new Entree_stockDAO();
		
		if ( request.getParameter( "commander" ) != null ) {
			
			int p = Integer.parseInt( request.getParameter( "idProduit" ) );

			ArrayList<Fournisseur> fbCol = fd.getAllNonArchiver();
			
			request.setAttribute("pb", pd.getById(p));
			request.setAttribute("fbCol", fbCol);
			request.getRequestDispatcher("fournisseurCommandeForm.jsp").forward(request, response);
		}
		
		if ( request.getParameter( "orderToValidate" ) != null ) {
			
			ArrayList<Fournisseur> fbCol = fd.getAllNonArchiver();
			
			// GET PARAMETERS
			int fk_produit = Integer.parseInt( request.getParameter( "fk_produit" ) );
			
			// CHECK	
			if ( request.getParameter( "fk_fournisseur" ).equals( "Choisir un fournisseur" ) 
					|| request.getParameter( "quantity" ).isBlank() ) {
				
				String invalidProvider = "Vous n’avez pas rempli tous les champs...";
				
				request.setAttribute("invalidProvider", invalidProvider);
				request.setAttribute("pb", pd.getById(fk_produit));
				request.setAttribute("fbCol", fbCol);
				request.getRequestDispatcher("fournisseurCommandeForm.jsp").forward(request, response);
				
				
			} else {
				
				// GET PARAMETERS
				int fk_fournisseur = Integer.parseInt( request.getParameter( "fk_fournisseur" ) );
				Integer q = Integer.parseInt( request.getParameter( "quantity" ) );
				Date d = new Date();
				
				System.out.println("fk_fournisseur : " + fk_fournisseur + " et q : " + q + " et d : " + d + " et fk_produit : " + fk_produit);
				
				// CHECK
				boolean orderValidated = false;
				for ( Fournisseur fb : fbCol ) {
					
					if ( fb.getId() == fk_fournisseur && q > 0 ) {
						
						orderValidated = true;
						
					}
				}
				
				// SAVE AND SEND
				if ( orderValidated ) {
					
					Entree_stock eb = new Entree_stock();
					
					eb.setFk_produit(fk_produit);
					eb.setFk_fournisseur(fk_fournisseur);
					eb.setDate( DateManipulator.dateConvertToSql(d) );
					eb.setQte(q);
					System.out.println(eb);
					ed.save(eb);
					
					response.sendRedirect("FournisseursAchatsEnCours?orderValidated=ok");
					
				} else {
					
					String invalidProvider = "Soit le fournisseur n’existe pas.<br>"
							+ "Soit on ne travaille plus avec.<br>"
							+ "Soit la quantité était nulle.";
					
					request.setAttribute("invalidProvider", invalidProvider);
					request.setAttribute("pb", pd.getById(fk_produit));
					request.setAttribute("fbCol", fbCol);
					request.getRequestDispatcher("fournisseurCommandeForm.jsp").forward(request, response);
					
				}
				
				
			}
		}
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
