package graphics.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import graphics.draw.Camera;
import graphics.marix.Vector;

public class Triangle implements Comparable<Triangle> {
	
	public double[] vertices = new double[12];
	public double[] textures = new double[12];
	
	public double[] transformed_vertices = new double[12];
	
	
	// Vertices
	public Vector sh1 = new Vector(4); 
	public Vector sh2 = new Vector(4);
	public Vector sh3 = new Vector(4);
	
	
	
	// Transformed Vertices
	public Vector tp1 = new Vector(4); 
	public Vector tp2 = new Vector(4);
	public Vector tp3 = new Vector(4);
	
	// Texture data
	public Vector tx1 = new Vector(4);
	public Vector tx2 = new Vector(4);
	public Vector tx3 = new Vector(4);
	
	Polygon p = new Polygon(new int[3], new int[3], 3);
	
	// Surface Normal 
	Vector normal = new Vector(3);
	
	// Average Position 
	Vector avgpos = new Vector(3);
	
	// Direction of Light 
	
	Vector light_ = new Vector(3);
	
	
	boolean cclockwise;
	
	double diffuse = 1;
	
	
	// Compute normals for lighting

	public Triangle()
	{
		
	}
	
	public void fromTriangles()
	{
		
	}
	
	
	// Some memory allocation prevention calculation of average postion and normal vector
	public void init()
	{
		// Calculate average position 
		avgpos.set(0, (sh1.x() + sh2.x() + sh3.x()) / 3);
		avgpos.set(1, (sh1.y() + sh2.y() + sh3.y()) / 3);
		avgpos.set(2, (sh1.z() + sh2.z() + sh3.z()) / 3);
		
		// Cross Product for normal calculation
		double dx_1 = sh3.x() - sh1.x();
		double dy_1 = sh3.y() - sh1.y();
		double dz_1 = sh3.z() - sh1.z();
		
		double dx_2 = sh2.x() - sh1.x();
		double dy_2 = sh2.y() - sh1.y();
		double dz_2 = sh2.z() - sh1.z();
		
		normal.set(0, dy_1*dz_2-dz_1*dy_2);
		normal.set(1, dz_1*dx_2-dx_1*dz_2);
		normal.set(2, dx_1*dy_2-dy_1*dx_2);
		
				
		normal.inPlaceNormalise();
	}
	
	
	public void reset(Camera camera)
	{
		// Create polygon
		
		int x1 = tp1.panelX();
		int x2 = tp2.panelX();
		int x3 = tp3.panelX();
		
		int y1 = tp1.panelY();
		int y2 = tp2.panelY();
		int y3 = tp3.panelY();
		
		p.xpoints[0] = x1; p.ypoints[0] = y1;
		p.xpoints[1] = x2; p.ypoints[1] = y2;
		p.xpoints[2] = x3; p.ypoints[2] = y3;
		
		// Calculate direction from light source in place
		light_.set(0, camera.getPos().x() - avgpos.x());
		light_.set(1, camera.getPos().y() - avgpos.y());
		light_.set(2, camera.getPos().z() - avgpos.z());
		
		light_.inPlaceNormalise();
		
		double dot =  light_.x() * normal.x() + light_.y() * normal.y() + light_.z() * normal.z();
			
		diffuse = Math.max(dot, 0);
		
		
		// Backface culling using twice area
		
		cclockwise = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2) <= 0;


	}
	
	public void draw(Graphics g)
	{
		if (cclockwise)
			g.setColor(new Color(r(), g(), b())); g.fillPolygon(p);
	}

	
	// Average of w 
	public double depth()
	{
		return (tp1.w() + tp2.w() + tp3.w()) / 3;
	}
	
	public int r()
	{
		return (int) (diffuse * (tx1.x() + tx2.x() + tx3.x()) / 3);
	}
	
	public int g()
	{
		return (int) (diffuse * (tx1.y() + tx2.y() + tx3.y()) / 3);
	}
	
	public int b()
	{
		return (int) (diffuse * (tx1.z() + tx2.z() + tx3.z()) / 3);
	}

	// For sorting by depth
	@Override
	public int compareTo(Triangle o) 
	{
		return Double.compare(depth(), o.depth());
	}
	
	

}
