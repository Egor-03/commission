
package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.SubjectDaoImpl;
import by.grsu.anikevich.comission.db.model.Subject;
import by.grsu.anikevich.comission.web.dto.TableStateDto;




public class SubjectServlet extends AbstractListServlet {
	private static final IDao<Integer,  Subject> subjectDao =  SubjectDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int totalSubjects = subjectDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalSubjects);

		List<Subject> subjects = subjectDao.find(tableStateDto);

		List<Subject> dtos = subjects.stream().map((entity) -> {
			Subject dto = new Subject();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("subject-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String subjectIdStr = req.getParameter("id");
		Subject dto = new Subject();
		if (!Strings.isNullOrEmpty(subjectIdStr)) {
			Integer subjectId = Integer.parseInt(subjectIdStr);
			Subject entity = subjectDao.getById(subjectId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("subject-edit.jsp").forward(req, res);
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Subject subject = new Subject();
		String subjectIdStr = req.getParameter("id");

		subject.setName(req.getParameter("name"));
		if (Strings.isNullOrEmpty(subjectIdStr)) {
			subjectDao.insert(subject);
		} else {
			subject.setId(Integer.parseInt(subjectIdStr));
			subjectDao.update(subject);
		}
		res.sendRedirect("/subject");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		subjectDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
