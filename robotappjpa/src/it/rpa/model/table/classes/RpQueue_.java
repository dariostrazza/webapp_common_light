package it.rpa.model.table.classes;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-08T18:14:39.647+0100")
@StaticMetamodel(RpQueue.class)
public class RpQueue_ {
	public static volatile SingularAttribute<RpQueue, Long> id;
	public static volatile SingularAttribute<RpQueue, RpProcess> rpProcessProducer;
	public static volatile SingularAttribute<RpQueue, RpProcess> rpProcessConsumer;
	public static volatile SingularAttribute<RpQueue, String> keyField;
	public static volatile SingularAttribute<RpQueue, String> description;
	public static volatile SingularAttribute<RpQueue, String> name;
	public static volatile SingularAttribute<RpQueue, String> idExtSys;
}
