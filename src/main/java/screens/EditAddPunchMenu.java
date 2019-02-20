package screens;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import sreeninterface.EmployeeBrowser;
import sreeninterface.ListInfoMod;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAddPunchMenu extends JPanel implements ListInfoMod{
	
	private JTable table;
	private EmployeeBrowser sibling;
	private int empId = -1;
	
	@SuppressWarnings("serial")
	public EditAddPunchMenu(EmployeeBrowser sibling) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{154, 145, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setSibling(sibling);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(2);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JPanel punchPanel = new JPanel();
		GridBagConstraints gbc_punchPanel = new GridBagConstraints();
		gbc_punchPanel.fill = GridBagConstraints.BOTH;
		gbc_punchPanel.weighty = 10.0;
		gbc_punchPanel.gridx = 0;
		gbc_punchPanel.gridy = 1;
		add(punchPanel, gbc_punchPanel);
		punchPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Type", "Time", "Method"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		punchPanel.add(table);
		
		JCalendar j = new JCalendar();
		panel_1.add(j);
		
		JButton btnAddTime = new JButton("Add Time");
		panel_1.add(btnAddTime);
		
		JButton btnModifyTime = new JButton("Modify Time");
		panel_1.add(btnModifyTime);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnDelete);
	}
	//TODO: Design GUI

	@Override
	public void newId(int id)
	{
		empId = id;
	}

	@Override
	public void setSibling(EmployeeBrowser e)
	{
		sibling = e;
		
	}

	public int getEmpId()
	{
		return empId;
	}
}
