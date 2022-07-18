package model;

import java.sql.Date;

public class Entree_stock {

	private int id, fk_fournisseur, fk_produit, qte, archiver;
	private Date date;
	
	private Fournisseur f;
	private Produit p;
	
	public Entree_stock(int id, int fk_fournisseur, int fk_produit, int qte, int archiver, Date date, Fournisseur f,
			Produit p) {
		super();
		this.id = id;
		this.fk_fournisseur = fk_fournisseur;
		this.fk_produit = fk_produit;
		this.qte = qte;
		this.archiver = archiver;
		this.date = date;
		this.f = f;
		this.p = p;
	}

	public Entree_stock() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_fournisseur() {
		return fk_fournisseur;
	}

	public void setFk_fournisseur(int fk_fournisseur) {
		this.fk_fournisseur = fk_fournisseur;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Fournisseur getF() {
		return f;
	}

	public void setF(Fournisseur f) {
		this.f = f;
	}

	public Produit getP() {
		return p;
	}

	public void setP(Produit p) {
		this.p = p;
	}


	public String affichage() {
		return "Entree_stock [id=" + id + ", fk_fournisseur=" + fk_fournisseur + ", fk_produit=" + fk_produit + ", qte="
				+ qte + ", archiver=" + archiver + ", date=" + date + ", f=" + f + ", p=" + p + "]";
	}

	@Override
	public String toString() {
		return "Entree_stock [id=" + id + ", fk_fournisseur=" + fk_fournisseur + ", fk_produit=" + fk_produit + ", qte="
				+ qte + ", archiver=" + archiver + ", date=" + date + "]";
	}
	
	
}
