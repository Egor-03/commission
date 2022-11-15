package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.FacultyDaoImpl;
import by.grsu.anikevich.comission.db.model.Faculty;


public class FacultyServletBackup extends HttpServlet {
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer facultyId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Faculty facultyById = facultyDao.getById(facultyId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (facultyById == null) {
			pw.println("no brand by id=" + facultyId);
		} else {
			pw.println(facultyById.toString());
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
			Faculty facultyEntity = new Faculty();
			facultyEntity.setName(paramName);
			facultyDao.insert(facultyEntity);
			pw.println("Saved:" + facultyEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}

