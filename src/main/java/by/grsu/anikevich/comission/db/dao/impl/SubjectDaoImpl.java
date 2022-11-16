package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Subject;

public class SubjectDaoImpl extends AbstractDao implements IDao<Integer, Subject> {

	public static final SubjectDaoImpl INSTANCE = new SubjectDaoImpl();

	private SubjectDaoImpl() {
		super();
	}

	@Override
	public void insert(Subject entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into subject(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "subject"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Subject entity", e);
		}

	}

	@Override
	public void update(Subject entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update subject set name=?");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "subject"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update Subject entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from subject where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete Subject entity", e);
		}
	}

	@Override
	public Subject getById(Integer id) {
		Subject entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from subject where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get subject entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Subject> getAll() {
		List<Subject> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from subject");
			while (rs.next()) {
				Subject entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Subject entities", e);
		}

		return entitiesList;
	}

	private Subject rowToEntity(ResultSet rs) throws SQLException {
		Subject entity = new Subject();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}

	@Override
	public List<Subject> getAllwithId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
