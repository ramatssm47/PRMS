package sg.edu.nus.iss.phoenix.role.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.RoleList;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.dao.JDBCFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.role.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Role Data Access Object (DAO). This class contains all database handling that
 * is needed to permanently store and retrieve Role object instances.
 */
public class RoleDaoImpl implements RoleDao {

	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class.getName());

    private Connection conn;
        /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#createValueObject()
	 */
    @Override
    public Role createValueObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#getObject()
	 */
    @Override
    public Role getObject(String role) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#load(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
	 */
    @Override
    public void load(Role valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#loadAll()
	 */
    @Override
    public List<Role> loadAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#create(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
	 */
    @Override
    public void create(Role valueObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#save(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
	 */
    @Override
    public void save(Role valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#delete(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
	 */
    @Override
    public void delete(Role valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#deleteAll(java.sql.Connection)
	 */
    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`role`.dao.impl.RoleDAO#countAll()
	 */
    @Override
    public int countAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`user`.dao.impl.UserDAO#searchMatching(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
         * @param valueObject
	 *            This parameter contains the class instance to be deleted.
	 *            Primary-key field must be set for this to work properly.
	 */
    @Override
    public List<Role> searchMatching(Role valueObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        /* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`user`.dao.impl.UserDAO#searchMatching(sg.edu.nus.iss.`phoenix`.`user`.entity.User)
         * @param role
	 *            This parameter contains the string value where search will
	 *            be based.
	 */
    @Override
    public Role searchMatching(String role) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        /**
	 * loadRoles-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
     @Override
    public List<Role> loadRoles() throws SQLException {
        conn = JDBCFactory.getConnection();
        List<Role> roleList=new ArrayList<Role>();
        try{
            if(conn!=null)
        {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("Select * from phoenix.Role");
            
            while(rs.next())
            {
                Role role=new Role();
                role.setRoleId(rs.getInt("RID"));
                role.setRoleName(rs.getString("RName"));
                roleList.add(role);
                
            }
        }
        }catch(SQLException ex){
            System.out.println("roledaoex");

            roleList=null;
        }finally{
            conn.close();
        }
        
        return roleList;
    }
    public int fetchRid(User vod) throws SQLException{
    conn = JDBCFactory.getConnection();
    Statement stmt=conn.createStatement();
    int rID = 0;
    try{
        if(conn!=null)
        {
          String sql1 = "Select rid from phoenix.userrole where Userid='" + vod.getuId()+ "'";  
          ResultSet resultSet = stmt.executeQuery(sql1);
                    
                    while (resultSet.next()) {
                        rID = resultSet.getInt("RID");
                    }
        }
    }catch(Exception e)
    {
        e.printStackTrace();
    }finally{
        conn.close();
    }
    return rID;
}

     public int fetchRoleIdForName(String roleName) {
    
    int rID = 0;
    try{
        conn = JDBCFactory.getConnection();
    Statement stmt=conn.createStatement();
        if(conn!=null)
        {
          String sql1 = "Select rid from phoenix.role where rName='" + roleName+ "'";  
          ResultSet resultSet = stmt.executeQuery(sql1);
                    
                    while (resultSet.next()) {
                        rID = resultSet.getInt("RID");
                    }
        }
    }catch(Exception e)
    {
        e.printStackTrace();
    }finally{
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return rID;
}
    
}
