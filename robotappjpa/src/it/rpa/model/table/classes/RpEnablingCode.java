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
 * The persistent class for the RP_ENABLING_CODE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_ENABLING_CODE", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpEnablingCode.findAll", query = "SELECT r FROM RpEnablingCode r"), })
public class RpEnablingCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "BUSINESS_OWNER")
	private String businessOwner;

	public RpEnablingCode() {
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

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

}