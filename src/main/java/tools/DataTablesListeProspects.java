package tools;

import java.sql.Date;

public class DataTablesListeProspects {

	// ATTRIBUTES
	private int id;
	private String name;
	private String mail;
	private Date dateLastVisit;
	private int numberOfProductsViewed;
	private int sumOfProductClicks;

	// CONSTRUCTORS
	public DataTablesListeProspects() {
		super();
	}

	public DataTablesListeProspects(int id, String name, String mail, Date dateLastVisit, int numberOfProductsViewed,
			int sumOfProductClicks) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.dateLastVisit = dateLastVisit;
		this.numberOfProductsViewed = numberOfProductsViewed;
		this.sumOfProductClicks = sumOfProductClicks;
	}

	// PROPERTIES
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateLastVisit() {
		return dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	public int getNumberOfProductsViewed() {
		return numberOfProductsViewed;
	}

	public void setNumberOfProductsViewed(int numberOfProductsViewed) {
		this.numberOfProductsViewed = numberOfProductsViewed;
	}

	public int getSumOfProductClicks() {
		return sumOfProductClicks;
	}

	public void setSumOfProductClicks(int sumOfProductClicks) {
		this.sumOfProductClicks = sumOfProductClicks;
	}

	@Override
	public String toString() {
		return "\n DataTablesListeProspects [id=" + id + ", name=" + name + ", mail=" + mail + ", dateLastVisit=" + dateLastVisit
				+ ", numberOfProductsViewed=" + numberOfProductsViewed + ", sumOfProductClicks=" + sumOfProductClicks
				+ "]";
	}
	
}
