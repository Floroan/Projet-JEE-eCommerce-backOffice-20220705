package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Image;
import model.Produit;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;

import dao.ImageDAO;
import dao.ProduitDAO;
import dao.Sous_categorieDAO;

/**
 * Servlet implementation class AjoutProduit
 */
public class AjoutProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Sous_categorieDAO ssDao;
	private ImageDAO imgDao;
	private ProduitDAO prodDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database.Connect();
		HttpSession session = request.getSession(true);
		ssDao = new Sous_categorieDAO();
		prodDao = new ProduitDAO();
		
		if(request.getParameter("ajouter") != null) {
			Produit p = new Produit();
			p.setTitre(request.getParameter(Constantes.prodTitre));
			p.setDescription(request.getParameter(Constantes.descripProd));
			p.setImage(request.getParameter(Constantes.mainImgProd));
			p.setFk_sous_categorie(Integer.parseInt(request.getParameter(Constantes.ssCatProd)));
			p.setStock(Integer.parseInt(request.getParameter(Constantes.stockProd)));
			p.setStock_min(Integer.parseInt(request.getParameter(Constantes.stockMinProd)));
			p.setPrix(Double.parseDouble(request.getParameter(Constantes.PUProd).replace(",", ".")));
			
			int added = prodDao.save(p);
			session.setAttribute("added", added);
			System.out.println(added);
		}
		
		if(request.getParameter("recImg") != null) {
			System.out.println("voir images");
//			int added = Integer.parseInt((String) session.getAttribute("added"));
//			int idProd = Integer.parseInt(request.getParameter("selectProd"));
		
			imgDao = new ImageDAO();
			ArrayList<Image> imgs = imgDao.getAllByProduit(Integer.parseInt(request.getParameter("selectProd")));
			Image img = new Image();
			img.setFk_produit(Integer.parseInt(request.getParameter("selectProd")));
			img.setUrl(request.getParameter("newImage"));
			img.setArchiver(0);
			imgDao.save(img);
			 
		}
		
//		if(request.getParameter("selectProd") != null) {
//			//int added = Integer.parseInt((String) session.getAttribute("added"));
//			int idProd = Integer.parseInt(request.getParameter("selectProd"));
//			
//			imgDao = new ImageDAO();
//			//ArrayList<Image> imgs = imgDao.getAllByProduit(idProd);
//			 
//		}
			
		request.setAttribute("last5", prodDao.getLast5());
		request.setAttribute("ss_cats", ssDao.getAll());
		request.getRequestDispatcher("/ajoutProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
