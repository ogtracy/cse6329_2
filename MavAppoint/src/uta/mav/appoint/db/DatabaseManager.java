package uta.mav.appoint.db;

import java.sql.SQLException;
import java.util.ArrayList;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.beans.WaitList;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.login.UpdatePasswordUser;
import uta.mav.appoint.db.command.CheckUserPassword;


public class DatabaseManager {
		DBImplInterface imp = new RDBImpl();
	
			
	//user login checking, check username and password against database
	//then return role if a match is found
	public Boolean createAdvisor(CreateAdvisorBean ca) throws SQLException{
		return imp.createAdvisor(ca);
	}
		
	public Boolean createStudent(StudentUser studentUser) throws SQLException{
		return imp.createStudent(studentUser);
	}
        
        public Boolean updateStudent(StudentUser studentUser) throws SQLException{
		return imp.updateStudent(studentUser);
	}
	
	public LoginUser checkUser(GetSet set) throws SQLException{
		return imp.checkUser(set);
		}
        
        public String[] getMailIds() {
		return imp.getMailIds();
		}
        public String setWaitList(GetSet set) throws SQLException{
		return imp.waitUser(set);
		}
        
        public ArrayList<Object> checkUser(String email) throws SQLException{
		return imp.getAppointments(email);
		}
	
	public UpdatePasswordUser checkUserPassword(GetSet set) throws SQLException{
		return imp.checkUserPassword(set);
		}
	
	public int addUser(GetSet set) throws SQLException{
		return imp.addUser(set);
	}
	
	public ArrayList<String> getAdvisors() throws SQLException{
		return imp.getAdvisors();
	}
	
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException{
		return imp.getAdvisorSchedule(name);
	}

	public Boolean createAppointment(Appointment a,String email) throws SQLException{
		return imp.createAppointment(a,email);
	}

	public ArrayList<Object> getAppointments(LoginUser user) throws SQLException{
		if (user instanceof AdvisorUser){
			return imp.getAppointments((AdvisorUser)user);
		}
		else if (user instanceof StudentUser){
			return imp.getAppointments((StudentUser)user);
		}
		else if (user instanceof AdminUser){
			return imp.getAppointments((AdminUser)user);
		}
		else
			return null;
	}
        
        public ArrayList<Object> getWaitList() throws SQLException{
            return imp.getWaitList();
        }

	public Boolean cancelAppointment(int id) throws SQLException{
		return imp.cancelAppointment(id);
	}
	
	public String addTimeSlot(AllocateTime at) throws SQLException{
		return imp.addTimeSlot(at);
	}
	
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException{
		return imp.getAppointmentTypes(pname);
	}
	
	public Boolean updateAppointment(Appointment a) throws SQLException{
		return imp.updateAppointment(a);
	}
        
        public String updateNotify(GetSet set) throws SQLException{
		return imp.updateNotify(set);
	}

	public Boolean deleteTimeSlot(AllocateTime at) throws SQLException{
		return imp.deleteTimeSlot(at);
	}

	public Appointment getAppointment(String date, String email) throws SQLException{
		return imp.getAppointment(date,email);
	}

	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.addAppointmentType(user, at);
	}
}

