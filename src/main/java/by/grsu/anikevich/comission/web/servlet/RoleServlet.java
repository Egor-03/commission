package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.RoleDaoImpl;
import by.grsu.anikevich.comission.db.model.Role;

public class RoleServlet extends HttpServlet {
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer roleId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Role roleById = roleDao.getById(roleId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (roleById == null) {
			pw.println("no brand by id=" + roleId);
		} else {
			pw.println(roleById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			Role roleEntity = new Role();
			roleEntity.setName(paramName);
			roleDao.insert(roleEntity);
			pw.println("Saved:" + roleEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}
