package model;

public class Adresse_livraison {

	private int id, fk_user, archiver;
	private String adresse, cp, ville, pays;
	
	private Utilisateur u;
	private Commande c;
	
	public Adresse_livraison() {
		super();
	}


	public Adresse_livraison(int id, int fk_user, int archiver, String adresse, String cp, String ville, String pays,
			Utilisateur u, Commande c) {
		super();
		this.id = id;
		this.fk_user = fk_user;
		this.archiver = archiver;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.u = u;
		this.c = c;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public Commande getC() {
		return c;
	}

	public void setC(Commande c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Adresse_livraison [id=" + id + ", fk_user=" + fk_user + ", archiver=" + archiver + ", adresse="
				+ adresse + ", cp=" + cp + ", ville=" + ville + ", pays=" + pays + ", u=" + u + ", c=" + c + "]";
	}

}
