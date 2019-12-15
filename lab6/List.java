@SuppressWarnings("overrides")
public class List <T> implements ListInterface <T>  {
	private class Node {
		T item;
		Node next;

		Node(T x){
			item = x;
			next = null;
		}
	}
	private Node head;     // reference to first Node in List
	private int numItems;
	public List(){
		head = null;
		numItems = 0;
	}
	private Node find(int index){
		Node N = head;
		for(int i=1; i<index; i++){
			N = N.next;
		}
		return N;
	}
	public boolean isEmpty(){
		return(numItems == 0);
	}
	public int size() {
		return numItems;
	}
	public T get(int index) throws ListIndexOutOfBoundsException {

		if( index<1 || index>numItems ){
			throw new ListIndexOutOfBoundsException(
					"ListIndexOutOfBoundsException: get(): invalid index: " + index);
		}
		Node N = find(index);
		return N.item;
	}
	public void add(int index, T newItem) 
		throws ListIndexOutOfBoundsException{

			if( index<1 || index>(numItems+1) ){
				throw new ListIndexOutOfBoundsException(
						"ListIndexOutOfBoundsException: add(): invalid index: " + index);
			}
			if(index==1){
				Node N = new Node(newItem);
				N.next = head;
				head = N;
			}else{
				Node P = find(index-1); // at this point index >= 2
				Node C = P.next;
				P.next = new Node(newItem);
				P = P.next;
				P.next = C;
			}
			numItems++;
		}
	public void remove(int index)
		throws ListIndexOutOfBoundsException{
			if( index<1 || index>numItems ){
				throw new ListIndexOutOfBoundsException(
						"ListIndexOutOfBoundsException: remove(): invalid index: " + index);
			}
			if(index==1){
				Node N = head;
				head = head.next;
				N.next = null;
			}else{
				Node P = find(index-1);
				Node N = P.next;
				P.next = N.next;
				N.next = null;
			}
			numItems--;
		}
	public void removeAll(){
		head = null;
		numItems = 0;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node N = head;

		for( ; N!=null; N=N.next){
			sb.append(N.item).append(" ");
		}
		return new String(sb);
	}
	@SuppressWarnings("unchecked")
	public boolean equals(Object rhs){
		boolean eq = false;
		List<T> R = null;
		Node N = null;
		Node M = null;

		if(rhs instanceof List){
			R = (List)rhs;
			eq = ( this.numItems == R.numItems );

			N = this.head;
			M = R.head;
			while(eq && N!=null){
				eq = (N.item == M.item);
				N = N.next;
				M = M.next;
			}
		}
		return eq;
	}
}
