package it.rpa.ejb.common.classes;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.dao.common.classes.CommonDAO;
import it.rpa.dao.common.classes.GenericDAO;
import it.rpa.dao.exception.classes.DAOException;
import it.rpa.ejb.interceptor.classes.ControllerInterceptor;
import it.rpa.model.table.classes.RpAssProfFunc;
import it.rpa.model.table.classes.RpAssSubProfFunc;
import it.rpa.model.view.classes.RpvAssProfMenu;
import it.rpa.model.view.classes.RpvAssSubProfMenu;
import it.rpa.model.view.classes.RpvAssUserCodeProf;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.bean.classes.ProfileCBean;
import it.rpa.services.common.interfaces.Profiling;

/**
 * Session Bean implementation class ProfilingBean
 */

@Interceptors(ControllerInterceptor.class)
@Stateless
public class ProfilingBean implements Profiling {

	private static final String DEFAULT_ENABLING_CODE = "AADD00";

	@EJB
	CommonDAO commonDAO;

	@EJB
	GenericDAO genericDAO;

	public ProfilingBean() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<KeyValueCBean> getMenuContents(String enablingCode, String user, Profile profile)
			throws EJBCommonException {
		Collection<KeyValueCBean> menuContents = new ArrayList<KeyValueCBean>();
		Collection<RpvAssProfMenu> rpvAssProfMenus = null;
		Collection<RpvAssSubProfMenu> rpvAssSubProfMenus = null;
		KeyValueCBean menuContent = null;
		String subProfileKey = null;

		try {
			rpvAssProfMenus = (Collection<RpvAssProfMenu>) genericDAO.findByParam("findByProf", RpvAssProfMenu.class,
					"profile", profile.toString());
			for (RpvAssProfMenu rpvAssProfMenu : rpvAssProfMenus) {
				menuContent = new KeyValueCBean();
				menuContent.setKey(rpvAssProfMenu.getPage());
				menuContent.setValue(rpvAssProfMenu.getId().getIdMenu());
				menuContents.add(menuContent);
			}
			if (profile == Profile.AM && commonDAO.isApplicationMaintance(enablingCode, user)) {
				subProfileKey = CommonMethods.SUB_PROF_AM;
			}
			// da aggiungere per gli altri profili

			if (null != subProfileKey) {
				rpvAssSubProfMenus = (Collection<RpvAssSubProfMenu>) genericDAO.findByParam("findBySubProf",
						RpvAssSubProfMenu.class, "profile", subProfileKey);
				for (RpvAssSubProfMenu rpvAssSubProfMenu : rpvAssSubProfMenus) {
					menuContent = new KeyValueCBean();
					menuContent.setKey(rpvAssSubProfMenu.getPage());
					menuContent.setValue(rpvAssSubProfMenu.getId().getIdMenu());
					menuContents.add(menuContent);
				}
			}

		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getMenuContents");
			throw new EJBCommonException(daoException.getMessage());
		}
		return menuContents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getFunctionalities(String enablingCode, String user, Profile profile)
			throws EJBCommonException {
		Collection<String> functionalties = new ArrayList<String>();
		Collection<RpAssProfFunc> rpAssProfFuncs = null;
		Collection<RpAssSubProfFunc> rpAssSubProfFuncs = null;
		String subProfileKey = null;

		try {
			rpAssProfFuncs = (Collection<RpAssProfFunc>) genericDAO.findByParam("findFuncbyProfile",
					RpAssProfFunc.class, "profile", profile.toString());
			for (RpAssProfFunc functionality : rpAssProfFuncs) {
				functionalties.add(functionality.getId().getIdFunc());
			}

			if (profile == Profile.AM && commonDAO.isApplicationMaintance(enablingCode, user)) {
				subProfileKey = CommonMethods.SUB_PROF_AM;
			}
			// da aggiungere per gli altri profili

			if (null != subProfileKey) {
				rpAssSubProfFuncs = (Collection<RpAssSubProfFunc>) genericDAO.findByParam("findFuncbySubProfile",
						RpAssSubProfFunc.class, "subProfile", subProfileKey);
				for (RpAssSubProfFunc functionality : rpAssSubProfFuncs) {
					functionalties.add(functionality.getId().getIdFunc());
				}
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getFunctionalities");
			throw new EJBCommonException(daoException.getMessage());
		}
		return functionalties;
	}

	@Override
	public ProfileCBean getProfileByUser(String user) throws EJBCommonException {
		ProfileCBean profileCBean = new ProfileCBean();
		RpvAssUserCodeProf rpvAssUserCodeProf = null;

		try {
			// verifico se l'utente è censito e se ha un profilo
			rpvAssUserCodeProf = (RpvAssUserCodeProf) genericDAO.findById(user, RpvAssUserCodeProf.class);
			if (null != rpvAssUserCodeProf) {
				// se l'utente è censito ma non ha un profilo assegno quello UPB
				if (null == rpvAssUserCodeProf.getCode()) {
					profileCBean.setEnablingCode(DEFAULT_ENABLING_CODE);
					profileCBean.setProfile(Profile.UPB.toString());
				} else {
					profileCBean.setEnablingCode(rpvAssUserCodeProf.getCode());
					profileCBean.setProfile(rpvAssUserCodeProf.getProfile());
				}
				profileCBean.setUser(rpvAssUserCodeProf.getId());
			}
		} catch (DAOException daoException) {
			ControllerLogger.fatal(daoException.getMessage(), "getProfileByUser");
			throw new EJBCommonException(daoException.getMessage());
		}
		return profileCBean;
	}

}
