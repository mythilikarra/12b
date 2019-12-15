//#Mythili Karra
////#mmkarra
////#12B
////#2/8/2019
//This is the Dictionary class 
public class Dictionary implements DictionaryInterface{
	private class Node {
		String key;
		String val;
		Node next;
		Node(String x, String y) {
			key = x;
			val = y;
			next = null;
		}
	}
	private Node head;     // reference to first Node in List
	private int numItems;  // number of items in this IntegerList

	public Dictionary(){
		head = null;
		numItems = 0;
	}
	public boolean isEmpty(){
		return(numItems == 0);
	}
	public int size() {
		return numItems;
	}
	public String lookup(String key) {
		Node A = head;
		if(A != null) {
			if(numItems == 1) {
				if(A.key.equals(key)) {
					return A.val;
				}
			}
			else {
				for(int i=0; i<numItems; i++){
					// A = A.next;
					if(A.key.equals(key)) {
						return A.val;
					}
					else { //here
						A = A.next;//here
					}//here
				}
			}
		}
		return null;
	}
	public void insert(String key, String value) throws DuplicateKeyException{
		if(lookup(key) == null) {
			if(head == null) {
				Node N = new Node(key,value);
				head = N;
				numItems++;
			}
			else {
				Node N = head;
				for(int i = 1; i < numItems; i++) {
					if(N.next == null) {
						break;
					}
					else {
						N = N.next;
					}
				}
				Node x = new Node(key, value);
				N.next = x;
				numItems++;
			}
		}
		else {
			throw new DuplicateKeyException("Cannot insert duplicate keys");                }
	}

	public void delete(String key) throws KeyNotFoundException{
		if (lookup(key) != null) {
			Node N = head;
			if(numItems == 1) {
				if(N.key.equals(key)) {
					head = head.next;
					numItems--;
				}	
			}
			else {
				if(N.key.equals(key)) {
					head = N.next;
					numItems--;
				}
				else {
					head = N;
					for(int i=0; i < numItems; i++){
						if(N.next.key.equals(key)) {
							N.next = N.next.next;
							numItems--;
							break;
						}
						else {
							N = N.next;
						}
					}	

				}		

			}
		}
		else {
			throw new KeyNotFoundException("cannot delete non-existent key");
		}		

	}

	public void makeEmpty() {
		head = null;
		numItems = 0;
	}
	private String myString(Node H){
		if( H==null ){
			return "";
		}else{
			return (H.key + " " + H.val + " " + myString(H.next));
		}
	}

	public String toString(){
		return myString(head);
	}

}
