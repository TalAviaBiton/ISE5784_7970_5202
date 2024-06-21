package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;


/**
 * This class represents a sphere
 */
public class Sphere extends RadialGeometry {
    /**
     * a point for the center of the sphere
     */
    private final Point center;

    /**
     * Constructor to initialize the sphere
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * calculates the sphere;s normal in point p
     *
     * @param p the point that the normal is going throw
     * @return the sphere;s normal in point p
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    /**
     * finds all the intersections of a ray and the sphere
     *
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and sphere
     */

 /**   @Override
    public List<Point> findIntersections(Ray ray) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();

        if (head.equals(center)) {
            Point p = ray.getPoint(radius);
            return List.of(p);
        }
        Vector u = center.subtract(head);
        double tm = alignZero(direction.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        if (d >= radius) {
            return null;
        }
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) {
            return null;
        } else if (t2 <= 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        } else if (t1 <= 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        } else {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }
    }**/
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector u=center.subtract(ray.getHead());
        if(center.equals(ray.getHead()))
            return List.of(ray.getPoint(radius));
        double tm=alignZero(u.dotProduct(ray.getDirection()));
        double d=alignZero(Math.sqrt(u.lengthSquared()-tm*tm));
        if (d >= radius ) //|| center.distance(ray.getHead())==radius)
            return null;
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        if(tm-th >0)
        {
            if(tm+th>0)
            {
                return List.of(
                                ray.getPoint(alignZero(tm + th)),
                                ray.getPoint(alignZero(tm - th))
                               );
            }
            else
            {
                return List.of( ray.getPoint(alignZero(tm - th)));
            }
        }
        else
        {
             return List.of( ray.getPoint(alignZero(tm + th)));
        }

    }
}
