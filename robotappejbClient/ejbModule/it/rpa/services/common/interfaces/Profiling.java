package it.rpa.services.common.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.CommonMethods.Profile;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.bean.classes.ProfileCBean;

@Local
public interface Profiling {

	Collection<String> getFunctionalities(String enablingCode, String user, Profile profile) throws EJBCommonException;

	Collection<KeyValueCBean> getMenuContents(String enablingCode, String user, Profile profile)
			throws EJBCommonException;

	ProfileCBean getProfileByUser(String user) throws EJBCommonException;

}
