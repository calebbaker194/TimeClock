package screens;

import javax.swing.JFrame;
import sqlengine.ResultList;
import sqlengine.SQL;
import swingmods.BaseWindow;
import swingmods.FormField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import manager.Manager;

import java.awt.Frame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class HomeMenu extends JFrame implements BaseWindow{


	private LoginMenu loginMenu;
	private int lastSelection = -1;
	private JTable listTable;
	ResultList employeeList = null;
	
	public HomeMenu()
	{
		this(new LoginMenu());
	}

	public HomeMenu(LoginMenu loginMenu) 
	{
		super();
		
		loginMenu.setVisible(false);
		this.loginMenu=loginMenu;
		getContentPane().setLayout(new CardLayout());
		//For List|Info Screens
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		getContentPane().add(splitPane,"Home Menu");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//For List|Info Screens
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setSize(650, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		listTable = new JTable();
		
		listTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Status", "Last Punch", "Time Active"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(listTable);
		splitPane.setLeftComponent(scrollPane);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuBar.add(btnNewButton);
		
		JMenu mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmLoginAsAdmin = new JMenuItem("Login as Admin");
		mnAdmin.add(mntmLoginAsAdmin);
		
		mntmLoginAsAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginAsAdmin();
			}
		});
		
		JMenu mnEmployee = new JMenu("Employee");
		menuBar.add(mnEmployee);
		
		JMenuItem mntmEditUser = new JMenuItem("Edit User");
		mnEmployee.add(mntmEditUser);
		
		mntmEditUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editUsers();
			}
		});
		
		JMenuItem mntmEditSupervisorworkgroup = new JMenuItem("Edit Supervisor/WorkGroup");
		mnEmployee.add(mntmEditSupervisorworkgroup);
		
		mntmEditSupervisorworkgroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editSupervisors();
			}
		});
		
		JMenu mnWorkGroup = new JMenu("Work Group");
		menuBar.add(mnWorkGroup);
		
		JMenuItem mntmAddremoveWorkGroup = new JMenuItem("Add/Remove Work Group");
		mnWorkGroup.add(mntmAddremoveWorkGroup);
		
		mntmAddremoveWorkGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editWorkGroup();
			}
		});
		
		JMenuItem mntmAddremoveMembers = new JMenuItem("Add/Remove Members");
		mnWorkGroup.add(mntmAddremoveMembers);
		
		mntmAddremoveMembers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editWorkGroup();
			}
		});
		
		JMenuItem mntmEditSchedule = new JMenuItem("Edit Schedule");
		mnWorkGroup.add(mntmEditSchedule);
		
		mntmEditSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editWorkGroup();
			}
		});
		
		JMenu mnExportHours = new JMenu("Export Hours");
		menuBar.add(mnExportHours);
		
		JMenuItem mntmExportHoursCsv = new JMenuItem("Export Hours CSV");
		mnExportHours.add(mntmExportHoursCsv);
		
		mntmExportHoursCsv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportHours();
			}
		});
		

		DefaultExitOperation();
		
		listTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listTable.getSelectedRow() != -1)
				{
					e.consume();
				}	
			}
		});
		
		setVisible(true);
		
