package manager;

import sqlengine.SQL;

public class Manager {
	
	public static String adminId = null;

	public Manager() {
		
	}

	public static boolean enableAdmin(String username, String password) {
		adminId = (String) SQL.SFQ("SELECT admin_id FROM admin WHERE admin_uname='"+username+"' AND admin_password='"+password+"'");
		return adminId != null;
	}

	public static String getAdminId() {
		return adminId;
	}

	public static boolean timePunch(String empId, String password) {
		return (boolean) SQL.SFQ("SELECT timepunch("+empId+",'"+password+"') = 0 AS result;");
	}

	public static boolean timePunchAdmin(String adminId,String empId) {
		return (boolean) SQL.SFQ("SELECT timepunch("+adminId+","+empId+"') = 0 AS result;");
	}
	
	
}
