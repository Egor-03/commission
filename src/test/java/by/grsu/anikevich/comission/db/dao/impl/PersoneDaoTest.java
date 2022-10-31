package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Role;

public class PersoneDaoTest extends AbstractTest{
	private static final IDao<Integer, Persone> personedao = PersoneDaoImpl.INSTANCE;
	private static final IDao<Integer, Role> roledao = RoleDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Persone entity = new Persone();
		entity.setRoleId(1);
		entity.setFirstName("first_name");
		entity.setSecondName(rs.getString("second_name"));
		entity.setPatronymic(rs.getString("patronymic"));
		entity.setMail(rs.getString("mail"));
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Persone entity = new Persone();
		entity.setName("Candidate");

		dao.insert(entity);

		String newName = "NEW_Candidate";
		entity.setName(newName);
		dao.update(entity);

		Persone updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Persone entity = new Persone();
		entity.setName("Candidate");
		dao.insert(entity);
		dao.delete(entity.getId());
		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Persone entity = new Persone();
		entity.setName("Candidate");
		dao.insert(entity);
		Persone selectedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Persone entity = new Persone();
			entity.setName("Candidate" + i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

}
