package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dao.CommandeDAO;
import dao.CommentaireDAO;
import dao.ContactDAO;
import dao.Entree_stockDAO;
import dao.GenericDAO;
import dao.RechercheDAO;
import dao.UtilisateurDAO;
import dao.VisiteDAO;
import model.Commande;
import model.Commentaire;
import model.Contact;
import model.Entree_stock;
import model.ModelSettersConstructor;
import model.Produit;
import model.Utilisateur;
import service_commande.Service_commandes;
import service_produit.Service_produits_fournisseur;
import tools.Database;
import tools.RegexValidator;

public class TestMoi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Integer ii = new Integer(2);
//		
//		UtilisateurDAO utdao;
//		Utilisateur ut = new Utilisateur();
//		
//		Database.Connect();
//		utdao = new UtilisateurDAO();
//		ut = utdao.getOneAndReviews(3);
//		 System.out.println(ut.userAndCommentaires());
		 
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
//		 ArrayList<Commande> comdsByEtat = comDao.getAllByEtat(0);
//		 for(Commande cmd : comdsByEtat) {
//			 System.out.println(cmd.toStringFull());
//		 }
//		 
//		 System.out.println("commandes sur 7 jours: " + comDao.getWithInterval(7));
//		 System.out.println("commandes sur 30 jours: " + comDao.getWithInterval(30));
		 
//		 RechercheDAO recDao = new RechercheDAO();
//		 
//		 System.out.println(recDao.getmotsAndcount(2));
//		 
//		 System.out.println(recDao.getmotCountAndUser());
		 
		 
		 
//		 comDao.countCommande_a24h();
//		 
		 //GenericDAO<Commande> gen = new GenericDAO<Commande>(new Commande());
		 //gen.resultObject_WithIntervalDays("Commandes", 30);
		 
		GenericDAO gen = new GenericDAO();
		
		 TreeMap<String, String> mp = gen.getColumnsAndLengthByOrder("utilisateurs");
		 String testmail = "jesuisun@email.com";
		 String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 
		 boolean first = RegexValidator.matchMe(testmail, regexEmail);
		 
		 System.out.println(first);
		 
		 for(Map.Entry<String, String> entry : mp.entrySet()) {
			 System.out.println(entry.getKey() + ", " + entry.getValue());
		 }
		 
//		 gen.countRows("commandes");
//		 gen.countRows_WithInterval("commandes", 224);
//		 
//		 System.out.println("last24h commandes: " + Service_commandes.last_commandes_With24hInterval() + " %");
		 
//			Class<?> cl = Commande.class;
//			System.out.println("ma classe: " +  cl.getSimpleName());
//			Method[] meths = cl.getMethods();
//			
//			for(Method m: meths) {
//				System.out.println(m.getName());
//			}
//			
//		 ContactDAO con = new ContactDAO();
//		 ArrayList<Contact> contacts = con.getAll();
//		 System.out.println(contacts);
		
		 
		 
		 

		 
		 
//		ArrayList<String > l = new ArrayList<String>();
//		l.add("toto");
//		l.add("titi");
//		l.add("tutu");
//		
//		ArrayList<String > l2 = new ArrayList<String>();
//		l2.add("toto");
//		l2.add("titi");
//		l2.add("tutu");
//		
//		String st1 = "onsenfou";
//		String st2 = "onsenfou2";
//		
//		
//		System.out.println(l);
//		
//		HashMap<String, ArrayList<String>> map = new HashMap<>();
//		
//		map.put(st1, l);
//		map.put(st2, l2);
//		
//		System.out.println(map);
//		
//		for(Map.Entry<String, ArrayList<String>> entry : map.entrySet() ) {
//			String key = entry.getKey();
//			
//			if(key.equals("onsenfou")) {
//			ArrayList<String> vals = entry.getValue();
//			if(vals.contains("toto")) {
//				System.out.println("trouvé");
//			}else {
//				System.out.println("pas trouvé");
//			}
//			}
//		}
//		
//		
//		VisiteDAO vDao = new VisiteDAO();
//		
//		for(Map.Entry<Produit, Integer> entry : vDao.getVisitesByProduit(5).entrySet() ) {
//			Produit key = entry.getKey();
//			Integer val = entry.getValue();
//			System.out.println(key.getTitre().substring(20) + "," + val);
//		}
		
		
		
	
		 
		 
	} // fin main
	

	
	
	
	
	
	//  combo page + privileges condition user + privileges
	private static boolean allowTo(HashMap<String, ArrayList<String>> privi, String page) {
		boolean allow = false;
		for(Map.Entry<String, ArrayList<String>> entry : privi.entrySet() ) {
			String key = entry.getKey();
			
			if(key.equals(page)) {
			ArrayList<String> vals = entry.getValue();
			if(vals.contains("toto")) {
				allow = true;
				System.out.println("trouvé");
			}else {
				allow = false;
				System.out.println("pas trouvé");
			}
			}
			
		}
		return allow;
		
	}

}
