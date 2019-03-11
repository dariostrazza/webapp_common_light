package it.rpa.ejb.interceptor.classes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import it.rpa.common.utilities.ControllerLogger;

public class ControllerInterceptor {

	private final String LOGGER_FUNCTION_STRING = "CONTROLLER"; //$NON-NLS-1$

	@AroundInvoke
	public Object interceptMethodCall(final InvocationContext ctx) throws Exception {
		Object returnObject = null;
		try {
			ControllerLogger.info("START |" //$NON-NLS-1$
					+ ctx.getMethod().getName(), LOGGER_FUNCTION_STRING);
			returnObject = ctx.proceed();
			ControllerLogger.info("END |" //$NON-NLS-1$
					+ ctx.getMethod().getName(), LOGGER_FUNCTION_STRING);
			return returnObject;
		} catch (final Exception e) {
			ControllerLogger.fatal(e.getMessage(), LOGGER_FUNCTION_STRING);
			throw e;
		}

	}
}
