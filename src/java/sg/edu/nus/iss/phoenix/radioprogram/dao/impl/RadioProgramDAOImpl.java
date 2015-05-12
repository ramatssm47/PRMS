package sg.edu.nus.iss.phoenix.radioprogram.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.JDBCFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;


/**
 * RadioProgram Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve RadioProgram object
 * instances.
 */
public class RadioProgramDAOImpl implements RadioProgramDAO {

	Connection connection;

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#createValueObject()
	 */
	@Override
	public RadioProgram createValueObject() {
		return new RadioProgram();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#getObject(java.lang.String)
	 */
	@Override
	public RadioProgram getObject(String name) throws NotFoundException,
			SQLException {

		RadioProgram valueObject = createValueObject();
		valueObject.setName(name);
		load(valueObject);
		return valueObject;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#load(sg.edu.nus.iss.`phoenix`.`radioprogram`.entity.RadioProgram)
	 */
	@Override
	public void load(RadioProgram valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getName() == null) {
			// System.out.println("Can not select without Primary-Key!");
			throw new NotFoundException("Can not select without Primary-Key!");
		}

		String sql = "SELECT * FROM `phoenix`.`radioprogram` WHERE (`name` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getName());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#loadAll()
	 */
	@Override
	public List<RadioProgram> loadAll() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `phoenix`.`radioprogram` ORDER BY `name` ASC; ";
		List<RadioProgram> searchResults = listQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#create(sg.edu.nus.iss.`phoenix`.`radioprogram`.entity.RadioProgram)
	 */
	@Override
	public synchronized void create(RadioProgram valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `phoenix`.`radioprogram` (`name`, `description`, `typicalDuration`) VALUES (?,?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getName());
			stmt.setString(2, valueObject.getDescription());
			stmt.setTime(3, valueObject.getTypicalDuration());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#save(sg.edu.nus.iss.`phoenix`.`radioprogram`.entity.RadioProgram)
	 */
	@Override
	public void save(RadioProgram valueObject) throws NotFoundException,
			SQLException {

		String sql = "UPDATE `phoenix`.`radioprogram` SET `description` = ?, `typicalDuration` = ? WHERE (`name` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getDescription());
			stmt.setTime(2, valueObject.getTypicalDuration());

			stmt.setString(3, valueObject.getName());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#delete(sg.edu.nus.iss.`phoenix`.`radioprogram`.entity.RadioProgram)
	 */
	@Override
	public void delete(RadioProgram valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getName() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `phoenix`.`radioprogram` WHERE (`name` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getName());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#deleteAll(java.sql.Connection)
	 */
	@Override
	public void deleteAll(Connection conn) throws SQLException {

		String sql = "DELETE FROM `phoenix`.`radioprogram`";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			int rowcount = databaseUpdate(stmt);
			System.out.println(""+rowcount);
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
			
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#countAll()
	 */
	@Override
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM `phoenix`.`radioprogram`";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int allRows = 0;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next())
				allRows = result.getInt(1);
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
		return allRows;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`radioprogram`.dao.impl.RadioProgramDAO#searchMatching(sg.edu.nus.iss.`phoenix`.`radioprogram`.entity.RadioProgram)
	 */
	@Override
	public List<RadioProgram> searchMatching(RadioProgram valueObject) throws SQLException {

		List<RadioProgram> searchResults = new ArrayList<RadioProgram>();
		openConnection();
		boolean first = true;
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM `phoenix`.`radioprogram` WHERE 1=1 ");

		if (valueObject.getName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `name` LIKE '").append(valueObject.getName())
					.append("%' ");
		}

		if (valueObject.getDescription() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `description` LIKE '").append(valueObject.getDescription())
					.append("%' ");
		}

		if (valueObject.getTypicalDuration() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `typicalDuration` = '")
					.append(valueObject.getTypicalDuration()).append("' ");
		}

		sql.append("ORDER BY `name` ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList<RadioProgram>();
		else
			searchResults = listQuery(connection.prepareStatement(sql
					.toString()));
		closeConnection();
		return searchResults;
	}

	/**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return only one row. The
	 * resultset will be converted to valueObject. If no rows were found,
	 * NotFoundException will be thrown.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 * @param valueObject
	 *            Class-instance where resulting data will be stored.
	 */
	protected void singleQuery(PreparedStatement stmt, RadioProgram valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setName(result.getString("name"));
				valueObject.setDescription(result.getString("description"));
				valueObject.setTypicalDuration(result
						.getTime("typicalDuration"));

			} else {
				// System.out.println("RadioProgram Object Not Found!");
				throw new NotFoundException("RadioProgram Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected List<RadioProgram> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<RadioProgram> searchResults = new ArrayList<RadioProgram>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				RadioProgram temp = createValueObject();

				temp.setName(result.getString("name"));
				temp.setDescription(result.getString("description"));
				temp.setTypicalDuration(result.getTime("typicalDuration"));
                                temp.setRadioId(result.getInt("progID"));
				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<RadioProgram>) searchResults;
	}

	private void openConnection() {
		          this.connection = JDBCFactory.getConnection();
		
	}

	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
