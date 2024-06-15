package geometries;
/**
 * This class represents a plane
 */

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;


public class Plane implements Geometry {
    /**
     * parameter to represent a point in the plane
     */
    private Point q;

    /**
     * parameter to represent the normal for the plane
     */
    private Vector normal;

    /**
     * Constructor to initialize the plane
     *
     * @param p1 the first point
     * @param p2 second point value
     * @param p3 third point value
     */
    public Plane(final Point p1, final Point p2, final Point p3) {
        q = p1;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p3.subtract(p2);
        Vector v3 = p2.subtract(p1);
        if (p1.equals(p2) || (Math.abs(v1.dotProduct(v2)) == 1 && Math.abs(v1.dotProduct(v3)) == 1 && Math.abs(v3.dotProduct(v2)) == 1))
            throw new IllegalArgumentException("Error: the points of the plane are on the same line");
        normal = (v1.crossProduct(v2)).normalize();

    }

    /**
     * Constructor to initialize the plane
     *
     * @param q      the  point
     * @param normal the normal for the plane
     */
    public Plane(Vector normal, Point q) {
        this.normal = normal;
        this.q = q;
    }

    /**
     * a method to normalize the normal
     *
     * @return the normal of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * a method to return q
     *
     * @return q
     */
    public Point getQ() {
        return q;
    }

    /**
     * @param point the point that the normal is going throw
     * @return the normal of the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
     @Override
   public List<Point> findIntersections(Ray ray) {

         double t= this.normal.dotProduct(q.subtract(ray.getHead()))/(normal.dotProduct(ray.getDirection()));
         return new LinkedList<>(List.of(
                         ray.getHead().add(ray.getDirection().scale(t))));

   }
}
