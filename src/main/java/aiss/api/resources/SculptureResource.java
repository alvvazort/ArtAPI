package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import aiss.api.resources.comparators.ComparatorNameSculpture;
import aiss.api.resources.comparators.ComparatorNameSculptureReversed;
import aiss.model.Sculpture;
import aiss.model.repository.MapMuseumRepository;
import aiss.model.repository.MuseumRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/sculptures")
public class SculptureResource {
	
	public static SculptureResource _instance=null;
	MuseumRepository repository;
	
	private SculptureResource(){
		repository=MapMuseumRepository.getInstance();
	}
	
	public static SculptureResource getInstance()
	{
		if(_instance==null)
			_instance=new SculptureResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Sculpture> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("knownArtist") Boolean knownArtist,
			@QueryParam("limit") String limit, @QueryParam("offset") String offset, @QueryParam("page") String page){

		List<Sculpture> sculptures = new ArrayList<Sculpture>();
		List<Sculpture> result = new ArrayList<Sculpture>();

		for(Sculpture s : repository.getAllSculptures()) {
			if(q==null || q.equals("")
					|| s.getName().toLowerCase().contains(q.toLowerCase()) 
					|| s.getMaterial().toLowerCase().contains(q.toLowerCase()) 
					|| s.getArtist().toLowerCase().contains(q.toLowerCase())
					|| s.getYear().toLowerCase().contains(q.toLowerCase())){
				if(knownArtist == null || 
						(knownArtist && s.getArtist() != "Desconocido") 
						|| (!knownArtist && s.getArtist() == "Desconocido")){
					sculptures.add(s);
				}
			}	
		}
		
		if(order != null) {
			if(order.equals("name")) {
				Collections.sort(sculptures, new ComparatorNameSculpture());
			}
			else if(order.equals("-name")) {
				Collections.sort(sculptures, new ComparatorNameSculptureReversed());
			}
			else {
				throw new BadRequestException("The order parameter must be 'name' or '-name'.");
			}
		}

		Integer limite = 0;
		Integer pag = 1;

		if(offset!=null) {
			try {
				sculptures = sculptures.subList(Integer.valueOf(offset), sculptures.size());
			}catch(Exception e) {
				throw new IllegalArgumentException("Value too big (" + Integer.valueOf(offset) + ")"); 
			}
		}

		if(limit != null && page != null) {
			pag = Integer.valueOf(page);
			try {
				limite = Integer.valueOf(limit);
				for(int i=(pag-1)*limite; i < pag*limite; i++)
					result.add(sculptures.get(i));

			}catch(Exception e) {
				throw new IllegalArgumentException("There are less than " + pag*limite + " sculptures matching those parameters");
			}
			return result;
		}

		else if(limit != null) {
			limite = Integer.valueOf(limit);
			if(limite>sculptures.size()) {
				limite=sculptures.size();
			}
			for(int i=0; i < limite; i++) 
				result.add(sculptures.get(i));
			return result;
		}

		return sculptures;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Sculpture get(@PathParam("id") String sculptureId)
	{
		
		Sculpture sculpture = repository.getSculpture(sculptureId);
		
		if (sculpture == null) {
			throw new NotFoundException("The sculpture with id= "+ sculptureId +" was not found");			
		}
		
		return sculpture;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSculpture(@Context UriInfo uriInfo, Sculpture sculpture) {
		
		if (sculpture.getName() == null || "".equals(sculpture.getName()))
			throw new BadRequestException("The name of the sculpture must not be null.");
		
		if (sculpture.getArtist()==null)
			throw new BadRequestException("The sculpture artist must not be null.");

		repository.addSculpture(sculpture);

		// Builds the response. Returns the song the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(sculpture.getSculptureId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(sculpture);			
		return resp.build();
	}
	
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateSculpture(@PathParam("id") String sculptureId, Sculpture sculpture) {
		
		if (sculpture.getName() == null || "".equals(sculpture.getName() ))
            throw new BadRequestException("The name of the sculpture must not be null.");

        if (sculpture.getArtist() == null)
            throw new BadRequestException("The sculpture artist must not be null.");
        
		Sculpture oldSculpture = repository.getSculpture(sculptureId);
		if (oldSculpture == null) {
			throw new NotFoundException("The sculpture with id= "+ sculptureId +" was not found.");			
		}
		
		// Update Name
		if (sculpture.getName()!=null)
			oldSculpture.setName(sculpture.getName());
		
		// Update Material
		if (sculpture.getMaterial()!=null)
			oldSculpture.setMaterial(sculpture.getMaterial());
		
		// Update Artist
		if (sculpture.getArtist()!=null)
			oldSculpture.setArtist(sculpture.getArtist());
		
		// Update Year
		if (sculpture.getYear()!=null)
			oldSculpture.setYear(sculpture.getYear());
	
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSculpture(@PathParam("id") String sculptureId) {
		Sculpture toberemoved=repository.getSculpture(sculptureId);
        if (toberemoved == null)
            throw new NotFoundException("The sculpture with id= "+ sculptureId +" was not found");
        else
            repository.deleteSculpture(sculptureId);

        return Response.noContent().build();
	}
	
}
