package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_SUB_PROF_FUNC database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_SUB_PROF_FUNC", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssSubProfFunc.findAll", query = "SELECT r FROM RpAssSubProfFunc r"),
		@NamedQuery(name = "RpAssSubProfFunc.findFuncbySubProfile", query = "SELECT r FROM RpAssSubProfFunc r WHERE r.id.idProf LIKE :subProfile"), })
public class RpAssSubProfFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssSubProfFuncPK id;

	public RpAssSubProfFunc() {
	}

	public RpAssSubProfFuncPK getId() {
		return this.id;
	}

	public void setId(RpAssSubProfFuncPK id) {
		this.id = id;
	}

}