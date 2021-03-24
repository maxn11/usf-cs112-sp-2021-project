package cs112project;

import java.util.ArrayList;
import javax.swing.*;    // all of the Swing objects
import java.awt.*;       // more windowing components, including Container

public class ProjectDriver {

	public static void initAndShowGUI() {
		//read data and test
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		Predictor tester = new DummyPredictor();
		data = (tester.readData("DataPoints.txt"));
		tester.test(data.get(0));
		tester.test(data.get(1));
		
		//accuracy and precision
		double acc = tester.getAccuracy(data);
		double prec = tester.getPercision(data);
		
		String accString = Double.toString(acc);
		String precString = Double.toString(prec);
		
		//GUI
		JFrame frame = new JFrame("Max's GUI Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		contentPane.add(new JLabel("Accuracy: " + accString));
		contentPane.add(new JLabel("Precision: " + precString));
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(          
		new Runnable() { public void run() { initAndShowGUI(); } }        
		);
	}

}
