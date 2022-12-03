package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Faculty;
import by.grsu.anikevich.comission.web.dto.SortDto;
import by.grsu.anikevich.comission.web.dto.TableStateDto;

public class FacultyDaoImpl extends AbstractDao implements IDao<Integer, Faculty> {

	public static final FacultyDaoImpl INSTANCE = new FacultyDaoImpl();

	private FacultyDaoImpl() {
		super();
	}

	@Override
	public void insert(Faculty entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into faculty(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "faculty"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Faculty entity", e);
		}

	}

	@Override
	public void update(Faculty entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update faculty set name=?");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "faculty"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update Faculty entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from faculty where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete Faculty entity", e);
		}
	}

	@Override
	public Faculty getById(Integer id) {
		Faculty entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from faculty where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Faculty entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Faculty> getAll() {
		List<Faculty> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from faculty");
			while (rs.next()) {
				Faculty entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Faculty entities", e);
		}

		return entitiesList;
	}

	private Faculty rowToEntity(ResultSet rs) throws SQLException {
		Faculty entity = new Faculty();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}

	@Override
	public List<Faculty> getAllwithId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Faculty> find(TableStateDto tableStateDto) {
		List<Faculty> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from faculty");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Requests using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Faculty entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select faculty entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from faculty");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get Faculty count", e);
		}
	}
}
