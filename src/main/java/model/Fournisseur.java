package model;

import tools.RegexValidator;

public class Fournisseur {

	private int id, archiver;
	private String nom;

	public Fournisseur() {
		super();
	}

	public Fournisseur(int id, int archiver, String nom) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.nom = nom;
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

	public void setNom(String nom) throws BeanException {
		if ( !RegexValidator.supplierValidator(nom) ) {
			throw new BeanException(
					"Depuis le setter.<br>"
					+ "Le nom du fournisseur doit posséder de 1 à 50 caractères<br>"
					+ "sans contenir de caractères spéciaux."
					);
		} else {
			this.nom = nom;			
		}
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + "]";
	}

}