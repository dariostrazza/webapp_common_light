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
 * The persistent class for the RP_EXT_SYSTEM database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_EXT_SYSTEM", schema = "WRPA_OWN")
@NamedQuery(name = "RpExtSystem.findAll", query = "SELECT r FROM RpExtSystem r")
public class RpExtSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPEXTSYSTEM_GENERATOR", sequenceName = "SEQ_RP_EXT_SYSTEM", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPEXTSYSTEM_GENERATOR")
	@Column(name = "ID")
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME")
	private String name;

	public RpExtSystem() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}