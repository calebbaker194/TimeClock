package launch;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import database.InitalizeDatabase;
import screens.HomeMenu;
import screens.LoginMenu;
import sqlengine.PostGresServer;
import sqlengine.ResultList;
import sqlengine.SQL;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<PostGresServer> prevousServers;
	
	public static void main(String[] args) throws Exception {
		new Main();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setUndecorated(true);	
		setVisible(true);
		Thread t = new Thread(this);
		t.start();	
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage i;
		try {
			File imageFile = new File("data/images/timeclock.jpg");
			System.out.println(imageFile.getAbsolutePath());
			i = ImageIO.read(imageFile);
			g.drawImage(i.getScaledInstance(500, 500, BufferedImage.SCALE_AREA_AVERAGING),0, 0, null);
		} catch (IOException e) {
			System.out.println("Image Not found for init loading");
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() 
	{
		//String to where servers are saved
		String savedDataFile=System.getenv("APPDATA")+"\\CTTimeClock\\servers.cfg";
		File f = new File(savedDataFile);
		File fd = new File(savedDataFile.substring(0,savedDataFile.lastIndexOf('\\')));
		if(!f.exists()) // if the cfg file doesn't exists
		{
			try
			{
				fd.mkdirs();
				f.createNewFile();
			} catch (IOException e)
			{
				System.out.println("Failed To Create the file for congiguration");
				e.printStackTrace();
			}
		}
		//Create FileStream and Empty the Arraylist of loaded servers
		FileInputStream fis;
		ObjectInputStream o = null;
		prevousServers = new ArrayList<PostGresServer>();
		try {
			//Create the Input Streams
			fis = new FileInputStream(savedDataFile);
			o = new ObjectInputStream(fis);
			//Load in all the servers
			while(true)
			{
				prevousServers.add((PostGresServer) o.readObject());
			}
		}
		catch (EOFException e){
			// Close the Object reader
			try {
				o.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				
			}
		}
		catch (IOException e) {
			System.out.println("No servers.cfg found");
		} catch (ClassNotFoundException e) {
			System.err.println("Object reader does not work Critical Failure");
			e.printStackTrace();
		}
		if(prevousServers.size() > 0)
		{
			if(testLogin(prevousServers.get(0)))
			{
				
				new HomeMenu(); // No need to login. SQLEngine.SQLConnection saves everything.
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				return;
			}
		}
		new LoginMenu();
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private boolean testLogin(PostGresServer pgdb) 
	{	
		try
		{
			boolean isConnected = (SQL.Connect(pgdb.getDb(), pgdb.getHost(), pgdb.getPort(), pgdb.getUsername(), pgdb.getPassword())).equals("0");
			
			if(isConnected)
			{
				ResultList r= SQL.executeQuery("SELECT * FROM employee LIMIT 5");
				
				if(r.hasError())
				{
					System.out.println("Setting Up Database");
					InitalizeDatabase.InitDatabase("caleb", "tori");
				}
			}
				
			return isConnected;
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

}