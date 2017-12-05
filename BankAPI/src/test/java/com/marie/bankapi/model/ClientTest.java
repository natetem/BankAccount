package com.marie.bankapi.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Marie Jeanne NATETE
 */
public class ClientTest {

    private Client marie;

    @Before
    public void setUp() throws Exception {
        marie = new Client(1L, "Marie", "NATETE");
    }

    /**
     * Test of getId one method, of class Client.
     */
    @Test
    public void getIdOne() {
        System.out.println("getIdOne");
        Long expResult = 1L;
        Long result = marie.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of the wrong Id method, of class Client.
     */
    @Test
    public void getWrongId() {
        System.out.println("getWrongId");
        Long expResult = 2L;
        Long result = marie.getId();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getFirstName is Marie method, of class Client.
     */
    @Test
    public void getFirstNameMarie() {
        System.out.println("getFirstNameMarie");
        String expResult = "Marie";
        String result = marie.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of the wrong FirstName.
     */
    @Test
    public void getWrongFirstName() {
        System.out.println("getWrongFirstName");
        String expResult = "Chantal";
        String result = marie.getFirstName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getLastName method which return NATETE.
     */
    @Test
    public void getLastNameNATETE() {
        System.out.println("getLastName");
        String expResult = "NATETE";
        String result = marie.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * test with the wrong lastName.
     */
    @Test
    public void getWrongLastNameNATETE() {
        System.out.println("getLastName");
        String expResult = "MACRON";
        String result = marie.getLastName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Client.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "Jeanne";
        marie.setFirstName(firstName);
        String result = marie.getFirstName();
        assertEquals(firstName, result);

    }

    /**
     * Test of setLastName method, of class Client.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "DUBOIS";
        marie.setLastName(lastName);
        String result = marie.getLastName();
        assertEquals(lastName, result);
    }

    /**
     * Test of hashCode method, of class Client.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 306806287;
        int result = marie.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Client(1L, "Marie", "NATETE");
        boolean expResult = true;
        boolean result = marie.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Client{id=1, firstName=Marie, lastName=NATETE}";
        String result = marie.toString();
        assertEquals(expResult, result);

    }

}
