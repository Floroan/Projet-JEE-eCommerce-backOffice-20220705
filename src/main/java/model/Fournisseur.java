package model;

public class Fournisseur {

	private int id, archiver;
	private String nom;
	
	public Fournisseur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Fournisseur() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + "]";
	}
	
}
