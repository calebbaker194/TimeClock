package screens;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.InitalizeDatabase;
import sqlengine.PostGresServer;
import sqlengine.SQL;
import swingmods.BaseWindow;
import tcregex.TCRegex;

import javax.swing.JScrollPane;

public class LoginMenu extends JFrame implements BaseWindow
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3697473316938392585L;
	private JTextField ipAddress;
	private JTextField port;
	private JTextField database;
	private JTextField dbusername;
	private JTextField dbpassword;
	private JButton adddb;
	private JTextField dbnickname;
	private JTextField dbNicknameDsp;
	private JTextField dbipAddressDsp;
	private JTable storeddbTable;
	private int lastSelectedRow=0;
	private boolean edit = false;
	private ArrayList<PostGresServer> prevousServers = new ArrayList<PostGresServer>();
	
	public LoginMenu()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 550);
		setLocationRelativeTo(null);
		
		storeddbTable = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		
		storeddbTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name","UnserName", "Host", "Port"
				}
			));
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("IP address:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(5, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		ipAddress = new JTextField();
		GridBagConstraints gbc_ipAddress = new GridBagConstraints();
		gbc_ipAddress.insets = new Insets(5, 0, 5, 0);
		gbc_ipAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipAddress.gridx = 1;
		gbc_ipAddress.gridy = 0;
		panel.add(ipAddress, gbc_ipAddress);
		ipAddress.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Port:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		port = new JTextField();
		GridBagConstraints gbc_port = new GridBagConstraints();
		gbc_port.insets = new Insets(0, 0, 5, 0);
		gbc_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_port.gridx = 1;
		gbc_port.gridy = 1;
		panel.add(port, gbc_port);
		port.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Database:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		database = new JTextField();
		GridBagConstraints gbc_database = new GridBagConstraints();
		gbc_database.insets = new Insets(0, 0, 5, 0);
		gbc_database.fill = GridBagConstraints.HORIZONTAL;
		gbc_database.gridx = 1;
		gbc_database.gridy = 2;
		panel.add(database, gbc_database);
		database.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Username:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		dbusername = new JTextField();
		GridBagConstraints gbc_dbusername = new GridBagConstraints();
		gbc_dbusername.insets = new Insets(0, 0, 5, 0);
		gbc_dbusername.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbusername.gridx = 1;
		gbc_dbusername.gridy = 3;
		panel.add(dbusername, gbc_dbusername);
		dbusername.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		dbpassword = new JTextField();
		GridBagConstraints gbc_dbpassword = new GridBagConstraints();
		gbc_dbpassword.insets = new Insets(0, 0, 5, 0);
		gbc_dbpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbpassword.gridx = 1;
		gbc_dbpassword.gridy = 4;
		panel.add(dbpassword, gbc_dbpassword);
		dbpassword.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		dbnickname = new JTextField();
		GridBagConstraints gbc_dbnickname = new GridBagConstraints();
		gbc_dbnickname.insets = new Insets(0, 0, 5, 0);
		gbc_dbnickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbnickname.gridx = 1;
		gbc_dbnickname.gridy = 5;
		panel.add(dbnickname, gbc_dbnickname);
		dbnickname.setColumns(10);
		
		JButton deletedb = new JButton("Delete");
		GridBagConstraints gbc_deletedb = new GridBagConstraints();
		gbc_deletedb.anchor = GridBagConstraints.EAST;
		gbc_deletedb.insets = new Insets(0, 5, 5, 5);
		gbc_deletedb.gridx = 0;
		gbc_deletedb.gridy = 6;
		panel.add(deletedb, gbc_deletedb);
		
		adddb = new JButton("add");
		
		GridBagConstraints gbc_adddb = new GridBagConstraints();
		gbc_adddb.insets = new Insets(0, 0, 5, 0);
		gbc_adddb.gridx = 1;
		gbc_adddb.gridy = 6;
		panel.add(adddb, gbc_adddb);
		
		dbNicknameDsp = new JTextField();
		dbNicknameDsp.setEditable(false);
		GridBagConstraints gbc_dbNicknameDsp = new GridBagConstraints();
		gbc_dbNicknameDsp.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbNicknameDsp.insets = new Insets(0, 0, 5, 0);
		gbc_dbNicknameDsp.gridwidth = 2;
		gbc_dbNicknameDsp.gridx = 0;
		gbc_dbNicknameDsp.gridy = 12;
		panel.add(dbNicknameDsp, gbc_dbNicknameDsp);
		dbNicknameDsp.setColumns(10);
		
		dbipAddressDsp = new JTextField();
		dbipAddressDsp.setEditable(false);
		GridBagConstraints gbc_dbipAddressDsp = new GridBagConstraints();
		gbc_dbipAddressDsp.insets = new Insets(0, 0, 5, 0);
		gbc_dbipAddressDsp.gridwidth = 2;
		gbc_dbipAddressDsp.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbipAddressDsp.gridx = 0;
		gbc_dbipAddressDsp.gridy = 13;
		panel.add(dbipAddressDsp, gbc_dbipAddressDsp);
		dbipAddressDsp.setColumns(10);
		
		JButton connectBtn = new JButton("           Connect             ");
		
		GridBagConstraints gbc_connectBtn = new GridBagConstraints();
		gbc_connectBtn.gridwidth = 2;
		gbc_connectBtn.insets = new Insets(0, 2, 0, 5);
		gbc_connectBtn.gridx = 0;
		gbc_connectBtn.gridy = 14;
		panel.add(connectBtn, gbc_connectBtn);
		
		JScrollPane scrollPane = new JScrollPane(storeddbTable);
		splitPane.setRightComponent(scrollPane);
		
		readServers();
		
		storeddbTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0 && storeddbTable.getSelectedRow() != -1) 
				{
					e.consume();
					rowClicked();
				}	
			}
		});
		
		deletedb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteSavedDB();
			}
			
		});
		
		adddb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveDB();
			}

		});
		
		connectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectDB();
			}
		});
		
		setVisible(true);
	}

	protected void connectDB() 
	{
		String reply="";
		if(   ipAddress.getText().matches(TCRegex.IP_ADDRESS+"|"+TCRegex.FQND)
		   && TCRegex.isPort(port.getText()))
		   //&& dbpassword.getText().matches(TCRegex.PASSWORD))
		{
			reply = SQL.Connect(database.getText(),
								ipAddress.getText(),
								Integer.parseInt(port.getText()),
								dbusername.getText(),
								dbpassword.getText());
			if(reply.equals("0")) // See if the connection was a success
			{
				
				// If We used a row that was not the first
				if(storeddbTable.getSelectedRow()>0)
				{
					// Move it to be the firest row for the auto logon next time
					PostGresServer pgst = prevousServers.get(0);
					prevousServers.add(0, prevousServers.remove(storeddbTable.getSelectedRow()));
					prevousServers.add(prevousServers.size()-1, pgst);
				}
				else if(storeddbTable.getSelectedRow()==-1) // If we didnt use the saved data table at all
				{
					// Add our data to the array
					prevousServers.add(0,new PostGresServer(  ipAddress.getText(),
															database.getText(),
															Integer.parseInt(port.getText()),
															dbusername.getText(),
															dbpassword.getText(),
															dbnickname.getText()));
				}
				//Save adjustments so that next time we log on it will be where we left it
				saveServers();
				HomeMenu hm = new HomeMenu(this);
				this.setVisible(false);
				return;
			}
		}//Error Print Outs 
		if(!ipAddress.getText().matches(TCRegex.IP_ADDRESS+"|"+TCRegex.FQND))
			reply = "Host Invalid: Must be an IP address or FQDN\n"+reply;
		if(!TCRegex.isPort(port.getText()))
			reply = "Port Invalid: Port must Be a number between 1 and 65535\n"+reply;
		//if(!dbpassword.getText().matches(TCRegex.PASSWORD))
		//	reply = "Password Invalid: Must Be at least 6 characters a-z A-Z 0-9 or !@#$%^&*()_+=\n"+reply;
		//Show Error Message And Reason Connection Failed
		if(reply.startsWith("Running"))
		{
			int confirm = JOptionPane.showConfirmDialog(this, "The server is running but the databse has not been created. Create Now?");
			if(confirm == JOptionPane.YES_OPTION)
			{
				try
				{
					InitalizeDatabase.createDatabase(dbusername.getText(),dbpassword.getText(),ipAddress.getText(), Integer.parseInt(port.getText()));
					// If We used a row that was not the first
					if(storeddbTable.getSelectedRow()>0)
					{
						// Move it to be the firest row for the auto logon next time
						PostGresServer pgst = prevousServers.get(0);
						prevousServers.add(0, prevousServers.remove(storeddbTable.getSelectedRow()));
						prevousServers.add(prevousServers.size()-1, pgst);
					}
					else if(storeddbTable.getSelectedRow()==-1) // If we didnt use the saved data table at all
					{
						// Add our data to the array
						prevousServers.add(0,new PostGresServer(  ipAddress.getText(),
																database.getText(),
																Integer.parseInt(port.getText()),
																dbusername.getText(),
																dbpassword.getText(),
																dbnickname.getText()));
					}
					//Save adjustments so that next time we log on it will be where we left it
					saveServers();
					HomeMenu hm = new HomeMenu(this);
					this.setVisible(false);
					return;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(),"Error Occured When Setting Up Database. Please Try Again Or contact customer support at 903-946-3351", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		JOptionPane.showMessageDialog(this, reply,"Login Failed", JOptionPane.ERROR_MESSAGE);
		
		return;
	}

	protected void saveDB() {

		if(!edit)
		{
			PostGresServer pgs = new PostGresServer(ipAddress.getText(),
													database.getText(),
													Integer.parseInt(port.getText()),
													dbusername.getText(),
													dbpassword.getText(),
													dbnickname.getText());
			prevousServers.add(pgs);
			clearTextFields();
		}
		if(edit)
		{
			PostGresServer tempToDelete = prevousServers.get(storeddbTable.getSelectedRow()); 
			PostGresServer pgs = new PostGresServer(ipAddress.getText(),
					database.getText(),
					Integer.parseInt(port.getText()),
					dbusername.getText(),
					dbpassword.getText(),
					dbnickname.getText());
			prevousServers.add(storeddbTable.getSelectedRow(),pgs);
			prevousServers.remove(tempToDelete);
			setEdit(false);
			clearTextFields();
		}
		saveServers();
		
	}

	protected void rowClicked() {
		
		if(edit && storeddbTable.getSelectedRow()==lastSelectedRow)
		{
			setEdit(false);
			clearTextFields();
			storeddbTable.clearSelection();
		}
		else
		{				
			setEdit(true);
			PostGresServer temp = prevousServers.get(storeddbTable.getSelectedRow());
			ipAddress.setText(temp.getHost());
			database.setText(temp.getDb());
			port.setText(temp.getPort()+"");
			dbusername.setText(temp.getUsername());
			dbpassword.setText(temp.getPassword());
			dbnickname.setText(temp.getNickname());
			dbipAddressDsp.setText(temp.getHost());
			dbNicknameDsp.setText(temp.getNickname());
		}
		lastSelectedRow = storeddbTable.getSelectedRow();
	}

	protected void deleteSavedDB() {
		if(storeddbTable.getSelectedRow()>=0)
			prevousServers.remove(storeddbTable.getSelectedRow());
		
		// They May Just want to clear the fields
		clearTextFields();
		saveServers();
	}

	protected void setEdit(boolean b) 
	{
		adddb.setText(b ? "Save":"Add");
		edit = b;
	}

	public void clearTextFields()
	{
		ipAddress.setText("");
		database.setText("");
		port.setText("");
		dbusername.setText("");
		dbpassword.setText("");
		dbnickname.setText("");
		dbNicknameDsp.setText("");
		dbipAddressDsp.setText("");
	}
	public void saveServers() {

		String savedDataFile=System.getenv("APPDATA")+"\\CTTimeClock\\servers.cfg";
		System.out.println("Saving Files");
		try {
			new File(System.getenv("APPDATA")+"\\CTTimeClock").mkdirs();
			new File(savedDataFile).createNewFile();	
		} catch (IOException e1) {
			System.err.println("Creadting new server files or making directory");
			e1.printStackTrace();
		}
		FileOutputStream fis;
		try {
			fis = new FileOutputStream(savedDataFile);
			ObjectOutputStream o = new ObjectOutputStream(fis);
			Iterator<PostGresServer> it = prevousServers.iterator();
			while(it.hasNext())
			{
				o.writeObject(it.next());
				o.flush();
			}
			refreshTable();
			o.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void refreshTable() 
	{
		storeddbTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name","UnserName", "Host", "Port"
				}
			));
		
		Iterator<PostGresServer> it = prevousServers.iterator();
		while(it.hasNext())
		{
			PostGresServer temp = it.next();
			Vector<String> v = new Vector<String>(4);
			v.addElement(temp.getNickname());
			v.addElement(temp.getUsername());
			v.addElement(temp.getHost());
			v.addElement(temp.getPort()+"");
			((DefaultTableModel)storeddbTable.getModel()).addRow(v);
		}
	}


	private void readServers() 
	{
		String savedDataFile=System.getenv("APPDATA")+"\\CTTimeClock\\servers.cfg";
		
		FileInputStream fis=null;
		ObjectInputStream o=null;
		setPrevousServers(new ArrayList<PostGresServer>());
		try {
			fis = new FileInputStream(savedDataFile);
			o = new ObjectInputStream(fis);
			while(true)
			{
				prevousServers.add((PostGresServer) o.readObject());
			}
			
		}catch(EOFException ef)
		{
			refreshTable();
			
			try {
				o.close();
				fis.close();
			} catch (IOException e) {
				System.err.println("Error closing object or file stream");
				e.printStackTrace();
			} catch (NullPointerException e) {
				
			}
		}
		catch (StreamCorruptedException e) {
			new File(savedDataFile).delete();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.err.println("Object reader does not work Critical Failure");
			e.printStackTrace();
		}

		
	}

	public int DefaultExitOperation() {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
	    	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.out.println("Good BYE");
	    	}
		});
		return 0;
	}

	public ArrayList<PostGresServer> getPrevousServers() {
		return prevousServers;
	}

	public void setPrevousServers(ArrayList<PostGresServer> prevousServers) {
		this.prevousServers = prevousServers;
	}
}
