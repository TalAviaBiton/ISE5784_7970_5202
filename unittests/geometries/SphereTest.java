package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing sphere
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(new Point(2, 0, 0), 4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: ensure there are no exceptions
        assertDoesNotThrow(
                () -> sphere.getNormal(new Point(6, 0, 0)),
                "Error: sphere get normal sends an exception");

        // TC02: ensure that the normal is normalized
        assertEquals(
                1,
                sphere.getNormal(new Point(3, 0, 0)).length(),
                "ERROR: the normal of the sphere is not normalize");

    }
}