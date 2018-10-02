package swingmods;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormField extends JTextField implements FocusListener , DocumentListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 239226233136768155L;
	private boolean edit;
	private boolean isHinting;
	private String hint="";
	
	public FormField() {
		super();
	}
	
	public FormField(String text) 
	{
		super();
		super.setText(text);
	}
	
	@Override
	public void focusGained(FocusEvent arg0) 
	{
		setForeground(Color.BLACK);
	    if(this.getText().isEmpty()) {
	        super.setText("");
	        isHinting = false;
	      }
	}

	@Override
	public void focusLost(FocusEvent arg0) 
	{
	    if(this.getText().isEmpty()) {
	        super.setText(hint);
	        isHinting = true;
	        setForeground(Color.gray);
	      }
	}
	
	  @Override
	  public String getText() 
	  {
	    return isHinting ? "" : super.getText();
	  }
	
	  public void clearEdit()
	  {
		  edit = false;
	  }

	@Override
	public void changedUpdate(DocumentEvent e) {
		if(e.getLength()>0 || (getText() != hint && !getForeground().equals(Color.GRAY)))
			edit = true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if(e.getLength()>0 || (getText() != hint && !getForeground().equals(Color.GRAY)))
			edit = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if(e.getLength()>0 || (getText() != hint && !getForeground().equals(Color.GRAY)))
			edit = true;
	}
	
	public void resetEdit()
	{
		edit = false;
	}
	
	public boolean hasEdit()
	{
		return edit;
	}
	
	public void setHint(String h)
	{
		hint = h;
	}
	
	public String getHint()
	{
		return hint;
	}
	
	/*
	 * @param forms: used as a list of forms to clean up the code
	 * 
	 * Makes
	 * formA.resetEdit()
	 * formB.resetEdit()
	 * formC.resetEdit()
	 * formD.resetEdit()
	 * 
	 * Into 
	 * 
	 * FormField.resetAllEdits(formA,formB,formC,formD)
	 */
	public static void resetAllEdits(FormField... forms)
	{
		for(FormField f: forms)
		{
			f.resetEdit();
		}
	}
	public boolean isEmpty()
	{
		return !(getText().length() > 0 && !(getForeground().equals(Color.GRAY)));
	}
	
}
