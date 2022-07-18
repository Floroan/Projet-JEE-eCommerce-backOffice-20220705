package model;

import java.util.ArrayList;

public class Categorie {

	private int id, archiver;
	private String titre;
	private ArrayList<Sous_categorie> sousCAtegorie;
	
	public Categorie() {
		super();
	}

	public Categorie(int id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public ArrayList<Sous_categorie> getSousCAtegorie() {
		return sousCAtegorie;
	}

	public void setSousCAtegorie(ArrayList<Sous_categorie> sousCAtegorie) {
		this.sousCAtegorie = sousCAtegorie;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", archiver=" + archiver + ", titre=" + titre + ", sousCAtegorie="
				+ sousCAtegorie + "]";
	}
	
}
