package it.rpa.services.common.bean.classes;

import it.rpa.services.common.bean.classes.KeyValueCBean;

import java.util.List;

public class ProcessCBean {
	
	private String processName;
	private String processId;
	private String modality;
	private List<Integer> columnKey;
	private int columnNum;
	private boolean unmanaged;
	private List<KeyValueCBean> modalities;

	public ProcessCBean() {

	}

	public List<Integer> getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(List<Integer> columnKey) {
		this.columnKey = columnKey;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public boolean isUnmanaged() {
		return unmanaged;
	}

	public void setUnmanaged(boolean unmanaged) {
		this.unmanaged = unmanaged;
	}

	public List<KeyValueCBean> getModalities() {
		return modalities;
	}

	public void setModalities(List<KeyValueCBean> modalities) {
		this.modalities = modalities;
	}
}

