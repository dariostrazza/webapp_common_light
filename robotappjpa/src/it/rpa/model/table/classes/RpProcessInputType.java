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
 * The persistent class for the RP_PROCESS_INPUT_TYPE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_PROCESS_INPUT_TYPE", schema = "WRPA_OWN")
@NamedQuery(name = "RpProcessInputType.findAll", query = "SELECT r FROM RpProcessInputType r ORDER BY r.id")
public class RpProcessInputType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPPROCESSINPUTTYPE_GENERATOR", sequenceName = "SEQ_RP_PROCESS_INPUT_TYPE", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPPROCESSINPUTTYPE_GENERATOR")
	@Column(name = "ID")
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	public RpProcessInputType() {
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