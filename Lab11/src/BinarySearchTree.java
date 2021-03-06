/*
 * Binary search tree stores values indexed by keys. Keys must be Comparable and
 * are organized based on their natural ordering (i.e. the ordering given by
 * their compareTo). Values can be of any object type. This tree implementation
 * is not balanced, i.e. it may behave as a linked list in the worst case. Keys
 * may not be repeated, but values can.
 */

public class BinarySearchTree<K extends Comparable<K>, V> {
	private BSTNode root = null;

	/*
	 * true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}


	/*
	 * Adds a given value indexed with a given key to the tree according to the
	 * binary search structure.  If the key is already present, this method throws
	 * a DuplicateKeyException.
	 */
	public void add(K key, V value) {
		if (root == null){
			root = new BSTNode(key, value);
		}
		BSTNode current = root;
		boolean done = false;
		while(!done){
			if(key.compareTo(current.key) < 0){
				if(current.leftChild == null){
					current.leftChild = new BSTNode(key, value);
					done = true;
				}else if(key.compareTo(current.key) == 0){
					throw new DuplicateKeyException(key);
				}else{
					current = current.leftChild;
				}
			}
			else{
				if(current.rightChild == null){
					current.rightChild = new BSTNode(key, value);
					done = true;
				}else if(key.compareTo(current.key) == 0){
					throw new DuplicateKeyException(key);
				}else{
					current = current.rightChild;
				}
			}
		}
	}

	/*
	 * Returns the value associated with the given key in this binary search tree.
	 * If there is no element associated with this key, null is returned.
	 */
	public V get(K key) {
		if (root == null){
			return null;
		}
		BSTNode current = root;
		while(current != null && current.key.compareTo(key) != 0){
			if(key.compareTo(current.key) < 0){
				if(current.leftChild == null){
					return null;
				} else {
					current = current.leftChild;
				}
			}else{
				if(current.rightChild == null){
					return null;
				}else{
					current = current.rightChild;
				}
			}
		}if(current == null){
			return null;		
		}else{	
			return current.value;
		}
	}



	/*
	 * Removes an element with the given key. The resulting tree is a binary
	 * search tree. If there is no such key, the tree is unchanged. Returns
	 * the value associated with this key or null if there is no such value. 
	 */
	public V remove(K key) {
		if (root == null){
			return null;
		}
		BSTNode current = root;
		boolean done = false;
		while(current != null && current.key.compareTo(key) != 0 && done != true){
			System.out.println(key + " " + current.key);
			if(key.compareTo(current.key) < 0){
				if(current.leftChild == null){
					return null;
				} else if(current.leftChild.key.compareTo(key)==0){
					done = true;
				}
				else {
					current = current.leftChild;
				}
			}else{
				if(current.rightChild == null){
					return null;
				}else if(current.rightChild.key.compareTo(key)==0){
					done = true;
				}else{
					current = current.rightChild;
				}
			}
		}if(current == null){
			return null;		
		}else{	
			System.out.println("test1");
			//leaf case left
			if(current.leftChild != null && current.leftChild.key.compareTo(key) == 0 && current.leftChild.leftChild == null && current.leftChild.rightChild == null){
				System.out.println("test2");
				V toReturn = current.leftChild.value;
				current.leftChild = null;
				return toReturn;
			}
			//leaf case right
			if(current.rightChild != null && current.rightChild.key.compareTo(key) == 0 && current.rightChild.leftChild == null && current.rightChild.rightChild == null){
				System.out.println("test3");
				V toReturn = current.rightChild.value;
				current.leftChild = null;
				return toReturn;
			}
			//has right child but no left child
			else if(current.leftChild != null && current.leftChild.key.compareTo(key) == 0 && current.leftChild.leftChild == null && current.leftChild.rightChild != null){
				System.out.println("test4");
				V toReturn = current.leftChild.value;
				current.leftChild = current.leftChild.rightChild;
				return toReturn;
			}
			//has left child but no right child
			else if(current.rightChild != null && current.rightChild.key.compareTo(key) == 0 && current.rightChild.rightChild == null && current.rightChild.leftChild != null){
				System.out.println("test5");
				V toReturn = current.rightChild.value;
				current.rightChild = current.rightChild.leftChild;
				return toReturn;
			}
			//right child has left and right child
			else if(current.rightChild.rightChild != null && current.rightChild.leftChild != null){
				System.out.println("test6");
				V toReturnV = current.rightChild.value;
				current.rightChild = current.rightChild.leftChild;
				return toReturnV;
			}
			else if(current.leftChild.leftChild != null && current.leftChild.rightChild != null){
				System.out.println("test7");
				V toReturnV = current.leftChild.value;
				current.leftChild = current.leftChild.rightChild;
				return toReturnV;
			}
			else if(current.rightChild != null && current.rightChild.key.compareTo(key) == 0 && current.rightChild.rightChild != null){
				System.out.println("test10");
				V toReturn = current.rightChild.value;
				current.rightChild = current.rightChild.rightChild;
				return toReturn;
			}
			else if(current.leftChild != null && current.leftChild.key.compareTo(key) == 0 && current.leftChild.leftChild != null){
				System.out.println("test11");
				V toReturn = current.leftChild.value;
				current.leftChild = current.leftChild.leftChild;
				return toReturn;
			}
		}
		System.out.println("test8");
		return null;
	}

	/*
	 * Clears all elements from the tree.
	 */
	public void clear() {
		root = null;
	}

	private class BSTNode {
		public K key;
		public V value;
		public BSTNode leftChild = null;
		public BSTNode rightChild = null;

		// null key will generate a null pointer exception when 
		// a method (such as compareTo) is called on it. 
		// This is matches the Java Collections Framework specification.
		public BSTNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}
}