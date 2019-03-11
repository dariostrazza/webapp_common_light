package it.rpa.common.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonMethods {

	public static final int RESPONSE_OK = 200;
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	public static final String ERROR_MESS = "Errore di connessione al Server";
	public static final String SUB_PROF_AM = "AM";

	public enum Profile {
		AM, RD, UPB;
	}


	public static String readExcelFile(InputStream inputFile, ExcelCBean eCbean, int numColumsTemplate, int columnkey) {

		String result = "";
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int numCol = 0;
		int numRow = 0;
		int i;
		Row row = null;
		FormulaEvaluator evaluator = null;
		DataFormatter dataFormatter = new DataFormatter();
		int index = 0;
		List<String> line = null;
		Cell column = null;
		String value = "";

		try {
			workbook = new XSSFWorkbook(inputFile);
			sheet = workbook.getSheetAt(0);
			numRow = sheet.getPhysicalNumberOfRows();
			row = sheet.getRow(0);
			numCol = row.getLastCellNum();
			evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			// Controllo se il file è valido per colonne e numero di righe
			if (numColumsTemplate != numCol || numRow <= 1) {
				result = "Formato file non valido. Numero colonne attese: " + numColumsTemplate
						+ ", colonne presenti nel file: " + numCol + ".";
				ControllerLogger.fatal("file con 1 riga o numero colonne non valido, numero di colonne attese "
						+ numColumsTemplate + ", individuate " + numCol + ". Righe: " + numRow,
						"readExcelFileDataRegistry");
				throw new MalformedInputException(numCol);
			}

			// analizzo tutte le righe dopo la riga di intestazione
			for (i = 1; i < numRow; i++) {
				line = new ArrayList<String>();
				row = sheet.getRow(i);

				for (index = 0; index < numColumsTemplate; index++) {
					column = row.getCell(index);
					evaluator.evaluate(column);
					if (null != column) {
						value = dataFormatter.formatCellValue(column, evaluator);
						value = value.trim();
					} else {
						// la cella non è valorizzata
						value = "";
					}
					// se la colonna chiave è vuota sono arrivato alla fine del file
					if (value.isEmpty() && columnkey == (index + 1)) {
						ControllerLogger.info("lettura file input numero righe=" + (i - 1), "readExcelFile");
						return "";
					}
					line.add(value);
				}
				eCbean.addRow(line);
			}

			// in caso di corretta esecuzione restituisco una stringa vuota
			result = "";

		} catch (MalformedInputException malformedInputException) {
			ControllerLogger.fatal(malformedInputException.getMessage(), "readExcelFile");
		} catch (IOException ioException) {
			ControllerLogger.fatal(ioException.getMessage(), "readExcelFile");
			result = "Formato file non valido";
		} finally {
			if (null != workbook) {
				try {
					workbook.close();
				} catch (IOException ioException) {
					ControllerLogger.fatal(ioException.getMessage(), "readExcelFile");
				}
			}
		}
		return result;
	}

}
