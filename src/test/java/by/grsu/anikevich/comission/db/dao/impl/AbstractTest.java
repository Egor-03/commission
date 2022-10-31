package by.grsu.anikevich.comission.db.dao.impl;

import java.util.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import by.grsu.anikevich.comission.db.dao.AbstractDao;

public abstract class AbstractTest {
	@BeforeAll
	private static void setup() {
		AbstractDao.init("test-db");
	}

	@BeforeEach
	private void setupThis() {
		AbstractDao.deleteDb();
		AbstractDao.createDbSchema();
	}

	protected Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	protected int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

}
