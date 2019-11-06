package graphics.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Faces {

	public Face face1;
	public Face face2;
	public Face face3;
	
	public Face face;

	public Faces(Face face1, Face face2, Face face3)
	{
		this.face1 = face1;
		this.face2 = face2;
		this.face3 = face3;
		
		reset();
	}

	/*
	 * Performs the interpolation the faces using a weighted average
	 */
	public void reset()
	{
		
		boolean flag = face == null;
		
		if (flag)
			face = new Face();
		
		List<Triangle> t1 = face1.triangles;
		List<Triangle> t2 = face2.triangles;
		List<Triangle> t3 = face3.triangles;

		double w1 = face1.w;
		double w2 = face2.w;
		double w3 = face3.w;

		// Compute weighted average of vertices and texture data
		for (int i = 0; i < t1.size(); i++)
		{
			
			Triangle triangle = flag ? new Triangle() : face.triangles.get(i);
			
			triangle.sh1 = t1.get(i).sh1.scale(w1).add(t2.get(i).sh1.scale(w2)).add(t3.get(i).sh1.scale(w3));
			triangle.sh2 = t1.get(i).sh2.scale(w1).add(t2.get(i).sh2.scale(w2)).add(t3.get(i).sh2.scale(w3));
			triangle.sh3 = t1.get(i).sh3.scale(w1).add(t2.get(i).sh3.scale(w2)).add(t3.get(i).sh3.scale(w3));

			triangle.sh1.set(3, 1);
			triangle.sh2.set(3, 1);
			triangle.sh3.set(3, 1);

			triangle.tx1 = t1.get(i).tx1.scale(w1).add(t2.get(i).tx1.scale(w2)).add(t3.get(i).tx1.scale(w3));
			triangle.tx2 = t1.get(i).tx2.scale(w1).add(t2.get(i).tx2.scale(w2)).add(t3.get(i).tx2.scale(w3));
			triangle.tx3 = t1.get(i).tx3.scale(w1).add(t2.get(i).tx3.scale(w2)).add(t3.get(i).tx3.scale(w3));
			
			triangle.init();

			if (flag)
				face.triangles.add(triangle);

		}
	}
	
	public void swap(int no, Face face)
	{

		Face a = null;
		Face b = null;
		Face r = null;

		switch(no)
		{
		case 1: r = face1; a = face2; b = face3; break;
		case 2: r = face2; a = face1; b = face3; break;
		case 3: r = face3; a = face1; b = face2; break;
		}
		
		// Compute weighted average of vertices and texture data
		for (int i = 0; i < a.triangles.size(); i++)
		{
			
			// Triangle for each face
			Triangle face_a_triangle = a.triangles.get(i);
			Triangle face_b_triangle = b.triangles.get(i); 
			Triangle face_r_triangle = face.triangles.get(i); 

			// Vertices
			face_a_triangle.sh1 = face_a_triangle.sh1.add(face_r_triangle.sh1).scale(0.5);
			face_a_triangle.sh2 = face_a_triangle.sh2.add(face_r_triangle.sh2).scale(0.5);
			face_a_triangle.sh3 = face_a_triangle.sh3.add(face_r_triangle.sh3).scale(0.5); 

			face_b_triangle.sh1 = face_b_triangle.sh1.add(face_r_triangle.sh1).scale(0.5); 
			face_b_triangle.sh2 = face_b_triangle.sh2.add(face_r_triangle.sh2).scale(0.5); 
			face_b_triangle.sh3 = face_b_triangle.sh3.add(face_r_triangle.sh3).scale(0.5); 
			
			// Vertices
			face_a_triangle.tx1 = face_a_triangle.tx1.add(face_r_triangle.tx1).scale(0.5); 
			face_a_triangle.tx2 = face_a_triangle.tx2.add(face_r_triangle.tx2).scale(0.5); 
			face_a_triangle.tx3 = face_a_triangle.tx3.add(face_r_triangle.tx3).scale(0.5); 

			face_b_triangle.tx1 = face_b_triangle.tx1.add(face_r_triangle.tx1).scale(0.5);
			face_b_triangle.tx2 = face_b_triangle.tx2.add(face_r_triangle.tx2).scale(0.5);
			face_b_triangle.tx3 = face_b_triangle.tx3.add(face_r_triangle.tx3).scale(0.5);
			
		}
		
		switch(no)
		{
		case 1: this.face1 = face; break;
		case 2: this.face2 = face; break;
		case 3: this.face3 = face; break;
		}
		

		reset();
	}


}
