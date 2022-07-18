package model;

public class Coordonnee {

	
	private int id, archiver;
	private String nom, adresse, telephone, email, logo;
	
	public Coordonnee() {
		super();
	}
	
	public Coordonnee(int id, int archiver, String nom, String adresse, String telephone, String email, String logo) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.logo = logo;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "Coordonnee [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", adresse=" + adresse
				+ ", telephone=" + telephone + ", email=" + email + ", logo=" + logo + "]";
	}
	
	
}
