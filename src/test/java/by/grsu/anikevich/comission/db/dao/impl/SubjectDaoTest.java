package by.grsu.anikevich.comission.db.dao.impl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Subject;

public class SubjectDaoTest  extends AbstractTest {
	private static final IDao<Integer, Subject> dao = SubjectDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Subject entity = new Subject();
		entity.setName("sb");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Subject entity = new Subject();
		entity.setName("sb");

		dao.insert(entity);

		String newName = "NEW_sb";
		entity.setName(newName);
		dao.update(entity);

		Subject updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Subject entity = new Subject();
		entity.setName("sb");
		dao.insert(entity);
		dao.delete(entity.getId());
		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Subject entity = new Subject();
		entity.setName("Candidate");
		dao.insert(entity);
		Subject selectedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Subject entity = new Subject();
			entity.setName("Candidate" + i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

}
