package it.rpa.ejb.common.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.dao.common.classes.CommonDAO;
import it.rpa.dao.common.classes.GenericDAO;
import it.rpa.dao.exception.classes.DAOException;
import it.rpa.ejb.interceptor.classes.ControllerInterceptor;
import it.rpa.model.table.classes.RpBusinessOwner;
import it.rpa.model.table.classes.RpProcess;
import it.rpa.model.table.classes.RpProcessInputType;
import it.rpa.model.table.classes.RpProfile;
import it.rpa.model.view.classes.RpvAssCodeProcDetail;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.bean.classes.ProcessCBean;
import it.rpa.services.common.interfaces.Common;

/**
 * Session Bean implementation class CommonBean
 */

@Interceptors(ControllerInterceptor.class)
@Stateless
public class CommonBean implements Common {

	@EJB
	CommonDAO commonDAO;

	@EJB
	GenericDAO genericDAO;

	public CommonBean() {

	}

	// Common WS

	@Override
	public Collection<String> getKeyFieldsQueue(String enablingCode, Profile profile, String category)
			throws EJBCommonException {
		Collection<String> keyFields = null;

		try {
			if (profile == Profile.UPB) {
				keyFields = commonDAO.findDistinctKeyFieldByCode(enablingCode, category);
			} else {
				keyFields = commonDAO.findDistinctKeyField(category);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getKeyFieldsQueue");
			throw new EJBCommonException(daoException.getMessage());
		}
		return keyFields;
	}

	@Override
	public Collection<String> getDistinctBusinessOwners(String enablingCode, Profile profile)
			throws EJBCommonException {
		Collection<String> businessOwners = null;

		try {
			businessOwners = commonDAO.findDistinctBusinessOwners();
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getDistinctBusinessOwners");
			throw new EJBCommonException(daoException.getMessage());
		}
		return businessOwners;
	}

	@Override
	public Collection<String> getDistinctCategories(String enablingCode, Profile profile, String businessOwner,
			String service) throws EJBCommonException {
		Collection<String> categories = null;

		try {
			if (profile == Profile.UPB) {
				categories = commonDAO.findDistinctCategoriesByCode(enablingCode);
			} else {
				if (profile == Profile.RD) {
					businessOwner = commonDAO.findBusinessOwnerByCode(enablingCode);
				}
				categories = commonDAO.findDistinctCategories(businessOwner, service);
			}
			return categories;
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getDistinctCategories");
			throw new EJBCommonException(daoException.getMessage());
		}
	}

	@Override
	public Collection<String> getDistinctServices(String enablingCode, Profile profile, String businessOwner)
			throws EJBCommonException {
		Collection<String> services;

		try {
			if (profile == Profile.RD) {
				businessOwner = commonDAO.findBusinessOwnerByCode(enablingCode);
			}
			services = commonDAO.findDistinctServices(businessOwner);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getDistinctServices");
			throw new EJBCommonException(daoException.getMessage());
		}
		return services;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getScheduleModalities() throws EJBCommonException {
		Collection<KeyValueCBean> keyValueCBeans = new ArrayList<KeyValueCBean>();
		KeyValueCBean keyValueCBean = null;
		Collection<RpProcessInputType> rpProcessInputTypes = null;

		try {
			rpProcessInputTypes = (Collection<RpProcessInputType>) genericDAO.findAll(RpProcessInputType.class);
			for (RpProcessInputType item : rpProcessInputTypes) {
				keyValueCBean = new KeyValueCBean();
				keyValueCBean.setKey(item.getId() + "");
				keyValueCBean.setValue(item.getDescription());
				keyValueCBeans.add(keyValueCBean);
				keyValueCBean = null;
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getScheduleModalities");
			throw new EJBCommonException(daoException.getMessage());
		}
		return keyValueCBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getBusinessOwnersList() throws EJBCommonException {
		Collection<RpBusinessOwner> rpBusinessOwners = null;
		Collection<String> businessOwners = new ArrayList<String>();

		try {
			rpBusinessOwners = (Collection<RpBusinessOwner>) genericDAO.findAll(RpBusinessOwner.class);
			for (RpBusinessOwner businessOwner : rpBusinessOwners) {
				businessOwners.add(businessOwner.getId());
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getBusinessOwnersList");
			throw new EJBCommonException(daoException.getMessage());
		}
		return businessOwners;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getProfiles() throws EJBCommonException {
		Collection<RpProfile> rpProfiles = null;
		Collection<KeyValueCBean> keyValueCBeans = new ArrayList<KeyValueCBean>();
		KeyValueCBean keyValueCBean = null;

		try {
			rpProfiles = (Collection<RpProfile>) genericDAO.findAll(RpProfile.class);
			for (RpProfile profile : rpProfiles) {
				keyValueCBean = new KeyValueCBean();
				keyValueCBean.setKey(profile.getId());
				keyValueCBean.setValue(profile.getDescription());
				keyValueCBeans.add(keyValueCBean);
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProfiles");
			throw new EJBCommonException(daoException.getMessage());
		}
		return keyValueCBeans;
	}

	// Common
	@Override
	public Boolean isProcessOwner(String userId, long processId) throws EJBCommonException {
		boolean result = false;

		try {
			result = commonDAO.isProcessOwner(userId, processId);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "isProcessOwner");
			throw new EJBCommonException(daoException.getMessage());
		}
		return result;
	}

	@Override
	public List<Long> getEnableProcesses(String enablingCode, Profile profile) throws EJBCommonException {
		List<Long> enableProcesses = new ArrayList<Long>();

		try {
			enableProcesses = commonDAO.findProcessByEnablingCode(enablingCode);
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getEnableProcesses");
			throw new EJBCommonException(daoException.getMessage());
		}
		return enableProcesses;
	}

	@Override
	public long getProcessId(String processName) throws EJBCommonException {
		long processId = 0;
		RpProcess rpProcess = null;

		try {
			rpProcess = commonDAO.findProcessByName(processName);
			processId = rpProcess.getId();
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcessId");
			throw new EJBCommonException(daoException.getMessage());
		}
		return processId;
	}
	
	
	@Override
	public Collection<ProcessCBean> getProcessNames(String enablingCode,Profile profile, String category,String businessOwner)
			throws EJBCommonException {
		Collection<ProcessCBean> processes = new ArrayList<ProcessCBean>();
		Collection<RpvAssCodeProcDetail> rpvAssCodeProcDetails = null;
		ProcessCBean processCBean = null;

		try {
			rpvAssCodeProcDetails = commonDAO.findProcessesByCode(enablingCode,category,businessOwner);
			for(RpvAssCodeProcDetail process: rpvAssCodeProcDetails){
				processCBean = new ProcessCBean();
				processCBean.setProcessId(process.getId().getIdProcess()+"");
				processCBean.setProcessName(process.getProcessName());
				processes.add(processCBean);
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcessNames");
			throw new EJBCommonException(daoException.getMessage());
		}
		return processes;
	}
	
	@Override
	public Collection<ProcessCBean> getProcessesList(String enablingCode,Profile profile,String cateogry, String businessOwner)
			throws EJBCommonException {
		Collection<ProcessCBean> processes = new ArrayList<ProcessCBean>();
		Collection<RpProcess> rpProcesses = null;
		ProcessCBean process = null;
	
		try {
			if(profile == Profile.RD){
				businessOwner = commonDAO.findBusinessOwnerByCode(enablingCode);
			}
			
			if(null != businessOwner && !businessOwner.isEmpty()){
				rpProcesses = commonDAO.findProcessesBusinessByBusinessOwner(cateogry,businessOwner);
			}else {
				rpProcesses = commonDAO.findProcessesBusiness(cateogry);
			}
			
			for (RpProcess processItem : rpProcesses) {
				process = new ProcessCBean();
				process.setProcessId(processItem.getId()+"");
				process.setProcessName(processItem.getName());
				processes.add(process);
				process = null;
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProcessesList");
			throw new EJBCommonException(daoException.getMessage());
		}
		return processes;
	}


}
