package graphics.data;

import java.util.ArrayList;
import java.util.List;

import graphics.marix.Vector;

public class Face 
{
	
	// Mesh, Base and EV Constant data
	Data data;
	
	// Face identifier
	int face_no;
	
	public double w = 0; 

	public List<Triangle> triangles = new ArrayList<Triangle>();

	public Face(Data data, int face_no)
	{
		this.data = data; this.face_no = face_no;
		
		load();
	}
	
	public Face()
	{
		
	}

	public void load()
	{
				
		List<Double> sh_f = Config.load(String.format("sh_%03d.csv", face_no));
		List<Double> tx_f = Config.load(String.format("tx_%03d.csv", face_no));
		
		// A scale factor to scale everything between (-1, 1)
		double scale_factor = 0.000025;

		// Constant given face_no
		double sh_e_const = data.sh_e.get(face_no - 1);

		// Constant given face_no
		double tx_e_const = data.tx_e.get(face_no - 1);
		
		for (int i = 0; i < data.mesh.size() / 3; i++)
		{
			
			int mesh_index_base = i * 3;
			
			Triangle triangle = new Triangle();
									
			// 3 line indices to vertices (x, y, z) in a triangle in sh_file and tx_file
			int v1_index = (data.mesh.get(mesh_index_base + 0) - 1) * 3; 
			int v2_index = (data.mesh.get(mesh_index_base + 1) - 1) * 3; 
			int v3_index = (data.mesh.get(mesh_index_base + 2) - 1) * 3;
						
			// Triangle Vertex 1
			double v1x = (data.sh_b.get(v1_index)     + sh_f.get(v1_index    ) * sh_e_const) * scale_factor;
			double v1y = (data.sh_b.get(v1_index + 1) + sh_f.get(v1_index + 1) * sh_e_const) * scale_factor;
			double v1z = (data.sh_b.get(v1_index + 2) + sh_f.get(v1_index + 2) * sh_e_const) * scale_factor;
			
			// Triangle Texture 1  
			double t1r = (data.tx_b.get(v1_index)     + tx_f.get(v1_index)     * tx_e_const);
			double t1g = (data.tx_b.get(v1_index + 1) + tx_f.get(v1_index + 1) * tx_e_const);
			double t1b = (data.tx_b.get(v1_index + 2) + tx_f.get(v1_index + 2) * tx_e_const);
			
			triangle.sh1 = new Vector(v1x, v1y, v1z, 1);
			triangle.tx1 = new Vector(t1r, t1g, t1b, 1);
			
			// Triangle Vertex 2
			double v2x = (data.sh_b.get(v2_index)     + sh_f.get(v2_index    ) * sh_e_const) * scale_factor;
			double v2y = (data.sh_b.get(v2_index + 1) + sh_f.get(v2_index + 1) * sh_e_const) * scale_factor;
			double v2z = (data.sh_b.get(v2_index + 2) + sh_f.get(v2_index + 2) * sh_e_const) * scale_factor;
			
			// Triangle Tx2
			double t2r = (data.tx_b.get(v2_index)     + tx_f.get(v2_index)     * tx_e_const);
			double t2g = (data.tx_b.get(v2_index + 1) + tx_f.get(v2_index + 1) * tx_e_const);
			double t2b = (data.tx_b.get(v2_index + 2) + tx_f.get(v2_index + 2) * tx_e_const);
			
			triangle.sh2 = new Vector(v2x, v2y, v2z, 1);
			triangle.tx2 = new Vector(t2r, t2g, t2b, 1);
			
			// Triangle Vertex 3
			double v3x = (data.sh_b.get(v3_index)     + sh_f.get(v3_index    ) * sh_e_const) * scale_factor;
			double v3y = (data.sh_b.get(v3_index + 1) + sh_f.get(v3_index + 1) * sh_e_const) * scale_factor;
			double v3z = (data.sh_b.get(v3_index + 2) + sh_f.get(v3_index + 2) * sh_e_const) * scale_factor;

			// Triangle Tx3
			double t3r = (data.tx_b.get(v3_index)     + tx_f.get(v3_index)     * tx_e_const);
			double t3g = (data.tx_b.get(v3_index + 1) + tx_f.get(v3_index + 1) * tx_e_const);
			double t3b = (data.tx_b.get(v3_index + 2) + tx_f.get(v3_index + 2) * tx_e_const);
			
			triangle.sh3 = new Vector(v3x, v3y, v3z, 1);
			triangle.tx3 = new Vector(t3r, t3g, t3b, 1);
			
		//	triangle.init();
			
			triangles.add(triangle);
		

		}
		

	}
	
	 
	
	
	
	
	
}
