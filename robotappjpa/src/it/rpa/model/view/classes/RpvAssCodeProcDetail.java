package it.rpa.model.view.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.rpa.model.table.classes.RpAssProcessCodePK;

@Cacheable(false)
@Entity
@Table(name = "RPV_ASS_CODE_PROC_DETAIL", schema = "WRPA_OWN")
@NamedQueries(value = {
		@NamedQuery(name = "RpvAssCodeProcDetail.findAll", query = "SELECT r FROM RpvAssCodeProcDetail r"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findDistinctCategorieByEnablingCode", query = "SELECT DISTINCT r.categoria FROM RpvAssCodeProcDetail r WHERE r.id.idCode LIKE :enablingCode ORDER BY r.categoria"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findDistinctBusinessOwnerByEnablingCode", query = "SELECT DISTINCT r.businessOwner FROM RpvAssCodeProcDetail r WHERE r.id.idCode LIKE :enablingCode"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findDistinctServiziByEnablingCode", query = "SELECT DISTINCT r.servizio FROM RpvAssCodeProcDetail r WHERE r.id.idCode LIKE :enablingCode"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findDistinctServiziByEnablingCodeBusinessOwner", query = "SELECT DISTINCT r.servizio FROM RpvAssCodeProcDetail r WHERE r.id.idCode LIKE :enablingCode AND r.businessOwner LIKE :businessOwner"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findProcessNameByEnablingCode", query = "SELECT r FROM RpvAssCodeProcDetail r WHERE r.id.idCode = :enablingCode ORDER BY r.processName"),
		@NamedQuery(name = "RpvAssCodeProcDetail.findProcessNameByEnablingCodeCategory", query = "SELECT r FROM RpvAssCodeProcDetail r WHERE r.id.idCode = :enablingCode AND r.categoria LIKE :category ORDER BY r.processName") })
public class RpvAssCodeProcDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProcessCodePK id;

	@Column(name = "NAME")
	private String processName;

	@Column(name = "BUSINESS_OWNER")
	private String businessOwner;

	@Column(name = "CATEGORIA")
	private String categoria;

	@Column(name = "SERVIZIO")
	private String servizio;

	public RpvAssCodeProcDetail() {

	}

	public RpAssProcessCodePK getId() {
		return id;
	}

	public void setId(RpAssProcessCodePK id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

}
