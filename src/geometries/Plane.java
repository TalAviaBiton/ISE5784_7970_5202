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
     * @param x the first point
     * @param y second point value
     * @param z third point value
     */
    public Plane(final Point x, final Point y, final Point z)
    {
        q = x;
        normal=null;
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
        return normal.normalize();

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
        return normal.normalize();
    }
}
