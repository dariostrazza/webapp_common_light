package it.rpa.model.table.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the RP_PROCESS database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_PROCESS", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpProcess.findAll", query = "SELECT r FROM RpProcess r"),
		@NamedQuery(name = "RpProcess.findByName", query = "SELECT r FROM RpProcess r WHERE r.name like :processname"),
		@NamedQuery(name = "RpProcess.findAllProcessBusiness", query = "SELECT r FROM RpProcess r WHERE r.rpProcessType.id = 2 ORDER BY r.name"),
		@NamedQuery(name = "RpProcess.findAllProcessBusinessByCategory", query = "SELECT r FROM RpProcess r WHERE r.rpProcessType.id = 2 AND r.categoria LIKE :category ORDER BY r.name"),
		@NamedQuery(name = "RpProcess.findAllProcessBusinessByBO", query = "SELECT r FROM RpProcess r WHERE r.rpProcessType.id = 2 AND r.rpBusinessOwner.id LIKE :businessOwner"),
		@NamedQuery(name = "RpProcess.findAllProcessBusinessByCategoryBO", query = "SELECT r FROM RpProcess r WHERE r.rpProcessType.id = 2 AND r.categoria LIKE :category AND r.rpBusinessOwner.id LIKE :businessOwner"),
		@NamedQuery(name = "RpProcess.findDistinctBusinessOwner", query = "SELECT DISTINCT r.rpBusinessOwner.id FROM RpProcess r WHERE r.rpProcessType.id = 2 ORDER BY r.rpBusinessOwner.id"), })

public class RpProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPPROCESSPK_GENERATOR", sequenceName = "SEQ_RP_PROCESS", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPPROCESSPK_GENERATOR")
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "FK_ID_PROCESS_INPUT_TYPE", nullable = false)
	private RpProcessInputType rpProcessInputType;

	@ManyToOne
	@JoinColumn(name = "FK_ID_EXT_SYS", nullable = false)
	private RpExtSystem rpExtSystem;

	@ManyToOne
	@JoinColumn(name = "FK_ID_PROCESS_TYPE", nullable = false)
	private RpProcessType rpProcessType;

	@Lob
	@Column(name = "PROCESS_TEMPLATE")
	private byte[] processTemplate;

	@Column(name = "NUM_COLUMNS")
	private Integer numColumns;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "COLUMN_KEY")
	private String columnKey;

	@ManyToOne
	@JoinColumn(name = "BUSINESS_OWNER", nullable = false)
	private RpBusinessOwner rpBusinessOwner;

	@Column(name = "CATEGORIA")
	private String categoria;

	@Column(name = "SERVIZIO")
	private String servizio;

	@Column(name = "ID_EXT_SYS")
	private String idExtSys;

	@Column(name = "ID_PROCESS_MAIN")
	private long idProcessMain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE")
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "UNMANAGED")
	private boolean unmanaged;

	public RpProcess() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RpProcessInputType getRpProcessInputType() {
		return rpProcessInputType;
	}

	public String getIdExtSys() {
		return idExtSys;
	}

	public void setIdExtSys(String idExtSys) {
		this.idExtSys = idExtSys;
	}

	public void setRpProcessInputType(RpProcessInputType rpProcessInputType) {
		this.rpProcessInputType = rpProcessInputType;
	}

	public RpExtSystem getRpExtSystem() {
		return rpExtSystem;
	}

	public void setRpExtSystem(RpExtSystem rpExtSystem) {
		this.rpExtSystem = rpExtSystem;
	}

	public RpProcessType getRpProcessType() {
		return rpProcessType;
	}

	public void setRpProcessType(RpProcessType rpProcessType) {
		this.rpProcessType = rpProcessType;
	}

	public byte[] getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(byte[] processTemplate) {
		this.processTemplate = processTemplate;
	}

	public Integer getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(Integer numColumns) {
		this.numColumns = numColumns;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public RpBusinessOwner getRpBusinessOwner() {
		return rpBusinessOwner;
	}

	public void setRpBusinessOwner(RpBusinessOwner rpBusinessOwner) {
		this.rpBusinessOwner = rpBusinessOwner;
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

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIdProcessMain() {
		return idProcessMain;
	}

	public void setIdProcessMain(long idProcessMain) {
		this.idProcessMain = idProcessMain;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public boolean isUnmanaged() {
		return unmanaged;
	}

	public void setUnmanaged(boolean unmanaged) {
		this.unmanaged = unmanaged;
	}

}