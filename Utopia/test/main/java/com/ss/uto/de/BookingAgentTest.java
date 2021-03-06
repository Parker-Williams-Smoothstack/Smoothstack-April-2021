/**
 * 
 */
package com.ss.uto.de;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Parker W.
 *
 */
class BookingAgentTest {

	/**
	 * Test method for {@link com.ss.uto.de.BookingAgent#hashCode()}.
	 */
	@Test
	void testHashCode() {
		BookingAgent a1 = new BookingAgent();
		BookingAgent a2 = new BookingAgent();
		assertEquals(a1.hashCode(), a2.hashCode());
		assertNotEquals(a1.hashCode(), a2.hashCode());
		assertEquals(a1.hashCode(), a2.hashCode());
	}

	/**
	 * Test method for {@link com.ss.uto.de.BookingAgent#getBooking()}.
	 */
	@Test
	void testGetBooking() {
		BookingAgent a = new BookingAgent();
		Booking b1 = new Booking();
		Booking b2 = new Booking();
		b2.setId(5);
		assertEquals(a.getBooking(), b1);
		assertNotEquals(b2, a.getBooking());
		a.setBooking(b2);
		assertEquals(b2, a.getBooking());
		assertNotEquals(b1, a.getBooking());
	}

	/**
	 * Test method for
	 * {@link com.ss.uto.de.BookingAgent#setBooking(com.ss.uto.de.Booking)}.
	 */
	@Test
	void testSetBooking() {
		BookingAgent a = new BookingAgent();
		Booking b1 = new Booking();
		Booking b2 = new Booking();
		b2.setId(5);
		assertEquals(a.getBooking(), b1);
		assertNotEquals(b2, a.getBooking());
		a.setBooking(b2);
		assertEquals(b2, a.getBooking());
		assertNotEquals(b1, a.getBooking());
	}


	/**
	 * Test method for {@link com.ss.uto.de.BookingAgent#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		BookingAgent a1 = new BookingAgent();
		BookingAgent a2 = new BookingAgent();
		assertEquals(a1, a2);
	}

}
