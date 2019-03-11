package it.rpa.services.common.classes;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.DataCBean;
import it.rpa.services.common.bean.classes.KeyValueCBean;
import it.rpa.services.common.bean.classes.ProcessCBean;
import it.rpa.services.registry.bean.classes.EnablingCodeCBean;
import it.rpa.services.registry.bean.classes.ProcessRegistry;

public class CommonUtility {

	public static String printCollectionString(Collection<String> entries) {
		JsonArrayBuilder collectionBuilder = Json.createArrayBuilder();
		JsonArrayBuilder collectionRowBuilder = null;
		for (String string : entries) {
			collectionRowBuilder = Json.createArrayBuilder();
			collectionRowBuilder.add(string);
			collectionBuilder.add(collectionRowBuilder);
		}
		return collectionBuilder.build().toString();
	}
	
	public static String printResultProcesses(Collection<ProcessCBean> processes) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		bldResult.append("[");
		for (ProcessCBean item : processes) {
			bldResult.append("[");
			bldResult.append("\"");
			bldResult.append(item.getProcessId());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessName());
			bldResult.append("\"],");
		}
		if(bldResult.length()>1){
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		}else{
			return "[]";
		}
	}


	public static String printResultKeyValues(Collection<KeyValueCBean> keyValueCBeans) {

		JsonArrayBuilder keyValuesBuilder = Json.createArrayBuilder();
		JsonArrayBuilder rowBuilder = null;

		for (KeyValueCBean item : keyValueCBeans) {
			rowBuilder = Json.createArrayBuilder();
			rowBuilder.add(item.getKey());
			rowBuilder.add(item.getValue());
			keyValuesBuilder.add(rowBuilder);
		}
		return keyValuesBuilder.build().toString();
	}

	public static String printResultEnablingCodes(Collection<EnablingCodeCBean> enablingCode) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		bldResult.append("[");
		for (EnablingCodeCBean item : enablingCode) {
			bldResult.append("[");
			bldResult.append("\"");
			bldResult.append(item.getCode());
			bldResult.append("\",\"");
			bldResult.append(item.getCodeDescription());
			bldResult.append("\",\"");
			bldResult.append(item.getProfile());
			bldResult.append("\",\"");
			bldResult.append(item.getProfileDescription());
			bldResult.append("\",\"");
			bldResult.append(item.getBusinessOwner());
			bldResult.append("\"],");
		}
		if (bldResult.length() > 1) {
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		} else {
			return "[]";
		}
	}

	public static Response printResponse(String result, int statusCode) {
		Response response = Response.status(statusCode).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600").entity(result).build();

		return response;
	}

	public static Response printResponse(String result) {
		return printResponse(result, 414);
	}

	public static void printHttpServletResponse(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}

	public static String printResultOptionList(Collection<String> values) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		bldResult.append("[");
		for (String item : values) {
			bldResult.append("\"");
			bldResult.append(item);
			bldResult.append("\",");
		}
		if (bldResult.length() > 1) {
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		} else {
			return "[]";
		}
	}

	public static String printResultData(Collection<DataCBean> dataCBean) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		bldResult.append("[");
		for (DataCBean item : dataCBean) {
			bldResult.append("[");
			bldResult.append("\"");
			bldResult.append(item.getCode());
			bldResult.append("\",\"");
			bldResult.append(item.getDescription());
			bldResult.append("\",\"");
			bldResult.append(item.getType());
			bldResult.append("\"],");
		}
		if (bldResult.length() > 1) {
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		} else {
			return "[]";
		}

	}

	public static String printProcessesRegistry(Collection<ProcessRegistry> processRegistry) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		SimpleDateFormat date = new SimpleDateFormat(CommonMethods.DATE_TIME_FORMAT);
		bldResult.append("[");
		for (ProcessRegistry item : processRegistry) {
			bldResult.append("[");
			bldResult.append("\"");
			bldResult.append(item.getProcessId());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessName());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessDescription());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessType());
			bldResult.append("\",\"");
			bldResult.append(item.getBusinessOwner());
			bldResult.append("\",\"");
			bldResult.append(item.getService());
			bldResult.append("\",\"");
			bldResult.append(item.getCategory());
			bldResult.append("\",\"");
			bldResult.append(item.getIdProcessMain());
			bldResult.append("\",\"");
			bldResult.append(date.format(item.getInsertDate()));
			bldResult.append("\",\"");
			bldResult.append(date.format(item.getLastUpdateDate()));
			bldResult.append("\",\"");
			// verifico se è un processo di business
			if (item.getProcessType().equals("Processo Business")) {
				bldResult.append(item.getProcessIdExtSys());
				bldResult.append("\",\"");
				if (null != item.getNumCol()) {
					bldResult.append(item.getNumCol());
				} else {
					bldResult.append("");
				}
				bldResult.append("\",\"");
				if (null != item.getKeyCol()) {
					bldResult.append(item.getKeyCol());
				} else {
					bldResult.append("");
				}
				bldResult.append("\",\"");
				bldResult.append(item.getProcessInputType());
				bldResult.append("\",\"");
				bldResult.append(item.getIdExtSysQueue());
				bldResult.append("\",\"");
				bldResult.append(item.getQueueId());
				bldResult.append("\",\"");
				bldResult.append(item.getQueueName());
				bldResult.append("\",\"");
				bldResult.append(item.getQueueDescription());
				bldResult.append("\",\"");
				bldResult.append(item.getKeyFieldQueue());
				bldResult.append("\",\"");
				bldResult.append(item.getQueueProcessProducer());
				bldResult.append("\",\"");
				if (item.isUnmanaged()) {
					bldResult.append("si");
				} else {
					bldResult.append("no");
				}
			} else {
				// è un processo di governo
				bldResult.append(item.getProcessIdExtSys());
				bldResult.append("\",\"");
				// non ho i campi del processo di business
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
				bldResult.append("\",\"");
				bldResult.append("-");
			}
			bldResult.append("\"],");
		}
		if (bldResult.length() > 1) {
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		} else {
			return "[]";
		}
	}

	public static String printProcessesGovernance(Collection<ProcessRegistry> processes) {
		StringBuilder bldResult = new StringBuilder();
		StringBuilder bldReturn = new StringBuilder();
		SimpleDateFormat date = new SimpleDateFormat(CommonMethods.DATE_TIME_FORMAT);
		bldResult.append("[");
		for (ProcessRegistry item : processes) {
			bldResult.append("[");
			bldResult.append("\"");
			bldResult.append(item.getProcessId());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessName());
			bldResult.append("\",\"");
			bldResult.append(item.getIdProcessMain());
			bldResult.append("\",\"");
			bldResult.append(item.getProcessDescription());
			bldResult.append("\",\"");
			bldResult.append(item.getBusinessOwner());
			bldResult.append("\",\"");
			bldResult.append(item.getService());
			bldResult.append("\",\"");
			bldResult.append(item.getCategory());
			bldResult.append("\",\"");
			bldResult.append(date.format(item.getInsertDate()));
			bldResult.append("\",\"");
			bldResult.append(date.format(item.getLastUpdateDate()));
			bldResult.append("\"],");
		}
		if (bldResult.length() > 1) {
			bldReturn.append(bldResult.substring(0, bldResult.length() - 1));
			bldReturn.append("]");
			return bldReturn.toString();
		} else {
			return "[]";
		}

	}


}
