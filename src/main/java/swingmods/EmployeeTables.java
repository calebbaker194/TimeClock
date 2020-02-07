package swingmods;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This is the Class for generating tables model into what ever screen you want. 
 * Tables will be created with static methods and will also have a string for there query. 
 * @author Caleb
 *
 */
public class EmployeeTables {
	
	public static String ClockTableQuery = "SELECT employee_id AS id, employee_last_name || ', ' || employee_first_name AS name,  FROM employee WHERE true";
	@SuppressWarnings("serial")
	public static JTable getClockTable()
	{
		JTable jt = new JTable();
		
		jt.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Name", "Status", "Last Punch", "Time Active"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class 
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		return jt;
	}
	
	public static String SelectTable = "SELECT employee_id AS id, employee_last_name AS lname, employee_first_name AS fname, employee_active AS active FROM employee WHERE true";
	@SuppressWarnings("serial")
	public static JTable getSelectTable()
	{
		JTable jt = new JTable();
		
		jt.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Last Name", "First Name", "Active"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class 
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		return jt;
	}
}
