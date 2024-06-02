package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * testing vector
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}  Vector(Double 3).
     */
    @Test
    public void testConstructor() {

        // =============== Boundary Values Tests ==================
        // TC01: trying to create zero vector (0,0,0)
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");

        // TC02: trying to create zero vector (Double3.ZERO
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception");

    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // TC03: checking if the calculation of length squared is right
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void testLength() {
        // TC04: checking if the calculation of length is right
        Vector v1 = new Vector(1, 2, 2);
        assertEquals(3, v1.length(), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v2 = new Vector(-2, -4, -6);
        // TC05: checking if the calculation of dot product for orthogonal vectors is zero
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");

        // TC06: checking if the calculation of dot product is right
        assertEquals(0, v1.dotProduct(v2) + 28, "ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v2 = new Vector(-2, -4, -6);

        // TC05: checking if the calculation of cross product for parallel throws an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");
        Vector vr = v1.crossProduct(v3);

        // TC06: checking if the calculation of dot product is orthogonal to its operands
        assertEquals(
                0,
                vr.dotProduct(v1) + vr.dotProduct(v3),
                "ERROR: dotProduct() result is not orthogonal to its operands");
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        // TC07: checking if the calculation of normalized vector is a unit vector
        assertEquals(
                1,
                u.length(),
                "ERROR: the normalized vector is not a unit vector");

        // TC08: checking if the calculation of normalized vector is parallel to the original one
        assertThrows(
                IllegalArgumentException.class,
                () -> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        // TC09: checking if the calculation of normalized vector is not opposite to the original one
        assertFalse(
                v.dotProduct(u) < 0,
                "ERROR: the normalized vector is opposite to the original one");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 2);
        Vector v2 = new Vector(3, 6, 6);
        // TC10: checking if the calculation of scale is right
        assertEquals(
                v2,
                v1.scale(3),
                "ERROR: scale() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 0, 0);
        // TC11: checking if the calculation of add is right
        assertEquals(
                new Vector(2, 2, 3),
                v1.add(v2),
                "ERROR: add() wrong value");
        // TC12: checking that vector +-itself throws an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + -itself does not throw an exception");
    }

}