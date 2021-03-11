package cs112practice;

public class Pokemon {
	
	// Name: Maximus Nie
	// abstract methods are at the bottom
	
	// Can only be accessed by the inherited class 
	private String name;  
	private String color;  
	private boolean hasTrainer;
	// Constructor  
	public Pokemon(String nParam, String cParam) {    
		this.name = nParam;    
		this.color = cParam;    
		this.hasTrainer = false;  
		}  
	public Pokemon(String nParam, String cParam, boolean hParam) {  
		this.name = nParam;  
		this.color = cParam;  
		this.hasTrainer = hParam;  
		}  
	// Default constructor  
	public Pokemon() {  
		this.name = "";  
		this.color = "";  
		this.hasTrainer = false;  
		}  
	// Accessor  
	public String getName() {    
		return this.name;  
		}  
	public String getColor() {  
		return this.color;  
		}  
	public boolean hasTrainer() {  
		return this.hasTrainer;  
		}  
	// Mutator  
	public void setName(String nParam) {    
		this.name = nParam;  
		}  
	public void setColor(String cParam) {  
		this.color = cParam;  
		}  
	public void setHasTrainer(boolean hParam) {  
		this.hasTrainer = hParam;  
		}  
	// Method  
	public String toString() {    
		return "I am a Pokemon: " + this.name + " : " + this.color + " : " + this.hasTrainer();
	}
	
	// 2/18/21 method
	public void move(String word) {
		System.out.println(word);
	}
	
	// 2/18/21 equals function
	public boolean equals(Object obj) {
		if(obj instanceof Pokemon) {
			Pokemon other = (Pokemon) obj;
			if(this.name == other.getName() && this.color == other.getColor()
			   && this.hasTrainer == other.hasTrainer()) {
				return true;
			}
		}
		return false;
	}
	
}

// Comment
// made
// by 
// Sobia