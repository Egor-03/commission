package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.db.model.Subject;

public class SpecialityDaoTest extends AbstractTest {
	private static final IDao<Integer, Speciality> specialityDao = SpecialityDaoImpl.INSTANCE;
	private static final IDao<Integer, Faculty> facultyDao = FacultyDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Speciality entity = new Speciality();
		entity.setFirstSubjectId(saveSubject("firstSubject").getId());
		entity.setSecondSubjectId(saveSubject("secondSubject").getId());
		entity.setThirdSubjectId(saveSubject("thirdSubject").getId());
		entity.setName("name");
		entity.setFacultyId(saveFaculty("faculty").getId());
		specialityDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Speciality entity = new Speciality();
		entity.setFirstSubjectId(saveSubject("firstSubject").getId());
		entity.setSecondSubjectId(saveSubject("secondSubject").getId());
		entity.setThirdSubjectId(saveSubject("thirdSubject").getId());
		entity.setName("name");
		entity.setFacultyId(saveFaculty("faculty").getId());
		specialityDao.insert(entity);

		Integer newfirstSubject = saveSubject("newfirstSubject").getId();
		Integer newSecondSubject = saveSubject("newSecondSubject").getId();
		Integer newThirdSubject = saveSubject("newThirdSubject").getId();
		entity.setFirstSubjectId(newfirstSubject);
		entity.setSecondSubjectId(newSecondSubject);
		entity.setThirdSubjectId(newThirdSubject);
		entity.setName("name");
		specialityDao.update(entity);

		Speciality updatedEntity = specialityDao.getById(entity.getId());
		Assertions.assertEquals(newfirstSubject, updatedEntity.getFirstSubjectId());
		Assertions.assertEquals(newSecondSubject, updatedEntity.getSecondSubjectId());
		Assertions.assertEquals(newThirdSubject, updatedEntity.getThirdSubjectId());
		Assertions.assertEquals("name", updatedEntity.getName());

	}

	@Test
	public void testDelete() {
		Speciality entity = new Speciality();
		entity.setFirstSubjectId(saveSubject("firstSubject").getId());
		entity.setSecondSubjectId(saveSubject("secondSubject").getId());
		entity.setThirdSubjectId(saveSubject("thirdSubject").getId());
		entity.setName("name");
		entity.setFacultyId(saveFaculty("faculty").getId());
		specialityDao.insert(entity);
		specialityDao.delete(entity.getId());
		Assertions.assertNull(specialityDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Speciality entity = new Speciality();
		Integer newfirstSubject = saveSubject("newfirstSubject").getId();
		Integer newSecondSubject = saveSubject("newSecondSubject").getId();
		Integer newThirdSubject = saveSubject("newThirdSubject").getId();
		entity.setFirstSubjectId(newfirstSubject);
		entity.setSecondSubjectId(newSecondSubject);
		entity.setThirdSubjectId(newThirdSubject);
		entity.setName("name");
		entity.setFacultyId(saveFaculty("faculty").getId());

		specialityDao.insert(entity);

		Speciality selectedEntity = specialityDao.getById(entity.getId());
		Assertions.assertEquals(newfirstSubject, selectedEntity.getFirstSubjectId());
		Assertions.assertEquals(newSecondSubject, selectedEntity.getSecondSubjectId());
		Assertions.assertEquals(newThirdSubject, selectedEntity.getThirdSubjectId());
		Assertions.assertEquals("name", selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Speciality entity = new Speciality();
			entity.setFirstSubjectId(saveSubject("firstSubject" + i).getId());
			entity.setSecondSubjectId(saveSubject("secondSubject" + i).getId());
			entity.setThirdSubjectId(saveSubject("thirdSubject" + i).getId());
			entity.setName("name" + i);
			entity.setFacultyId(saveFaculty("faculty" + i).getId());
			specialityDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, specialityDao.getAll().size());
	}

	private Subject saveSubject(String name) {
		Subject entity = new Subject();
		entity.setName(name);
		subjectDao.insert(entity);
		return entity;
	}

	private Faculty saveFaculty(String name) {
		Faculty entity = new Faculty();
		entity.setName(name);
		facultyDao.insert(entity);
		return entity;
	}

}
