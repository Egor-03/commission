package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.State;


public class StateDaoTest extends AbstractTest {
	private static final IDao<Integer, State> dao = StateDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		State entity = new State();
		entity.setName("st");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		State entity = new State();
		entity.setName("Candidate");

		dao.insert(entity);

		String newName = "new_st";
		entity.setName(newName);
		dao.update(entity);

		State updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		State entity = new State();
		entity.setName("st");
		dao.insert(entity);
		dao.delete(entity.getId());
		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		State entity = new State();
		entity.setName("st");
		dao.insert(entity);
		State selectedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			State entity = new State();
			entity.setName("st" + i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

}
