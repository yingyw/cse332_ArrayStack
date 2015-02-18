//Yingying Wang
//CSE 332,SECTION AA
//This program called ArrayStack is a stack implementation
//that using an array, and implements DStack interface.
import java.util.EmptyStackException;

public class ArrayStack implements DStack{
	private static final int RESIZE_FACTOR = 2;
	private static final int INITIAL_SIZE = 10;
	private double[] data;
	private int index;
	// Initialize the original empty ArrayStack
	public ArrayStack() {
		data = new double[INITIAL_SIZE];
		index = -1;
	}
	
	// Check the ArrayStack is empty or not.
	// Returns true if it's empty,
	// Returns false otherwise
	@Override
	public boolean isEmpty(){
		return index<0;
	}
	
	// Put the given value to the top of the stack
	// If the stack is full, increase the size of array twice as before.
	@Override
	public void push(double newValue){
		index++;
		if(index >= data.length){
			double[] newData = new double[RESIZE_FACTOR*data.length];
			for(int i=0; i<data.length; i++){
				newData[i] = data[i];
			}
			data = newData;
		}
		data[index] = newValue;
	}
	
	// Removing the top element of stack
	// If the stack is empty, throw an exception
	// Returns the value of removed element
	@Override
	public double pop(){
        checkEmpty();
		double removed = data[index];
        index--;
		return removed;
	}
	
	// Take a look at the top element of stack without removing it
	// Returns the value of top element
	// If the stack is empty, throw an exception
	@Override
	public double peek(){
		checkEmpty();
		return data[index];
	}
	
	// Helper method used to throw an exception is the stack is empty
	public void checkEmpty(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
	}
}
