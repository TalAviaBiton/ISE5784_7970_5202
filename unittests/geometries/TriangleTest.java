package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.awt.*;
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
     @Test
    void testFindIntersections() {

    }

}