package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeomtriesTest {

    @Test
    void testFindIntersections() {
        List<Intersectable> geomtries=new LinkedList<Intersectable>();

        // ============ Equivalence Partitions Tests ==============
        //TC01: some (but not all) of the shapes has intersection

        // =============== Boundary Values Tests ==================
        //TC02: no shape has intersection
        Plane plane = new Plane(
                new Point(1,-4,0),
                new Point(3.64,-4.75,0), new Point(2.34, -2.26, 0));

        Point[] points =
                {       new Point(-4.3, 1.33, 0),
                        new Point(-3.89, 4.13, 0),
                        new Point(-2.32, 1.66, 0)
                };
        Triangle triangle = new Triangle(points[0], points[1], points[2]);

        Sphere sphere = new Sphere(new Point(0,4,0), 1);
        Ray ray=new Ray(new Point(-9.15,7.74,0),new Vector(7.15,2.26,0));
        geomtries.add(plane);
        geomtries.add(sphere);
        geomtries.add(triangle);
        geomtries.fi
        //TC03: only one shape has intersection
        //TC04: all shapes has intersection
        //TC05: empty collection
        assertNull(geomtries,"the geometries collection is empty");

    }

}