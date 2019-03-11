package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_MENU database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_MENU", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpMenu.findAll", query = "SELECT r FROM RpMenu r"), })
public class RpMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LABEL")
	private String label;

	@Column(name = "PAGE")
	private String page;

	public RpMenu() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}