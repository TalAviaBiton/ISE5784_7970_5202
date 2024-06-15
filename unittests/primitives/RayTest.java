package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void getPoint() {

        // ============ Equivalence Partitions Tests ==============
        //TC01: positive distance between head and point
        Ray ray1=new Ray(new Point(0,0,1),new Vector(0,0,3).normalize());
        Point point1=new Point(0,0,2);
        assertEquals(
                1,
                ray1.getPoint(1).distance(point1),
                "ERROR: getPoint returns wrong point when the distance between head and point is positive"
        );

        //TC02: negative distance between head and point
        Ray ray2=new Ray(new Point(0,2,0),new Vector(0,1,0).normalize());
        Point point2=new Point(0,1,0);
        assertEquals(
                -1,
                ray2.getPoint(-1).distance(point2),
                "ERROR: getPoint returns wrong point when the distance between head and point is negative"
        );
        // =============== Boundary Values Tests ==================
        //TC03: distance between head and point = 0
        Ray ray3=new Ray(new Point(1,2,3),new Vector(4,5,6));
        assertEquals(
                0,
                ray3.getPoint(0).distance(ray3.getHead()),
                "ERROR: getPoint returns wrong point when the distance between head and point is zero"
        );

    }

}