package model;

public class Image {

	private int id, fk_produit, archiver;
	private String url;
	
	private Produit p;

	public Image(int id, int fk_produit, int archiver, String url, Produit p) {
		super();
		this.id = id;
		this.fk_produit = fk_produit;
		this.archiver = archiver;
		this.url = url;
		this.p = p;
	}
	
	public Image(int id, int fk_produit, int archiver, String url) {
		super();
		this.id = id;
		this.fk_produit = fk_produit;
		this.archiver = archiver;
		this.url = url;
	}

	public Image() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_produit() {
		return fk_produit;
	}

	public void setFk_produit(int fk_produit) {
		this.fk_produit = fk_produit;
	}

	public int getArchiver() {
		return archiver;
	}

	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Produit getP() {
		return p;
	}

	public void setP(Produit p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", fk_produit=" + fk_produit + ", archiver=" + archiver + ", url=" + url + ", p=" + p
				+ "]";
	}

	
}
