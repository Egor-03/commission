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
import by.grsu.anikevich.comission.db.dao.impl.PersoneDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.RequestDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.SpecialityDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.StateDaoImpl;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Request;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.State;
import by.grsu.anikevich.comission.web.dto.RequestDto;



public class RequestServlet extends HttpServlet {
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
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
		List<Request> requests = requestDao.getAll(); 

		List<RequestDto> dtos = requests.stream().map((entity) -> {
			RequestDto dto = new RequestDto();
			dto.setId(entity.getId());
			Speciality speciality = specialityDao.getById(entity.getSpecialityId());
			dto.setSpecialityName(speciality.getName());
			Persone persone = personeDao.getById(entity.getPersonId());
			dto.setPersoneName(persone.getFirstName()+" "+ persone.getSecondName());
			State state = stateDao.getById(entity.getStateId());
			dto.setStateName(state.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("request-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestIdStr = req.getParameter("id");
		RequestDto dto = new RequestDto();
		if (!Strings.isNullOrEmpty(requestIdStr)) {
			Integer requestId = Integer.parseInt(requestIdStr);
			Request entity = requestDao.getById(requestId);
			dto.setPersonId(entity.getPersonId());
			dto.setSpecialityId(entity.getSpecialityId());
			dto.setStateId(entity.getStateId());
			dto.setId(entity.getId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("request-edit.jsp").forward(req, res);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Request request = new Request();
		String requestIdStr = req.getParameter("id");
		String personeIdStr = req.getParameter("person_id");
		String specialityIdstr = req.getParameter("speciality_id");
		String stateIdStr = req.getParameter("state_id");
		
		request.setPersonId(personeIdStr== null ? null: Integer.parseInt(personeIdStr));
		request.setSpecialityId(specialityIdstr== null ? null: Integer.parseInt(specialityIdstr));
		request.setStateId(stateIdStr== null ? null: Integer.parseInt(stateIdStr));
		
		if (Strings.isNullOrEmpty(requestIdStr)) {
			requestDao.insert(request);
		} else {
			request.setId(Integer.parseInt(requestIdStr));
			requestDao.update(request);
		}
		res.sendRedirect("/request");
	}
	
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		requestDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
