package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the RP_QUEUE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_QUEUE", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpQueue.findAll", query = "SELECT r FROM RpQueue r"),
		@NamedQuery(name = "RpQueue.findIdByProcessCons", query = "SELECT r FROM RpQueue r WHERE r.rpProcessConsumer.id = :processConsumerId"), })
public class RpQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RPQUEUE_GENERATOR", sequenceName = "SEQ_RP_QUEUE", allocationSize = 1, schema = "WRPA_OWN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RPQUEUE_GENERATOR")
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "FK_ID_PROC_PROD", nullable = false)
	private RpProcess rpProcessProducer;

	@ManyToOne
	@JoinColumn(name = "FK_ID_PROC_CONS", nullable = false)
	private RpProcess rpProcessConsumer;

	@Column(name = "KEY_FIELD", nullable = false)
	private String keyField;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "ID_EXT_SYS", nullable = false)
	private String idExtSys;

	public RpQueue() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RpProcess getRpProcessProducer() {
		return rpProcessProducer;
	}

	public void setRpProcessProducer(RpProcess rpProcessProducer) {
		this.rpProcessProducer = rpProcessProducer;
	}

	public RpProcess getRpProcessConsumer() {
		return rpProcessConsumer;
	}

	public void setRpProcessConsumer(RpProcess rpProcessConsumer) {
		this.rpProcessConsumer = rpProcessConsumer;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdExtSys() {
		return idExtSys;
	}

	public void setIdExtSys(String idExtSys) {
		this.idExtSys = idExtSys;
	}

}