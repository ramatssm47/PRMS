/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.user.delegate;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author Gautam,Preeti
 */
public class UserDelegate {
    
    
    UserService userService=new UserService();
    public List<Role> getAllRolesDelegate() throws SQLException {
    
    return userService.getAllRolesService();
    
}
    
    public boolean userDeleteDelegate(User uid) throws SQLException
    {
        return userService.userDeleteService(uid);
    }
    public boolean saveDetailsDelegate(User vaUser) throws SQLException
    {
        return userService.saveDetailsService(vaUser);
    }
    
      public boolean validateDetailsDelegate(User v) throws SQLException
    {
        return userService.validateDetailsService(v);
    }
    public List<User> displayAllUserDelegate() throws SQLException
    {
        return userService.displayAllUserService();
    }
    public User getUserDetails(String id) throws SQLException
    {
        return userService.getUserDetails(id);
    }
    
    public List<User> getAllUsers() throws SQLException
    {
        return userService.getAllUsers();
    }
    
    
    public Boolean ModifyUserDelegate(User user)throws SQLException
    {
        
        return userService.ModifyUserService(user);
    }
    
    public List<User> getPresenterProducerList(String fetchRole) throws SQLException{
        return userService.getPresenterProducerList(fetchRole);
}

    public List<User>searchUserWithId(String userid,String roleType)throws SQLException
    {
        return userService.searchUserWithId(userid,roleType);
    }
    
}