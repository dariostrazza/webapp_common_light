package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_FUNCTIONALITY database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_FUNCTIONALITY", schema = "WRPA_OWN")
@NamedQuery(name = "RpFunctionality.findAll", query = "SELECT r FROM RpFunctionality r")
public class RpFunctionality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "DESCRIPTION")
	private String description;

	public RpFunctionality() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}