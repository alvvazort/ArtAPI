package aiss.model.repository;

import java.util.Collection;

import aiss.model.Painting;
import aiss.model.Sculpture;
import aiss.model.Museum;

public interface MuseumRepository {
	
	
	// Paintings
	public void addPainting(Painting p);
	public Collection<Painting> getAllPaintings();
	public Painting getPainting(String paintingId);
	public void updatePainting(Painting p);
	public void deletePainting(String paintingId);
	
	// Sculptures
	public void addSculpture(Sculpture s);
	public Collection<Sculpture> getAllSculptures();
	public Sculpture getSculpture(String sculptureId);
	public void updateSculpture(Sculpture s);
	public void deleteSculpture(String sculptureId);
	
	// Museums
	public void addMuseum(Museum m);
	public Collection<Museum> getAllMuseums();
	public Museum getMuseum(String museumId);
	public void updateMuseum(Museum m);
	public void deleteMuseum(String museumId);
	
	public Collection<Painting> getAllPaintingsByMuseum(String museumId);
	public void addPainting(String museumId, String paintingId);
	public void removePainting(String museumId, String paintingId);
	
	public Collection<Sculpture> getAllSculpturesByMuseum(String museumId);
	public void addSculpture(String museumId, String sculptureId);
	public void removeSculpture(String museumId, String sculptureId); 

}
