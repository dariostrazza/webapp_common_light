package it.rpa.common.utilities;

import java.io.Serializable;
import java.util.Date;

public class MapCBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long rpaProcess;
	private String mapCode;
	private String applicationCode;
	private String processName;
	private String mapDescription;
	private boolean mapFlagCore;
	private String attachLink;
	private Date insertDate;
	private Date lastUpdateDate;
	private String codeForAccess;
	private long anomalyCount;
	private boolean deleteFlag;
	private String note;
	private String object;
	private String element;

	public MapCBean() {

	}

	public long getRpaProcess() {
		return rpaProcess;
	}

	public void setRpaProcess(long rpaProcess) {
		this.rpaProcess = rpaProcess;
	}

	public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getMapDescription() {
		return mapDescription;
	}

	public void setMapDescription(String mapDescription) {
		this.mapDescription = mapDescription;
	}

	public boolean isMapFlagCore() {
		return mapFlagCore;
	}

	public void setMapFlagCore(boolean mapFlagCore) {
		this.mapFlagCore = mapFlagCore;
	}

	public String getAttachLink() {
		return attachLink;
	}

	public void setAttachLink(String attachLink) {
		this.attachLink = attachLink;
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

	public String getCodeForAccess() {
		return codeForAccess;
	}

	public void setCodeForAccess(String codeForAccess) {
		this.codeForAccess = codeForAccess;
	}

	public long getAnomalyCount() {
		return anomalyCount;
	}

	public void setAnomalyCount(long anomalyCount) {
		this.anomalyCount = anomalyCount;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
