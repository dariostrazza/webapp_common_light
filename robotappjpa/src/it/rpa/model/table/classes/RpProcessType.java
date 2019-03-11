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
 * The persistent class for the RP_PROCESS_TYPE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_PROCESS_TYPE", schema = "WRPA_OWN")
@NamedQuery(name = "RpProcessType.findAll", query = "SELECT r FROM RpProcessType r")
public class RpProcessType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPPROCESSTYPE_GENERATOR", sequenceName = "SEQ_RP_PROCESS_TYPE", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPPROCESSTYPE_GENERATOR")
	@Column(name = "ID")
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	public RpProcessType() {
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

}