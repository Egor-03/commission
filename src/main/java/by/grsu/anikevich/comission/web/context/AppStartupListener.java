package by.grsu.anikevich.comission.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.dao.impl.FacultyDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.PersoneDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.RequestDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.RoleDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.SpecialityDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.StateDaoImpl;
import by.grsu.anikevich.comission.db.dao.impl.SubjectDaoImpl;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Request;
import by.grsu.anikevich.comission.db.model.Role;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.State;
import by.grsu.anikevich.comission.db.model.Subject;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, State> stateDao = StateDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Faculty facultyEntity = new Faculty();
		facultyEntity.setName("facultyname");
		facultyDao.insert(facultyEntity);
		System.out.println("created: " + facultyEntity);

		Subject subjectEntity = new Subject();
		subjectEntity.setName("subjectname");
		subjectDao.insert(subjectEntity);
		System.out.println("created: " + subjectEntity);

		State stateEntity = new State();
		stateEntity.setName("statename");
		stateDao.insert(stateEntity);
		System.out.println("created: " + stateEntity);

		Role roleEntity = new Role();
		roleEntity.setName("rolename");
		roleDao.insert(roleEntity);
		System.out.println("created: " + roleEntity);


		Persone personeEntity = new Persone();
		personeEntity.setRoleId(roleEntity.getId());
		personeEntity.setFirstName("first_name");
		personeEntity.setSecondName("second_name");
		personeEntity.setPatronymic("patronymic");
		personeEntity.setMail("mail");
		personeDao.insert(personeEntity);
		System.out.println("created: " + personeEntity);

		Speciality specialityEntity = new Speciality();

		specialityEntity.setFirstSubjectId(subjectEntity.getId());
		specialityEntity.setSecondSubjectId(subjectEntity.getId());
		specialityEntity.setThirdSubjectId(subjectEntity.getId());
		specialityEntity.setName("spesialityname");
		specialityEntity.setFacultyId(facultyEntity.getId());
		specialityDao.insert(specialityEntity);
		System.out.println("created: " + specialityEntity);



		Request requestEntity = new Request();
		requestEntity.setPersonId(personeEntity.getId());
		requestEntity.setSpecialityId(specialityEntity.getId());
		requestEntity.setStateId(stateEntity.getId());
		requestDao.insert(requestEntity);
		System.out.println("created: " + requestEntity);
		System.out.println("initial data created");
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}
