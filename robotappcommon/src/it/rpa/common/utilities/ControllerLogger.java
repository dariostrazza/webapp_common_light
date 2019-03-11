package it.rpa.common.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

public class ControllerLogger {

	private static Log logger;

	static {
		ControllerLogger.logger = LogFactory.getLog("CONTROLLER");
		MDC.put("user", "System");
	}

	public static void start(final String message, final String function) {
		MDC.put("function", function);
		ControllerLogger.logger.info("START|" + message);
	}

	public static void info(final String message, final String function) {
		MDC.put("function", function);
		ControllerLogger.logger.info("INFO|" + message);
	}

	public static void end(final String message, final String function) {
		MDC.put("function", function);
		ControllerLogger.logger.info("END|" + message);
	}

	public static void warn(final String message, final String function) {
		MDC.put("function", function);
		ControllerLogger.logger.warn("WARNING|" + message);
	}

	public static void fatal(final String message, final String function) {
		MDC.put("function", function);
		ControllerLogger.logger.fatal("FATAL|" + message);
	}

}
