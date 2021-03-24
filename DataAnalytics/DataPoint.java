package cs112project;

public class DataPoint {
	private Double f1;
	private Double f2;
	private String label;
	private Boolean isTest;
	
	//full constructor
	public DataPoint(Double f1Param, Double f2Param, String lParam, Boolean tParam) {
		this.f1 = f1Param;
		this.f2 = f2Param;
		this.label = lParam;
		this.isTest = tParam;
	}

	//default constructor
	public DataPoint() {
		this.f1 = 0.0;
		this.f2 = 0.0;
		this.label = "";
		this.isTest = false;
	}
		
	//accessors
	public Double getF1() {
		return this.f1;
	}
	public Double getF2() {
		return this.f2;
	}
	public String getLabel() {
		return this.label;
	}
	public Boolean getIsTest() {
		return this.isTest;
	}
		
	//mutators
	public void setF1(Double f1Param) {
		if (f1Param < 0.0) {
			return;
		}
		this.f1 = f1Param;
	}
	public void setF2(Double f2Param) {
		if (f2Param < 0.0) {
			return;
		}
		this.f2 = f2Param;
	}
	public void setLabel(String lParam) {
		//if (!(lParam.equals("Green")) || !(label.equals("Blue"))) {
		//	return;
		//}
		this.label = lParam;
	}
	public void setIsTest(Boolean tParam) {
		this.isTest = tParam;
	}
	
	//toString method
	public String toString() {
		return ("F1: " + this.f1 + " F2: " + this.f2 + " Label: " + this.label
				+ " isTest: " + this.isTest);
	}
}
