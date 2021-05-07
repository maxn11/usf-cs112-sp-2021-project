package cs112project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
//import java.util.List;

public class KNNPredictor extends Predictor {
	
	//fields
	private int k;
	private int surv;
	private int dead;
	private int trainCount;
	//create array list of data points
	ArrayList<DataPoint> data = new ArrayList<DataPoint>();
	
	//constructor
	public KNNPredictor(int KParam) {
		this.k = KParam;
		this.surv = 0;
		this.dead = 0;
	}
	public KNNPredictor() {
		this.k = 3;
		this.surv = 0;
		this.dead = 0;
	}
	
	//getRecordFromLine method
	public ArrayList<String> getRecordFromLine(String line) {
		//create array list of data points
		ArrayList<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line))
		{
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		//return the values array list
		return values;
	}
	
	//readData method
	public ArrayList<DataPoint> readData(String filename) {
		//create array list of data points
		//ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		try (Scanner scanner = new Scanner(new File(filename));) {
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				ArrayList<String> records = getRecordFromLine(scanner.nextLine());
				//select the columns from the records and create a DataPoint object
				//System.out.println(records.get(1));
				//System.out.println(records.get(6));
				double age;
				try {
					age = Double.parseDouble(records.get(5)); //5 because commas
				}
				catch (NumberFormatException ex) {
					age = 0.0;
				}
				//System.out.println(age);
				double fare;
				try {
					fare = Double.parseDouble(records.get(6)); //6 because commas
				}
				catch (NumberFormatException ex) {
					fare = 0.0;
				}
				catch (IndexOutOfBoundsException ignore) {
					fare = 0.0;
				}
				//double age = Double.parseDouble(records.get(4));
				//double fare = Double.parseDouble(records.get(5));
				String label;
				try {
					if(records.get(1).equals("survived")) {
						label = "0";
					} else {
						label = records.get(1); //survived status
					}
				}
				catch (NumberFormatException ex) {
					label = "0";
				}
				//String label = records.get(1); //survived status
				//store the DataPoint object in a collection
				Random rand = new Random();
				double randNum = rand.nextDouble();
				//90% of data is put into training
				//train = false
				//test = true
				if (randNum < 0.9) {
					//set the type of DataPoint as "train" and put into collection
					boolean isTest = false;
					DataPoint obj = new DataPoint(age, fare, label, isTest);
					data.add(obj);
					//adds to survived number only if train type
					if (label.equals("1")) {
						surv += 1;
					} else {
						dead += 1;
					}
					trainCount += 1;
				} else {
					//set the type of DataPoint as "test" and put into collection
					boolean isTest = true;
					DataPoint obj = new DataPoint(age, fare, label, isTest);
					data.add(obj);
				}
			}
			//print number of DataPoints of each label
			System.out.println("Survived Training DataPoints: " + surv);
			System.out.println("Deceased Training DataPoints: " + dead);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("File Not Found");
		}
		
		//return the data array list
		return data;
	}
	
	//getDistance private helper method
	private double getDistance(DataPoint p1, DataPoint p2) {
		double distance = 0.0;
		//age = x1, y1
		//fare = x2, y2
		//use distance formula
		double tempX = (p1.getF2() - p1.getF1());
		double tempY = (p2.getF2() - p2.getF1());
		tempX = tempX * tempX; //square numbers
		tempY = tempY * tempY;
		double tempZ = tempX + tempY;
		distance = Math.sqrt(tempZ); //square root
		return distance;
	}
	
	//test method
	public String test(DataPoint point) {
		//check if point is a test point
		Double[][] arr = new Double[trainCount][2];
		int xCounter = 0;
		if (point.getIsTest() == true) {
			//for (int i = 0; i < data.size(); i++) {
			for (DataPoint d: data) {
				//if (data.get(i).getIsTest() == false) {
				if (d.getIsTest() == false) {
					//double testDistance = getDistance(point, data.get(i));
					double testDistance = getDistance(point, d);
					//double testLabel = Double.parseDouble(data.get(i).getLabel());
					double testLabel = d.getLabel().equals("1")? 1.0 : 0.0;
					arr[xCounter][0] = testDistance;
					arr[xCounter][1] = testLabel;
					xCounter++;
				}
			}
			//System.out.println("-----------");
			//System.out.println(arr[0][1]);
			//sort 2d array
			java.util.Arrays.sort(arr, new java.util.Comparator<Double[]>() {
				public int compare(Double[] a, Double[] b) {
					return a[0].compareTo(b[0]);
					}
				});
			//return the label that occurs more within the first "k" indexes
			//System.out.println(arr[0][1]);
			int oneCounter = 0;
			int zeroCounter = 0;
			for (int i = 0; i < k; i++) {
				double kLabel = arr[i][1];
				if (kLabel == 0.0) {
					zeroCounter++;
				} else {
					oneCounter++;
				}
			}
			//System.out.println(zeroCounter);
			//System.out.println(oneCounter);
			if (zeroCounter > oneCounter) {
				return ("0");
			} else {
				return ("1");
			}
		}
		return (null);
	}
	
	//graph helper method
	/*
	public ArrayList<Double> graphHelper(List<DataPoint> data) {
		ArrayList<Double> arrList = new ArrayList<Double>();
		double truePositive = 0;
		double falsePositive = 0;
		double trueNegative = 0;
		double falseNegative = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getIsTest() == true) {
				String labelTest = test(data.get(i));
				if(labelTest.equals("1") && data.get(i).getLabel().equals("1")) {
					truePositive++;
				}
				else if(labelTest.equals("0") && data.get(i).getLabel().equals("0")) {
					trueNegative++;
				}
				else if(labelTest.equals("1") && data.get(i).getLabel().equals("0")) {
					falsePositive++;
				}
				else if(labelTest.equals("0") && data.get(i).getLabel().equals("1")) {
					falseNegative++;
				}
			}
		}
		arrList.add(truePositive);
		arrList.add(falsePositive);
		arrList.add(trueNegative);
		arrList.add(falseNegative);
		return arrList;
	}
	*/
	
	//getAccuracy method
	public Double getAccuracy(ArrayList<DataPoint> dataParam) {
		double truePositive = 0;
		double falsePositive = 0;
		double trueNegative = 0;
		double falseNegative = 0;
		for (int i = 0; i < dataParam.size(); i++) {
			if (dataParam.get(i).getIsTest() == true) {
				String labelTest = test(dataParam.get(i));
				if(labelTest.equals("1") && dataParam.get(i).getLabel().equals("1")) {
					truePositive++;
				}
				else if(labelTest.equals("0") && dataParam.get(i).getLabel().equals("0")) {
					trueNegative++;
				}
				else if(labelTest.equals("1") && dataParam.get(i).getLabel().equals("0")) {
					falsePositive++;
				}
				else if(labelTest.equals("0") && dataParam.get(i).getLabel().equals("1")) {
					falseNegative++;
				}
			}
		}
		System.out.println(truePositive);
		System.out.println(falsePositive);
		System.out.println(trueNegative);
		System.out.println(falseNegative);
		return ((truePositive + trueNegative) / (truePositive + trueNegative + 
				 falsePositive + falseNegative));
	}
	
	//getPercision method
	public Double getPercision(ArrayList<DataPoint> dataParam) {
		double truePositive = 0;
		@SuppressWarnings("unused")
		double falsePositive = 0;
		@SuppressWarnings("unused")
		double trueNegative = 0;
		double falseNegative = 0;
		for (int i = 0; i < dataParam.size(); i++) {
			if (dataParam.get(i).getIsTest() == true) {
				String labelTest = test(dataParam.get(i));
				if(labelTest.equals("1") && dataParam.get(i).getLabel().equals("1")) {
					truePositive++;
				}
				else if(labelTest.equals("0") && dataParam.get(i).getLabel().equals("0")) {
					trueNegative++;
				}
				else if(labelTest.equals("1") && dataParam.get(i).getLabel().equals("0")) {
					falsePositive++;
				}
				else if(labelTest.equals("0") && dataParam.get(i).getLabel().equals("1")) {
					falseNegative++;
				}
			}
		}
		//System.out.println(truePositive);
		//System.out.println(falsePositive);
		//System.out.println(trueNegative);
		//System.out.println(falseNegative);
		//fn tp tn emer
		//tp tp fn right
		return (falseNegative / (truePositive + trueNegative));
	}
	
}
