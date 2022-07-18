package model;

import java.sql.Date;

public class Contact {

	private int id, fk_user, etat, archiver;
	private String sujet, email, message;
	private Date date;
	
	private Utilisateur u;

	public Contact(int id, int fk_user, int etat, int archiver, String sujet, String email, String message, Date date,
			Utilisateur u) {
		super();
		this.id = id;
		this.fk_user = fk_user;
		this.etat = etat;
		this.archiver = archiver;
		this.sujet = sujet;
		this.email = email;
		this.message = message;
		this.date = date;
		this.u = u;
	}

	public Contact() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_user() {
		return fk_user;
	}

	public void setFk_user(int fk_user) {
		this.fk_user = fk_user;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Override
	public String toString() {
		return "Contact [id=" + id + ", fk_user=" + fk_user + ", etat=" + etat + ", archiver=" + archiver + ", "
				+ (sujet != null ? "sujet=" + sujet + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (message != null ? "message=" + message + ", " : "") + (date != null ? "date=" + date + ", " : "")
				+ (u != null ? "u=" + u : "") + "]";
	}

	
}
