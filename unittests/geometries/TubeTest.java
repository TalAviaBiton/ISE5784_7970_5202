package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(2,0,0), new Vector(0,1,0));
        Tube tube=new Tube(ray, 4);
        assertDoesNotThrow(() -> tube.getNormal(new Point(0, 1, 1)), "");
        assertThrows(
                IllegalArgumentException.class,
                () -> tube.getNormal(new Point(4, 0, 0)),
                "ERROR: the two points are vertical to the direction vector of the tube");

    }
}