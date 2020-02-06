import java.util.Stack;

/*
 * This class includes modified stack which consists of State objects.
 * Similar to standard stack class, you can push an object, pop, peek, check if it is empty, return object at definite index, and return size.
 * Differently, isExist method is changed so that we can check properties' equality instead of object's equality.
 * Also, appropriate toString method is provided.
 * This class is used in defining both visited list and path list.
 */
public class ModifiedStack {

	protected Stack <State> stack;
	
	ModifiedStack(){
		stack = new Stack<>();
	}
	
	public void push( State cur) {
		stack.push( cur);
	}
	
	public void pop() {
		stack.pop();
	}
	
	public State peek() {
		return stack.peek();
	}
	
	public State elementAt( int index) {
		return stack.elementAt( index);
	}
	
	public int size() {
		return stack.size();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public boolean isExist( State s) {
		for( int i = 0; i < stack.size(); i++) {
			State cur = stack.elementAt( i );
			if( cur.equals(s) )
				return true;
		}
		return false;
	}
	
	public String toString() {
		String str = "[ ";
		if( !stack.isEmpty()) {
			for( int i = 0; i < stack.size() - 1; i++)
				str += stack.elementAt( i ) + ", ";
			str += stack.elementAt( stack.size() - 1);
		}
		str += " ]";
		return str;
	}
}
