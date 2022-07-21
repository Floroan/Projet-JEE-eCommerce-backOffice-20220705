package tools;

public class DataTablesListeClients {

	// ATTRIBUTES
		private int id;
		private String name;
		private String mail;
		private String dateFirstOrder;
		private String dateLastOrder;
		private Double orderAverage;
		private Double orderSum;
		
		// CONSTRUCTORS
		public DataTablesListeClients() {
			super();
		}
		
		public DataTablesListeClients(int id, String name, String mail, String dateFirstOrder, String dateLastOrder, Double orderAverage,
				Double orderSum) {
			super();
			this.id = id;
			this.name = name;
			this.mail = mail;
			this.dateFirstOrder = dateFirstOrder;
			this.dateLastOrder = dateLastOrder;
			this.orderAverage = orderAverage;
			this.orderSum = orderSum;
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
		public String getDateFirstOrder() {
			return dateFirstOrder;
		}
		public void setDateFirstOrder(String dateFirstOrder) {
			this.dateFirstOrder = dateFirstOrder;
		}
		public String getDateLastOrder() {
			return dateLastOrder;
		}
		public void setDateLastOrder(String dateLastOrder) {
			this.dateLastOrder = dateLastOrder;
		}
		public Double getOrderAverage() {
			return orderAverage;
		}
		public void setOrderAverage(Double orderAverage) {
			this.orderAverage = orderAverage;
		}
		public Double getOrderSum() {
			return orderSum;
		}
		public void setOrderSum(Double orderSum) {
			this.orderSum = orderSum;
		}

		@Override
		public String toString() {
			return "DataTablesClients [id=" + id + ", name=" + name + ", mail=" + mail + ", dateFirstOrder=" + dateFirstOrder
					+ ", dateLastOrder=" + dateLastOrder + ", orderAverage=" + orderAverage + ", orderSum=" + orderSum
					+ "]";
		}
		
}
