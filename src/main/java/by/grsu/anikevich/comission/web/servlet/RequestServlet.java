package by.grsu.anikevich.comission.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.FacultyDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.PersoneDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.RequestDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.SpecialityDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.StateDaoImpl;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Request;
import by.grsu.anikevich.comission.db.model.Role;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.State;
import by.grsu.anikevich.comission.db.model.Subject;
import by.grsu.anikevich.comission.web.dto.RequestDto;
import by.grsu.anikevich.comission.web.dto.SpecialityDto;



public class RequestServlet extends HttpServlet {
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
	private static final IDao<Integer, State> stateDao = StateDaoImpl.INSTANCE;
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
		String parameter = req.getParameter("facultyId");
		String specialityId = req.getParameter("specialityId");
		

		if (!Strings.isNullOrEmpty(parameter) ) {
			
			List<Speciality> specialities = specialityDao.getAllwithId(Integer.parseInt(parameter));
			List<Request> requests= new ArrayList<>();
			for(Integer i=0;i<specialities.size();i++)
			{
				List<Request> tmp= new ArrayList<>();
				tmp= requestDao.getAllwithId(specialities.get(i).getId());
				for(Integer j=0;j<tmp.size();j++)
				{
					requests.add(tmp.get(j));
				}
			}
			List<RequestDto> dtos = requests.stream().map((entity) -> {
				RequestDto dto = new RequestDto();
				dto.setId(entity.getId());
				Speciality speciality = specialityDao.getById(entity.getSpecialityId());
				dto.setSpecialityName(speciality.getName());
				Faculty faculty = new Faculty();
				faculty = facultyDao.getById(speciality.getFacultyId());
				dto.setFacultyName(faculty.getName());
				Persone persone = personeDao.getById(entity.getPersonId());
				dto.setPersoneName(persone.getFirstName()+" "+ persone.getSecondName());
				State state = stateDao.getById(entity.getStateId());
				dto.setStateName(state.getName());
				return dto;
			}).collect(Collectors.toList());
			req.setAttribute("list", dtos);
			req.getRequestDispatcher("request-list.jsp").forward(req, res);
			
		} else {
			if (Strings.isNullOrEmpty(specialityId) ) {
				List<Request> requests = requestDao.getAll(); 
				List<RequestDto> dtos = requests.stream().map((entity) -> {
					RequestDto dto = new RequestDto();
					dto.setId(entity.getId());
					Speciality speciality = specialityDao.getById(entity.getSpecialityId());
					dto.setSpecialityName(speciality.getName());
					Faculty faculty = new Faculty();
					faculty = facultyDao.getById(speciality.getFacultyId());
					dto.setFacultyName(faculty.getName());
					Persone persone = personeDao.getById(entity.getPersonId());
					dto.setPersoneName(persone.getFirstName()+" "+ persone.getSecondName());
					State state = stateDao.getById(entity.getStateId());
					dto.setStateName(state.getName());
					return dto;
				}).collect(Collectors.toList());
				req.setAttribute("list", dtos);
				req.getRequestDispatcher("request-list.jsp").forward(req, res);
				
			}else {
			
			Integer specialityI = Integer.parseInt(specialityId); // read request parameter
			List<Request> requests = requestDao.getAllwithId(specialityI); 
			List<RequestDto> dtos = requests.stream().map((entity) -> {
				RequestDto dto = new RequestDto();
				dto.setId(entity.getId());
				Speciality speciality = specialityDao.getById(entity.getSpecialityId());
				dto.setSpecialityName(speciality.getName());
				Faculty faculty = new Faculty();
				faculty = facultyDao.getById(speciality.getFacultyId());
				dto.setFacultyName(faculty.getName());
				Persone persone = personeDao.getById(entity.getPersonId());
				dto.setPersoneName(persone.getFirstName()+" "+ persone.getSecondName());
				State state = stateDao.getById(entity.getStateId());
				dto.setStateName(state.getName());
				return dto;
			}).collect(Collectors.toList());

			req.setAttribute("list", dtos);
			req.getRequestDispatcher("request-list.jsp").forward(req, res);
		}
			}
		
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
		req.setAttribute("allPersons", getAllPersoneDtos());
		req.setAttribute("allSpecialites", getAllSpecialitesDtos());
		req.setAttribute("allStates", getAllStatesDtos());
		req.getRequestDispatcher("request-edit.jsp").forward(req, res);
	}
	private List<Persone> getAllPersoneDtos() {
		return personeDao.getAll().stream().map((entity) -> {
			Persone dto = new Persone();
			dto.setId(entity.getId());
			dto.setFirstName(entity.getFirstName());
			dto.setSecondName(entity.getSecondName());
			return dto;
		}).collect(Collectors.toList());
	}
	private List<Speciality> getAllSpecialitesDtos() {
		return specialityDao.getAll().stream().map((entity) -> {
			Speciality dto = new Speciality();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			
			return dto;
		}).collect(Collectors.toList());
	}
	private List<State> getAllStatesDtos() {
		return stateDao.getAll().stream().map((entity) -> {
			State dto = new State();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			
			return dto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Request request = new Request();
		String requestIdStr = req.getParameter("id");
		String personeIdStr = req.getParameter("personId");
		String specialityIdstr = req.getParameter("specialityId");
		String stateIdStr = req.getParameter("stateId");
		
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
