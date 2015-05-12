/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.user.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sg.edu.nus.iss.phoenix.role.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author Preeti
 */
public class UserService {

    RoleDao roleDao;
    UserDao userDao;

    public UserService() {
        DAOFactory factory = new DAOFactory();
        roleDao = factory.getRoleDAO();
        userDao = factory.getUserDAO();
    }

    public List<Role> getAllRolesService() throws SQLException {
        return roleDao.loadRoles();
    }

    public boolean validateDetailsService(User val) throws SQLException {
        return userDao.isUserExists(val);
    }

    public boolean saveDetailsService(User vaUser) throws SQLException {
        return userDao.insertUserDetails(vaUser);

    }

    public User getUserDetails(String id) throws SQLException {
        return userDao.getUserDetails(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public List<User> displayAllUserService() throws SQLException {
        List<User> userL = userDao.loadAllUsers();
        return getUserWithRoles(userL);

    }

    public Boolean ModifyUserService(User user) throws SQLException {
        return userDao.saveModifiedDetails(user);

    }

    public boolean userDeleteService(User ud) throws SQLException {
        return userDao.deleteUserDoaImpl(ud);
    }

    private List<User> getUserWithRoles(List<User> userL) {
        Map<Integer, User> resultMap = new HashMap<Integer, User>();
        ListMultimap<Integer, User> map = ArrayListMultimap.create();
        for (User a : userL) {
            map.put(Integer.parseInt(a.getuId()), a);
        }
        for (Map.Entry<Integer, User> ent : map.entries()) {
            if (!resultMap.containsKey(ent.getKey())) {
                resultMap.put(ent.getKey(), ent.getValue());
            } else {
                User us = ent.getValue();
                //us.getRoles().addAll(us.getRoles());
                User resUser = resultMap.get(ent.getKey());
                resUser.getRoles().addAll(us.getRoles());
            }
        }
        return new ArrayList<>(resultMap.values());
    }

    public List<User> getPresenterProducerList(String fetchRole) throws SQLException {

        return getUserWithRoles(userDao.getPresenterProducerList(fetchRole));
    } 
     
     
         public List<User>searchUserWithId(String userid,String roleType)throws SQLException
    {
        
         return getUserWithRoles(userDao.searchUserWithId(userid,roleType));
    }
         
         public int fetchId(User vod) throws SQLException
         {
             return userDao.fetchUid(vod);
         }
         
         public int fetchRoleId(User vd) throws SQLException
         {
             return roleDao.fetchRid(vd);
         }
         
         public int fetchRoleIdForName(String roleName){
             return roleDao.fetchRoleIdForName(roleName);
             
         }
}
