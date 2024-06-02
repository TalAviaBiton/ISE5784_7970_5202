package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * testing point
 */
class PointTest {

    /**
     * points and vectors for the tests of point
     */
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2, 4, 6);
    Point p3 = new Point(2, 4, 5);
    Vector v1 = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // TC01: checking if the calculation of subtract is right
        assertEquals(
                v1,
                p2.subtract(p1),
                "ERROR: (point2 - point1) does not work correctly");

        // TC02: checking that point -itself throws an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "ERROR: (point - itself) does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}
     */
    @Test
    void testAdd() {
        // TC03: checking if the calculation of add works right
        assertEquals(
                p2,
                p1.add(v1),
                "ERROR: (point + vector) = other point does not work correctly");
        // TC04: checking if the calculation of point +vector= (0,0,0) works right
        assertEquals(
                Point.ZERO,
                p1.add(v1Opposite),
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}
     */
    @Test
    void testDistanceSquared() {
        // TC05: checking if the calculation of distanceSquared between point to itself is 0
        assertEquals(
                0,
                p1.distanceSquared(p1),
                "ERROR: point squared distance to itself is not zero");

        // TC06: checking if the calculation of distanceSquared is right
        assertEquals(
                9,
                p1.distanceSquared(p3),
                "ERROR: squared distance between points is wrong");

        // TC07: checking if the calculation of distanceSquared (between the same points as TC06) is right
        assertEquals(
                9,
                p3.distanceSquared(p1) ,
                "ERROR: squared distance between points is wrong");

    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}
     */
    @Test
    void testDistance() {

        // TC08: checking if the calculation of distance between point to itself is 0
        assertEquals(0,
                p1.distance(p1),
                "ERROR: point distance to itself is not zero");

        // TC09: checking if the calculation of distance is right
        assertEquals(
                3,
                p1.distance(p3) ,
                "ERROR: distance between points is wrong");

        // TC10: checking if the calculation of distance (between the same points as TC06) is right
        assertEquals(
                3,
                p3.distance(p1) ,
                "ERROR: distance between points is wrong");

    }

}