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
 * The persistent class for the RP_EXCEPTION_CATALOG database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_EXCEPTION_CATALOG", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpExceptionCatalog.findAll", query = "SELECT r FROM RpExceptionCatalog r"),
		@NamedQuery(name = "RpExceptionCatalog.findByCode", query = "SELECT r FROM RpExceptionCatalog r WHERE r.code like :codeException") })
public class RpExceptionCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}