package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Request;
import by.grsu.anikevich.comission.db.model.Role;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.State;
import by.grsu.anikevich.comission.db.model.Subject;

public class RequestDaoTest extends AbstractTest {
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
	private static final IDao<Integer, State> stateDao = StateDaoImpl.INSTANCE;
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> firstSubjectDao = SubjectDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> secondSubjectDao = SubjectDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> thirdSubjectDao = SubjectDaoImpl.INSTANCE;
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Request entity = new Request();
		entity.setPersonId(savePersone("first_name", "second_name", "patronymic", "mail", "candidate").getId());
		entity.setSpecialityId(saveSpeciality("math", "rus", "fiz", "Math and Inf", "KB").getId());
		entity.setStateId(saveState("looking").getId());
		requestDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Request entity = new Request();
		entity.setPersonId(savePersone("first_name", "second_name", "patronymic", "mail", "candidate").getId());
		entity.setSpecialityId(saveSpeciality("math", "rus", "fiz", "Math and Inf", "KB").getId());
		entity.setStateId(saveState("looking").getId());
		requestDao.insert(entity);

		Integer newPersonId = savePersone("newfirst_name", "newsecond_name", "patronymic", "email", "administrator")
				.getId();
		Integer newSpecialityId = saveSpeciality("math", "rus", "fiz", "Math and Inf", "Poit").getId();
		Integer newStateId = saveState("apply").getId();
		entity.setPersonId(newPersonId);
		entity.setSpecialityId(newSpecialityId);
		entity.setStateId(newStateId);
		requestDao.update(entity);

		Request updatedEntity = requestDao.getById(entity.getId());
		Assertions.assertEquals(newPersonId, updatedEntity.getPersonId());
		Assertions.assertEquals(newSpecialityId, updatedEntity.getSpecialityId());
		Assertions.assertEquals(newStateId, updatedEntity.getStateId());

	}

	@Test
	public void testDelete() {
		Request entity = new Request();
		entity.setPersonId(savePersone("first_name", "second_name", "patronymic", "mail", "candidate").getId());
		entity.setSpecialityId(saveSpeciality("math", "rus", "fiz", "Math and Inf", "KB").getId());
		entity.setStateId(saveState("looking").getId());
		requestDao.insert(entity);
		requestDao.delete(entity.getId());
		Assertions.assertNull(requestDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Request entity = new Request();
		Integer newPersonId = savePersone("newfirst_name", "newsecond_name", "patronymic", "email", "administrator")
				.getId();
		Integer newSpecialityId = saveSpeciality("math", "rus", "fiz", "Math and Inf", "Poit").getId();
		Integer newStateId = saveState("apply").getId();
		entity.setPersonId(newPersonId);
		entity.setSpecialityId(newSpecialityId);
		entity.setStateId(newStateId);

		requestDao.insert(entity);

		Request selectedEntity = requestDao.getById(entity.getId());
		Assertions.assertEquals(newPersonId, selectedEntity.getPersonId());
		Assertions.assertEquals(newSpecialityId, selectedEntity.getSpecialityId());
		Assertions.assertEquals(newStateId, selectedEntity.getStateId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Request entity = new Request();
			entity.setPersonId(
					savePersone("first_name" + i, "second_name" + i, "patronymic" + i, "mail" + i, "candidate" + i)
							.getId());
			entity.setSpecialityId(
					saveSpeciality("math" + i, "rus" + i, "fiz" + i, "Math and Inf" + i, "KB" + i).getId());
			entity.setStateId(saveState("looking" + i).getId());
			requestDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, requestDao.getAll().size());
	}

	private Persone savePersone(String persone_firstName, String persone_secondName, String persone_patronymic,
			String mail, String role) {
		Role roleEntity = new Role();
		roleEntity.setName(role);
		roleDao.insert(roleEntity);

		Persone personeEntity = new Persone();
		personeEntity.setFirstName(persone_firstName);
		personeEntity.setSecondName(persone_secondName);
		personeEntity.setPatronymic(persone_patronymic);
		personeEntity.setMail(mail);
		personeEntity.setRoleId(roleEntity.getId());
		personeDao.insert(personeEntity);
		return personeEntity;
	}

	private State saveState(String name) {
		State entity = new State();
		entity.setName(name);
		stateDao.insert(entity);
		return entity;
	}

	private Speciality saveSpeciality(String firstSubject, String secondSubject, String thirdSubject,
			String facultyName, String specialityName) {
		Subject firstSubjectEntity = new Subject();
		Subject secondSubjectEntity = new Subject();
		Subject thirdSubjectEntity = new Subject();

		firstSubjectEntity.setName(firstSubject);
		secondSubjectEntity.setName(secondSubject);
		thirdSubjectEntity.setName(thirdSubject);
		firstSubjectDao.insert(firstSubjectEntity);
		secondSubjectDao.insert(secondSubjectEntity);
		thirdSubjectDao.insert(thirdSubjectEntity);

		Faculty facultyEntity = new Faculty();
		facultyEntity.setName(facultyName);
		facultyDao.insert(facultyEntity);

		Speciality specialityEntity = new Speciality();
		specialityEntity.setFirstSubjectId(firstSubjectEntity.getId());
		specialityEntity.setSecondSubjectId(secondSubjectEntity.getId());
		specialityEntity.setThirdSubjectId(thirdSubjectEntity.getId());
		specialityEntity.setName(specialityName);
		specialityEntity.setFacultyId(facultyEntity.getId());
		specialityDao.insert(specialityEntity);
		return specialityEntity;

	}

}
