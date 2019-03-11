package it.rpa.common.utilities;

import java.util.ArrayList;
import java.util.List;

public class ExcelCBean {
	private List<List<String>> table;

	public ExcelCBean() {
		table = new ArrayList<List<String>>();
	}

	public List<List<String>> getTable() {
		return table;
	}

	public void setTable(List<List<String>> table) {
		this.table = table;
	}

	public void addRow(List<String> row) {
		this.table.add(row);
	}

}
