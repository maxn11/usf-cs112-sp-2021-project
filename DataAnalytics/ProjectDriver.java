package cs112project;

import java.util.ArrayList;
import javax.swing.*;    // all of the Swing objects
import java.awt.*;       // more windowing components, including Container
import java.text.DecimalFormat;

public class ProjectDriver {

	public static void initAndShowGUI() {
		//read data
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		Predictor obj = new KNNPredictor();
		data = (obj.readData("titanic.csv"));
		//COPY AND PASTE THE ABOVE INTO MAIN METHOD OF GRAPH
		
		//accuracy and precision
		double acc = obj.getAccuracy(data);
		double prec = obj.getPercision(data);
		
		acc = acc * 100;
		prec = prec * 100;
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		String accString = df2.format(acc);
		String precString = df2.format(prec);
		
		//String accString = Double.toString(acc);
		//String precString = Double.toString(prec);
		
		//GUI
		JFrame frame = new JFrame("Max's GUI Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		contentPane.add(new JLabel("Accuracy: " + accString + "%"));
		contentPane.add(new JLabel("Precision: " + precString + "%"));
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(          
		new Runnable() { public void run() { initAndShowGUI(); } }        
		);
	}

}
