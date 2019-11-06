package graphics.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;

/*
 * Uses Barycentric coordinates to calculate weights
 */
public class GfxTriangle {
	
	Path2D path = new Path2D.Double(); 
	
	// Triangle Coords
	double x1; double y1;
	double x2; double y2;
	double x3; double y3;
	
	// Weights 
	double w1; double w2; double w3;
	
	// Size of triangle
	int tri_size = 100; int tri_disp = 8; 
	
	// Circle selector in triangle
	Point p = new Point(0, 0); public int p_size = 10; boolean dragged = false;
	
	Panel panel;

	public GfxTriangle(Panel panel)
	{
		
		this.panel = panel;
		
		panel.addComponentListener(new ComponentAdapter() 
		{
			// On resize, recalculate triangle position
			public void componentResized(ComponentEvent componentEvent) 
			{
				Rectangle r = panel.getBounds(); 
				x1 = r.width - tri_disp; y1 = r.height - tri_disp; reset();  

				p.x = (int) x1 - tri_size;
				p.y = (int) y1;
				
				weights();
			}
		});
		
	}
	
	public void reset()
	{
		int w = (int) (tri_size * Math.cos(Math.PI / 3)); 
		int h = (int) (tri_size * Math.sin(Math.PI / 3));

		path.reset();
		
		// Calculate triangle coordinates		
		x2 = x1 - tri_size; y2 = y1; x3 = x1 - w; y3 = y1 - h;
		
		// Create triangle
		path.moveTo(x1, y1);
		path.lineTo(x2, y2); 
		path.lineTo(x3, y3); 
		path.lineTo(x1, y1);
	}
	
	/* https://codeplea.com/triangular-interpolation */
	public void weights()
	{

		double _w1_ = ((y2 - y3) * (p.x - x3) + (x3 - x2) * (p.y - y3))
			/
			 ((y2 - y3) * ( x1 - x3) + (x3 - x2) *  (y1 - y3));
		
		double _w2_ = ((y3 - y1) * (p.x - x3) + (x1 - x3) * (p.y - y3))
			/
			 ((y2 - y3) * ( x1 - x3) + (x3 - x2) *  (y1 - y3));
		
		double _w3_ = 1 - _w1_ - _w2_;
		
		w1 = _w2_;
		w2 = _w3_;
		w3 = _w1_;
		
		panel.resetFace();
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.GRAY);
		
		((Graphics2D) g).fill(path);
		
		g.setColor(Color.GREEN);
		
		int off = 3;
		
		g.drawString("3", (int) x1 - off, (int) y1 - off);
		g.drawString("1", (int) x2 - off, (int) y2 - off);
		g.drawString("2", (int) x3 - off, (int) y3 - off);

		g.setColor(Color.PINK);
		g.drawOval(p.x - p_size/2, p.y - p_size/2, p_size, p_size);
		g.setColor(Color.BLACK);
		
		
		
		
	}
	
	public boolean contains(int x, int y)
	{
		return path.contains(x, y);
	}
	
	public boolean search(int x, int y)
	{
		return Math.abs(p.x - x) < p_size + 10 && Math.abs(p.y - y) < p_size + 10;
	}
	
	@Override
	public String toString() 
	{
		return "(" + w1 + ", " + w2 + ", " + w3 + ")";
 	}
	
}
