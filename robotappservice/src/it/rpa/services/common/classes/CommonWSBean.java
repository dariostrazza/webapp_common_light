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
import it.rpa.services.common.bean.classes.ProcessCBean;
import it.rpa.services.common.interfaces.Common;
import it.rpa.services.interceptor.classes.ServiceInterceptor;

@Interceptors(ServiceInterceptor.class)
@Path("common")
@Stateless(name = "CommonBean", mappedName = "ejb/CommonBean")
public class CommonWSBean {

	@EJB
	Common common;

	/**
	 * Default constructor.
	 */
	public CommonWSBean() {

	}

	@GET
	@Path("/keyFieldsQueue")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getKeyFiledsQueue(@QueryParam("enablingCode") String enablingCode,
			@QueryParam("profile") Profile profile, @QueryParam("category") String category) {
		Collection<String> keyFields = null;
		String result = "";
		Response response = null;

		try {
			keyFields = common.getKeyFieldsQueue(enablingCode, profile, category);
			result = CommonUtility.printCollectionString(keyFields);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getKeyFiledsQueue");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getKeyFiledsQueue");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@GET
	@Path("/businessOwner")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getBusinessOwners(@QueryParam("enablingCode") String enablingCode,
			@QueryParam("profile") Profile profile) {
		Collection<String> distinctBusinessOwners = null;
		String result = "";
		Response response = null;

		try {
			distinctBusinessOwners = common.getDistinctBusinessOwners(enablingCode, profile);
			result = CommonUtility.printResultOptionList(distinctBusinessOwners);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getBusinessOwners");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getBusinessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@GET
	@Path("/service")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getServices(@QueryParam("enablingCode") String enablingCode, @QueryParam("profile") Profile profile,
			@QueryParam("businessOwner") String businessOwner) {
		Collection<String> distinctServices = null;
		String result = "";
		Response response = null;

		try {
			distinctServices = common.getDistinctServices(enablingCode, profile, businessOwner);
			result = CommonUtility.printResultOptionList(distinctServices);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getServices");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getServices");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@GET
	@Path("/category")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getCategories(@QueryParam("enablingCode") String enablingCode,
			@QueryParam("profile") Profile profile, @QueryParam("businessOwner") String businessOwner,
			@QueryParam("service") String service) {
		Collection<String> distinctCategories = null;
		String result = "";
		Response response = null;

		try {
			distinctCategories = common.getDistinctCategories(enablingCode, profile, businessOwner, service);
			result = CommonUtility.printResultOptionList(distinctCategories);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbcommonException) {
			ControllerLogger.fatal(ejbcommonException.getMessage(), "getCategories");
			response = CommonUtility.printResponse(ejbcommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getCategories");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@GET
	@Path("/scheduleModalities")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getScheduleModalities(@QueryParam("userId") String userId) {
		Collection<KeyValueCBean> keyValueCBeans = null;
		String result = "";
		Response response = null;

		try {
			keyValueCBeans = common.getScheduleModalities();
			result = CommonUtility.printResultKeyValues(keyValueCBeans);
			response = CommonUtility.printResponse(result, 200);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getScheduleModalities");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getScheduleModalities");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@GET
	@Path("/selectBusinessOwners")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getSelectBusinessOwners() {
		String result = "";
		Collection<String> businessOwners = null;
		Response response = null;

		try {
			businessOwners = common.getBusinessOwnersList();
			result = CommonUtility.printCollectionString(businessOwners);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getSelectBusinessOwners");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getSelectBusinessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/profiles")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getProfiles() {
		Collection<KeyValueCBean> keyValueCBeans = null;
		Response response = null;
		String result = null;

		try {
			keyValueCBeans = common.getProfiles();
			result = CommonUtility.printResultKeyValues(keyValueCBeans);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getProfiles");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getProfiles");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}
	
	@GET
	@Path("/processName")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getProcessNames(@QueryParam("enablingCode") String enablingCode,@QueryParam("profile") Profile profile,@QueryParam("category") String category,@QueryParam("businessOwner") String businessOwner) {
		Collection<ProcessCBean> processes = null;
		String result = "";
		Response response = null;

		try {
			if(profile == Profile.UPB){
				processes = common.getProcessNames(enablingCode,profile,category,businessOwner);
			} else{
				processes = common.getProcessesList(enablingCode,profile,category,businessOwner);
			} 
			result = CommonUtility.printResultProcesses(processes);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "getProcessNames");
			response = CommonUtility.printResponse(ejbCommonException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getProcessNames");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

}