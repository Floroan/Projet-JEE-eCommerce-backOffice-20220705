package tools;

public final class Constantes {

	//basics
	public final static String archiver = "archiver", statut = "statut", etat = "etat", modifier= "modifier",
	
	// produit
	idProd = "idProd", prodTitre = "prodTitre", descripProd = "descripProd", mainImgProd ="mainImgProd", ssCatProd = "ssCatProd",
	stockProd = "stockProd", stockMinProd = "stockMinProd", PUProd= "PUProd", archiveProd= "archiveProd",
	
	//adresse
	newAdresse = "newAdresse", newVille = "newVille", newPays = "newPays", newCP = "newCP",
	
	// commande
	editCommande = "editCommande", archiveCommande = "archiveCommande", idcommande = "idCommande",
	
	// commentaire
	commentaireId = "commentaireId",
	
	// privilèges
	tout = "tout", gestionProduits = "gestionProduits", gestionCommandes = "gestionCommandes", 
	gestionAchats = "gestionAchats", moderateur = "moderateur", 
	
	//message
	idContact = "idContact",
	
	//database
	databaseName = "projetdeux_tangflo";
	
	public final static int 
	//message
	nonlu =0, enCours=1, resolu=2, nonResolu=3, 
			
	// etat commande aFaire, enPreparation, aLivrer, livree
	cmdSansEtat = 0, cmdEnCours = 1, cmdValidees = 2, cmdLivrees = 3;
			
	
	
}
