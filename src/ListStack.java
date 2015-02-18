//Yingying Wang
//CSE 332,SECTION AA
//This program called ListStack is a stack implementation
//that using a linked list, and implements DStack interface.

import java.util.EmptyStackException;

public class ListStack implements DStack {
	private ListNode frontNode;
	
	//Initialize the original empty ListStack
	public ListStack() {
		frontNode = null;
	}
	
	// Check whether the ListStack is empty or not
	// Returns true if the stack is empty, returns false otherwise
	@Override
	public boolean isEmpty(){
		 return frontNode == null;
	}
	
	// put the given value on top of the stack
	@Override
	public void push(double newValue){
		ListNode temp = new ListNode(newValue,frontNode);
		frontNode = temp;
	}
	
	//removes the top element of the stack
	//returns the value of top element
	// throws an exception if the stack is empty
	@Override
	public double pop(){
		checkEmpty();
		double result = frontNode.data;
		frontNode = frontNode.next;
		return result;
	}
	
	//take a look at top element of the stack without removing it
	//returns the value of top element
	//throws an exception if the stack is empty
	@Override
	public double peek(){
		checkEmpty();
		return frontNode.data;
	}
	
	// helper method used to throw an exception if the stack is empty
	public void checkEmpty(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
	}
   
	//Inner class creates a linked list node object that stores a double
	//value inside, and a link to next list node.
    class ListNode {
		public double data;
		public ListNode next;
		
		// Initialize the single ListNode.
		public ListNode(double data) {
			this(data, null);
		}
		
		// Initialize the ListNode attached with another ListNode.
		public ListNode(double data, ListNode next){
			this.data = data;
			this.next = next;
		}

	}

}
