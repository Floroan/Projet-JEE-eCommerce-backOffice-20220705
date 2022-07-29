package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.CoordonneesDAO;
import dao.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Coordonnee;
import model.Slide;
import tools.Database;
import tools.FileDownloader;

/**
 * Servlet implementation class SiteFrontOffice
 */
public class SiteFrontOffice extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	public static final int TAILLE_TAMPON = 10240;
	public static final String LOGO_PATH = "/Users/tanguymanas/Documents/developpement"
			+ "/eclipse-202206-workspace-web/Projet-JEE-eCommerce-backOffice-20220705/src/main/webapp"
			+ "/assets/img/logo/";
	public static final String SLIDER_PATH = "/Users/tanguymanas/Documents/developpement"
			+ "/eclipse-202206-workspace-web/Projet-JEE-eCommerce-backOffice-20220705/src/main/webapp"
			+ "/assets/img/slider/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiteFrontOffice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		Database.Connect();

		CoordonneesDAO cd = new CoordonneesDAO();
		SliderDAO sd = new SliderDAO();

		// BOUTON ARCHIVER
		if ( request.getParameter("archived") != null ) {
			
			int id = Integer.parseInt( request.getParameter("id") );
			Slide sb = sd.getOneById(id);
			
			if ( request.getParameter( "archived").equals( "isArchived" ) ) {
				sb.setArchiver(0);	
			} 
			if ( request.getParameter( "archived").equals( "isNotArchived" ) ) {
				sb.setArchiver(1);
			}
			
			sd.save(sb);
		}
		
		// ALERT : Mettre à jour le slider
		if ( request.getParameter("alertUpdateSlider") != null ) {
			
			if ( request.getParameter("alertUpdateSlider").equalsIgnoreCase("yes") ) {

				String alertUpdateSlider = "Les slides qui ont le statut archivé n’ont pas été mis à jour";
				request.setAttribute("alertUpdateSlider", alertUpdateSlider);
				
			}
		}
		
		// DELETE A SLIDE
		if ( request.getParameter("deleted") != null ) {
			int id = Integer.parseInt( request.getParameter("id") );
			sd.deleteById(id);
		}

		request.setAttribute("cb", cd.getById(1));
		request.setAttribute("sbCol", sd.getAll());
		request.getRequestDispatcher("siteFrontOffice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		Database.Connect();

		CoordonneesDAO cd = new CoordonneesDAO();
		SliderDAO sd = new SliderDAO();

		// COMPANY UPDATE FORM
		if (request.getParameter("updateCompany") != null) {
			
			FileDownloader f = new FileDownloader();

			String n = request.getParameter("name");
			String a = request.getParameter("address");
			String p = request.getParameter("phone");
			String m = request.getParameter("mail");


			Part part = request.getPart("logoFile"); // On récupère le champ du fichier

			String nomFichier = FileDownloader.getFileName(part); // On vérifie qu'on a bien reçu un fichier
			System.out.println(nomFichier);

			// Si on a bien un fichier
			if (nomFichier != null && !nomFichier.isEmpty()) {
				
//				String nomChamp = part.getName();
//				System.out.println(nomChamp);
				
				// Corrige un bug du fonctionnement d'Internet Explorer
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
						.substring(nomFichier.lastIndexOf('\\') + 1);

				// On écrit définitivement le fichier sur le disque
				f.writeFile(part, nomFichier, LOGO_PATH);
				
			}
			
			// SAVE
			Coordonnee cb = cd.getById(1);
			cb.setNom(n);
			cb.setAdresse(a);
			cb.setTelephone(p);
			cb.setEmail(m);
			cb.setLogo("assets/img/logo/" + nomFichier);
			
			System.out.println(cb);
			cd.save(cb);

			response.sendRedirect("SiteFrontOffice");

		}
		
		// NEW SLIDE FORM
		if (request.getParameter("addNewSlide") != null) {
			
			FileDownloader f = new FileDownloader();

			String t = request.getParameter("title");
			String tb = request.getParameter("titleButton");
			String urlB = request.getParameter("urlButton");
			String text = request.getParameter("text");


			Part part = request.getPart("newSlideFile"); // On récupère le champ du fichier

			String nomFichier = FileDownloader.getFileName(part); // On vérifie qu'on a bien reçu un fichier
			System.out.println(nomFichier);

			// Si on a bien un fichier
			if (nomFichier != null && !nomFichier.isEmpty()) {
				
//				String nomChamp = part.getName();
//				System.out.println(nomChamp);
				
				// Corrige un bug du fonctionnement d'Internet Explorer
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
						.substring(nomFichier.lastIndexOf('\\') + 1);

				// On écrit définitivement le fichier sur le disque
				f.writeFile(part, nomFichier, SLIDER_PATH);

				
			}
			
			// SAVE
			String dbPath = "assets/img/slider/" + nomFichier;
			Slide s = new Slide();
			s.setTitre(t);
			s.setTitreBouton(tb);
			s.setUrl(urlB);
			s.setDescription(text);
			s.setImage(dbPath);
			
			System.out.println(s);
			sd.save(s);

			response.sendRedirect("SiteFrontOffice");

		}
		
		// UPDATE SLIDE FORM
		if (request.getParameter("updateSlide") != null) {
			
			FileDownloader f = new FileDownloader();

			int row = sd.getAll().size();
			ArrayList<Integer> idCol = new ArrayList<>();
			
			for ( int i = 1; i <= row; i++) {
				
				int id = Integer.parseInt( request.getParameter( "row-" + i + "-id" ) );
				idCol.add(id);
				
			}
			
			int j = -1;
			int k = 0;
			String alertUpdateSlider = "no";
			for ( Slide s : sd.getAll() ) {
				
				j++;
				k++;
				s = sd.getOneById( idCol.get(j) );
				
				if ( s.getArchiver() == 1 ) {
					
					alertUpdateSlider = "yes";
					
				} else {
					
					String t = request.getParameter( "row-" + k + "-title" );
					String tb = request.getParameter( "row-" + k + "-titleButton" );
					String urlB = request.getParameter( "row-" + k + "-urlButton" );
					String text = request.getParameter( "row-" + k + "-text" );
					
					
					Part part = request.getPart("row-" + k + "-urlImg"); // On récupère le champ du fichier

					String nomFichier = FileDownloader.getFileName(part); // On vérifie qu'on a bien reçu un fichier
					System.out.println(nomFichier);

					// Si on a bien un fichier
					if (nomFichier != null && !nomFichier.isEmpty()) {
						
//						String nomChamp = part.getName();
//						System.out.println(nomChamp);
						
						// Corrige un bug du fonctionnement d'Internet Explorer
						nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
								.substring(nomFichier.lastIndexOf('\\') + 1);

						// On écrit définitivement le fichier sur le disque
						f.writeFile(part, nomFichier, SLIDER_PATH);

						// SAVE NEW IMAGE
						String dbPath = "assets/img/slider/" + nomFichier;
						s.setImage(dbPath);
						
					}
					
					// SAVE
					s.setTitre(t);
					s.setTitreBouton(tb);
					s.setUrl(urlB);
					s.setDescription(text);
					
					System.out.println(s);
					sd.save(s);
					
				}
			}
			
			response.sendRedirect("SiteFrontOffice?alertUpdateSlider=" + alertUpdateSlider);
		}
	}

//	private void writeFile(Part part, String nomFichier, String chemin) throws IOException {
//		BufferedInputStream entree = null;
//		BufferedOutputStream sortie = null;
//		try {
//			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
//			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
//
//			byte[] tampon = new byte[TAILLE_TAMPON];
//			int longueur;
//			while ((longueur = entree.read(tampon)) > 0) {
//				sortie.write(tampon, 0, longueur);
//			}
//		} finally {
//			try {
//				sortie.close();
//			} catch (IOException ignore) {
//			}
//			try {
//				entree.close();
//			} catch (IOException ignore) {
//			}
//		}
//	}
//
//	private static String getFileName(Part part) {
//		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
//			if (contentDisposition.trim().startsWith("filename")) {
//				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
//			}
//		}
//		return null;
//	}
}
