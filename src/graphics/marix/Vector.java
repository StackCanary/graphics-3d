package graphics.marix;

/*
 * Library from Graphics Practical 1
 */

public class Vector {
	
	double[] vector; public int size;
	
	public Vector(int size)
	{
		vector = new double[size]; this.size = size;
	}
	
	public Vector(double x, double y)
	{
		this(2);
		
		set(0, x);
		set(1, y);
	}
	
	public Vector(double x, double y, double z)
	{
		this(3);
		
		set(0, x);
		set(1, y);
		set(2, z);
	}
	
	public Vector(double x, double y, double z, double h)
	{
		this(4);
		
		set(0, x);
		set(1, y);
		set(2, z);
		set(3, h);
	}
	
	public int panelX()
	{
		return (int) ((1 - x()) * 200);
	}
	
	public int panelY()
	{
		return (int) ((1 + y()) * 200);
	}
	
	public double x()
	{
		return get(0);
	}
	
	public double y()
	{
		return get(1);
	}
	
	public double z()
	{
		return get(2);
	}
	
	public double w()
	{
		return get(3);
	}

	public void perspectiveDivide()
	{
		double z = vector[2];
		
		vector[0] = vector[0] / vector[3];
		vector[1] = vector[1] / vector[3];
		vector[2] = vector[2] / vector[3];
		vector[3] = z;
	} 
	
	public double get(int i)
	{
		return vector[i];
	}
	
	public void set(int i, double val)
	{
		vector[i] = val; 
	}
	
	public double dot(Vector v)
	{
		assert(v.size == this.size);

		double result = 0;
		
		for (int i = 0; i < size; i++)
		{
			result += this.vector[i] * v.vector[i];
		}
		
		return result;
	}
	
	public void inPlaceScale(double scale_factor)
	{

		for (int i = 0; i < size; i++)
		{
			vector[i] = vector[i] * scale_factor; 
		}
		
	}

	public Vector scale(double scale_factor)
	{
		Vector result = new Vector(size); 
		
		for (int i = 0; i < size; i++)
		{
			result.vector[i] = vector[i] * scale_factor; 
		}
		
		return result;
	}
	
	public Vector sub(Vector v)
	{
		assert(v.size == this.size);
		
		Vector result = new Vector(size);

		for (int i = 0; i < size; i++)
		{
			result.vector[i] = vector[i] - v.vector[i];
		}
		
		return result;
	}
	
	public Vector add(Vector v)
	{
		assert(v.size == this.size);
		
		Vector result = new Vector(size);

		for (int i = 0; i < size; i++)
		{
			result.vector[i] = vector[i] + v.vector[i];
		}
		
		return result;
	}
	
	public Vector neg()
	{
		Vector result = new Vector(size);
		
		for (int i = 0; i < size; i++)
		{
			result.vector[i] = -vector[i];
		}
		
		return result;
	}
	

	
	public void inPlaceNormalise()
	{
		double scale_factor = 0;
		
		for (int i = 0; i < size; i++)
		{
			scale_factor += Math.pow(vector[i], 2); 
		}
		
		scale_factor = 1/Math.sqrt(scale_factor);
	
		this.inPlaceScale(scale_factor);	
	}
	
	public Vector normalize()
	{
		double scale_factor = 0;
		
		for (int i = 0; i < size; i++)
		{
			scale_factor += Math.pow(vector[i], 2); 
		}
		
		scale_factor = 1/Math.sqrt(scale_factor);
	
		return this.scale(scale_factor);
	}
	
	public Vector cross(Vector v)
	{
		assert(v.size == this.size);
		
		if (size < 3)
			throw new RuntimeException("Vector Cross Product is only implemented for >= Size 3");
		
		Vector result = new Vector(size);

		result.vector[0] = vector[1]*v.vector[2]-vector[2]*v.vector[1]; 
		result.vector[1] = vector[2]*v.vector[0]-vector[0]*v.vector[2];
		result.vector[2] = vector[0]*v.vector[1]-vector[1]*v.vector[0];
		
		
		return result;
	}
	
	public Vector clone()
	{
		Vector v = new Vector(size);
		
		for (int i = 0; i < size; i++)
			v.set(i, get(i));
		
		return v;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("(");
		
		for (int i = 0; i < size; i++)
		{
			sb.append(get(i));
			
			if (i < size - 1)
				sb.append(", ");
			
		}
		
		sb.append(")");
		
		return sb.toString();
	}
	
	public static Vector origin3d()
	{
		return new Vector(0, 0, 0);
	}
	
	public boolean safeForViewPort(int w, int h)
	{
		double factor = 3;
		
		return panelX() >= -200 && panelX() <= w * factor && panelY() >= -200 && panelY() <= h * factor;
		
	}
	
}
