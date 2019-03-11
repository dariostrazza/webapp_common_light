package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_PROF_CODE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_PROF_CODE", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssProfCode.findAll", query = "SELECT r FROM RpAssProfCode r"),
		@NamedQuery(name = "RpAssProfCode.findProfbyCode", query = "SELECT r FROM RpAssProfCode r WHERE r.id.idCode LIKE :code"), })
public class RpAssProfCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProfCodePK id;

	public RpAssProfCode() {
	}

	public RpAssProfCodePK getId() {
		return this.id;
	}

	public void setId(RpAssProfCodePK id) {
		this.id = id;
	}

}