package it.rpa.model.view.classes;

import it.rpa.model.table.classes.RpAssProcessCodePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-08T18:14:39.709+0100")
@StaticMetamodel(RpvAssCodeProcDetail.class)
public class RpvAssCodeProcDetail_ {
	public static volatile SingularAttribute<RpvAssCodeProcDetail, RpAssProcessCodePK> id;
	public static volatile SingularAttribute<RpvAssCodeProcDetail, String> processName;
	public static volatile SingularAttribute<RpvAssCodeProcDetail, String> businessOwner;
	public static volatile SingularAttribute<RpvAssCodeProcDetail, String> categoria;
	public static volatile SingularAttribute<RpvAssCodeProcDetail, String> servizio;
}
