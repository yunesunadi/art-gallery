package main;

public class OrderedArtwork {
	private String id;
	private Customer customer;
	private String artworkName;
	private String orderDate;
	
	public OrderedArtwork(String id, Customer customer, String artworkName, String orderDate) {
		this.id = id;
		this.customer = customer;
		this.artworkName = artworkName;
		this.orderDate = orderDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getArtwork() {
		return artworkName;
	}

	public void setArtwork(String artworkName) {
		this.artworkName = artworkName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
