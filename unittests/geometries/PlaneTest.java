package geometries;

import primitives.Point;
import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing plane
 */
class PlaneTest {

    /** Test method for {@link geometries.Plane#Plane(Point, Point, Point)}  */

    /**Test method for {@link geometries.Plane#Plane(Vector, Point)}    */
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
                plane.getNormal().dotProduct(new Vector(1, 1, 1)),
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
     @Test
    void testFindIntersections() {
         Plane plane = new Plane(new Vector(3, 3, 3), new Point(1, 1, 1));
         // ============ Equivalence Partitions Tests ==============
         //TC01: Ray crosses the plane (1 point)
         var exp01= List.of(new Point (1.6, 0.4, 1));
         assertEquals(
                 exp01,
                 plane.findIntersections(
                         new Ray(
                                 new Point(1, 0, 1),
                                 new Vector(3,2, 0).normalize())),
                 "ERROR: Ray crosses the plane returns a wrong point");
         //TC02: Ray doesn't cross the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(1, 2, 3),
                                 new Vector(6, 0, 7).normalize())),
                                "ERROR: Ray doesn't cross the plane returns a point");
         // =============== Boundary Values Tests ==================
         //TC11: Ray is parallel to the plane and the direction vector is on the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(0, 0, -9),
                                 new Vector(-6, 5, 1).normalize())),
                 "ERROR: Ray is parallel to the plane and the direction vector is on the plane returns a point");
         //TC12: Ray is parallel to the plane and the direction vector isn't on the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(1, -1, 2),
                                 new Vector(1, -4, 3).normalize())),
                 "ERROR: Ray is parallel to the plane and the direction vector isn't on the plane returns a point");
         //TC13: Ray is vertical to the plane and starts before the plane (1 point)
         var exp13= List.of(new Point (55, 54, 54));
         assertEquals(
                 exp13,
                 plane.findIntersections(
                         new Ray(
                                 new Point (1,0,0),
                                 new Vector(6, 6, 6).normalize())),
                 "ERROR: Ray is vertical to the plane and starts before the plane returns a wrong point");
         //TC14: Ray is vertical to the plane and starts on the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(2, 1, 0),
                                 new Vector(6, 6, 6).normalize())),
                 "ERROR: Ray is vertical to the plane and starts on the plane returns a point");
         //TC15: Ray is vertical to the plane and starts after the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(4, 8, 7),
                                 new Vector(6, 6, 6).normalize())),
                 "ERROR: Ray is vertical to the plane and starts after the plane returns a point");
         //TC16: Ray starts on the plane's head point and isn't vertical or parallel to the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(1, 1, 1),
                                 new Vector(6, 7, 0).normalize())),
                 "ERROR: Ray starts on the plane's head point and isn't vertical or parallel to the plane returns a point");
         //TC17: Ray starts on the plane and isn't vertical or parallel to the plane (0 points)
         assertNull(
                 plane.findIntersections(
                         new Ray(
                                 new Point(2, 2, -1),
                                 new Vector(6, 7, 0).normalize())),
                 "ERROR: Ray starts on the plane  and isn't vertical or parallel to the plane returns a point");
     }
}