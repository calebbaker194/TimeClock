package database;


import java.util.HashMap;
import java.util.List;
import javax.management.InstanceAlreadyExistsException;
import sqlengine.ResultList;
import sqlengine.SQL;

public class InitalizeDatabase
{
	private static boolean init=false;
	public static void  InitDatabase(String pusername, String ppassword)
	{
		init = true;
		// Update this when adding a table
		int[] tablecount = new int[8];
		
		//String to drop all of the tables that I need
		String adminDrop = "DROP TABLE admin CASCADE";
		String employeeDrop = "DROP TABLE employee CASCADE";
		String svDrop = "DROP TABLE sv CASCADE";
		String tpDrop = "DROP TABLE tp CASCADE";
		String tptypeDrop = "DROP TABLE tptype CASCADE";
		String wgDrop = "DROP TABLE wg CASCADE";
		String wgmemberDrop = "DROP TABLE wgmember CASCADE";
		String verify_drop = "DROP TABLE verify CASCADE";
		
		//Drop all of the tables in case I had a bad init.
		SQL.executeQuery(adminDrop);
		SQL.executeQuery(employeeDrop);
		SQL.executeQuery(svDrop);
		SQL.executeQuery(tpDrop);
		SQL.executeQuery(tptypeDrop);
		SQL.executeQuery(wgDrop);
		SQL.executeQuery(wgmemberDrop);
		SQL.executeQuery(verify_drop);
		
		// Schema section? --Depends If I figure out how well to do it, or to put it in the next iteration;
		
		// Table section
		int count = 0;
		
		String employeeTable="CREATE TABLE \"employee\" (\r\n" + 
				"	\"employee_id\" serial,\r\n" + 
				"	\"employee_first_name\" TEXT,\r\n" + 
				"	\"employee_middle_name\" TEXT,\r\n" + 
				"	\"employee_last_name\" TEXT,\r\n" + 
				"	\"employee_dob\" date ,\r\n" + 
				"	\"employee_ssn\" TEXT NOT NULL DEFAULT '0',\r\n" + 
				"	\"employee_wages\" FLOAT(30) NOT NULL DEFAULT '0',\r\n" + 
				"	\"employee_password\" TEXT DEFAULT '0',\r\n" + 
				"	\"employee_issupervisoer\" BOOLEAN NOT NULL DEFAULT 'false',\r\n" + 
				"	\"employee_hassupervisoer\" BOOLEAN NOT NULL DEFAULT 'false',\r\n" + 
				"	\"employee_supervisor_id\" integer,\r\n" + 
				"   \"employee_clockstatus\" BOOLEAN DEFAULT 'false',\r\n"+      // True = IN : False = OUT
				"   \"employee_lastpunch\" timestamp, \r\n"+
				"   \"employee_isactive\" BOOLEAN, \r\n"+
				"	CONSTRAINT employee_pk PRIMARY KEY (\"employee_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		tablecount[count++]=print(SQL.executeQuery(employeeTable));

		String tpTable=
				"CREATE TABLE \"tp\" (\r\n" + 
				"	\"timepunch_id\" serial NOT NULL,\r\n" + 
				"	\"timepunch_employee_id\" integer NOT NULL DEFAULT '0',\r\n" + 
				"	\"timepunch_time\" timestamp  NOT NULL,\r\n" + 
				"	\"timepunch_type\" BOOLEAN ,\r\n" + 
				"	CONSTRAINT tp_pk PRIMARY KEY (\"timepunch_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		tablecount[count++]=print(SQL.executeQuery(tpTable));
		
		String wgTable=
				"CREATE TABLE \"wg\" (\r\n" + 
				"	\"workgroup_id\" serial NOT NULL,\r\n" + 
				"	\"workgroup_name\" TEXT NOT NULL,\r\n" + 
				"	\"workgroup_starttime\" TIME NOT NULL,\r\n" + 
				"	\"workgroup_stoptime\" TIME NOT NULL,\r\n" + 
				"	CONSTRAINT wg_pk PRIMARY KEY (\"workgroup_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		tablecount[count++]=print(SQL.executeQuery(wgTable));
		
		String wgmember=
				"CREATE TABLE \"wgmember\" (\r\n" + 
				"	\"workgroupmember_id\" serial NOT NULL,\r\n" + 
				"	\"workgroupmember_workgroup_id\" integer NOT NULL,\r\n" + 
				"	\"workgroupmember_shift_id\" integer NOT NULL DEFAULT '1',\r\n" + 
				"	\"workgroupmember_employee_id\" integer NOT NULL,\r\n" + 
				"	CONSTRAINT wgmember_pk PRIMARY KEY (\"workgroupmember_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		tablecount[count++]=print(SQL.executeQuery(wgmember));	
		
		String svTable=
				"CREATE TABLE \"sv\" (\r\n" + 
				"	\"sv_id\" serial NOT NULL,\r\n" + 
				"	\"sv_employee_id\" integer NOT NULL,\r\n" + 
				"	\"sv_number\" text DEFAULT '0',\r\n" + 
				"	\"sv_password\" integer NOT NULL,\r\n" + 
				"	CONSTRAINT sv_pk PRIMARY KEY (\"sv_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		tablecount[count++]=print(SQL.executeQuery(svTable));
		
		String adminTable=
				"CREATE TABLE \"admin\" (\r\n" +
				"	\"admin_id\" serial NOT NULL, \r\n "+
				"   \"admin_uname\" text NOT NULL, \r\n"+
				"	\"admin_password\" text NOT NULL, \r\n"+
				"	\"admin_level\" integer \r\n,"+
				"	CONSTRAINT admin_pk PRIMARY KEY (\"admin_id\")\r\n)" +
				" WITH (\r\n" +
				" OIDS=FALSE );";
		
		tablecount[count++]=print(SQL.executeQuery(adminTable));
		
		
		
		String foreignKey=
				"ALTER TABLE \"employee\" ADD CONSTRAINT \"employee_fk0\" FOREIGN KEY (\"employee_supervisor_id\") REFERENCES \"employee\"(\"employee_id\");\r\n" + 
				"ALTER TABLE \"tp\" ADD CONSTRAINT \"tp_fk0\" FOREIGN KEY (\"timepunch_employee_id\") REFERENCES \"employee\"(\"employee_id\");\r\n" + 
				"ALTER TABLE \"wgmember\" ADD CONSTRAINT \"wgmember_fk0\" FOREIGN KEY (\"workgroupmember_workgroup_id\") REFERENCES \"wg\"(\"workgroup_id\");\r\n" + 
				"ALTER TABLE \"wgmember\" ADD CONSTRAINT \"wgmember_fk1\" FOREIGN KEY (\"workgroupmember_employee_id\") REFERENCES \"employee\"(\"employee_id\");\r\n" + 
				"ALTER TABLE \"sv\" ADD CONSTRAINT \"sv_fk0\" FOREIGN KEY (\"sv_employee_id\") REFERENCES \"employee\"(\"employee_id\");\r\n";
		SQL.executeQuery(foreignKey);
		//Special table to check if the db is already here on the db
		String dbverify="CREATE TABLE \"verify\" (\r\n" + 
				"	\"verify_id\" serial,\r\n" + 
				"	\"table_employee\" TEXT DEFAULT '-1',\r\n" + 
				"	\"table_tp\" TEXT DEFAULT '-1',\r\n" +
				"	\"table_wg\" TEXT DEFAULT '-1',\r\n" + 
				"	\"table_wgmember\" TEXT DEFAULT '-1',\r\n" + 
				"	\"table_sv\" TEXT DEFAULT '-1',\r\n" + 
				"	\"table_admin\" TEXT DEFAULT '-1',\r\n"+
				"	CONSTRAINT verify_id PRIMARY KEY (\"verify_id\")\r\n" + 
				") WITH (\r\n" + 
				"  OIDS=FALSE\r\n" + 
				");\r\n";
		SQL.executeQuery(dbverify);
		
		//Insert the Verify Record
		
		SQL.executeQuery("INSERT INTO verify VALUES('"+tablecount[0]+
				"','"+tablecount[1]+
				"','"+tablecount[2]+
				"','"+tablecount[3]+
				"','"+tablecount[4]+
				"','"+tablecount[5]+
				"','"+tablecount[6]+
				"','"+tablecount[7]+"')");
		
		//Insert the admin record
		
		SQL.executeQuery("INSERT INTO admin VALUES("
				+ "0"
				+ "'"+pusername+"',"
				+ "'"+ppassword+"',"
				+ "'"+0+"')");
		
		// Function section		
		
		//TimePunch
		SQL.executeQuery("CREATE OR REPLACE FUNCTION public.timepunch(\r\n" + 
				"    pid text,\r\n" + 
				"    ppassword text)\r\n" + 
				"  RETURNS integer AS\r\n" + 
				"$BODY$\r\n" + 
				"DECLARE \r\n" + 
				"	empid TEXT;\r\n" + 
				"	clockstatus BOOLEAN;\r\n" + 
				"BEGIN\r\n" + 
				"SELECT employee_id INTO empid FROM employee\r\n" + 
				"WHERE CAST(employee_id AS TEXT)=pid AND employee_password=ppassword;\r\n" + 
				"IF empid IS NULL \r\n" + 
				"THEN\r\n" + 
				"  RETURN -1;\r\n" + 
				"END IF;\r\n" + 
				"SELECT employee_clockstatus \r\n" + 
				"INTO clockstatus \r\n" + 
				"FROM employee \r\n" + 
				"WHERE enployee_id = empid;\r\n" + 
				"INSERT INTO tp (timepunch_employee_id,timepunch_time,timepunch_type)\r\n" + 
				"VALUES (empid,LOCALTIMESTAMP,NOT clockstatus);\r\n" +  
				"UPDATE employee \r\n" + 
				"SET employee_lastpunch = LOCALTIMESTAMP , emploee_clockstatus = NOT clockstatus\r\n" + 
				"WHERE employee_id = empid;\r\n" + 
				"RETURN 0;\r\n" + 
				"END\r\n" + 
				"$BODY$\r\n" + 
				"  LANGUAGE plpgsql VOLATILE\r\n" + 
				"  COST 100;\r\n" + 
				"ALTER FUNCTION public.timepunch(text, text)\r\n" + 
				"  OWNER TO postgres;");
		//TimePunchAdmin 
		SQL.executeQuery("CREATE OR REPLACE FUNCTION public.timepunch(\n" + 
				"    padmin_id integer,\n" + 
				"    pempid integer)\n" + 
				"  RETURNS integer AS\n" + 
				"$BODY$\n" + 
				"DECLARE \n" + 
				"	empid INTEGER;\n" + 
				"	padmintest INTEGER;\n" + 
				"	clockstatus BOOLEAN;\n" + 
				"BEGIN\n" + 
				"\n" + 
				"SELECT employee_id INTO empid FROM employee\n" + 
				"WHERE employee_id = pempid;\n" + 
				"\n" + 
				"IF empid IS NULL \n" + 
				"THEN\n" + 
				"  RETURN -1;-- THE Employee Doesnt Exsist\n" + 
				"END IF;\n" + 
				"\n" + 
				"SELECT admin_id INTO padmintest\n" + 
				"FROM admin \n" + 
				"WHERE admin_id = padmin_id;\n" + 
				"\n" + 
				"IF padmintest IS NULL \n" + 
				"THEN\n" + 
				"  RETURN -1;-- The manager id doesnt exists\n" + 
				"END IF;\n" + 
				"\n" + 
				"SELECT employee_clockstatus \n" + 
				"INTO clockstatus \n" + 
				"FROM employee \n" + 
				"WHERE employee_id = empid;\n" + 
				"INSERT INTO tp (timepunch_employee_id,timepunch_time,timepunch_type)\n" + 
				"VALUES (empid,LOCALTIMESTAMP,NOT clockstatus);\n" + 
				"UPDATE employee \n" + 
				"SET employee_lastpunch = LOCALTIMESTAMP , employee_clockstatus = NOT clockstatus\n" + 
				"WHERE employee_id = empid;\n" + 
				"RETURN 0;\n" + 
				"END\n" + 
				"$BODY$\n" + 
				"  LANGUAGE plpgsql VOLATILE\n" + 
				"  COST 100;\n" + 
				"ALTER FUNCTION public.timepunch(integer, integer)\n" + 
				"  OWNER TO postgres;\n" + 
				"");
		
		//TimePunch 
		SQL.executeQuery("-- Function: timepunch(integer, text)\n" + 
				"\n" + 
				"-- DROP FUNCTION timepunch(integer, text);\n" + 
				"\n" + 
				"CREATE OR REPLACE FUNCTION timepunch(pid integer, ppassword text)\n" + 
				"  RETURNS integer AS\n" + 
				"$BODY$\n" + 
				"DECLARE \n" + 
				"	empid INTEGER;\n" + 
				"	clockstatus BOOLEAN;\n" + 
				"BEGIN\n" + 
				"SELECT employee_id INTO empid FROM employee\n" + 
				"WHERE employee_id = pid AND employee_password=ppassword;\n" + 
				"IF empid IS NULL \n" + 
				"THEN\n" + 
				"  RETURN -1;\n" + 
				"END IF;\n" + 
				"SELECT employee_clockstatus \n" + 
				"INTO clockstatus \n" + 
				"FROM employee \n" + 
				"WHERE employee_id = empid;\n" + 
				"INSERT INTO tp (timepunch_employee_id,timepunch_time,timepunch_type)\n" + 
				"VALUES (empid,LOCALTIMESTAMP,NOT clockstatus);\n" + 
				"UPDATE employee \n" + 
				"SET employee_lastpunch = LOCALTIMESTAMP , employee_clockstatus = NOT clockstatus\n" + 
				"WHERE employee_id = empid;\n" + 
				"RETURN 0;\n" + 
				"END\n" + 
				"$BODY$\n" + 
				"  LANGUAGE plpgsql VOLATILE\n" + 
				"  COST 100;\n" + 
				"ALTER FUNCTION timepunch(integer, text)\n" + 
				"  OWNER TO postgres;\n" + 
				"");
		
		System.out.println("Finished");
	}
	private static int print(List<HashMap<String, Object>> list) {
		
//		if(list >=0)
//		{
//			System.out.println("Update Succesfull with "+list+" rows Affected");
//		}
//		else
//		{
//			init&=false;
//		}
//		return list;
		return 1;
	}
	public static void createDatabase(String pusername, String ppassword, String host, int port)
	{
		SQL.Connect("postgres", host, port, pusername, ppassword);
		
		SQL.executeQuery("CREATE DATABASE \"cttimeclock\"\n" + 
				"  WITH OWNER = " +pusername+"\n" + 
				"       ENCODING = 'UTF8'\n" + 
				"       TABLESPACE = pg_default\n" + 
				"       LC_COLLATE = 'English_United States.1252'\n" + 
				"       LC_CTYPE = 'English_United States.1252'\n" + 
				"       CONNECTION LIMIT = -1;\n" + 
				"");
		SQL.Connect("cttimeclock", host, port, pusername, ppassword);
		InitDatabase(pusername, ppassword);;
		
	}
}
