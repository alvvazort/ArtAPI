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

import aiss.api.resources.comparators.ComparatorNamePainting;
import aiss.api.resources.comparators.ComparatorNamePaintingReversed;
import aiss.model.Painting;
import aiss.model.repository.MapMuseumRepository;
import aiss.model.repository.MuseumRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/paintings")
public class PaintingResource {

	public static PaintingResource _instance=null;
	MuseumRepository repository;

	private PaintingResource(){
		repository=MapMuseumRepository.getInstance();
	}

	public static PaintingResource getInstance()
	{
		if(_instance==null)
			_instance=new PaintingResource();
		return _instance; 
	}

	@GET
	@Produces("application/json")
	public Collection<Painting> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("knownArtist") Boolean knownArtist,
			@QueryParam("limit") String limit, @QueryParam("offset") String offset, @QueryParam("page") String page){

		List<Painting> paintings = new ArrayList<Painting>();
		List<Painting> result = new ArrayList<Painting>();

		for(Painting p : repository.getAllPaintings()) {
			if(q==null || q.equals("")
					|| p.getName().toLowerCase().contains(q.toLowerCase()) 
					|| p.getStyle().toLowerCase().contains(q.toLowerCase()) 
					|| p.getArtist().toLowerCase().contains(q.toLowerCase())
					|| p.getTechnique().toLowerCase().contains(q.toLowerCase())
					|| p.getYear().toLowerCase().contains(q.toLowerCase())){
				if(knownArtist == null || 
						(knownArtist && p.getArtist() != "Desconocido") 
						|| (!knownArtist && p.getArtist() == "Desconocido")){
					paintings.add(p);
				}	
			}	
		}
		
		if(order != null) {
			if(order.equals("name")) {
				Collections.sort(paintings, new ComparatorNamePainting());
			}
			else if(order.equals("-name")) {
				Collections.sort(paintings, new ComparatorNamePaintingReversed());
			}
			else {
				throw new BadRequestException("The order parameter must be 'name' or '-name'.");
			}
		}

		Integer limite = 0;
		Integer pag = 1;

		if(offset!=null) {
			try {
				paintings = paintings.subList(Integer.valueOf(offset), paintings.size());
			}catch(Exception e) {
				throw new IllegalArgumentException("Value too big (" + Integer.valueOf(offset) + ")"); 
			}
		}

		if(limit != null && page != null) {
			pag = Integer.valueOf(page);
			try {
				limite = Integer.valueOf(limit);
				for(int i=(pag-1)*limite; i < pag*limite; i++)
					result.add(paintings.get(i));

			}catch(Exception e) {
				throw new IllegalArgumentException("There are less than " + pag*limite + " paintings matching those parameters");
			}
			return result;
		}

		else if(limit != null) {
			limite = Integer.valueOf(limit);
			if(limite>paintings.size()) {
				limite=paintings.size();
			}
			for(int i=0; i < limite; i++) 
				result.add(paintings.get(i));
			return result;
		}

		return paintings;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Painting get(@PathParam("id") String paintingId)
	{

		Painting painting = repository.getPainting(paintingId);

		if (painting == null) {
			throw new NotFoundException("The painting with id= "+ paintingId +" was not found");			
		}

		return painting;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPainting(@Context UriInfo uriInfo, Painting painting) {

		if (painting.getName() == null || "".equals(painting.getName()))
			throw new BadRequestException("The name of the painting must not be null.");

		if (painting.getArtist()==null)
			throw new BadRequestException("The painting artist must not be null.");

		repository.addPainting(painting);

		// Builds the response. Returns the song the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(painting.getPaintingId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(painting);			
		return resp.build();
	}


	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updatePainting(@PathParam("id") String paintingId, Painting painting) {

		if (painting.getName() == null || "".equals(painting.getName() ))
			throw new BadRequestException("The name of the painting must not be null.");

		if (painting.getArtist() == null)
			throw new BadRequestException("The painting artist must not be null.");

		Painting oldPainting = repository.getPainting(paintingId);
		if (oldPainting == null) {
			throw new NotFoundException("The painting with id= "+ paintingId +" was not found.");			
		}

		// Update Name
		if (painting.getName()!=null)
			oldPainting.setName(painting.getName());

		// Update Style
		if (painting.getStyle()!=null)
			oldPainting.setStyle(painting.getStyle());

		// Update Artist
		if (painting.getArtist()!=null)
			oldPainting.setArtist(painting.getArtist());

		// Update Year
		if (painting.getYear()!=null)
			oldPainting.setYear(painting.getYear());

		// Update Technique
		if (painting.getTechnique()!=null)
			oldPainting.setTechnique(painting.getTechnique());


		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removePainting(@PathParam("id") String paintingId) {
		Painting toberemoved=repository.getPainting(paintingId);
		if (toberemoved == null)
			throw new NotFoundException("The painting with id= "+ paintingId +" was not found");
		else
			repository.deletePainting(paintingId);

		return Response.noContent().build();
	}

}