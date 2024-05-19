package geometries;
import primitives.Point;
import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    public PlaneTest(Point p1, Point p2, Point p3){
    assertEquals(
            p1,
            p2,
            "ERROR: testGetNormal() two points of the plane are equal");
   assertEquals(
                true,
                p1.isOnSameLine(p2) || p1.isOnSameLine(p3) || p2.isOnSameLine(p3),
                 "ERROR: testGetNormal() two points of the plane are on the same line");


    }

    @Test
    void testGetNormal() {
         Plane plane = new Plane(new Point(1,1,1), new Point(2,2,2), new Point(3,3,3));
         assertEquals(
                0,
                 (plane.getNormal()).dotProduct(new Vector(1,1,1)),
                "ERROR: testGetNormal() normal is not vertical to plane");

    }

    @Test
    void testGetNormal2() {
    }
}