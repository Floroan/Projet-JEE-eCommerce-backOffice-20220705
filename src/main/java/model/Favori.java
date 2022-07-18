package model;

public class Favori {

	private int id, fk_prod, fk_user, archiver;

	public Favori(int id, int fk_prod, int fk_user, int archiver) {
		super();
		this.id = id;
		this.fk_prod = fk_prod;
		this.fk_user = fk_user;
		this.archiver = archiver;
	}

	public Favori() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_prod() {
		return fk_prod;
	}

	public void setFk_prod(int fk_prod) {
		this.fk_prod = fk_prod;
	}

	public int getFk_user() {
		return fk_user;
	}

	public void setFk_user(int fk_user) {
		this.fk_user = fk_user;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	@Override
	public String toString() {
		return "Favori [id=" + id + ", fk_prod=" + fk_prod + ", fk_user=" + fk_user + ", archiver=" + archiver + "]";
	}
	
	
	
}
