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
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

// TODO : The warning labels need updated
public class ListEmployeesMenu extends JPanel implements EmployeeBrowser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -313005299187034905L;
	private ResultList employees;
	private JTable listTable;
	private EmployeeInfoMenu employeeMenu;
	private PunchMenu punchMenu;
	private JCheckBox showActive;
	private JPanel leftPanel = new JPanel();
	
	@SuppressWarnings("serial")
	public ListEmployeesMenu()
	{
	
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{181, 0, 0};
		gbl_leftPanel.rowHeights = new int[]{149, 149, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
		// LEFT SIDE OF SPLIT PANE
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weightx = 10.0;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		leftPanel.add(scrollPane, gbc_scrollPane);
		
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
		
			scrollPane.setViewportView(listTable);
			
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.EAST;
			gbc_panel.gridheight = 2;
			gbc_panel.fill = GridBagConstraints.VERTICAL;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			leftPanel.add(panel, gbc_panel);
			
			showActive = new JCheckBox("Show Inactive");
			showActive.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					employeesUpdated();
				}
			});
			panel.add(showActive);
		
		if(true) //Code organization. Remove on release
		{
		
		}
		
		//RIGHT SIDE OF SPLIT PANE
		
		employeeMenu = new EmployeeInfoMenu((EmployeeBrowser)this);
		punchMenu = new PunchMenu((EmployeeBrowser)this);
		
		
		splitPane.setRightComponent(employeeMenu);
		
		quereyEmployees();
		addPopupMenu();
	}

	private void addPopupMenu() 
	{
		JPopupMenu contextMenu = new JPopupMenu();
		JMenuItem delete = new JMenuItem("Set Inactive");
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
				+ "FROM employee ";
		if(!showActive.isSelected())
			employeeQuery += "WHERE employee_isactive IS NULL";
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
		SQL.executeQuery("UPDATE employee SET employee_isactive=false WHERE employee_id = "+employees.get(listTable.getSelectedRow()).get("id"));
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
