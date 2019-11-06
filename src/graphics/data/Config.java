package graphics.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Config {

	final static String directory = "CS4102 2019 P2 data/"; 

	public static ArrayList<Double> load(String file) 
	{
		ArrayList<Double> data = new ArrayList<Double>();

		String _file_ = String.format("%s%s", Config.directory, file);

		try {
			BufferedReader bReader = new BufferedReader(new FileReader(_file_));

			String line = null;

			int i = 0;

			while ((line = bReader.readLine()) != null)
			{
				for (String index : line.trim().split(","))
				{
					data.add(Double.parseDouble(index));
				}

			}

			bReader.close(); return data;

		} catch (FileNotFoundException e) {
			System.out.println("Could not find " + _file_); 
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Could not read from " + _file_); 
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Integer> loadInt(String file) 
	{
		ArrayList<Integer> data = new ArrayList<Integer>();

		String _file_ = String.format("%s%s", Config.directory, file);

		try {
			BufferedReader bReader = new BufferedReader(new FileReader(_file_));

			String line = null;

			int i = 0;

			while ((line = bReader.readLine()) != null)
			{
				for (String index : line.trim().split(","))
				{
					data.add(Integer.parseInt(index));
				}

			}

			bReader.close(); return data;

		} catch (FileNotFoundException e) {
			System.out.println("Could not find " + _file_); 
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Could not read from " + _file_); 
			e.printStackTrace();
		}

		return null;
	}


}
