package graphics.draw;

import graphics.marix.Vector;

/*
 * From Graphics p1
 */

public class Camera {
	
	Vector pos;
	Vector dir; 
	Vector  up;
	
	
	double yaw = -90; double pit = 0;
	
	double speed = 4d; double mouse_sensitivity = 0.04d; double key_sensitivity = 1d;
	
	public Camera() 
	{
		reset();
	}
	
	public void reset()
	{
		pos = new Vector(0, 0, 600);
		dir = pos.neg().normalize(); // Looking at the origin from pos 
		up = new Vector(0, 1, 0);
	}
	
	public Vector getPos()
	{
		return pos;
	}
	
	public Vector getTar()
	{
		return pos.add(dir);
	}
	
	public Vector  getUp()
	{
		return  up;
	}
	
	public void f()
	{
		pos = pos.add(dir.scale(speed));
	}
	
	public void d()
	{
		pos = pos.sub(dir.scale(speed));
	}
	
	public void r()
	{
		pos = pos.add(dir.cross(up).normalize().scale(speed));
	}
	
	public void l()
	{
		pos = pos.sub(dir.cross(up).normalize().scale(speed));
	}
	
	public void up()
	{
		pit += key_sensitivity; update();
	}
	
	public void down()
	{
		pit -= key_sensitivity; update();
	}
	
	public void right()
	{
		yaw += key_sensitivity; update();
	}
	
	public void left()
	{
		yaw -= key_sensitivity; update();
	}
	
	public void vertical(int dy)
	{
		pit += key_sensitivity * dy; update();
	}
	
	public void lateral(int dx)
	{
		yaw += key_sensitivity * dx; update();
	}
	
	public void update()
	{
		if(pit > 89.0d)
		  pit =  89.0d;
		
		if(pit < -89.0d)
		  pit = -89.0d;
		
		dir.set(0, Math.cos(Math.toRadians(pit)) * Math.cos(Math.toRadians(yaw)));
		dir.set(1, Math.sin(Math.toRadians(pit)));
		dir.set(2, Math.cos(Math.toRadians(pit)) * Math.sin(Math.toRadians(yaw)));
		
		dir = dir.normalize();

	}
	

}
