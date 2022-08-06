package model;

import java.sql.Date;
import java.util.ArrayList;

import tools.Constantes;

public class Commande {

	private int id, fk_utilisateur, fk_adresse, etat, archiver;
	private double total;
	private Date date;
	
	private Utilisateur u;
	
	private ArrayList<Details_commande> details;
	private Adresse_livraison adresse;

	
	public Commande() {
		super();
	}

	

	public Commande(int id, int fk_utilisateur, int fk_adresse, int etat, int archiver, double total, Date date,
			Utilisateur u, ArrayList<Details_commande> details, Adresse_livraison adresse) {
		super();
		this.id = id;
		this.fk_utilisateur = fk_utilisateur;
		this.fk_adresse = fk_adresse;
		this.etat = etat;
		this.archiver = archiver;
		this.total = total;
		this.date = date;
		this.u = u;
		this.details = details;
		this.adresse = adresse;
	}



	public int getFk_adresse() {
		return fk_adresse;
	}

	public void setFk_adresse(int fk_adresse) {
		this.fk_adresse = fk_adresse;
	}

	public ArrayList<Details_commande> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<Details_commande> details) {
		this.details = details;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_utilisateur() {
		return fk_utilisateur;
	}

	public void setFk_utilisateur(int fk_utilisateur) {
		this.fk_utilisateur = fk_utilisateur;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		if(etat <= Constantes.maxEtats) {
			this.etat = etat;
		}else {
			// soulever l'exception
		}
		
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public double getTotal() {
		return total;	
	}
	
	public double getTotalDetails() {
		total = 0;
		if(this.details == null) {
			return this.total;
		}else {
		for (Details_commande dc : details) {
			System.out.println("qte from getter: " + dc.getQte() + "/" + dc.getPrix());
			total += dc.getQte() * dc.getPrix();
		}
		return this.total;
		}	
	}
	

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public Adresse_livraison getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse_livraison adresse) {
		this.adresse = adresse;
	}
	

	@Override
	public String toString() {
		return "Commande [id=" + id + ", fk_utilisateur=" + fk_utilisateur + ", fk_adresse=" + fk_adresse + ", etat="
				+ etat + ", archiver=" + archiver + ", total=" + total + ", " + (date != null ? "date=" + date : "")
				+ "]";
	}



	public String toStringFull() {
		return "Commande [id=" + id + ", fk_utilisateur=" + fk_utilisateur + ", fk_adresse=" + fk_adresse + ", etat="
				+ etat + ", archiver=" + archiver + ", total=" + total + ", date=" + date + ", u=" + u + ", details="
				+ details + ", adresse=" + adresse + "]";
	}
	

	public double getColumnTotal() {
		return total;	
	}
	
}
