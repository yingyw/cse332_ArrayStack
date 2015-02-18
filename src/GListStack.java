//Yingying Wang
//CSE 332,SECTION AA
//This class called GListStack is a stack implementation
//that using a linked list, and implements GStack interface.
//The code is similar to ListStack, but it's generic.

import java.util.EmptyStackException;

public class GListStack<T> implements GStack<T> {
	private ListNode frontNode;

	//Initialize the original empty ListStack
	public GListStack() {
		frontNode = null;
	}

	// Check whether the ListStack is empty or not
	// Returns true if the stack is empty, returns false otherwise
	@Override
	public boolean isEmpty() {
		 return frontNode == null;
	}

	// put the given element on top of the stack
	@Override
	public void push(T newElement) {
		ListNode temp = new ListNode(newElement,frontNode);
		frontNode = temp;
	}

	//removes the top element of the stack
	//returns the top element
	// throws an exception if the stack is empty
	@Override
	public T pop() {
		checkEmpty();
		T result = frontNode.data;
		frontNode = frontNode.next;
		return result;
	}

	//take a look at top element of the stack without removing it
	//returns the top element
	//throws an exception if the stack is empty
	@Override
	public T peek() {
		checkEmpty();
		return frontNode.data;
	}
	
	// helper method used to throw an exception if the stack is empty
	public void checkEmpty(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
	}
	
	//Inner class creates a linked list node object that stores a 
	//element inside, and a link to next list node.
	class ListNode {
		public T data;
		public ListNode next;
		
		// Initialize the single ListNode.
		public ListNode(T data) {
			this(data, null);
		}
		
		// Initialize the ListNode attached with another ListNode.
		public ListNode(T data, ListNode next){
			this.data = data;
			this.next = next;
		}

	}

}
