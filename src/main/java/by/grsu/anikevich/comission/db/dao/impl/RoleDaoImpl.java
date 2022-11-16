package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Role;

public class RoleDaoImpl extends AbstractDao implements IDao<Integer, Role> {

	public static final RoleDaoImpl INSTANCE = new RoleDaoImpl();

	private RoleDaoImpl() {
		super();
	}

	@Override
	public void insert(Role entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into role(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "role"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Role entity", e);
		}

	}

	@Override
	public void update(Role entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update role set name=?");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "role"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update Role entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from role where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete Role entity", e);
		}
	}

	@Override
	public Role getById(Integer id) {
		Role entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from role where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Role entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Role> getAll() {
		List<Role> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from role");
			while (rs.next()) {
				Role entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Role entities", e);
		}

		return entitiesList;
	}

	private Role rowToEntity(ResultSet rs) throws SQLException {
		Role entity = new Role();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}

	@Override
	public List<Role> getAllwithId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
