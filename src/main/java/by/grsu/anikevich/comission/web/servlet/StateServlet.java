
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
import by.grsu.anikevich.comission.db.dao.impl.StateDaoImpl;
import by.grsu.anikevich.comission.db.model.State;
import by.grsu.anikevich.comission.web.dto.TableStateDto;




public class StateServlet extends AbstractListServlet {
	private static final IDao<Integer, State> stateDao = StateDaoImpl.INSTANCE;

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
		int totalStates = stateDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalStates);
		List<State> states = stateDao.find(tableStateDto);

		List<State> dtos = states.stream().map((entity) -> {
			State dto = new State();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("state-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String stateIdStr = req.getParameter("id");
		State dto = new State();
		if (!Strings.isNullOrEmpty(stateIdStr)) {
			Integer stateId = Integer.parseInt(stateIdStr);
			State entity = stateDao.getById(stateId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("state-edit.jsp").forward(req, res);
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		State state = new State();
		String stateIdStr = req.getParameter("id");

		state.setName(req.getParameter("name"));
		if (Strings.isNullOrEmpty(stateIdStr)) {
			stateDao.insert(state);
		} else {
			state.setId(Integer.parseInt(stateIdStr));
			stateDao.update(state);
		}
		res.sendRedirect("/state");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		stateDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
