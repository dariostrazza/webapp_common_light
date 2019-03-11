package it.rpa.services.registry.classes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.rpa.common.utilities.CommonMethods;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.common.utilities.ExcelCBean;
import it.rpa.registry.exception.classes.EJBRegistryException;
import it.rpa.services.common.classes.CommonUtility;
import it.rpa.services.interceptor.classes.ServiceInterceptor;
import it.rpa.services.registry.interfaces.Registry;

@Interceptors(ServiceInterceptor.class)
@WebServlet("/DataUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class DataRegistryUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int NUM_COL_DATA_REGISTRY_TEMPLATE = 3;
	private static final int COL_KEY_DATA_REGISTRY_TEMPLATE = 1;

	@EJB
	Registry registry;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataRegistryUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommonUtility.printHttpServletResponse(response);
		InputStream inputFile = null;
		InputStream inputStream = null;
		Part part = null;
		byte[] buffer = null;
		boolean result = false;
		ExcelCBean eCbean = new ExcelCBean();
		String fileError = "";

		try {
			// prendo il file dalla request
			part = request.getParts().iterator().next();
			inputStream = part.getInputStream();
			buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();

			// Analizzo il file per caricare i queue items
			inputFile = new ByteArrayInputStream(buffer);
			fileError = CommonMethods.readExcelFile(inputFile, eCbean, NUM_COL_DATA_REGISTRY_TEMPLATE,
					COL_KEY_DATA_REGISTRY_TEMPLATE);
			if (null != fileError && !fileError.isEmpty()) {
				response.setStatus(414);
				response.getWriter().write(fileError);
			} else {

				// richiamo EJB
				result = registry.insertDataFromExcel(eCbean);

				// Preparo la risposta
				if (result) {
					response.getWriter().write("File caricato correttamente");
					response.setStatus(200);
				} else {
					response.getWriter().write("Impossibile inserire il contenuto del file");
					response.setStatus(414);
				}

			}

		} catch (EJBRegistryException ejbRegistryException) {
			ControllerLogger.fatal(ejbRegistryException.getMessage(), "doPost");
			response.setStatus(414);
			response.getWriter().write(CommonMethods.ERROR_MESS);
		} finally {
			if (null != inputFile) {
				inputFile.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
		}

	}

}