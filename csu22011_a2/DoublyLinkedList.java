package csu22011_a2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 9/10/23
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     */
    public boolean isEmpty()
    {
    	//TODO
      if(head == null && tail == null) {
    	  return true;
      }
      return false;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     */
    public void insertBefore( int pos, T data ) 
    {
      //TODO 
    	if (isEmpty()) { // if there are no items in list
    		DLLNode newNode = new DLLNode(data, null, null); // is head and tail bc its only one in list
    		head = newNode;
    		tail = newNode; 
    	}else if(pos<=0) { //adding to head bc pos is less than or equal to head
    		DLLNode tempNode = head;
    		DLLNode newNode = new DLLNode(data, null, tempNode);
    		head = newNode;
    		tempNode.prev = newNode;
    	} else if (pos>0){ // all cases where pos is greater than 0 
    		DLLNode tempNode = head;
    		if(tempNode.next == null) { //1 node list
    			DLLNode newNode = new DLLNode (data, head, null);
    			tail = newNode;
    			tempNode.next = tail;		
    		} else if (tempNode.next != null) { // more than 1 node list 
    			boolean go = true;
    			int i = 0;
    			while(go) {
    				if(i<pos && tempNode.next == null) { //adding on to end bc pos is greater than amt in list
    					DLLNode newNode = new DLLNode (data, tempNode, null);
    					tail = newNode;
    					tempNode.next = newNode;
    					go = false;
    					
    				} else if (i == pos) {
    					DLLNode newNode = new DLLNode (data, tempNode.prev, tempNode); //data, prev pointer, next pointer				
    					tempNode.prev.next = newNode;
    					tempNode.prev = newNode;
    					go = false;
    				}
    				i ++;
    				tempNode = tempNode.next;
    			}
    		}
    	}
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     *
     */
    public T get(int pos) 
    {
      //TODO
    	DLLNode tempNode = head;
    	if (isEmpty()) {       //list is empty
    		return null;
    	} 
    	else if (pos >= 0) {
    		boolean go = true;
    		int i = 0;  
    		while(go) {
    			if (i < pos && tempNode.next == null) { //position is invalid - too high
    				go = false;
    				return null;  				
    			} else if (i == pos) {
    				go = false;
    				return tempNode.data;
    			}
    			i++;
    			tempNode = tempNode.next;
    		}
    	 } 
    	return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     */
    public boolean deleteAt(int pos) 
    {
      //TODO
    	DLLNode tempNode = head;
    	if  (isEmpty()) {
    		return false;
    	} else if (pos > 0 && !isEmpty()) {
    		boolean go = true;
    		int i = 0;
    		while(go) {
    			if (i == pos && tempNode.next == null) {
    				//real work
    				tail = tempNode.prev;
    				tempNode.prev.next = null;
    				go = false;
    				return true;
    			} else if (i==pos) {
    				tempNode.next.prev = tempNode.prev;
    				tempNode.prev.next = tempNode.next;
    				go = false;
    				return true;
    			} else if (i < pos && tempNode.next == null) {
    				go = false;
    				//return false;
    			} 
    			i++;
    			tempNode = tempNode.next;
    		}
    	} else if (pos == 0) {
    		if(tempNode.next !=null) {
    			head = tempNode.next;
        		tempNode.next.prev = null; //this can't work if deleting last element
        		return true;
    		} else if (tempNode.next == null) {
    			head = null;
    			tail = null;
    			return true;
    		}
    		
    	} else if (pos < 0) {
    		return false;
    	}
      return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     */
    public void reverse()
    {
      //TODO
    	if(!isEmpty()) {
    		DLLNode tempNode = head;
    		int length;			
    		for (length = 0; tempNode.next != null; length ++) {
    			tempNode = tempNode.next;
    		}
    		tempNode = tempNode.prev; //back up one to start at c not d
    		for(int k = 0; k<length; k++) { //inserting at end
    			insertBefore(length*2, tempNode.data);
    			tempNode = tempNode.prev;
    		}
    		//need to redefine head here
    		tempNode = head;
    		for(int i = 0; i<length; i++) { //starting at head of what i want to delete; going to head of new list
    			tempNode = tempNode.next;
    			deleteAt(0);
    			
    		}
    	}
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     */
    
    public void makeUnique()
   {
     //TODO
   	 if(!isEmpty()) {
   		 DLLNode tempNode = head;
   		 int lengthIndex;
   		 for (lengthIndex = 0; tempNode.next != null; lengthIndex ++) {
   			 tempNode = tempNode.next;
   		 }
   		 tempNode = head;
   		 
   		 for (int i = 0; i<=lengthIndex; i++) {
   			 DLLNode secondTemp = head;
   			 for(int j = 0; j <= lengthIndex; j++ ) {
   				 if(i!=j && tempNode.data == secondTemp.data) {
   					 deleteAt(j);
   					 lengthIndex = lengthIndex -1;
   				 } else {
   					 secondTemp = secondTemp.next;
   				 }

   			 }
   			 tempNode = tempNode.next;
   		 }
   	 }	else {
   		 return;
   	 }
   }
    
    
    
    
    
	 //option 1
	 // cycle through the list again
	 // once delete at when .data equals but pos1 != pos2
	 //		if.next!= null; then cancel while
	 //		reset tempNode2
	 // 	loop back up, restart while
	 
	 
	 // option 2
	// cycle through the list again counting how many repeats
	 // if 1 repeat, do nothing
	 //if 2 repeat, loop through and delete
	 //if 3+ - detail
	 
//	 
//     public void makeUnique(){
//    	 if (isEmpty()) {
//    		 return;
//    	 } else if (!isEmpty()) {
//    		 int length = getLength();
//    		 DLLNode tempNode1 = head;
//    		 for(int pos1 = 0; pos1<length; pos1++) {
//    			 DLLNode tempNode2 = head;
//    			 boolean go = true;
//    			 int pos2 = 0;
//    			 int repeatCount = 0;
//    			 while(go) { //seeing how many repeats there are
//    				 if (tempNode1.data == tempNode2.data && pos1 != pos2) {
//    					 repeatCount++;
//    					 pos2++;
//    				 } else {
//    					 pos2++;
//    				 }
//    				 if (tempNode2.next ==null){
//    					 go = false;
//    				 } else {
//    					 tempNode2 = tempNode2.next;
//    				 }
//    			 }
//    			 tempNode2 = head;
//    			 if (repeatCount == 1) { //if only one delete and move on
//    				 for (int i = 0; i<length; i++) {
//    					 if(pos1 !=i && tempNode1.data == tempNode2.data) {
//    						 deleteAt(i);
//    						 length = length -1;
//    					 }
//    					 tempNode2 = tempNode2.next;
//    				 }
//    			 } else if (repeatCount>1) { //if more than 1
//    				 for (int i = 0; i<length; i++) {
//    					 tempNode2 = head;
//    					 for (int j = 0; j<i; j++) {
//    						 tempNode2 = tempNode2.next;
//    					 }
//    					 boolean go2 = true;
//    					 if (pos1 != i) {
//    						 while (go2) {
//        						 if(tempNode1.data == tempNode2.data) {
//            						 deleteAt(i);
//            						 length = length -1;
//            						 i = i-2;
//            						 go2 = false;
//            					 } 
//        						 if (tempNode2.next == null) {
//        	    					 go2 = false;
//        	    				 } else {
//        	    					 tempNode2 = tempNode2.next;
//        	    					 i++;
//        	    				 }
//        					 }
//    					 }
//    					 
//    				 }
//    			 } 
//    			 tempNode1 = tempNode1.next;
//    		 }
//    	 }
//    }
     
     public int getLength() {
    	 DLLNode tempNode = head;
    	 int length;
    	 if (isEmpty()) {
    		 return 0;
    	 }
    	 for (length = 1; tempNode.next != null; length ++) {
 			tempNode = tempNode.next;
 		}
    	 return length;
     }
     
     public boolean isHeadNull() {
    	 if (head == null) {
    		 return true;
    	 } else {
    		 return false;
    	 }
     }
     public boolean isTailNull() {
    	 if (tail == null) {
    		 return true;
    	 } else {
    		 return false;
    	 }
     }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
     *
     *  The lines before the for loop involve 
     *  - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
     *  - the allocation and assignment of two variables which are constant time operations.
     *  Thus the code before the for-loop will take Theta(1) time to run.
     *
     *  The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
     *
     *  The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
     *  Each iteration will involve:
     *   * The if-conditional will run:
     *       - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
     *       - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
     *       - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
     *     Therefore the if-then-else conditions will cost Theta(1) in the worst case.
     *   * The final call to StringBuilder's append will cost again Theta(1)
     *   * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list). 
     *     Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
     *  Therefore each iteration of the loop will cost Theta(1).
     *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
     *
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
     *
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



