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
import by.grsu.anikevich.comission.db.dao.impl.RoleDaoImpl;
import by.grsu.anikevich.comission.db.model.Role;
import by.grsu.anikevich.comission.web.dto.TableStateDto;




public class RoleServlet extends AbstractListServlet {
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

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
		int totalRoles = roleDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalRoles);
		List<Role> roles = roleDao.find(tableStateDto);

		List<Role> dtos = roles.stream().map((entity) -> {
			Role dto = new Role();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("role-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String roleIdStr = req.getParameter("id");
		Role dto = new Role();
		if (!Strings.isNullOrEmpty(roleIdStr)) {
			Integer roleId = Integer.parseInt(roleIdStr);
			Role entity = roleDao.getById(roleId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("role-edit.jsp").forward(req, res);
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Role faculty = new Role();
		String facultyIdStr = req.getParameter("id");

		faculty.setName(req.getParameter("name"));
		if (Strings.isNullOrEmpty(facultyIdStr)) {
			roleDao.insert(faculty);
		} else {
			faculty.setId(Integer.parseInt(facultyIdStr));
			roleDao.update(faculty);
		}
		res.sendRedirect("/role");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		roleDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
