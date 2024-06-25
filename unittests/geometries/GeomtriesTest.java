package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeomtriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {

        Point[] points1 =
                {new Point(7, 3, 0),
                        new Point(4, 5, 0),
                        new Point(6, 7, 0)
                };
        Point[] points2 =
                {new Point(4, 1, -1),
                        new Point(-4, 1, -1),
                        new Point(0, 1, 4)
                };
        Triangle triangle123 = new Triangle(points1[0], points1[1], points1[2]);
        Triangle triangle4=new Triangle(points2[0], points2[1], points2[2]);
        Sphere sphere134 = new Sphere(new Point(1, 7, 0), 1);
        Sphere sphere2 = new Sphere(new Point(0, 4, 0), 1);
        Plane plane14 = new Plane(
                new Point(-25, 0, 0),
                new Point(0, 0, 10), new Point(-10, 0, 0));
        Plane plane24 = new Plane(
                new Point(1, -4, 0),
                new Point(4, -5, 0), new Point(2, -2, 0));


        // ============ Equivalence Partitions Tests ==============
        //TC01: some (but not all) of the shapes has intersection
        Geometries geometries1 = new Geometries();
        geometries1.geometries.add(sphere134);
        geometries1.geometries.add(triangle123);
        geometries1.geometries.add(plane14);

        List<Point> expected1 = List.of(
                new Point(1, 8, 0),
                new Point(1,6, 0),
                new Point(1, 0, 0));

        Ray ray = new Ray(new Point(1, -5, 0), new Vector(0, 15, 0));
        assertEquals(
                expected1,
                geometries1.findIntersections(ray),
                "Error: doesn't return the right intersection points " +
                        "when some (but not all) of the shapes has intersection");

        // =============== Boundary Values Tests ==================
        //TC02: no shape has intersection
        Geometries geometries2 = new Geometries();
        geometries2.geometries.add(sphere2);
        geometries2.geometries.add(triangle123);
        geometries2.geometries.add(plane24);
        assertNull(
                geometries2.findIntersections(ray),
                "ERROR: doesn't return null when no shape has intersection");

        //TC03: only one shape has intersection
        Geometries geometries3 = new Geometries();
        geometries3.geometries.add(sphere134);
        geometries3.geometries.add(triangle123);
        geometries3.geometries.add(plane24);

        List<Point> expected3 = List.of(
                new Point(1, 8, 0),
                new Point(1,6, 0));

        assertEquals(
                expected3,
                geometries3.findIntersections(ray),
                "Error: doesn't return the right intersection points when only one shape has intersection");

        //TC04: all shapes has intersection
        Geometries geometries4 = new Geometries();
        geometries4.geometries.add(sphere134);
        geometries4.geometries.add(triangle4);
        geometries4.geometries.add(plane14);

        List<Point> expected4 = List.of(
                new Point(1, 8, 0),
                new Point(1,6, 0),
                new Point(1,1,0),
                new Point(1,0,0));

        assertEquals(
                expected4,
                geometries4.findIntersections(ray),
                "Error: doesn't return the right intersection points when all shapes has intersection");

        //TC05: empty collection
        Geometries geometries5 = new Geometries();
        assertNull(
                geometries5.findIntersections(ray),
                "ERROR: doesn't return null when the geometries collection is empty");

    }

}
