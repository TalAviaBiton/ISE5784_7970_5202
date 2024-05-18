package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    public void testConstructor(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vector(0,0,0),
                "ERROR: zero vector does not throw an exception");

    }

    @Test
    void testLengthSquared() {
        Vector v1=new Vector(1,2,3);
        assertEquals(14,v1.lengthSquared(),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector v1=new Vector(1,2,2);
        assertEquals(3,v1.length(),"ERROR: length() wrong value");
    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals(0, v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(0, v1.dotProduct(v2)+28,"ERROR: dotProduct() wrong value");
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v2 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class,
                ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");
        Vector vr = v1.crossProduct(v3);
        //assertEquals(3,vr.length(),"ERROR: crossProduct() wrong value");
        //assertEquals(0,
                //vr.length() - (v1.length() * v3.length()),
               // "ERROR: crossProduct() wrong result length");
        assertEquals(
                0,
                vr.dotProduct(v1)*vr.dotProduct(v3),
                "ERROR: dotProduct() result is not orthogonal to its operands");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(1,u.length(),"ERROR: the normalized vector is not a unit vector");
        assertThrows(
                IllegalArgumentException.class,
                ()->v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
        assertEquals(3,v.dotProduct(u),"ERROR: the normalized vector is opposite to the original one");
    }

    @Test
    void testScale() {
        Vector v1=new Vector(1,2,2);
        Vector v2 = new Vector(3,6,6);
        assertEquals(v2,v1.scale(3),"ERROR: scale() wrong value");
    }

    @Test
    void testAdd() {
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(1,0,0);
        assertEquals(new Vector(2,2,3),v1.add(v2));
        assertThrows(
                IllegalArgumentException.class,
                ()->v1.add(new Vector(-1,-2,-3)),
                "ERROR: Vector + -itself does not throw an exception");
    }

}