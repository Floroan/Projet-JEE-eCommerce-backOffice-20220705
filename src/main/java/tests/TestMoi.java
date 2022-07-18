package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;

import dao.CommandeDAO;
import dao.CommentaireDAO;
import dao.ContactDAO;
import dao.Entree_stockDAO;
import dao.GenericDAO;
import dao.UtilisateurDAO;
import model.Commande;
import model.Commentaire;
import model.Contact;
import model.Entree_stock;
import model.ModelSettersConstructor;
import model.Utilisateur;
import service_commande.Service_commandes;
import service_produit.Service_produits_fournisseur;
import tools.Database;

public class TestMoi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer ii = new Integer(2);
		
		UtilisateurDAO utdao;
		Utilisateur ut = new Utilisateur();
		
		Database.Connect();
		utdao = new UtilisateurDAO();
		ut = utdao.getOneAndReviews(3);
		 System.out.println(ut.userAndCommentaires());
		 
//		 Entree_stockDAO esDao = new Entree_stockDAO();
//		 ArrayList<Entree_stock> entrees = esDao.getAllByFournisseur(1);
//		 for(Entree_stock e : entrees) {
//			 System.out.println(e.affichage());
//		 }
//		 
//		 entrees = esDao.getAllByProduit(16);
//		 for(Entree_stock e : entrees) {
//			 System.out.println(e.affichage());
//		 }
		 
		 
//		 CommentaireDAO commentDao = new CommentaireDAO();
//		 ArrayList<Commentaire> commentaires = commentDao.getAllByProduit(16);
//		 System.out.println(commentaires);
//		 
//		 ModelSettersConstructor msc = new ModelSettersConstructor("luca", "t@t.vf");
//		 System.out.println(msc.getNom() + ", " + msc.getMail());
//	
		 //System.out.println(Service_produits_fournisseur.produitEntreeStockFournisseur(10));
		 
		 
//		 CommandeDAO comDao = new CommandeDAO();
//		 comDao.countCommande_a24h();
//		 
//		 GenericDAO gen = new GenericDAO();
//		 gen.countRows("commandes");
//		 gen.countRows_WithInterval("commandes", 224);
//		 
//		 System.out.println("last24h commandes: " + Service_commandes.last_commandes_With24hInterval() + " %");
		 
			Class<?> cl = Commande.class;
			System.out.println("ma classe: " +  cl.getSimpleName());
			Method[] meths = cl.getMethods();
			
			for(Method m: meths) {
				System.out.println(m.getName());
			}
			
		 ContactDAO con = new ContactDAO();
		 ArrayList<Contact> contacts = con.getAll();
		 System.out.println(contacts);
	}

}
