package by.grsu.anikevich.comission.db.dao;

import java.util.List;

import by.grsu.anikevich.comission.db.model.Speciality;

public interface IDao<ID, TYPE> {
	void insert(TYPE t);

	void update(TYPE t);

	void delete(ID id);

	TYPE getById(ID id);

	List<TYPE> getAll();
	List<TYPE> getAllwithId(ID id);
}
