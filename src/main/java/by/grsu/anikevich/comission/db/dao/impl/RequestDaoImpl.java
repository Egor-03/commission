package by.grsu.anikevich.comission.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.anikevich.comission.db.dao.AbstractDao;
import by.grsu.anikevich.comission.db.dao.IDao;
import by.grsu.anikevich.comission.db.model.Request;
import by.grsu.anikevich.comission.db.model.Speciality;

public class RequestDaoImpl extends AbstractDao implements IDao<Integer, Request> {

	public static final RequestDaoImpl INSTANCE = new RequestDaoImpl();

	private RequestDaoImpl() {
		super();
	}

	@Override
	public void insert(Request entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into request(person_id,speciality_id,state_id) values(?,?,?)");
			pstmt.setInt(1, entity.getPersonId());
			pstmt.setInt(2, entity.getSpecialityId());
			pstmt.setInt(3, entity.getStateId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "request"));
 		} catch (SQLException e) {
			throw new RuntimeException("can't insert Request entity", e);
		}

	}

	@Override
	public void update(Request entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update request set person_id=?, speciality_id =?, state_id=?  where id =? ");
			pstmt.setInt(1, entity.getPersonId());
			pstmt.setInt(2, entity.getSpecialityId());
			pstmt.setInt(3, entity.getStateId());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "request"));
		} catch (SQLException e) {
			throw new RuntimeException("can't update Request entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from request where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't delete Request entity", e);
		}
	}

	@Override
	public Request getById(Integer id) {
		Request entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from request where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Request entity by id", e);
		}

		return entity;
	}
	
	@Override
	public List<Request> getAll() {
		List<Request> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from request");
			while (rs.next()) {
				Request entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Request entities", e);
		}

		return entitiesList;
	}

	

	@Override
	public List<Request> getAllwithId(Integer specialityId) {
		List<Request> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from request where speciality_id=?");
			pstmt.setInt(1, specialityId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Request entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select request entities", e);
		}

		return entitiesList;
		
	}
	private Request rowToEntity(ResultSet rs) throws SQLException {
		Request entity = new Request();
		entity.setId(rs.getInt("id"));
		entity.setPersonId(rs.getInt("person_id"));
		entity.setSpecialityId(rs.getInt("speciality_id"));
		entity.setStateId(rs.getInt("state_id"));
		return entity;
	}
	
	public List<Request> getAllwith(Integer id) {
		List<Request> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from request where id=?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Request entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select request entities", e);
		}

		return entitiesList;	
	}
	public List<Request> getAllwithSpeciality(Integer speciality_id) {
		List<Request> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from request where speciality_id=?");
			pstmt.setInt(1, speciality_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Request entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select request entities", e);
		}

		return entitiesList;
		
	}
}
