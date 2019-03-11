package it.rpa.common.utilities;

import java.util.ArrayList;
import java.util.List;

public class ScheduleInfoBean {

	private String scheduleId;
	private String userId;
	private String processId;
	private int numColProcess;
	private List<Integer> keyColumnsProcess;
	private List<String> keyValues;
	private List<String> dataItems;

	public ScheduleInfoBean() {
		keyValues = new ArrayList<String>();
		dataItems = new ArrayList<String>();
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public int getNumColProcess() {
		return numColProcess;
	}

	public void setNumColProcess(int numColProcess) {
		this.numColProcess = numColProcess;
	}

	public List<Integer> getKeyColumnsProcess() {
		return keyColumnsProcess;
	}

	public void setKeyColumnsProcess(List<Integer> keyColumnsProcess) {
		this.keyColumnsProcess = keyColumnsProcess;
	}

	public List<String> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(List<String> keyValues) {
		this.keyValues = keyValues;
	}

	public List<String> getDataItems() {
		return dataItems;
	}

	public void setDataItems(List<String> dataItems) {
		this.dataItems = dataItems;
	}

	public void addQueueItem(String keyValue, String dataItem) {
		dataItems.add(dataItem);
		keyValues.add(keyValue);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
