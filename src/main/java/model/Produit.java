package model;

import java.util.ArrayList;

public class Produit {

	private int id, fk_sous_categorie, stock, stock_min, archiver;
	private String titre, description, image;
	private double prix;
	private ArrayList<Image> images;
	
	private ArrayList<Commentaire> commentaires;
	private ArrayList<Visite> visites;
	
	private ArrayList<Entree_stock> entrees_stock;
	
	private Sous_categorie ss_categorie;

	public Produit(int id, int fk_sous_categorie, int stock, int stock_min, int archiver, String titre,
			String description, String image, double prix, ArrayList<Entree_stock> entrees_stock,
			Sous_categorie ss_categorie) {
		super();
		this.id = id;
		this.fk_sous_categorie = fk_sous_categorie;
		this.stock = stock;
		this.stock_min = stock_min;
		this.archiver = archiver;
		this.titre = titre;
		this.description = description;
		this.image = image;
		this.prix = prix;
		this.entrees_stock = entrees_stock;
		this.ss_categorie = ss_categorie;
	}

	public Produit() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_sous_categorie() {
		return fk_sous_categorie;
	}

	public void setFk_sous_categorie(int fk_sous_categorie) {
		this.fk_sous_categorie = fk_sous_categorie;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock_min() {
		return stock_min;
	}

	public void setStock_min(int stock_min) {
		this.stock_min = stock_min;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(ArrayList<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public ArrayList<Visite> getVisites() {
		return visites;
	}

	public void setVisites(ArrayList<Visite> visites) {
		this.visites = visites;
	}

	public ArrayList<Entree_stock> getEntrees_stock() {
		return entrees_stock;
	}

	public void setEntrees_stock(ArrayList<Entree_stock> entrees_stock) {
		this.entrees_stock = entrees_stock;
	}

	public Sous_categorie getSs_categorie() {
		return ss_categorie;
	}

	public void setSs_categorie(Sous_categorie ss_categorie) {
		this.ss_categorie = ss_categorie;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", fk_sous_categorie=" + fk_sous_categorie + ", stock=" + stock + ", stock_min="
				+ stock_min + ", archiver=" + archiver + ", " + (titre != null ? "titre=" + titre + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (image != null ? "image=" + image + ", " : "") + "prix=" + prix + ", "
				+ (images != null ? "images=" + images + ", " : "")
				+ (commentaires != null ? "commentaires=" + commentaires + ", " : "")
				+ (visites != null ? "visites=" + visites + ", " : "")
				+ (entrees_stock != null ? "entrees_stock=" + entrees_stock + ", " : "")
				+ (ss_categorie != null ? "ss_categorie=" + ss_categorie : "") + "]";
	}
	
}

