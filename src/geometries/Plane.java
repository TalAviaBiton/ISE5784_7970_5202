package geometries;
/**
 * This class represents a plane
 */

import primitives.Point;
import primitives.Vector;

public class  Plane implements Geometry{
    /** parameter to represent a point in the plane */
    private Point q;

    /** parameter to represent the normal for the plane */
    private Vector normal;

    /**
     * Constructor to initialize the plane
     * @param p1 the first point
     * @param p2 second point value
     * @param p3 third point value
     */
    public Plane(final Point p1, final Point p2, final Point p3)
    {
        q = p1;
        if(p1.equals(p2) || p1.isOnSameLine(p2) || p1.isOnSameLine(p3) || p2.isOnSameLine(p3))
            throw new IllegalArgumentException("Error: the points of the plane are on the same line");
        normal=(p1.subtract(p2).crossProduct(p3.subtract(p2))).normalize();

    }

    /**
     * Constructor to initialize the plane
     * @param q the  point
     * @param normal the normal for the plane
     */
    public Plane(Vector normal, Point q) {
        this.normal = getNormal(normal);
        this.q = q;
    }

    /**
     * a method to normalize the normal
     * @return     the normal normalized
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * a method to return q
     * @return     q
     */
    public Point getQ() {
        return q;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}
