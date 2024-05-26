package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(2,0,0), new Vector(0,1,0));
        Cylinder cylinder=new Cylinder(3,4,ray);
        assertDoesNotThrow(
                () -> cylinder.getNormal(new Point(0, 1, 1)),
                "Error: cylinder get normal sends an exception");
        assertThrows(
                IllegalArgumentException.class,
                () -> cylinder.getNormal(new Point(2,0,0)),
                "ERROR: the point is on the base of the cylinder");
        assertThrows(
                IllegalArgumentException.class,
                () -> cylinder.getNormal(new Point(2,3,0)),
                "ERROR: the point is on the base of the cylinder");

        assertEquals(
                1,
                cylinder.getNormal(new Point(2,1,0)).length(),
                "ERROR: the normal of the tube is not normalize");
    }
}