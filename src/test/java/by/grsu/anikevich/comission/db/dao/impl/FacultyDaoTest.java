package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Faculty;

public class FacultyDaoTest extends AbstractTest {
	private static final IDao<Integer, Faculty> dao = FacultyDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Faculty entity = new Faculty();
		entity.setName("Inf");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Faculty entity = new Faculty();
		entity.setName("Inf");

		dao.insert(entity);

		String newName = "NEW_Candidate";
		entity.setName(newName);
		dao.update(entity);

		Faculty updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Faculty entity = new Faculty();
		entity.setName("Inf");
		dao.insert(entity);
		dao.delete(entity.getId());
		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Faculty entity = new Faculty();
		entity.setName("Candidate");
		dao.insert(entity);
		Faculty selectedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Faculty entity = new Faculty();
			entity.setName("Candidate" + i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

}
