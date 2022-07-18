package model;

public class Sous_categorie {

	private int id, fk_categorie, archiver;
	private String titre;
	
	private Categorie c;
	
	public Sous_categorie() {
		super();
	}

	public Sous_categorie(int id, int fk_categorie, int archiver, String titre) {
		super();
		this.id = id;
		this.fk_categorie = fk_categorie;
		this.archiver = archiver;
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_categorie() {
		return fk_categorie;
	}

	public void setFk_categorie(int fk_categorie) {
		this.fk_categorie = fk_categorie;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Categorie getC() {
		return c;
	}

	public void setC(Categorie c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Sous_categorie [id=" + id + ", fk_categorie=" + fk_categorie + ", archiver=" + archiver + ", "
				+ (titre != null ? "titre=" + titre + ", " : "") + (c != null ? "c=" + c : "") + "]";
	}
	
}
