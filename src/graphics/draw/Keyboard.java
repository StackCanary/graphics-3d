package graphics.draw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import graphics.data.Face;

public class Keyboard implements KeyListener {

	Panel panel;

	public Keyboard(Panel panel)
	{
		this.panel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		// Control Camera
		switch( e.getKeyCode())
		{
		case KeyEvent.VK_W:     panel.camera.f();     panel.resetCamera(); return;
		case KeyEvent.VK_A:     panel.camera.l();     panel.resetCamera(); return;
		case KeyEvent.VK_S:     panel.camera.d();     panel.resetCamera(); return;
		case KeyEvent.VK_D:     panel.camera.r();     panel.resetCamera(); return;
		case KeyEvent.VK_UP:    panel.camera.up();    panel.resetCamera(); return;
		case KeyEvent.VK_DOWN:  panel.camera.down();  panel.resetCamera(); return;
		case KeyEvent.VK_LEFT:  panel.camera.left();  panel.resetCamera(); return;
		case KeyEvent.VK_RIGHT: panel.camera.right(); panel.resetCamera(); return;
		}
		
		// Rotation
		switch( e.getKeyCode())
		{
		case KeyEvent.VK_K:  panel.rotation++;  panel.resetCamera(); return;
		case KeyEvent.VK_J:  panel.rotation--;  panel.resetCamera(); return;
		}
		
		// Face Swapping 
		
		int no = -1; int face_no = -1;

		switch( e.getKeyCode())
		{
		case KeyEvent.VK_1: no = 1; break;
		case KeyEvent.VK_2: no = 2; break; 
		case KeyEvent.VK_3: no = 3; break;
		}

		if (no > -1)
		{
			face_no = Integer.parseInt(JOptionPane.showInputDialog("Select face no (1-199) to replace face " + no));

			if (face_no > 0 && face_no < 200)
			{
				panel.faces.swap(no, new Face(panel.data, face_no));
				panel.resetFace();
				panel.resetCamera();
			}
				
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

	}



}
