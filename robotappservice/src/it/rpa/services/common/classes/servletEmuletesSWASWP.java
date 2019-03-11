package it.rpa.services.common.classes;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.rpa.common.exception.classes.EJBCommonException;
import it.rpa.common.utilities.ControllerLogger;
import it.rpa.services.common.bean.classes.ProfileCBean;
import it.rpa.services.common.interfaces.Profiling;
import it.rpa.services.interceptor.classes.ServiceInterceptor;

@Interceptors(ServiceInterceptor.class)
@WebServlet("emulateswa")
public class servletEmuletesSWASWP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	Profiling profiling;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletEmuletesSWASWP() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String urlDecRedirect = java.net.URLDecoder.decode(request.getParameter("url"), "UTF-8");

		// String profileResponse = "UPB"; String userIdentity = "U0H3537UPB"; String
		// enabligCode = "YADD75";
		// String profileResponse = "RD"; String userIdentity = "U0H3537RD";String
		// enabligCode = "YADD77";
		// String profileResponse = "AM"; String userIdentity = "U0H3537AM";String
		// enabligCode = "YADD76";
		// String profileResponse = "GOV"; String userIdentity = "U0H3537GOV";String
		// enabligCode = "YADD78";
		// String profileResponse = "SM"; String userIdentity = "U0H3537SM";String
		// enabligCode = "YADD79";

		String userIdentity = "U0H3537AM";
		String profileResponse = "null";
		String enabligCode = "null";
		Cookie cookieProfile = null;
		Cookie cookieUserId = null;
		Cookie cookieEnabligCode = null;
		ProfileCBean profileCBean = null;

		try {
			profileCBean = profiling.getProfileByUser(userIdentity);
			if (null != profileCBean) {
				profileResponse = profileCBean.getProfile();
				enabligCode = profileCBean.getEnablingCode();
			}
		} catch (EJBCommonException ejbCommonException) {
			ControllerLogger.fatal(ejbCommonException.getMessage(), "doGet");
		} finally {

			// Setto i Cookie per la profilazione nel client
			cookieProfile = new Cookie("profile", profileResponse);
			cookieProfile.setPath("/");
			cookieProfile.setMaxAge(60 * 60); // 1 ora
			response.addCookie(cookieProfile);

			cookieUserId = new Cookie("userid", userIdentity);
			cookieUserId.setPath("/");
			cookieUserId.setMaxAge(60 * 60); // 1 ora
			response.addCookie(cookieUserId);

			cookieEnabligCode = new Cookie("enablingcode", enabligCode);
			cookieEnabligCode.setPath("/");
			cookieEnabligCode.setMaxAge(60 * 60); // 1 ora
			response.addCookie(cookieEnabligCode);

			// Redirect alla pagina richiesta
			response.sendRedirect(urlDecRedirect);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}