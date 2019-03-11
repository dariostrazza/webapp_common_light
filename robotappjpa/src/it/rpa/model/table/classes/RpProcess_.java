package it.rpa.model.table.classes;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-11T12:24:06.120+0100")
@StaticMetamodel(RpProcess.class)
public class RpProcess_ {
	public static volatile SingularAttribute<RpProcess, Long> id;
	public static volatile SingularAttribute<RpProcess, RpProcessInputType> rpProcessInputType;
	public static volatile SingularAttribute<RpProcess, RpExtSystem> rpExtSystem;
	public static volatile SingularAttribute<RpProcess, RpProcessType> rpProcessType;
	public static volatile SingularAttribute<RpProcess, byte[]> processTemplate;
	public static volatile SingularAttribute<RpProcess, Integer> numColumns;
	public static volatile SingularAttribute<RpProcess, String> name;
	public static volatile SingularAttribute<RpProcess, String> description;
	public static volatile SingularAttribute<RpProcess, String> columnKey;
	public static volatile SingularAttribute<RpProcess, RpBusinessOwner> rpBusinessOwner;
	public static volatile SingularAttribute<RpProcess, String> categoria;
	public static volatile SingularAttribute<RpProcess, String> servizio;
	public static volatile SingularAttribute<RpProcess, String> idExtSys;
	public static volatile SingularAttribute<RpProcess, Long> idProcessMain;
	public static volatile SingularAttribute<RpProcess, Date> insertDate;
	public static volatile SingularAttribute<RpProcess, Date> lastUpdateDate;
	public static volatile SingularAttribute<RpProcess, Boolean> unmanaged;
}
