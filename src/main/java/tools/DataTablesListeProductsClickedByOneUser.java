package tools;

public class DataTablesListeProductsClickedByOneUser {

	private int id;
	private String image;
	private String titre;
	private String mail;
	private int sumOfProductClicks;

	public DataTablesListeProductsClickedByOneUser() {
		super();
	}

	public DataTablesListeProductsClickedByOneUser(int id, String image, String titre, String mail,
			int sumOfProductClicks) {
		super();
		this.id = id;
		this.image = image;
		this.titre = titre;
		this.mail = mail;
		this.sumOfProductClicks = sumOfProductClicks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getSumOfProductClicks() {
		return sumOfProductClicks;
	}

	public void setSumOfProductClicks(int sumOfProductClicks) {
		this.sumOfProductClicks = sumOfProductClicks;
	}

}
