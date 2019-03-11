package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_PROF_FUNC database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_PROF_FUNC", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssProfFunc.findAll", query = "SELECT r FROM RpAssProfFunc r"),
		@NamedQuery(name = "RpAssProfFunc.findFuncbyProfile", query = "SELECT r FROM RpAssProfFunc r WHERE r.id.idProf LIKE :profile"), })
public class RpAssProfFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProfFuncPK id;

	public RpAssProfFunc() {
	}

	public RpAssProfFuncPK getId() {
		return this.id;
	}

	public void setId(RpAssProfFuncPK id) {
		this.id = id;
	}

}