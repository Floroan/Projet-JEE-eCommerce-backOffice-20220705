package tools;

import java.lang.reflect.Array;

public final class Constantes {

	//basics
	public final static String archiver = "archiver", statut = "statut", etat = "etat", modifier= "modifier", supprimer = "supprimer",
	
	// produit
	idProd = "idProd", prodTitre = "prodTitre", descripProd = "descripProd", mainImgProd ="mainImgProd", ssCatProd = "ssCatProd",
	stockProd = "stockProd", stockMinProd = "stockMinProd", PUProd= "PUProd", archiveProd= "archiveProd",
	
	//adresse
	changerAdresse= "changerAdresse", newAdresse = "newAdresse", newVille = "newVille", newPays = "newPays", newCP = "newCP",
	
	// commande
	editCommande = "editCommande", archiveCommande = "archiveCommande", idcommande = "idCommande", remonteCmd = "remonteCmd", signalerCmd = "signalerCmd",
	
	// detail commande
	detailId = "detailId", detailQuantite = "detailQte", modifierQte= "modifierQte", augmenterQte = "augmenterQte", diminuerQte = "diminuerQte", archiverDetail = "archiverDetail",
	
	// commentaire
	commentaireId = "commentaireId",
	
	// privilèges
	tout = "tout", gestionProduits = "gestionProduits", gestionCommandes = "gestionCommandes", 
	gestionAchats = "gestionAchats", moderateur = "moderateur", 
	
	//message = contact...
	idContact = "idContact", rechercheContact = "rechercheContact",
	
	//database
	databaseName = "projetdeux_tangflo",
	

	// messages
	messageNumberFormat = "Une ou plusieurs saisies sont invalides, vérifiez les champs";
	
	public final static int 
	//message
	nonlu =0, enCours=1, resolu=2, nonResolu=3, 
	
	// etat commande  aPreparer, enPreparation, aLivrer (bouton en livraison), livree(historique)
	cmdSansEtat = 0, cmdEnCours = 1, cmdValidee = 2, cmdLivree = 3, cmdSignalee = 4,
			
	
	
	/* Contantes longueur max des champs en base de données */
	
			
	// generique, longueur max des champs
	lghArchiver = 1, lghID = 1, lghEtat = 1,
	
	// adresse livraison, longueur max des champs
	lghLigneAdress = 255, lghCp = 11, lgjVille = 100, lghPays = 100,
	
	// produit, longueur max des champs
	lghPrix = 11, lghStock = 11, lghStock_min = 11,
	
	// commande, longueur max des champs
	lghEtatCommande = 11,
	
	// commentaire, longueur max des champs
	lghCommentaire = 1000, lghNote = 11,
	
	// coordonnées, longueur max des champs
	lghNomCoordonnees = 50, lghAdresseCoordonnees = 255, lghTelephoneCoordonnees = 25, lghEmailCoordonnees = 50,
			lghLogoCoordonnees = 255,
	
	// contact, longueur max des champs
	lghSujet = 100, lghEmailContact = 100;
	
	//suite..
	
	
	/* les contantes regex */
	
	
	
}