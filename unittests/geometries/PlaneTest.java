package geometries;
import primitives.Point;
import org.junit.jupiter.api.Test;
import primitives.Vector;

import java.security.InvalidAlgorithmParameterException;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    public PlaneTest(){
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,1,1);
        Point p3=new Point(2,1,1);

        assertThrows(
            IllegalArgumentException.class,
            () -> {Plane plane = new Plane(p1, p2, p3);},
            "ERROR: testGetNormal() two points of the plane are equal");

        Point p4=new Point(1,0,0);
        Point p5=new Point(2,0,0);
        assertThrows(
            InvalidAlgorithmParameterException.class,
            () -> {Plane plane = new Plane(p1, p4, p5);},
            "ERROR: testGetNormal() two points of the plane are on the same line");
//   assertEquals(
//                true,
//                p1.isOnSameLine(p2) || p1.isOnSameLine(p3) || p2.isOnSameLine(p3),
//                 "ERROR: testGetNormal() two points of the plane are on the same line");


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