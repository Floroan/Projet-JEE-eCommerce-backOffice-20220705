package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelSettersConstructor {

	private String nom;
	private String mail;
	
	private Pattern p;

	public ModelSettersConstructor(String nom, String mail) {
		super();
		this.setNom(nom);
		this.setMail(mail);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if(nom.length() < 4) {
			this.nom = "le nom est trop court";
		}else {
			this.nom = nom;
		}
		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		Matcher m = p.matcher(mail);
		if(!m.matches()) {
			this.mail = "Le format de l'email n'est pas bon";
		}else {
			this.mail = mail;
		}
		
	}
	
	
	
}
