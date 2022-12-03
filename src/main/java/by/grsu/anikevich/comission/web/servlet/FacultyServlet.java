package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.FacultyDaoImpl;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.web.dto.TableStateDto;



public class FacultyServlet extends AbstractListServlet{
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;

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
		int totalFaculties = facultyDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalFaculties);

		List<Faculty> faculties = facultyDao.find(tableStateDto);

		List<Faculty> dtos = faculties.stream().map((entity) -> {
			Faculty dto = new Faculty();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("faculty-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String facultyIdStr = req.getParameter("id");
		Faculty dto = new Faculty();
		if (!Strings.isNullOrEmpty(facultyIdStr)) {
			Integer specialityId = Integer.parseInt(facultyIdStr);
			Faculty entity = facultyDao.getById(specialityId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("faculty-edit.jsp").forward(req, res);
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Faculty faculty = new Faculty();
		String facultyIdStr = req.getParameter("id");

		faculty.setName(req.getParameter("name"));
		if (Strings.isNullOrEmpty(facultyIdStr)) {
			facultyDao.insert(faculty);
		} else {
			faculty.setId(Integer.parseInt(facultyIdStr));
			facultyDao.update(faculty);
		}
		res.sendRedirect("/faculty");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		facultyDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
