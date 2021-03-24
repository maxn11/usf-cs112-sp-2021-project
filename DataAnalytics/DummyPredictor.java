package cs112project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DummyPredictor extends Predictor {
	
	public ArrayList<DataPoint> readData(String filename) {
		//create array list of data points
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		try
		{
			Scanner scn = new Scanner(new File(filename));
			while (scn.hasNextLine())
			{
				//reading file
				String tempX = scn.nextLine();
				String tempY = scn.nextLine();
				//splitting data by spaces
				String[] splitX = tempX.split(" ");
				String[] splitY = tempY.split(" ");
				//separated pieces
				double xF1 = Double.parseDouble(splitX[0]);
				double xF2 = Double.parseDouble(splitX[1]);
				String xLabel = (splitX[2]);
				boolean xIsTest = Boolean.parseBoolean(splitX[3]); 
				double yF1 = Double.parseDouble(splitY[0]);
				double yF2 = Double.parseDouble(splitY[1]);
				String yLabel = (splitY[2]);
				boolean yIsTest = Boolean.parseBoolean(splitY[3]);
				
				//create DataPoint objects from data
				DataPoint objX = new DataPoint(xF1, xF2, xLabel, xIsTest);
				data.add(objX);
				DataPoint objY = new DataPoint(yF1, yF2, yLabel, yIsTest);
				data.add(objY);
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("File Not Found");
		}
		
		//return the data array list
		return data;
	}
	
	public String test(DataPoint data) {
		return ("Good");
	}
	
	public Double getAccuracy(ArrayList<DataPoint> data) {
		return 1.0;
	}
	public Double getPercision(ArrayList<DataPoint> data) {
		return 2.0;
	}
}
