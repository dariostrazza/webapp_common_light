package it.rpa.dao.common.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.dao.exception.classes.DAOException;
import it.rpa.ejb.interceptor.classes.ControllerInterceptor;
import it.rpa.model.table.classes.RpAssProcessCodePK_;
import it.rpa.model.table.classes.RpAssProcessOwner;
import it.rpa.model.table.classes.RpBusinessOwner_;
import it.rpa.model.table.classes.RpEnablingCode;
import it.rpa.model.table.classes.RpProcess;
import it.rpa.model.table.classes.RpProcessType_;
import it.rpa.model.table.classes.RpProcess_;
import it.rpa.model.table.classes.RpUser;
import it.rpa.model.view.classes.RpvAssCodeProcDetail;
import it.rpa.model.view.classes.RpvAssCodeProcDetail_;

@Interceptors(ControllerInterceptor.class)
@Stateless
public class CommonDAO {

	@PersistenceContext(unitName = "robotappjpa")
	EntityManager emRpaPortal;

	/**
	 * Default constructor.
	 */
	public CommonDAO() {
	}

	public Collection<String> findDistinctKeyField(String category) throws DAOException {
		Collection<String> keyFields = null;
		TypedQuery<String> query = null;

		try {
			if (null != category) {
				query = emRpaPortal.createNamedQuery("RpvAssQueueProcess.findDistinctKeyFieldByCategories",
						String.class);
				query = query.setParameter("category", category);
			} else {
				query = emRpaPortal.createNamedQuery("RpvAssQueueProcess.findDistinctKeyField", String.class);
			}
			keyFields = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctDataCatalogByExtId");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return keyFields;
	}

	public Collection<String> findDistinctKeyFieldByCode(String enablingCode, String category) throws DAOException {
		Collection<String> keyFields = null;
		TypedQuery<String> query = null;

		try {
			if (null != category) {
				query = emRpaPortal.createNamedQuery("RpvAssCodeExtQueueId.findDistinctKeyFieldByCategories",
						String.class);
				query = query.setParameter("category", category);
			} else {
				query = emRpaPortal.createNamedQuery("RpvAssCodeExtQueueId.findDistinctKeyField", String.class);
			}
			keyFields = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctKeyFieldByCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return keyFields;
	}


	public List<Long> findProcessByEnablingCode(String enablingCode) throws DAOException {
		List<Long> rpAssProcessCodes = null;
		TypedQuery<Long> query = null;

		try {
			query = emRpaPortal.createNamedQuery("RpAssProcessCode.findProcessbyCode", Long.class);
			query = query.setParameter("code", enablingCode);
			rpAssProcessCodes = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessByEnablingCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpAssProcessCodes;
	}

	public boolean isProcessOwner(String userId, long processId) throws DAOException {
		Collection<RpAssProcessOwner> rpAssProcessOwners = null;
		TypedQuery<RpAssProcessOwner> query = null;
		boolean result = false;

		try {
			query = emRpaPortal.createNamedQuery("RpAssProcessOwner.findOwnersByProcessId", RpAssProcessOwner.class);
			query = query.setParameter("processId", processId);
			rpAssProcessOwners = query.getResultList();
			if (rpAssProcessOwners.size() == 0) {
				result = true;
			} else {
				for (RpAssProcessOwner rpAssProcessOwner : rpAssProcessOwners) {
					if (userId.equalsIgnoreCase(rpAssProcessOwner.getId().getIdUser())) {
						result = true;
					}
				}
			}

		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "isProcessOwner");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}


	public boolean isApplicationMaintance(String enablingCode, String user) throws DAOException {
		RpUser rpUser = null;
		TypedQuery<RpUser> query = null;
		boolean result = false;

		try {
			query = emRpaPortal.createNamedQuery("RpUser.findUserSubProfileByCode", RpUser.class);
			query = query.setParameter("enablingCode", enablingCode);
			query = query.setParameter("user", user);
			rpUser = query.getSingleResult();
			if (null != rpUser) {
				result = true;
			}
		} catch (NoResultException noResultException) {
			ControllerLogger.fatal(noResultException.getMessage(), "isApplicationMaintance");
			result = false;
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "isApplicationMaintance");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return result;
	}



	public RpProcess findProcessByName(String processName) throws DAOException {
		Collection<RpProcess> rpProcesses = null;
		TypedQuery<RpProcess> query = null;
		RpProcess rpProcess = null;

		try {
			query = emRpaPortal.createNamedQuery("RpProcess.findByName", RpProcess.class);
			query = query.setParameter("processname", processName);
			rpProcesses = query.getResultList();
			if (rpProcesses.size() != 1) {
				ControllerLogger.fatal("Unexspected size of rpProcess: " + rpProcesses.size(), "findProcessByName");
				throw new DAOException("Trovati " + rpProcesses.size() + "processi associati al nome: " + processName);
			}
			rpProcess = rpProcesses.iterator().next();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessByName");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpProcess;
	}

	public String findBusinessOwnerByCode(String enablingCode) throws DAOException {
		RpEnablingCode rpEnablingCode = null;
		String businessOwner = null;
		try {
			rpEnablingCode = emRpaPortal.find(RpEnablingCode.class, enablingCode);
			businessOwner = rpEnablingCode.getBusinessOwner();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findBusinessOwnerByCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return businessOwner;
	}

	public List<String> findDistinctBusinessOwners() throws DAOException {
		List<String> businessOwners = null;
		TypedQuery<String> query = null;

		try {
			query = emRpaPortal.createNamedQuery("RpProcess.findDistinctBusinessOwner", String.class);
			businessOwners = query.getResultList();

		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctBusinessOwners");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return businessOwners;
	}

	public Collection<String> findDistinctServices(String businessOwner) throws DAOException {

		List<String> services = null;
		TypedQuery<String> query = null;
		final List<Predicate> predicateList = new ArrayList<Predicate>();
		Predicate predicate = null;

		try {
			final CriteriaBuilder criteriaBuilder = emRpaPortal.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
			Root<RpProcess> root = criteriaQuery.from(RpProcess.class);

			criteriaQuery.select(root.get(RpProcess_.servizio)).distinct(true);

			// filtro sul business owner
			if (null != businessOwner) {
				predicate = criteriaBuilder.like(root.get(RpProcess_.rpBusinessOwner).get(RpBusinessOwner_.id),
						businessOwner);
				predicateList.add(predicate);
			}

			// filtro sul process type
			predicate = criteriaBuilder.equal(root.get(RpProcess_.rpProcessType).get(RpProcessType_.id), 2);
			predicateList.add(predicate);

			criteriaQuery.where(predicateList.toArray(new Predicate[0]));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(RpProcess_.servizio)));
			query = emRpaPortal.createQuery(criteriaQuery);
			services = query.getResultList();
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctServices");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return services;
	}


	public Collection<String> findDistinctCategories(String businessOwner, String service) throws DAOException {

		List<String> services = null;
		TypedQuery<String> query = null;
		final List<Predicate> predicateList = new ArrayList<Predicate>();
		Predicate predicate = null;

		try {
			final CriteriaBuilder criteriaBuilder = emRpaPortal.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
			Root<RpProcess> root = criteriaQuery.from(RpProcess.class);

			criteriaQuery.select(root.get(RpProcess_.categoria)).distinct(true);

			// filtro sul process type
			predicate = criteriaBuilder.equal(root.get(RpProcess_.rpProcessType).get(RpProcessType_.id), 2);
			predicateList.add(predicate);

			// filtro sul business owner
			if (null != businessOwner) {
				predicate = criteriaBuilder.like(root.get(RpProcess_.rpBusinessOwner).get(RpBusinessOwner_.id),
						businessOwner);
				predicateList.add(predicate);
			}

			// filtro sul servizio
			if (null != service) {
				predicate = criteriaBuilder.like(root.get(RpProcess_.servizio), service);
				predicateList.add(predicate);
			}

			criteriaQuery.where(predicateList.toArray(new Predicate[0]));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(RpProcess_.categoria)));
			query = emRpaPortal.createQuery(criteriaQuery);
			services = query.getResultList();
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctCategories");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return services;
	}

	public Collection<String> findDistinctCategoriesByCode(String enablingCode) throws DAOException {
		Collection<String> categories = null;
		TypedQuery<String> query = null;

		try {
			query = emRpaPortal.createNamedQuery("RpvAssCodeProcDetail.findDistinctCategorieByEnablingCode",
					String.class);
			query = query.setParameter("enablingCode", enablingCode);
			categories = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findDistinctCategoriesByCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return categories;
	}

	public Collection<RpvAssCodeProcDetail> findProcessesByCode(String enablingCode, String category, String businessOwner)
			throws DAOException {

		Collection<RpvAssCodeProcDetail> rpvAssCodeProcDetails = null;	

		try {
			final CriteriaBuilder criteriaBuilder = emRpaPortal
					.getCriteriaBuilder();
			final CriteriaQuery<RpvAssCodeProcDetail> criteriaQuery = criteriaBuilder
					.createQuery(RpvAssCodeProcDetail.class);
			final Root<RpvAssCodeProcDetail> root = criteriaQuery.from(RpvAssCodeProcDetail.class);
			final List<Predicate> predicateList = new ArrayList<Predicate>();
			Predicate predicate = null;
			
			predicate = criteriaBuilder.equal(root.get(RpvAssCodeProcDetail_.id).get(RpAssProcessCodePK_.idCode), enablingCode);
			predicateList.add(predicate);
			
			if(null != businessOwner){
				predicate = criteriaBuilder.equal(root.get(RpvAssCodeProcDetail_.businessOwner), businessOwner);
				predicateList.add(predicate);
			}
			if(null != category){
				predicate = criteriaBuilder.equal(root.get(RpvAssCodeProcDetail_.categoria), category);
				predicateList.add(predicate);
			}	
								
			criteriaQuery.where(predicateList.toArray(new Predicate[0]));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(RpvAssCodeProcDetail_.processName)));
			rpvAssCodeProcDetails = emRpaPortal.createQuery(criteriaQuery).getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessesByCode");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpvAssCodeProcDetails;
	}	
	
	public Collection<RpProcess> findProcessesBusiness(String category) throws DAOException {
		Collection<RpProcess> rpProcesses = null;
		TypedQuery<RpProcess> query = null;

		try {
			if(null == category){
				query = emRpaPortal.createNamedQuery("RpProcess.findAllProcessBusiness",RpProcess.class);
			}else{
				query = emRpaPortal.createNamedQuery("RpProcess.findAllProcessBusinessByCategory",RpProcess.class);
				query = query.setParameter("category", category);
			}
			rpProcesses = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessesBusiness");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpProcesses;
	}
	
	public Collection<RpProcess> findProcessesBusinessByBusinessOwner(String category,String businessOwner) throws DAOException {
		Collection<RpProcess> rpProcesses = null;
		TypedQuery<RpProcess> query = null;

		try {
			if(null == category){
				query = emRpaPortal.createNamedQuery("RpProcess.findAllProcessBusinessByBO",RpProcess.class);
			}else{
				query = emRpaPortal.createNamedQuery("RpProcess.findAllProcessBusinessByCategoryBO",RpProcess.class);
				query = query.setParameter("category", category);
			}
			query = query.setParameter("businessOwner", businessOwner);
			rpProcesses = query.getResultList();
		} catch (RuntimeException runtimeException) {
			ControllerLogger.fatal(runtimeException.getMessage(), "findProcessesBusinessByBusinessOwner");
			throw new DAOException(CommonMethods.ERROR_MESS);
		}
		return rpProcesses;
	}
}
