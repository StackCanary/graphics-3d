package graphics.draw;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import graphics.data.Data;
import graphics.data.Face;

public class Draw extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	Panel panel = new Panel();
	
	public Draw()
	{
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setResizable(false);
		panel.setFocusable(true); panel.requestFocusInWindow();
		
		add(panel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	public static void invoke()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new Draw();
			}
		});
	}
	
	public static void main(String[] args)
	{
		invoke();
	}

}
