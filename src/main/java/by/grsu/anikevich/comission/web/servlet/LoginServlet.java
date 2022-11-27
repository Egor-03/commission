package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;

import by.grsu.anikevich.comission.web.security.AuthenticationFilter;
import by.grsu.anikevich.comission.web.security.User;

public class LoginServlet extends HttpServlet {

	private static final List<User> USERS = new ArrayList<>();
	static {
		USERS.add(new User("admin", "password1", "Ivan", "Ivanov"));
		USERS.add(new User("manager", "password2", "Petr", "Petrov"));
		USERS.add(new User("blockeduser", "password3", "Alexander", "Alexandrod").setBlocked(true)); // no permissions
																										// to view the
																										// site
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");

		if (Strings.isNullOrEmpty(login) || Strings.isNullOrEmpty(password)) {
			res.sendError(401, "missing login or password");
			return;
		}

		Optional<User> userByLogin = USERS.stream().filter((user) -> {
			return login.equals(user.getEmail());
		}).findFirst();

		if (userByLogin.isEmpty()) {
			res.sendError(401, "login is incorrect");
			return;
		}

		User user = userByLogin.get();
		if (!password.equals(user.getPassword())) {
			res.sendError(401, "password is incorrect");
			return;
		}

		req.getSession(true).setAttribute(AuthenticationFilter.USER_ATTR_NAME, user);
		System.out.println("login executed by user: " + login);
		res.sendRedirect("/");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute(AuthenticationFilter.USER_ATTR_NAME);
			System.out.println("logout executed");
		}
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}
}
