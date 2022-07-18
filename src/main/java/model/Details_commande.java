package model;

public class Details_commande {

	 private int id, fk_commande, fk_produit, qte, archiver;
	 private double prix;
	
	 private Commande c;
	 private Produit p;



	public Details_commande(int id, int fk_commande, int fk_produit, int qte, int archiver, double prix, Commande c,
			Produit p) {
		super();
		this.id = id;
		this.fk_commande = fk_commande;
		this.fk_produit = fk_produit;
		this.qte = qte;
		this.archiver = archiver;
		this.prix = prix;
		this.c = c;
		this.p = p;
	}

	public Details_commande() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_commande() {
		return fk_commande;
	}

	public void setFk_commande(int fk_commande) {
		this.fk_commande = fk_commande;
	}

	public int getFk_produit() {
		return fk_produit;
	}

	public void setFk_produit(int fk_produit) {
		this.fk_produit = fk_produit;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Commande getC() {
		return c;
	}

	public void setC(Commande c) {
		this.c = c;
	}
	
	public Produit getP() {
		return p;
	}

	public void setP(Produit p) {
		this.p = p;
	}

	public String affichage() {
		return "Details_commande [id=" + id + ", fk_commande=" + fk_commande + ", fk_produit=" + fk_produit + ", qte="
				+ qte + ", archiver=" + archiver + ", prix=" + prix + ", " + (c != null ? "c=" + c + ", " : "")
				+ (p != null ? "p=" + p : "") + "]";
	}
	
	@Override
	public String toString() {
		return "Details_commandeDAO [id=" + id + ", fk_commande=" + fk_commande + ", fk_produit=" + fk_produit + ", qte="
				+ qte + ", archiver=" + archiver + ", prix=" + prix + "]";
	}
	 
	 
	 
}
