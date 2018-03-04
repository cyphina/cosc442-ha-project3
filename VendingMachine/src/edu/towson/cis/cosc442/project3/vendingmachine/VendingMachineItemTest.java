/**
 * 
 */
package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cyphina
 *
 */
public class VendingMachineItemTest {

	/**
	 * @throws java.lang.Exception
	 * I liked naming every test item seperately so I didn't use setup to create the same kinds of vending machine items
	 * to test. 
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the vending machine constructor
	 */
	@Test
	public void testVendingMachineItem() {
		VendingMachineItem bananaCandy = new VendingMachineItem("BananaCandy",7.0);
		assertEquals(bananaCandy.getPrice(), 7.0, 0.001);
		assertEquals(bananaCandy.getName(), "BananaCandy");	
	}
	
	/**
	 * Test the vending machine constructor exception handling properly triggers
	 */
	@Test
	public void testVendingMachineItemException() {
		try {
			VendingMachineItem mangoCandy = new VendingMachineItem("MangoCandy",-7.0);
			fail();
		}
		catch(VendingMachineException e)
		{
			assertEquals(e.getMessage(),"Price cannot be less than zero");
		}
	}
	
	/**
	 * Ensures that the VendingMachineItem correctly gets its name from GetName() accessor
	 */
	@Test
	public void testGetName() {
		VendingMachineItem coconutCandy = new VendingMachineItem("CoconutCandy",5.0);
		assertEquals(coconutCandy.getName(), "CoconutCandy");	
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem#getPrice()}.
	 */
	@Test
	public void testGetPrice() {
		VendingMachineItem pineapplyCandy = new VendingMachineItem("pineapplyCandy", 3.0);
		assertEquals(pineapplyCandy.getPrice(), 3.0,0.001);
	}

}
