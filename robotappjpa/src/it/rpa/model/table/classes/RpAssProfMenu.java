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
 * The persistent class for the RP_ASS_PROF_MENU database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ASS_PROF_MENU", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpAssProfMenu.findAll", query = "SELECT r FROM RpAssProfMenu r"),
		@NamedQuery(name = "RpAssProfMenu.findMenubyProfile", query = "SELECT r FROM RpAssProfMenu r WHERE r.id.idProf LIKE :profile"), })
public class RpAssProfMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProfMenuPK id;

	@Column(name = "POSITION")
	private long position;

	public RpAssProfMenu() {
	}

	public RpAssProfMenuPK getId() {
		return this.id;
	}

	public void setId(RpAssProfMenuPK id) {
		this.id = id;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}