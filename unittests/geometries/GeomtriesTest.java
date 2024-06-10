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
        List<Intersectable> geomtries1=new LinkedList<Intersectable>();

        // ============ Equivalence Partitions Tests ==============
        //TC01: some (but not all) of the shapes has intersection
        Sphere sphere1 = new Sphere(new Point(1.04,7.24,0), 1);
        Point[] points1 =
                {       new Point(6.66, 2.84, 0),
                        new Point(3.98, 5.15, 0),
                        new Point(5.89, 6.67, 0)
                };
        Triangle triangle1 = new Triangle(points[0], points[1], points[2]);
        geomtries1.add(sphere1);
        geomtries1.add(triangle1);
        geomtries4.add(sphere3);
        geomtries3.add(plane4);
        geomtries4.add(triangle3);
        List<Point> expected1=new list(
            new Point (2.58,-1.38,0)
            new Point (2.36,-0.15,0)
            new Point (1.68,3.68,0)
            new Point (1.22,6.25,0)
            new Point(0.87,8.22,0));//Im not sure 
        Ray ray1=new Ray(new Point(-2.24,-5.13,0),new Vector(3.44,11.49,0));
        assertEquals(expected1,geometries1.findIntersection(ray1),"Error: doesnt return the right intersection points");
        
        // =============== Boundary Values Tests ==================
        //TC02: no shape has intersection
        List<Intersectable> geomtries2=new LinkedList<Intersectable>();
        Plane plane2 = new Plane(
                new Point(1,-4,0),
                new Point(3.64,-4.75,0), new Point(2.34, -2.26, 0));

        Point[] points2 =
                {       new Point(-4.3, 1.33, 0),
                        new Point(-3.89, 4.13, 0),
                        new Point(-2.32, 1.66, 0)
                };
        Triangle triangle2 = new Triangle(points[0], points[1], points[2]);

        Sphere sphere2 = new Sphere(new Point(0,4,0), 1);
        Ray ray2=new Ray(new Point(-9.15,7.74,0),new Vector(7.15,2.26,0));
        geomtries2.add(plane2);
        geomtries2.add(sphere2);
        geomtries2.add(triangle2);
        assertNull( geomtries2.findIntersection(ray2),"ERROR: doesnt return null when there are no intersections");
        
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

        Ray ray3=new Ray(new Point(3.49,-6.47,0),new Vector(-2.29,12.82,0));
        List<Point> expected3=new list(
            new Point (2.36,-0.15,0)
            new Point(1.68,,3.68,0));//Im not sure 
        assertEquals(expected3,geometries3.findIntersection(ray3),"Error: doesnt return the right intersection points")
        
        //TC04: all shapes has intersection
        List<Intersectable> geomtries4=new LinkedList<Intersectable>();
         Plane plane4 = new Plane(
                new Point(-4,0,0),
                new Point(0,0,5.72), new Point(2.27, -1.42, 0));
        geomtries4.add(sphere3);
        geomtries3.add(plane4);
        geomtries4.add(triangle3);
        List<Point> expected4=new list(
            new Point (-1.53,-2.77,0)
            new Point (-1.15,-1.51,0)
            new Point (-0.36,1.15,0)
            new Point (0.25,3.19,0)
            new Point(-0.9,-0.65,0));//Im not sure 
        Ray ray4=new Ray(new Point(-2.24,-5.13,0),new Vector(3.44,11.49,0));
        assertEquals(expected4,geometries4.findIntersection(ray4),"Error: doesnt return the right intersection points");
        
        //TC05: empty collection
        assertNull(geomtries.findIntersection(ray),"ERROR: doesnt return null when the geometries collection is empty");

    }

}
