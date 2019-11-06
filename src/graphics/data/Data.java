package graphics.data;

import java.util.ArrayList;
import java.util.List;

public class Data {

	ArrayList<Integer> mesh = Config.loadInt("mesh.csv"); 
	
	// Shape
	List<Double> sh_b = Config.load("sh_000.csv");
	List<Double> sh_e = Config.load("sh_ev.csv");
	
	// Texture
	List<Double> tx_b = Config.load("tx_000.csv");
	List<Double> tx_e = Config.load("tx_ev.csv");
	
	public Data()
	{
		
	}

}
