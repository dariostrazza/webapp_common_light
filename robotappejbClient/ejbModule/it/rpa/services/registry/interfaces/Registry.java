package it.rpa.services.registry.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.DataCBean;
import it.rpa.common.utilities.ExcelCBean;
import it.rpa.registry.exception.classes.EJBRegistryException;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.registry.bean.classes.EnablingCodeCBean;
import it.rpa.services.registry.bean.classes.ProcessRegistry;

@Local
public interface Registry {

	// DATA
	Collection<DataCBean> getData() throws EJBRegistryException;

	boolean removeData(String dataCode) throws EJBRegistryException;

	boolean insertData(String code, String description, String type) throws EJBRegistryException;

	boolean insertDataFromExcel(ExcelCBean eCbean) throws EJBRegistryException;

	// EXCEPTION
	Collection<KeyValueCBean> getException() throws EJBRegistryException;

	boolean removeException(String exceptionCode) throws EJBRegistryException;

	boolean insertException(String code, String description) throws EJBRegistryException;

	boolean insertExceptionFromExcel(ExcelCBean eCbean) throws EJBRegistryException;

	// ProcessRegistry
	Collection<ProcessRegistry> getProcessesQueue() throws EJBRegistryException;

	Collection<ProcessRegistry> getProcessesGovernance() throws EJBRegistryException;

	boolean updateProcGov(String dateIns, String idProcMain, String idProcess, String name, String description,
			String businessOwner, String service, String category, String idExtProc) throws EJBRegistryException;

	boolean updateProcGovernance(String idProcMain, String idProcess, String name, String description,
			String businessOwner, String service, String category) throws EJBRegistryException;

	Collection<String> getProcessOwners(String processId) throws EJBRegistryException;

	Boolean deleteProcessOwners(String user, long processId) throws EJBRegistryException;

	// Business Owner
	Collection<KeyValueCBean> getBusinessOwnersLicense() throws EJBRegistryException;

	Boolean deleteBusinessOwner(String businessOwner) throws EJBRegistryException;

	boolean updateBusinessOwner(String businessOwner, int license) throws EJBRegistryException;

	// Codici Abilitativi
	Collection<EnablingCodeCBean> getEnablingCodes() throws EJBRegistryException;

	Collection<String> getUsersWithSubProfileByCode(String enablingCode) throws EJBRegistryException;

	boolean updateProfileRD(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayUsersRD) throws EJBRegistryException;

	Boolean deleteEnablingCodeProfile(String enablingCode, Profile profile) throws EJBRegistryException;

	Boolean deleteUserWithSubProfileByCode(String enablingCode, String user) throws EJBRegistryException;

	boolean updateProfileAM(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayUsersAM) throws EJBRegistryException;

	boolean updateProfileGOV(String enablingCode, String descriptionCode, String businessOwner, Profile profile)
			throws EJBRegistryException;

	Boolean deleteEnableProcess(String enablingCode, long processId) throws EJBRegistryException;

	boolean updateProfileUPB(String enablingCode, String descriptionCode, String businessOwner, Profile profile,
			List<String> arrayEnableProcess) throws EJBRegistryException;

	// Utenti
	Collection<KeyValueCBean> getUsers() throws EJBRegistryException;

	Boolean deleteUser(String user) throws EJBRegistryException;

	boolean updateUser(String user, String enablingCode) throws EJBRegistryException;

}
