package screens;

import java.util.HashMap;

import javax.swing.JPanel;

import sqlengine.ResultList;
import sqlengine.SQL;
import sreeninterface.EmployeeBrowser;
import sreeninterface.ListInfoMod;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Checkbox;

public class EditAdministrators extends JPanel implements ListInfoMod{
	public EditAdministrators() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{18, 105, 133, 147, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 293, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel = new JLabel("Group Permision");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		Checkbox checkbox = new Checkbox("New check box");
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.insets = new Insets(0, 0, 5, 5);
		gbc_checkbox.gridx = 1;
		gbc_checkbox.gridy = 1;
		add(checkbox, gbc_checkbox);
		
		Checkbox checkbox_1 = new Checkbox("New check box");
		GridBagConstraints gbc_checkbox_1 = new GridBagConstraints();
		gbc_checkbox_1.insets = new Insets(0, 0, 5, 5);
		gbc_checkbox_1.gridx = 1;
		gbc_checkbox_1.gridy = 2;
		add(checkbox_1, gbc_checkbox_1);
		
		Checkbox checkbox_2 = new Checkbox("New check box");
		GridBagConstraints gbc_checkbox_2 = new GridBagConstraints();
		gbc_checkbox_2.insets = new Insets(0, 0, 5, 5);
		gbc_checkbox_2.gridx = 1;
		gbc_checkbox_2.gridy = 3;
		add(checkbox_2, gbc_checkbox_2);
		
		Checkbox checkbox_3 = new Checkbox("New check box");
		GridBagConstraints gbc_checkbox_3 = new GridBagConstraints();
		gbc_checkbox_3.insets = new Insets(0, 0, 5, 5);
		gbc_checkbox_3.gridx = 1;
		gbc_checkbox_3.gridy = 4;
		add(checkbox_3, gbc_checkbox_3);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 6;
		add(table, gbc_table);
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmployeeBrowser Sibling;
	private JTable table;
	
	@Override
	public void newId(int id)
	{
		ResultList rl = new ResultList(SQL.executeQuery("SELECT employee.* FROM employee WHERE employee_id="+id));
		HashMap<String, Object> rr = rl.get(0);
		
	}

	@Override
	public void setSibling(EmployeeBrowser e)
	{
		this.Sibling = e;
	}

}
