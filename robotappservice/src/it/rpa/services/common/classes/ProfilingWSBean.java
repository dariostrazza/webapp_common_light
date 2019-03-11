package it.rpa.services.common.classes;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.interfaces.Profiling;
import it.rpa.services.interceptor.classes.ServiceInterceptor;

@Interceptors(ServiceInterceptor.class)
@Path("Profiling")
@Stateless(name = "ProfilingBean", mappedName = "ejb/ProfilingBean")
// @LocalBean
public class ProfilingWSBean {

	@EJB
	Profiling profiling;

	/**
	 * Default constructor.
	 */
	public ProfilingWSBean() {

	}

	@Path("/menu")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getMenuContents(@QueryParam("enablingCode") String enablingCode, @QueryParam("user") String user,
			@QueryParam("profile") Profile profile) {
		Collection<KeyValueCBean> menuContents = null;
		Response response = null;
		String result = null;

		try {
			menuContents = profiling.getMenuContents(enablingCode, user, profile);
			result = CommonUtility.printResultKeyValues(menuContents);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getMenuContents");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getMenuContents");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/functionalities")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getFunctionalities(@QueryParam("enablingCode") String enablingCode, @QueryParam("user") String user,
			@QueryParam("profile") Profile profile) {
		Collection<String> functionalities = null;
		Response response = null;
		String result = null;

		try {
			functionalities = profiling.getFunctionalities(enablingCode, user, profile);
			result = CommonUtility.printCollectionString(functionalities);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getFunctionalities");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getFunctionalities");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

}