package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing cylinder
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));
        Cylinder cylinder = new Cylinder(3, 4, ray);

        // ============ Equivalence Partitions Tests ==============

        // TC01: ensure there are no exceptions
        assertDoesNotThrow(
                () -> cylinder.getNormal(new Point(0, 1, 1)),
                "Error: cylinder get normal sends an exception");

        // TC02: ensure that the normal is normalized
        assertEquals(
                1,
                cylinder.getNormal(new Point(3, 1, 0)).length(),
                "ERROR: the normal of the tube is not normalize");

        // =============== Boundary Values Tests ==================

        // TC03: ensure that sends an exception if the point is on the base of the cylinder
        assertThrows(
                IllegalArgumentException.class,
                () -> cylinder.getNormal(new Point(2, 0, 0)),
                "ERROR: the point is on the base of the cylinder");

        // TC04: ensure that sends an exception if the point is on the base of the cylinder
        assertThrows(
                IllegalArgumentException.class,
                () -> cylinder.getNormal(new Point(2, 3, 0)),
                "ERROR: the point is on the base of the cylinder");


    }

    @Test
    void testFindIntersections() {

    }
}