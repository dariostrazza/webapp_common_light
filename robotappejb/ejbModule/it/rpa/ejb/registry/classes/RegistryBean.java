package it.rpa.ejb.registry.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.common.utilities.DataCBean;
import it.rpa.common.utilities.ExcelCBean;
import it.rpa.dao.common.classes.CommonDAO;
import it.rpa.dao.common.classes.GenericDAO;
import it.rpa.dao.exception.classes.DAOException;
import it.rpa.dao.registry.classes.RegistryDAO;
import it.rpa.ejb.interceptor.classes.ControllerInterceptor;
import it.rpa.model.table.classes.RpAssProcessOwner;
import it.rpa.model.table.classes.RpAssProfCode;
import it.rpa.model.table.classes.RpAssProfCodePK;
import it.rpa.model.table.classes.RpBusinessOwner;
import it.rpa.model.table.classes.RpDataCatalog;
import it.rpa.model.table.classes.RpEnablingCode;
import it.rpa.model.table.classes.RpExceptionCatalog;
import it.rpa.model.table.classes.RpExtSystem;
import it.rpa.model.table.classes.RpOffice;
import it.rpa.model.table.classes.RpProcess;
import it.rpa.model.table.classes.RpProcessType;
import it.rpa.model.table.classes.RpQueue;
import it.rpa.model.table.classes.RpUser;
import it.rpa.model.view.classes.RpvAssProfCode;
import it.rpa.registry.exception.classes.EJBRegistryException;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.registry.bean.classes.EnablingCodeCBean;
import it.rpa.services.registry.bean.classes.ProcessRegistry;
import it.rpa.services.registry.interfaces.Registry;

/**
 * Session Bean implementation class RegistryBean
 */

@Interceptors(ControllerInterceptor.class)
@Stateless
@LocalBean
public class RegistryBean implements Registry {

	private static final long EXT_STYSTEM = 1;
	private static final long PROCESS_GOVERNANCE_TYPE = 1;

	@EJB
	RegistryDAO registryDAO;

	@EJB
	CommonDAO commonDAO;

	@EJB
	GenericDAO genericDAO;

