//Mythili Karra
////mmkarra
////12M
////9 March 2019
public class Dictionary {
	private class Node {
		String key;
		String value;
		Node left;
		Node right;
		Node(String k, String v) {
			key = k;
			value = v;
			left = right = null;
		}
	}
	private Node root;
	private int numPairs;
	public Dictionary() {
		root = null;
		numPairs = 0;
	}

	private Node findKey(Node R, String k) {
		if(R==null || R.key.equals(k)) {
			return R;
		}
		if( R.key.compareToIgnoreCase(k) > 0)
			return findKey(R.left, k);
		else {
			return findKey(R.right, k);
		}
	}
	Node findParent(Node N, Node R) {
		Node P = null;
		if( N!=R ){
			P = R;
			while( P.left!=N && P.right!=N ){
				if(N.key.compareTo(P.key)<0)
					P = P.left;
				else {
					P = P.right;
				}
			}
		}
		return P;

	}

	Node findLeftmost(Node R){
		Node L = R;
		if( L!=null ) for( ; L.left!=null; L=L.left) ;
		return L;
	}
	void printInOrder(Node R){
		if( R!=null ){
			printInOrder(R.left);
			System.out.println( R.key + " " + R.value);
			printInOrder(R.right);
		}
	}

	void deleteAll(Node N){
		if( N!=null ){
			deleteAll(N.left);
			deleteAll(N.right);
		}
	}
	public boolean isEmpty(){
		return(numPairs==0);
	}
	public int size(){
		return(numPairs);
	}
	public String lookup(String k){
		Node N;
		N = findKey(root, k);
		if(N == null) {
			return null;
		}
		else {
			return N.value;
		}
	}
	void insert(String k, String v) throws DuplicateKeyException{
		Node N, A, B;
		if(lookup(k) == null) {
			N = new Node(k, v);
			B = null;
			A = root;
			while( A!=null ){
				B = A;
				if( k.compareTo(A.key)<0 ) A = A.left;
				else A = A.right;
			}
			if( B==null ) root = N;
			else if( k.compareTo(B.key)<0 ) B.left = N;
			else {
				B.right = N;
			}
			numPairs++;
		}
		else {
			throw new DuplicateKeyException("Cannot insert a duplicate key");  }
	}

	void delete(String k){
		Node N, P, S;
		if( lookup(k)==null ){
			throw new KeyNotFoundException(
					"Dictionary Error: cannot delete() non-existent key: " + k);
		}
		N = findKey(root, k);
		if( N.left==null && N.right==null ){  // case 1 (no children)
			if( N==root ){
				root = null;
			}else{
				P = findParent(N, root);
				if( P.right==N ) P.right = null;
				else {
					P.left = null;
				}
			}
		}else if( N.right==null ){  // case 2 (left but no right child)
			if( N==root ){
				root = N.left;
			}else{
				P = findParent(N, root);
				if( P.right==N ) P.right = N.left;
				else P.left = N.left;
			}
		}else if( N.left==null ){  // case 2 (right but no left child)
			if( N==root ){
				root = N.right;
			}else{
				P = findParent(N, root);
				if( P.right==N ) P.right = N.right;
				else P.left = N.right;
			}
		}else{                     // case3: (two children: N->left!=NULL && N->right!=NULL)
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);
			if( P.right==S ) P.right = S.right;
			else P.left = S.right;
		}
		numPairs--;
	}
	void makeEmpty(){
		deleteAll(root);
		root = null;
		numPairs = 0;
	}
	public String toString(){
		printInOrder(root);
		return "";
	}
}

