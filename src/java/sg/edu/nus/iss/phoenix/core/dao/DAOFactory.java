package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.role.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.role.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.user.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDAOImpl;

public class DAOFactory {
	private UserDao userDAO = new UserDaoImpl();
	private RoleDao roleDAO = new RoleDaoImpl();
	private RadioProgramDAO rpdao = new RadioProgramDAOImpl();
        private ScheduleDAO psDAO = new ScheduleDAOImpl();
        private AnnualScheduleDAO asDAO=new AnnualScheduleDAOImpl();
        private WeeklyScheduleDAO wsDAO=new WeeklyScheduleDAOImpl();
	public UserDao getUserDAO() {
		// TODO Auto-generated method stub
		return userDAO;
	}

	
	public RoleDao getRoleDAO() {
		// TODO Auto-generated method stub
		return roleDAO;
	}

	
	public RadioProgramDAO getRadioProgramDAO() {
		// TODO Auto-generated method stub
		return rpdao;
	}
        
        public ScheduleDAO getPsDAO() {
        return psDAO;
        }
        
        public AnnualScheduleDAO getAsDAO(){
            return asDAO;
        }
        public WeeklyScheduleDAO getWsDAO(){
            return wsDAO;
        }
        
        }