	/**
	 * Default constructor.
	 */
	public RegistryBean() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<DataCBean> getData() throws EJBRegistryException {
		Collection<DataCBean> dataCBeans = new ArrayList<DataCBean>();
		Collection<RpDataCatalog> rpDataCatalogs = null;
		DataCBean dataCBean = null;

		try {
			rpDataCatalogs = (Collection<RpDataCatalog>) genericDAO.findAll(RpDataCatalog.class);
			for (RpDataCatalog rpDataCatalog : rpDataCatalogs) {
				dataCBean = new DataCBean();
				dataCBean.setCode(rpDataCatalog.getCode());
				dataCBean.setDescription(rpDataCatalog.getDescription());
				dataCBean.setType(rpDataCatalog.getType());
				dataCBeans.add(dataCBean);
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getData");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return dataCBeans;
	}

	@Override
	public boolean removeData(String dataCode) throws EJBRegistryException {
		Boolean result = false;

		try {
			result = registryDAO.deleteData(dataCode);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "removeData");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean insertData(String code, String description, String type) throws EJBRegistryException {
		Boolean result = false;
		RpDataCatalog rpDataCatalog = new RpDataCatalog();

		try {
			rpDataCatalog.setCode(code);
			rpDataCatalog.setDescription(description);
			rpDataCatalog.setType(type);
			result = registryDAO.persistData(rpDataCatalog);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "insertData");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getException() throws EJBRegistryException {
		Collection<KeyValueCBean> exceptionCBeans = new ArrayList<KeyValueCBean>();
		Collection<RpExceptionCatalog> rpExceptionCatalogs = null;
		KeyValueCBean exceptionCBean = null;

		try {
			rpExceptionCatalogs = (Collection<RpExceptionCatalog>) genericDAO.findAll(RpExceptionCatalog.class);
			for (RpExceptionCatalog rpExceptionCatalog : rpExceptionCatalogs) {
				exceptionCBean = new KeyValueCBean();
				exceptionCBean.setKey(rpExceptionCatalog.getCode());
				exceptionCBean.setValue(rpExceptionCatalog.getDescription());
				exceptionCBeans.add(exceptionCBean);
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getException");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return exceptionCBeans;
	}

	@Override
	public boolean removeException(String exceptionCode) throws EJBRegistryException {
		Boolean result = false;
		try {
			result = registryDAO.deleteException(exceptionCode);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "removeException");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean insertException(String code, String description) throws EJBRegistryException {
		Boolean result = false;
		RpExceptionCatalog rpExceptionCatalog = new RpExceptionCatalog();

		try {
			rpExceptionCatalog.setCode(code);
			rpExceptionCatalog.setDescription(description);
			result = registryDAO.persistException(rpExceptionCatalog);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "insertException");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean insertDataFromExcel(ExcelCBean eCbean) throws EJBRegistryException {

		Boolean result = true;
		RpDataCatalog rpDataCatalog = null;

		try {
			for (List<String> line : eCbean.getTable()) {
				rpDataCatalog = new RpDataCatalog();
				rpDataCatalog.setCode(line.get(0));
				rpDataCatalog.setDescription(line.get(1));
				rpDataCatalog.setType(line.get(2));
				result = result && registryDAO.persistData(rpDataCatalog);
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "insertData");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean insertExceptionFromExcel(ExcelCBean eCbean) throws EJBRegistryException {

		Boolean result = true;
		RpExceptionCatalog rpExceptionCatalog = null;

		try {
			for (List<String> line : eCbean.getTable()) {

				rpExceptionCatalog = new RpExceptionCatalog();
				rpExceptionCatalog.setCode(line.get(0));
				rpExceptionCatalog.setDescription(line.get(1));
				result = result && registryDAO.persistException(rpExceptionCatalog);

			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "insertData");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProcessRegistry> getProcessesQueue() throws EJBRegistryException {
		List<ProcessRegistry> processesRegistry = new ArrayList<ProcessRegistry>();
		Collection<RpProcess> rpProcesses = null;
		RpQueue rpQueue = null;
		ProcessRegistry processRegistry = null;

		try {
			rpProcesses = (Collection<RpProcess>) genericDAO.findAll(RpProcess.class);
			for (RpProcess processItem : rpProcesses) {
				processRegistry = new ProcessRegistry();
				processRegistry.setProcessId(processItem.getId());
				processRegistry.setProcessName(processItem.getName());
				processRegistry.setProcessDescription(processItem.getDescription());
				processRegistry.setBusinessOwner(processItem.getRpBusinessOwner().getId());
				processRegistry.setCategory(processItem.getCategoria());
				processRegistry.setService(processItem.getServizio());
				processRegistry.setProcessType(processItem.getRpProcessType().getDescription());
				processRegistry.setIdProcessMain(processItem.getIdProcessMain());
				processRegistry.setInsertDate(processItem.getInsertDate());
				processRegistry.setLastUpdateDate(processItem.getLastUpdateDate());

				// verifico se è un processo di governo
				if (PROCESS_GOVERNANCE_TYPE == processItem.getRpProcessType().getId()) {
					processRegistry.setProcessIdExtSys(processItem.getIdExtSys());
				} else {
					// processo di business, leggo i campi della coda
					processRegistry.setProcessIdExtSys(processItem.getIdExtSys());
					processRegistry.setProcessInputType(processItem.getRpProcessInputType().getId());
					processRegistry.setNumCol(processItem.getNumColumns());
					processRegistry.setKeyCol(processItem.getColumnKey());
					processRegistry.setUnmanaged(processItem.isUnmanaged());
					rpQueue = registryDAO.findQueueByProcessConsumer(processItem.getId());
					// se non trovo code si tratta di un processo Producer quindi non lo aggiungo
					if (null != rpQueue) {
						processRegistry.setQueueId(rpQueue.getId());
						processRegistry.setQueueName(rpQueue.getName());
						processRegistry.setQueueDescription(rpQueue.getDescription());
						processRegistry.setIdExtSysQueue(rpQueue.getIdExtSys());
						processRegistry.setKeyFieldQueue(rpQueue.getKeyField());
						processRegistry.setQueueProcessProducer(rpQueue.getRpProcessProducer().getId());

					} else {
						ControllerLogger.fatal(
								"Il processo di business: " + processItem.getName() + " non ha una coda associata",
								"getProcesses");
						throw new EJBRegistryException(
								"Il processo di business: " + processItem.getName() + " non ha una coda associata");
					}
				}

				processesRegistry.add(processRegistry);
				processRegistry = null;

			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcesses");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return processesRegistry;
	}

	@Override
	public Collection<ProcessRegistry> getProcessesGovernance() throws EJBRegistryException {
		List<ProcessRegistry> processesRegistry = new ArrayList<ProcessRegistry>();
		Collection<RpProcess> rpProcesses = null;
		ProcessRegistry processRegistry = null;

		try {
			rpProcesses = registryDAO.findProcessesBusiness();
			for (RpProcess processItem : rpProcesses) {
				processRegistry = new ProcessRegistry();
				processRegistry.setProcessId(processItem.getId());
				processRegistry.setProcessName(processItem.getName());
				processRegistry.setProcessDescription(processItem.getDescription());
				processRegistry.setBusinessOwner(processItem.getRpBusinessOwner().getId());
				processRegistry.setCategory(processItem.getCategoria());
				processRegistry.setService(processItem.getServizio());
				processRegistry.setIdProcessMain(processItem.getIdProcessMain());
				processRegistry.setInsertDate(processItem.getInsertDate());
				processRegistry.setLastUpdateDate(processItem.getLastUpdateDate());
				processesRegistry.add(processRegistry);
				processRegistry = null;
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcessesGovernance");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return processesRegistry;
	}

	@Override
	public boolean updateProcGov(String dateIns, String idProcMain, String idProcess, String name, String description,
			String businessOwner, String service, String category, String idExtProc) throws EJBRegistryException {
		boolean result = false;
		RpProcess rpProcess = null;
		RpProcessType rpProcessType = null;
		RpExtSystem rpExtSystem = null;
		RpBusinessOwner rpBusinessOwner = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonMethods.DATE_TIME_FORMAT);

		try {
			rpProcessType = new RpProcessType();
			rpProcessType.setId(PROCESS_GOVERNANCE_TYPE); // si tratta di un processo di governo

			rpExtSystem = new RpExtSystem();
			rpExtSystem.setId(EXT_STYSTEM);

			rpBusinessOwner = new RpBusinessOwner();
			rpBusinessOwner.setId(businessOwner);

			rpProcess = new RpProcess();
			if (idProcess.length() > 0) {
				rpProcess.setId(Long.parseLong(idProcess));
				rpProcess.setInsertDate(simpleDateFormat.parse(dateIns));
			} else {
				rpProcess.setInsertDate(new Date());
			}
			rpProcess.setName(name);
			rpProcess.setDescription(description);
			rpProcess.setRpBusinessOwner(rpBusinessOwner);
			rpProcess.setServizio(service);
			rpProcess.setCategoria(category);
			rpProcess.setRpProcessType(rpProcessType);
			rpProcess.setRpExtSystem(rpExtSystem);
			rpProcess.setIdExtSys(idExtProc);
			rpProcess.setIdProcessMain(Long.parseLong(idProcMain));
			rpProcess.setLastUpdateDate(new Date());
			result = registryDAO.updateProcess(rpProcess);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProcGov");
			throw new EJBRegistryException(daoException.getMessage());
		} catch (ParseException parseException) {
			ControllerLogger.fatal(parseException.getMessage(), "updateProcGov");
			throw new EJBRegistryException("Errore formato data non valido");
		}
		return result;
	}

	@Override
	public boolean updateProcGovernance(String idProcMain, String idProcess, String name, String description,
			String businessOwner, String service, String category) throws EJBRegistryException {
		boolean result = false;
		RpProcess rpProcess = null;
		RpBusinessOwner rpBusinessOwner = null;

		try {
			rpProcess = (RpProcess) genericDAO.findById(Long.parseLong(idProcess), RpProcess.class);
			if (null != rpProcess) {
				rpBusinessOwner = new RpBusinessOwner();
				rpBusinessOwner.setId(businessOwner);
				rpProcess.setName(name);
				rpProcess.setDescription(description);
				rpProcess.setRpBusinessOwner(rpBusinessOwner);
				rpProcess.setServizio(service);
				rpProcess.setCategoria(category);
				rpProcess.setIdProcessMain(Long.parseLong(idProcMain));
				rpProcess.setLastUpdateDate(new Date());
				result = registryDAO.updateProcess(rpProcess);
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProcGovernance");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getProcessOwners(String processId) throws EJBRegistryException {
		Collection<RpAssProcessOwner> rpAssProcessOwners = null;
		Collection<String> processOwners = new ArrayList<String>();

		try {
			rpAssProcessOwners = (Collection<RpAssProcessOwner>) genericDAO.findByParam("findOwnersByProcessId",
					RpAssProcessOwner.class, "processId", Long.parseLong(processId));
			for (RpAssProcessOwner rpAssProcessOwner : rpAssProcessOwners) {
				processOwners.add(rpAssProcessOwner.getId().getIdUser());
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcessOwners");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return processOwners;
	}

	@Override
	public Boolean deleteProcessOwners(String user, long processId) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteProcessOwners(user, processId);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteProcessOwners");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	// Business Owner
	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getBusinessOwnersLicense() throws EJBRegistryException {
		Collection<RpBusinessOwner> rpBusinessOwners = null;
		Collection<KeyValueCBean> keyValueCBeans = new ArrayList<KeyValueCBean>();
		KeyValueCBean keyValueCBean = null;

		try {
			rpBusinessOwners = (Collection<RpBusinessOwner>) genericDAO.findAll(RpBusinessOwner.class);
			for (RpBusinessOwner businessOwner : rpBusinessOwners) {
				keyValueCBean = new KeyValueCBean();
				keyValueCBean.setKey(businessOwner.getId());
				keyValueCBean.setValue(businessOwner.getLicense() + "");
				keyValueCBeans.add(keyValueCBean);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getBusinessOwnersLicense");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return keyValueCBeans;
	}

	@Override
	public Boolean deleteBusinessOwner(String businessOwner) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteBusinessOwner(businessOwner);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteBusinessOwner");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean updateBusinessOwner(String businessOwner, int license) throws EJBRegistryException {
		boolean result = false;
		RpBusinessOwner rpBusinessOwner = null;

		try {
			rpBusinessOwner = new RpBusinessOwner();
			rpBusinessOwner.setId(businessOwner);
			rpBusinessOwner.setLicense(license);
			result = registryDAO.updateBusinessOwner(rpBusinessOwner);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateBusinessOwner");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	// Codici Abilitativi
	@SuppressWarnings("unchecked")
	@Override
	public Collection<EnablingCodeCBean> getEnablingCodes() throws EJBRegistryException {
		Collection<RpvAssProfCode> rpvAssProfCodes = null;
		Collection<EnablingCodeCBean> enablingCodeCBeans = new ArrayList<EnablingCodeCBean>();
		EnablingCodeCBean enablingCodeCBean = null;

		try {
			rpvAssProfCodes = (Collection<RpvAssProfCode>) genericDAO.findAll(RpvAssProfCode.class);
			for (RpvAssProfCode rpvAssProfCode : rpvAssProfCodes) {
				enablingCodeCBean = new EnablingCodeCBean();
				enablingCodeCBean.setCode(rpvAssProfCode.getId().getIdCode());
				enablingCodeCBean.setCodeDescription(rpvAssProfCode.getCodeDescription());
				enablingCodeCBean.setProfile(rpvAssProfCode.getId().getIdProf());
				enablingCodeCBean.setProfileDescription(rpvAssProfCode.getProfileDescription());
				enablingCodeCBean.setBusinessOwner(rpvAssProfCode.getBusinessOwner());
				enablingCodeCBeans.add(enablingCodeCBean);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getEnablingCodes");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return enablingCodeCBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getUsersWithSubProfileByCode(String enablingCode) throws EJBRegistryException {
		Collection<RpUser> rpUsers = null;
		Collection<String> usersAM = new ArrayList<String>();

		try {
			rpUsers = (Collection<RpUser>) genericDAO.findByParam("findUsersSubProfileByCode", RpUser.class,
					"enablingCode", enablingCode);
			for (RpUser user : rpUsers) {
				usersAM.add(user.getId());
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getUsersWithSubProfileByCode");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return usersAM;
	}

	@Override
	public boolean updateProfileRD(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayUsersRD) throws EJBRegistryException {
		boolean result = false;
		RpEnablingCode rpEnablingCode = new RpEnablingCode();
		RpAssProfCodePK rpAssProfCodePK = new RpAssProfCodePK();
		RpAssProfCode rpAssProfCode = new RpAssProfCode();

		try {
			rpEnablingCode.setId(enablingCode);
			rpEnablingCode.setDescription(descriptionCode);
			rpEnablingCode.setBusinessOwner(businessOwner);
			rpAssProfCodePK.setIdCode(enablingCode);
			rpAssProfCodePK.setIdProf(profile.toString());
			rpAssProfCode.setId(rpAssProfCodePK);
			result = registryDAO.updateEnablingCodeProfile(rpEnablingCode, rpAssProfCode);
			if (result && arrayUsersRD.size() > 0) {
				result = registryDAO.insertUsersWithSubProfile(rpEnablingCode, arrayUsersRD);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProfileRD");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public Boolean deleteEnablingCodeProfile(String enablingCode, Profile profile) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteCodeProfile(enablingCode, profile);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteEnablingCodeProfile");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public Boolean deleteUserWithSubProfileByCode(String enablingCode, String user) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteUserWithSubProfileByCode(enablingCode, user);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteUserWithSubProfileByCode");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public Boolean deleteEnableProcess(String enablingCode, long processId) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteEnableProcess(enablingCode, processId);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteEnableProcess");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean updateProfileAM(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayUsersAM) throws EJBRegistryException {
		boolean result = false;
		RpEnablingCode rpEnablingCode = new RpEnablingCode();
		RpAssProfCodePK rpAssProfCodePK = new RpAssProfCodePK();
		RpAssProfCode rpAssProfCode = new RpAssProfCode();

		try {
			rpEnablingCode.setId(enablingCode);
			rpEnablingCode.setDescription(descriptionCode);
			rpEnablingCode.setBusinessOwner(businessOwner);
			rpAssProfCodePK.setIdCode(enablingCode);
			rpAssProfCodePK.setIdProf(profile.toString());
			rpAssProfCode.setId(rpAssProfCodePK);
			result = registryDAO.updateEnablingCodeProfile(rpEnablingCode, rpAssProfCode);
			if (result && arrayUsersAM.size() > 0) {
				result = registryDAO.insertUsersWithSubProfile(rpEnablingCode, arrayUsersAM);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProfileAM");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean updateProfileUPB(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayEnableProcess) throws EJBRegistryException {
		boolean result = false;
		RpEnablingCode rpEnablingCode = new RpEnablingCode();
		RpAssProfCodePK rpAssProfCodePK = new RpAssProfCodePK();
		RpAssProfCode rpAssProfCode = new RpAssProfCode();

		try {
			rpEnablingCode.setId(enablingCode);
			rpEnablingCode.setDescription(descriptionCode);
			rpEnablingCode.setBusinessOwner(businessOwner);
			rpAssProfCodePK.setIdCode(enablingCode);
			rpAssProfCodePK.setIdProf(profile.toString());
			rpAssProfCode.setId(rpAssProfCodePK);
			result = registryDAO.updateEnablingCodeProfile(rpEnablingCode, rpAssProfCode);
			if (result && arrayEnableProcess.size() > 0) {
				result = registryDAO.insertEnableProcess(enablingCode, arrayEnableProcess);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProfileUPB");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean updateProfileGOV(String enablingCode, String descriptionCode, String businessOwner, Profile profile)
			throws EJBRegistryException {
		boolean result = false;
		RpEnablingCode rpEnablingCode = new RpEnablingCode();
		RpAssProfCodePK rpAssProfCodePK = new RpAssProfCodePK();
		RpAssProfCode rpAssProfCode = new RpAssProfCode();

		try {
			rpEnablingCode.setId(enablingCode);
			rpEnablingCode.setDescription(descriptionCode);
			rpEnablingCode.setBusinessOwner(businessOwner);
			rpAssProfCodePK.setIdCode(enablingCode);
			rpAssProfCodePK.setIdProf(profile.toString());
			rpAssProfCode.setId(rpAssProfCodePK);
			result = registryDAO.updateEnablingCodeProfile(rpEnablingCode, rpAssProfCode);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateProfileGOV");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	// Utenti
	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getUsers() throws EJBRegistryException {
		Collection<RpUser> rpUsers = null;
		Collection<KeyValueCBean> keyValueCBeans = new ArrayList<KeyValueCBean>();
		KeyValueCBean keyValueCBean = null;

		try {
			rpUsers = (Collection<RpUser>) genericDAO.findAll(RpUser.class);
			for (RpUser user : rpUsers) {
				keyValueCBean = new KeyValueCBean();
				keyValueCBean.setKey(user.getId());
				if (null != user.getRpEnablingCode()) {
					keyValueCBean.setValue(user.getRpEnablingCode().getId());
				} else {
					keyValueCBean.setValue("");
				}
				keyValueCBeans.add(keyValueCBean);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getUsers");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return keyValueCBeans;
	}

	@Override
	public Boolean deleteUser(String user) throws EJBRegistryException {

		Boolean result = false;

		try {
			result = registryDAO.deleteUser(user);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "deleteUser");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public boolean updateUser(String user, String enablingCode) throws EJBRegistryException {
		boolean result = false;
		RpUser rpUser = null;
		RpOffice rpOffice = null;
		RpEnablingCode rpEnablingCode = null;

		try {
			rpEnablingCode = (RpEnablingCode) genericDAO.findById(enablingCode, RpEnablingCode.class);
			if (null != rpEnablingCode) {
				rpUser = new RpUser();
				rpOffice = new RpOffice();

				rpOffice.setId("1");
				rpUser.setId(user);
				rpUser.setRpEnablingCode(rpEnablingCode);
				rpUser.setRpOffice(rpOffice);
				rpUser.setSubProfile(false);
				result = registryDAO.updateUser(rpUser);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "updateUser");
			throw new EJBRegistryException(daoException.getMessage());
		}
		return result;
	}

}
