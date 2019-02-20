package screens;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;
import swingmods.FormField;
import javax.swing.SwingConstants;
import sqlengine.ResultList;
import sqlengine.SQL;
import sreeninterface.EmployeeBrowser;
import sreeninterface.ListInfoMod;

import javax.swing.JComboBox;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class EmployeeInfoMenu extends JPanel implements ListInfoMod{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7216251536946804112L;
	
	
	private FormField fName;
	private FormField mName;
	private FormField lName;
	private FormField ssNumber;
	private EmployeeBrowser sibling;
	private JComboBox<String> wgCombo;
	private JCheckBox isManager;
	private int employeeId = -1;
	private boolean createNew = false;
	private boolean editMode = false;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnCancel;
	private JPanel formFields;
	
	public EmployeeInfoMenu(EmployeeBrowser sibling) {
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
		
		setSibling(sibling);
		formFields = new JPanel();
		formFields.setMaximumSize(new Dimension(300, 500));
		formFields.setBackground(Color.BLUE);
		
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		formFields.setLayout(gbl_infoPanel);
		
		JLabel fNameErr = new JLabel("First Name Required");
		fNameErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_fNameErr = new GridBagConstraints();
		gbc_fNameErr.anchor = GridBagConstraints.WEST;
		gbc_fNameErr.gridwidth = 2;
		gbc_fNameErr.insets = new Insets(0, 0, 5, 0);
		gbc_fNameErr.gridx = 1;
		gbc_fNameErr.gridy = 0;
		formFields.add(fNameErr, gbc_fNameErr);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 5, 5, 5);
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 1;
		formFields.add(lblFirstName, gbc_lblFirstName);
		
		fName = new FormField();
		fName.setHint("First Name");
		GridBagConstraints gbc_fName = new GridBagConstraints();
		gbc_fName.gridwidth = 2;
		gbc_fName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fName.insets = new Insets(0, 0, 5, 0);
		gbc_fName.gridx = 1;
		gbc_fName.gridy = 1;
		formFields.add(fName, gbc_fName);
		fName.setColumns(10);
		
		JLabel mNameErr = new JLabel("   ");
		mNameErr.setFont(new Font("Dialog", Font.PLAIN, 10));
		mNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_mNameErr = new GridBagConstraints();
		gbc_mNameErr.anchor = GridBagConstraints.WEST;
		gbc_mNameErr.gridwidth = 2;
		gbc_mNameErr.insets = new Insets(0, 0, 5, 0);
		gbc_mNameErr.gridx = 1;
		gbc_mNameErr.gridy = 2;
		formFields.add(mNameErr, gbc_mNameErr);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		GridBagConstraints gbc_lblMiddleName = new GridBagConstraints();
		gbc_lblMiddleName.insets = new Insets(0, 5, 5, 5);
		gbc_lblMiddleName.anchor = GridBagConstraints.WEST;
		gbc_lblMiddleName.gridx = 0;
		gbc_lblMiddleName.gridy = 3;
		formFields.add(lblMiddleName, gbc_lblMiddleName);
		
		mName = new FormField();
		mName.setHint("Middle Name");
		GridBagConstraints gbc_mName = new GridBagConstraints();
		gbc_mName.gridwidth = 2;
		gbc_mName.fill = GridBagConstraints.HORIZONTAL;
		gbc_mName.insets = new Insets(0, 0, 5, 0);
		gbc_mName.gridx = 1;
		gbc_mName.gridy = 3;
		formFields.add(mName, gbc_mName);
		mName.setColumns(10);
		
		JLabel lNameErr = new JLabel("Last Name Required");
		lNameErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lNameErr.setForeground(Color.RED);
		GridBagConstraints gbc_lNameErr = new GridBagConstraints();
		gbc_lNameErr.anchor = GridBagConstraints.WEST;
		gbc_lNameErr.gridwidth = 2;
		gbc_lNameErr.insets = new Insets(0, 0, 5, 0);
		gbc_lNameErr.gridx = 1;
		gbc_lNameErr.gridy = 4;
		formFields.add(lNameErr, gbc_lNameErr);
		
		JLabel lblLastname = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.insets = new Insets(0, 5, 5, 5);
		gbc_lblLastname.anchor = GridBagConstraints.WEST;
		gbc_lblLastname.gridx = 0;
		gbc_lblLastname.gridy = 5;
		formFields.add(lblLastname, gbc_lblLastname);
		
		lName = new FormField();
		lName.setHint("Last Name");
		GridBagConstraints gbc_lName = new GridBagConstraints();
		gbc_lName.gridwidth = 2;
		gbc_lName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lName.insets = new Insets(0, 0, 5, 0);
		gbc_lName.gridx = 1;
		gbc_lName.gridy = 5;
		formFields.add(lName, gbc_lName);
		lName.setColumns(10);
		
		JLabel ssErr = new JLabel("   ");
		ssErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ssErr.setForeground(Color.RED);
		GridBagConstraints gbc_ssErr = new GridBagConstraints();
		gbc_ssErr.anchor = GridBagConstraints.WEST;
		gbc_ssErr.gridwidth = 2;
		gbc_ssErr.insets = new Insets(0, 0, 5, 0);
		gbc_ssErr.gridx = 1;
		gbc_ssErr.gridy = 6;
		formFields.add(ssErr, gbc_ssErr);
		
		JLabel lblSocialSecurity = new JLabel("Social Security#:");
		GridBagConstraints gbc_lblSocialSecurity = new GridBagConstraints();
		gbc_lblSocialSecurity.insets = new Insets(0, 5, 5, 5);
		gbc_lblSocialSecurity.anchor = GridBagConstraints.WEST;
		gbc_lblSocialSecurity.gridx = 0;
		gbc_lblSocialSecurity.gridy = 7;
		formFields.add(lblSocialSecurity, gbc_lblSocialSecurity);
		
		ssNumber = new FormField();
		ssNumber.setHint("Social Security#");
		GridBagConstraints gbc_ssNumber = new GridBagConstraints();
		gbc_ssNumber.gridwidth = 2;
		gbc_ssNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssNumber.insets = new Insets(0, 0, 5, 0);
		gbc_ssNumber.gridx = 1;
		gbc_ssNumber.gridy = 7;
		formFields.add(ssNumber, gbc_ssNumber);
		ssNumber.setColumns(10);
		
		JLabel workGroupErr = new JLabel("  ");
		workGroupErr.setHorizontalAlignment(SwingConstants.LEFT);
		workGroupErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		workGroupErr.setForeground(Color.RED);
		GridBagConstraints gbc_workGroupErr = new GridBagConstraints();
		gbc_workGroupErr.anchor = GridBagConstraints.WEST;
		gbc_workGroupErr.gridwidth = 2;
		gbc_workGroupErr.insets = new Insets(0, 0, 5, 0);
		gbc_workGroupErr.gridx = 1;
		gbc_workGroupErr.gridy = 8;
		formFields.add(workGroupErr, gbc_workGroupErr);
		
		JLabel lblWorkGroup = new JLabel("Work Group:");
		GridBagConstraints gbc_lblWorkGroup = new GridBagConstraints();
		gbc_lblWorkGroup.insets = new Insets(0, 5, 5, 5);
		gbc_lblWorkGroup.anchor = GridBagConstraints.WEST;
		gbc_lblWorkGroup.gridx = 0;
		gbc_lblWorkGroup.gridy = 9;
		formFields.add(lblWorkGroup, gbc_lblWorkGroup);
		
		wgCombo = new JComboBox<String>();
		GridBagConstraints gbc_wgCombo = new GridBagConstraints();
		gbc_wgCombo.anchor = GridBagConstraints.EAST;
		gbc_wgCombo.gridwidth = 2;
		gbc_wgCombo.insets = new Insets(0, 0, 5, 0);
		gbc_wgCombo.gridx = 1;
		gbc_wgCombo.gridy = 9;
		formFields.add(wgCombo, gbc_wgCombo);
		
		JLabel managerErr = new JLabel("  ");
		managerErr.setHorizontalAlignment(SwingConstants.LEFT);
		managerErr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		managerErr.setForeground(Color.RED);
		GridBagConstraints gbc_managerErr = new GridBagConstraints();
		gbc_managerErr.anchor = GridBagConstraints.WEST;
		gbc_managerErr.gridwidth = 2;
		gbc_managerErr.insets = new Insets(0, 0, 5, 0);
		gbc_managerErr.gridx = 1;
		gbc_managerErr.gridy = 10;
		formFields.add(managerErr, gbc_managerErr);
		
		JLabel lblManger = new JLabel("Manger:");
		GridBagConstraints gbc_lblManger = new GridBagConstraints();
		gbc_lblManger.insets = new Insets(0, 0, 5, 5);
		gbc_lblManger.gridx = 0;
		gbc_lblManger.gridy = 11;
		formFields.add(lblManger, gbc_lblManger);
		
		isManager = new JCheckBox(" ");
		isManager.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_isManager = new GridBagConstraints();
		gbc_isManager.anchor = GridBagConstraints.WEST;
		gbc_isManager.insets = new Insets(0, 0, 5, 5);
		gbc_isManager.gridx = 1;
		gbc_isManager.gridy = 11;
		formFields.add(isManager, gbc_isManager);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 12;
		add(formFields);
		add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);
		panel.setMaximumSize(new Dimension(300, 100));
		panel.setBackground(Color.RED);
		
		
		btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelClicked();
			}
		});
		
		btnSave = new JButton("Save");
		panel.add(btnSave);
		
		btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newEmployee();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveClicked();
			}
		});
		
		btnCancel.setEnabled(false);
		btnSave.setEnabled(false);
		fName.setEnabled(false);
		mName.setEnabled(false);
		lName.setEnabled(false);
		ssNumber.setEnabled(false);
		
		Component rigidArea = Box.createRigidArea(new Dimension(200, 200));
		add(rigidArea);
	}
	
	public void newId(int empId) {
		
		setCreateNew(false);
		
		ResultList rl = new ResultList(SQL.executeQuery("SELECT employee.* FROM employee WHERE employee_id="+empId));
		HashMap<String, Object> rr = rl.get(0);
		
		
		fName.setForeground(Color.BLACK);
		lName.setForeground(Color.BLACK);
		mName.setForeground(Color.BLACK);
		ssNumber.setForeground(Color.BLACK);
		fName.setText((String) rr.get("employee_first_name"));
		lName.setText((String) rr.get("employee_last_name"));
		mName.setText((String) rr.get("employee_middle_name"));
		ssNumber.setText((String) rr.get("employee_ssn"));
		
		
		
		btnAdd.setEnabled(false);
		btnCancel.setEnabled(true);
		btnSave.setEnabled(true);
		fName.setEnabled(true);
		mName.setEnabled(true);
		lName.setEnabled(true);
		ssNumber.setEnabled(true);
		
		setEditMode(true);
	}
	
	public void newEmployee() 
	{
		fName.setText("");
		lName.setText("");
		mName.setText("");
		ssNumber.setText("");
		
		clearEditMarkers();
		
		employeeId = -1;
		
		setCreateNew(true);
		
		btnAdd.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
		fName.setEnabled(true);
		mName.setEnabled(true);
		lName.setEnabled(true);
		ssNumber.setEnabled(true);
		
	}
	
	public void saveClicked() {
		
		btnAdd.setEnabled(true);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		fName.setEnabled(false);
		mName.setEnabled(false);
		lName.setEnabled(false);
		ssNumber.setEnabled(false);
		
		if(createNew)
		{
			addEmployee();
		}
		else
		{
			updateEmployee();
		}
		clearEditMarkers();
		
		sibling.employeesUpdated();
	}
	
	private void clearEditMarkers()
	{
		FormField.resetAllEdits(fName,lName,mName,ssNumber);
		isManager.setSelected(false);
	}

	public void cancelClicked() {
		fName.setText("");
		lName.setText("");
		mName.setText("");
		ssNumber.setText("");
		
		clearEditMarkers();
		setEditMode(false);
		setCreateNew(false);
		
		btnAdd.setEnabled(true);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		fName.setEnabled(false);
		mName.setEnabled(false);
		lName.setEnabled(false);
		ssNumber.setEnabled(false);

		employeeId = -1;
		sibling.clearSelected();
	}
	
	protected void updateEmployee()
	{
		String updateQuery="UPDATE employee"
						 + "SET ";
		 
		if(fName.hasEdit()) {
			updateQuery+="employee_first_name='"+fName.getText()+"', ";
		}
		if(lName.hasEdit()) {
			updateQuery+="employee_last_name='"+lName.getText()+"', ";
		}
		if(mName.hasEdit()) {
			updateQuery+="employee_middle_name='"+mName.getText()+"', ";
		}
		if(isManager.isSelected()) {
			updateQuery+="employee_manager_id='"+1+"', ";
		}
		if(ssNumber.hasEdit()) {
			updateQuery+="employee_ssn='"+ssNumber.getText()+"'";
		}
		else if(updateQuery.length() > 20){
			updateQuery=updateQuery.substring(0, updateQuery.indexOf(','))+"";
		}
		else if(updateQuery.length() < 25)
		{	
			System.out.println("Fail");
			return;
		}
		
		updateQuery+="WHERE employee_id='"+employeeId+"';\n\n";
		
		SQL.executeQuery(updateQuery);
	}
	
	protected int addEmployee()
	{
		if(fName.isEmpty() || lName.isEmpty())
		{
			// TODO: Add warning
			return -1;	
		}
		
		
		String t = "INSERT INTO employee ("
				+ "employee_first_name,"
				+ "employee_middle_name,"
				+ "employee_last_name,"
				+ "employee_ssn"
				+ (isManager.isSelected() ? (",employee_supervisor_id) "): ") ")
				+ "VALUES ('"
				+ fName.getText() + "', '"
				+ mName.getText() + "', '"
				+ lName.getText() + "', '"
				+ ssNumber.getText() + "'"
				+ (isManager.isSelected() ? (","+1): "" )
				+")";
		System.out.println(t);
		SQL.executeQuery(t).get(0).get("employee_id");
		
		//SQLEngine.executeDBQuery("INSERT INTO wgmember");
		//TODO Add insert into wg 
			return 0;
	}
	

	public EmployeeBrowser getSibling()
	{
		return sibling;
	}

	public void setSibling(EmployeeBrowser sibling)
	{
		this.sibling = sibling;
	}

	public boolean hasUnsaved()
	{
		return (createNew &&  FormField.hasEdits(fName,lName,mName,ssNumber)) || (employeeId != -1 && FormField.hasEdits(fName,lName,mName,ssNumber));
	}

	public boolean isEditMode()
	{
		return editMode;
	}

	public void setEditMode(boolean editMode)
	{
		if(editMode)
			createNew = false;
		
		System.out.println(editMode);
		formFields.setEnabled(editMode);
		btnAdd.setEnabled(!editMode);
		this.editMode = editMode;
		

		
		
	}
	public boolean getCreateNew()
	{
		return createNew;
	}

	public void setCreateNew(boolean createNew)
	{
		if(createNew)
			editMode = false;
		formFields.setEnabled(createNew);
		btnAdd.setEnabled(!createNew);
		this.createNew = createNew;
	}
}
