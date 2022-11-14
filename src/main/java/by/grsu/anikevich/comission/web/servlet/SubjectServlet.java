package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.SubjectDaoImpl;
import by.grsu.anikevich.comission.db.model.Subject;


public class SubjectServlet extends HttpServlet {
	private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer subjectId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Subject subjectById = subjectDao.getById(subjectId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (subjectById == null) {
			pw.println("no brand by id=" + subjectId);
		} else {
			pw.println(subjectById.toString());
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
			Subject subjectEntity = new Subject();
			subjectEntity.setName(paramName);
			subjectDao.insert(subjectEntity);
			pw.println("Saved:" + subjectEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}
