package it.rpa.services.common.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.bean.classes.ProcessCBean;

@Local
public interface Common {

	// Common WS
	Collection<String> getKeyFieldsQueue(String enablingCode, Profile profile, String category)
			throws EJBCommonException;

	Collection<String> getDistinctBusinessOwners(String enablingCode, Profile profile) throws EJBCommonException;

	Collection<String> getDistinctCategories(String enablingCode, Profile profile, String businessOwner, String service)
			throws EJBCommonException;

	Collection<String> getDistinctServices(String enablingCode, Profile profile, String businessOwner)
			throws EJBCommonException;

	// get all for select list
	Collection<KeyValueCBean> getScheduleModalities() throws EJBCommonException;

	Collection<String> getBusinessOwnersList() throws EJBCommonException;

	Collection<KeyValueCBean> getProfiles() throws EJBCommonException;

	// Common method
	List<Long> getEnableProcesses(String enabligCode, Profile profile) throws EJBCommonException;

	Boolean isProcessOwner(String userId, long processId) throws EJBCommonException;

	long getProcessId(String processName) throws EJBCommonException;
	
	Collection<ProcessCBean> getProcessNames(String enablingCode,Profile profile,String category,String businessOwner)
			throws EJBCommonException;
	
	Collection<ProcessCBean> getProcessesList(String enablingCode, Profile profile,String category,String businessOwner) throws EJBCommonException;
	

}
