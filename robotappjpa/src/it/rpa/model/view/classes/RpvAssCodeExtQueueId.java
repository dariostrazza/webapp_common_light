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
@Table(name = "RPV_ASS_CODE_EXT_QUEUE_ID", schema = "WRPA_OWN")
@NamedQueries(value = {
		@NamedQuery(name = "RpvAssCodeExtQueueId.findAll", query = "SELECT r FROM RpvAssCodeExtQueueId r"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findByCode", query = "SELECT r FROM RpvAssCodeExtQueueId r WHERE r.id.idCode = :code"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findByCodeProcId", query = "SELECT r FROM RpvAssCodeExtQueueId r WHERE r.id.idCode = :code AND r.id.idProcess = :processId"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findByCodeServizioCategoriaOwner", query = "SELECT r FROM RpvAssCodeExtQueueId r WHERE r.id.idCode = :code AND r.categoria = :categoria AND r.servizio = :servizio AND r.businessOwner = :businessOwner"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findDistinctQueuIdByCode", query = "SELECT DISTINCT r.queueId FROM RpvAssCodeExtQueueId r WHERE r.id.idCode = :code"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findDistinctKeyField", query = "SELECT DISTINCT r.keyField FROM RpvAssCodeExtQueueId r ORDER BY r.keyField"),
		@NamedQuery(name = "RpvAssCodeExtQueueId.findDistinctKeyFieldByCategories", query = "SELECT DISTINCT r.keyField FROM RpvAssCodeExtQueueId r WHERE r.categoria LIKE :category ORDER BY r.keyField"), })
public class RpvAssCodeExtQueueId implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProcessCodePK id;

	@Column(name = "KEY_FIELD")
	private String keyField;

	@Column(name = "NAME")
	private String processName;

	@Column(name = "ID_EXT_SYS")
	private String queueId;

	@Column(name = "BUSINESS_OWNER")
	private String businessOwner;

	@Column(name = "CATEGORIA")
	private String categoria;

	@Column(name = "SERVIZIO")
	private String servizio;

	public RpvAssCodeExtQueueId() {
	}

	public RpAssProcessCodePK getId() {
		return id;
	}

	public void setId(RpAssProcessCodePK id) {
		this.id = id;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
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
