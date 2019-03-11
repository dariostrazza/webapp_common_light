package it.rpa.services.registry.bean.classes;

import java.util.Date;

public class ProcessRegistry {

	private long processId;
	private long queueId;
	private String processName;
	private String processDescription;
	private Integer numCol;
	private String keyCol;
	private String businessOwner;
	private String category;
	private String service;
	private String queueName;
	private String queueDescription;
	private String keyFieldQueue;
	private String idExtSysQueue;
	private long processInputType;
	private String processType;
	private String processIdExtSys;
	private long queueProcessProducer;
	private long idProcessMain;
	private Date insertDate;
	private Date lastUpdateDate;
	private boolean unmanaged;

	public ProcessRegistry() {
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public long getQueueId() {
		return queueId;
	}

	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public Integer getNumCol() {
		return numCol;
	}

	public void setNumCol(Integer numCol) {
		this.numCol = numCol;
	}

	public String getKeyCol() {
		return keyCol;
	}

	public void setKeyCol(String keyCol) {
		this.keyCol = keyCol;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueDescription() {
		return queueDescription;
	}

	public void setQueueDescription(String queueDescription) {
		this.queueDescription = queueDescription;
	}

	public String getKeyFieldQueue() {
		return keyFieldQueue;
	}

	public void setKeyFieldQueue(String keyFieldQueue) {
		this.keyFieldQueue = keyFieldQueue;
	}

	public String getIdExtSysQueue() {
		return idExtSysQueue;
	}

	public void setIdExtSysQueue(String idExtSysQueue) {
		this.idExtSysQueue = idExtSysQueue;
	}

	public long getProcessInputType() {
		return processInputType;
	}

	public void setProcessInputType(long processInputType) {
		this.processInputType = processInputType;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getProcessIdExtSys() {
		return processIdExtSys;
	}

	public void setProcessIdExtSys(String processIdExtSys) {
		this.processIdExtSys = processIdExtSys;
	}

	public long getQueueProcessProducer() {
		return queueProcessProducer;
	}

	public void setQueueProcessProducer(long queueProcessProducer) {
		this.queueProcessProducer = queueProcessProducer;
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
