package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing tube
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(ray, 4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: ensure there are no exceptions
        assertDoesNotThrow(
                () -> tube.getNormal(new Point(0, 1, 1)),
                "Error: tube get normal sends an exception");

        // TC02: ensure that the normal is normalized
        assertEquals(
                1,
                tube.getNormal(new Point(7, 1, 0)).length(),
                "ERROR: the normal of the tube is not normalize");

        // =============== Boundary Values Tests ==================
        // TC03: ensure that sends an exception if the two points are vertical to the direction vector of the tube
        assertThrows(
                IllegalArgumentException.class,
                () -> tube.getNormal(new Point(4, 0, 0)),
                "ERROR: the two points are vertical to the direction vector of the tube");

    }

    @Test
    void testFindIntersections() {

    }
}