package model;

public class Slide {

	private int id, archiver;
	private String titre, description, image, titreBouton, url;
	
	public Slide() {
		super();
	}

	public Slide(int id, int archiver, String titre, String description, String image, String titreBouton, String url) {
		super();
		this.id = id;
		this.archiver = archiver;
		this.titre = titre;
		this.description = description;
		this.image = image;
		this.titreBouton = titreBouton;
		this.url = url;
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

	public String getTitreBouton() {
		return titreBouton;
	}

	public void setTitreBouton(String titreBouton) {
		this.titreBouton = titreBouton;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", archiver=" + archiver + ", titre=" + titre + ", description=" + description
				+ ", image=" + image + ", titreBouton=" + titreBouton + ", url=" + url + "]";
	}
	
	
}
