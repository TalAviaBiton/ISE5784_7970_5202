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
        assertNull( geomtries.findIntersection(ray),"ERROR: doesnt return null when there are no intersections");
        //TC03: only one shape has intersection
        List<Intersectable> geomtries3=new LinkedList<Intersectable>();
        Point[] points3 =
                {       new Point(-1.46, -3.04, 0),
                        new Point(-2.32, 0, 0),
                        new Point(0, -3, 0)
                };
        Triangle triangle3 = new Triangle(points[0], points[1], points[2]);

        Sphere sphere3 = new Sphere(new Point(1.57,1.68,0), 2);
        Sphere sphere3.1 = new Sphere(new Point(-3.4,3.13,0), 1.5);
        geomtries3.add(sphere3);
        geomtries3.add(sphere3.1);
        geomtries3.add(triangle3);

        Ray ray3=new Ray(new Point(5.26,-6.39,0),new Vector(-2.14,10.69,0));
        //TC04: all shapes has intersection
        //TC05: empty collection
        assertNull(geomtries.findIntersection(ray),"ERROR: doesnt return null when the geometries collection is empty");

    }

}
