/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		 // Start from the first node
		 Node currentNode = first;

		 // Traverse the list to the node at the given index
		 for (int i = 0; i < index; i++) {
			 currentNode = currentNode.next; // Move to the next node
		 }
	 
		 // Return the node at the specified index
		 return currentNode;
	 }
	
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		 // Validate the index
		 if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index must be between 0 and size");
		}
	
		// Create the new node to be inserted
		Node addNode = new Node(block);
	
		// If the index is 0, insert at the beginning
		if (index == 0) {
			addNode.next = first;  // Point the new node's next to the current first node
			first = addNode;       // Make the new node the first node in the list
			{
				if (size ==0){
					last = addNode;
				}
			}
		} 
		// If the index is size, insert at the end (tail)
		else if (index == size) {
			 if(last != null){
				last.next = addNode;
			 }
			 last = addNode;
		} 

		// If the index is somwhere in the middle of the list, insert it there
		// Traverse the list to the node at the given index
		else{
		Node currentNode = first;

		for (int i = 0; i < index-1; i++) {
			
			currentNode = currentNode.next; // Move to the next node
		}
		addNode.next = currentNode.next;  // Set the node at thats index is one before 'index''s next to the new node
		currentNode.next = addNode;
	}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		// Create the new node to be inserted
		Node lastNode = new Node(block);

		Node currentNode = first;
			while (currentNode.next != null) {  // Traverse to the last node
				currentNode = currentNode.next;
			}
			currentNode.next = lastNode;  // Set the last node's next to the new node
			size++;
		} 
		
		
	
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
	
			// Create the new node to be inserted
			Node firstNode = new Node(block);
			
				firstNode.next = first;  // Point the new node's next to the current first node
				first = firstNode;       // Make the new node the first node in the list
				size++;
			}
	

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		//// create a node to check
		Node checkNode = first;

		if (index ==0){
			return checkNode.block;
		}
		else if(index == size){
			checkNode = getLast();
			return checkNode.block;
		}
		
		for (int i = 0; i < index; i++){
			checkNode = checkNode.next;
		}

		return checkNode.block;	
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// goes over the list and check if there is a memory block that is equal to block
		//// create a node to check
		Node checkNode = first;
		for(int i = 0; i< size; i++){
			
			if (checkNode.block.equals(block)){
				return i;
			}
			checkNode = checkNode.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		
		  // If the node to remove is the first node
		  if (first.block.equals(node.block)) {
			first = first.next;  // Set the first node to the next node
			size--;
			return;
		}
	
		// Traverse the list to find the node before the one we want to remove
		Node checkNode = first;
		while (checkNode != null && checkNode.next != null) {
			if (checkNode.next.block.equals(node.block)) {
				checkNode.next = checkNode.next.next;  // Skip the node to remove
				size--;
				return;
			}
			checkNode = checkNode.next;  // Move to the next node
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		 if (index==0){
			first = first.next;  // Set the first node to the next node
			size--;
			return;
		 }

		// Remove from the middle or end
		Node previousNode = first;
		for (int i = 0; i < index - 1; i++) {
			previousNode = previousNode.next;
		}
	
		// Remove the node by skipping it
		previousNode.next = previousNode.next.next;
	
		// Handle last node case
		if (index == size - 1) {
			previousNode.next = null;  // Set the new last node's next to null
		}
	
		size--;
	
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		remove(indexOf(block));
		return;
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){

		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		 ListIterator itr = this.iterator();
		 String str = "";
		 while(itr.hasNext()){
			str+= "(" + itr.current.block.baseAddress + " , " + itr.current.block.length + ") ";
			itr.next();
		 }
		 return str;
	}
}