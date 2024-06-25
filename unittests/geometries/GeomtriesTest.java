package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeomtriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {

        Sphere sphere1 = new Sphere(new Point(1, 7, 0), 1);
        Sphere sphere134 = new Sphere(new Point(1, 1, 0), 2);
        Sphere sphere2 = new Sphere(new Point(0, 4, 0), 1);
        Sphere sphere3 = new Sphere(new Point(-3, 3, 0), 1.5);

        Point[] points1 =
                {new Point(7, 3, 0),
                        new Point(4, 5, 0),
                        new Point(6, 7, 0)
                };
        Triangle triangle1 = new Triangle(points1[0], points1[1], points1[2]);
        Point[] points2 =
                {new Point(-4, 1, 0),
                        new Point(-4, 4, 0),
                        new Point(-2, 2, 0)
                };
        Triangle triangle2 = new Triangle(points2[0], points2[1], points2[2]);

        Point[] points3 =
                {new Point(-1, -3, 0),
                        new Point(-2, 0, 0),
                        new Point(0, -3, 0)
                };
        Triangle triangle134 = new Triangle(points3[0], points3[1], points3[2]);


        Plane plane14 = new Plane(
                new Point(-25, 0, 0),
                new Point(0, 0, 10), new Point(-10, 0, 0));
        Plane plane2 = new Plane(
                new Point(1, -4, 0),
                new Point(4, -5, 0), new Point(2, -2, 0));


        // ============ Equivalence Partitions Tests ==============
        //TC01: some (but not all) of the shapes has intersection
        Geometries geometries1 = new Geometries();
        geometries1.geometries.add(sphere1);
        geometries1.geometries.add(triangle1);
        geometries1.geometries.add(plane14);
        geometries1.geometries.add(triangle134);

        List<Point> expected1 = new LinkedList<>(List.of(
                new Point(1, 8, 0),
                new Point(1,6, 0),
                new Point(1, 0, 0)));

        Ray ray1 = new Ray(new Point(1, -5, 0), new Vector(0, 15, 0));
        assertEquals(
                expected1,
                geometries1.findIntersections(ray1),
                "Error: doesn't return the right intersection points " +
                        "when some (but not all) of the shapes has intersection");

        // =============== Boundary Values Tests ==================
        //TC02: no shape has intersection
        Geometries geometries2 = new Geometries();
        geometries2.geometries.add(plane2);
        geometries2.geometries.add(sphere2);
        geometries2.geometries.add(triangle2);
        Ray ray2 = new Ray(new Point(-9.15, 7.74, 0), new Vector(7.15, 2.26, 0));
        assertNull(
                geometries2.findIntersections(ray2),
                "ERROR: doesnt return null when no shape has intersection");

        //TC03: only one shape has intersection
        Geometries geometries3 = new Geometries();
        geometries3.geometries.add(sphere134);
        geometries3.geometries.add(sphere3);
        geometries3.geometries.add(triangle134);

        List<Point> expected3 = new LinkedList<>(List.of(
                new Point(2.36, -0.15, 0),
                new Point(1.68, 3.68, 0)));

        Ray ray3 = new Ray(new Point(3.49, -6.47, 0), new Vector(-2.29, 12.82, 0));
        assertEquals(
                expected3,
                geometries3.findIntersections(ray3),
                "Error: doesnt return the right intersection points when only one shape has intersection");

        //TC04: all shapes has intersection
        Geometries geometries4 = new Geometries();
        geometries4.geometries.add(sphere134);
        geometries3.geometries.add(plane14);
        geometries4.geometries.add(triangle134);

        List<Point> expected4 = new LinkedList<>(List.of(
                new Point(-1.53, -2.77, 0),
                new Point(-1.15, -1.51, 0),
                new Point(-0.36, 1.15, 0),
                new Point(0.25, 3.19, 0),
                new Point(-0.9, -0.65, 0)));

        Ray ray4 = new Ray(new Point(-2.24, -5.13, 0), new Vector(3.44, 11.49, 0));
        assertEquals(
                expected4,
                geometries4.findIntersections(ray4),
                "Error: doesnt return the right intersection points when all shapes has intersection");

        //TC05: empty collection
        Geometries geometries5 = new Geometries();
        assertNull(
                geometries5.findIntersections(ray1),
                "ERROR: doesnt return null when the geometries collection is empty");

    }

}
