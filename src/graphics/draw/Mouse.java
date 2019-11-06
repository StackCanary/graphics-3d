package graphics.draw;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Mouse extends MouseAdapter
{

	public Panel panel; public GfxTriangle triangle;
	
	public Mouse(Panel panel)
	{
		this.panel = panel; this.triangle = panel.gfxTriangle; 
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		triangle.dragged = triangle.search(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		triangle.dragged = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if (triangle.dragged)
		{

			if (triangle.contains(e.getX(),e.getY())) 
			{
				triangle.p.x = e.getX();
				triangle.p.y = e.getY();
				
				triangle.weights();
			}

			panel.rehash(); 
		}

	}
}