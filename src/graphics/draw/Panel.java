package graphics.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;

import javax.swing.JPanel;

import graphics.data.Data;
import graphics.data.Face;
import graphics.data.Faces;
import graphics.data.Triangle;
import graphics.marix.Matrix;
import graphics.marix.MatrixUtil;


// Some elements of this practical are from the Graphics1 Practical 

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	GfxTriangle gfxTriangle; Mouse mouse;
	
	double tx = 0;
	double ty = 0;
	double tz = 0;
	double sf = 100;

	   int w = 400; 
	   int h = 400;
	double a = Math.PI / 3; // Fov
	double n = 0.1d;
	double f = 10000.0d;
	
	int rotation = 0; final double rotation_epislon = Math.PI / 18;
	
	
	Matrix m = null;
	
	public Camera camera = new Camera();
	
	Data data = new Data();
	
	Faces faces;

	
	public Panel()
	{
		gfxTriangle = new GfxTriangle(this); mouse = new Mouse(this);	
		
		setBackground(Color.BLACK);
		
		Face face1 = new Face(data, 1);
		Face face2 = new Face(data, 2);
		Face face3 = new Face(data, 3);
		
		faces = new Faces(face1, face2, face3);  

		resetFace();
		
		setFocusable(true);
				
		addMouseListener(mouse); addMouseMotionListener(mouse); 
		
		addKeyListener(new Keyboard(this));

		resetCamera();
	}
	
	public void resetFace()
	{
		// Set face weights
		
		faces.face1.w = gfxTriangle.w1;
		faces.face2.w = gfxTriangle.w2;
		faces.face3.w = gfxTriangle.w3;
		
		faces.reset(); resetCamera();
	}
	
	// Go from model space to clip space and then perspective divide 
	public void resetCamera()
	{
		m = MatrixUtil.projP((double) h / (double) w, n, f, a).mul(MatrixUtil.view(camera.getPos(), camera.getTar(), camera.getUp()).mul(MatrixUtil.model(tx, ty, tz, sf, rotation * rotation_epislon)));
		
		for (Triangle t : faces.face.triangles)
		{
			
			t.tp1 = m.mul(t.sh1, t.tp1);
			t.tp2 = m.mul(t.sh2, t.tp2);
			t.tp3 = m.mul(t.sh3, t.tp3);
			
			t.tp1.perspectiveDivide();
			t.tp2.perspectiveDivide();
			t.tp3.perspectiveDivide();
			
			t.reset(camera);
		}
		
		// Sort using depth
		Collections.sort(faces.face.triangles);
		
		rehash();
	
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		// draw face
		for (Triangle t : faces.face.triangles)
		{
			t.draw(g);
		}
		
		// draw selection triangle
		gfxTriangle.draw(g);

	}
	
	
	
	public void rehash()
	{
		repaint(); revalidate();
	}

}
