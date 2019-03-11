package it.rpa.model.view.classes;

import it.rpa.model.table.classes.RpAssProfCodePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-08T18:14:39.725+0100")
@StaticMetamodel(RpvAssProfCode.class)
public class RpvAssProfCode_ {
	public static volatile SingularAttribute<RpvAssProfCode, RpAssProfCodePK> id;
	public static volatile SingularAttribute<RpvAssProfCode, String> codeDescription;
	public static volatile SingularAttribute<RpvAssProfCode, String> profileDescription;
	public static volatile SingularAttribute<RpvAssProfCode, String> businessOwner;
}