//		if(false) //TODO: need to make a check for init
//		{
//			int ret = JOptionPane.showConfirmDialog(this,
//					"This Database Does not appear to be initalized. Initalize it now or cancle to exit?",
//					"Initalize Database",
//					JOptionPane.YES_NO_OPTION);
//			if(ret==JOptionPane.YES_OPTION)
//			{
//				JTextField userNameField = new JTextField(5);
//			    JTextField passwordField = new JTextField(5);
//			    JLabel errMessage = new JLabel();
//			    
//			    JPanel myPanel = new JPanel();
//			    myPanel.add(new JLabel("username:"));
//			    myPanel.add(userNameField);
//			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//			    myPanel.add(new JLabel("pasosword:"));
//			    myPanel.add(passwordField);
//			    
//
//			    boolean initLogin = true;
//			    
//			    while(initLogin &&(!userNameField.getText().matches(TCRegex.PASSWORD) && !passwordField.getText().matches(TCRegex.PASSWORD)))
//			    {
//				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
//				             "Please enter a username and password", JOptionPane.OK_CANCEL_OPTION);
//				    if (result == JOptionPane.CANCEL_OPTION) 
//				    {
//				        initLogin = false;
//				    }
//				    errMessage.setText("Username and password must be 6 characters long");
//			    }
//			    if(initLogin)
//				InitalizeDatabase.InitDatabase(userNameField.getText(),passwordField.getText());
//			}
//			else
//			{
//				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//			}
//		}
		
		//queryStatus();
		loadScreens();
		editUsers();
		
	}

	protected void loadScreens()
	{		
		System.out.println("Loaded Screens");
//		ListWorkGroupMenu lw = new ListWorkGroupMenu();
//		getContentPane().add(lw, "EditWorkGroup");
	    
	    ListEmployeesMenu le = new ListEmployeesMenu();
	    getContentPane().add(le,"EditEmployee");
	    
//	    ListSupervisorsMenu ls = new ListSupervisorsMenu();
//	    getContentPane().add(ls,"EditSupervisor");
//	    
//	    ExportEmployeeHours ee = new ExportEmployeeHours();
//	    getContentPane().add(ee,"ExportHours");
	}
	
	protected void exportHours() {
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this , "ExportHours");
	}

	protected void editWorkGroup() {
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this , "EditWorkGroup");
	}

	protected void editSupervisors() {
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this , "EditSupervisor");
	}

	protected void editUsers() {
		System.out.println("Switching to edit users screen");
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
	    cl.show(getContentPane() , "EditEmployee");
	}

	protected void loginAsAdmin() {
		
		int ret = JOptionPane.showConfirmDialog(this,
				"Please Login",
				"Login",
				JOptionPane.YES_NO_OPTION);
		if(ret==JOptionPane.YES_OPTION)
		{
			JTextField userNameField = new JTextField(5);
		    JTextField passwordField = new JTextField(5);
		    JLabel errMessage = new JLabel();
		    
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("username:"));
		    myPanel.add(userNameField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("pasosword:"));
		    myPanel.add(passwordField);
		    

		    boolean login = true;
		    boolean loginSuccess = false;
		    
		    while(login && loginSuccess)
		    {
			    int result = JOptionPane.showConfirmDialog(null, myPanel, 
			             "Please enter a username and password", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.CANCEL_OPTION) 
			    {
			        login = false;
			    }
			    else
			    {
				    Manager.enableAdmin(userNameField.getText(), passwordField.getText());
				    errMessage.setText("Incorrect Login");
				    loginSuccess =Manager.getAdminId() != null;
			    }
		    }
		    
		    if(!login)
		    	return;
		    
		    if(loginSuccess)
		    {
		    	
		    }
		}
		
	}

	protected void displayTimeCard()
	{
		JFrame p = new JFrame();
		p.setAlwaysOnTop(true);
		p.getContentPane().setLayout(new BorderLayout());
		//p.getContentPane().add(new EmployeeTimeCard(employeeList.get(listTable.getSelectedRow()).get("id")+""));
		p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p.setVisible(true);
	}

	private void queryStatus() 
	{
		employeeList = new ResultList(SQL.executeQuery("SELECT employee_id AS id,"
				+ " concat(employee_last_name,',',employee_first_name) AS name,"
				+ " employee_clockstatus AS status, employee_lastPunch AS lastpunch"
				+ " FROM employee"));
		
		Vector<String> v;
		((DefaultTableModel)listTable.getModel()).setRowCount(0);;
		for(HashMap<String, Object> row : employeeList)
		{
			//TODO Modify Data To be more Readable
			v = new Vector<String>(5);
			v.addElement(row.get("id")+"");
			v.addElement(row.get("name")+"");
			v.addElement(((Boolean)row.get("status"))? "IN" : "OUT");
			SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-MM-dd");
			java.sql.Timestamp lastPunch = ((java.sql.Timestamp) row.get("lastpunch"));
			v.addElement(sdf.format(lastPunch));
			long diff = new java.util.Date().getTime()-lastPunch.getTime();
			v.addElement(diff/3600000l+":"+(diff % 3600000l)/60000l);
			((DefaultTableModel)listTable.getModel()).addRow(v);
		}
	}

	@Override
	public int DefaultExitOperation() {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
	    	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				loginMenu.setVisible(true);
	    	}
		});
		return 0;
	}
}
