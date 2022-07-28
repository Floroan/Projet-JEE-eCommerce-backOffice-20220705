package tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class FileDownloader {

	/*
	 * À AJOUTER DANS web.xml
	 */
//	<servlet>
//    <description></description>
//    <display-name>SiteFrontOffice</display-name>
//    <servlet-name>SiteFrontOffice</servlet-name>
//    <servlet-class>control.SiteFrontOffice</servlet-class>
//    <multipart-config>
//		<location>/Users/tanguymanas/Documents/developpement/eclipse-202206-workspace-web/Projet-JEE-eCommerce-backOffice-20220705/src/main/webapp/assets/img/logotmp/</location> <!-- A adapter chez vous -->
//		<max-file-size>10485760</max-file-size> <!-- 10 Mo -->
//		<max-request-size>52428800</max-request-size> <!-- 5 x 10 Mo -->
//		<file-size-threshold>1048576</file-size-threshold> <!-- 1 Mo -->
//	  </multipart-config>
//  </servlet>
	
	public static final int TAILLE_TAMPON = 10240;

	public void writeFile(Part part, String nomFichier, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

	public static String getFileName(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
