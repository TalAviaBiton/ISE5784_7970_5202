package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PointTest {

    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);
    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);

    @Test
    void testSubtract() {
        // Subtract points
        assertEquals(p2.subtract(p1),v1,"ERROR: (point2 - point1) does not work correctly");
        assertThrows(
                IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "ERROR: (point - itself) does not throw an exception");
    }

    @Test
    void testAdd() {
        // Add vector to point
        assertEquals(p1.add(v1),p2,"ERROR: (point + vector) = other point does not work correctly");
        assertEquals(
                p1.add(v1Opposite),
                Point.ZERO,
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        // distances
        assertEquals(0, p1.distanceSquared(p1),"ERROR: point squared distance to itself is not zero");
        assertEquals(
                0,
                p1.distanceSquared(p3) - 9,
                "ERROR: squared distance between points is wrong");
        assertEquals(
                0,
                p3.distanceSquared(p1) - 9,
                "ERROR: squared distance between points is wrong");

    }

    @Test
    void testDistance() {
        assertEquals(0, p1.distance(p1),"ERROR: point distance to itself is not zero");
        assertEquals(0, p1.distance(p3) - 3,"ERROR: distance between points to itself is wrong");
        assertEquals(0, p3.distance(p1) - 3,"ERROR: distance between points to itself is wrong");

    }

}