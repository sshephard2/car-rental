package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RegistrationNumberTest class.  JUnit test cases for the RegistrationNumber class.
 *
 * @author Stephen Shephard
 * @version 1.0
 */
class RegistrationNumberTest {

    /**
     * Test method for RegistrationNumber Factory method.
     * Valid parameters.
     */
    @Test
    void testGetInstanceValid() {
        RegistrationNumber rn = RegistrationNumber.getInstance("AB01", "LCN");

        // An object should have been created
        assertNotNull(rn);
    }

    /**
     * Test method for RegistrationNumber Factory method.
     * Missing first parameter.
     */
    @Test
    void testGetInstanceMissingFirst() {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.getInstance(null, "NCL"));
    }

    /**
     * Test method for RegistrationNumber Factory method.
     * Missing second parameter.
     */
    @Test
    void testGetInstanceMissingSecond() {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.getInstance("AB01", null));
    }

    /**
     * Test method for RegistrationNumber Factory method.
     * Invalid first parameter.
     */
    @Test
    void testGetInstanceInvalidFirst() {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.getInstance("AB0", "NCL"));
    }

    /**
     * Test method for RegistrationNumber Factory method.
     * Invalid second parameter.
     */
    @Test
    void testGetInstanceInvalidSecond() {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.getInstance("AB01", "NC"));
    }

    /**
     * Test method for RegistrationNumber Factory method.
     * Can't create duplicates.
     */
    @Test
    void testGetInstanceDuplication() {
        RegistrationNumber.getInstance("AB02", "BRB");
        assertThrows(IllegalStateException.class, () -> RegistrationNumber.getInstance("AB02", "BRB"));
    }

    /**
     * Test method for RegistrationNumber.getFirstPart method.
     */
    @Test
    void testGetFirstPart() {
        RegistrationNumber rn = RegistrationNumber.getInstance("AB03", "CLO");
        assertEquals("AB03", rn.getFirstPart());
    }

    /**
     * Test method for RegistrationNumber.getSecondPart method.
     */
    @Test
    void testGetSecondPart() {
        RegistrationNumber rn = RegistrationNumber.getInstance("AB04", "CAR");
        assertEquals("CAR", rn.getSecondPart());
    }

    /**
     * Test method for RegistrationNumber.toString method.
     */
    @Test
    void testToString() {
        RegistrationNumber rn = RegistrationNumber.getInstance("AB05", "XYZ");
        assertEquals("AB05 XYZ", rn.toString());
    }

    /**
     * Test method for valid RegistrationNumber.valueOf method.
     */
    @Test
    void testRegistrationNumberValueOf() {
        RegistrationNumber rn = RegistrationNumber.valueOf("AB06 BBC");
        // An object should have been created
        assertNotNull(rn);
    }

    /**
     * Test method for null RegistrationNumber.valueOf method.
     */
    @Test
    void testRegistrationNumberNullValueOf() {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.valueOf(null));
    }

    /**
     * Test method for invalid RegistrationNumber.valueOf method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"AB06 123", "AB06123", "A B C"})
    void testRegistrationNumberInvalidValueOf(String regNumber) {
        assertThrows(IllegalArgumentException.class, () -> RegistrationNumber.valueOf(regNumber));
    }

    /**
     * Test method for duplicate RegistrationNumber.valueOf method.
     */
    @Test
    void testRegistrationNumberDuplicateValueOf() {
        RegistrationNumber.valueOf("AB07 BBC");
        assertThrows(IllegalStateException.class, () -> RegistrationNumber.valueOf("AB07 BBC"));
    }

}
