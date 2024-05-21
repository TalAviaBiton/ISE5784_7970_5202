package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        Sphere sphere=new Sphere(new Point(2,0,0),4);
        assertDoesNotThrow(() -> sphere.getNormal(new Point(6, 0, 0)), "");
    }
}