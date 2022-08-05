package service_commande;
import java.util.ArrayList;

import dao.CommandeDAO;
import model.Commande;
import tools.Database;

public class Service_commandes {

	
	public static int last_commandes_With24hInterval() {
		int count = 0;
		Database.Connect();
		CommandeDAO cdao = new CommandeDAO();
		int total = cdao.countTotalCommande();
		int last24h = cdao.countCommande_a24h();
		
		count = last24h * 100 / total;
		
		return count;
	}
	
	public static double chiffreAffaireTotal(ArrayList<Commande> cmds) {
		double tt = 0;
		for(Commande c : cmds) {
			
			tt += c.getColumnTotal();
		}
		return tt;
	}
}
