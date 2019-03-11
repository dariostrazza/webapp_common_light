package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_PROCESS_OWNER database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_PROCESS_OWNER", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssProcessOwner.findAll", query = "SELECT r FROM RpAssProcessOwner r"),
		@NamedQuery(name = "RpAssProcessOwner.findProcessbyOwner", query = "SELECT r FROM RpAssProcessOwner r WHERE r.id.idUser LIKE :userId"),
		@NamedQuery(name = "RpAssProcessOwner.findOwnersByListProcess", query = "SELECT r FROM RpAssProcessOwner r WHERE r.id.idProcess IN :processEnable"),
		@NamedQuery(name = "RpAssProcessOwner.findOwnersByProcessId", query = "SELECT r FROM RpAssProcessOwner r WHERE r.id.idProcess = :processId"), })

public class RpAssProcessOwner implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProcessUserPK id;

	public RpAssProcessOwner() {
	}

	public RpAssProcessUserPK getId() {
		return this.id;
	}

	public void setId(RpAssProcessUserPK id) {
		this.id = id;
	}

}