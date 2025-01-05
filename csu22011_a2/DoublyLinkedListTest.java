package csu22011_a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2); 
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    	
    
    //test isEmpty -- right now this still DOES NOT HELP the yellow line on the if head==null && tail==null
    @Test
    public void testIsEmpty() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue("checking isEmpty on an empty list", testDLL.isEmpty());
    	testDLL.insertBefore(0,3);
    	assertFalse("checking is Empty on a list with 1 item", testDLL.isEmpty());
    	testDLL.insertBefore(1, 2);
    	assertFalse("checking is empty on a list with mult items", testDLL.isEmpty());
    	
    	assertEquals("head", Integer.valueOf(3),testDLL.get(0));
    	assertEquals("tail",Integer.valueOf(2), testDLL.get(1));
    }
    
    
    //test get method
    @Test
    public void testGet() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	assertNull(testDLL.get(100000));
    	
    	//asserting null with empty array
    	assertNull(testDLL.get(0));
    	assertNull(testDLL.get(-10));
    	//adding, but still asserting null
    	testDLL.insertBefore(0, 1);
    	assertNull(testDLL.get(-1));
    	assertNull(testDLL.get(10));
    	
    	//testing head
    	assertEquals("testing get on a list - expecting to return 1", Integer.valueOf(1) ,testDLL.get(0));
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(0, 3);
    	
    	testDLL.insertBefore(5, 5);
    	//testing tail
    	assertEquals("testing get on tail of a list - expecting to return 5", Integer.valueOf(5), testDLL.get(3));
    	//testing middle (origionally was declared first)
    	assertEquals("testing get on a list - expecting to return 2", Integer.valueOf(1) ,testDLL.get(1));
    	
    	//double test the equality statements that are for the if statements
    	
    	
    	//still need a testCase for the last return null which is currently unreachable
    }
    	

    @Test
    public void testDeleteAt() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertFalse("checking deleteAt() on an empty list", testDLL.deleteAt(2));
    	assertFalse("delete at on 0", testDLL.deleteAt(0));
    	
    	testDLL.insertBefore(0, 3);
    	testDLL.insertBefore(1, 4);
    	testDLL.insertBefore(-1, 2);
    	testDLL.insertBefore(10, 5); // {2,3,4.5}
    	
    	testDLL.deleteAt(2);
    	assertEquals("checking the new list", "2,3,5", testDLL.toString());
    	
    	assertFalse("checking with negative number", testDLL.deleteAt(-5));
    	assertFalse("trying to get something un red",testDLL.deleteAt(4));
    }
    
    
    
    @Test
    public void testReverse() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 3);
    	testDLL.insertBefore(1, 4);
    	testDLL.insertBefore(-1, 2);
    	testDLL.insertBefore(10, 5); // {2,3,4.5}
    	testDLL.reverse();
    	assertEquals("testing reverse", "5,4,3,2", testDLL.toString());
    }
    
    @Test
    public void testMakeUnique() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.makeUnique();
    	
    	testDLL.insertBefore(0, 3);
    	testDLL.insertBefore(1, 4);
    	testDLL.insertBefore(-1, 2);
    	testDLL.insertBefore(3, 3);
    	//DLL data in linear order is {2,3,4,3}
    	
    	testDLL.makeUnique();
    	assertEquals("checking makeUnique() on a list with extra point as tail", "2,3,4", testDLL.toString());
    }
    
    @Test
    public void testGetLength() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertEquals(0,testDLL.getLength());
    	testDLL.insertBefore(0, 3);
    	testDLL.insertBefore(1, 4);
    	testDLL.insertBefore(-1, 2);
    	int testLength = testDLL.getLength();
    	assertEquals("testing getLength", 3, testLength);
    }
    
    
    //tests added to comply with submitty unit testing
    @Test
    public void testDeleteAtAllEelements() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(0, 2);
    	assertTrue(testDLL.deleteAt(0));
    	assertTrue(testDLL.deleteAt(0));
    }
    
    @Test
    public void testUniqueWithMultElementsOfSameNumber() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 1);
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 1);
    	testDLL.insertBefore(10, 2);
    	testDLL.insertBefore(10, 2);
    	testDLL.insertBefore(12, 2);
    	testDLL.insertBefore(13, 2);
    	
    	testDLL.makeUnique();
    	assertEquals("make unique on multiple elements", "1,2", testDLL.toString() );
    }
    
    @Test
    public void testUniqueThenGet() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 1);
    	testDLL.insertBefore(3, 2);
    	testDLL.insertBefore(4, 1);
    	testDLL.insertBefore(5, 1);
    	testDLL.insertBefore(6, 2);
    	testDLL.insertBefore(7, 1);
    	
    	
    	testDLL.makeUnique();
    	assertEquals("testing get after a list is made unique", Integer.valueOf(1) ,testDLL.get(1));
    }
    
    @Test
    public void testIsHeadNull() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue(testDLL.isHeadNull());
    	assertTrue(testDLL.isTailNull());
    	testDLL.insertBefore(1, 2);
    	assertFalse(testDLL.isHeadNull());
    	assertFalse(testDLL.isTailNull());
    }
    
    @Test
    public void testIsTailNull() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue(testDLL.isTailNull());
    	assertTrue(testDLL.isHeadNull());
    	testDLL.insertBefore(1, 2);
    	assertFalse(testDLL.isTailNull());
    	assertFalse(testDLL.isHeadNull());
    }
    
}

