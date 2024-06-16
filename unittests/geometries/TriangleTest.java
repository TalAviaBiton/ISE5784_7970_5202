package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.List;
/**
 * testing triangle
 */
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private final double DELTA = 0.000001;

    /** Test method for {@link geometries.Triangle#getNormal(Point)} */
    @Test
    void testGetNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] points =
                {       new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)
                };
        Triangle triangle = new Triangle(points[0], points[1], points[2]);
        // ensure there are no exceptions
        assertDoesNotThrow(
                () -> triangle.getNormal(new Point(0, 0, 1)),
                "");
        // generate the test result
        Vector result = triangle.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1,
                result.length(),
                DELTA,
                "Polygon's normal is not a unit vector");

    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}
     */
     @Test
    void testFindIntersections() {
         Point[] points =
                 {       new Point(0, 0, 1),
                         new Point(1, 0, 0),
                         new Point(0, 1, 0)
                 };
         Triangle triangle = new Triangle(points[0], points[1], points[2]);
         // ============ Equivalence Partitions Tests ==============
         //TC01: Ray crosses the triangle inside the triangle (1 point)
         var exp01= List.of(new Point (1,0.5,0.5));
         assertEquals(
                 exp01,
                 triangle.findIntersections(
                         new Ray(
                                 new Point (2,0.5,0),
                                 new Vector(-1, 0, 0.5).normalize())),
                 "ERROR: Ray crosses the triangle inside the triangle returns a wrong point");
         //TC02: Ray pass out of the triangle opposite to a rib (0 points)
         assertNull(
                 triangle.findIntersections(
                         new Ray(
                                 new Point(0, 0, 4),
                                 new Vector(0, -1, 1).normalize())),
                 "ERROR: Ray pass out of the triangle opposite to a rib returns a point");
         //TC02: Ray pass out of the triangle opposite to a vertex (0 points)
         assertNull(
                 triangle.findIntersections(
                         new Ray(
                                 new Point(-1,-1,2),
                                 new Vector(-1, 1, 0).normalize())),
                 "ERROR: Ray pass out of the triangle opposite to a vertex returns a point");
         // =============== Boundary Values Tests ==================
         //TC11: Ray crosses the triangle on a rib of the triangle (0 points)
         assertNull(
                 triangle.findIntersections(
                         new Ray(
                                 new Point(2, 0, 0.5),
                                 new Vector(2, 0, 0).normalize())),
                 "ERROR: Ray crosses the triangle on a rib of the triangle returns a point");
         //TC12: Ray crosses the triangle on a vertex of the triangle (0 points)
         assertNull(
                 triangle.findIntersections(
                         new Ray(
                                 new Point(0, 0, 1),
                                 new Vector(-1, 1, 0).normalize())),
                 "ERROR: Ray crosses the triangle on a vertex of the triangle returns a point");
         //TC13: Ray crosses the continuation of a rib of the triangle (0 points)
         assertNull(
                 triangle.findIntersections(
                         new Ray(
                                 new Point(4, 4, 0),
                                 new Vector(2, 0, 0).normalize())),
                 "ERROR: Ray crosses the continuation of a rib of the triangle returns a point");

     }

}