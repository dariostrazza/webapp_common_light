package it.rpa.model.view.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Cacheable(false)
@Entity
@Table(name = "RPV_ASS_QUEUE_PROCESS", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpvAssQueueProcess.findAll", query = "SELECT r FROM RpvAssQueueProcess r"),
		@NamedQuery(name = "RpvAssQueueProcess.findDistinctKeyField", query = "SELECT DISTINCT r.keyField FROM RpvAssQueueProcess r ORDER BY r.keyField"),
		@NamedQuery(name = "RpvAssQueueProcess.findDistinctKeyFieldByCategories", query = "SELECT DISTINCT r.keyField FROM RpvAssQueueProcess r WHERE r.categoria LIKE :category ORDER BY r.keyField"), })
public class RpvAssQueueProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private long processId;

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

	public RpvAssQueueProcess() {
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
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
