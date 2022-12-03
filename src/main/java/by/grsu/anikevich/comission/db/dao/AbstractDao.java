package by.grsu.anikevich.comission.db.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

import by.grsu.anikevich.comission.web.dto.SortDto;
import by.grsu.anikevich.comission.web.dto.TableStateDto;

public class AbstractDao {
	private static final String DB_FOLDER = "db-storage";
	private static String DB_URL;
	private static String DB_NAME;

	public static void init(String dbName) {
		DB_NAME = dbName;
		DB_URL = String.format("jdbc:sqlite:%s/%s", DB_FOLDER, DB_NAME);
	}

	protected static Connection createConnection() throws SQLException {
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		return DriverManager.getConnection(DB_URL, config.toProperties());
	}

	protected Integer getGeneratedId(Connection c, String tableName, String idColumnName) throws SQLException {
		String selectIdSql = String.format("select max(%s) as max_id from %s", idColumnName, tableName);

		PreparedStatement pstmt = c.prepareStatement(selectIdSql);
		pstmt.execute();

		ResultSet rs = pstmt.getResultSet();
		final Integer id = rs.getInt("max_id");
		return id;
	}

	protected Integer getGeneratedId(Connection c, String tableName) throws SQLException {
		return getGeneratedId(c, tableName, "id");
	}

	public static void createDbSchema() {
		System.out.println(String.format("create DB %s", DB_NAME));

		try (Connection c = createConnection()) {
			String sql = new String(Files.readAllBytes(Paths.get("docs/db/db.sql")));
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);

			ResultSet rs = c.getMetaData().getTables(null, null, null, null);
			while (rs.next()) {
				System.out.println("created table " + rs.getString("TABLE_NAME"));
			}
		} catch (IOException | SQLException e) {
			throw new RuntimeException("can't create DB schema", e);
		}
	}

	public static void deleteDb() {
		System.out.println(String.format("delete DB %s", DB_NAME));
		File dbDataFile = new File(String.format("%s/%s", DB_FOLDER, DB_NAME));
		dbDataFile.delete();
	}

	public static boolean isDbExist() throws SQLException {
		try (Connection c = createConnection()) {
			DatabaseMetaData metaData = c.getMetaData();
			ResultSet rs = metaData.getTables(null, null, null, null);
			return rs.next(); // assume DB exists if at least one table presents
		}
	}
	protected int resolveOffset(TableStateDto dto) {
		int offset = dto.getItemsPerPage() * (dto.getPage() - 1);
		return dto.getTotalCount() < offset ? 0 : offset;

	}

	protected String resolveSortOrder(SortDto sortDto) {
		return sortDto.isAscending() ? "asc" : "desc";
	}

}
