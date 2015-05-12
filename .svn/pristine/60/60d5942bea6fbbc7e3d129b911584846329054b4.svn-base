package sg.edu.nus.iss.phoenix.user.dao.impl;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

import sg.edu.nus.iss.phoenix.user.dao.UserDao;

import sg.edu.nus.iss.phoenix.core.dao.JDBCFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 * User Data Access Object (DAO). This class contains all database handling that
 * is needed to permanently store and retrieve User object instances.
 */

/**
 *
 * @author Gautam,Preeti,Vijay
 */
public class UserDaoImpl implements UserDao {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    JDBCFactory daoFactory = new JDBCFactory();
    private Connection conn;


    @Override
    public sg.edu.nus.iss.phoenix.user.entity.User createValueObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public sg.edu.nus.iss.phoenix.user.entity.User getObject(String id) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(sg.edu.nus.iss.phoenix.user.entity.User valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<sg.edu.nus.iss.phoenix.user.entity.User> loadAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(sg.edu.nus.iss.phoenix.user.entity.User valueObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(sg.edu.nus.iss.phoenix.user.entity.User valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(sg.edu.nus.iss.phoenix.user.entity.User valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public sg.edu.nus.iss.phoenix.user.entity.User searchMatching(sg.edu.nus.iss.phoenix.user.entity.User valueObject) throws SQLException {
        conn = JDBCFactory.getConnection();
        if (conn != null) {
            Statement stmt = conn.createStatement();
            String sql = "select * from Phoenix.User where UserId=? and Password=?";
            PreparedStatement pmt = conn.prepareStatement(sql);
            pmt.setString(1, valueObject.getUserId());
            pmt.setString(2, valueObject.getPassword());
            ResultSet rs = pmt.executeQuery();
            if (rs.next()) {

                valueObject.setEmailId(rs.getString("Email_Id"));
                valueObject.setContact(rs.getString("Contact"));
                valueObject.setUserName(rs.getString("Name"));
                sql = "select * from Phoenix.UserRole where UID=?";
                pmt = conn.prepareStatement(sql);
                pmt.setInt(1, rs.getInt("UID"));
                ResultSet roleSet = pmt.executeQuery();

                while (roleSet.next()) {
                    Role role = new Role();
                    role.setRoleId(roleSet.getInt("RID"));

                    String sqlForRole = "select * from Phoenix.Role where RID=" + role.getRoleId();
                    Statement rolestmt = conn.createStatement();
                    ResultSet roleResultSet = rolestmt.executeQuery(sqlForRole);
                    if (roleResultSet.next()) {
                        role.setRoleName(roleResultSet.getString("RName"));
                    }
                    valueObject.getRoles().add(role);

                }

            }else{
                valueObject = null;
            }
            return valueObject;
        } else {
            throw new SQLException();
        }

    }

    @Override
    public sg.edu.nus.iss.phoenix.user.entity.User searchMatching(int uid) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertUserDetails(User valueObject)
            throws SQLException {
        boolean returnParam = false;
        conn = JDBCFactory.getConnection();
        try {
            if (conn != null) {
                String sql = "INSERT INTO phoenix.user (`UserId`,`Password`,`Status`,`Email_Id`,`Contact`,`Name`)VALUES(?,?,1,?,?,?)";
                PreparedStatement pmt = conn.prepareStatement(sql);
                Statement rolestmt = conn.createStatement();
                pmt.setString(1, valueObject.getUserId());
                pmt.setString(2, valueObject.getPassword());
                pmt.setString(3, valueObject.getEmailId());
                pmt.setString(4, valueObject.getContact());
                pmt.setString(5, valueObject.getUserName());
                int isUserInserted = pmt.executeUpdate();

                if (isUserInserted > 0) {
                    String sql1 = "Select uid from phoenix.user where Userid='" + valueObject.getUserId() + "'";
                    ResultSet roleResultSet = rolestmt.executeQuery(sql1);
                    int uID = 0;

                    while (roleResultSet.next()) {
                        uID = roleResultSet.getInt("UID");
                    }
                    List<Role> gr = valueObject.getRoles();
                    for (Role a : gr) {
                        String insert = "INSERT INTO phoenix.userrole(UID,RID) VALUES(" + uID + "," + a.getRoleId() + ")";
                        pmt.addBatch(insert);
                    }
                    int result[] = pmt.executeBatch();
                    if (result.length > 0) {
                        returnParam = true;
                    }

                }

            }
        } catch (SQLException ex) {
        } finally {
            conn.close();
        }
        return returnParam;
    }

    @Override
    public User getUserDetails(String id) throws SQLException {

        User user = new User();
        List<Role> roleList = new ArrayList<Role>();

        conn = JDBCFactory.getConnection();
        try {
            if (conn != null) {

                String sqlToRetrieve = "select * from phoenix.user where Userid='" + id + "'and status =1";
                Statement retrieveStmt = conn.createStatement();
                ResultSet rs = retrieveStmt.executeQuery(sqlToRetrieve);
                if (rs.next()) {
                    user.setUserId(rs.getString("userid"));
                    user.setEmailId(rs.getString("email_id"));
                    user.setContact(rs.getString("contact"));
                    user.setUserName(rs.getString("Name"));
                    String sqlToRetrieveRole = "select * from phoenix.userRole where UID='" + rs.getString("UID") + "'";
                    ResultSet rsForRoles = retrieveStmt.executeQuery(sqlToRetrieveRole);

                    while (rsForRoles.next()) {
                        Role r = new Role();
                        r.setRoleId(rsForRoles.getInt("RID"));
                        String sqlToRetrieveRoleName = "select * from phoenix.Role where RID=" + r.getRoleId() + " and status =1";

                        Statement stmtTORetrieveRoleName = conn.createStatement();

                        ResultSet rsForRetrieveRole = stmtTORetrieveRoleName.executeQuery(sqlToRetrieveRoleName);
                        while (rsForRetrieveRole.next()) {
                            r.setRoleName(rsForRetrieveRole.getString("RName"));
                            roleList.add(r);
                        }

                    }
                    user.setRoles(roleList);
                    conn.close();
                    return user;
                } else {
                    conn.close();
                    return user;
                }

            } else {
                throw new SQLException();
            }

        } catch (Exception e) {
           
        }
        conn.close();
        return user; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> listOfusers = new ArrayList<User>();
        try {
            conn = JDBCFactory.getConnection();
            String sql = "SELECT * FROM Phoenix.user where status=1";
            Statement stmt = conn.createStatement();
            ResultSet userResultSet = stmt.executeQuery(sql);
            while (userResultSet.next()) {
                List<Role> userRoles = new ArrayList<Role>();

                User user = new User();
                user.setUserId(userResultSet.getString("userid"));
                user.setEmailId(userResultSet.getString("email_id"));
                user.setContact(userResultSet.getString("contact"));
                user.setUserName(userResultSet.getString("Name"));
                Statement retrieveRoleIdStmt = conn.createStatement();
                String sqlToRetrieveRole = "select * from phoenix.userRole where UID='" + userResultSet.getString("UID") + "'";
                ResultSet rsForRoles = retrieveRoleIdStmt.executeQuery(sqlToRetrieveRole);

                while (rsForRoles.next()) {
                    
                    Role r = new Role();
                    r.setRoleId(rsForRoles.getInt("RID"));
                    String sqlToRetrieveRoleName = "select * from phoenix.Role where RID=" + r.getRoleId() + " and status =1";

                    Statement stmtTORetrieveRoleName = conn.createStatement();

                    ResultSet rsForRetrieveRole = stmtTORetrieveRoleName.executeQuery(sqlToRetrieveRoleName);
                    while (rsForRetrieveRole.next()) {
                        r.setRoleName(rsForRetrieveRole.getString("RName"));
                        userRoles.add(r);
                    }

                }

                user.setRoles(userRoles);

                listOfusers.add(user);
            }

        } catch (Exception e) {
             listOfusers=null;
             return null;
        } finally {
            conn.close();
        }
        return listOfusers;

    }

    @Override
    public Boolean saveModifiedDetails(User user) throws SQLException {

        String currentUserId;
        Boolean result = false;
        try {
            conn = JDBCFactory.getConnection();
            result = updateUserDetails(user, conn);
            
        } catch (Exception e) {
            result=true;
        } finally {
            
            conn.close();
        }
        
        return result;
    }

    public Boolean updateUserDetails(User user, Connection conn) throws SQLException {

        String updateUserSql = "update Phoenix.user set Email_ID=?,Contact=?,Name=? where UserId=?";
        PreparedStatement updateUserStmt = conn.prepareStatement(updateUserSql);
        updateUserStmt.setString(1, user.getEmailId());
        updateUserStmt.setString(2, user.getContact());
        updateUserStmt.setString(3, user.getUserName());
        updateUserStmt.setString(4, user.getUserId());
        Boolean res = updateUserStmt.execute();
        res = deleteCurrentRoles(user, conn);
        res=insertNewRoles(user, conn);
        return res;

    }

    public Boolean deleteCurrentRoles(User user, Connection conn) throws SQLException {
        String deleteRoleForuserSql = "delete from Phoenix.userRole where UID=?";
        PreparedStatement deleteRoleForuserStmt = conn.prepareStatement(deleteRoleForuserSql);
        deleteRoleForuserStmt.setString(1, getCurrentModifiedUserUID(user, conn));
        return deleteRoleForuserStmt.execute();
    }

    public String getCurrentModifiedUserUID(User user, Connection conn) throws SQLException {

        String getIDSQl = "select UID from Phoenix.User where UserId='" + user.getUserId() + "'";
        Statement getIDStmt = conn.createStatement();
        ResultSet getIDResultset = getIDStmt.executeQuery(getIDSQl);
        getIDResultset.next();
        String id = getIDResultset.getString(1);

        return id;

    }

    public Boolean insertNewRoles(User user, Connection conn) throws SQLException {
        Boolean res=null;
        for(Integer roleId:getRoleID(user, conn)){
            
        String insertNewRoleSQL="insert into Phoenix.UserRole(UID,RID)values(?,?)";
        PreparedStatement insertNewRolePreparedStatement=conn.prepareStatement(insertNewRoleSQL);
        insertNewRolePreparedStatement.setInt(1, Integer.parseInt(getCurrentModifiedUserUID(user, conn)));
        insertNewRolePreparedStatement.setInt(2, roleId);
        res=insertNewRolePreparedStatement.execute();
        }
        return res;
    }

    public List<Integer> getRoleID(User user, Connection conn) throws SQLException {
       List<Integer> returnRoles=new ArrayList<Integer>();
        for (int i = 0; i < user.getRoles().size(); i++) {
            if(user.getRoles().get(i).getRoleName().equals("StationManager")){
                user.getRoles().get(i).setRoleName("Station Manager");
            }
            String getRoleIDSql = "select * from Phoenix.Role where RName='"+user.getRoles().get(i).getRoleName()+"' and status=1";
            Statement getRoleIDStmt = conn.createStatement();
            ResultSet getRoleIDRs=getRoleIDStmt.executeQuery(getRoleIDSql);
            getRoleIDRs.next();
            returnRoles.add(getRoleIDRs.getInt("RID"));
             
        }
        return returnRoles;
    }

 public  List<User> loadAllUsers() throws SQLException // in seqence diagram displayAllUser
    {
       List<User> listOfusers = new ArrayList<User>();
       List<Role> userRoles = new ArrayList<Role>();
        conn = JDBCFactory.getConnection();
        
        try{
        if(conn!=null)
        {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select usr.uid,usr.name,usr.userid,usr.Status,usr.email_id,usr.contact,ur.RID,rl.RName from userrole ur join user usr on usr.UID = ur.UID join role rl on ur.RID = rl.RID where usr.Status=1");
            while(rs.next())
            {
                
                User userdetails=new User();
                Role userrole=new Role();
                List<Role> rl=new ArrayList<Role>();
                userdetails.setuId(rs.getString("uid"));
                userdetails.setUserName(rs.getString("Name"));
                userdetails.setUserId(rs.getString("userid"));
                userdetails.setEmailId(rs.getString("Email_Id"));
                userdetails.setContact(rs.getString("Contact"));
                userrole.setRoleId(rs.getInt("RID"));
                userrole.setRoleName(rs.getString("RName"));
                rl.add(userrole);
                userdetails.setRoles(rl);
                listOfusers.add(userdetails);
                                }
                
                
                
        }
        
    }
        
 catch(SQLException ex){
    ex.printStackTrace();
    throw ex;
}finally
        {
            conn.close();
        }
        return listOfusers;
    }
    @Override
    public List<User> getPresenterProducerList(String fetchRole) throws SQLException {
//        

        List<User> listOfusers = new ArrayList<User>();
        List<Role> userRoles = new ArrayList<Role>();
        conn = JDBCFactory.getConnection();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "select usr.uid,usr.name,usr.userid,usr.Status,usr.email_id,usr.contact,ur.RID,rl.RName from userrole ur join user usr on usr.UID = ur.UID join role rl on ur.RID = rl.RID where usr.Status=1 and rl.RName='" + fetchRole + "'";
                System.out.println(sql);

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    User userdetails = new User();
                    Role userrole = new Role();
                    List<Role> rl = new ArrayList<Role>();
                    userdetails.setuId(rs.getString("uid"));
                    userdetails.setUserName(rs.getString("Name"));
                    userdetails.setUserId(rs.getString("userid"));
                    userdetails.setEmailId(rs.getString("Email_Id"));
                    userdetails.setContact(rs.getString("Contact"));
                    userrole.setRoleId(rs.getInt("RID"));
                    userrole.setRoleName(rs.getString("RName"));
                    rl.add(userrole);
                    userdetails.setRoles(rl);
                    listOfusers.add(userdetails);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            conn.close();
        }
        return listOfusers;
    }
  public  boolean deleteUserDoaImpl(User uid) throws SQLException
   {
       conn = JDBCFactory.getConnection();
       boolean returnParam=false;
       try{
        if(conn!=null)
        {
       Statement stmt=conn.createStatement();
       String deletesql="update Phoenix.user set status=0 where UID="+uid.getuId();
       int duser=stmt.executeUpdate(deletesql);
       if(duser==1)
       {
           returnParam=true;
       }
        }
       }catch(SQLException ex)
       {
       }finally{
           conn.close();
       }
       
       
            return returnParam;
   }
    @Override
    public boolean isUserExists(User vo) throws SQLException
    {
       boolean retParam = false;
       conn=JDBCFactory.getConnection();
       try{
           if(conn!=null)
           {
        Statement stmt=conn.createStatement();
        String sqls="select * from Phoenix.user where status =1 and userid='"+vo.getUserId()+"'";
        ResultSet duser=stmt.executeQuery(sqls);
        if(duser.next()){
            retParam= true;
        }else{
            retParam= false;
        }
         
           }
       }catch(SQLException ex)
       {
       }finally {
           conn.close();
       }
       return retParam;
       }
	   
	    @Override
    public List<User> searchUserWithId(String userid,String roleType) throws SQLException {

        List<User> listOfusers = new ArrayList<User>();
        List<Role> userRoles = new ArrayList<Role>();
        conn = JDBCFactory.getConnection();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "select usr.uid,usr.name,usr.userid,usr.Status,usr.email_id,usr.contact,ur.RID,rl.RName from userrole ur join user usr on usr.UID = ur.UID join role rl on ur.RID = rl.RID where usr.Status=1 and rl.RName='"+roleType+"' and usr.userid like '%"+userid+"%'";
                System.out.println(sql);

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    User userdetails = new User();
                    Role userrole = new Role();
                    List<Role> rl = new ArrayList<Role>();
                    userdetails.setuId(rs.getString("uid"));
                    userdetails.setUserName(rs.getString("Name"));
                    userdetails.setUserId(rs.getString("userid"));
                    userdetails.setEmailId(rs.getString("Email_Id"));
                    userdetails.setContact(rs.getString("Contact"));
                    userrole.setRoleId(rs.getInt("RID"));
                    userrole.setRoleName(rs.getString("RName"));
                    rl.add(userrole);
                    userdetails.setRoles(rl);
                    listOfusers.add(userdetails);
                }

            }
            
        }catch(Exception e){e.printStackTrace();}
        finally{
          conn.close();
          return listOfusers;
        }

    }
    
    public int fetchUid(User vod) throws SQLException{
    conn = JDBCFactory.getConnection();
    Statement stmt=conn.createStatement();
    int uID = 0;
    try{
        if(conn!=null)
        {
          String sql1 = "Select uid from phoenix.user where Userid='" + vod.getUserId() + "'";  
          ResultSet resultSet = stmt.executeQuery(sql1);
                    
                    while (resultSet.next()) {
                        uID = resultSet.getInt("UID");
                    }
        }
    }catch(Exception e)
    {
        
    }
    return uID;
}
}
