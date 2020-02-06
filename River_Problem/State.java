public class State {
	
	// properties
	protected int numOfMis;
	protected int numOfCan ;
	protected boolean boatSide;
	
	// default constructor
	State(){
		this.numOfCan = 0;
		this.numOfMis = 0;
		this.boatSide = false;
	}
	
	// constructor
	State( int numOfMis, int numOfCan, boolean boatSide ){
		this.numOfMis = numOfMis;
		this.numOfCan = numOfCan;
		this.boatSide = boatSide;
	}
	
	/*
	 * This method returns true if two objects' properties equal.
	 */
	public boolean equals( State s){
		return this.numOfCan == s.numOfCan && this.numOfMis == s.numOfMis && this.boatSide == s.boatSide;
	}

	/*
	 * This method converts State object to string format.
	 */
	public String toString() {
		String str = "";
		str += "(" + numOfCan + " C, " + numOfMis + " M, ";
		if( boatSide)
			str += "North)";
		else
			str += "South)";
		return str;
	}
	
}