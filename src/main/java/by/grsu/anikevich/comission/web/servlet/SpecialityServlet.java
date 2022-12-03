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
import by.grsu.anikevich.comission.db.dao.impl.SpecialityDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.SubjectDaoImpl;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.Subject;
import by.grsu.anikevich.comission.web.dto.SpecialityDto;
import by.grsu.anikevich.comission.web.dto.TableStateDto;

public class SpecialityServlet extends AbstractListServlet {
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;


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
		int totalSpecialities = specialityDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalSpecialities);


		if (Strings.isNullOrEmpty(parameter) ) {
			List<Speciality> specialities = specialityDao.find(tableStateDto);
			List<SpecialityDto> dtos = specialities.stream().map((entity) -> {
				SpecialityDto dto = new SpecialityDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				Faculty faculty = facultyDao.getById(entity.getFacultyId());
				dto.setFacultyName(faculty.getName());
				Subject firtsSubject = subjectDao.getById(entity.getFirstSubjectId());
				dto.setFirstSubjectName(firtsSubject.getName());
				Subject secondSubject = subjectDao.getById(entity.getSecondSubjectId());
				dto.setSecondSubjectName(secondSubject.getName());
				Subject thirdSubject = subjectDao.getById(entity.getThirdSubjectId());
				dto.setThirdSubjectName(thirdSubject.getName());
				return dto;
			}).collect(Collectors.toList());
			req.setAttribute("list", dtos);
			req.getRequestDispatcher("speciality-list.jsp").forward(req, res);
		} else {
			Integer facultyId = Integer.parseInt(parameter); // read request parameter

			List<Speciality> specialities = specialityDao.getAllwithId(facultyId);
			List<SpecialityDto> dtos = specialities.stream().map((entity) -> {
				SpecialityDto dto = new SpecialityDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				Faculty faculty = facultyDao.getById(entity.getFacultyId());
				dto.setFacultyName(faculty.getName());
				Subject firtsSubject = subjectDao.getById(entity.getFirstSubjectId());
				dto.setFirstSubjectName(firtsSubject.getName());
				Subject secondSubject = subjectDao.getById(entity.getSecondSubjectId());
				dto.setSecondSubjectName(secondSubject.getName());
				Subject thirdSubject = subjectDao.getById(entity.getThirdSubjectId());
				dto.setThirdSubjectName(thirdSubject.getName());
				return dto;
			}).collect(Collectors.toList());
			req.setAttribute("list", dtos);
			req.getRequestDispatcher("speciality-list.jsp").forward(req, res);
		}

	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String specialityIdStr = req.getParameter("id");
		SpecialityDto dto = new SpecialityDto();
		if (!Strings.isNullOrEmpty(specialityIdStr)) {
			Integer specialityId = Integer.parseInt(specialityIdStr);
			Speciality entity = specialityDao.getById(specialityId);
			dto.setId(entity.getId());
			dto.setFacultyId(entity.getFacultyId());
			dto.setFirstSubjectId(entity.getFirstSubjectId());
			dto.setSecondSubjectId(entity.getSecondSubjectId());
			dto.setThirdSubjectId(entity.getThirdSubjectId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allSubjects", getAllSubjectsDtos());
		req.setAttribute("allFaculties", getAllFacultyDtos());
		req.getRequestDispatcher("speciality-edit.jsp").forward(req, res);
	}

	private List<Subject> getAllSubjectsDtos() {
		return subjectDao.getAll().stream().map((entity) -> {
			Subject dto = new Subject();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}
	private List<Faculty> getAllFacultyDtos() {
		return facultyDao.getAll().stream().map((entity) -> {
			Faculty dto = new Faculty();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}



	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Speciality speciality = new Speciality();
		String specialityIdStr = req.getParameter("id");
		String firstSubjectId = req.getParameter("firstSubjectId");
		String secondSubjectId = req.getParameter("secondSubjectId");
		String thirdSubjectId = req.getParameter("thirdSubjectId");
		String facultyIdStr = req.getParameter("facultyId");

		speciality.setName(req.getParameter("name"));
		speciality.setFacultyId(facultyIdStr== null ? null: Integer.parseInt(facultyIdStr));
		speciality.setFirstSubjectId(firstSubjectId== null ? null: Integer.parseInt(firstSubjectId));
		speciality.setSecondSubjectId(secondSubjectId== null ? null: Integer.parseInt(secondSubjectId));
		speciality.setThirdSubjectId(thirdSubjectId== null ? null: Integer.parseInt(thirdSubjectId));

		if (Strings.isNullOrEmpty(specialityIdStr)) {
			specialityDao.insert(speciality);
		} else {
			speciality.setId(Integer.parseInt(specialityIdStr));
			specialityDao.update(speciality);
		}
		res.sendRedirect("/speciality");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		specialityDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
