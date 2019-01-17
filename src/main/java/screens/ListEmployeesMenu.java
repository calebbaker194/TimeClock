package screens;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import sqlengine.ResultList;
import sqlengine.SQL;
import sreeninterface.EmployeeBrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

// TODO : The warning labels need updated
public class ListEmployeesMenu extends JPanel implements EmployeeBrowser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -313005299187034905L;
	private ResultList employees;
	private JTable listTable;
	private EmployeeInfoMenu employeeMenu;
	
	@SuppressWarnings("serial")
	public ListEmployeesMenu()
	{
	
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		
		// LEFT SIDE OF SPLIT PANE
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		listTable = new JTable();
		listTable.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() > 1)
				{
					e.consume();
					tableClicked(e);
				}
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
		
		}
		
		//RIGHT SIDE OF SPLIT PANE
		
		employeeMenu = new EmployeeInfoMenu((EmployeeBrowser)this);
		
		splitPane.setRightComponent(employeeMenu);
		
		quereyEmployees();
		addPopupMenu();
	}

	private void addPopupMenu() 
	{
		JPopupMenu contextMenu = new JPopupMenu();
		JMenuItem delete = new JMenuItem("Remove Employee");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				deleteEmployee();
			}
		});
		
		contextMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = listTable.rowAtPoint(SwingUtilities.convertPoint(contextMenu, new Point(0, 0), listTable));
                        if (rowAtPoint > -1) {
                        	listTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
		contextMenu.add(delete);
		
		listTable.setComponentPopupMenu(contextMenu);
	}
	
	protected void tableClicked(MouseEvent e) 
	{
		if(employeeMenu.hasUnsaved())
		{
			int ans = JOptionPane.showConfirmDialog(this,
					"You have unsaved changes. Click yes to save, no to discard, and cancle to review ",
					 "Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION);
			if(ans == JOptionPane.YES_OPTION)
			{
				employeeMenu.saveClicked();
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
	}

	/*
	 * This populates the info of the employee into the info screen 
	 */
	private void populatEmployeeInfo() 
	{
		if(listTable.getSelectedRow() == -1)
			return;
		int fetchID =  (int) employees.get(listTable.getSelectedRow()).get("id");
		employeeMenu.newId(fetchID);		
	}

	protected void quereyEmployees()
	{
		String employeeQuery=
				  "SELECT employee_id AS id, "
					   + "employee_last_name || ', ' || employee_first_name AS name, "
					   + "'' AS idk "
				+ "FROM employee";
		employees = new ResultList(SQL.executeQuery(employeeQuery));
		DefaultTableModel tm =((DefaultTableModel)listTable.getModel());
		tm.setRowCount(0);
		
		for(HashMap<String,Object> row : employees)
		{
			Object[] addRow = {row.get("id"),row.get("name"),""};
			tm.addRow(addRow);
		}
	}

	protected void deleteEmployee()
	{
		SQL.executeQuery("DELETE FROM employee where employee_id = "+employees.get(listTable.getSelectedRow()).get("id"));
		employeesUpdated();
	}
	
	@Override
	public void employeesUpdated()
	{
		quereyEmployees();
	}


	@Override
	public void clearSelected()
	{
		listTable.clearSelection();
	}
}
