package com.w3m.api.ridemyspot;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RideMySpot_APIServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, rider");
	}
}
