package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Role;

public class RoleDaoTest extends AbstractTest {
	private static final IDao<Integer, Role> dao = RoleDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Role entity = new Role();
		entity.setName("Candidate");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Role entity = new Role();
		entity.setName("Candidate");

		dao.insert(entity);

		String newName = "NEW_Candidate";
		entity.setName(newName);
		dao.update(entity);

		Role updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Role entity = new Role();
		entity.setName("Candidate");
		dao.insert(entity);
		dao.delete(entity.getId());
		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Role entity = new Role();
		entity.setName("Candidate");
		dao.insert(entity);
		Role selectedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Role entity = new Role();
			entity.setName("Candidate" + i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

}
