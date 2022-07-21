package model;

import java.sql.Date;
import java.util.ArrayList;

public class Utilisateur {

	private int id, archiver;
	private String nom, prenom, email, password;
	private Date date_inscription;
	
	private ArrayList<Commande> commandes;
	private ArrayList<Recherche> recherches;
	private ArrayList<Adresse_livraison> adresses;
	private ArrayList<Contact> contacts;
	private ArrayList<Favori> favoris;
	private ArrayList<Commentaire> commentaires;
	private ArrayList<Visite> visites;
	
	public Utilisateur(int id, int archiver, String nom, String prenom, String email, String password,
			Date date_inscription) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.date_inscription = date_inscription;
	}

	public Utilisateur(int id, int archiver, String nom, String prenom, String email, String password,
			Date date_inscription, ArrayList<Commande> commandes) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.date_inscription = date_inscription;
		this.commandes = commandes;
	}
	
	public Utilisateur() {
		super();
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate_inscription() {
		return date_inscription;
	}

	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}
	
	

	public ArrayList<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}

	public ArrayList<Recherche> getRecherches() {
		return recherches;
	}

	public void setRecherches(ArrayList<Recherche> recherches) {
		this.recherches = recherches;
	}

	public ArrayList<Adresse_livraison> getAdresses() {
		return adresses;
	}

	public void setAdresses(ArrayList<Adresse_livraison> adresses) {
		this.adresses = adresses;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	

	public ArrayList<Favori> getFavoris() {
		return favoris;
	}

	public void setFavoris(ArrayList<Favori> favoris) {
		this.favoris = favoris;
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

	public String userAndCommandes() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", commandes="
				+ commandes + "]";
	}

	public String userAndRecherches() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", recherches="
				+ recherches + "]";
	}


	public String userAndAdresses() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", adresses=" + adresses
				+ "]";
	}


	public String userAndContacts() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", contacts=" + contacts
				+ "]";
	}


	public String userAndFavoris() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", favoris=" + favoris
				+ "]";
	}

	
	public String userAndCommentaires() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", commentaires="
				+ commentaires + "]";
	}


	public String toStringFull() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", commandes="
				+ commandes + ", recherches=" + recherches + ", adresses=" + adresses + ", contacts=" + contacts
				+ ", favoris=" + favoris + ", commentaires=" + commentaires + "]";
	}
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", date_inscription=" + date_inscription + ", commandes="
				+ commandes + ", recherches=" + recherches + ", adresses=" + adresses + ", contacts=" + contacts
				+ ", favoris=" + favoris + ", commentaires=" + commentaires + ", visites=" + visites + "]";
	}

}
