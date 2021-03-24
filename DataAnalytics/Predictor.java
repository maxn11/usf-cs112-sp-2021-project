package cs112project;

import java.util.ArrayList;

public abstract class Predictor {
	//abstract methods
	public abstract ArrayList<DataPoint> readData(String filename);
	public abstract String test(DataPoint data);
	public abstract Double getAccuracy(ArrayList<DataPoint> data);
	public abstract Double getPercision(ArrayList<DataPoint> data);
}
