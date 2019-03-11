package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_PROCESS_CODE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_PROCESS_CODE", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssProcessCode.findAll", query = "SELECT r FROM RpAssProcessCode r"),
		@NamedQuery(name = "RpAssProcessCode.findProcessbyCode", query = "SELECT r.id.idProcess FROM RpAssProcessCode r WHERE r.id.idCode LIKE :code"), })

public class RpAssProcessCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProcessCodePK id;

	public RpAssProcessCode() {
	}

	public RpAssProcessCodePK getId() {
		return this.id;
	}

	public void setId(RpAssProcessCodePK id) {
		this.id = id;
	}

}