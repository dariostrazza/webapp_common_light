package it.rpa.dao.registry.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.dao.exception.classes.DAOException;
import it.rpa.ejb.interceptor.classes.ControllerInterceptor;
import it.rpa.model.table.classes.RpAssProcessCode;
import it.rpa.model.table.classes.RpAssProcessCodePK;
import it.rpa.model.table.classes.RpAssProcessOwner;
import it.rpa.model.table.classes.RpAssProcessUserPK;
import it.rpa.model.table.classes.RpAssProfCode;
import it.rpa.model.table.classes.RpAssProfCodePK;
import it.rpa.model.table.classes.RpBusinessOwner;
import it.rpa.model.table.classes.RpDataCatalog;
import it.rpa.model.table.classes.RpEnablingCode;
import it.rpa.model.table.classes.RpExceptionCatalog;
import it.rpa.model.table.classes.RpExtSystem;
import it.rpa.model.table.classes.RpOffice;
import it.rpa.model.table.classes.RpProcess;
import it.rpa.model.table.classes.RpProcessInputType;
import it.rpa.model.table.classes.RpProcessType;
import it.rpa.model.table.classes.RpQueue;
import it.rpa.model.table.classes.RpUser;

@Interceptors(ControllerInterceptor.class)
@Stateless
public class RegistryDAO {

	@PersistenceContext(unitName = "robotappjpa")
	EntityManager emRpaPortal;
	
	private static final long EXT_STYSTEM = 1; 
	private static final long PROCESS_BUSINESS_TYPE = 2;

	public RegistryDAO() {
	}

