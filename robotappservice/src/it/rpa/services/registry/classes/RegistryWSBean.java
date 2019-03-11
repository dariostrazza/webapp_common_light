package it.rpa.services.registry.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.common.utilities.DataCBean;
import it.rpa.registry.exception.classes.EJBRegistryException;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.classes.CommonUtility;
import it.rpa.services.interceptor.classes.ServiceInterceptor;
import it.rpa.services.registry.bean.classes.EnablingCodeCBean;
import it.rpa.services.registry.bean.classes.ProcessRegistry;
import it.rpa.services.registry.interfaces.Registry;

@Interceptors(ServiceInterceptor.class)
@Path("registry")
@Stateless(name = "RegistryBean", mappedName = "ejb/RegistryBean")
public class RegistryWSBean {

	@EJB
	Registry registry;

	/**
	 * Default constructor.
	 */
	public RegistryWSBean() {

	}

	// DATA
	@GET
	@Path("/data")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getData() {
		String result = "";
		Collection<DataCBean> dataCBeans = null;
		Response response = null;

		try {
			dataCBeans = registry.getData();
			result = CommonUtility.printResultData(dataCBeans);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getData");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getData");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeData/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeData(@PathParam("code") String dataCode) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.removeData(dataCode);
			if (result) {
				response = CommonUtility.printResponse("Record eliminato correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile cancellare il record selezionato");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "removeData");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "removeData");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeData/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeDataOption(@PathParam("code") String dataCode) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateData")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateData(@FormParam("code") String code, @FormParam("description") String description,
			@FormParam("type") String type) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.insertData(code, description, type);
			if (result) {
				response = CommonUtility.printResponse("Record aggiornato correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile aggiornare il record");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateData");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateData");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	// EXCEPTION
	@GET
	@Path("/exception")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getException() {
		String result = "";
		Collection<KeyValueCBean> exceptionCBeans = null;
		Response response = null;

		try {
			exceptionCBeans = registry.getException();
			result = CommonUtility.printResultKeyValues(exceptionCBeans);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getException");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getException");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeException/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeException(@PathParam("code") String exceptionCode) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.removeException(exceptionCode);
			if (result) {
				response = CommonUtility.printResponse("Record eliminato correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile cancellare il record selezionato");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "removeException");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "removeException");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeException/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeExceptionOption(@PathParam("code") String exceptionCode) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateException")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateException(@FormParam("code") String code, @FormParam("description") String description) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.insertException(code, description);
			if (result) {
				response = CommonUtility.printResponse("Record aggiornato correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile aggiornare il record");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateException");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateException");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeApplication/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeApplicationOption(@PathParam("code") String dataCode) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	// PROCESS
	@Path("/registryProcess")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getRegistryProcess() {
		Collection<ProcessRegistry> processRegistry = null;
		Response response = null;
		String result = null;

		try {
			processRegistry = registry.getProcessesQueue();
			result = CommonUtility.printProcessesRegistry(processRegistry);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getRegistryProcess");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getRegistryProcess");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/registryProcessGovernance")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getRegistryProcessGovernance() {
		Collection<ProcessRegistry> processRegistry = null;
		Response response = null;
		String result = null;

		try {
			processRegistry = registry.getProcessesGovernance();
			result = CommonUtility.printProcessesGovernance(processRegistry);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getRegistryProcessGovernance");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getRegistryProcessGovernance");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeFieldValue/{valueId}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteFieldValueOption(@PathParam("valueId") long valueId) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@OPTIONS
	@Path("/removeMap/{code}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeMapOption(@PathParam("code") String dataCode) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@OPTIONS
	@Path("/removeProcessAddField/{id}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response removeProcessAdditionalFieldOption(@PathParam("id") String id) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateProcGov")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProcGov(@FormParam("dateIns") String dateIns, @FormParam("idProcessMain") String idProcMain,
			@FormParam("idProcess") String idProcess, @FormParam("name") String name,
			@FormParam("description") String description, @FormParam("businessOwner") String businessOwner,
			@FormParam("service") String service, @FormParam("category") String category,
			@FormParam("idExtProc") String idExtProc) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.updateProcGov(dateIns, idProcMain, idProcess, name, description, businessOwner, service,
					category, idExtProc);
			if (result) {
				response = CommonUtility.printResponse("Processo inserito correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il processo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProcGov");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProcGov");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@POST
	@Path("/updateProcGovernance")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProcGovernance(@FormParam("idProcessMain") String idProcMain,
			@FormParam("idProcess") String idProcess, @FormParam("name") String name,
			@FormParam("description") String description, @FormParam("businessOwner") String businessOwner,
			@FormParam("service") String service, @FormParam("category") String category) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.updateProcGovernance(idProcMain, idProcess, name, description, businessOwner, service,
					category);
			if (result) {
				response = CommonUtility.printResponse("Processo modificato correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile modificare il processo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProcGovernance");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProcGovernance");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/processOwners")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getProcessOwners(@QueryParam("processId") String processId) {
		Collection<String> processOwners = null;
		Response response = null;
		String result = null;

		try {
			processOwners = registry.getProcessOwners(processId);
			result = CommonUtility.printCollectionString(processOwners);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getProcessOwners");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getProcessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/processOwnersRemove/{user}/{processId}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteProcessOwners(@PathParam("user") String user, @PathParam("processId") long processId) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteProcessOwners(user, processId);
			if (result) {
				response = CommonUtility.printResponse("Owners cancellato", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile cancellare l'elemento");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteProcessOwners");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteProcessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/processOwnersRemove/{user}/{processId}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteProcessOwnersOption(@PathParam("user") String user, @PathParam("processId") long processId) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@Path("/businessOwners")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getBusinessOwners() {
		Collection<KeyValueCBean> businessOwners = null;
		Response response = null;
		String result = null;

		try {
			businessOwners = registry.getBusinessOwnersLicense();
			result = CommonUtility.printResultKeyValues(businessOwners);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getBusinessOwners");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getBusinessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeBusinessOwner/{businessOwner}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteBusinessOwners(@PathParam("businessOwner") String businessOwner) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteBusinessOwner(businessOwner);
			if (result) {
				response = CommonUtility.printResponse("Business Owner cancellato", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse(
						"Impossibile cancellare l'elemento, controllare se ci sono processi o profili associati a tale elemento");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteBusinessOwners");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteBusinessOwners");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeBusinessOwner/{businessOwner}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteBusinessOwnersOption(@PathParam("businessOwner") String businessOwner) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateBusinessOwner")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateBusinessOwner(@FormParam("businessOwner") String businessOwner,
			@FormParam("license") int license) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.updateBusinessOwner(businessOwner, license);
			if (result) {
				response = CommonUtility.printResponse("Business Owner inserito correttamente",
						CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il Business Owner");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateBusinessOwner");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateBusinessOwner");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/enablingCodes")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getEnablingCodes() {
		Collection<EnablingCodeCBean> enablingCode = null;
		Response response = null;
		String result = null;

		try {
			enablingCode = registry.getEnablingCodes();
			result = CommonUtility.printResultEnablingCodes(enablingCode);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getEnablingCodes");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getEnablingCodes");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/usersByCode")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getUsersByCode(@QueryParam("enablingCode") String enablingCode) {
		Collection<String> usersAM = null;
		Response response = null;
		String result = null;

		try {
			usersAM = registry.getUsersWithSubProfileByCode(enablingCode);
			result = CommonUtility.printCollectionString(usersAM);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getUsersByCode");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getUsersByCode");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@POST
	@Path("/updateProfileRD")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProfileRD(@FormParam("enablingCode") String enablingCode,
			@FormParam("descriptionCode") String descriptionCode, @FormParam("businessOwner") String businessOwner,
			@FormParam("profile") Profile profile, @FormParam("additionalUsersRD") String additionalUsersRD) {

		Response response = null;
		Boolean result = false;
		List<String> arrayUsersRD = new ArrayList<String>();

		try {
			if (additionalUsersRD.length() > 0) {
				arrayUsersRD = Arrays.asList(additionalUsersRD.split("\\s*,\\s*"));
			}
			result = registry.updateProfileRD(enablingCode, descriptionCode, businessOwner, profile, arrayUsersRD);
			if (result) {
				response = CommonUtility.printResponse("Codice abilitativo inserito correttamente",
						CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il codice abilitativo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProfileRD");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProfileRD");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeEnablingCode/{enablingCode}/{profile}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteEnablingCodeProfile(@PathParam("enablingCode") String enablingCode,
			@PathParam("profile") Profile profile) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteEnablingCodeProfile(enablingCode, profile);
			if (result) {
				response = CommonUtility.printResponse("Codice abilitativo cancellato", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse(
						"Impossibile cancellare l'elemento, controllare se ci sono informazioni associate a tale codice");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteEnablingCodeProfile");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteEnablingCodeProfile");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeEnablingCode/{enablingCode}/{profile}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteEnablingCodeProfileOption(@PathParam("enablingCode") String enablingCode,
			@PathParam("profile") Profile profile) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@DELETE
	@Path("/removeUsersByCode/{enablingCode}/{user}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteUserByCode(@PathParam("enablingCode") String enablingCode, @PathParam("user") String user) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteUserWithSubProfileByCode(enablingCode, user);
			if (result) {
				response = CommonUtility.printResponse("Utenza cancellata", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile cancellare l'utenza");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteUserByCode");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteUserByCode");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeUsersByCode/{enablingCode}/{user}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteUserByCodeOption(@PathParam("enablingCode") String enablingCode,
			@PathParam("user") String user) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateProfileAM")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProfileAM(@FormParam("enablingCode") String enablingCode,
			@FormParam("descriptionCode") String descriptionCode, @FormParam("businessOwner") String businessOwner,
			@FormParam("profile") Profile profile, @FormParam("additionalUsersAM") String additionalUsersAM) {

		Response response = null;
		Boolean result = false;
		List<String> arrayUsersAM = new ArrayList<String>();

		try {
			if (additionalUsersAM.length() > 0) {
				arrayUsersAM = Arrays.asList(additionalUsersAM.split("\\s*,\\s*"));
			}

			result = registry.updateProfileAM(enablingCode, descriptionCode, businessOwner, profile, arrayUsersAM);
			if (result) {
				response = CommonUtility.printResponse("Codice abilitativo inserito correttamente",
						CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il codice abilitativo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProfileAM");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProfileAM");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeEnableProcess/{enablingCode}/{processId}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteEnableProcess(@PathParam("enablingCode") String enablingCode,
			@PathParam("processId") long processId) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteEnableProcess(enablingCode, processId);
			if (result) {
				response = CommonUtility.printResponse("Processo disabilitato", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile disabilitare il processo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteEnableProcess");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteEnableProcess");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeEnableProcess/{enablingCode}/{processId}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteEnableProcessOption(@PathParam("enablingCode") String enablingCode,
			@PathParam("processId") long processId) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateProfileUPB")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProfileUPB(@FormParam("enablingCode") String enablingCode,
			@FormParam("descriptionCode") String descriptionCode, @FormParam("businessOwner") String businessOwner,
			@FormParam("profile") Profile profile, @FormParam("additionalProcess") String additionalProcess) {

		Response response = null;
		Boolean result = false;
		List<String> arrayEnableProcess = new ArrayList<String>();

		try {
			if (additionalProcess.length() > 0) {
				arrayEnableProcess = Arrays.asList(additionalProcess.split("\\s*,\\s*"));
			}

			result = registry.updateProfileUPB(enablingCode, descriptionCode, businessOwner, profile,
					arrayEnableProcess);
			if (result) {
				response = CommonUtility.printResponse("Codice abilitativo inserito correttamente",
						CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il codice abilitativo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProfileUPB");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProfileUPB");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@POST
	@Path("/updateProfileGOV")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateProfileGOV(@FormParam("enablingCode") String enablingCode,
			@FormParam("descriptionCode") String descriptionCode, @FormParam("businessOwner") String businessOwner,
			@FormParam("profile") Profile profile) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.updateProfileGOV(enablingCode, descriptionCode, businessOwner, profile);
			if (result) {
				response = CommonUtility.printResponse("Codice abilitativo inserito correttamente",
						CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse("Impossibile inserire il codice abilitativo");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateProfileGOV");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateProfileGOV");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@Path("/users")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response getUsers() {
		Collection<KeyValueCBean> users = null;
		Response response = null;
		String result = null;

		try {
			users = registry.getUsers();
			result = CommonUtility.printResultKeyValues(users);
			response = CommonUtility.printResponse(result, CommonMethods.RESPONSE_OK);
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "getUsers");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "getUsers");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@DELETE
	@Path("/removeUser/{user}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteUser(@PathParam("user") String user) {
		Response response = null;
		Boolean result = false;

		try {
			result = registry.deleteUser(user);
			if (result) {
				response = CommonUtility.printResponse("Utenza cancellata", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse(
						"Impossibile cancellare l'elemento, verificare se l'uetnte è configurato come owner di un processo nella relativa anagrafica");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "deleteUser");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "deleteUser");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

	@OPTIONS
	@Path("/removeUser/{user}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteUserOption(@PathParam("user") String user) {
		Response response = CommonUtility.printResponse("OK option remove", CommonMethods.RESPONSE_OK);
		return response;
	}

	@POST
	@Path("/updateUser")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ "application/x-www-form-urlencoded;charset=UTF-8" })
	public Response updateUser(@FormParam("user") String user, @FormParam("code") String enablingCode) {

		Response response = null;
		Boolean result = false;

		try {
			result = registry.updateUser(user, enablingCode);
			if (result) {
				response = CommonUtility.printResponse("Utente inserito correttamente", CommonMethods.RESPONSE_OK);
			} else {
				response = CommonUtility.printResponse(
						"Impossibile inserire l'utente, verificare che il codice abiliattivo inserito sia corretto");
			}
		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "updateUser");
			response = CommonUtility.printResponse(ejbRegistryException.getMessage());
		} catch (Exception exception) {
			ControllerLogger.fatal(exception.getMessage(), "updateUser");
			response = CommonUtility.printResponse(CommonMethods.ERROR_MESS);
		}
		return response;
	}

}