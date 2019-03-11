package it.rpa.model.table.classes;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-08T18:14:39.662+0100")
@StaticMetamodel(RpUser.class)
public class RpUser_ {
	public static volatile SingularAttribute<RpUser, String> id;
	public static volatile SingularAttribute<RpUser, RpOffice> rpOffice;
	public static volatile SingularAttribute<RpUser, RpEnablingCode> rpEnablingCode;
	public static volatile SingularAttribute<RpUser, Boolean> subProfile;
}
