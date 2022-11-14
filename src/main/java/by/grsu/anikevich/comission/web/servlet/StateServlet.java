package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.StateDaoImpl;
import by.grsu.anikevich.comission.db.model.State;

public class StateServlet extends HttpServlet {
	private static final IDao<Integer, State> stateDao = StateDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer stateId = Integer.parseInt(req.getParameter("id")); // read request parameter
		State stateById = stateDao.getById(stateId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (stateById == null) {
			pw.println("no brand by id=" + stateId);
		} else {
			pw.println(stateById.toString());
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
			State stateEntity = new State();
			stateEntity.setName(paramName);
			stateDao.insert(stateEntity);
			pw.println("Saved:" + stateEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}
