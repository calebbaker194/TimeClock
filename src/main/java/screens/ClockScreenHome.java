package screens;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

public class ClockScreenHome extends JPanel{
	public ClockScreenHome() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 213, 80, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel timeLabel = new JLabel("timeLabel");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_timeLabel = new GridBagConstraints();
		gbc_timeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timeLabel.gridx = 1;
		gbc_timeLabel.gridy = 0;
		add(timeLabel, gbc_timeLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 1;
		add(horizontalGlue, gbc_horizontalGlue);
		
		clockTable = new JTable();
		GridBagConstraints gbc_clockTable = new GridBagConstraints();
		gbc_clockTable.weighty = 1.0;
		gbc_clockTable.insets = new Insets(0, 0, 5, 5);
		gbc_clockTable.fill = GridBagConstraints.BOTH;
		gbc_clockTable.gridx = 1;
		gbc_clockTable.gridy = 1;
		add(clockTable, gbc_clockTable);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton timeCardBtn = new JButton("Time Card");
		GridBagConstraints gbc_timeCardBtn = new GridBagConstraints();
		gbc_timeCardBtn.insets = new Insets(0, 0, 5, 5);
		gbc_timeCardBtn.gridx = 0;
		gbc_timeCardBtn.gridy = 0;
		panel.add(timeCardBtn, gbc_timeCardBtn);
		
		JLabel nameLabel = new JLabel("Name Here");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_nameLabel.gridwidth = 15;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		panel.add(nameLabel, gbc_nameLabel);
		
		JButton punchButton = new JButton("Status Change");
		GridBagConstraints gbc_punchButton = new GridBagConstraints();
		gbc_punchButton.gridwidth = 15;
		gbc_punchButton.gridx = 0;
		gbc_punchButton.gridy = 2;
		panel.add(punchButton, gbc_punchButton);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue_1.gridx = 2;
		gbc_horizontalGlue_1.gridy = 1;
		add(horizontalGlue_1, gbc_horizontalGlue_1);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5047072867507265690L;
	private JTable clockTable;

}
