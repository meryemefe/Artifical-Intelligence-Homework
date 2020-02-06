import java.util.Scanner;

public class SolutionClass {

	public static void main(String[] args) {
		
		// Create two stacks. Stack "visited" holds visited stacks and stack "path" holds possible paths.
		ModifiedStack visited = new ModifiedStack();
		ModifiedStack path = new ModifiedStack();
		
		// n represents how many cannibals or missionaries there are.
		int n;
		System.out.println( "Enter how many cannibals and missionaries you want.\n(For example, if you enter 3, the program tries to solve the problem for 3 cannibals and 3 missionaries.)");
		Scanner scan = new Scanner( System.in);
		n = scan.nextInt();
		
		// Create initial state and goal state.
		State initialState = new State( n, n, true);
		State finalState = new State( 0, 0, false);
		
		// Add initialState to both visited and path stack.
		path.push( initialState);
		visited.push( initialState);
		
		// Create two states: cur holds current state and temp is temporary state to use in checking
		State cur = initialState;
		State temp = new State();

		// To see every single steps separately, user entered 1 every time
		System.out.println( "To continue to create possible solution, enter 1 for each step.");
		int cont;
		cont = scan.nextInt();

		while( cont != 1)
			cont = scan.nextInt();
		System.out.println( path);
		
		// If path is empty, this means no solution is found and found will be remarked as false.
		boolean found = true;
		
		/********************************/
		/* DEPTH FIRST SEARCH ALGORITHM */
		/********************************/

		// Search solution until you reach the goal
		while( !cur.equals( finalState) ) {
			
			// If path is empty, this means there is no solution and stop searching 
			if( path.isEmpty() ) {
				found = false;
				break;
			}
			
			// In order to move next step, user should enter 1
			do {
				cont = scan.nextInt();					
			} while( cont != 1);

			// Get next state
			temp = stateGenerator( cur, n, visited);	
			
			// Main depth-first search
			if( temp != null) {
				path.push( temp);
				cur = temp;
			} else {
				path.pop();
				if( !path.isEmpty())
					cur = path.peek();
			}
			System.out.println( path);
		}
		
		if( found == false)
			System.out.println( "\nNo solution found!");
		else
			displaySolution( path, n);
		
		scan.close();
	}
	
	/*
	 * This method prints solutions to show number of people in both sides of the river in every steps.
	 */
	public static void displaySolution( ModifiedStack path, int n) {
		System.out.println( "\n\t   SOLUTION STEPS\n");
		for( int i = 0; i < path.size(); i++) {
			String str;
			str = path.elementAt(i).numOfCan + " C, " + path.elementAt(i).numOfMis + " M";
			if( path.elementAt(i).boatSide == true)
				str += "  -------------->  ";
			else
				str += "  <--------------  ";
			str += (n - path.elementAt(i).numOfCan) + " C, " + (n - path.elementAt(i).numOfMis) + " M";
			System.out.println( str);
		}
	}

	/*
	 * This method finds next state if there is any possible state which provides all conditions.
	 * Then, it checks this whether state is visited before. If it is not visited, the method adds it to visited stack and return this state. 
	 * Otherwise, it returns null.
	 * Every condition is written explicitly.
	 */
	public static State stateGenerator( State current, int n, ModifiedStack visited) {

		// If the boat is in the north side
		if( current.boatSide == true) {
			// Carry 2 cannibals
			if( current.numOfCan >= 2 && (((current.numOfMis >= current.numOfCan - 2) && (n - current.numOfMis >= n- current.numOfCan + 2)) 
					|| current.numOfMis == 0 || n - current.numOfMis == 0) ) {
				State nextState = new State( current.numOfMis, current.numOfCan - 2, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 cannibal and 1 missionary
			if( current.numOfCan >= 1 && current.numOfMis >= 1 && (((current.numOfMis - 1 >= current.numOfCan - 1) && (n - current.numOfMis + 1 >= n- current.numOfCan + 1))
					|| current.numOfMis - 1 == 0) ) {
				State nextState = new State( current.numOfMis - 1, current.numOfCan - 1, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
				
			// Carry 2 missionaries
			if( current.numOfMis >= 2 && (((current.numOfMis - 2 >= current.numOfCan) && (n - current.numOfMis + 2 >= n- current.numOfCan))
					|| current.numOfMis - 2 == 0) ) {
				State nextState = new State( current.numOfMis - 2, current.numOfCan, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 cannibal
			if( current.numOfCan >= 1 && (((current.numOfMis >= current.numOfCan - 1) && (n - current.numOfMis >= n- current.numOfCan + 1))
					|| current.numOfMis == 0 || n- current.numOfMis == 0) ) {
				State nextState = new State( current.numOfMis, current.numOfCan - 1, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 missionary
			if( current.numOfMis >= 1 && (((current.numOfMis - 1 >= current.numOfCan) && (n - current.numOfMis + 1 >= n- current.numOfCan))
					|| current.numOfMis - 1 == 0) ) {
				State nextState = new State( current.numOfMis - 1, current.numOfCan, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
		}
		// If boat is in the south side
		else {
			// Carry 2 cannibals
			if( n - current.numOfCan >= 2 && (((n - current.numOfMis >= n - current.numOfCan - 2) && (current.numOfMis >= current.numOfCan + 2)) 
					|| n - current.numOfMis == 0 || current.numOfMis == 0) ) {
				State nextState = new State( current.numOfMis, current.numOfCan + 2, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 cannibal and 1 missionary
			if( n - current.numOfCan >= 1 && n - current.numOfMis >= 1 && (((n - current.numOfMis - 1 >= n - current.numOfCan - 1) && (current.numOfMis + 1 >= current.numOfCan + 1))
					|| n - current.numOfMis - 1 == 0) ) {
				State nextState = new State( current.numOfMis + 1, current.numOfCan + 1, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
				
			// Carry 2 missionaries
			if( n - current.numOfMis >= 2 && (((n - current.numOfMis - 2 >= n - current.numOfCan) && (current.numOfMis + 2 >= current.numOfCan))
					|| n - current.numOfMis - 2 == 0) ) {
				State nextState = new State( current.numOfMis + 2, current.numOfCan, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 cannibal
			if( n - current.numOfCan >= 1 && (((n - current.numOfMis >= n - current.numOfCan - 1) && (current.numOfMis >= current.numOfCan + 1))
					|| n - current.numOfMis == 0 || current.numOfMis == 0) ) {
				State nextState = new State( current.numOfMis, current.numOfCan + 1, !current.boatSide);

				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
			
			// Carry 1 missionary
			if( n - current.numOfMis >= 1 && (((n - current.numOfMis - 1 >= n - current.numOfCan) && (current.numOfMis + 1 >= current.numOfCan))
					|| n - current.numOfMis - 1 == 0) ) {
				State nextState = new State( current.numOfMis + 1, current.numOfCan, !current.boatSide);
				
				if( !visited.isExist( nextState)) {
					visited.push( nextState);
					return nextState;
				}
			}
		}
		return null;

	}

}
