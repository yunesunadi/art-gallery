package main;

public class Artwork {
	private String id;
	private String name;
	private String description;
	private String price;
	private String type;
	private String category;
	private String artistName;
	private boolean isSoldOut;
	private boolean isRemoved;
	
	public Artwork(String id, String name, String description, String price, String type, String category, String artistName,
			boolean isSoldOut, boolean isRemoved) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
		this.category = category;
		this.artistName = artistName;
		this.isSoldOut = isSoldOut;
		this.isRemoved = isRemoved;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getArtist() {
		return artistName;
	}
	
	public void setArtist(String artistName) {
		this.artistName = artistName;
	}
	
	public boolean isSoldOut() {
		return isSoldOut;
	}
	
	public void setSoldOut(boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}
	
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
}
