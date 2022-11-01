package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.resources.comparators.ComparatorNameMuseum;
import aiss.api.resources.comparators.ComparatorNameMuseumReversed;
import aiss.model.Museum;
import aiss.model.Painting;
import aiss.model.Sculpture;
import aiss.model.repository.MapMuseumRepository;
import aiss.model.repository.MuseumRepository;

@Path("/museums")
public class MuseumResource{

	/* Singleton */
	private static MuseumResource _instance=null;
	MuseumRepository repository;

	private MuseumResource() {
		repository= MapMuseumRepository.getInstance();

	}

	public static MuseumResource getInstance()
	{
		if(_instance==null)
			_instance=new MuseumResource();
		return _instance;
	}


	@GET
	@Produces("application/json")
	public Collection<Museum> getAll(@QueryParam("order") String order,@QueryParam("isEmpty") Boolean isEmpty,
			@QueryParam("q") String q, @QueryParam("limit") String limit,@QueryParam("offset") String offset,
			@QueryParam("page") String page){

		List<Museum> museums = new ArrayList<Museum>();
		List<Museum> result = new ArrayList<Museum>();

		for(Museum m: repository.getAllMuseums()) {
			if(q==null || q.equals("")
					|| m.getName().toLowerCase().contains(q.toLowerCase()) 
					|| m.getCountry().toLowerCase().contains(q.toLowerCase()) 
					|| m.getCity().toLowerCase().contains(q.toLowerCase())
					|| m.getOpeningYear().toLowerCase().contains(q.toLowerCase())) {
				if(isEmpty == null || 
						(isEmpty && m.getPaintings() == null && m.getSculptures() == null) 
						|| (!isEmpty && ((m.getPaintings() != null) || (m.getSculptures() != null)))){
					museums.add(m);
				}
			}
		}

		if(order != null) {
			if(order.equals("name")) {
				Collections.sort(museums, new ComparatorNameMuseum());
			}
			else if(order.equals("-name")) {
				Collections.sort(museums, new ComparatorNameMuseumReversed());
			}
			else {
				throw new BadRequestException("The order parameter must be 'name' or '-name'.");
			}
		}
		
		Integer limite = 0;
		Integer pag = 1;
		
		
		if(offset!=null) {
			try {
				museums = museums.subList(Integer.valueOf(offset), museums.size());
			}catch(Exception e) {
				throw new IllegalArgumentException("Value too big (" + Integer.valueOf(offset) + ")"); 
			}
		}

		if(limit != null && page != null) {
			pag = Integer.valueOf(page);
			try {
				limite = Integer.valueOf(limit);
				for(int i=(pag-1)*limite; i < pag*limite; i++)
					result.add(museums.get(i));

			}catch(Exception e) {
				throw new IllegalArgumentException("There are less than " + pag*limite + " paintings matching those parameters");
			}
			return result;
		}

		else if(limit != null) {
			limite = Integer.valueOf(limit);
			if(limite>museums.size()) {
				limite=museums.size();
			}
			for(int i=0; i < limite; i++) 
				result.add(museums.get(i));
			return result;
		}
		
		return museums;
	}


	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Museum get(@PathParam("id") String id)
	{
		Museum list = repository.getMuseum(id);

		if (list == null) {
			throw new NotFoundException("The museum with id = "+ id +" was not found");			
		}

		return list;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addMuseum(@Context UriInfo uriInfo, Museum museum) {
		if (museum.getName() == null || "".equals(museum.getName()))
			throw new BadRequestException("The name of the museum must not be null");

		if (museum.getPaintings()!=null || museum.getSculptures()!=null)
			throw new BadRequestException("Fields 'paintings' and 'sculptures' must be null,"
					+ " in order to add/delete artworks to/from the museum, use the proper POST/DELETE methods");

		repository.addMuseum(museum);

		// Builds the response. Returns the Museum that has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(museum.getMuseumId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(museum);			
		return resp.build();
	}


	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateMuseum(@PathParam("id") String id, Museum museum) {
		Museum oldMuseum = repository.getMuseum(id);
		if (oldMuseum == null) {
			throw new NotFoundException("The museum with id= "+ id +" was not found");			
		}

		if (museum.getPaintings()!=null || museum.getSculptures()!=null)
			throw new BadRequestException("Fields 'paintings' and 'sculptures' must be null,"
					+ " in order to add/delete artworks to/from the museum, use the proper POST/DELETE methods");

		// Update name
		if (museum.getName()!=null)
			oldMuseum.setName(museum.getName());

		// Update Country
		if (museum.getCountry()!=null)
			oldMuseum.setCountry(museum.getCountry());

		// Update City
		if (museum.getCity()!=null)
			oldMuseum.setCity(museum.getCity());

		// Update OpeningYear
		if (museum.getOpeningYear()!=null)
			oldMuseum.setOpeningYear(museum.getOpeningYear()); 

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeMuseum(@PathParam("id") String id) {
		Museum toberemoved=repository.getMuseum(id);
		if (toberemoved == null)
			throw new NotFoundException("The museum with id= "+ id +" was not found");
		else
			repository.deleteMuseum(id);

		return Response.noContent().build();
	}


	@POST	
	@Path("/{museumId}/paintings/{paintingId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPainting(@Context UriInfo uriInfo,@PathParam("museumId") String museumId, @PathParam("paintingId") String paintingId)
	{				

		Museum museum = repository.getMuseum(museumId);
		Painting painting = repository.getPainting(paintingId);

		if (museum==null)
			throw new NotFoundException("The museum with id= " + museumId + " was not found");

		if (painting == null)
			throw new NotFoundException("The painting with id= " + paintingId + " was not found");

		if (museum.getPainting(paintingId)!=null)
			throw new BadRequestException("The painting is already included in the museum.");

		repository.addPainting(museumId, paintingId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(museumId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(museum);			
		return resp.build();
	}


	@DELETE
	@Path("/{museumId}/paintings/{paintingId}")
	public Response removePainting(@PathParam("museumId") String museumId, @PathParam("paintingId") String paintingId) {
		Museum museum = repository.getMuseum(museumId);
		Painting painting = repository.getPainting(paintingId);

		if (museum==null)
			throw new NotFoundException("The museum with id=" + museumId + " was not found");

		if (painting == null)
			throw new NotFoundException("The painting with id=" + paintingId + " was not found");

		repository.removePainting(museumId, paintingId);		

		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{museumId}/sculptures/{sculptureId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addSculpture(@Context UriInfo uriInfo,@PathParam("museumId") String museumId, @PathParam("sculptureId") String sculptureId)
	{				

		Museum museum = repository.getMuseum(museumId);
		Sculpture sculpture = repository.getSculpture(sculptureId);

		if (museum==null)
			throw new NotFoundException("The museum with id= " + museumId + " was not found");

		if (sculpture == null)
			throw new NotFoundException("The sculpture with id= " + sculptureId + " was not found");

		if (museum.getSculpture(sculptureId)!=null)
			throw new BadRequestException("The sculpture is already included in the museum.");

		repository.addSculpture(museumId, sculptureId);

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(museumId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(museum);			
		return resp.build();
	}


	@DELETE
	@Path("/{museumId}/sculptures/{sculptureId}")
	public Response removeSculpture(@PathParam("museumId") String museumId, @PathParam("sculptureId") String sculptureId) {
		Museum museum = repository.getMuseum(museumId);
		Sculpture sculpture = repository.getSculpture(sculptureId);

		if (museum==null)
			throw new NotFoundException("The museum with id=" + museumId + " was not found");

		if (sculpture == null)
			throw new NotFoundException("The sculpture with id=" + sculptureId + " was not found");

		repository.removeSculpture(museumId, sculptureId);		

		return Response.noContent().build();
	}
}