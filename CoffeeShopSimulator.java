/**
 * @author Gurshaan Mand, #8037043  
 * @version April 11, 2025  
 * PURPOSE: Simulates coffee preparation by processing customer orders and managing coffee machine tasks.
 */

 import java.util.Random;

public class CoffeeShopSimulator {

	public static final int NUM_COUNTERS = 5;
	public static final int NUM_ORDERS = 150;

	public static void main(String[] args) {
		OrderQueue[] orderQueues = new OrderQueueLL[NUM_COUNTERS]; // array that holds 5 orederQueue interfaces
		int randomCounter;
		String orderDetails; // orders details stored in a string
		Random random = new Random();
		int[] counterOrderCount = new int[NUM_COUNTERS]; // int array of size 5

		// Each counter has its own queue
		for (int i = 0; i < NUM_COUNTERS; i++) {
			orderQueues[i] = new OrderQueueLL(); // for each interface we make a seprate linkedlist
		}

		for (int i = 0; i < NUM_ORDERS; i++) {
			randomCounter = random.nextInt(NUM_COUNTERS + 1);

			if (randomCounter == NUM_COUNTERS) {
				// Occasionally process orders for a random counter
				int counterToProcess = random.nextInt(NUM_COUNTERS); // gets a rnd number between 1-5

				// "orderQueues[counterToProcess]" gets random linkedlist from the orderQueue
				// array
				// sends the interface associated with the counterNumber

				processOrders(counterToProcess, orderQueues[counterToProcess], counterOrderCount);
			} else {
				// A counter receives a new order
				orderDetails = String.format("[Counter %d - Order #%d: Coffee]", randomCounter, i);

				placeOrder(randomCounter, orderDetails, orderQueues[randomCounter]);
				// TODO: place an order at the randomCounter - hint look at placeOrder and
				// counterOrderCount!

				System.out.printf("New order placed at Counter: %d\n\n", randomCounter);
			}
		}

		System.out.println("\nAll orders processed.");
	}
	/**
	 * PURPOSE: Goes through all coffee orders and processes them one by one.
	 * Handles making and serving each drink.
	 */

	public static void processOrders(int counterNum, OrderQueue queue, int[] counterOrderCount) {
		System.out.printf("Processing Orders at Counter #%d\n", counterNum);

		if (queue.isEmpty()) {
			System.out.println("No pending orders at this counter.");
			return;
		}
		
		else {

			String order = queue.dequeue();
			System.out.println("Removed: " + order);
			processOrders(counterNum, queue, counterOrderCount);

		}
		System.out.println();
		// TODO: Process i.e. dequeue and print all the orders in the queue and reset
		// processed order count
	}

	public static void placeOrder(int counterNum, String orderDetails, OrderQueue queue) {
		queue.enqueue(orderDetails);
		System.out.printf("New order placed at Counter %d: %s\n", counterNum, orderDetails);

	}
}

interface OrderQueue {
	void enqueue(String s); // Add order

	String dequeue(); // Remove and return order

	String peek(); // Check the next order without removing

	boolean isEmpty(); // Check if the queue is empty
}

class OrderQueueLL implements OrderQueue {

	private static class Node {
		String data;
		Node next;

		// TODO: Constructor!
		public Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node first, last;
	private int numElements;

	// Constructor
	public OrderQueueLL() {
		this.first = null;
		this.last = null;
		this.numElements = 0;
	}
	/**
	 * PURPOSE: Adds a new coffee order to the queue.
	 * The order will wait its turn to be processed.
	 */
	public void enqueue(String s) {
		// TODO: Implement enqueue method to add order at the end of the queue
		// add to the end
		// Since "last" i aumme hold the value for end which is null
		Node newNode = new Node(s, null); // Create a neww Node

		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else { // if there is atleast 1 Node
			last.next = newNode;
			last = newNode;
		}

		numElements++; // increase number of elements by 1 either way
	}
	/**
	 * PURPOSE: Removes the next coffee order from the queue.
	 * This is the order that will be processed next.
	 */
	public String dequeue() {
		// TODO: Implement dequeue method to remove and return the first order
		// reutnr and remove the first Node

		String firstOrder = "";
		Node curr = first;
		Node last = this.last;

		if(isEmpty()){
			System.out.println("List is Empty!");
			firstOrder = null;
		}
		else{
			try {
				firstOrder = (String) curr.data; // gets the first order
			} catch (java.lang.ClassCastException pikachu) {
				System.out.println("Invalid data type in your Node" + pikachu.getMessage());
			}

			first = first.next;
			numElements--;
		}

		if (first == null) {
            last = null;
        }


		return firstOrder;

	}

	public String peek() {
		// TODO: Implement peek method to check the next order without removing
		// look at the first order and reutnr it
		String firstOrder = "";

		if (!isEmpty()) { // if list is not empty

			// If the data value is not a string for some reason
			try {
				firstOrder = (String) first.data; // gets the first order
			} catch (java.lang.ClassCastException pikachu) {
				System.out.println("Invalid data type in your Node" + pikachu.getMessage());
			}

		} else {
			System.out.println("List is Empty!");
			firstOrder = null;
		}

		return firstOrder;

	}

	public boolean isEmpty() {
		// TODO: Implement method to check if the queue is empty
		// if the number of elemeetns is 0 meaning no elements in the list it will
		// return true, meaning empty
		return numElements == 0;
	}
}