	public boolean deleteData(String dataCode)
			throws DAOException {
		RpDataCatalog rpDataCatalog = null;
		boolean result = false;

		try {
			rpDataCatalog = emRpaPortal.find(RpDataCatalog.class, dataCode);
			if (null != rpDataCatalog) {
				emRpaPortal.remove(rpDataCatalog);
				result = true;
			} else {
				result = false;
			}
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteData");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean deleteException(String exceptionCode)
			throws DAOException {
		RpExceptionCatalog rpExceptionCatalog = null;
		boolean result = false;

		try {
			rpExceptionCatalog = emRpaPortal.find(RpExceptionCatalog.class, exceptionCode);
			if (null != rpExceptionCatalog) {
				emRpaPortal.remove(rpExceptionCatalog);
				result = true;
			} else {
				result = false;
			}
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteException");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean persistData(RpDataCatalog rpDataCatalog)
			throws DAOException {
		boolean result = false;

		try {
			emRpaPortal.merge(rpDataCatalog);
			emRpaPortal.flush();
			result = true; 
		} catch (PersistenceException persistenceException) { 
			result = false;
			ControllerLogger.fatal(persistenceException.getMessage(), "persistData");
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "persistData");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean persistException(RpExceptionCatalog rpExceptionCatalog)
			throws DAOException {
		boolean result = false;

		try {
			emRpaPortal.merge(rpExceptionCatalog);
			emRpaPortal.flush();
			result = true; 
		} catch (PersistenceException persistenceException) { 
			result = false;
			ControllerLogger.fatal(persistenceException.getMessage(), "persistException");
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "persistException");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}
	
	public Collection<RpProcess> findProcessesBusiness() throws DAOException {
		Collection<RpProcess> rpProcesses = null;
		TypedQuery<RpProcess> query = null;

		try {
			query = emRpaPortal.createNamedQuery("RpProcess.findAllProcessBusiness",
					RpProcess.class);
			rpProcesses = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessesBusiness");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpProcesses;
	}

	public RpQueue findQueueByProcessConsumer(long processId) throws DAOException {
		RpQueue rpQueue = null;
		TypedQuery<RpQueue> query = null;
		Collection<RpQueue> rpQueues = null;

		try{
			query = emRpaPortal.createNamedQuery("RpQueue.findIdByProcessCons", RpQueue.class);
			query = query.setParameter("processConsumerId", processId);
			rpQueues = query.getResultList();
			if(rpQueues.size() > 1){
				ControllerLogger.fatal("Unexspected size of rpQueue: "+rpQueues.size(), "findQueueByProcessConsumer");
				throw new DAOException("Trovati "+rpQueues.size()+" Queue associate a questo Processo:"+ processId);	
			}
			else if(rpQueues.size() == 0){
				rpQueue = null;
			}
			else{
				rpQueue = rpQueues.iterator().next();
			}
		}catch(RuntimeException runtimeException){
			ControllerLogger.fatal(runtimeException.getMessage(), "findQueueByProcessConsumer");
			throw new DAOException(CommonMethods.ERROR_MESS);			
		}
		return rpQueue;
	}



	public RpProcess insertProcessWithFile(int numCol,byte[] buffer, String name,
			String description, String columnKey, String businessOwner, String service, 
			String category, String idExtSysQueue, String nameQueue, String descriptionQueue,
			String keyField, long processInputType, String idProcess, String idExtProc, String idQueue, long queueProducer,boolean unmanaged, long idProcessMain, String dateIns)
					throws DAOException {
		RpProcess rpProcess = null;
		RpProcess rpProcessLoaded = null;
		RpQueue rpQueue = null;
		RpProcessType rpProcessType = null;
		RpExtSystem rpExtSystem = null;
		RpBusinessOwner rpBusinessOwner = null;
		RpProcessInputType rpProcessInputType = null;
		RpProcess rpProcessProducer = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			rpProcessType = new RpProcessType();
			rpProcessType.setId(PROCESS_BUSINESS_TYPE);

			rpExtSystem = new RpExtSystem();
			rpExtSystem.setId(EXT_STYSTEM);

			rpBusinessOwner = new RpBusinessOwner();
			rpBusinessOwner.setId(businessOwner);

			rpProcessInputType = new RpProcessInputType();
			rpProcessInputType.setId(processInputType);

			rpProcess = new RpProcess();
			if(idProcess.length() > 0) {
				rpProcess.setId(Long.parseLong(idProcess));
				rpProcess.setInsertDate(simpleDateFormat.parse(dateIns));
			} else {
				rpProcess.setInsertDate(new Date());
			}
			
			rpProcess.setNumColumns(numCol);
			rpProcess.setName(name);
			rpProcess.setDescription(description);
			rpProcess.setColumnKey(columnKey);
			rpProcess.setRpBusinessOwner(rpBusinessOwner);
			rpProcess.setServizio(service);
			rpProcess.setCategoria(category);
			rpProcess.setProcessTemplate(buffer);
			rpProcess.setRpProcessType(rpProcessType);
			rpProcess.setRpExtSystem(rpExtSystem);
			rpProcess.setRpProcessInputType(rpProcessInputType);
			rpProcess.setIdExtSys(idExtProc);
			rpProcess.setIdProcessMain(idProcessMain);
			rpProcess.setLastUpdateDate(new Date());
			rpProcess.setUnmanaged(unmanaged);
			rpProcessLoaded = emRpaPortal.merge(rpProcess);
			emRpaPortal.flush();

			rpQueue = new RpQueue();
			if(idQueue.length() > 0) {
				rpQueue.setId(Long.parseLong(idQueue));
			}
			rpProcessProducer = new RpProcess();
			rpProcessProducer.setId(queueProducer);
			rpQueue.setRpProcessConsumer(rpProcessLoaded);
			rpQueue.setRpProcessProducer(rpProcessProducer);
			rpQueue.setIdExtSys(idExtSysQueue);
			rpQueue.setName(nameQueue);
			rpQueue.setDescription(descriptionQueue);
			rpQueue.setKeyField(keyField);
			emRpaPortal.merge(rpQueue);

			emRpaPortal.flush();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "insertProcessWithFile");
			throw new DAOException(CommonMethods.ERROR_MESS);
		} catch (ParseException parseException) {
			ControllerLogger.fatal(parseException.getMessage(), "insertProcessWithFile");
			throw new DAOException("Errore formato data non valido");
		}
		return rpProcessLoaded;
	} 
	
	public RpProcess insertProcessWithoutFile(String name,String description, String businessOwner,
			String service, String category, String idExtSysQueue, String nameQueue,
			String descriptionQueue, String keyField, long processInputType, String idProcess, String idExtProc,
			String idQueue, long queueProducer,boolean unmanaged, long idProcessMain, String dateIns)
					throws DAOException {
		RpProcess rpProcess = null;
		RpProcess rpProcessLoaded = null;
		RpQueue rpQueue = null;
		RpProcessType rpProcessType = null;
		RpExtSystem rpExtSystem = null;
		RpBusinessOwner rpBusinessOwner = null;
		RpProcessInputType rpProcessInputType = null;
		RpProcess rpProcessProducer = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			rpProcessType = new RpProcessType();
			rpProcessType.setId(PROCESS_BUSINESS_TYPE);

			rpExtSystem = new RpExtSystem();
			rpExtSystem.setId(EXT_STYSTEM);

			rpBusinessOwner = new RpBusinessOwner();
			rpBusinessOwner.setId(businessOwner);

			rpProcessInputType = new RpProcessInputType();
			rpProcessInputType.setId(processInputType);

			rpProcess = new RpProcess();
			if(idProcess.length() > 0) {
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
			rpProcess.setRpProcessInputType(rpProcessInputType);
			rpProcess.setIdExtSys(idExtProc);
			rpProcess.setIdProcessMain(idProcessMain);
			rpProcess.setLastUpdateDate(new Date());
			rpProcess.setUnmanaged(unmanaged);
			rpProcessLoaded = emRpaPortal.merge(rpProcess);
			emRpaPortal.flush();

			rpQueue = new RpQueue();
			if(idQueue.length() > 0) {
				rpQueue.setId(Long.parseLong(idQueue));
			}
			rpProcessProducer = new RpProcess();
			rpProcessProducer.setId(queueProducer);
			rpQueue.setRpProcessConsumer(rpProcessLoaded);
			rpQueue.setRpProcessProducer(rpProcessProducer);
			rpQueue.setIdExtSys(idExtSysQueue);
			rpQueue.setName(nameQueue);
			rpQueue.setDescription(descriptionQueue);
			rpQueue.setKeyField(keyField);
			emRpaPortal.merge(rpQueue);

			emRpaPortal.flush();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "insertProcessWithoutFile");
			throw new DAOException(CommonMethods.ERROR_MESS);
		} catch (ParseException parseException) {
			ControllerLogger.fatal(parseException.getMessage(), "insertProcessWithoutFile");
			throw new DAOException("Errore formato data non valido");
		}
		return rpProcessLoaded;
	} 
	


	
	public boolean updateProcess(RpProcess rpProcess) throws DAOException {

		boolean result = false;
		try{
			emRpaPortal.merge(rpProcess);
			emRpaPortal.flush();
			result = true;
		} catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "updatePrococess");
			result = false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "updatePrococess");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean insertProcessOwners(long processId, List<String> owners) throws DAOException {

		boolean result = false;
		RpAssProcessOwner rpAssProcessOwner = null;
		RpAssProcessUserPK rpAssProcessUserPK = null;
		RpUser rpUser = null;
		RpOffice rpOffice = new RpOffice();
		rpOffice.setId("1");

		try {
			for(String owner: owners){
				//inserisco l'utente
				rpUser = new RpUser();
				rpUser.setRpOffice(rpOffice);
				rpUser.setId(owner);
				rpUser.setSubProfile(false);
				emRpaPortal.merge(rpUser);
				emRpaPortal.flush();

				//inserisco l'owner
				rpAssProcessOwner = new RpAssProcessOwner();
				rpAssProcessUserPK = new RpAssProcessUserPK();
				rpAssProcessUserPK.setIdProcess(processId);
				rpAssProcessUserPK.setIdUser(owner);
				rpAssProcessOwner.setId(rpAssProcessUserPK);
				emRpaPortal.merge(rpAssProcessOwner);
				emRpaPortal.flush();
			}
			result = true; 
		} catch (PersistenceException persistenceException) { 
			result = false;
			ControllerLogger.fatal(persistenceException.getMessage(), "insertProcessOwners");
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "insertProcessOwners");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean deleteProcessOwners(String user,long processId) throws DAOException {
		RpAssProcessOwner rpAssProcessOwner = null;
		RpAssProcessUserPK rpAssProcessOwnerPK = new RpAssProcessUserPK();
		boolean result = false;

		try {
			rpAssProcessOwnerPK.setIdProcess(processId);
			rpAssProcessOwnerPK.setIdUser(user);
			rpAssProcessOwner = emRpaPortal.find(RpAssProcessOwner.class, rpAssProcessOwnerPK);
			if (null != rpAssProcessOwner) {
				emRpaPortal.remove(rpAssProcessOwner);
				result = true;
			} else {
				result = false;
			}
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteProcessOwners");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}
		
	
	
	public boolean deleteBusinessOwner(String businessOwner) throws DAOException {
		RpBusinessOwner rpBusinessOwner = null;
		boolean result = false;

		try {
			rpBusinessOwner = emRpaPortal.find(RpBusinessOwner.class, businessOwner);
			if (null != rpBusinessOwner) {
				emRpaPortal.remove(rpBusinessOwner);
				emRpaPortal.flush();
				result = true;
			} else {
				result = false;
			}

		}  catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "deleteBusinessOwner");
			return false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteBusinessOwner");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean updateBusinessOwner(RpBusinessOwner rpBusinessOwner) throws DAOException {

		boolean result = false;
		try{
			emRpaPortal.merge(rpBusinessOwner);
			emRpaPortal.flush();
			result = true;
		} catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "updateBusinessOwner");
			result = false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "updateBusinessOwner");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean updateEnablingCodeProfile(RpEnablingCode rpEnablingCode,RpAssProfCode rpAssProfCode) throws DAOException {

		boolean result = false;
		try{
			emRpaPortal.merge(rpEnablingCode);
			emRpaPortal.flush();
			emRpaPortal.merge(rpAssProfCode);
			emRpaPortal.flush();
			result = true;
		} catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "updateEnablingCodeProfile");
			result = false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "updateEnablingCodeProfile");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean deleteCodeProfile(String enablingCode, Profile profile) throws DAOException {
		RpEnablingCode rpEnablingCode = null;
		RpAssProfCode rpAssProfCode = null;
		RpAssProfCodePK rpAssProfCodePK = new RpAssProfCodePK();
		boolean result = false;
		
		try {
			rpAssProfCodePK.setIdCode(enablingCode);
			rpAssProfCodePK.setIdProf(profile.toString());
			rpAssProfCode = emRpaPortal.find(RpAssProfCode.class, rpAssProfCodePK);
			rpEnablingCode = emRpaPortal.find(RpEnablingCode.class, enablingCode);
			if (null != rpAssProfCode && null != rpEnablingCode ) {
				emRpaPortal.remove(rpAssProfCode);
				emRpaPortal.flush();
				emRpaPortal.remove(rpEnablingCode);
				emRpaPortal.flush();
				result = true;
			} else {
				result = false;
			}
		}  catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "deleteCodeProfile");
			return false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteCodeProfile");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean deleteUserWithSubProfileByCode(String enablingCode, String user) throws DAOException {
		RpUser rpUser = null;
		TypedQuery<RpUser> query = null;
		boolean result = false;

		try {
			query = emRpaPortal.createNamedQuery("RpUser.findUserSubProfileByCode",
					RpUser.class);
			query = query.setParameter("enablingCode", enablingCode);
			query = query.setParameter("user", user);
			rpUser = query.getSingleResult();
			if (null != rpUser ) {
				rpUser.setSubProfile(false);
				emRpaPortal.merge(rpUser);
				emRpaPortal.flush();
				result = true;
			} else {
				result = false;
			}
		}  catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "deleteUserWithSubProfileByCode");
			return false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteUserWithSubProfileByCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean insertUsersWithSubProfile(RpEnablingCode rpEnablingCode, List<String> users) throws DAOException {

		boolean result = false;
		RpUser rpUser = null;
		RpOffice rpOffice = new RpOffice();
		rpOffice.setId("1");

		try {
			for(String user: users){
				//inserisco l'utente
				rpUser = new RpUser();
				rpUser.setId(user);
				rpUser.setRpOffice(rpOffice);
				rpUser.setRpEnablingCode(rpEnablingCode);
				rpUser.setSubProfile(true);
				emRpaPortal.merge(rpUser);
				emRpaPortal.flush();
			}
			result = true; 
		} catch (PersistenceException persistenceException) { 
			result = false;
			ControllerLogger.fatal(persistenceException.getMessage(), "inserUsersAM");
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "inserUsersAM");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean deleteEnableProcess(String enablingCode, long processId) throws DAOException {
		RpAssProcessCode rpAssProcessCode = null;
		RpAssProcessCodePK rpAssProcessCodePK = new RpAssProcessCodePK();
		boolean result = false;

		try {
			rpAssProcessCodePK.setIdCode(enablingCode);
			rpAssProcessCodePK.setIdProcess(processId);
			rpAssProcessCode = emRpaPortal.find(RpAssProcessCode.class, rpAssProcessCodePK);
			if (null != rpAssProcessCode) {
				emRpaPortal.remove(rpAssProcessCode);
				emRpaPortal.flush();
				result = true;
			} else {
				result = false;
			}
		}  catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "deleteEnableProcess");
			return false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteEnableProcess");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

	public boolean insertEnableProcess(String enablingCode, List<String> processesId) throws DAOException {

		boolean result = false;
		RpAssProcessCode rpAssProcessCode = null;
		RpAssProcessCodePK rpAssProcessCodePK = null;

		try {
			for(String process: processesId){
				//inserisco l'utente
				rpAssProcessCodePK = new RpAssProcessCodePK();
				rpAssProcessCode = new RpAssProcessCode();
				rpAssProcessCodePK.setIdCode(enablingCode);
				rpAssProcessCodePK.setIdProcess(Long.parseLong(process));
				rpAssProcessCode.setId(rpAssProcessCodePK);
				emRpaPortal.merge(rpAssProcessCode);
				emRpaPortal.flush();
			}
			result = true; 
		} catch (PersistenceException persistenceException) { 
			result = false;
			ControllerLogger.fatal(persistenceException.getMessage(), "insertEnableProcess");
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "insertEnableProcess");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}
		
	public boolean deleteUser(String user) throws DAOException {
		RpUser rpUser = null;
		boolean result = false;

		try {
			rpUser = emRpaPortal.find(RpUser.class, user);
			if (null != rpUser) {
				emRpaPortal.remove(rpUser);
				emRpaPortal.flush();
				result = true;
			} else {
				result = false;
			}

		}  catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "deleteUser");
			return false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "deleteUser");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}
	
	public boolean updateUser(RpUser rpUser) throws DAOException {

		boolean result = false;
		
		try{
			emRpaPortal.merge(rpUser);
			emRpaPortal.flush();
			result = true;
		} catch (PersistenceException persistenceException) {
			ControllerLogger.fatal(persistenceException.getMessage(), "updateUser");
			result = false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "updateUser");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}

}
