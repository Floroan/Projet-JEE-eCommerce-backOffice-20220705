package control;

import java.io.IOException;

import dao.Entree_stockDAO;
import dao.FournisseurDao;
import dao.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Entree_stock;
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
		
		// SEND FROM SERVLET FournisseurCommandeForm
		if ( request.getParameter( "orderValidated" ) != null ) {
			
			String orderValidated = "Votre commande est passée au statut « Réceptionner ».";
			request.setAttribute("orderValidated", orderValidated);
			
		}
		
		// BOUTON RÉCEPTIONNER
		if ( request.getParameter( "receptionner" ) != null ) {
			
			int idEntreeStock = Integer.parseInt( request.getParameter( "idEntreeStock" ) );
			int orderQty = Integer.parseInt( request.getParameter( "orderQty" ) );
			int idProduit = Integer.parseInt( request.getParameter( "idProduit" ) );
			
			Produit pb = pd.getById(idProduit);
			int newQty = pb.getStock() + orderQty;
			pb.setStock(newQty);
			//pd.save(pb);
			
			Entree_stock eb = ed.getById(idEntreeStock);
			eb.setArchiver(1);
			ed.save(eb);
			
			String orderValidated = "Votre commande a bien été archivée.<br>"
					+ "Vous pouvez la retrouver dans l’ « Historique »";
			request.setAttribute("orderValidated", orderValidated);
			
		}

//		ArrayList<Produit> pbCol = pd.getAllProductToOrder();
//		ArrayList<Entree_stock> ebCol = ed.getAllToReceive();
//		ArrayList<Fournisseur> fbCol = fd.getAllNonArchiver();
		request.setAttribute("pbCol", pd.getAllProductToOrder());
		request.setAttribute("ebCol", ed.getAllToReceive());
		request.setAttribute("fbCol", fd.getAllNonArchiver());
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
