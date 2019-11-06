package graphics.marix;

/*
 * Library from Graphics Practical 1
 */

public class MatrixUtil {

	public static Matrix Rotate3d(double rotation)
	{
		Matrix matrix = new Matrix(4, 4);

		// Rotation around the y axis
		
		double c = Math.cos(rotation);
		double s = Math.sin(rotation);
				
		matrix.set(0, 0, c); matrix.set(0, 2, s);
		matrix.set(1, 1, 1);
		matrix.set(2, 0,-s); matrix.set(2, 2, c);
		
		matrix.set(3, 3, 1);
				
		return matrix;
	}


	public static Matrix Scale3d(double scale_factor)
	{
		Matrix matrix = new Matrix(4, 4);

		for (int i = 0; i < 3; i++)
			matrix.set(i, i, scale_factor);

		matrix.set(3, 3, 1);

		return matrix;
	}


	public static Matrix Translation3d(double x, double y, double z)
	{
		Matrix matrix = new Matrix(4, 4);

		for (int i = 0; i < 4; i++)
			matrix.set(i, i, 1);

		matrix.set(0, 3, x);
		matrix.set(1, 3, y);
		matrix.set(2, 3, z);
		
		return matrix;
	}
	
	
	public static Matrix model(double x, double y, double z, double scale_factor, double rotation)
	{
		return Translation3d(x, y, z).mul(Scale3d(scale_factor)).mul(Rotate3d(rotation));
	}

	
	/*
	 * http://in2gpu.com/2015/05/17/view-matrix/ (Example was wrong, but I amended it)
	 */
	public static Matrix view(Vector pos, Vector tar, Vector up)
	{
		Matrix matrix = new Matrix(4, 4);
		
		Vector f = tar.sub(pos);
		f = f.normalize();
		Vector s = f.cross(up);
		s = s.normalize();
		Vector v = s.cross(f);
		
		matrix.set(0, 0, s.x()); matrix.set(0, 1	, s.y()); matrix.set(0, 2, s.z()); matrix.set(0, 3, -s.dot(pos));
		matrix.set(1, 0, v.x()); matrix.set(1, 1	, v.y()); matrix.set(1, 2, v.z()); matrix.set(1, 3, -v.dot(pos));
		matrix.set(2, 0,-f.x()); matrix.set(2, 1	,-f.y()); matrix.set(2, 2,-f.z()); matrix.set(2, 3,  f.dot(pos));
		matrix.set(3, 0, 0    ); matrix.set(3, 1	, 0    ); matrix.set(3, 2, 0    ); matrix.set(3, 3,  1d        );
		
		return matrix;
	}
	
	/*
	 * Perspective Projection
	 * http://in2gpu.com/2015/05/23/enter-the-matrix-and-projection/
	 */
	public static Matrix projP(double ar, double near, double far, double angle)
	{
	    Matrix m = new Matrix(4, 4);	
	    
	    m.set(0, 0, 1d / (ar * Math.tan(angle / 2)));
	    m.set(1, 1, 1d / Math.tan(angle / 2));
	    m.set(2, 2, (-near -far) / (near - far));
	    m.set(3, 2, 1d);
	    m.set(2, 3, 2d * near * far /(near - far));
	 
	    return m;
	}
	
}
