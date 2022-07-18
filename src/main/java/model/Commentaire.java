package model;

import java.sql.Date;

public class Commentaire {

	private int id, note, fk_prod, fk_user, archiver;
	private String commentaire;
	private Date date;
	
	private Produit prod;
	private Utilisateur utilisateur;
	
	public Commentaire() {
		super();
	}

	public Commentaire(int id, int note, int fk_prod, int fk_user, int archiver, String commentaire, Date date,
			Produit prod, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.note = note;
		this.fk_prod = fk_prod;
		this.fk_user = fk_user;
		this.archiver = archiver;
		this.commentaire = commentaire;
		this.date = date;
		this.prod = prod;
		this.utilisateur = utilisateur;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", note=" + note + ", fk_prod=" + fk_prod + ", fk_user=" + fk_user
				+ ", archiver=" + archiver + ", " + (commentaire != null ? "commentaire=" + commentaire + ", " : "")
				+ (date != null ? "date=" + date + ", " : "") + (prod != null ? "prod=" + prod + ", " : "")
				+ (utilisateur != null ? "utilisateur=" + utilisateur : "") + "]";
	}
	
	
}
