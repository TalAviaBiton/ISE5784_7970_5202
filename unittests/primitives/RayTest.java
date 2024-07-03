package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RayTest {

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}
     */
    @Test
    void getPoint() {

        // ============ Equivalence Partitions Tests ==============
        //TC01: positive distance between head and point
        Ray ray1 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 3).normalize());
        Point point1 = new Point(0, 0, 3);
        assertEquals(
                point1,
                ray1.getPoint(2),
                "ERROR: getPoint returns wrong point when the distance between head and point is positive"
        );

        //TC02: negative distance between head and point
        Ray ray2 = new Ray(new Point(0, 2, 0), new Vector(0, 1, 0).normalize());
        Point point2 = new Point(0, 1, 0);
        assertEquals(
                point2,
                ray2.getPoint(-1),
                "ERROR: getPoint returns wrong point when the distance between head and point is negative"
        );
        // =============== Boundary Values Tests ==================
        //TC03: distance between head and point = 0
        Ray ray3 = new Ray(new Point(1, 2, 3), new Vector(4, 5, 6));
        assertEquals(
                0,
                ray3.getPoint(0).distance(ray3.getHead()),
                "ERROR: getPoint returns wrong point when the distance between head and point is zero"
        );

    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(0, 1, 0));
        List<Point> list = new LinkedList<Point>();
        Point point020 = new Point(0, 2, 0);
        Point point110 = new Point(1, 1, 0);
        Point point200 = new Point(2, 0, 0);
        Point point364 = new Point(3, 6, 4);
        Point point158 = new Point(1, 5, 8);
        Point point333 = new Point(3, 3, 3);

        // ============ Equivalence Partitions Tests ==============
        //TC01: A point in the middle of the list is the one closest to the beginning of the ray

        list.add(point158);
        list.add(point200);
        list.add(point020);
        list.add(point333);
        list.add(point364);
        assertEquals(
                point020,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is in the middle returns a wrong point");

        // =============== Boundary Values Tests ==================
        //TC02: An empty list (the method should return a null value).

        list.clear();
        assertNull(
                ray.findClosestPoint(list),
                "Error: when sending an empty list doesnt return null");

        //TC03: The first point is closest to the beginning of the horn

        list.clear();
        list.add(point020);
        list.add(point158);
        list.add(point200);
        list.add(point333);
        list.add(point364);
        assertEquals(
                point020,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is the first returns a wrong point");

        //TC04: The last point is closest to the beginning of the horn

        list.clear();
        list.add(point158);
        list.add(point200);
        list.add(point364);
        list.add(point333);
        list.add(point110);
        assertEquals(
                point110,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is the last returns a wrong point");

    }
}