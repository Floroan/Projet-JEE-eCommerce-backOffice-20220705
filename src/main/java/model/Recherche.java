package model;

import java.sql.Date;

public class Recherche {
 
	private int id, fk_user, archiver;
	private String motcle;
	private Date date;
	
	private Utilisateur u;
	
	public Recherche() {
		super();
	}

	public Recherche(int id, int fk_user, int archiver, String motcle, Date date) {
		super();
		this.id = id;
		this.fk_user = fk_user;
		this.archiver = archiver;
		this.motcle = motcle;
		this.date = date;
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

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public String getMotcle() {
		return motcle;
	}

	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Recherche [id=" + id + ", fk_user=" + fk_user + ", archiver=" + archiver + ", motcle=" + motcle
				+ ", date=" + date + ", u=" + u + "]";
	}
	
	
}
