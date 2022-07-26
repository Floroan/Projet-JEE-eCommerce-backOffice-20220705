package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Commentaire;
import tools.Constantes;
import tools.Database;

import java.io.IOException;

import dao.CommentaireDAO;
import dao.ProduitDAO;

/**
 * Servlet implementation class FicheProduit
 */
public class FicheProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private ProduitDAO prodDao;
	private CommentaireDAO commDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FicheProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database.Connect();
		
		prodDao = new ProduitDAO();
		commDao = new CommentaireDAO();		
		
		if(request.getParameter(Constantes.archiver) != null) {

			Commentaire comm = commDao.getById(Integer.parseInt(request.getParameter(Constantes.commentaireId)));
			
			if(comm.getArchiver() == 0) {
				comm.setArchiver(1);
			}
			else if(comm.getArchiver() == 1) {
				comm.setArchiver(0);
			}
			commDao.archiverById(comm);
		}
		
		if(request.getParameter(Constantes.modifier) != null) {
			System.out.println(request.getParameter("areaComm"));
			Commentaire comm = commDao.getById(Integer.parseInt(request.getParameter(Constantes.commentaireId)));
			comm.setCommentaire(request.getParameter("areaComm"));
			commDao.save(comm);
		}
		request.setAttribute("message", "Actuellement, il n'y a pas encore de  commentaires pour ce produit");
		request.setAttribute("prod", prodDao.getById(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("commentaires", commDao.getAllByProduit(Integer.parseInt(request.getParameter("id"))));
		request.getRequestDispatcher("/ficheProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
