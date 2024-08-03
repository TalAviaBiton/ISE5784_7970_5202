package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;


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

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getHead()))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        Vector u = center.subtract(ray.getHead());
        double tm = alignZero(ray.getDirection().dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        if (d >= radius)
            return null;
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        if (alignZero(tm - th) > 0) {
            if (alignZero(tm + th) > 0) {
                return List.of(
                        new GeoPoint(this, ray.getPoint(alignZero(tm + th))),
                        new GeoPoint(this, ray.getPoint(alignZero(tm - th)))
                );
            } else {
                return List.of(new GeoPoint(this,ray.getPoint(alignZero(tm - th))));
            }
        } else {
            if (alignZero(tm + th) > 0) {
                return List.of(new GeoPoint(this,ray.getPoint(alignZero(tm + th))));
            } else {
                return null;
            }
        }

    }
}
