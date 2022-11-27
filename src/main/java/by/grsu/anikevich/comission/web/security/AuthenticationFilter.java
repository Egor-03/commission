package by.grsu.anikevich.comission.web.security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	public static final String USER_ATTR_NAME = "loggedUser";

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// skip filter logic if /login resource is requested
		if (!"/login".equals(req.getRequestURI())) {
			HttpSession session = req.getSession(false);

			if (session == null) {
				// example of Authentication error. We can return 401 here. But better to
				// redirect user to the Login page
				res.sendRedirect("login");
				return;
			}

			Object user = session.getAttribute(USER_ATTR_NAME);
			if (user == null || !(user instanceof User)) {
				// similar to above. session may exist on server and it can be linked to the
				// Browser (via Cookies!!!). But user didn't enter the valid credentials yet
				// therefore server doesn't know who is this user
				res.sendRedirect("login");
				return;
			}

			// example of Authorization error: user is known but access to the requested
			// resource is Forbidden (HTTP status 403)
			if (((User) user).isBlocked()) {
				res.sendError(403, "user is blocked");
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// close any resources here
	}
}
