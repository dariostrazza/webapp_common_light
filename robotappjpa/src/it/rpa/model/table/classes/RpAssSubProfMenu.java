package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_ASS_SUB_PROF_MENU database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_SUB_PROF_MENU", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssSubProfMenu.findAll", query = "SELECT r FROM RpAssSubProfMenu r"),
		@NamedQuery(name = "RpAssSubProfMenu.findMenubySubProfile", query = "SELECT r FROM RpAssSubProfMenu r WHERE r.id.idProf LIKE :profile"), })
public class RpAssSubProfMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssSubProfMenuPK id;

	@Column(name = "POSITION")
	private long position;

	public RpAssSubProfMenu() {
	}

	public RpAssSubProfMenuPK getId() {
		return this.id;
	}

	public void setId(RpAssSubProfMenuPK id) {
		this.id = id;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}