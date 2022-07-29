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

import dao.CategorieDAO;
import dao.ImageDAO;
import dao.ProduitDAO;
import dao.Sous_categorieDAO;

/**
 * Servlet implementation class DetailProduit
 */
public class DetailProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategorieDAO catDao;
	private Sous_categorieDAO scatDao;
	private ProduitDAO prodDao;
	private Produit prod;
	private ImageDAO imgDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(0);
		Database.Connect();

		catDao = new CategorieDAO();
		prodDao = new ProduitDAO();

		scatDao = new Sous_categorieDAO();

		if (request.getParameter("modifier") != null) {
			
			try {
			
			prod = new Produit();
			prod.setId(Integer.parseInt(request.getParameter(Constantes.idProd)));
			prod.setTitre(request.getParameter(Constantes.prodTitre));
			prod.setDescription(request.getParameter(Constantes.descripProd));
			prod.setImage(request.getParameter(Constantes.mainImgProd));
			prod.setStock(Integer.parseInt(request.getParameter(Constantes.stockProd)));
			prod.setStock_min(Integer.parseInt(request.getParameter(Constantes.stockMinProd)));
			prod.setFk_sous_categorie(Integer.parseInt(request.getParameter(Constantes.ssCatProd)));
			prod.setPrix(Double.parseDouble(request.getParameter(Constantes.PUProd).replace(",", ".")));

			prodDao.save(prod);
			System.out.println(request.getParameter(Constantes.idProd));
			}catch(NumberFormatException ne) {
				request.setAttribute("message", Constantes.messageNumberFormat);
			}
		}

		if (request.getParameter(Constantes.archiver) != null) {
			System.out.println("go");
			prod = prodDao.getById(Integer.parseInt(request.getParameter(Constantes.idProd)));
			if (prod.getArchiver() == 0) {
				prod.setArchiver(1);
			} else if (prod.getArchiver() == 1) {
				prod.setArchiver(0);
			}
			prodDao.archiverById(prod);
		}

		if (request.getParameter("deleteImg") != null) {
			// imgToDelet
			imgDao = new ImageDAO();
			imgDao.deleteById(Integer.parseInt(request.getParameter("imgToDelete")));
			System.out.println("go delete img");
		}

		if (request.getParameter("del") != null) {
			// imgToChange
			System.out.println("go change, " + "lien: " + request.getParameter(Constantes.mainImgProd));
		}

		if (request.getParameter("change2") != null) {
			// imgToChange
			System.out.println("go change, " + request.getParameter("id") + ", lien: "
					+ request.getParameter(Constantes.mainImgProd));
		}

		if (request.getParameter("recImg") != null) {

			imgDao = new ImageDAO();
			// ArrayList<Image> imgs =
			// imgDao.getAllByProduit(Integer.parseInt(request.getParameter("selectProd")));
			Image img = new Image();
			img.setFk_produit(Integer.parseInt(request.getParameter("idProd4AddImg")));
			img.setUrl(request.getParameter("newImage"));
			img.setArchiver(0);
			imgDao.save(img);
		}

		if (request.getParameter("newMainImg") != null) {

			if (request.getParameter(Constantes.mainImgProd).contains(".jpg")
					&& request.getParameter(Constantes.mainImgProd).contains("http")
					|| request.getParameter(Constantes.mainImgProd).contains(".png")
							&& request.getParameter(Constantes.mainImgProd).contains("http")) {
				System.out.println("go change, " + "lien: " + request.getParameter(Constantes.mainImgProd));
				Produit p = prodDao.getById(Integer.parseInt(request.getParameter(Constantes.idProd)));
				p.setImage(request.getParameter(Constantes.mainImgProd));
				prodDao.save(p);

			} else {
				request.setAttribute("message",
						"Merci de vérifier votre lien, il doit commencer par http.. et contenir une image jpg ou png");
			}

		}

		
		
		request.setAttribute("cats", catDao.getAll());
		request.setAttribute("ss_cats", scatDao.getAll());
		prod = prodDao.getById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("prod", prod);
		request.setAttribute("ss_cat_produit", scatDao.getById(prod.getFk_sous_categorie()));

		if (session.getAttribute("isConnected") == null) {
			request.getRequestDispatcher("/error500.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/detailProduit.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}

 class ValideForm{
	
}


