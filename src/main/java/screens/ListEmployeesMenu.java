package screens;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sqlengine.ResultList;
import sqlengine.SQL;
import swingmods.FormField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
// TODO : The warning labels need updated
public class ListEmployeesMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -313005299187034905L;
	private ResultList employees;
	private JTable listTable;
	
	private FormField fName;
	private FormField mName;
	private FormField lName;
	private FormField ssNumber;
	private JComboBox<String> wgCombo;
	private JComboBox<String> managerCombo;
	
	private boolean isNew = false;
	private boolean fNameEdit = false;
	private boolean mNameEdit = false;
	private boolean lNameEdit = false;
	private boolean ssNumberEdit = false;
	private boolean wgEdit = false;
	private boolean managerEdit = false;
	
	private JButton btnAdd;
	
	private HashMap<String, String> wgIdName;
	private HashMap<String, String> managerIdName;
	
	@SuppressWarnings("serial")
	public ListEmployeesMenu()
	{
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		listTable = new JTable();
		listTable.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				tableClicked(e);
			}
		});
		
		listTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Not Sure"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		if(true) //Code organization. Remove on release
		{
		
		scrollPane.setViewportView(listTable);
		
		JPanel infoPanel = new JPanel();
		splitPane.setRightComponent(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{112, 0, 19, 0};
		gbl_infoPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_infoPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		infoPanel.setLayout(gbl_infoPanel);
		
		JLabel fNameErr = new JLabel("New label");
		fNameErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_fNameErr = new GridBagConstraints();
		gbc_fNameErr.anchor = GridBagConstraints.WEST;
		gbc_fNameErr.gridwidth = 2;
		gbc_fNameErr.insets = new Insets(0, 0, 5, 0);
		gbc_fNameErr.gridx = 1;
		gbc_fNameErr.gridy = 0;
		infoPanel.add(fNameErr, gbc_fNameErr);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 5, 5, 5);
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 1;
		infoPanel.add(lblFirstName, gbc_lblFirstName);
		
		fName = new FormField();
		fName.setHint("First Name");
		GridBagConstraints gbc_fName = new GridBagConstraints();
		gbc_fName.gridwidth = 2;
		gbc_fName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fName.insets = new Insets(0, 0, 5, 0);
		gbc_fName.gridx = 1;
		gbc_fName.gridy = 1;
		infoPanel.add(fName, gbc_fName);
		fName.setColumns(10);
		
		JLabel mNameErr = new JLabel("New label");
		mNameErr.setFont(new Font("Dialog", Font.PLAIN, 10));
		mNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_mNameErr = new GridBagConstraints();
		gbc_mNameErr.anchor = GridBagConstraints.WEST;
		gbc_mNameErr.gridwidth = 2;
		gbc_mNameErr.insets = new Insets(0, 0, 5, 0);
		gbc_mNameErr.gridx = 1;
		gbc_mNameErr.gridy = 2;
		infoPanel.add(mNameErr, gbc_mNameErr);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		GridBagConstraints gbc_lblMiddleName = new GridBagConstraints();
		gbc_lblMiddleName.insets = new Insets(0, 5, 5, 5);
		gbc_lblMiddleName.anchor = GridBagConstraints.WEST;
		gbc_lblMiddleName.gridx = 0;
		gbc_lblMiddleName.gridy = 3;
		infoPanel.add(lblMiddleName, gbc_lblMiddleName);
		
		mName = new FormField();
		mName.setHint("Middle Name");
		GridBagConstraints gbc_mName = new GridBagConstraints();
		gbc_mName.gridwidth = 2;
		gbc_mName.fill = GridBagConstraints.HORIZONTAL;
		gbc_mName.insets = new Insets(0, 0, 5, 0);
		gbc_mName.gridx = 1;
		gbc_mName.gridy = 3;
		infoPanel.add(mName, gbc_mName);
		mName.setColumns(10);
		
		JLabel lNameErr = new JLabel("New label");
		lNameErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_lNameErr = new GridBagConstraints();
		gbc_lNameErr.anchor = GridBagConstraints.WEST;
		gbc_lNameErr.gridwidth = 2;
		gbc_lNameErr.insets = new Insets(0, 0, 5, 5);
		gbc_lNameErr.gridx = 1;
		gbc_lNameErr.gridy = 4;
		infoPanel.add(lNameErr, gbc_lNameErr);
		
		JLabel lblLastname = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.insets = new Insets(0, 5, 5, 5);
		gbc_lblLastname.anchor = GridBagConstraints.WEST;
		gbc_lblLastname.gridx = 0;
		gbc_lblLastname.gridy = 5;
		infoPanel.add(lblLastname, gbc_lblLastname);
		
		lName = new FormField();
		lName.setHint("Last Name");
		GridBagConstraints gbc_lName = new GridBagConstraints();
		gbc_lName.gridwidth = 2;
		gbc_lName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lName.insets = new Insets(0, 0, 5, 0);
		gbc_lName.gridx = 1;
		gbc_lName.gridy = 5;
		infoPanel.add(lName, gbc_lName);
		lName.setColumns(10);
		
		JLabel ssErr = new JLabel("New label");
		ssErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ssErr.setForeground(Color.RED);
		GridBagConstraints gbc_ssErr = new GridBagConstraints();
		gbc_ssErr.anchor = GridBagConstraints.WEST;
		gbc_ssErr.gridwidth = 2;
		gbc_ssErr.insets = new Insets(0, 0, 5, 5);
		gbc_ssErr.gridx = 1;
		gbc_ssErr.gridy = 6;
		infoPanel.add(ssErr, gbc_ssErr);
		
		JLabel lblSocialSecurity = new JLabel("Social Security#:");
		GridBagConstraints gbc_lblSocialSecurity = new GridBagConstraints();
		gbc_lblSocialSecurity.insets = new Insets(0, 5, 5, 5);
		gbc_lblSocialSecurity.anchor = GridBagConstraints.WEST;
		gbc_lblSocialSecurity.gridx = 0;
		gbc_lblSocialSecurity.gridy = 7;
		infoPanel.add(lblSocialSecurity, gbc_lblSocialSecurity);
		
		ssNumber = new FormField();
		ssNumber.setHint("Social Security#");
		GridBagConstraints gbc_ssNumber = new GridBagConstraints();
		gbc_ssNumber.gridwidth = 2;
		gbc_ssNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssNumber.insets = new Insets(0, 0, 5, 0);
		gbc_ssNumber.gridx = 1;
		gbc_ssNumber.gridy = 7;
		infoPanel.add(ssNumber, gbc_ssNumber);
		ssNumber.setColumns(10);
		
		JLabel workGroupErr = new JLabel("New label");
		workGroupErr.setHorizontalAlignment(SwingConstants.LEFT);
		workGroupErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		workGroupErr.setForeground(Color.RED);
		GridBagConstraints gbc_workGroupErr = new GridBagConstraints();
		gbc_workGroupErr.anchor = GridBagConstraints.WEST;
		gbc_workGroupErr.gridwidth = 2;
		gbc_workGroupErr.insets = new Insets(0, 0, 5, 5);
		gbc_workGroupErr.gridx = 1;
		gbc_workGroupErr.gridy = 8;
		infoPanel.add(workGroupErr, gbc_workGroupErr);
		
		JLabel lblWorkGroup = new JLabel("Work Group:");
		GridBagConstraints gbc_lblWorkGroup = new GridBagConstraints();
		gbc_lblWorkGroup.insets = new Insets(0, 5, 5, 5);
		gbc_lblWorkGroup.anchor = GridBagConstraints.WEST;
		gbc_lblWorkGroup.gridx = 0;
		gbc_lblWorkGroup.gridy = 9;
		infoPanel.add(lblWorkGroup, gbc_lblWorkGroup);
		
		wgCombo = new JComboBox<String>();
		GridBagConstraints gbc_wgCombo = new GridBagConstraints();
		gbc_wgCombo.anchor = GridBagConstraints.EAST;
		gbc_wgCombo.gridwidth = 2;
		gbc_wgCombo.insets = new Insets(0, 0, 5, 0);
		gbc_wgCombo.gridx = 1;
		gbc_wgCombo.gridy = 9;
		infoPanel.add(wgCombo, gbc_wgCombo);
		
		JLabel managerErr = new JLabel("New label");
		managerErr.setHorizontalAlignment(SwingConstants.LEFT);
		managerErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		managerErr.setForeground(Color.RED);
		GridBagConstraints gbc_managerErr = new GridBagConstraints();
		gbc_managerErr.anchor = GridBagConstraints.WEST;
		gbc_managerErr.gridwidth = 2;
		gbc_managerErr.insets = new Insets(0, 0, 5, 5);
		gbc_managerErr.gridx = 1;
		gbc_managerErr.gridy = 10;
		infoPanel.add(managerErr, gbc_managerErr);
		
		JLabel lblNewLabel = new JLabel("Manager:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 11;
		infoPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		managerCombo = new JComboBox<String>();
		GridBagConstraints gbc_managerCombo = new GridBagConstraints();
		gbc_managerCombo.anchor = GridBagConstraints.EAST;
		gbc_managerCombo.gridwidth = 2;
		gbc_managerCombo.insets = new Insets(0, 0, 5, 0);
		gbc_managerCombo.gridx = 1;
		gbc_managerCombo.gridy = 11;
		infoPanel.add(managerCombo, gbc_managerCombo);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 12;
		infoPanel.add(panel, gbc_panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);
		
		
		
		JButton cancelEdit = new JButton("Cancel");
		panel.add(cancelEdit);
		
		cancelEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sCancleClicked();
			}
		});
		
		JButton saveEditBtn = new JButton("Save");
		panel.add(saveEditBtn);
		
		btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createEmployee();
			}
		});
		
		saveEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sSaveClicked();
			}
		});
		
		}
	}

	protected int sSaveClicked() {
		if(isNew)
		{
			addEmployee();
		}
		else
		{
			updateEmployee();
		}
		btnAdd.setVisible(true);
		clearEditMarkers();
		
		return 0;
	}

	protected void sCancleClicked() {
		
	}

	protected void tableClicked(MouseEvent e) 
	{
		if(isNew || hasUnsavedEdit())
		{
			int ans = JOptionPane.showConfirmDialog(this,
					"You have unsaved changes. Click yes to save, no to discard, and cancle to review ",
					 "Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION);
			if(ans == JOptionPane.YES_OPTION)
			{
				int check = sSaveClicked();
				if( check != 0)
					return;
			}
			if(ans == JOptionPane.NO_OPTION)
			{
				
			}
			else
			{
				return;
			}
		}	
		populatEmployeeInfo();
		
		
		//Clear Markers After the population call
		//TODO: In the future add a populate() method to form field to automaticaly clear out the edit
		isNew = false;
		clearEditMarkers();
	}

	/*
	 * This populates the info of the employee into the info screen 
	 */
	private void populatEmployeeInfo() 
	{
		int fetchID = Integer.parseInt((String) employees.get(listTable.getSelectedRow()).get("id"));
		ResultList rl = new ResultList(SQL.executeQuery("SELECT employee.* FROM employee WHERE employee_id="+fetchID));
		HashMap<String, Object> rr = rl.get(0);
		
		fName.setText((String) rr.get("employee_first_name"));
		lName.setText((String) rr.get("employee_last_name"));
		mName.setText((String) rr.get("employee_middle_name"));
		ssNumber.setText((String) rr.get("employee_ssn"));
		
		
	}

	private void clearEditMarkers() 
	{
		FormField.resetAllEdits(fName,lName,mName,ssNumber);
		wgEdit = false;
		managerEdit = false;	
	}

	private boolean hasUnsavedEdit() 
	{
		return fName.hasEdit() || lName.hasEdit() || mName.hasEdit() || ssNumber.hasEdit() || wgEdit || managerEdit;
	}

	protected void quereyEmployees()
	{
		String employeeQuery=
				  "SELECT employee_id AS id"
					   + "CONCAT(employee_last_name,', ',employee_first_name) AS name"
					   + "'' AS idk"
				+ "FROM employee";
		employees = new ResultList(SQL.executeQuery(employeeQuery));
		DefaultTableModel tm =((DefaultTableModel)listTable.getModel());
		tm.setRowCount(0);
		
		for(HashMap<String,Object> row : employees)
		{
			Object[] addRow = {row.get("id"),row.get("name"),""};
			tm.addRow(addRow);
		}
		populateManagerComboOptions();
		populateWgComboOptions();
	}
	
	protected void updateEmployee()
	{
		String updateQuery="UPDATE employee"
						 + "SET ";
		 
		if(fNameEdit) {
			updateQuery+="employee_first_name='"+fName.getText()+"', ";
		}
		if(lNameEdit) {
			updateQuery+="employee_last_name='"+lName.getText()+"', ";
		}
		if(mNameEdit) {
			updateQuery+="employee_middle_name='"+mName.getText()+"', ";
		}
		if(managerEdit) {
			updateQuery+="employee_manager_id='"+managerIdName.get(managerCombo.getSelectedItem())+"', ";
		}
		if(ssNumberEdit) {
			updateQuery+="employee_ssn='"+ssNumber.getText()+"'";
		}
		else {
			updateQuery=updateQuery.substring(0, updateQuery.indexOf(','))+"";
		}
		
		updateQuery+="WHERE employee_id='"+employees.get(listTable.getSelectedRow()).get("id")+"';\n\n";
		
		if(wgEdit) {
			SQL.executeQuery("DELETE FROM wgmember WHERE workgroupmember_employee_id ="+employees.get(listTable.getSelectedRow()).get("id")); 
			SQL.executeQuery("INSERT INTO wgmember (workgroupmember_workgroup_id,workgroupmember_employee_id) VALUES ("+wgCombo.getSelectedIndex()+","+employees.get(listTable.getSelectedRow()).get("id")+")");
		}
	}
	
	protected void deleteEmployee()
	{
		SQL.executeQuery("SELECT deleteemployee("+employees.get(listTable.getSelectedRow()).get("id")+") AS ret");
		//TODO Notify Failure or success;
	}
	
	/*
	 * This is used to change the GUI and any fields to start creating an enployee
	 */
	protected void createEmployee()
	{
		isNew=true; 
		btnAdd.setVisible(false);
	}
	
	/*
	 *  This is to call the SQL Side to acutally save the employee Add
	 */
	protected int addEmployee()
	{
		if(fName.isEmpty() || lName.isEmpty())
			return -1;
		String mngID ="";
		if( !(managerCombo == null || managerIdName == null || managerIdName.get(managerCombo.getSelectedIndex()+"") == null))
			mngID =managerIdName.get(managerCombo.getSelectedIndex()+"");
		
		String t = "INSERT INTO employee ("
				+ "employee_first_name,"
				+ "employee_middle_name,"
				+ "employee_last_name,"
				+ "employee_ssn"
				+ (mngID.length() > 0 ? (",employee_supervisor_id) "): ") ")
				+ "VALUES ('"
				+ fName.getText() + "', '"
				+ mName.getText() + "', '"
				+ lName.getText() + "', '"
				+ ssNumber.getText() + "'"
				+ (mngID.length() > 0 ? (","+mngID): "" )
				+")";
		System.out.println(t);
		int empid = (int) SQL.executeQuery("INSERT INTO employee ("
				+ "employee_first_name,"
				+ "employee_middle_name,"
				+ "employee_last_name,"
				+ "employee_ssn"
				+ (mngID.length() > 0 ? (",employee_supervisor_id) "): ") ")
				+ "VALUES ('"
				+ fName.getText() + "', '"
				+ mName.getText() + "', '"
				+ lName.getText() + "', '"
				+ ssNumber.getText() + "'"
				+ (mngID.length() > 0 ? (","+mngID): "" )
				+");").get(0).get("employee_id");
		
		//SQLEngine.executeDBQuery("INSERT INTO wgmember");
		//TODO Add insert into wg 
			return 0;
	}
	
	/*
	 * Populate the Work Group Selection Box
	 */
	protected void populateWgComboOptions()
	{
		String wgQuery = "SELECT workgroup_id AS id, workgroup_name AS name FROM wg";
		ResultList wgs = new ResultList(SQL.executeQuery(wgQuery));
		
		String[] wgsArry = new String[wgs.size()];
		int counter = 0;
		wgIdName = new HashMap<String,String>();
		
		for(HashMap<String, Object> row : wgs)
		{
			wgIdName.put(row.get("name")+"", row.get("id")+"");
			wgsArry[counter++] = row.get("name")+"";
		}
		
		wgCombo.setModel(new DefaultComboBoxModel<String>(wgsArry));
	}
	
	/*
	 * Populate the manager Combo Box
	 */
	protected void populateManagerComboOptions()
	{
		String wgQuery = "SELECT CONCAT(employee_last_name,', ',employee_first_name)";
		ResultList wgs = new ResultList(SQL.executeQuery(wgQuery));
		
		String[] wgsArry = new String[wgs.size()];
		int counter = 0;
		managerIdName = new HashMap<String,String>();

		for(HashMap<String, Object> row : wgs)
		{
			managerIdName.put(row.get("name")+"", row.get("id")+"");
			wgsArry[counter++] = row.get("name")+"";
		}
		
		managerCombo.setModel(new DefaultComboBoxModel<String>(wgsArry));
	}
}
