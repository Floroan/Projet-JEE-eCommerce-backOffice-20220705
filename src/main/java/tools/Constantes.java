package tools;

public final class Constantes {

	//basics
	public final static String archiver = "archiver", statut = "statut", etat = "etat", modifier= "modifier", supprimer = "supprimer",
	
	// produit
	idProd = "idProd", prodTitre = "prodTitre", descripProd = "descripProd", mainImgProd ="mainImgProd", ssCatProd = "ssCatProd",
	stockProd = "stockProd", stockMinProd = "stockMinProd", PUProd= "PUProd", archiveProd= "archiveProd",
	
	//adresse
	newAdresse = "newAdresse", newVille = "newVille", newPays = "newPays", newCP = "newCP",
	
	// commande
	editCommande = "editCommande", archiveCommande = "archiveCommande", idcommande = "idCommande",
	
	// detail commande
	detailId = "detailId", detailQuantite = "detailQte", modifierQte= "modifierQte", augmenterQte = "augmenterQte", diminuerQte = "diminuerQte", archiverDetail = "archiverDetail",
	
	// commentaire
	commentaireId = "commentaireId",
	
	// privilèges
	tout = "tout", gestionProduits = "gestionProduits", gestionCommandes = "gestionCommandes", 
	gestionAchats = "gestionAchats", moderateur = "moderateur", 
	
	//message
	idContact = "idContact",
	
	//database
	databaseName = "projetdeux_tangflo",
	
	// messages
	messageNumberFormat = "Une plusieurs saisies sont invalides, vérifiez les champs";
	
	public final static int 
	//message
	nonlu =0, enCours=1, resolu=2, nonResolu=3, 
			
	// etat commande  aPreparer, enPreparation, aLivrer (bouton en livraison), livree(historique)
	cmdSansEtat = 0, cmdEnCours = 1, cmdValidee = 2, cmdLivree = 3;
			
	
	
}
