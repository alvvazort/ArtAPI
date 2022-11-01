package aiss.model;

public class Sculpture {
	
	//Attributes
	private String sculptureId;
	private String name;
	private String artist;
	private String year;
	private String material;
	
	//Constructors
	public Sculpture() {
	}

	public Sculpture(String sculptureId, String name, String artist, String year, String material) {
		this.sculptureId = sculptureId;
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.material = material;
	}
	
	public Sculpture(String name, String artist, String year, String material) {
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.material = material;
	}
	
	//Getters and Setters
	public String getSculptureId() {
		return sculptureId;
	}

	public void setSculptureId(String sculptureId) {
		this.sculptureId = sculptureId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}
