package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import aiss.api.resources.MuseumResource;
import aiss.api.resources.PaintingResource;
import aiss.api.resources.SculptureResource;


public class ArtApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public ArtApplication() {

		singletons.add(MuseumResource.getInstance());
		singletons.add(PaintingResource.getInstance());
		singletons.add(SculptureResource.getInstance());

	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
