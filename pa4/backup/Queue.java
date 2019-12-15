public class Queue implements QueueInterface {
	private int numItems;
	private Node head;
	private Node tail;
	private class Node {
		Object item;
		Node next;

		Node(Object x) {
			item = x;
			next = null;
		}
	}
	public Queue() {
		tail = null;
		head = null;
		numItems = 0;
	}
	public boolean isEmpty() {
		return(numItems == 0);
	}

	public int length() {
		return numItems;
	}

	public void enqueue(Object obj) {
		Node n = new Node(obj);
		if(head == null && tail == null) {
			head = n;
			tail = n;
			numItems++;
		}
		else {
			tail.next = n;
			tail = n;
			numItems++;
		}
	}

	public Object dequeue() throws QueueEmptyException{
		if ( isEmpty() ){
			throw new QueueEmptyException(
					"Queue: dequeue() called on empty queue");
		}

		Object o = head.item;
		if(head == tail) {
			head = null;
			tail = null;
			numItems--;
		}
		else {   
			head = head.next;
			numItems--;
		}
		return o;
	}
		public Object peek() throws QueueEmptyException{
			if(isEmpty() == true) {
				throw new QueueEmptyException("cannot peek() on an empty Queue");

			}
			else {
			return head.item;
			}
		}

		public void dequeueAll() {
			numItems = 0;
			head = null;
			tail = null;
		}

		public String toString() {
			String string =  "";
			Node A = head;
			while (A != null) {
				string = string + A.item + " ";
				A = A.next;
			}	
			return string;
		}








	}
