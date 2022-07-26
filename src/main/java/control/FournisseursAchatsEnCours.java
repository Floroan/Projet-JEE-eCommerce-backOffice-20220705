package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.Entree_stockDAO;
import dao.FournisseurDao;
import dao.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Entree_stock;
import model.Fournisseur;
import model.Produit;
import tools.Database;

/**
 * Servlet implementation class FournisseursAchatsEnCours
 */
public class FournisseursAchatsEnCours extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FournisseursAchatsEnCours() {
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
		Entree_stockDAO ed = new Entree_stockDAO();
		FournisseurDao fd = new FournisseurDao();
		
		ArrayList<Produit> pbCol = pd.getAllProductToOrder();
		ArrayList<Entree_stock> ebCol = ed.getAllToReceive();
		ArrayList<Fournisseur> fbCol = fd.getAllNonArchiver();
		
		/*
		 *  COMMANDER VIA MODAL
		 *  -> ne fonctionne pas !
		 */
//		if ( request.getParameter( "commander" ) != null ) {
//			
//			String f = request.getParameter( "fournisseur" );
//			Integer q = Integer.parseInt( request.getParameter( "quantity" ) );
//			Date d = new Date();
//			String p = request.getParameter( "fk_produit" );
//			
//			System.out.println("f : " + f + " et q : " + q + " et d : " + d + " et p : " + p);
//			
//			for ( Fournisseur fb : fbCol ) {
//				
//				
//				if ( fb.getNom().equals(f) && q > 0 ) {
//					
//					Entree_stock eb = new Entree_stock();
//					
//					eb.setFk_fournisseur(0);
//					eb.setFk_produit(0);
//					
//					
//					eb.setDate(null);
//					eb.setQte(0);
//					
//					
//					System.out.println(eb);
//					// ed.save(eb);
//					
//					String orderValidated = "Votre commande est passée au statut réceptionnée.";
//					request.setAttribute("orderValidated", orderValidated);
//					
//				} else {
//					
//					String invalidProvider = "Soit le fournisseur n’existe pas ou a été bannis.<br>"
//							+ "Soit la quantité était nulle.";
//					request.setAttribute("invalidProvider", invalidProvider);
//					
//				}
//			}
//		}
		/*
		 * FIN COMMANDE VIA MODAL
		 */
		
		if ( request.getParameter( "orderValidated" ) != null ) {
			
			String orderValidated = "Votre commande est passée au statut « Réceptionner ».";
			request.setAttribute("orderValidated", orderValidated);
			
		}
		
		request.setAttribute("pbCol", pbCol);
		request.setAttribute("ebCol", ebCol);
		request.setAttribute("fbCol", fbCol);
		request.getRequestDispatcher("fournisseursAchatsEnCours.jsp").forward(request, response);
		
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
