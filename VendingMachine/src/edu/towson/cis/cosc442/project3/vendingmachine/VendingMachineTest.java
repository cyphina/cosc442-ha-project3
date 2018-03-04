package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine vMachine;
	
	@Before
	public void setUp() throws Exception {
		vMachine = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*Test the vendingmachine's add functionality*/
	@Test
	public void testAddItem() {
		VendingMachineItem vItem = new VendingMachineItem("RaspberryCandy", 2.0f);
		vMachine.addItem(vItem,VendingMachine.D_CODE);
		assertEquals(vMachine.getItem(VendingMachine.D_CODE), vItem);
	}

	/*Test adding the item to a bad slot*/
	@Test
	public void testAddItemBadSlot() {
		VendingMachineItem vItem = new VendingMachineItem("LuckyCandy", 2.0f);
		try{
			vMachine.addItem(vItem,"POPCORNFLUFFLES");
			fail("Exception not caught for adding item to a wrong slot");
		}
		catch(VendingMachineException e)
		{
			assertEquals(e.getMessage(),"Invalid code for vending machine item");
		}
	}
	
	/*Test to make sure we cannot add to a slot that already has an item added */
	@Test
	public void testAddItemFullSlot() {
		VendingMachineItem vItem = new VendingMachineItem("LuckyCandy", 2.0f);
		try{
			vMachine.addItem(vItem,VendingMachine.A_CODE);
			vMachine.addItem(vItem,VendingMachine.A_CODE);
			fail("Exception not caught for adding item to a full slot");
		}
		catch(VendingMachineException e)
		{
			assertEquals(e.getMessage(), "Slot " + VendingMachine.A_CODE + " already occupied");
		}
	}
	/*Test to ensure item removal from the vending machine works*/
	@Test
	public void testRemoveItem() {
		VendingMachineItem vItem = new VendingMachineItem("PopcornChicken", 5.0f);
		vMachine.addItem(vItem, VendingMachine.B_CODE);
		assertEquals(vMachine.removeItem(VendingMachine.B_CODE), vItem);
		assertEquals(vMachine.getItem(VendingMachine.B_CODE),null);
	}

	/*Test to ensure if we remove an item that's not even there, we throw an exception*/
	@Test
	public void testRemoveItemBad() {
		try{
			vMachine.removeItem(VendingMachine.B_CODE);
			fail("Exception not caught when trying to remove bad item");
		}
		catch(VendingMachineException e) {
			assertEquals(e.getMessage(), "Slot " + VendingMachine.B_CODE + " is empty -- cannot remove item");
		}
	}
	
	/*Test to add money to current purchase*/
	@Test
	public void testInsertMoney() {
		vMachine.insertMoney(20.5);
		assertEquals(vMachine.getBalance(), 20.5, 0.0001);
	}

	/*Test to check if exception is thrown when adding bad amounts of money to current purchase*/
	@Test
	public void testInsertMoneyBad() {
		try {
			vMachine.insertMoney(-10);
			fail("Attempted to assert negative money to balance");
		}
		catch(VendingMachineException e) 
		{
			assertEquals(e.getMessage(), "Invalid amount.  Amount must be >= 0");
		}
	}
	
	/*Test to make sure we can get the current amount of money we've inserted into the machine*/
	@Test
	public void testGetBalance() {
		vMachine.insertMoney(10.5);
		assertEquals(vMachine.balance, 10.5, 0.0001);
	}
	
	/*Test to ensure we can purchase an item that exists at some slot if the preconditions are met*/
	@Test
	public void testMakeSucessfulPurchase() {
		VendingMachineItem vItem = new VendingMachineItem("CottonCandy", 15.0f);
		vMachine.addItem(vItem, VendingMachine.A_CODE);
		vMachine.insertMoney(15.0);
		assertTrue(vMachine.makePurchase(VendingMachine.A_CODE));	
		assertEquals(vMachine.getItem(VendingMachine.A_CODE), null);
	}
	
	/*Test to ensure we can't purchase an item that's there if we don't have any money*/
	@Test
	public void testNotEnoughMoneyPurchase() {
		VendingMachineItem vItem = new VendingMachineItem("CottonCandy", 15.0f);
		vMachine.addItem(vItem, VendingMachine.C_CODE);
		assertFalse(vMachine.makePurchase(VendingMachine.C_CODE));	
		assertEquals(vMachine.getItem(VendingMachine.C_CODE), vItem);
	}

	/*Test to ensure we can't purchase an item from an empty slot.*/
	@Test
	public void testEmptySlotPurchase() {
		assertEquals(vMachine.getItem(VendingMachine.A_CODE), null);
		assertFalse(vMachine.makePurchase(VendingMachine.A_CODE));	
	}
	
	/*Test to make sure we return all the change (balance becomes 0)*/
	@Test
	public void testReturnChange() {
		vMachine.insertMoney(10);
		vMachine.insertMoney(12.5);
		double oldBalance = vMachine.getBalance();
		assertEquals(vMachine.returnChange(),oldBalance,0.001);
		assertEquals(vMachine.getBalance(), 0, 0.001);
	}

}
