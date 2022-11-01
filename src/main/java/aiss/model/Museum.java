package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Museum {
	
	//Attributes
	private String museumId;
	private String name;
	private String country;
	private String city;
	private String openingYear;
	private List<Painting> paintings;
	private List<Sculpture> sculptures;
	
	//Constructors
	public Museum() {
	}
	
	public Museum(String museumId, String name, String country, String city, String openingYear,
			List<Painting> paintings, List<Sculpture> sculptures) {
		this.museumId = museumId;
		this.name = name;
		this.country = country;
		this.city = city;
		this.openingYear = openingYear;
		this.paintings = paintings;
		this.sculptures = sculptures;
	}
	
	public Museum(String name, String country, String city, String openingYear,
			List<Painting> paintings, List<Sculpture> sculptures) {
		this.name = name;
		this.country = country;
		this.city = city;
		this.openingYear = openingYear;
		this.paintings = paintings;
		this.sculptures = sculptures;
	}
	
	//Getters and Setters	
	public String getMuseumId() {
		return museumId;
	}

	public void setMuseumId(String museumId) {
		this.museumId = museumId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOpeningYear() {
		return openingYear;
	}

	public void setOpeningYear(String openingYear) {
		this.openingYear = openingYear;
	}

	public List<Painting> getPaintings() {
		return paintings;
	}

	protected void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

	public List<Sculpture> getSculptures() {
		return sculptures;
	}

	protected void setSculptures(List<Sculpture> sculptures) {
		this.sculptures = sculptures;
	}
	
	
	// Painting methods
	public Painting getPainting(String id) {
		if (paintings==null)
			return null;
		
		Painting painting =null;
		for(Painting p: paintings)
			if (p.getPaintingId().equals(id))
			{
				painting=p;
				break;
			}
		
		return painting;
	}
	
	public void addPainting(Painting p) {
		if (paintings==null)
			paintings = new ArrayList<Painting>();
		paintings.add(p);
	}
	
	public void deletePainting(Painting p) {
		paintings.remove(p);
	}
	
	public void deletePainting(String id) {
		Painting p = getPainting(id);
		if (p!=null)
			paintings.remove(p);
	}
	
	// Sculpture methods
	public Sculpture getSculpture(String id) {
		if (sculptures==null)
			return null;
		
		Sculpture sculpture =null;
		for(Sculpture s: sculptures)
			if (s.getSculptureId().equals(id))
			{
				sculpture=s;
				break;
			}
		
		return sculpture;
	}
	
	public void addSculpture(Sculpture s) {
		if (sculptures==null)
			sculptures = new ArrayList<Sculpture>();
		sculptures.add(s);
	}
	
	public void deleteSculpture(Sculpture s) {
		sculptures.remove(s);
	}
	
	public void deleteSculpture(String id) {
		Sculpture s = getSculpture(id);
		if (s!=null)
			sculptures.remove(s);
	}

}
