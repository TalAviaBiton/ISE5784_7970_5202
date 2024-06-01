package geometries;

import primitives.Point;
import org.junit.jupiter.api.Test;
import primitives.Vector;

import java.security.InvalidAlgorithmParameterException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing plane
 */
class PlaneTest {

    /** Test method for {@link geometries.Plane#Plane(Point, Point, Point)}  */
    /**
     * Test method for {@link geometries.Plane#Plane(Vector, Point)}
     */
    public PlaneTest() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 1, 1);
        Point p3 = new Point(2, 1, 1);

        // TC01: ensure that sends an exception if the two points of the plane are equal
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Plane plane = new Plane(p1, p2, p3);
                },
                "ERROR: testGetNormal() two points of the plane are equal");

        Point p4 = new Point(1, 0, 0);
        Point p5 = new Point(2, 0, 0);
        Point p6 = new Point(3, 0, 0);

        // TC02: ensure that sends an exception if the three points of the plane are on the same line
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Plane plane = new Plane(p6, p4, p5);
                },
                "ERROR: testGetNormal() three points of the plane are on the same line");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        Plane plane = new Plane(new Point(1, 1, 1), new Point(3, 4, 2), new Point(3, 3, 3));
        // TC03: ensure that sends an exception if normal is not vertical to plane
        assertEquals(
                0,
                (plane.getNormal()).dotProduct(new Vector(1, 1, 1)),
                "ERROR: testGetNormal() normal is not vertical to plane");

        // TC04: ensure that the normal is normalized
        assertEquals(1, plane.getNormal().length(), "ERROR: the normal of the plane is not normalize");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    void testGetNormal2() {
        Plane plane = new Plane(new Point(1, 1, 1), new Point(3, 4, 2), new Point(3, 3, 3));
        // TC05: ensure that sends an exception if normal is not vertical to plane
        assertEquals(
                0,
                (plane.getNormal()).dotProduct(new Vector(1, 1, 1)),
                "ERROR: testGetNormal() normal is not vertical to plane");

        // TC06: ensure that the normal is normalized
        assertEquals(1, plane.getNormal().length(), "ERROR: the normal of the plane is not normalize");

    }
}