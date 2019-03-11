package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_BUSINESS_OWNER database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_BUSINESS_OWNER", schema = "WRPA_OWN")
@NamedQuery(name = "RpBusinessOwner.findAll", query = "SELECT r FROM RpBusinessOwner r")
public class RpBusinessOwner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "LICENSE")
	private long license;

	public RpBusinessOwner() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getLicense() {
		return license;
	}

	public void setLicense(long license) {
		this.license = license;
	}

}