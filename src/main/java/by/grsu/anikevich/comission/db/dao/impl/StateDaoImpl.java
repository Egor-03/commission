package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.State;

public class StateDaoImpl extends AbstractDao implements IDao<Integer, State> {
	public static final StateDaoImpl INSTANCE = new StateDaoImpl();

	private StateDaoImpl() {
		super();
	}

	@Override
	public void insert(State entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into state(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "state"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert State entity", e);
		}

	}

	@Override
	public void update(State entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update state set name=?");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "state"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update State entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from state where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete State entity", e);
		}
	}

	@Override
	public State getById(Integer id) {
		State entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from state where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get State entity by id", e);
		}

		return entity;
	}

	@Override
	public List<State> getAll() {
		List<State> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from state");
			while (rs.next()) {
				State entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Role entities", e);
		}

		return entitiesList;
	}

	private State rowToEntity(ResultSet rs) throws SQLException {
		State entity = new State();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}

	@Override
	public List<State> getAllwithId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
