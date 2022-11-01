package aiss.model;

public class Painting {
	
	//Attributes
	private String paintingId;
	private String name;
	private String artist;
	private String year;
	private String technique;
	private String style;
	
	//Constructors
	public Painting() {
	}

	public Painting(String paintingId, String name, String artist, String year, String technique, String style) {
		this.paintingId = paintingId;
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.technique = technique;
		this.style = style;
	}
	
	public Painting(String name, String artist, String year, String technique, String style) {
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.technique = technique;
		this.style = style;
	}
	
	//Getters and Setters
	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
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

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}
