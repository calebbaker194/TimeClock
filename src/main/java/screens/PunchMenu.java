package screens;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.Manager;
import sreeninterface.EmployeeBrowser;
import sreeninterface.ListInfoMod;
import swingmods.FormField;

public class PunchMenu extends JPanel implements ListInfoMod{
	
	private FormField loginName;
	private FormField passwordField;
	private EmployeeBrowser sibling;
	private int empId = -1;
	
	public PunchMenu(EmployeeBrowser employeeBrowser) {
		super();
		setSibling(employeeBrowser);
		setName("PunchMenu");
		
		GridBagLayout gbl_empInfoPanel = new GridBagLayout();
		gbl_empInfoPanel.columnWidths = new int[] {0, 0};
		gbl_empInfoPanel.rowHeights = new int[]{0, 0, 0};
		gbl_empInfoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_empInfoPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_empInfoPanel);
		
		JButton btnTimeCard = new JButton("Time Card");
		GridBagConstraints gbc_btnTimeCard = new GridBagConstraints();
		gbc_btnTimeCard.anchor = GridBagConstraints.NORTH;
		gbc_btnTimeCard.insets = new Insets(0, 0, 5, 0);
		gbc_btnTimeCard.gridx = 0;
		gbc_btnTimeCard.gridy = 0;
		add(btnTimeCard, gbc_btnTimeCard);
		
		Box verticalBox = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_verticalBox.anchor = GridBagConstraints.SOUTH;
		gbc_verticalBox.gridx = 0;
		gbc_verticalBox.gridy = 1;
		add(verticalBox, gbc_verticalBox);
		
		loginName = new FormField();
		loginName.setEditable(false);
		verticalBox.add(loginName);
		loginName.setColumns(1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblPassword = new JLabel("Password:");
		horizontalBox.add(lblPassword);
		
		passwordField = new FormField();
		horizontalBox.add(passwordField);
		passwordField.setColumns(10);
		
		JButton punchBtn = new JButton("In/Out/Lunch");
		punchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(punchBtn);
		
		btnTimeCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		punchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

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
	
}
