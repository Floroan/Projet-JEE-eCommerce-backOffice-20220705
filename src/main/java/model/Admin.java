package model;

public class Admin {

	private int id, archiver;
	private String nom, email, password, privileges;
	
	public Admin(int id, int archiver, String nom, String email, String password, String privileges) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.privileges = privileges;
	}

	public Admin() {
		super();
	}

	public Admin(String nom, String email, String password) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
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

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", archiver=" + archiver + ", nom=" + nom + ", email=" + email + ", password="
				+ password + ", privileges=" + privileges + "]";
	}
	
	
	
	
}
