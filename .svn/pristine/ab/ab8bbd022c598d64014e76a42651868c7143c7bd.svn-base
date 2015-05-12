package sg.edu.nus.iss.phoenix.authenticate.service;

import java.sql.SQLException;

import java.util.logging.*;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

import sg.edu.nus.iss.phoenix.role.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public class AuthenticateService {

	private static final Logger logger = 
			Logger.getLogger(AuthenticateService.class.getName());

	DAOFactory factory;
	UserDao udao;
	RoleDao rdao;

	public AuthenticateService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactory();
		udao = factory.getUserDAO();
		rdao = factory.getRoleDAO();

	}

/*
	public User validateUserIdPassword(User user) {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			userList = (ArrayList<User>) udao.loadAll();
			for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
				User user1 = (User) iterator.next();
				if (user.getId().equalsIgnoreCase(user1.getId()) && user.getPassword().equalsIgnoreCase(user1.getPassword()) ) {
					return user1;
				}
				//System.out.println(user1.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
*/
	//The user that we pass in to authenticate is different from the
	//instance that is returned
	public User validateUserIdPassword(final User toAuth) {
		User found = null;
		try {
			found = udao.searchMatching(toAuth);
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "user searchMatching", ex);
			return (null);
		}
		return (found);
	}

	public User evaluateAccessPreviledge(User user) {
//		try {
//			Role role = rdao.getObject(user.getRoles().get(0).getRole());
//			//user.setAccessPrivilege(role.getAccessPrivilege());
//			return user;
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return user;
            return null;
	}
}
