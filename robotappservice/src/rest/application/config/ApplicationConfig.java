package rest.application.config;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return getRestClasses();
	}

	// Auto-generated from RESTful web service wizard
	private Set<Class<?>> getRestClasses() {
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();

		resources.add(it.rpa.services.common.classes.ProfilingWSBean.class);
		resources.add(it.rpa.services.common.classes.CommonWSBean.class);
		resources.add(it.rpa.services.registry.classes.RegistryWSBean.class);
		return resources;
	}
}