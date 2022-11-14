package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.PersoneDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.RoleDaoImpl;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Role;

public class PersoneServlet extends HttpServlet {
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
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
		List<Persone> persones = personeDao.getAll(); // get data

		List<ModelDto> dtos = models.stream().map((entity) -> {
			ModelDto dto = new ModelDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setActual(entity.getActual());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			Brand brand = brandDao.getById(entity.getBrandId());
			dto.setBrandName(brand.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("model-list.jsp").forward(req, res);
	}

}
