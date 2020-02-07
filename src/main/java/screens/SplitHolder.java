package screens;

import javax.swing.JComponent;
import javax.swing.JPanel;
import sreeninterface.EmployeeBrowser;
import sreeninterface.ListInfoMod;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class SplitHolder extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeBrowser browser;
	private ListInfoMod modify;

	public SplitHolder(EmployeeBrowser e, ListInfoMod m)
	{
		setBrowser(e);
		setModify(m);
		m.setSibling(e);
		e.setInfoPeer(m);
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent((JComponent)browser);
		splitPane.setRightComponent((JComponent)modify);
	}
	
	public EmployeeBrowser getBrowser()
	{
		return browser;
	}

	public void setBrowser(EmployeeBrowser browser)
	{
		this.browser = browser;
	}

	public ListInfoMod getModify()
	{
		return modify;
	}

	public void setModify(ListInfoMod modify)
	{
		this.modify = modify;
	}
}
