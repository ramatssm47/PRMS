package sg.edu.nus.iss.phoenix.user.dao;
   /**
 *
 * @author Preeti,Gautam
 */
import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.user.entity.User;


import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public interface UserDao {

	/**
	 * createValueObject-method. This method is used when the Dao class needs to
	 * create new value object instance. The reason why this method exists is
	 * that sometimes the programmer may want to extend also the valueObject and
	 * then this method can be overrided to return extended valueObject. NOTE:
	 * If you extend the valueObject class, make sure to override the clone()
	 * method in it!
	 */
	public abstract User createValueObject();

	/**
	 * getObject-method. This will create and load valueObject contents from
	 * database using given Primary-Key as identifier. This method is just a
	 * convenience method for the real load-method which accepts the valueObject
	 * as a parameter. Returned valueObject will be created using the
	 * createValueObject() method.
	 */
	public abstract User getObject(String id)
			throws NotFoundException, SQLException;

	/**
	 * load-method. This will load valueObject contents from database using
	 * Primary-Key as identifier. Upper layer should use this so that
	 * valueObject instance is created and only primary-key should be specified.
	 * Then call this method to complete other persistent information. This
	 * method will overwrite all other fields except primary-key and possible
	 * runtime variables. If load can not find matching row, NotFoundException
	 * will be thrown.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance to be loaded.
	 *            Primary-key field must be set for this to work properly.
	 */
	public abstract void load(User valueObject)
			throws NotFoundException, SQLException;

	/**
	 * LoadAll-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
	public abstract List<User> loadAll() throws SQLException;

	/**
	 * create-method. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
	 */
	public abstract void create(User valueObject)
			throws SQLException;

	/**
	 * save-method. This method will save the current state of valueObject to
	 * database. Save can not be used to create new instances in database, so
	 * upper layer must make sure that the primary-key is correctly specified.
	 * Primary-key will indicate which instance is going to be updated in
	 * database. If save can not find matching row, NotFoundException will be
	 * thrown.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance to be saved.
	 *            Primary-key field must be set for this to work properly.
	 */
	public abstract void save(User valueObject)
			throws NotFoundException, SQLException;

	/**
	 * delete-method. This method will remove the information from database as
	 * identified by by primary-key in supplied valueObject. Once valueObject
	 * has been deleted it can not be restored by calling save. Restoring can
	 * only be done using create method but if database is using automatic
	 * surrogate-keys, the resulting object will have different primary-key than
	 * what it was in the deleted object. If delete can not find matching row,
	 * NotFoundException will be thrown.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance to be deleted.
	 *            Primary-key field must be set for this to work properly.
	 */
	public abstract void delete(User valueObject)
			throws NotFoundException, SQLException;

	/**
	 * deleteAll-method. This method will remove all information from the table
	 * that matches this Dao and ValueObject couple. This should be the most
	 * efficient way to clear table. Once deleteAll has been called, no
	 * valueObject that has been created before can be restored by calling save.
	 * Restoring can only be done using create method but if database is using
	 * automatic surrogate-keys, the resulting object will have different
	 * primary-key than what it was in the deleted object. (Note, the
	 * implementation of this method should be different with different DB
	 * backends.)
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
	public abstract void deleteAll() throws SQLException;

	/**
	 * coutAll-method. This method will return the number of all rows from table
	 * that matches this Dao. The implementation will simply execute
	 * "select count(primarykey) from table". If table is empty, the return
	 * value is 0. This method should be used before calling loadAll, to make
	 * sure table has not too many rows.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
	public abstract int countAll() throws SQLException;

	/**
	 * searchMatching-Method. This method provides searching capability to get
	 * matching valueObjects from database. It works by searching all objects
	 * that match permanent instance variables of given object. Upper layer
	 * should use this by setting some parameters in valueObject and then call
	 * searchMatching. The result will be 0-N objects in a List, all matching
	 * those criteria you specified. Those instance-variables that have NULL
	 * values are excluded in search-criteria.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
	 */
	public abstract User searchMatching(User valueObject)
			throws SQLException;
                       /**
	 * searchMatching-Method. This method provides searching capability to get
	 * matching integer uid values from database. It works by searching all objects
	 * that match permanent instance variables of given value.The result 
         * will be 0-N objects in a List, all matching
	 * those criteria you specified. Those instance-variables that have NULL
	 * values are excluded in search-criteria.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param uid
	 *            This parameter contains the int value where search will
	 *            be based.
	 */
	public abstract User searchMatching(int uid)
			throws SQLException;
          /**
	 *insertUserDetails-method. This method inserts user details into the user table
         * with the given value object.It will execute database insert query in user table.
	 * The return value indicates true if the row is successfully inserted.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param valueObject
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
	 */
        public boolean insertUserDetails(User valueObject)
			throws SQLException;
        /**isUserExists-method. This method checks the user in user table.
         * It executes select query in user table to the user id.
         * The return value indicates true if user exists.
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * @param valueObject
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
	*/
        public boolean isUserExists(User vo) throws SQLException;
         /**
	 *getUserDetails-method. This method returns user details from the user table
         * for the string value given.The matchable values will be stored in a list.
	 * It will execute database select query in user table.
         * The fetched column values are stored in resultset.
	 * The return value indicates user object details of the userid passed.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param id
	 *            This parameter contains the string value to be matched.
	 */
        public User getUserDetails(String id)throws SQLException;
        /**deleteUserDoaImpl-method. This method is to delete user from user 
         * table for value object given.It executes update query in user table.
         * The return value indicates true if the row is deleted.
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * @param ud
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
	*/
        public boolean deleteUserDoaImpl(User ud) throws SQLException;
        /**
	 *getAllUsers-method. This method returns all the users details from the user table.
	 * It will execute all database select query in user table.
         * all the user values will be stored into the list object.
         * The fetched column values are stored in resultset.
	 * The return value indicates user object details of the userid passed.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param pmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
        public List<User>getAllUsers() throws SQLException;
        /**
	 *saveModifiedDetails-method. This method saves the modified details of 
         * the user in user table.It will execute update database query in user 
         * table.The return value indicates true on success.
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * * @param user
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
	*/
        public Boolean saveModifiedDetails(User user)throws SQLException;
        /**
	 * LoadAllUsers-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
         public abstract List<User> loadAllUsers() throws SQLException; // in seqence displayAllUser
        /**
	 * getPresenterProducerList-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * * @param fetchRole
	 *            This parameter contains the string value search will
	 *            be based.
	 */
        public abstract List<User> getPresenterProducerList(String fetchRole) throws SQLException;
    /**
	 * searchUserWithId-method. This will read all contents from database table and build
	 * a List containing valueObjects with the given value object matched.This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * * @param userid
	 *            This parameter contains string value search will
	 *            be based.
         * * @param roleType
	 *            This parameter contains the string roleType search will
	 *            be based.
         * 
	 */
        public List<User>searchUserWithId(String userid,String roleType)throws SQLException;
    public int fetchUid(User vod) throws SQLException;
}