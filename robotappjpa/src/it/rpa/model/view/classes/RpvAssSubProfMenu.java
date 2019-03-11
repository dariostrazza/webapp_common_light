package it.rpa.model.view.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.rpa.model.table.classes.RpAssSubProfMenuPK;

/**
 * The persistent class for the RPV_ASS_SUB_PROF_MENU database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RPV_ASS_SUB_PROF_MENU", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpvAssSubProfMenu.findAll", query = "SELECT r FROM RpvAssSubProfMenu r"),
		@NamedQuery(name = "RpvAssSubProfMenu.findBySubProf", query = "SELECT r FROM RpvAssSubProfMenu r WHERE r.id.idProf LIKE :profile ORDER BY r.position"),

})
public class RpvAssSubProfMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssSubProfMenuPK id;

	@Column(name = "PAGE")
	private String page;

	@Column(name = "POSITION")
	private long position;

	public RpvAssSubProfMenu() {

	}

	public RpAssSubProfMenuPK getId() {
		return id;
	}

	public void setId(RpAssSubProfMenuPK id) {
		this.id = id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}