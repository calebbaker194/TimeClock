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
		
		System.out.println("Removing Old Tables");
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
		
		System.out.println("Creating Tables");
		
		String employeeTable="CREATE TABLE \"employee\" (\n" + 
				"	\"employee_id\" serial,\n" + 
				"	\"employee_first_name\" TEXT,\n" + 
				"	\"employee_middle_name\" TEXT,\n" + 
				"	\"employee_last_name\" TEXT,\n" + 
				"	\"employee_dob\" date ,\n" + 
				"	\"employee_ssn\" TEXT NOT NULL DEFAULT '0',\n" + 
				"	\"employee_wages\" FLOAT(30) NOT NULL DEFAULT '0',\n" + 
				"	\"employee_password\" TEXT DEFAULT '0',\n" + 
				"	\"employee_issupervisoer\" BOOLEAN NOT NULL DEFAULT 'false',\n" + 
				"	\"employee_hassupervisoer\" BOOLEAN NOT NULL DEFAULT 'false',\n" + 
				"	\"employee_supervisor_id\" integer,\n" + 
				"   \"employee_clockstatus\" BOOLEAN DEFAULT 'false',\n"+      // True = IN : False = OUT
				"   \"employee_lastpunch\" timestamp, \n"+
				"   \"employee_isactive\" BOOLEAN, \n"+
				"	CONSTRAINT employee_pk PRIMARY KEY (\"employee_id\")\n" + 
				") WITH (\n" + 
				"  OIDS=FALSE\n" + 
				");\n";
		tablecount[count++]=print(SQL.executeQuery(employeeTable));

		String tpTable=
				"CREATE TABLE \"tp\" (\n" + 
				"	\"timepunch_id\" serial NOT NULL,\n" + 
				"	\"timepunch_employee_id\" integer NOT NULL DEFAULT '0',\n" + 
				"	\"timepunch_time\" timestamp  NOT NULL,\n" + 
				"	\"timepunch_type\" BOOLEAN ,\n" + 
				"	CONSTRAINT tp_pk PRIMARY KEY (\"timepunch_id\")\n" + 
				") WITH (\n" + 
				"  OIDS=FALSE\n" + 
				");\n";
		tablecount[count++]=print(SQL.executeQuery(tpTable));
		
		String wgTable=
				"CREATE TABLE \"wg\" (\n" + 
				"	\"workgroup_id\" serial NOT NULL,\n" + 
				"	\"workgroup_name\" TEXT NOT NULL,\n" + 
				"	\"workgroup_starttime\" TIME NOT NULL,\n" + 
				"	\"workgroup_stoptime\" TIME NOT NULL,\n" + 
				"	CONSTRAINT wg_pk PRIMARY KEY (\"workgroup_id\")\n" + 
				") WITH (\n" + 
				"  OIDS=FALSE\n" + 
				");\n";
		tablecount[count++]=print(SQL.executeQuery(wgTable));
		
		String wgmember=
				"CREATE TABLE \"wgmember\" (\n" + 
				"	\"workgroupmember_id\" serial NOT NULL,\n" + 
				"	\"workgroupmember_workgroup_id\" integer NOT NULL,\n" + 
				"	\"workgroupmember_shift_id\" integer NOT NULL DEFAULT '1',\n" + 
				"	\"workgroupmember_employee_id\" integer NOT NULL,\n" + 
				"	CONSTRAINT wgmember_pk PRIMARY KEY (\"workgroupmember_id\")\n" + 
				") WITH (\n" + 
				"  OIDS=FALSE\n" + 
				");\n";
		tablecount[count++]=print(SQL.executeQuery(wgmember));	
		
		String svTable=
				"CREATE TABLE \"sv\" (\n" + 
				"	\"sv_id\" serial NOT NULL,\n" + 
				"	\"sv_employee_id\" integer NOT NULL,\n" + 
				"	\"sv_number\" text DEFAULT '0',\n" + 
				"	\"sv_password\" integer NOT NULL,\n" + 
				"	CONSTRAINT sv_pk PRIMARY KEY (\"sv_id\")\n" + 
				") WITH (\n" + 
				"  OIDS=FALSE\n" + 
				");\n";
		tablecount[count++]=print(SQL.executeQuery(svTable));
		
		String adminTable=
				"CREATE TABLE \"admin\" (\n" +
				"	\"admin_id\" serial NOT NULL, \n "+
				"   \"admin_uname\" text NOT NULL, \n"+
				"	\"admin_password\" text NOT NULL, \n"+
				"	\"admin_level\" integer \n,"+
				"	CONSTRAINT admin_pk PRIMARY KEY (\"admin_id\")\n)" +
				" WITH (\n" +
				" OIDS=FALSE );";
		
		tablecount[count++]=print(SQL.executeQuery(adminTable));
		
		
		
		String foreignKey=
				"ALTER TABLE \"employee\" ADD CONSTRAINT \"employee_fk0\" FOREIGN KEY (\"employee_supervisor_id\") REFERENCES \"employee\"(\"employee_id\");\n" + 
				"ALTER TABLE \"tp\" ADD CONSTRAINT \"tp_fk0\" FOREIGN KEY (\"timepunch_employee_id\") REFERENCES \"employee\"(\"employee_id\");\n" + 
				"ALTER TABLE \"wgmember\" ADD CONSTRAINT \"wgmember_fk0\" FOREIGN KEY (\"workgroupmember_workgroup_id\") REFERENCES \"wg\"(\"workgroup_id\");\n" + 
				"ALTER TABLE \"wgmember\" ADD CONSTRAINT \"wgmember_fk1\" FOREIGN KEY (\"workgroupmember_employee_id\") REFERENCES \"employee\"(\"employee_id\");\n" + 
				"ALTER TABLE \"sv\" ADD CONSTRAINT \"sv_fk0\" FOREIGN KEY (\"sv_employee_id\") REFERENCES \"employee\"(\"employee_id\");\n";
		SQL.executeQuery(foreignKey);
		//Special table to check if the db is already here on the db
		
		//Insert the admin record
		
		SQL.executeQuery("INSERT INTO admin VALUES("
				+ "0"
				+ "'"+pusername+"',"
				+ "'"+ppassword+"',"
				+ "'"+0+"')");
		
		// Function section		
		
		//TimePunch
		SQL.executeQuery("CREATE OR REPLACE FUNCTION public.timepunch(\n" + 
				"    pid text,\n" + 
				"    ppassword text)\n" + 
				"  RETURNS integer AS\n" + 
				"$BODY$\n" + 
				"DECLARE \n" + 
				"	empid TEXT;\n" + 
				"	clockstatus BOOLEAN;\n" + 
				"BEGIN\n" + 
				"SELECT employee_id INTO empid FROM employee\n" + 
				"WHERE CAST(employee_id AS TEXT)=pid AND employee_password=ppassword;\n" + 
				"IF empid IS NULL \n" + 
				"THEN\n" + 
				"  RETURN -1;\n" + 
				"END IF;\n" + 
				"SELECT employee_clockstatus \n" + 
				"INTO clockstatus \n" + 
				"FROM employee \n" + 
				"WHERE enployee_id = empid;\n" + 
				"INSERT INTO tp (timepunch_employee_id,timepunch_time,timepunch_type)\n" + 
				"VALUES (empid,LOCALTIMESTAMP,NOT clockstatus);\n" +  
				"UPDATE employee \n" + 
				"SET employee_lastpunch = LOCALTIMESTAMP , emploee_clockstatus = NOT clockstatus\n" + 
				"WHERE employee_id = empid;\n" + 
				"RETURN 0;\n" + 
				"END\n" + 
				"$BODY$\n" + 
				"  LANGUAGE plpgsql VOLATILE\n" + 
				"  COST 100;\n" + 
				"ALTER FUNCTION public.timepunch(text, text)\n" + 
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
