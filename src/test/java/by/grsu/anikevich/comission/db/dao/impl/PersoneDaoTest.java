package by.grsu.anikevich.comission.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Persone;
import by.grsu.anikevich.comission.db.model.Role;

public class PersoneDaoTest extends AbstractTest {
	private static final IDao<Integer, Persone> personeDao = PersoneDaoImpl.INSTANCE;
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Persone entity = new Persone();
		entity.setRoleId(saveRole("role").getId());
		entity.setFirstName("first_name");
		entity.setSecondName("second_name");
		entity.setPatronymic("patronymic");
		entity.setMail("mail");
		personeDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Persone entity = new Persone();
		entity.setRoleId(saveRole("role").getId());
		entity.setFirstName("first_name");
		entity.setSecondName("second_name");
		entity.setPatronymic("patronymic");
		entity.setMail("mail");
		personeDao.insert(entity);

		entity.setFirstName("newfirst_name");
		entity.setSecondName("newsecond_name");
		entity.setPatronymic("newpatronymic");
		entity.setMail("mail");
		personeDao.update(entity);

		Persone updatedEntity = personeDao.getById(entity.getId());
		Assertions.assertEquals("newfirst_name", updatedEntity.getFirstName());
		Assertions.assertEquals("newsecond_name", updatedEntity.getSecondName());
		Assertions.assertEquals("newpatronymic", updatedEntity.getPatronymic());
		Assertions.assertEquals("mail", updatedEntity.getMail());

	}

	@Test
	public void testDelete() {
		Persone entity = new Persone();
		entity.setRoleId(saveRole("role").getId());
		entity.setFirstName("first_name");
		entity.setSecondName("second_name");
		entity.setPatronymic("patronymic");
		entity.setMail("mail");
		personeDao.insert(entity);
		personeDao.delete(entity.getId());
		Assertions.assertNull(personeDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Persone entity = new Persone();
		entity.setRoleId(saveRole("role").getId());
		entity.setFirstName("first_name");
		entity.setSecondName("second_name");
		entity.setPatronymic("patronymic");
		entity.setMail("mail");
		personeDao.insert(entity);

		Persone selectedEntity = personeDao.getById(entity.getId());
		Assertions.assertEquals("first_name", selectedEntity.getFirstName());
		Assertions.assertEquals("second_name", selectedEntity.getSecondName());
		Assertions.assertEquals("patronymic", selectedEntity.getPatronymic());
		Assertions.assertEquals("mail", selectedEntity.getMail());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Persone entity = new Persone();
			entity.setRoleId(saveRole("role" + i).getId());
			entity.setFirstName("first_name" + i);
			entity.setSecondName("second_name" + i);
			entity.setPatronymic("patronymic" + i);
			entity.setMail("mail" + i);
			personeDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, personeDao.getAll().size());
	}

	private Role saveRole(String name) {
		Role entity = new Role();
		entity.setName(name);
		roleDao.insert(entity);
		return entity;
	}

}
