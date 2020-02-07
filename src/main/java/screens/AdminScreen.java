package screens;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class AdminScreen extends JFrame{
	
	public static int EDIT_EMPLOYEES = 0;
	public static int EDIT_ADMINISTRATORS = 1;
	public static int EDIT_TIMES = 2;
	public static int EDIT_RULES = 3;
	public static int EDIT_SETTINGS = 4;
	public static int EDIT_GROUPS = 5;
	public static int PRINT_PAYSTUBS = 6;
    public static int DEFAULT_DISPLAY = 7;
	
	
	public JComponent[] screens = new JComponent[8];
	
	public AdminScreen(int screenNumber)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700,500);
		setLayout(new CardLayout());
	    showScreen(screenNumber);
		
	}
	public AdminScreen()
	{
		this(DEFAULT_DISPLAY);
	}

	public void showScreen(int screenNumber)
	{
		switch(screenNumber)
		{
		case 0:
			editEmployees();
			break;
		case 1:
			editAdministrators();
			break;
		case 2:
			editTimes();
			break;
		case 3:
			editRules();
			break;
		case 4:
			editSettings();
			break;
		case 5:
			editGroups();
			break;
		case 6:
			printPaystubs();
			break;
		default:
			displyOptions();
			break;
		}
		
	}
	
	public void editEmployees()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void editAdministrators()
	{
		if(screens[EDIT_ADMINISTRATORS] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_ADMINISTRATORS] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_ADMINISTRATORS], EDIT_ADMINISTRATORS+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_ADMINISTRATORS+"");
	}
	
	public void editTimes()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void editRules()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void editSettings()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void editGroups()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void printPaystubs()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
	
	public void displyOptions()
	{
		if(screens[EDIT_EMPLOYEES] == null)
		{
			ListEmployeesMenu le = new ListEmployeesMenu();
			screens[EDIT_EMPLOYEES] = new SplitHolder(le, new EmployeeInfoMenu(le));
			getContentPane().add(screens[EDIT_EMPLOYEES], EDIT_EMPLOYEES+"");
		}
		
		CardLayout cl = (CardLayout)(getLayout());
		cl.show(this, EDIT_EMPLOYEES+"");
	}
}
