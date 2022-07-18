package model;

import java.sql.Date;

public class Visite {
 
	private int id, archiver, fk_prod, fk_user;
	private Date date;
	
	private Utilisateur u;
	private Produit p;
	
	public Visite() {
		super();
	}

	public Visite(int id, int archiver, int fk_prod, int fk_user, Date date) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.fk_prod = fk_prod;
		this.fk_user = fk_user;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public int getFk_prod() {
		return fk_prod;
	}

	public void setFk_prod(int fk_prod) {
		this.fk_prod = fk_prod;
	}

	public int getFk_user() {
		return fk_user;
	}

	public void setFk_user(int fk_user) {
		this.fk_user = fk_user;
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

	public Produit getP() {
		return p;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public void setP(Produit p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Visite [id=" + id + ", archiver=" + archiver + ", fk_prod=" + fk_prod + ", fk_user=" + fk_user + ", "
				+ (date != null ? "date=" + date + ", " : "") + (u != null ? "u=" + u + ", " : "")
				+ (p != null ? "p=" + p : "") + "]";
	}
	
	
}
