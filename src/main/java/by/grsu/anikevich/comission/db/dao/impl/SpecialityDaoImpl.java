package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Speciality;
import by.grsu.anikevich.comission.web.dto.SortDto;
import by.grsu.anikevich.comission.web.dto.TableStateDto;

public class SpecialityDaoImpl extends AbstractDao implements IDao<Integer, Speciality> {
	public static final SpecialityDaoImpl INSTANCE = new SpecialityDaoImpl();

	private SpecialityDaoImpl() {
		super();
	}

	@Override
	public void insert(Speciality entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"insert into speciality(first_subject_id,second_subject_id,third_subject_id,name,faculty_id) values(?,?,?,?,?)");
			pstmt.setInt(1, entity.getFirstSubjectId());
			pstmt.setInt(2, entity.getSecondSubjectId());
			pstmt.setInt(3, entity.getThirdSubjectId());
			pstmt.setString(4, entity.getName());
			pstmt.setInt(5, entity.getFacultyId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "speciality"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Speciality entity", e);
		}

	}

	@Override
	public void update(Speciality entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"update speciality set first_subject_id=?, second_subject_id=?, third_subject_id=?, name=?, faculty_id=?  where id =? ");
			pstmt.setInt(1, entity.getFirstSubjectId());
			pstmt.setInt(2, entity.getSecondSubjectId());
			pstmt.setInt(3, entity.getThirdSubjectId());
			pstmt.setString(4, entity.getName());
			pstmt.setInt(5, entity.getFacultyId());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "speciality"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update Speciality entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from speciality where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete Speciality entity", e);
		}
	}

	@Override
	public Speciality getById(Integer id) {
		Speciality entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from speciality where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Speciality entity by id", e);
		}

		return entity;
	}
	@Override
	public List<Speciality> getAllwithId(Integer faculty_id) {
		List<Speciality> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from speciality where faculty_id=?");
			pstmt.setInt(1, faculty_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Speciality entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Speciality entities", e);
		}

		return entitiesList;
	}

	@Override
	public List<Speciality> getAll() {
		List<Speciality> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from speciality");
			while (rs.next()) {
				Speciality entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Speciality entities", e);
		}

		return entitiesList;
	}

	private Speciality rowToEntity(ResultSet rs) throws SQLException {
		Speciality entity = new Speciality();
		entity.setId(rs.getInt("id"));
		entity.setFirstSubjectId(rs.getInt("first_subject_id"));
		entity.setSecondSubjectId(rs.getInt("second_subject_id"));
		entity.setThirdSubjectId(rs.getInt("third_subject_id"));
		entity.setName(rs.getString("name"));
		entity.setFacultyId(rs.getInt("faculty_id"));
		return entity;
	}

	@Override
	public List<Speciality> find(TableStateDto tableStateDto) {
		List<Speciality> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from speciality");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Requests using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Speciality entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Speciality entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from speciality");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get Speciality count", e);
		}
	}

}
