package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the RP_OFFICE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_OFFICE", schema = "WRPA_OWN")
@NamedQuery(name = "RpOffice.findAll", query = "SELECT r FROM RpOffice r")
public class RpOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPOFFICE_GENERATOR", sequenceName = "SEQ_RP_OFFICE", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPOFFICE_GENERATOR")
	@Column(name = "ID")
	private String id;

	@Column(name = "DESCRIPTION")
	private String description;

	public RpOffice() {
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