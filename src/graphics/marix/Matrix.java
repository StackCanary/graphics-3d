package graphics.marix;

/*
 * Library from Graphics Practical 1
 */

public class Matrix {

	public double[][] matrix; int rows, cols;

	public Matrix(int rows, int cols)
	{
		matrix = new double[rows][cols]; 
		this.rows = rows; 
		this.cols = cols;
	}

	public void set(int row, int col, double val)
	{
		matrix[row][col] = val;
	}

	public double get(int row, int col)
	{
		return matrix[row][col];
	}
	
	public double[] fast_multiplication(double[] vectors)
	{
		double[] result = new double[vectors.length];
		
		for (int i = 0; i < vectors.length / 4; i ++)
		{
			int base = i * 4;
			
			for (int r = 0; r < rows; r++)
			{
				int dot = 0;

				for (int k = 0; k < cols; k++)
				{
					dot += matrix[r][k] * vectors[base + k];  
				}
				
				result[base + r] = dot;
			}
			
		}

		return result;
	}
	
	public Vector mul(Vector v)
	{
		assert(cols == v.size);
		
		Vector result = new Vector(v.size);

		for (int r = 0; r < rows; r++)
		{
			int dot = 0;

			for (int k = 0; k < cols; k++)
			{
				dot += matrix[r][k] * v.get(k);  
			}

			result.set(r, dot);
		}
		
		return result;
	}
	
	public Vector mul(Vector v, Vector result)
	{
		assert(cols == v.size);
		
		for (int r = 0; r < rows; r++)
		{
			int dot = 0;

			for (int k = 0; k < cols; k++)
			{
				dot += matrix[r][k] * v.get(k);  
			}

			result.set(r, dot);
		}
		
		return result;
	}

	public Matrix mul(Matrix m)
	{
		assert(this.cols == m.rows);

		Matrix result = new Matrix(this.rows, m.cols);

		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < m.cols; c++)
			{
				int dot = 0;

				for (int k = 0; k < m.rows; k++)
				{
					dot += matrix[r][k] * m.matrix[k][c];  
				}

				result.matrix[r][c] = dot;
			}
		}

		return result;
	}

	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				sb.append(matrix[row][col] + " ,");
			}

			sb.append("\n");
		}
		
		return sb.toString();
	}

	public static Matrix identity4d()
	{
		Matrix result = new Matrix(4, 4);
		
		for (int i = 0; i < 4; i++)
			result.set(0, 0, 1);
		
		return result;
	}


}
